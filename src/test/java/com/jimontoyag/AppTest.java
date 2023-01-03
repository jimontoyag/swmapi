package com.jimontoyag;

import com.jimontoyag.model.*;
import com.jimontoyag.service.MissionService;
import com.jimontoyag.service.SWAService;
import com.jimontoyag.service.impl.MissionServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/** Unit test for simple App. */
public class AppTest {
  /** Rigorous Test :-) */
  private SWAService swaService;
  private MissionService missionService;

  private final List<Mission> missions = List.of(
          new Mission(null, null, List.of(new People(1, "Gabriel")), null, null, 72L, 40000L),
          new Mission(null, null, List.of(new People(1, "Gabriel"), new People(2, "Isaac")), null, null,1200L, 5000000L),
          new Mission(null, null, List.of(new People(2, "Isaac")), null, null, 45L, 300000L)
  );

  @Before
  public void setup() {
    swaService = mock(SWAService.class);
    missionService = new MissionServiceImpl(
          missions
    );
  }

  //create mission tests
  @Test
  public void create_mission_test() {

  }
  @Test
  public void date_missing_test() {

  }
  @Test
  public void starship_missing_test() {

  }
  @Test
  public void zero_captains_test() {

  }
  @Test
  public void negative_planets_test() {

  }
  @Test
  public void negative_crew_test() {

  }

  //get missions by captain tests
  @Test
  public void get_all_mission_test() {
    List<Mission> result = missionService.getMissionsByCaptains(null);
    assertEquals(missions, result);
  }
  @Test
  public void get_two_mission_test(){
    List<Mission> result = missionService.getMissionsByCaptains(List.of(new People(1, "Gabriel")));
    assertEquals(List.of(
            missions.get(0), missions.get(1)
    ), result);
  }
  @Test
  public void get_every_mission_test(){
    List<Mission> result = missionService.getMissionsByCaptains(List.of(new People(1, "Gabriel"), new People(2, "Isaac")));
    assertEquals(List.of(
            missions.get(0), missions.get(1), missions.get(2)
    ), result);
  }
  //recomend mission tests
  @Test
  public void recomend_mission_test() {

    RecommendedMissions result = missionService.recomendMission();
    assertEquals(new RecommendedMissions(
            missions.get(1), missions.get(2)
    ), result);
  }
}
