package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import test.gateway.Group;
import test.gateway.User;
import test.gateway.UserService;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by admin on 08/01/2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "testContext.xml");
//        OrderService orderService = context.getBean("orderService", OrderService.class);
//        Order order = orderService.createOrder("my Name");
//        System.out.println("--- my order: " + order.toString());

//        MessageChannel channel = context.getBean("helloChannel", MessageChannel.class);
//        System.out.println("send msg: kaka");
//        channel.send(MessageBuilder.withPayload(new User("22")).build());
        //channel.send(MessageBuilder.withPayload("kaka").build());
//
//        System.out.println("send msg: blabla");
//        channel.send(MessageBuilder.withPayload("blabla").build());

        UserService userService = context.getBean("userService", UserService.class);

        List<User> listUser = userService.query("my name");
        for (User user : listUser) {
            System.out.println("user: " + user.toString());
        }

        System.out.println("async....");
        Future<List<Group>> listGroup = userService.loadGroup("my GROUP name");
        System.out.println("Done async");
        List<Group> list = listGroup.get();
        for (Group group : list) {
            System.out.println("Group: " + group.toString());
        }


    }
}
