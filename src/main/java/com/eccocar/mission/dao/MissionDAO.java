package com.eccocar.mission.dao;

import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import com.mongodb.lang.NonNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "mission"
)
public class MissionDAO {

  @Id
  private UUID uuid;

  private String name;

  @NonNull
  private Date missionStartDate;

  private Date missionEndDate;

  private Integer duration;

  @NonNull
  private String starship;

  private ArrayList<String> people;

  private Integer crew;

  private ArrayList<String> planets;

  private Double reward;

  private Double ratio;

  public MissionDAO() {
  }

  public MissionDAO(UUID uuid, String name, Date missionStartDate, String starship,
      ArrayList<String> people, Integer crew, ArrayList<String> planets, Double reward) {
    this.uuid = uuid;
    this.name = name;
    this.missionStartDate = missionStartDate;
    this.starship = starship;
    this.people = people;
    this.crew = crew;
    this.planets = planets;
    this.reward = reward;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getMissionStartDate() {
    return this.missionStartDate;
  }

  public void setMissionStartDate(Date missionStartDate) {
    this.missionStartDate = missionStartDate;
  }

  public Date getMissionEndDate() {
    return this.missionEndDate;
  }

  public void setMissionEndDate(Date missionEndDate) {
    this.missionEndDate = missionEndDate;
  }
  
  public Integer getDuration() {
    return this.duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public String getStarship() {
    return this.starship;
  }

  public void setStarship(String starship) {
    this.starship = starship;
  }

  public ArrayList<String> getPeople() {
    return this.people;
  }

  public void setPeople(ArrayList<String> people) {
    this.people = people;
  }

  public Integer getCrew() {
    return this.crew;
  }

  public void setCrew(Integer crew) {
    this.crew = crew;
  }

  public ArrayList<String> getPlanets() {
    return this.planets;
  }

  public void setPlanets(ArrayList<String> planets) {
    this.planets = planets;
  }

  public Double getReward() {
    return this.reward;
  }

  public void setReward(Double reward) {
    this.reward = reward;
  }

  public Double getRatio() {
    return this.ratio;
  }

  public void setRatio(Double ratio) {
    this.ratio = ratio;
  }

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " uuid='" + getUuid() + "'" +
      " name='" + getName() + "'" +
      " missionStartDate='" + getMissionStartDate() + "'" +
      " missionEndDate='" + getMissionEndDate() + "'" +
      " duration='" + getDuration() + "'" +
      " starship='" + getStarship() + "'" +
      " people='" + getPeople() + "'" +
      " crew='" + getCrew() + "'" +
      " planets='" + getPlanets() + "'" +
      " reward='" + getReward() + "'" +
      " ratio='" + getRatio() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(uuid, name, missionStartDate, missionEndDate, duration, starship, people, crew, planets, reward, ratio);
  }

  /**
   * Indicates whether some other object is "equal to" this one
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return false;
    } 
    if (!(o instanceof MissionDAO)) {
      return false;
    }
    MissionDAO mission = (MissionDAO) o;
    return Objects.equals(uuid, mission.uuid)
        && Objects.equals(name, mission.name)
        && Objects.equals(missionStartDate, mission.missionStartDate)
        && Objects.equals(missionEndDate, mission.missionEndDate)
        && Objects.equals(duration, mission.duration)
        && Objects.equals(starship, mission.starship) 
        && Objects.equals(people, mission.people) 
        && Objects.equals(crew, mission.crew) 
        && Objects.equals(planets, mission.planets)
        && Objects.equals(reward, mission.reward)
        && Objects.equals(ratio, mission.ratio);
  }

}
