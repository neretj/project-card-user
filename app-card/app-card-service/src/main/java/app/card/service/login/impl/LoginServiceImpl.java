package app.card.service.login.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.card.model.role.RoleVO;
import app.card.model.role.dao.RoleDao;
import app.card.model.user.UserVO;
import app.card.model.user.dao.UserDao;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LoginServiceImpl implements UserDetailsService {

	
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserVO user = new UserVO();
		
		user = userDao.getUserData(username);

		RoleVO roleVO = roleDao.getRoleById(user.getIdRole());

		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(roleVO.getName()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				grantedAuthorities);
	}

}
