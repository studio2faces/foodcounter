package org.s2f.mb.dao.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.s2f.mb.dao.UserDAO;
import org.s2f.mb.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override

    public User findByLogin(String login) {
        Query<User> query = getCurrentSession().createQuery("from User where login = :login");
        query.setParameter("login", login);
            return  query.uniqueResult();
    }

    @Override
    public User findByUuid(String uuid) {
        Query<User> query = getCurrentSession().createQuery("from User where uuid = :uuid");
        query.setParameter("uuid", uuid);
        return query.uniqueResult();
    }

    @Override
    public void persist(User user) {
        getCurrentSession().persist(user);
    }
}
