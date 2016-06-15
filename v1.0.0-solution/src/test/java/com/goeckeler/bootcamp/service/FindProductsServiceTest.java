package com.goeckeler.bootcamp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.goeckeler.bootcamp.domain.products.object.Product;
import com.goeckeler.bootcamp.service.boot.Catalog;
import com.goeckeler.bootcamp.test.BackendConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { BackendConfiguration.class
})
@Ignore("Need to debug this one, always creates data inconsistencies")
public class FindProductsServiceTest
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
  public void shouldFindNoProduct() {
    Optional<Product> product = productsService.findBy(-1L);

    assertNotNull(product);
    assertFalse(product.isPresent());
  }

  @Test
  public void shouldFindOneProduct() {
    Product funhouse = catalog.productFor("house").get();    
    assertNotNull(funhouse);
    assertNotNull(funhouse.getId());
    
    Optional<Product> product = productsService.findBy(funhouse.getId());

    assertNotNull(product);
    assertTrue(product.isPresent());
    assertEquals(product.get().getName(), "Funhouse");
  }
}
