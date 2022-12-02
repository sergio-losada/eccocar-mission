package com.springgen.mission.dto;

import com.mongodb.lang.NonNull;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "starship"
)
public class Starship {
  @NonNull
  private Integer id;

  private String name;

  private String crew;

  private String passengers;

  private ArrayList<String> pilots;

  public Starship() {
  }

  public Starship(Integer id, String name, String crew, String passengers) {
    this.id = id;
    this.name = name;
    this.crew = crew;
    this.passengers = passengers;
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
      " id='" + getId() + "'" +
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
    return Objects.hash(id, name, crew, passengers);
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
    return Objects.equals(id, starship.id) 
        && Objects.equals(name, starship.name)
        && Objects.equals(crew, starship.crew) 
        && Objects.equals(passengers, starship.passengers)
        && Objects.equals(pilots, starship.pilots) ;
  }
}
