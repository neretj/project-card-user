package card;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import app.card.model.card.CardVO;
import app.card.model.card.dao.CardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-test-model.xml")
public class CardTest {

	@Autowired
	private CardDao cardDao;

	@Test
	public void saveCardTest() throws ParseException {
		
		Long idUser = new Long(9);
		Long numberCard = Long.valueOf("2342342424324242");
		
		CardVO card = new CardVO();
		
		card.setNameCard("Name Card Test");
		
		SimpleDateFormat format = new SimpleDateFormat("yy/MM");
		Date date = format.parse("15/02");
		
		card.setExpiredDate(date);
		
		card.setNumberCard(numberCard);
		
		card.setIdUser(idUser);
		
		try {
			cardDao.saveCard(card);	
			System.out.println("Card saved");
			
		} catch (Exception e) {
			System.out.println("ERROR saving card " + e);
		}
	}

}
