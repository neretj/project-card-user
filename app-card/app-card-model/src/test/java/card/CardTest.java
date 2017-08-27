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
		
		CardVO card = new CardVO();
		
		card.setNameCard("Name Card Test");
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date = format.parse("02/07/2019");
		
		card.setExpiredDate(date);
		
		card.setNumberCard(new Long(342413423));
		
		card.setIdUser(new Long(4));
		
		try {
			cardDao.saveCard(card);	
			System.out.println("Card saved with id: " + card.getIdCard());
			
		} catch (Exception e) {
			System.out.println("ERROR saving card " + e);
		}
	}

}
