package edu.hanoi.service;

import edu.hanoi.service.model.User;
import org.apache.log4j.Logger;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by trungdovan on 12/6/16.
 */
@Component
public class HNServicePermissionEvaluator implements PermissionEvaluator {
	private final static Logger logger = Logger.getLogger(HNServicePermissionEvaluator.class);

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		logger.info("---- > method 1: " + targetDomainObject + ": " + permission);
		if (targetDomainObject instanceof User) {
			User user = (User) targetDomainObject;
			return user.getAge() > 5;
		}
		return true;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		logger.info("---- > method 2: " + targetType + ": " + permission);
		return true;
	}
}
