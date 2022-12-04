package com.eccocar.mission.dto;

import com.eccocar.mission.dto.Views.ParsedMission;
import com.fasterxml.jackson.annotation.JsonView;
import com.mongodb.lang.NonNull;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Objects;

public class Starship {

  @NonNull
  @JsonView(ParsedMission.class)
  private String url;

  @JsonView(ParsedMission.class)
  private String name;

  @JsonView(ParsedMission.class)
  private String crew;

  @JsonView(ParsedMission.class)
  private String passengers;

  @JsonView(ParsedMission.class)
  private ArrayList<String> pilots;

  public Starship() {
  }

  public Starship(String url, String name, String crew, String passengers) {
    this.url = url;
    this.name = name;
    this.crew = crew;
    this.passengers = passengers;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String id) {
    this.url = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCrew() {
    return this.crew;
  }

  public void setCrew(String crew) {
    this.crew = crew;
  }

  public String getPassengers() {
    return this.passengers;
  }

  public void setPassengers(String passengers) {
    this.passengers = passengers;
  }

  public ArrayList<String> getPilots() {
    return this.pilots;
  }

  public void setPilots(ArrayList<String> pilots) {
    this.pilots = pilots;
  }

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " url='" + getUrl() + "'" +
      " name='" + getName() + "'" +
      " crew='" + getCrew() + "'" +
      " passengers='" + getPassengers() + "'" +
      " pilots='" + getPilots() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(url, name, crew, passengers);
  }

  /**
   * Indicates whether some other object is "equal to" this one
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return false;
    } 
    if (!(o instanceof Starship)) {
      return false;
    }
    Starship starship = (Starship) o;
    return Objects.equals(url, starship.url) 
        && Objects.equals(name, starship.name)
        && Objects.equals(crew, starship.crew) 
        && Objects.equals(passengers, starship.passengers)
        && Objects.equals(pilots, starship.pilots);
  }
}
