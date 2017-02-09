package test.gateway;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by admin on 09/01/2017.
 */
public interface UserService {
    List<User> query(String username);
    Future<List<Group>> loadGroup(String name);
}
