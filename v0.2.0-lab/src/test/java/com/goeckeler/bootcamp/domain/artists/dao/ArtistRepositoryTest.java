package com.goeckeler.bootcamp.domain.artists.dao;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.goeckeler.bootcamp.domain.artists.object.Artist;
import com.goeckeler.bootcamp.service.boot.Catalog;
import com.goeckeler.bootcamp.test.BackendConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { BackendConfiguration.class
})
@Transactional
public class ArtistRepositoryTest
{
  @Autowired
  private ArtistRepository artistRepository;

  @Autowired
  private Catalog catalog;

  @Before
  public void setup() {
    // load sample data
    catalog.load();
  }

  @Test
  public void shouldFindCaseInsensitive() {
    final String helene = "helene fischer";

    Artist artist = artistRepository.findByNameIgnoreCase(helene);
    assertNotNull(artist);
    assertThat(artist.getName(), equalToIgnoringCase(helene));
  }

  @Test
  public void shouldFindInfixName() {
    final String helene = "helene";

    // there are two Helenes
    List<Artist> artists = artistRepository.findByNameLikeIgnoreCase("%" + helene + "%");
    assertNotNull(artists);
    assertThat(artists.size(), equalTo(2));
    assertThat(artists.stream().filter(a -> StringUtils.containsIgnoreCase(a.getName(), helene)).count(), equalTo(2L));
  }
}
