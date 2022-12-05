package com.eccocar.mission.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.eccocar.mission.dao.MissionDAO;
import com.eccocar.mission.dto.Mission;
import com.eccocar.mission.dto.People;
import com.eccocar.mission.dto.Planets;
import com.eccocar.mission.dto.Starship;

@Component
public class MissionMapper {

    /**
     * Casts a list of MissionDAO entities to Mission objects 
     * @param missionArray the array ot MissionDAO entities
     * @return array containing all the Mission objects after being casted
     */
    public ArrayList<Mission> getAllMissionDTO(List<MissionDAO> missionArray) {
        ArrayList<Mission> missions = new ArrayList<>();
        for(MissionDAO missionDAO: missionArray) {
            missions.add(this.getMission(missionDAO));
        }
        return missions;
    }

    /**
     * Casts a missionDAO entity to a Mission object
     * @param missionDAO the MissionDAO entity to be casted
     * @return the Mission object after being casted
     */
    public Mission getMission(MissionDAO missionDAO) {
        Mission mission = new Mission();
        RestTemplate restTemplate = new RestTemplate();

        // Assign the Mission fields to the MissionDTO object
        mission.setUuid(missionDAO.getUuid());
        mission.setName(missionDAO.getName());
        mission.setCrew(missionDAO.getCrew());
        mission.setDuration(missionDAO.getDuration());
        mission.setReward(missionDAO.getReward());
        mission.setMissionStartDate(missionDAO.getMissionStartDate());
        mission.setMissionEndDate(missionDAO.getMissionEndDate());
        
        // Calculate ratio reward/hours
        if(mission.getDuration() != null) {
            mission.setRatio(Math.round((missionDAO.getReward()/missionDAO.getDuration()) * 100.0) / 100.0);
        }
        
        // Fetch Starship data from its resource URL
        if(missionDAO.getStarship() != null) {
            var response = restTemplate.getForEntity(missionDAO.getStarship(), Starship.class);
            if(response.getStatusCode() == HttpStatus.OK) { 
                mission.setStarship(response.getBody());
            }
        }

        // Fetch People data from their resource URLs
        if(!missionDAO.getPeople().isEmpty()) {
            ArrayList<People> peopleArray = new ArrayList<>();
            for(String people: missionDAO.getPeople()) {
                var response = restTemplate.getForEntity(people, People.class);
                if(response.getStatusCode() == HttpStatus.OK) { 
                    peopleArray.add(response.getBody());
                }
            }
            mission.setPeople(peopleArray);
        }
        // Fetch Planets data from their resource URLs
        if(!missionDAO.getPlanets().isEmpty()) {
            ArrayList<Planets> planetsArray = new ArrayList<>();
            for(String planets: missionDAO.getPlanets()) {
                var response = restTemplate.getForEntity(planets, Planets.class);
                if(response.getStatusCode() == HttpStatus.OK) { 
                    planetsArray.add(response.getBody());
                }
            }
            mission.setPlanets(planetsArray);
        }
        return mission;
    }

}
