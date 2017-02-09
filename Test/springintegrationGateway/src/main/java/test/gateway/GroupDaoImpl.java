package test.gateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 09/01/2017.
 */
public class GroupDaoImpl {
    public List<Group> loadGroup(String name) {
        System.out.println("loadGroup() from thread : " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Group> list = new ArrayList<Group>();
        list.add(new Group(name + " 1"));
        list.add(new Group(name + " 2"));
        return list;
    }
}
