package com.goeckeler.bootcamp.domain.products.dao;

import org.springframework.data.repository.CrudRepository;

import com.goeckeler.bootcamp.domain.products.object.Product;

/**
 * DAO for entity {@link Product}.
 */
public interface ProductRepository extends CrudRepository<Product, Long>
{
  Product findByNameIgnoreCase(String productName);
}
