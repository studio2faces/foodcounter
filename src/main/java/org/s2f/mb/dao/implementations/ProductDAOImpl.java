package org.s2f.mb.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.s2f.mb.dao.ProductDAO;
import org.s2f.mb.model.entity.Product;
import org.s2f.mb.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Product persist(Product product) {
        return (Product) getCurrentSession().save(product);
    }

    @Override
    public List<Product> findAllByUuid(String uuid) {
        Query<Product> query = getCurrentSession().createQuery("select p from Product p where p.uuid = :uuid", Product.class);
        query.setParameter("uuid", uuid);
        return query.list();
    }
}
