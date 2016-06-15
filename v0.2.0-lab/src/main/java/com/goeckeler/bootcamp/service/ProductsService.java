package com.goeckeler.bootcamp.service;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.defaultIfEmpty;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.goeckeler.bootcamp.domain.products.dao.ProductRepository;
import com.goeckeler.bootcamp.domain.products.object.Product;
import com.goeckeler.bootcamp.domain.products.object.Products;

@Component
@Transactional(readOnly = true)
public class ProductsService
{
  private final static Logger LOG = LoggerFactory.getLogger(ProductsService.class);
  
  @Autowired
  private ProductRepository productRepository;

  public Products searchByArtist(final String artistName) {
    Products products = new Products();

    LOG.info("Searching for products by artist '{}'", defaultIfEmpty(artistName, "(n/a)"));
    
    // works, but doesn't it look ugly?
    Iterable<Product> allProducts = productRepository.findAll();
    allProducts.forEach(p -> {
      if (p.getArtists().getItems().stream().anyMatch(n -> containsIgnoreCase(n.getName(), defaultString(artistName)))) {
        products.add(p);
      }
    });

    LOG.info("Search for products by artist '{}' found: {}", defaultIfEmpty(artistName, "(n/a)"), products);

    return products;
  }

  public Optional<Product> findBy(Long id) {
    return Optional.ofNullable(productRepository.findOne(id));
  }
}
