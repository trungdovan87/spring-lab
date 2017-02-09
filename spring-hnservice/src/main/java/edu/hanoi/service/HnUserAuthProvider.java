package edu.hanoi.service;

import edu.hanoi.service.dao.UserDao;
import edu.hanoi.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trungdovan on 12/6/16.
 */
public class HnUserAuthProvider implements AuthenticationProvider {
	@Autowired
	private UserDao userDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		User user = userDAO.get(username);
		if (user == null)
			return null;
		if (!user.getPassword().equals(authentication.getCredentials()))
			return null;
		return successful(username, user.getPassword(), "ROLE_USER");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private UsernamePasswordAuthenticationToken successful(String username, String password, String role) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		grantedAuths.add(new SimpleGrantedAuthority(role));
		return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	}
}
