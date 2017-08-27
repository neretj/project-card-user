package app.card.web.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import app.card.service.card.dto.CardDto;

@Component
public class CardValidator implements Validator {

	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object o, Errors errors) {

		CardDto card = (CardDto) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameCard", "commons.field.empty");
		
		if (card.getNameCard().length() < 6) {
			errors.rejectValue("nameCard", "cardForm.namecard.max.size");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberCard", "commons.field.empty");
		
		if (card.getNumberCard().toString().length() != 16) {
			errors.rejectValue("numberCard", "cardForm.valid.numbercard");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expireDate", "commons.field.empty");
		
		if (card.getExpireDate() != null) {
			Calendar currentDate = Calendar.getInstance();
			int currentYear = currentDate.get(Calendar.YEAR);
			int currentMonth = currentDate.get(Calendar.MONTH);
			DateFormat formatoFecha = new SimpleDateFormat("MM/yyyy");
			try {
				Date date = formatoFecha.parse(currentMonth + "/" + currentYear);
				if (card.getExpireDate().before(date)) {
					errors.rejectValue("expireDate", "cardForm.expire.date");
				}
			} catch (ParseException e) {
			}			
		}

	}
}