package com.goeckeler.bootcamp.rest;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
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
    mvc.perform(MockMvcRequestBuilders.get("/products/search?artist-name=celine").accept(MediaType.APPLICATION_JSON)).andExpect(
        status().isOk()).andExpect(content().string(equalTo("{}")));
  }

  @Test
  public void shouldReturnOneProduct()
    throws Exception
  {
    mvc.perform(MockMvcRequestBuilders.get("/products/search?artist-name=p!nk").accept(MediaType.APPLICATION_JSON)).andExpect(
        status().isOk()).andExpect(content().string(equalTo("{name=Funhouse}")));
  }
}
