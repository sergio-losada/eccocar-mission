package com.springgen.mission.mock;

import com.springgen.mission.dto.Mission;
import com.springgen.mission.service.MissionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.mockito.Mockito;

public class MockingMission {
  /**
   * GET method mocking
   */
  public static void get(MissionService missionService) {
    Mockito.when(missionService.get()).thenReturn(new ArrayList<>(Arrays.asList(new Mission())));
  }

  /**
   * POST method mocking
   */
  public static void post(MissionService missionService) {
    Mockito.when(missionService.post(Mockito.any(Mission.class))).thenReturn(new Mission());
  }

  /**
   * PUT method mocking
   */
  public static void put(MissionService missionService) {
    Mockito.when(missionService.put(Mockito.any(Mission.class), Mockito.any(Integer.class))).thenReturn(new Mission());
  }

  /**
   * DELETE method mocking
   */
  public static void delete(MissionService missionService) {
    Mockito.doAnswer(invocation -> {   return invocation;}).when(missionService).delete(Mockito.any(Integer.class));}

  /**
   * GET BY KEY method mocking
   */
  public static void getByKey(MissionService missionService) {
    Mockito.when(missionService.getByKey(Mockito.any(Integer.class))).thenReturn(Optional.of(new Mission()));
  }

  public static void mockingMission(MissionService missionService) {
    get(missionService);
    post(missionService);
    put(missionService);
    delete(missionService);
    getByKey(missionService);
  }
}
