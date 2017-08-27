package app.card.service.card;

import java.util.List;

import app.card.service.card.dto.CardDto;

public interface CardService {

	CardDto saveOrUpdateCard(CardDto cardDto);
	
	List<CardDto> getAllCardsFromUser(String username);
	
	List<CardDto> getCardsByNumberCard(String username, Long numberCard);
}
