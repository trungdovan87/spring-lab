package edu.hanoi.service.dao.impl;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.model.Group;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by trungdovan on 12/4/16.
 */
@Component("groupDAO")
@Transactional
public class GroupDAOImpl implements GroupDAO {
    private final static Logger logger = Logger.getLogger(GroupDAOImpl.class);

    @Autowired
    private LocalSessionFactoryBean sessionFactory;


    @Override
    public List<Group> list() {
        Session session = sessionFactory.getObject().getCurrentSession();
        return session.createQuery("from Group").list();
    }

    @Override
    public void insert(Group userGroup) {
        Session session = sessionFactory.getObject().getCurrentSession();
        session.persist(userGroup);
    }
}
