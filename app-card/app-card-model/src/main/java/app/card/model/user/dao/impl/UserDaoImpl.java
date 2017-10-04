package app.card.model.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import app.card.model.user.UserVO;
import app.card.model.user.dao.UserDao;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public UserVO getUserData(String username) {

		UserVO user = null;
		String SQL = "select id_user, username, password, name, surname, id_role from User where username like ?";
		
		List<UserVO> users = jdbcTemplate.query(SQL, new Object[] { username }, new DataMapper());

		if (!users.isEmpty()) {
			user = users.get(0);
		}

		return user;
	}

	public void saveUser(UserVO userVO) {
		
		String sql = "INSERT INTO User (username, password, name, surname, id_role)" + " VALUES (?, ?, ?, ?, ?)";

		jdbcTemplate.update(sql, userVO.getUsername(), userVO.getPassword(), userVO.getName(), userVO.getUsername(),
				userVO.getIdRole());
		
	}

	public void updateUser(UserVO userVO) {

		String sql = "UPDATE User SET password = ?, name = ?, surname = ?, id_role = ? WHERE username = ?";

		jdbcTemplate.update(sql, userVO.getPassword(), userVO.getName(), userVO.getUsername(), userVO.getIdRole(),
				userVO.getUsername());

	}

	public class DataMapper implements RowMapper<UserVO> {
		public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserVO userVO = new UserVO();
			userVO.setIdUser(rs.getLong("id_user"));
			userVO.setUsername(rs.getString("username"));
			userVO.setName(rs.getString("name"));
			userVO.setSurname(rs.getString("surname"));
			userVO.setPassword(rs.getString("password"));
			userVO.setIdRole(rs.getLong("id_role"));
			return userVO;
		}
	}

}
