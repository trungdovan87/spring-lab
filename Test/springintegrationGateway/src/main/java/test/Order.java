package test;

/**
 * Created by admin on 08/01/2017.
 */
public class Order {
    private String name;
    private int id;
    public Order(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "order: id = " + id + ", name = " + name;
    }
}
