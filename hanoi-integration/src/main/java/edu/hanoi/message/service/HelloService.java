package edu.hanoi.message.service;

import org.springframework.stereotype.Service;

/**
 * Created by trungdovan on 12/20/16.
 */
@Service
public class HelloService {
	public String say(String name){
		return "JEE clazz say " + name;
	}
}
