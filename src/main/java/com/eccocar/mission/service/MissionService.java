package com.eccocar.mission.service;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eccocar.mission.dao.MissionDAO;
import com.eccocar.mission.dto.Mission;
import com.eccocar.mission.dto.People;
import com.eccocar.mission.dto.Starship;
import com.eccocar.mission.repository.MissionRepository;
import com.eccocar.mission.utils.MissionMapper;
import com.google.common.collect.Lists;

@Service
public class MissionService {

  @Autowired
  private MissionRepository missionRepository;

  @Autowired
  private MissionMapper missionMapper;

  // FIFO queue for mission recommendation
  private static Queue<Mission> queue;

  /**
   * Applies the logic of the GET request, invoking the repository
   * in order to retrieve the list of Missions stored in the database
   * @return the whole list of Missions present in the database
   */
  public ArrayList<Mission> getMissions() {
    return this.missionMapper.getAllMissionDTO(this.missionRepository.findAll());
  }

  /**
   * Applies the logic of the GET request, invoking the repository
   * in order to retrieve the list of Missions stored in the database
   * in which the specified captains are present
   * @return the list of Missions that matches the captains' IDs specified
   */
  public ArrayList<Mission> getMissionsByPeople(List<String> people) {
    var missions = this.missionRepository.findAll();
    Set<MissionDAO> missionSet = new HashSet<>();
    for(String captain: people) {
      for(MissionDAO mission: missions) {
        if(mission.getPeople().contains("https://swapi.dev/api/people/" + captain + "/")) {
          missionSet.add(mission);
        }
      }
    }
    return this.missionMapper.getAllMissionDTO(new ArrayList<>(missionSet));
  }

  /**
   * Applies the logic of the POST request, invoking the repository
   * in order to insert in the database the Mission passed through parameter
   * @param missionDAO Mission object that is being created
   * @return the saved Mission, returned by the save() method in the repository
   */
  public Mission post(MissionDAO missionDAO) {
    // Generate Mission UUID automatically
    missionDAO.setUuid(UUID.randomUUID());

    if(missionDAO.getMissionStartDate() == null) {
      // If given date is null, current date will be assigned
      missionDAO.setMissionStartDate(new Date());
    }
    if(missionDAO.getCrew() == null) {
      // If given crew is null, 0 tripulants will be assigned
      missionDAO.setCrew(0);
    }
    
    // The mapper casts between MissionDAO and MissionDTO
    Mission mission = this.missionMapper.getMission(missionDAO);

    // Calculate duration to assign the Mission end date
    int totalHours = this.calculateMissionDuration(mission);
    Date missionEndDate = this.addHoursToDate(mission.getMissionStartDate(), totalHours);
    
    // Assign the calculated fields before inserting
    missionDAO.setMissionEndDate(missionEndDate);
    mission.setMissionEndDate(missionEndDate);
    missionDAO.setDuration(totalHours);
    mission.setDuration(totalHours);
    missionDAO.setRatio(Math.round((mission.getReward()/totalHours) * 100.0) / 100.0);
    mission.setRatio(Math.round((mission.getReward()/totalHours) * 100.0) / 100.0);
    
    // Store mission in the database
    this.missionRepository.save(missionDAO);

    return mission;
  }

  /**
   * Dequeues the missions from the recommendation queue, following
   * the order given by the specified criteria (reward or ratio)
   * @param criteria request parameter of the selected criteria (reward or ratio)
   * @param isRecommendModeEnabled whether the previous operation was a recommendation one
   * or not, in order to keep polling from the queue or requeue the existing missions
   * @return
   */
  public Mission pollMissionQueue(String criteria, boolean isRecommendModeEnabled) {
    // Recommendation criteria: REWARD 
    if(criteria.equals("reward")) {
      if(!isRecommendModeEnabled) {
        var missions = this.getMissions().stream().sorted(Comparator.comparing(Mission::getReward));
        // Reverse order for FIFO queue (First In, First Out)
        queue = new LinkedList<>(Lists.reverse(missions.collect(Collectors.toList())));
      }
      return queue.poll();
    }
    // Recommendation criteria: RATIO REWARD/HOURS
    else {
      if(!isRecommendModeEnabled) {
        var missions = this.getMissions().stream().sorted(Comparator.comparing(Mission::getRatio));
        // Reverse order for FIFO queue (First In, First Out)
        queue = new LinkedList<>(Lists.reverse(missions.collect(Collectors.toList())));
      }
      return queue.poll();
    }
  }

