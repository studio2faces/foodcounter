package org.s2f.mb.service;

import org.s2f.mb.dao.ProductDAO;
import org.s2f.mb.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public Product save(Product product) {
        try {
            productDAO.save(product);
            return product;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> getAllByUuid(String uuid) {
        return productDAO.findAllByUuid(uuid);
    }
}
