package test.gateway;

import java.util.List;

/**
 * Created by admin on 09/01/2017.
 */
public class GroupOutEndpoint {
    public void processGroupList(List<Group> groups) {
        System.out.println("List Group size: " + groups.size());
    }
}
