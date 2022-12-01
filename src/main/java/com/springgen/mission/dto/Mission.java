package com.springgen.mission.dto;

import com.mongodb.lang.NonNull;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "mission"
)
public class Mission {
  @NonNull
  @Id
  private Integer id;

  private String name;

  private Date missionStartDate;

  private String starship;

  private ArrayList<Object> people;

  private Integer crew;

  private ArrayList<Object> planets;

  public Mission() {
  }

  public Mission(Integer id, String name, Date missionStartDate, String starship,
      ArrayList<Object> people, Integer crew, ArrayList<Object> planets) {
    this.id = id;
    this.name = name;
    this.missionStartDate = missionStartDate;
    this.starship = starship;
    this.people = people;
    this.crew = crew;
    this.planets = planets;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getMissionstartdate() {
    return this.missionStartDate;
  }

  public void setMissionstartdate(Date missionStartDate) {
    this.missionStartDate = missionStartDate;
  }

  public String getStarship() {
    return this.starship;
  }

  public void setStarship(String starship) {
    this.starship = starship;
  }

  public ArrayList<Object> getPeople() {
    return this.people;
  }

  public void setPeople(ArrayList<Object> people) {
    this.people = people;
  }

  public Integer getCrew() {
    return this.crew;
  }

  public void setCrew(Integer crew) {
    this.crew = crew;
  }

  public ArrayList<Object> getPlanets() {
    return this.planets;
  }

  public void setPlanets(ArrayList<Object> planets) {
    this.planets = planets;
  }

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      " name='" + getName() + "'" +
      " missionStartDate='" + getMissionstartdate() + "'" +
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
    return Objects.hash(id, name, missionStartDate, starship, people, crew, planets);
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
    return Objects.equals(id, mission.id) && Objects.equals(name, mission.name) && Objects.equals(missionStartDate, mission.missionStartDate) && Objects.equals(starship, mission.starship) && Objects.equals(people, mission.people) && Objects.equals(crew, mission.crew) && Objects.equals(planets, mission.planets);
  }
}
