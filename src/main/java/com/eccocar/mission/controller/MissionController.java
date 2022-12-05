package com.eccocar.mission.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eccocar.mission.dao.MissionDAO;
import com.eccocar.mission.dto.Mission;
import com.eccocar.mission.dto.Views.ParsedMission;
import com.eccocar.mission.service.MissionService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("mission")
public class MissionController {

  @Autowired
  private MissionService missionService;

  /**
   * Automatic detection whether the requested operation
   * is the recommendation one or not
   */
  private static boolean isRecommendModeEnabled;

  /**
   * Mapping of the GET Mission endpoint
   * It also allows querying by captains who are going to the mission
   * HTTP Status code: 200 OK
   * @return ArrayList of the Missions recorded in the database, in JSON format
   */
  @GetMapping(
      produces = "application/json"
  )
  @JsonView(ParsedMission.class)
  public ResponseEntity<ArrayList<Mission>> get(@RequestParam(required = false, value = "people") List<String> people) {
    isRecommendModeEnabled = false;
    // No captains in the request parameters of the URL
    if(people == null || people.isEmpty()) {
      return ResponseEntity.status(HttpStatus.OK).body(missionService.getMissions());
    }
    // Request filtered by captain(s) in the query string
    else {
      return ResponseEntity.status(HttpStatus.OK).body(missionService.getMissionsByPeople(people));
    }
  }

  /**
   * Mapping of the POST Mission endpoint
   * HTTP Status code: 201 CREATED or 409 CONFLICT
   * @param mission request body of the object to insert, in JSON format
   * @return the object inserted in the database, in JSON format
   */
  @PostMapping(
      consumes = "application/json"
  )
  public ResponseEntity<Mission> post(@RequestBody MissionDAO mission) {
    isRecommendModeEnabled = false;
    if(!this.missionService.isMissionValid(mission)) {
      // Invalid mission request body
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    if(missionService.getMissionByName(mission.getName()).isPresent()) {
      // Duplicated mission name
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    } 
    else {
      return ResponseEntity.status(HttpStatus.CREATED).body(this.missionService.post(mission));
    }
  }

  /**
   * Mapping of the DELETE operation endpoint
   * HTTP Status code: 204 NO CONTENT or 404 NOT FOUND
   * @param id path variable of the Primary Key of the object to be deleted
   * @return empty response body
   */
  @DeleteMapping("{name}")
  public ResponseEntity<Void> delete(@PathVariable("name") String name) {
    if(!missionService.getMissionByName(name).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {
      this.missionService.delete(name);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }

  /**
   * Mapping of the GET Mission recommendation endpoint
   * HTTP Status code: 200 OK or 400 BAD REQUEST 
   * @param criteria request parameter of the selected criteria (reward or ratio)
   * @return the recommended mission, according to the criteria specified
   */
  @GetMapping(
      value = "recommend",
      produces = "application/json"
  )
  @JsonView(ParsedMission.class)
  public ResponseEntity<Mission> getRecommendedMission(@RequestParam(value = "criteria") String criteria) {
    // Verifies that the criteria given in the request parameters of the URL is a valid one
    if(criteria.equals("reward") || criteria.equals("ratio")) {
      Mission recommendedMission = this.missionService.pollMissionQueue(criteria, isRecommendModeEnabled);
      isRecommendModeEnabled = true;
      return ResponseEntity.status(HttpStatus.OK).body(recommendedMission);
    }
    else {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }

}
