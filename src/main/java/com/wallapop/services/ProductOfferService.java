package com.wallapop.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.User;
import com.wallapop.repositories.ProductOfferRepository;
import com.wallapop.repositories.UserRepository;

@Service
public class ProductOfferService {
	@Autowired
	private ProductOfferRepository prodOfferRepository;

	@Autowired
	private UserRepository userRepository;
	
	//TODO añadir id a la oferta? posible ampliación con oferta destacada (extra, no obligatorio)
	public void addOffer(ProductOffer offerToAdd, User user) {
		offerToAdd.setUser(user);
		offerToAdd.setDate(new Date(new java.util.Date().getTime()));
		user.getOffers().add(offerToAdd);
		prodOfferRepository.save(offerToAdd);
		userRepository.save(user);

	}
	
	//El método básico
	public ProductOffer getOffer(Long id) {
		return prodOfferRepository.findById(id).get();
	}
	
	

	public List<ProductOffer> getOffersByUser(User user) {
		return prodOfferRepository.findOffersByUser(user);
	}
}
