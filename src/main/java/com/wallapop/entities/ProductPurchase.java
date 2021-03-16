package com.wallapop.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductPurchase {
	@Id
	@GeneratedValue
	private Long id;

	private ProductOffer offerBought;

	private User buyerUser;

	public ProductPurchase(ProductOffer offerBought, User buyerUser) {
		super();
		this.offerBought = offerBought;
		this.buyerUser = buyerUser;
	}

	public ProductPurchase() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductOffer getOffer() {
		return offerBought;
	}

	public void setOffer(ProductOffer offerBought) {
		this.offerBought = offerBought;
	}

	public User getBuyer() {
		return buyerUser;
	}

	public void setBuyer(User buyerUser) {
		this.buyerUser = buyerUser;
	}

}
