package com.jimontoyag.service.impl;

import com.jimontoyag.model.*;
import com.jimontoyag.service.MissionService;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class MissionServiceImpl implements MissionService {

    private final List<Mission> currentMissions;

    public MissionServiceImpl()
    {
        this.currentMissions = new ArrayList<>();
    }

    public MissionServiceImpl(List<Mission> currentMissions)
    {
        this.currentMissions = currentMissions;
    }
    @Override
    public boolean createMission(OffsetDateTime date, Starship starship, List<People> captains, Integer passengers, List<Planet> planets, Long reward) {

        if (date == null || starship == null ||
            passengers == null || passengers < 0 ||
            captains == null || planets == null ||
            captains.isEmpty() || planets.isEmpty()
        ) return false;

        if (captains.size() + passengers < starship.crew().intValue() ||
            captains.size() + passengers > starship.passengers().intValue()
        ) return false;

        for (People captain : captains)
        {
            if (!getMissionsByCaptains(List.of(captain)).isEmpty()) return false;
        }

        if (checkMissingPilot(starship.pilots(), captains)) return false;


        long duration = getDuration(captains.size(), passengers, planets);

        currentMissions.add(new Mission(date, starship, captains, passengers, planets, duration, reward));
        return true;
    }

    @Override
    public List<Mission> getMissionsByCaptains(List<People> captains) {

        if (captains == null || captains.isEmpty()) return currentMissions;

        List<Mission> ret = new ArrayList<>();

        for (People captain : captains)
        {
            for (Mission mission : currentMissions)
            {
                if (mission.captains().contains(captain) && !ret.contains(mission)) ret.add(mission);
            }
        }

        return ret;
    }

    @Override
    public RecommendedMissions recomendMission() {
        if (currentMissions.isEmpty()) return null;

        Mission perReward = currentMissions.get(0);
        Mission perRatio = currentMissions.get(0);
        for (Mission mission : currentMissions) {
            if (mission.reward() / mission.duration() > perRatio.reward() / perRatio.duration()) perRatio = mission;
            if (mission.reward() > perReward.reward()) perReward = mission;
        }
        return new RecommendedMissions(perReward, perRatio);
    }

    private int getDuration(int captains, int crew, List<Planet> planets)
    {
        float distance = 0;
        int kmPerHour = (captains * 100) + (crew * 10);
        for (Planet planet : planets) {
            if (planet.diameter().isPresent()) distance += planet.diameter().get().intValue();
        }
        return Math.round(distance / kmPerHour);
    }

    private boolean checkMissingPilot(List<Integer> ids, List<People> captains){
        if (ids.isEmpty()) return false;
        for (int pilot : ids)
        {
            for (People captain : captains)
            {
                if (pilot == captain.id()) return false;
            }
        }
        return true;
    }
}
