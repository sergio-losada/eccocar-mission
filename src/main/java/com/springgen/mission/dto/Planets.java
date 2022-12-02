package com.springgen.mission.dto;

import com.mongodb.lang.NonNull;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "planets"
)
public class Planets {
  @NonNull
  private Integer id;

  private String name;

  private Double diameter;

  public Planets() {
  }

  public Planets(Integer id, String name, Double diameter) {
    this.id = id;
    this.name = name;
    this.diameter = diameter;
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
      " id='" + getId() + "'" +
      " name='" + getName() + "'" +
      " diameter='" + getDiameter() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, name, diameter);
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
    return Objects.equals(id, planets.id) && Objects.equals(name, planets.name) && Objects.equals(diameter, planets.diameter);
  }
}
