package app.card.service.login;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
	
	UserDetails loadUserByUsername(String username, String password) throws Exception;
}
