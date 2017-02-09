package edu.hanoi.spring_ws;

import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Created by trungdovan on 12/19/16.
 */
@WebService
@Component
public class UserService {
	@WebMethod(operationName = "say")
	public String sayHello(String name) {
		return "Hanoi Java say hello to: " + name;
	}
}
