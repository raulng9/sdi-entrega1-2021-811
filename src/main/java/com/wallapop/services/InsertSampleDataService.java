package com.wallapop.services;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallapop.entities.ProductOffer;
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
	public void init() {

		// USUARIOS

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

		// OFERTAS
		ProductOffer offer1 = new ProductOffer("Funda iPhone", "Transparente con esquinas reforzadas",
				new Date(System.currentTimeMillis()), 8.0, u1);
		ProductOffer offer2 = new ProductOffer("Camiseta fútbol", "Andés C.F. Temporada 20-21",
				new Date(System.currentTimeMillis()), 50.0, u2);
		ProductOffer offer3 = new ProductOffer("Pin Aphex Twin", "Plastificado", new Date(System.currentTimeMillis()),
				2.80, u3);
		ProductOffer offer4 = (new ProductOffer("Libro ciencia ficción", "Autor: Douglas Adams",
				new Date(System.currentTimeMillis()), 42.42, u4));
		ProductOffer offer5 = (new ProductOffer("Kindle Oasis", "Lector e-book con pantalla de tinta electrónica",
				new Date(System.currentTimeMillis()), 250.99, u1));
		ProductOffer offer6 = (new ProductOffer("Estantería para libros", "Madera de roble",
				new Date(System.currentTimeMillis()), 70.50, u1));

		// OFERTAS
		ProductOffer offer7 = new ProductOffer("Funda iPad", "Transparente con esquinas reforzadas",
				new Date(System.currentTimeMillis()), 29.0, u5);
		ProductOffer offer8 = new ProductOffer("Camiseta fútbol", "Reading F.C.",
				new Date(System.currentTimeMillis()), 55.0, u5);
		ProductOffer offer9 = new ProductOffer("CD Blur", "Modern Life is Rubbish, como nuevo", new Date(System.currentTimeMillis()),
				8.50, u2);
		ProductOffer offer10 = (new ProductOffer("Libro ciencia ficción", "Autor: Ray Bradbury",
				new Date(System.currentTimeMillis()), 15.80, u3));
		ProductOffer offer11 = (new ProductOffer("Kindle Paperwhite", "Lector e-book con pantalla de tinta electrónica",
				new Date(System.currentTimeMillis()), 120.99, u4));
		ProductOffer offer12 = (new ProductOffer("Casco ciclismo", "Marca Bell, seminuevo",
				new Date(System.currentTimeMillis()), 39.99, u5));

		prodOfferService.addOffer(offer1, u1);
		prodOfferService.addOffer(offer2, u2);
		prodOfferService.addOffer(offer3, u3);
		prodOfferService.addOffer(offer4, u4);
		prodOfferService.addOffer(offer5, u1);
		prodOfferService.addOffer(offer6, u1);
		
		prodOfferService.addOffer(offer7, u5);
		prodOfferService.addOffer(offer8, u5);
		prodOfferService.addOffer(offer9, u2);
		prodOfferService.addOffer(offer10, u3);
		prodOfferService.addOffer(offer11, u3);
		prodOfferService.addOffer(offer12, u3);
		

	}

	// TODO mirar constructores de Date para poder incluir parsing de fechas en string

}
