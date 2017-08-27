package app.card.model.card.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import app.card.model.card.CardVO;
import app.card.model.card.dao.CardDao;

@Repository
public class CardDaoImpl implements CardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public Long saveCard(CardVO card) {
		
		String sql = "INSERT INTO Card (name_card, number_card, expire_date, id_user)" + " VALUES (?, ?, ?, ?)";

		int id = jdbcTemplate.update(sql, card.getNameCard(), card.getNumberCard(), card.getExpiredDate(), card.getIdUser());
		
		return new Long(id);
	}

	public void updateCard(CardVO card) {
		
		String sql = "UPDATE Card SET name_card = ?, number_card = ?, expire_date = ? WHERE id_card = ?";

		jdbcTemplate.update(sql, card.getNameCard(), card.getNumberCard(), card.getExpiredDate(), card.getIdCard());
	}
	
	public CardVO getCardByUserAndDataCard(Long userId, Long number, String name) {

		CardVO card = null;
		 
		String SQL = "select id_card, name_card, expire_date, number_card, id_user from Card where number_card = ? AND id_user = ? AND name_card like ?";

		List<CardVO> cards = jdbcTemplate.query(SQL, new Object[] { number, userId, name == null ? "%" : name }, new DataMapper());

		if (!cards.isEmpty()) {
			card = cards.get(0);
		}

		return card;
	}

	public List<CardVO> getCardByIdUser(Long id) {
		String SQL = "select id_card, name_card, expire_date, number_card, id_user from Card where id_user = ?";

		List<CardVO> cards = jdbcTemplate.query(SQL, new Object[] { id }, new DataMapper());

		return cards;
	}
	
	public class DataMapper implements RowMapper<CardVO> {
		public CardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CardVO cardVO = new CardVO();
			cardVO.setNameCard(rs.getString("name_card"));
			cardVO.setExpiredDate(rs.getDate("expire_date"));
			cardVO.setIdCard(rs.getLong("id_card"));
			cardVO.setNumberCard(rs.getLong("number_card"));
			cardVO.setIdUser(rs.getLong("id_user"));
			return cardVO;
		}
	}

}
