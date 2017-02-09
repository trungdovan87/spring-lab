package com.javarticles.spring.integration.gateway;

public class CustomerSlowServiceImpl implements CustomerService {
    public Customer getCustomerInfo(String customerId) {
        //Simulate a delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return new Customer(customerId + ", called by: " + Thread.currentThread().getName());
    }
}
