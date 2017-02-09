package edu.hanoi.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trungdovan on 12/20/16.
 */
@RestController
public class HelloController {
	@Autowired
	private MessageChannel helloChannel;

	@RequestMapping(value = "/say")
	public boolean say(@RequestParam String name) {
		return helloChannel.send(MessageBuilder.withPayload(name).build());
	}
}
