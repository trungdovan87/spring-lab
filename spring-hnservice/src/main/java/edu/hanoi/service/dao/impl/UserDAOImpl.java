package edu.hanoi.service.dao.impl;

import edu.hanoi.service.dao.UserDao;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by trungdovan on 12/2/16.
 */
@Component("userDAO")
@Transactional
public class UserDAOImpl implements UserDao {
    private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

    private LocalSessionFactoryBean sessionFactory;

    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(LocalSessionFactoryBean sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> list() {
        Session session = sessionFactory.getObject().getCurrentSession();
        Query query = session.createQuery("from User order by age desc");
        return query.list();
    }

    @Override
    public void insert(User user) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(user);
    }

    @Override
    public User get(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        List<User> list = session.createQuery("from User where username = '" + name + "'").list();
        if (list.size() > 0)
            return list.get(0);
        else
            return null;
    }

    @Override
    public void delete(String name) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.createQuery("delete from User where username = '" + name + "'").executeUpdate();

    }

    @Override
    public void update(User user) {
        sessionFactory.getObject().getCurrentSession().update(user);
    }
}
