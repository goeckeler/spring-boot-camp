package com.goeckeler.bootcamp.domain.products.object;

import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;

import com.goeckeler.bootcamp.common.object.EntitySet;

@Embeddable
public class Products
  extends EntitySet<Product>
{
  private static final long serialVersionUID = 5205959798956834924L;

  @ManyToMany
  private Set<Product> products;

  @Override
  protected Set<Product> data() {
    return products;
  }

  @Override
  protected void data(final Set<Product> products) {
    this.products = products;
  }
}
