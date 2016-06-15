package com.goeckeler.bootcamp.domain.products.object;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.goeckeler.bootcamp.domain.artists.object.Artist;
import com.goeckeler.bootcamp.domain.artists.object.Artists;

@Entity
@Table(name = "products")
public class Product
  implements Comparable<Product>, Serializable
{
  private static final long serialVersionUID = -6688837651949227501L;

  @Id
  @GeneratedValue
  private Long id;

  @Column(length = 80)
  private String name;

  @Embedded
  private Artists artists;

  public Product() {
    this(null);
  }

  public Product(String name) {
    this(name, (Artist[]) null);
  }

  public Product(String name, Artist... artists) {
    this.name = name;
    this.artists = new Artists();
    if (artists != null) {
      Arrays.asList(artists).forEach(a -> this.artists.add(a));
    }
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return defaultIfBlank(name, "(n/a)");
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Artists getArtists() {
    return artists;
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

    Product that = (Product) other;
    return this.toString().equals(that.toString());
  }

  @Override
  public int compareTo(Product that) {
    if (that == null) return 1;
    if (this == that) return 0;
    return getName().compareToIgnoreCase(that.getName());
  }

  @Override
  public String toString() {
    return getName() + " by " + getArtists().toString();
  }
}
