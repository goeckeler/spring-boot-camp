package com.goeckeler.bootcamp.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.goeckeler.bootcamp.domain.products.object.Product;

@Aspect
@Component
public class FailingProductRequestsAspect
{
  private final static Logger LOG = LoggerFactory.getLogger(FailingProductRequestsAspect.class);

  @AfterReturning(pointcut="execution(* com.goeckeler.bootcamp.rest.ProductsRestService.findById(..)) && args(id, ..)", returning="product")
  public void missingProduct(JoinPoint join, Long id, Product product) {
    if (product == null) {
      LOG.warn("Someone requested an invalid product with id #{}.", id);
    }
  }
}
