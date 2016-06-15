package com.goeckeler.bootcamp.rest;

import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goeckeler.bootcamp.domain.products.object.Product;
import com.goeckeler.bootcamp.domain.products.object.Products;
import com.goeckeler.bootcamp.service.ProductsService;

@RestController
@RequestMapping(value = "/products")
@Transactional(readOnly = true)
public class ProductsRestService
{
  private final static Logger LOG = LoggerFactory.getLogger(ProductsRestService.class);

  @Autowired
  private ProductsService productsService;

  @RequestMapping(method = GET, value = "/search")
  public String search(@RequestParam(value = "artist-name", required = false) String artistName) {
    LOG.info("REST> Searching for products by artist '{}'", defaultIfEmpty(artistName, "(n/a)"));
    Products products = productsService.searchByArtist(artistName);
    LOG.info("REST> Search for products by artist '{}' found: {}", defaultIfEmpty(artistName, "(n/a)"), products);
    return "{ products=\"" + products.toString() + "\" }";
  }

  @RequestMapping(method = GET, value = "/{id}")
  public Product findById(@PathVariable Long id, HttpServletResponse response) {
    Optional<Product> product = productsService.findBy(id);
    if (!product.isPresent()) {
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
      return null;
    }
    
    return product.get();
  }
}
