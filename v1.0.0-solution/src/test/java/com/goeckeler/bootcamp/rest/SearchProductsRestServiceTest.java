package com.goeckeler.bootcamp.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@IfProfileValue(name = "junit.stage", values = {
  "system", "integration"
})
@Ignore("Runs only if the controller has no further dependencies")
public class SearchProductsRestServiceTest
{
  private MockMvc mvc;

  @Before
  public void setUp()
    throws Exception
  {
    mvc = MockMvcBuilders.standaloneSetup(new ProductsRestService()).build();
  }

  @Test
  public void shouldReturnNoResults()
    throws Exception
  {
    mvc.perform(
        MockMvcRequestBuilders.get("/products/search?artist-name=celine").accept(MediaType.APPLICATION_JSON)).andExpect(
            status().isOk()).andExpect(content().string(equalTo("{}")));
  }

  @Test
  public void shouldReturnOneProduct()
    throws Exception
  {
    mvc.perform(
        MockMvcRequestBuilders.get("/products/search?artist-name=p!nk").accept(MediaType.APPLICATION_JSON)).andExpect(
            status().isOk()).andExpect(content().string(equalTo("{name=Funhouse}")));
  }
}
