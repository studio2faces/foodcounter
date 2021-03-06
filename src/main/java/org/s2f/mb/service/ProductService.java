package org.s2f.mb.service;

import lombok.extern.slf4j.Slf4j;
import org.s2f.mb.dao.ProductDAO;
import org.s2f.mb.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLDataException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product save(Product product) {
        try {
            productDAO.save(product);
        } catch (Exception e) {
            log.error("Can not save product {} to DB.", product.getName(), e);
        }
        return product;
    }

    public List<Product> getAllByUuid(String uuid) {
        return productDAO.findAllByUuid(uuid);
    }
}
