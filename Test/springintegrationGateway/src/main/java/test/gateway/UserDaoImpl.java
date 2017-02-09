package test.gateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 09/01/2017.
 */
public class UserDaoImpl {
    public List<User> query(String username) {
        List<User> list = new ArrayList<User>();
        list.add(new User(username + " 1"));
        list.add(new User(username + " 2"));
        return list;
    }
}
