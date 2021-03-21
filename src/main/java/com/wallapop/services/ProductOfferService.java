package com.wallapop.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	public void addOffer(ProductOffer offerToAdd, User user) {
		offerToAdd.setUser(user);
		offerToAdd.setDate(new Date(new java.util.Date().getTime()));
		user.getOffers().add(offerToAdd);
		prodOfferRepository.save(offerToAdd);
		userRepository.save(user);
	}

	public ProductOffer getOffer(Long id) {
		return prodOfferRepository.findById(id).get();
	}

	public List<ProductOffer> getOffersByUser(User user) {
		return prodOfferRepository.findOffersByUser(user);
	}

	public void deleteOffer(Long id) {
		ProductOffer offerToDelete = getOffer(id);
		if (offerToDelete.getPurchase() != null) {
			offerToDelete.getPurchase().setOffer(null);
			offerToDelete.getPurchase().setBuyer(null);
			offerToDelete.getUser().getPurchased().remove(offerToDelete.getPurchase());

		}
		offerToDelete.getUser().getOffers().remove(offerToDelete);
		offerToDelete.setUser(null);
		offerToDelete.setPurchase(null);
		prodOfferRepository.delete(offerToDelete);

	}

	public Page<ProductOffer> getNotBoughtOffers(Pageable pageable, User user) {
		Page<ProductOffer> notBoughtOffers = prodOfferRepository.searchNotBoughtOffers(pageable, user);
		return notBoughtOffers;
	}

	public Page<ProductOffer> searchOffersByTitle(Pageable pageable, String textToSearch, User user) {
		Page<ProductOffer> offers = new PageImpl<ProductOffer>(new LinkedList<ProductOffer>());
		textToSearch = "%" + textToSearch + "%";
		offers = prodOfferRepository.searchByTitle(pageable, textToSearch, user);
		return offers;
	}

}
