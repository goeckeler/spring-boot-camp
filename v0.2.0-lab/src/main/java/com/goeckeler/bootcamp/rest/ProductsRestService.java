package com.goeckeler.bootcamp.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeckeler.bootcamp.domain.products.object.Product;
import com.goeckeler.bootcamp.domain.products.object.Products;

@RestController
@RequestMapping(value = "/products")
public class ProductsRestService
{
  @RequestMapping(method = RequestMethod.GET, value = "/search")
  public Products search(@RequestParam(value = "artist-name", required = false) String artistName) {
    return new Products();
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/{id}")
  public Product findById(@PathVariable Long id) {
    return new Product();
  }
}
