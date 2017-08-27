package app.card.model.card.dao;

import java.util.List;

import app.card.model.card.CardVO;

public interface CardDao {

	public void saveCard(CardVO card);

	public void updateCard(CardVO card);
	
	List<CardVO> getCardByIdUser(Long id);
	
	CardVO getCardByUserAndDataCard(Long userId, Long number, String name);
}
