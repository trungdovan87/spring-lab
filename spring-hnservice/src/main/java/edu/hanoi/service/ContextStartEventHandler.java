package edu.hanoi.service;

import edu.hanoi.service.dao.GroupDAO;
import edu.hanoi.service.dao.UserDao;
import edu.hanoi.service.model.Group;
import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by trungdovan on 12/4/16.
 */
@Component
public class ContextStartEventHandler {
    private final static Logger logger = Logger.getLogger(ContextStartEventHandler.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private GroupDAO groupDao;

    @EventListener
    public void handleStartedEvent(ContextStartedEvent event) {
        System.out.println("------ Context Started Event");
    }

    @EventListener
    public void handleRefredEvent(ContextRefreshedEvent event) {
        System.out.print("------ Context Refreshed Event");
//        for (int i = 0; i < 10; i++) {
//            User user = new User();
//            user.setUsername("test" + i);
//            user.setPassword("123");
//            user.setAge(i);
//            user.setEmail(String.format("test%d@gmail.com", i));
//            userDao.insert(user);
//        }
//
//        for (int i = 0; i < 4; i++) {
//            Group userGroup = new Group();
//            userGroup.setName("USER ROLE " + i);
//            //userGroup.setId(i);
//            groupDao.insert(userGroup);
//        }
    }
}
