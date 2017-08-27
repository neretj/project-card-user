package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.card.model.user.UserVO;
import app.card.model.user.dao.UserDao;
import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-model.xml")
public class UserTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testSaveAndGet() {

		try {
			
			UserVO user = new UserVO();
			user.setUsername("user01");
			user.setPassword("123456");
			user.setName("name");
			user.setSurname("surname");
			user.setIdRole(new Long(2));

			userDao.saveUser(user);

			user = userDao.getUserData("user01");
			
			Assert.assertNotNull(user);

			user.setUsername("userAdmin");
			user.setPassword("123456");
			user.setName("name");
			user.setSurname("surname");			
			user.setIdRole(new Long(1));
			
			userDao.saveUser(user);
			
			Assert.assertNotNull(user);

		} catch (Exception e) {
			System.out.println("ERROR " + e);
		}

	}

}