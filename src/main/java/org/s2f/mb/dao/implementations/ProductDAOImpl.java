package org.s2f.mb.dao.implementations;

import org.s2f.mb.dao.ProductDAO;
import org.s2f.mb.model.entity.Product;
import org.s2f.mb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAllByUuid(String uuid) {
        return productRepository.findAllByUuid(uuid);
    }
}
