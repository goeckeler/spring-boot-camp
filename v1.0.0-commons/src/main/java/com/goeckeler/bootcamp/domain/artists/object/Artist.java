package com.goeckeler.bootcamp.domain.artists.object;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "artists", indexes = { @Index(name = "artists_name_index", columnList = "name", unique = false)
})
public class Artist
  implements Comparable<Artist>, Serializable
{
  private static final long serialVersionUID = 952914908735352548L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 80)
  private String name;

  public Artist() {
    this(null);
  }

  public Artist(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return defaultIfBlank(name, "Anonymous");
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    if (other == null) return false;

    if (!getClass().isAssignableFrom(other.getClass())) return false;

    Artist that = (Artist) other;
    return this.toString().equals(that.toString());
  }

  @Override
  public int compareTo(Artist that) {
    if (that == null) return 1;
    if (this == that) return 0;
    return getName().compareToIgnoreCase(that.getName());
  }

  @Override
  public String toString() {
    return getName();
  }
}
