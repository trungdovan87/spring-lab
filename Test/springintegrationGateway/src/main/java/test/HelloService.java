package test;

/**
 * Created by admin on 08/01/2017.
 */
public class HelloService {
    public String say(String name) {
        System.out.println("HelloService say: " + name + ". From thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "JEE class say: " + name;
    }

}
