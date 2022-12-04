package com.eccocar.mission.dto;

import com.eccocar.mission.dto.Views.ParsedMission;
import com.fasterxml.jackson.annotation.JsonView;
import com.mongodb.lang.NonNull;
import java.lang.Double;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public class Planets {

  @NonNull
  @JsonView(ParsedMission.class)
  private String url;

  @JsonView(ParsedMission.class)
  private String name;

  @JsonView(ParsedMission.class)
  private Double diameter;

  public Planets() {
  }

  public Planets(String url, String name, Double diameter) {
    this.url = url;
    this.name = name;
    this.diameter = diameter;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getDiameter() {
    return this.diameter;
  }

  public void setDiameter(Double diameter) {
    this.diameter = diameter;
  }

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " url='" + getUrl() + "'" +
      " name='" + getName() + "'" +
      " diameter='" + getDiameter() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(url, name, diameter);
  }

  /**
   * Indicates whether some other object is "equal to" this one
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return false;
    } 
    if (!(o instanceof Planets)) {
      return false;
    }
    Planets planets = (Planets) o;
    return Objects.equals(url, planets.url) 
        && Objects.equals(name, planets.name) 
        && Objects.equals(diameter, planets.diameter);
  }
}
