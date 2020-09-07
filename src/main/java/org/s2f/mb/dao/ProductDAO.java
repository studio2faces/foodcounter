package org.s2f.mb.dao;

import org.s2f.mb.model.entity.Product;

import java.util.List;

public interface ProductDAO {

    void persist(Product product);

    List<Product> findAllByUuid(String uuid);
}
