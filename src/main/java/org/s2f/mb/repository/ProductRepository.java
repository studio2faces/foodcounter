package org.s2f.mb.repository;

import org.s2f.mb.model.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    List<Product> findAllByUuid(String uuid);

    Product save(Product product);
}
