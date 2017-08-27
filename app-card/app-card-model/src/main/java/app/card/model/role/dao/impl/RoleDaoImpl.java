package app.card.model.role.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import app.card.model.role.RoleVO;
import app.card.model.role.dao.RoleDao;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public RoleVO getRoleById(Long id) {

		RoleVO role = null;
		
		String SQL = "select * from Role where id_role = ?";

		List<RoleVO> roles = jdbcTemplate.query(SQL, new Object[] { id }, new DataMapper());

		if (!roles.isEmpty()) {
			role = roles.get(0);
		}

		return role;
	}

	public RoleVO getRoleByUserId(Long id) {

		RoleVO role = null;
		String SQL = "select R.id_role, R.name from Role R join User U ON R.id_role = U.id_role where U.id_user = ?";

		List<RoleVO> cards = jdbcTemplate.query(SQL, new Object[] { id }, new DataMapper());

		if (!cards.isEmpty()) {
			role = cards.get(0);
		}

		return role;
	}

	public void saveRole(RoleVO roleVO) {

		String sql = "INSERT INTO Role (name)" + " VALUES (?)";

		jdbcTemplate.update(sql, roleVO.getName());
		
	}

	public class DataMapper implements RowMapper<RoleVO> {
		public RoleVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoleVO roleVO = new RoleVO();
			roleVO.setIdRole(rs.getLong("id_role"));
			roleVO.setName(rs.getString("name"));
			return roleVO;
		}
	}
}
