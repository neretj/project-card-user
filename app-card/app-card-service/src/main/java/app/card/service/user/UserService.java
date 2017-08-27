package app.card.service.user;

import app.card.service.user.dto.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
	
	UserDto getUserLogin(String username);

	void autologin(String username, String password);
	
	String findLoggedInUsername();
}
