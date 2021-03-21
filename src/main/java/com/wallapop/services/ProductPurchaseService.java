package com.wallapop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.ProductPurchase;
import com.wallapop.entities.User;
import com.wallapop.repositories.ProductOfferRepository;
import com.wallapop.repositories.ProductPurchaseRepository;
import com.wallapop.repositories.UserRepository;

@Service
public class ProductPurchaseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ProductPurchaseRepository prodPurchaseRepository;

	@Autowired
	private ProductOfferRepository prodOfferRepository;

	public void addPurchase(ProductPurchase purchaseToAdd) {
		prodPurchaseRepository.save(purchaseToAdd);
	}

	public List<ProductPurchase> getOffersPurchasedByUser(User buyer) {
		return prodPurchaseRepository.findPurchasesByUser(buyer);
	}

	public void buyProduct(User user, ProductOffer offerToBuy) {
		ProductPurchase purchase = new ProductPurchase(user, offerToBuy);
		// Antes de efectuar la compra, comprobamos que haya saldo suficiente y que el
		// usuario no sea también el dueño de la oferta
		if (user.getSaldo() >= offerToBuy.getPrice() && user.getEmail() != offerToBuy.getUser().getEmail()) {
			purchase.getOffer().setSold(true);
			purchase.getBuyer().setSaldo(user.getSaldo() - offerToBuy.getPrice());
			purchase.getBuyer().getPurchased().add(purchase);
			addPurchase(purchase);
			offerToBuy.setPurchase(purchase);
			user.getPurchased().add(purchase);
			prodOfferRepository.save(purchase.getOffer());
			userRepository.save(purchase.getBuyer());
		}
	}

}
