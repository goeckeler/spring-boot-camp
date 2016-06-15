package com.goeckeler.bootcamp.service.boot;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.goeckeler.bootcamp.domain.artists.dao.ArtistRepository;
import com.goeckeler.bootcamp.domain.artists.object.Artist;
import com.goeckeler.bootcamp.domain.products.dao.ProductRepository;
import com.goeckeler.bootcamp.domain.products.object.Product;

@Component
public class Catalog
{
  private final Map<String, String> catalog;

  private Set<Artist> artists = new TreeSet<>();
  private Set<Product> products = new TreeSet<>();

  @Autowired
  private ProductRepository productDao;

  @Autowired
  private ArtistRepository artistDao;

  public Catalog() {
    catalog = new HashMap<>();

    catalog.put("Funhouse", "P!nk");
    catalog.put("Atemlos", "Helene Fischer");
    catalog.put("Unser Tag", "Helene Fischer, Stefan Mross");
    catalog.put("Crazy Lady", "Helene Schneider");
  }

  @Transactional
  public void load() {
    createArtists();
    createProducts();
  }

  public Optional<Artist> artistFor(String name) {
    return artists.stream().filter(a -> containsIgnoreCase(a.getName(), name)).findFirst();
  }

  public Optional<Product> productFor(String name) {
    return products.stream().filter(p -> containsIgnoreCase(p.getName(), name)).findFirst();
  }

  private void createArtists() {
    Set<String> artistNames = new TreeSet<>();

    // beware of doubles
    for (String names : catalog.values()) {
      for (String name : StringUtils.split(names, ",")) {
        artistNames.add(name.trim());
      }
    }

    artistNames.forEach(name -> {
      Artist artist = artistDao.findByNameIgnoreCase(name);
      if (artist == null) {
        artist = artistDao.save(new Artist(name));
      }
      artists.add(artist);
    });
  }

  private void createProducts() {
    for (Entry<String, String> entry : catalog.entrySet()) {
      String name = entry.getKey();

      Product product = productDao.findByNameIgnoreCase(name);
      if (product == null) {
        product = new Product(name);
        for (String artist : StringUtils.split(entry.getValue(), ",")) {
          product.getArtists().add(artistFor(artist.trim()).get());
        }
        product = productDao.save(product);
      }
      products.add(product);
    };
  }
}
