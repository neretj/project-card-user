package app.card.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import app.card.service.card.CardService;
import app.card.service.card.dto.CardDto;
import app.card.service.user.UserService;
import app.card.service.user.dto.UserDto;
import app.card.web.controller.criteria.SearchCriteria;
import app.card.web.util.UserCredentialsUtils;
import app.card.web.validator.CardValidator;
import app.card.web.validator.UserValidator;

@Controller
public class UserController {

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CardValidator cardValidator;

	@Autowired
	private UserService userService;

	@Autowired
	private CardService cardService;

	@RequestMapping(value = "/newCard", method = RequestMethod.GET)
	public String newCard(Model model) {

		model.addAttribute("cardForm", new CardDto());

		return "newCard";
	}

	@RequestMapping(value = "/newCard", method = RequestMethod.POST)
	public String newCard(@ModelAttribute("cardForm") CardDto cardForm, BindingResult bindingResult, Model model,
			String message) {

		UserDetails userDetails = UserCredentialsUtils.getUserDetailsFromCredentialsLocale();

		String username = userDetails.getUsername();

		UserDto user = userService.getUserLogin(username);

		if (user != null && user.getIdUser() != null) {

			cardValidator.validate(cardForm, bindingResult);

			if (bindingResult.hasErrors()) {
				return "newCard";
			}

			cardForm.setIdUser(user.getIdUser());
			cardService.saveOrUpdateCard(cardForm);
		}

		return "redirect:/listCards";
	}

	@RequestMapping(value = "/listCards", method = RequestMethod.GET)
	public String getAllCards(Model model) {

		UserDetails userDetails = UserCredentialsUtils.getUserDetailsFromCredentialsLocale();

		String username = userDetails.getUsername();

		List<CardDto> cards = cardService.getAllCardsFromUser(username);

		model.addAttribute("cards", cards);
		model.addAttribute("listCardForm", new SearchCriteria());

		return "listCards";
	}

	@RequestMapping(value = "/listCards", method = RequestMethod.POST)
	public String getAllCards(@ModelAttribute(value = "listCardForm") SearchCriteria searchNumberCard,
			BindingResult bindingResult, Model model, String message) {

		ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "searchNumber", "commons.field.empty");

		UserDetails userDetails = UserCredentialsUtils.getUserDetailsFromCredentialsLocale();

		if (bindingResult.hasErrors()) {
			return "listCards";
		}

		Long number = Long.valueOf(searchNumberCard.getSearchNumber());

		String username = userDetails.getUsername();

		List<CardDto> cards = cardService.getCardsByNumberCard(username, number);

		if (cards.isEmpty()) {
			model.addAttribute("message", "No cards found");
		}

		model.addAttribute("cards", cards);

		return "listCards";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new UserDto());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") UserDto userForm, BindingResult bindingResult, Model model) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "registration";
		}

		try {

			userService.registerUser(userForm);
			userService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

		} catch (Exception e) {

		}

		return "redirect:/index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {

		if (error != null) {
			model.addAttribute("error", "Invalid username or password");
		}

		if (logout != null) {
			model.addAttribute("message", "You have been logged out successfully.");
		}

		return "login";
	}

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String welcome(Model model) {
		return "index";
	}
}
