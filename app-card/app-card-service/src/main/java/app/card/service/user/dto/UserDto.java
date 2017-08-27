package app.card.service.user.dto;

import java.util.List;

import app.card.service.card.dto.CardDto;

public class UserDto {

	private Long idUser;

	private String username;

	private String password;
	
	private String passwordConfirm;
	
	private String name;
	
	private String surname;
	
	private List<CardDto> cards;

	private RoleDto role;

	public UserDto() {
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public List<CardDto> getCards() {
		return cards;
	}

	public void setCards(List<CardDto> cards) {
		this.cards = cards;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	};

}
