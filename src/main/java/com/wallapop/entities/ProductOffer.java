package com.wallapop.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ProductOffer {

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private double price;
	private boolean isSold;

	// Cada oferta está asignada a un único usuario, pero cada usuario puede ser
	// dueño de varias ofertas
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;

	// Cada oferta tiene una única compra asociada y viceversa
	@OneToOne()
	@JoinColumn(name = "purchase_id")
	private ProductPurchase purchase;

	public ProductOffer(String title, String description, Date date, double price, User user) {
		super();
		this.title = title;
		this.date = date;
		this.description = description;
		this.price = price;
		this.user = user;

	}

	public ProductOffer() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isSold() {
		return isSold;
	}

	public void setSold(boolean sold) {
		this.isSold = sold;
	}

	public ProductPurchase getPurchase() {
		return purchase;
	}

	public void setPurchase(ProductPurchase purchase) {
		this.purchase = purchase;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
