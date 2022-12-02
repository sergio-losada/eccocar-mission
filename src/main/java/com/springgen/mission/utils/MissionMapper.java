package com.springgen.mission.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springgen.mission.dao.MissionDAO;
import com.springgen.mission.dto.Mission;
import com.springgen.mission.dto.People;
import com.springgen.mission.dto.Planets;
import com.springgen.mission.dto.Starship;

@Component
public class MissionMapper {

    public ArrayList<Mission> getAllMissionDTO(List<MissionDAO> missionArray) {
        ArrayList<Mission> missions = new ArrayList<>();
        for(MissionDAO missionDAO: missionArray) {
            missions.add(this.getMission(missionDAO));
        }
        return missions;
    }

    public Mission getMission(MissionDAO missionDAO) {
        Mission mission = new Mission();
        RestTemplate restTemplate = new RestTemplate();

        // Assign the Mission fields to the MissionDTO object
        mission.setName(missionDAO.getName());
        mission.setCrew(missionDAO.getCrew());
        mission.setMissionStartDate(missionDAO.getMissionStartDate());
        
        // Fetch Starship data from its resource URL
        if(missionDAO.getStarship() != null) {
            var response = restTemplate.getForEntity(missionDAO.getStarship(), Starship.class);
            mission.setStarship(response.getBody());
        }
        // Fetch People data from their resource URLs
        if(!missionDAO.getPeople().isEmpty()) {
            ArrayList<People> peopleArray = new ArrayList<>();
            for(String people: missionDAO.getPeople()) {
                var response = restTemplate.getForEntity(people, People.class);
                peopleArray.add(response.getBody());
            }
            mission.setPeople(peopleArray);
        }
        // Fetch Planets data from their resource URLs
        if(!missionDAO.getPlanets().isEmpty()) {
            ArrayList<Planets> planetsArray = new ArrayList<>();
            for(String planets: missionDAO.getPlanets()) {
                var response = restTemplate.getForEntity(planets, Planets.class);
                planetsArray.add(response.getBody());
            }
            mission.setPlanets(planetsArray);
        }
        return mission;
    }
    
}
