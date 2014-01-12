package tn.tunisietelecom.web.security;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import tn.tunisietelecom.event.entities.User;
import tn.tunisietelecom.event.service.UserService;
import tn.tunisietelecom.web.dto.ConnectedUser;

public class TelecomAuthenticationManager implements AuthenticationManager {

	private static Log log = LogFactory
			.getLog(TelecomAuthenticationManager.class);

	@Autowired
	private UserService userService;

	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {

		List<User> users = userService.findByLogin(auth.getName());
		User connected = null;

		if (users.size() == 0)
			throw new BadCredentialsException("Entered username is wrong!");
		else {
			for (User user : users)
				if (user.getPassword().equals(auth.getCredentials()))
					connected = user;
			if (connected == null)
				throw new BadCredentialsException("Entered password is wrong!");
		}

		ConnectedUser connectedUser = new ConnectedUser();
		BeanUtils.copyProperties(connected, connectedUser);
		connectedUser.setPrivilege(connected.getRole().getIdRole());
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				connectedUser, auth.getCredentials(),
				new ArrayList<GrantedAuthority>());
		return token;
	}

}