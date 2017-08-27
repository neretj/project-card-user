package login;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.card.service.card.CardService;
import app.card.service.user.UserService;
import app.card.service.user.dto.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-service.xml")
public class LoginTest {

	@Autowired
	private UserService userService;

	@Autowired
	private CardService cardService;

	@Test
	public void loginUserTest() {

		Assert.assertNotNull(cardService);

		Assert.assertNotNull(userService);
		
		String username = "test";

		UserDto user = userService.getUserLogin(username);

		Assert.assertNotNull(user);
	}
}
