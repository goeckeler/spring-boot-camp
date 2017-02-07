package com.goeckeler.bootcamp.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@IfProfileValue(name = "junit.stage", value = "system")
public class SearchProductsRestSystemTest
{
  @Value("${local.server.port}")
  private int port;

  private URL base;
  
  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp()
    throws Exception
  {
    this.base = new URL("http://localhost:" + port + "/products");
  }

  @Test
  public void shouldReturnNoResults()
    throws Exception
  {
    ResponseEntity<String> response =
        template.getForEntity(base.toString() + "/search?artist-name=celine", String.class);
    assertThat(response.getBody(), equalTo("{ products=\"\" }"));
  }

  @Test
  public void shouldReturnOneProduct()
    throws Exception
  {
    ResponseEntity<String> response = template.getForEntity(base.toString() + "/search?artist-name=p!nk", String.class);
    assertThat(response.getBody().toString(), equalTo("{ products=\"Funhouse by P!nk\" }"));
  }
}
