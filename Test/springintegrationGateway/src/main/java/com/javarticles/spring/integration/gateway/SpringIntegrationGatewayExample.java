package com.javarticles.spring.integration.gateway;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIntegrationGatewayExample {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        try {
            CustomerService customerService = (CustomerService) context.getBean("customerService"); 
            System.out.println("Get Customer info synchronously");
            for (int i = 0; i < 3; i++) {
                String id = "C0" + i;
                System.out.println("Id: " + id);
                Customer customer = customerService.getCustomerInfo(id);
                System.out.println("Customer information for: " + id + "(" + customer + ")");
            }
            
            customerService = (CustomerService) context.getBean("customerServiceByDispatcher"); 
            System.out.println("Get Customer info asynchronously using dispatcher");
            for (int i = 0; i < 3; i++) {
                String id = "C0" + i;
                System.out.println("Id: " + id);
                Customer customer = customerService.getCustomerInfo(id);
                System.out.println("Customer information for: " + id + "(" + customer + ")");
            }
        } finally {
            context.close();
        }
    }
}
