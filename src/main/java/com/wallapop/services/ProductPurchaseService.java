package com.wallapop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallapop.entities.ProductPurchase;
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

}
