package app.card.service.card.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import app.card.model.card.CardVO;
import app.card.service.card.dto.CardDto;

@Component
public class CardConverter {

	public CardDto toDto(CardVO cardVO) {
		CardDto cardDto = new CardDto();
		cardDto.setIdCard(cardVO.getIdCard());
		cardDto.setNameCard(cardVO.getNameCard());
		cardDto.setNumberCard(cardVO.getNumberCard());
		cardDto.setExpireDate(cardVO.getExpiredDate());
		cardDto.setIdCard(cardVO.getIdCard());
		return cardDto;
	}

	public List<CardDto> toDtos(List<CardVO> cardsVO) {
		List<CardDto> list = new ArrayList<CardDto>();
		for (CardVO cardVO : cardsVO) {
			list.add(toDto(cardVO));
		}
		return list;
	}
	
	public CardVO toVO(CardDto cardDto) {
		CardVO cardVO = new CardVO();
		cardVO.setIdCard(cardDto.getIdCard());
		cardVO.setNameCard(cardDto.getNameCard());
		cardVO.setNumberCard(cardDto.getNumberCard());
		cardVO.setExpiredDate(cardDto.getExpireDate());
		cardVO.setIdUser(cardDto.getIdUser());
		return cardVO;
	}

	public List<CardVO> toVOs(List<CardDto> cardsDto) {
		List<CardVO> list = new ArrayList<CardVO>();
		for (CardDto cardDto : cardsDto) {
			list.add(toVO(cardDto));
		}
		return list;
	}
	
}
