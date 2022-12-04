package com.eccocar.mission.dto;

import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.eccocar.mission.dto.Views.ParsedMission;
import com.fasterxml.jackson.annotation.JsonView;

public class Mission {

  @JsonView(ParsedMission.class)
  private UUID uuid;
  
  @JsonView(ParsedMission.class)
  private String name;

  @JsonView(ParsedMission.class)
  private Date missionStartDate;

  @JsonView(ParsedMission.class)
  private Date missionEndDate;

  @JsonView(ParsedMission.class)
  private Integer duration;

  @JsonView(ParsedMission.class)
  private Starship starship;

  @JsonView(ParsedMission.class)
  private ArrayList<People> people;

  @JsonView(ParsedMission.class)
  private Integer crew;

  @JsonView(ParsedMission.class)
  private ArrayList<Planets> planets;

  @JsonView(ParsedMission.class)
  private Double reward;

  @JsonView(ParsedMission.class)
  private Double ratio;

  public Mission() {
    
  }

  public Mission(String name, Date missionStartDate, Starship starship,
      ArrayList<People> people, Integer crew, ArrayList<Planets> planets, Double reward) {
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

  public Starship getStarship() {
    return this.starship;
  }

  public void setStarship(Starship starship) {
    this.starship = starship;
  }

  public ArrayList<People> getPeople() {
    return this.people;
  }

  public void setPeople(ArrayList<People> people) {
    this.people = people;
  }

  public Integer getCrew() {
    return this.crew;
  }

  public void setCrew(Integer crew) {
    this.crew = crew;
  }

  public ArrayList<Planets> getPlanets() {
    return this.planets;
  }

  public void setPlanets(ArrayList<Planets> planets) {
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
    return Objects.hash(name, missionStartDate, missionEndDate, duration, starship, people, crew, planets, reward, ratio);
  }

  /**
   * Indicates whether some other object is "equal to" this one
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return false;
    } 
    if (!(o instanceof Mission)) {
      return false;
    }
    Mission mission = (Mission) o;
    return Objects.equals(name, mission.name) 
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