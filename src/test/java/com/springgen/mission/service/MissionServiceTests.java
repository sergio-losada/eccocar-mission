package com.springgen.mission.service;

import static org.junit.jupiter.api.Assertions.*;

import com.springgen.mission.dto.Mission;
import com.springgen.mission.mock.MockingMission;
import java.util.ArrayList;
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
    ArrayList<Mission> response = this.missionService.get();
    assertEquals(response.size(), 1, "Response size must be 1");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).get();
  }

  /**
   * POST method service test
   */
  @Test
  @Order(2)
  public void test02_post() {
    // Testing request
    Mission mission = new Mission();
    Mission response = this.missionService.post(mission);
    assertTrue(response instanceof Mission, "Response types must coincide");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).post(mission);
  }

  /**
   * PUT method service test
   */
  @Test
  @Order(3)
  public void test03_put() {
    // Testing request
    Mission mission = new Mission();
    Integer pk = Integer.valueOf(1);
    Mission response = this.missionService.put(mission, pk);
    assertTrue(response instanceof Mission, "Response types must coincide");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).put(mission, pk);
  }

  /**
   * DELETE method service test
   */
  @Test
  @Order(4)
  public void test04_delete() {
    // Testing request
    this.missionService.delete(Integer.valueOf(1));
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).delete(Integer.valueOf(1));
  }

  /**
   * GET BY KEY method service test
   */
  @Test
  @Order(5)
  public void test05_getByKey() {
    // Testing request
    Optional<Mission> response = this.missionService.getByKey(Integer.valueOf(1));
    assertTrue(response.isPresent(), "Response entity must be present");
    // Mockito verification that the method was only invoked once
    Mockito.verify(missionService).getByKey(Integer.valueOf(1));
  }
}
