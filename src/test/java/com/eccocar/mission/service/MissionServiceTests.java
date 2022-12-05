package com.eccocar.mission.service;

import static org.junit.jupiter.api.Assertions.*;

import com.eccocar.mission.dao.MissionDAO;
import com.eccocar.mission.dto.Mission;
import com.eccocar.mission.mock.MockingMission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MissionServiceTests {
  @MockBean
  private MissionService missionService;

  @BeforeEach
  public void init() {
    MockingMission.mockingMission(missionService);
  }

  /**
   * GET method service test
   */
  @Test
  @Order(1)
  public void test01_get() {
    // Testing request
    ArrayList<Mission> response = this.missionService.getMissions();
    assertEquals(response.size(), 1, "Response size must be 1");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).getMissions();
  }

  /**
   * POST method service test
   */
  @Test
  @Order(2)
  public void test02_post() {
    // Testing request
    MissionDAO mission = new MissionDAO();
    Mission response = this.missionService.post(mission);
    assertTrue(response instanceof Mission, "Response types must coincide");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).post(mission);
  }

  /**
   * GET method service test
   */
  @Test
  @Order(3)
  public void test03_getByPeople() {
    // Testing request
    ArrayList<Mission> response = this.missionService.getMissionsByPeople(Arrays.asList("1"));
    assertEquals(response.size(), 1, "Response size must be 1");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).getMissionsByPeople(Arrays.asList("1"));
  }

  /**
   * GET BY KEY method service test
   */
  @Test
  @Order(4)
  public void test04_getByName() {
    // Testing request
    Optional<MissionDAO> response = this.missionService.getMissionByName("mission");
    assertTrue(response.isPresent(), "Response entity must be present");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).getMissionByName("mission");
  }

  /**
   * POLL MISSION QUEUE method service test
   */
  @Test
  @Order(5)
  public void test05_pollMissionQueue() {
    // Testing request
    Mission response = this.missionService.pollMissionQueue("ratio", true);
    assertTrue(response instanceof Mission, "Response entity must be a Mission");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).pollMissionQueue("ratio", true);
  }

}
