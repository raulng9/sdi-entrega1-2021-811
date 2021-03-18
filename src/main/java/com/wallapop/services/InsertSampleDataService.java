package com.wallapop.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallapop.entities.User;
import com.wallapop.repositories.ProductOfferRepository;
import com.wallapop.repositories.ProductPurchaseRepository;
import com.wallapop.repositories.UserRepository;

@Service
public class InsertSampleDataService {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductOfferService prodOfferService;

	@Autowired
	private ProductPurchaseService prodPurchaseService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductOfferRepository prodOfferRepository;

	@Autowired
	private ProductPurchaseRepository prodPurchaseRepositor;
	
	@PostConstruct
	public void init(){

		User u1 = new User("testmail@mail.com", "Raúl", "Test1 Test1");
		u1.setRole("ROLE_CLIENT");
		u1.setPassword("123456");
		
		userService.addUser(u1);

	}


}
