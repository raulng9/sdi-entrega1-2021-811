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

	public void purchaseProductOffer(User buyer, ProductOffer offerToBuy) {
		ProductPurchase purchaseToDo = new ProductPurchase(buyer, offerToBuy);

		// TODO bajar saldo, marcar la oferta como comprada, añadir a la lista de
		// ofertas compradas del usuario
		// y guardar todo otra vez en los repos correspondientes
	}

	public List<ProductPurchase> getOffersPurchasedByUser(User buyer) {
		return prodPurchaseRepository.findPurchasesByUser(buyer);
	}

}
