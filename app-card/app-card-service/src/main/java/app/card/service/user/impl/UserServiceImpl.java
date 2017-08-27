package app.card.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import app.card.model.card.dao.CardDao;
import app.card.model.role.dao.RoleDao;
import app.card.model.user.UserVO;
import app.card.model.user.dao.UserDao;
import app.card.service.card.dto.CardDto;
import app.card.service.card.dto.converter.CardConverter;
import app.card.service.user.UserService;
import app.card.service.user.dto.RoleDto;
import app.card.service.user.dto.UserDto;
import app.card.service.user.dto.converter.RoleConverter;
import app.card.service.user.dto.converter.UserConverter;
import app.card.service.util.ConstansConfig;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CardDao cardDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RoleConverter roleConverter;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private CardConverter cardConverter;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsService loginService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public UserDto registerUser(UserDto userDto) {

		Long idRoleUser = ConstansConfig.DEFAULT_REGISTER_ROLE;

		String passEncoded = bCryptPasswordEncoder.encode(userDto.getPassword());
		userDto.setPassword(passEncoded);

		UserVO userVO = userConverter.toVO(userDto);
		userVO.setIdRole(idRoleUser);

		Long id = userDao.saveUser(userVO);
		userVO.setIdUser(id);

		userDto = userConverter.toDto(userVO);

		return userDto;
	}

	public UserDto getUserLogin(String username) {

		UserVO userVO = null;

		try {

			userVO = userDao.getUserData(username);

		} catch (Exception e) {
			System.out.println("Error recuperando el usuario " + e);
		}

		UserDto userDto = null;

		if (userVO != null) {
			userDto = userConverter.toDto(userVO);

			RoleDto roleDto = roleConverter.toDto(roleDao.getRoleById(userVO.getIdRole()));
			userDto.setRole(roleDto);

			List<CardDto> cards = cardConverter.toDtos(cardDao.getCardByIdUser(userVO.getIdUser()));
			userDto.setCards(cards);
		}

		return userDto;
	}

	public void autologin(String username, String password) {

		UserDetails userDetails = loginService.loadUserByUsername(username);

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	public String findLoggedInUsername() {

		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}

		return null;
	}
}
