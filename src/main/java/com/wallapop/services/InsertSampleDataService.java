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
		
		User u2 = new User("testmail2@mail.com", "Persona2", "Test2 Test2");
		u2.setRole("ROLE_CLIENT");
		u2.setPassword("123456");
		
		userService.addUser(u2);
		
		User u3 = new User("testmail3@mail.com", "Persona3", "Test3 Test3");
		u3.setRole("ROLE_CLIENT");
		u3.setPassword("123456");
		
		userService.addUser(u3);
		
		User u4 = new User("testmail4@mail.com", "Persona4", "Test4 Test4");
		u4.setRole("ROLE_CLIENT");
		u4.setPassword("123456");
		
		userService.addUser(u4);
		
		User u5 = new User("testmail5@mail.com", "Persona5", "Test5 Test5");
		u5.setRole("ROLE_CLIENT");
		u5.setPassword("123456");
		
		userService.addUser(u5);
		
		User u6 = new User("testmail6@mail.com", "Persona6", "Test6 Test6");
		u6.setRole("ROLE_CLIENT");
		u6.setPassword("123456");
		
		userService.addUser(u6);
		
		User adminUser = new User("admin@mail.com", "Admin", "Admin1 Admin2");
		adminUser.setRole("ROLE_ADMIN");
		adminUser.setPassword("123456");
		
		userService.addUser(adminUser);

	}


}
