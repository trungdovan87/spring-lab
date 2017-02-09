package test.gateway;

/**
 * Created by admin on 09/01/2017.
 */
public class Group {
    private String name;
    public Group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group: name = " + name;
    }
}
