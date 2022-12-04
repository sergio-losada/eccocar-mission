package com.eccocar.mission.dto;

import com.eccocar.mission.dto.Views.ParsedMission;
import com.fasterxml.jackson.annotation.JsonView;
import com.mongodb.lang.NonNull;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;

public class People {

  @NonNull
  @JsonView(ParsedMission.class)
  private String url;

  @JsonView(ParsedMission.class)
  private String name;

  public People() {
  }

  public People(String url, String name) {
    this.url = url;
    this.name = name;
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

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " url='" + getUrl() + "'" +
      " name='" + getName() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(url, name);
  }

  /**
   * Indicates whether some other object is "equal to" this one
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return false;
    } 
    if (!(o instanceof People)) {
      return false;
    }
    People people = (People) o;
    return Objects.equals(url, people.url) 
        && Objects.equals(name, people.name);
  }
}
