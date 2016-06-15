package com.goeckeler.bootcamp.service;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.goeckeler.bootcamp.domain.products.object.Products;
import com.goeckeler.bootcamp.service.boot.Catalog;
import com.goeckeler.bootcamp.test.BackendConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BackendConfiguration.class
})
@Transactional
public class SearchProductsServiceTest
{
  @Autowired
  private ProductsService productsService;

  @Autowired
  private Catalog catalog;
  
  @Before
  public void setup() {
    catalog.load();  
  }
  
  @Test
  public void shouldFindNoProducts() {
    Products products = productsService.searchByArtist("celine");

    assertNotNull(products);
    assertThat(products.size(), equalTo(0));
  }

  @Test
  public void shouldFindOneProduct() {
    Products products = productsService.searchByArtist("p!nk");

    assertNotNull(products);
    assertThat(products.size(), equalTo(1));
    assertTrue(products.getItems().stream().allMatch(p -> p.getName().equalsIgnoreCase("funhouse")));
  }
}
