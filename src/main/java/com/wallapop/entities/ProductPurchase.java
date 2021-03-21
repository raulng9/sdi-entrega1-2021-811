package com.wallapop.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class ProductPurchase {
	@Id
	@GeneratedValue
	private Long id;

	@OneToOne()
	@JoinColumn(name = "productoffer_id")
	private ProductOffer offerBought;

	@ManyToOne()
	@JoinColumn(name = "buyer_id")
	private User buyer;

	public ProductPurchase(User buyer, ProductOffer offerBought) {
		super();
		this.offerBought = offerBought;
		this.buyer = buyer;
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
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public String getBuyerInfo() {
		return buyer.getFullName();
	}

	public String getTitle() {
		return offerBought.getTitle();
	}

	public double getPrice() {
		return offerBought.getPrice();
	}

	public String getOfferEmail() {
		return offerBought.getUser().getEmail();
	}
	
	public String getSellerInfo() {
		return offerBought.getUser().getFullName();
	}

	public String getDescription() {
		return offerBought.getDescription();
	}

}
