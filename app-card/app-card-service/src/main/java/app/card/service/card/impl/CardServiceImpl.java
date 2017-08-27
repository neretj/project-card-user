package app.card.service.card.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.card.model.card.CardVO;
import app.card.model.card.dao.CardDao;
import app.card.model.user.UserVO;
import app.card.model.user.dao.UserDao;
import app.card.service.card.CardService;
import app.card.service.card.dto.CardDto;
import app.card.service.card.dto.converter.CardConverter;

@Service
public class CardServiceImpl implements CardService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private CardDao cardDao;

	@Autowired
	private CardConverter cardConverter;

	public CardDto saveOrUpdateCard(CardDto cardDto) {

		CardVO card = cardDao.getCardByUserAndDataCard(cardDto.getIdUser(), cardDto.getNumberCard(),
				cardDto.getNameCard());

		CardVO cardVO = cardConverter.toVO(cardDto);

		if (card != null) {
			cardVO.setIdCard(card.getIdCard());
			cardDao.updateCard(cardVO);
		} else {
			cardDao.saveCard(cardVO);
		}

		return cardConverter.toDto(cardVO);
	}

	public List<CardDto> getAllCardsFromUser(String username) {

		UserVO user = null;

		user = userDao.getUserData(username);

		List<CardDto> cards = null;

		if (user != null) {
			cards = cardConverter.toDtos(cardDao.getCardByIdUser(user.getIdUser()));
		}

		return cards;

	}

	public List<CardDto> getCardsByNumberCard(String username, Long numberCard) {

		UserVO user = null;

		user = userDao.getUserData(username);

		String numberCardSearched = numberCard.toString();

		List<CardDto> cardsResult = new ArrayList<CardDto>();

		if (user != null && numberCardSearched != null) {

			List<CardVO> searchCards = cardDao.getCardByIdUser(user.getIdUser());

			for (CardVO cardVO : searchCards) {
				String numberCardStr = cardVO.getNumberCard().toString();
				if (numberCardStr.contains(numberCardSearched)) {
					cardsResult.add(cardConverter.toDto(cardVO));
				}
			}
		}

		return cardsResult;
	}

}
