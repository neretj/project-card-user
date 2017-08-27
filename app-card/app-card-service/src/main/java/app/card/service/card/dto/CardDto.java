package app.card.service.card.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CardDto {

	private Long idCard;

	private String nameCard;

	private Long numberCard;

	@DateTimeFormat(pattern = "yy-MM")
	private Date expireDate;

	private Long idUser;

	public CardDto() {
	}

	public Long getIdCard() {
		return idCard;
	}

	public void setIdCard(Long idCard) {
		this.idCard = idCard;
	}

	public String getNameCard() {
		return nameCard;
	}

	public void setNameCard(String cardName) {
		this.nameCard = cardName;
	}

	public Long getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(Long numberCard) {
		this.numberCard = numberCard;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

}