  /**
   * Applies the logic of the GET BY KEY request, invoking the repository
   * in order to retrieve from the database the specified object
   * @param id Primary Key of the object to retrieve
   * @return the entity whose Primary Key matches the given key, or Optional#empty() if none found
   */
  public Optional<MissionDAO> getMissionByName(String name) {
    return missionRepository.findByName(name);
  }

  /**
   * Executes the logic of the DELETE request before deleting, 
   * removing the Mission from the database permanently
   * @param uuid uuid of the mission to be deleted
   */
  public void delete(String name) {
    this.missionRepository.deleteById(this.getMissionByName(name).get().getUuid());
  }

  /**
   * Performs the validation of the mission given the specific criteria
   * @param mission the Mission object that is being validated
   * @return whether the given object is a valid Mission or not
   */
  public boolean isMissionValid(MissionDAO missionDAO) {
    Mission mission = this.missionMapper.getMission(missionDAO);
    // At least a starship (missionStartDate is assigned automatically if not present)
    if(mission.getStarship() == null) {
      return false;
    }
    // At least one or more captains and planets
    if(mission.getPeople() == null || mission.getPlanets() == null) {
      return false;  
    }
    if(mission.getPeople().isEmpty() || mission.getPlanets().isEmpty()) {
      return false;
    }
    // At least 0 or more crew members
    if(mission.getCrew() < 0) {
      return false;
    }
    // If a starship has had pilots, at least one of them must be in the mission
    RestTemplate restTemplate = new RestTemplate();
    var starship = restTemplate.getForEntity("https://swapi.dev/api/starships?name=" + mission.getStarship().getName(), Starship.class);
    // People that this starship has been piloted by
    var pilots = starship.getBody().getPilots();
    boolean isOnePilotPresent = false;
    if(pilots != null && !pilots.isEmpty()) {
      for(String pilot: pilots) {
        if(missionDAO.getPeople().contains(pilot)) {
          isOnePilotPresent = true;
        }
      }
      if(!isOnePilotPresent) {
        return false;
      }
    }
    // The number of captains and crew must be bigger than the crew required by the starship
    int crew = Integer.parseInt(mission.getStarship().getCrew().replace(",", ""));
    int pass = Integer.parseInt(mission.getStarship().getPassengers().replace(",", ""));
    if((mission.getPeople().size() + mission.getCrew()) < crew) {
      return false;
    }
    // The number of captains and crew must be smaller than the crew and passengers of the starship
    if((mission.getPeople().size() + mission.getCrew()) > (crew + pass)) {
      return false;
    }
    // A captain cannot be assigned to more than one mission at the same time
    ArrayList<People> people = this.getMissions().stream().map(missions -> missions.getPeople()).collect(Collectors.toList()).get(0);
    for(People captain: mission.getPeople()) {
      if(people.contains(captain)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Calculates the number of hours the mission will take
   * @param mission the mission whose duration is being calculated
   * @return the amount of hours the mission will take
   */
  private Integer calculateMissionDuration(Mission mission) {
    int totalMinutes = 0;
    // The mission duration is given by the sum of the diameter of the planets
    Double totalDiameter = mission.getPlanets().stream().mapToDouble(planet -> planet.getDiameter()).sum();
    Double distanceTraveled = 0.0;
    while(distanceTraveled < totalDiameter) {
      // Each crew member covers 10km/hour & each captain covers 100km/hour
      distanceTraveled += mission.getCrew()*10 + mission.getPeople().size()*100;
      totalMinutes += 60;
    }
    int totalHours = totalMinutes / 60;
    // Extra minutes (Y) are round to the next hour (X+1)
    if(totalMinutes % 60 > 0) {
      totalHours++;
    }
    // Minimum mission duration: 1 hour
    return (totalHours < 1) ? 1 :totalHours;
  }

  /**
   * Adds up the specified hours to the given date
   * @param date the initial date
   * @param hours the amount of hours to be added up
   * @return the date resulting from the sum of hours
   */
  private Date addHoursToDate(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    // Adds up the specified hours to the given date
    calendar.add(Calendar.HOUR_OF_DAY, hours);
    return calendar.getTime();
  }
  
}
