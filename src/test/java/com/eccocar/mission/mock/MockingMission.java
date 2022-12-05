package com.eccocar.mission.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.mockito.Mockito;

import com.eccocar.mission.dao.MissionDAO;
import com.eccocar.mission.dto.Mission;
import com.eccocar.mission.service.MissionService;

public class MockingMission {

  public static void mockingMission(MissionService missionService) {
    get(missionService);
    getByPeople(missionService);
    post(missionService);
    getMissionByName(missionService);
    pollMissionQueue(missionService);
  }

  /**
   * GET mocking method
   */
  public static void get(MissionService missionService) {
    Mockito.when(missionService.getMissions()).thenReturn(new ArrayList<>(Arrays.asList(new Mission())));
  }

  /**
   * GET BY PEOPLE mocking method 
   */
  public static void getByPeople(MissionService missionService) {
    Mockito.when(missionService.getMissionsByPeople(Mockito.anyList())).thenReturn(new ArrayList<>(Arrays.asList(new Mission())));
  }

  /**
   * POST mocking method
   */
  public static void post(MissionService missionService) {
    Mockito.when(missionService.post(Mockito.any(MissionDAO.class))).thenReturn(new Mission());
  }

  /**
   * GET BY NAME mocking method
   */
  public static void getMissionByName(MissionService missionService) {
    Mockito.when(missionService.getMissionByName(Mockito.anyString())).thenReturn(Optional.of(new MissionDAO()));
  }

  /**
   * GET BY NAME mocking method
   */
  public static void pollMissionQueue(MissionService missionService) {
    Mockito.when(missionService.pollMissionQueue(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(new Mission());
  }

}
