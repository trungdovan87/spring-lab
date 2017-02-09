package edu.hanoi.spring.controller;

import edu.hanoi.spring.model.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by trungdovan on 12/20/16.
 */
@Controller
public class MessageController {
	@MessageMapping("/message")
	@SendTo("/topic/chat")
	public Message say(Message message, Principal principal) {
		System.out.println(" From Client: " + message.getContent());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Message msg = new Message();
		msg.setContent(principal.getName() + ": " + message.getContent() + "!");
		return msg;
	}
}
