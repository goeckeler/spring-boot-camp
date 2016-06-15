package com.goeckeler.bootcamp.service;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
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
@Ignore("What am I testing here anyway?")
public class SearchProductsServiceTest
{
  @Test
  public void shouldFindNoProducts() {
    Products products = null; // well ...

    assertNotNull(products);
    assertThat(products.size(), equalTo(0));
  }

  @Test
  public void shouldFindOneProduct() {
    Products products = null; // well ...

    assertNotNull(products);
    assertThat(products.size(), equalTo(1));
    assertTrue(products.getItems().stream().allMatch(p -> p.getName().equalsIgnoreCase("funhouse")));
  }
}
