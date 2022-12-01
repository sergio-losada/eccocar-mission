package com.springgen.mission.service;

import com.springgen.mission.dto.Mission;
import com.springgen.mission.repository.MissionRepository;
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
  @Autowired
  private MissionRepository missionRepository;

  /**
   * Applies the logic of the GET request, invoking the repository
   * in order to retrieve the list of records stored in the database
   * @return the whole list of objects present in the database
   */
  public ArrayList<Mission> get() {
    return (ArrayList<Mission>)this.missionRepository.findAll();
  }

  /**
   * Applies the logic of the POST request, invoking the repository
   * in order to insert in the database the object passed through parameter
   * @param mission object that is being created
   * @return the saved object, returned by the save() method in the repository
   */
  public Mission post(Mission mission) {
    return missionRepository.save(mission);
  }

  /**
   * Applies the logic of the PUT request, invoking the repository
   * in order to update in the database the object passed through parameter
   * @param mission object that is being updated
   * @param id Primary Key of the object to update
   * @return the saved object, returned by the save() method in the repository
   */
  public Mission put(Mission mission, Integer id) {
    return missionRepository.save(mission);
  }

  /**
   * Applies the logic of the GET BY KEY request, invoking the repository
   * in order to retrieve from the database the specified object
   * @param id Primary Key of the object to retrieve
   * @return the entity whose Primary Key matches the given key, or Optional#empty() if none found
   */
  public Optional<Mission> getByKey(Integer id) {
    return missionRepository.findById(id);
  }

  /**
   * Applies the logic of the DELETE request, invoking the repository
   * in order to delete from the database the specified object
   * @param id Primary Key of the object to delete
   */
  public void delete(Integer id) {
    missionRepository.deleteById(id);
  }
}
