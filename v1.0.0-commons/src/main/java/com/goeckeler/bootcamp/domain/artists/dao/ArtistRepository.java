package com.goeckeler.bootcamp.domain.artists.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.goeckeler.bootcamp.domain.artists.object.Artist;

/**
 * DAO for entity {@link Artist}.
 */
public interface ArtistRepository extends CrudRepository<Artist, Long>
{
  Artist findByNameIgnoreCase(final String name);
  List<Artist> findByNameLikeIgnoreCase(final String name);  
}
