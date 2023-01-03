package com.jimontoyag.service;

import com.jimontoyag.model.*;

import java.time.OffsetDateTime;
import java.util.List;

public interface MissionService {
    boolean createMission(OffsetDateTime date, Starship starship, List<People> captains, Integer passengers, List<Planet> planets, Long reward);

    List<Mission> getMissionsByCaptains(List<People> captains);

    RecommendedMissions recomendMission();
}
