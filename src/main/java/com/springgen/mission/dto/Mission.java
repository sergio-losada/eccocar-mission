package com.springgen.mission.dto;

import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Mission {

  private UUID uuid;
  
  private String name;

  private Date missionStartDate;

  private Date missionEndDate;

  private Starship starship;

  private ArrayList<People> people;

  private Integer crew;

  private ArrayList<Planets> planets;

  public Mission() {
  }

  public Mission(String name, Date missionStartDate, Starship starship,
      ArrayList<People> people, Integer crew, ArrayList<Planets> planets) {
    this.name = name;
    this.missionStartDate = missionStartDate;
    this.starship = starship;
    this.people = people;
    this.crew = crew;
    this.planets = planets;
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

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " name='" + getName() + "'" +
      " missionStartDate='" + getMissionStartDate() + "'" +
      " starship='" + getStarship() + "'" +
      " people='" + getPeople() + "'" +
      " crew='" + getCrew() + "'" +
      " planets='" + getPlanets() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, missionStartDate, starship, people, crew, planets);
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
        && Objects.equals(starship, mission.starship) 
        && Objects.equals(people, mission.people) 
        && Objects.equals(crew, mission.crew) 
        && Objects.equals(planets, mission.planets);
  }


}
