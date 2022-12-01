package com.springgen.mission.controller;

import com.springgen.mission.dto.Mission;
import com.springgen.mission.service.MissionService;
import java.lang.Integer;
import java.lang.Void;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mission")
public class MissionController {
  @Autowired
  private MissionService missionService;

  /**
   * Mapping of the GET operation endpoint
   * HTTP Status code: 200 OK
   * @return ArrayList of the database records, in JSON format
   */
  @GetMapping(
      produces = "application/json"
  )
  public ResponseEntity<ArrayList<Mission>> get() {
    return ResponseEntity.status(HttpStatus.OK).body(missionService.get());
  }

  /**
   * Mapping of the POST operation endpoint
   * HTTP Status code: 201 CREATED or 409 CONFLICT
   * @param mission request body of the object to insert, in JSON format
   * @return the object inserted in the database, in JSON format
   */
  @PostMapping(
      consumes = "application/json"
  )
  public ResponseEntity<Mission> post(@RequestBody Mission mission) {
    if(missionService.getByKey(mission.getId()).isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    } else {
      this.missionService.post(mission);
      return ResponseEntity.status(HttpStatus.CREATED).body(mission);
    }
  }

  /**
   * Mapping of the PUT operation endpoint
   * HTTP Status code: 200 CREATED or 404 CONFLICT
   * @param mission request body of the object to update, in JSON format
   * @param id path variable of the Primary Key of the object to be updated
   * @return the object updated in the database, in JSON format
   */
  @PutMapping(
      value = "{id}",
      consumes = "application/json"
  )
  public ResponseEntity<Mission> put(@RequestBody Mission mission, @PathVariable("id") Integer id) {
    if(!missionService.getByKey(id).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {
      this.missionService.put(mission, id);
      return ResponseEntity.status(HttpStatus.OK).body(mission);
    }
  }

  /**
   * Mapping of the GET BY KEY operation endpoint
   * HTTP Status code: 200 OK or 404 NOT FOUND
   * @param mission request body of the object to insert, in JSON format
   * @param id path variable of the Primary Key of the object to be retrieved
   * @return the object retrieved by its Primary Key from the database, in JSON format
   */
  @GetMapping(
      value = "{id}",
      produces = "application/json"
  )
  public ResponseEntity<Mission> getByKey(@PathVariable("id") Integer id) {
    if(!missionService.getByKey(id).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {
      this.missionService.getByKey(id);
      return ResponseEntity.status(HttpStatus.OK).body(this.missionService.getByKey(id).get());
    }
  }

  /**
   * Mapping of the DELETE operation endpoint
   * HTTP Status code: 204 NO CONTENT or 404 NOT FOUND
   * @param id path variable of the Primary Key of the object to be deleted
   * @return empty response body
   */
  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    if(!missionService.getByKey(id).isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } else {
      this.missionService.delete(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
  }
}
