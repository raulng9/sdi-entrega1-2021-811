package com.wallapop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallapop.entities.ProductOffer;
import com.wallapop.repositories.ProductOfferRepository;
import com.wallapop.repositories.UserRepository;

@Service
public class ProductOfferService {
	@Autowired
	private ProductOfferRepository prodOfferRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	//El método básico
	public ProductOffer getOffer(Long id) {
		return prodOfferRepository.findById(id).get();
	}
}
