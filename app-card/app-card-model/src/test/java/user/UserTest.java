package user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.card.model.role.RoleVO;
import app.card.model.role.dao.RoleDao;
import app.card.model.user.UserVO;
import app.card.model.user.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-model.xml")
public class UserTest {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Test
	public void testSaveAndGet() {

		try {
			
			RoleVO roleAdmin = new RoleVO();
			roleAdmin.setName("admin");
			
			Long idAdminRole = roleDao.saveRole(roleAdmin);
			
			RoleVO roleUser= new RoleVO();
			roleUser.setName("user");
			
			Long idUserRole = roleDao.saveRole(roleUser);
			
			UserVO user = new UserVO();
			user.setUsername("nicoc");
			user.setPassword("123456");
			user.setName("nico");
			user.setSurname("campoy");
			user.setIdRole(idUserRole);

			userDao.saveUser(user);

			user = userDao.getUserData("nicoc");
			System.out.println("user " + user.getUsername());

			user.setUsername("nereatj");
			user.setPassword("123456");
			user.setName("nerea");
			user.setSurname("toral");			
			user.setIdRole(idAdminRole);
			
			userDao.saveUser(user);
			
			user = userDao.getUserData("nereatj");
			System.out.println("user " + user.getUsername());

		} catch (Exception e) {
			System.out.println("ERROR " + e);
		}

	}

}