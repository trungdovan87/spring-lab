package test.gateway;

/**
 * Created by admin on 09/01/2017.
 */
public class User {
    private String name;
    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User: name = " + name;
    }
}
