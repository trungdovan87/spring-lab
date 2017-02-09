package com.javarticles.spring.integration.gateway;

public class CustomerServiceImpl implements CustomerService {
    public Customer getCustomerInfo(String customerId) {
        return new Customer(customerId + ", called by: " + Thread.currentThread().getName());
    }
}
