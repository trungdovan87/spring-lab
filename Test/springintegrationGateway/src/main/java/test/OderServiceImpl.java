package test;

/**
 * Created by admin on 08/01/2017.
 */
public class OderServiceImpl {
    private int genId = 0;
    public Order createNewOrder(String name) {
        genId++;
        return  new Order(genId, name);
    }

    public OderServiceImpl() {
        System.out.println("create Oder ServiceImpl");
    }
}
