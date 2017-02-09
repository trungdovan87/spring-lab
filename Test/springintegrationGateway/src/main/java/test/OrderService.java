package test;

import org.springframework.integration.annotation.Gateway;

/**
 * Created by admin on 08/01/2017.
 */
public interface OrderService {
    String same(String s);
    Order createOrder(String name);
    Order sameCreateOrder(String name);
}
