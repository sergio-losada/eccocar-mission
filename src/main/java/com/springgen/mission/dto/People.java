package com.springgen.mission.dto;

import com.mongodb.lang.NonNull;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(
    collection = "people"
)
public class People {
  @NonNull
  private Integer id;

  private String name;

  public People() {
  }

  public People(Integer id, String name) {
    this.id = id;
    this.name = name;
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

  /**
   * Returns a string representation of the object
   */
  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      " name='" + getName() + "'" +
    "}";
  }

  /**
   * Returns a hash code value for the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, name);
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
    return Objects.equals(id, people.id) && Objects.equals(name, people.name);
  }
}
