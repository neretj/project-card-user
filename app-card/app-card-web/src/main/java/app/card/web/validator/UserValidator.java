package app.card.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.card.service.user.UserService;
import app.card.service.user.dto.UserDto;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {
		
		UserDto user = (UserDto) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "commons.field.empty");
		
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "userform.max.size.username");
		}
		
		if (userService.getUserLogin(user.getUsername()) != null) {
			errors.rejectValue("username", "userform.username.duplicate");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "commons.field.empty");
		
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "userForm.size.password");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "userform.equal.passwords");
		}
	}
}