package edu.hanoi.service.dao;

import edu.hanoi.service.model.User;

import java.util.List;

/**
 * Created by trungdovan on 12/2/16.
 */
public interface UserDao {
    List<User> list();

    void insert(User user);

    User get(String name);

    void delete(String name);

    void update(User user);
}
