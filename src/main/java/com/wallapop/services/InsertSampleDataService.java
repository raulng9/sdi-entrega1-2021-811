package com.wallapop.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private ProductPurchaseRepository prodPurchaseRepository;

	@PostConstruct
	public void init() {

		// USUARIOS

		User u1 = new User("testmail@mail.com", "Raúl", "Test1 Test1");
		u1.setRole("ROLE_CLIENT");
		u1.setPassword("123456");

		User u2 = new User("testmail2@mail.com", "Persona2", "Test2 Test2");
		u2.setRole("ROLE_CLIENT");
		u2.setPassword("123456");

		User u3 = new User("testmail3@mail.com", "Persona3", "Test3 Test3");
		u3.setRole("ROLE_CLIENT");
		u3.setPassword("123456");

		User u4 = new User("testmail4@mail.com", "Persona4", "Test4 Test4");
		u4.setRole("ROLE_CLIENT");
		u4.setPassword("123456");

		User u5 = new User("testmail5@mail.com", "Persona5", "Test5 Test5");
		u5.setRole("ROLE_CLIENT");
		u5.setPassword("123456");

		User u6 = new User("testmail6@mail.com", "Persona6", "Test6 Test6");
		u6.setRole("ROLE_CLIENT");
		u6.setPassword("123456");

		User adminUser = new User("admin@mail.com", "Admin", "Admin1 Admin2");
		adminUser.setRole("ROLE_ADMIN");
		adminUser.setPassword("admin");

		// OFERTAS
		ProductOffer offer1 = new ProductOffer("Funda iPhone", "Transparente con esquinas reforzadas",
				new Date(System.currentTimeMillis()), 8.0, u1);
		ProductOffer offer2 = new ProductOffer("Camiseta fútbol", "Andés C.F. Temporada 20-21",
				new Date(System.currentTimeMillis()), 50.0, u1);
		ProductOffer offer3 = new ProductOffer("Pin Aphex Twin", "Plastificado", new Date(System.currentTimeMillis()),
				2.80, u1);
		ProductOffer offer4 = (new ProductOffer("Libro ciencia ficción", "Autor: Douglas Adams",
				new Date(System.currentTimeMillis()), 42.42, u2));
		ProductOffer offer5 = (new ProductOffer("Kindle Oasis", "Lector e-book con pantalla de tinta electrónica",
				new Date(System.currentTimeMillis()), 250.99, u2));
		ProductOffer offer6 = (new ProductOffer("Estantería para libros", "Madera de roble",
				new Date(System.currentTimeMillis()), 70.50, u2));

		ProductOffer offer7 = new ProductOffer("Funda iPad", "Transparente con esquinas reforzadas",
				new Date(System.currentTimeMillis()), 29.0, u3);
		ProductOffer offer8 = new ProductOffer("Camiseta fútbol", "Reading F.C.", new Date(System.currentTimeMillis()),
				55.0, u3);
		ProductOffer offer9 = new ProductOffer("CD Blur", "Modern Life is Rubbish, como nuevo",
				new Date(System.currentTimeMillis()), 8.50, u3);
		ProductOffer offer10 = (new ProductOffer("Libro ciencia ficción", "Autor: Ray Bradbury",
				new Date(System.currentTimeMillis()), 15.80, u4));
		ProductOffer offer11 = (new ProductOffer("Kindle Paperwhite", "Lector e-book con pantalla de tinta electrónica",
				new Date(System.currentTimeMillis()), 120.99, u4));
		ProductOffer offer12 = (new ProductOffer("Casco ciclismo", "Marca Bell, seminuevo",
				new Date(System.currentTimeMillis()), 39.99, u4));

		ProductOffer offer13 = new ProductOffer("Portátil MacBookPro", "Modelo late 2015, como nuevo",
				new Date(System.currentTimeMillis()), 600.0, u5);
		ProductOffer offer14 = new ProductOffer("Disco duro portátil", "Toshiba 1TB HDD, con funda incluida",
				new Date(System.currentTimeMillis()), 62.0, u5);
		ProductOffer offer15 = new ProductOffer("Balón de fútbol", "Adidas Jabulani Mundial 2010",
				new Date(System.currentTimeMillis()), 12.50, u5);
		ProductOffer offer16 = (new ProductOffer("Libro: La broma infinita",
				"Autor: David Foster Wallace, marcas de uso", new Date(System.currentTimeMillis()), 14.00, u6));
		ProductOffer offer17 = (new ProductOffer("Objetivo Nikkon 50mm F2.8", "Como nuevo",
				new Date(System.currentTimeMillis()), 320.00, u6));
		ProductOffer offer18 = (new ProductOffer("Altavoz inalámbrico Sony", "Sin casi uso",
				new Date(System.currentTimeMillis()), 89.99, u6));

		Set<ProductOffer> offersOfUser1 = new HashSet<ProductOffer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer1);
				add(offer2);
				add(offer3);

			}
		};
		u1.setOffers(offersOfUser1);

		Set<ProductOffer> offersOfUser2 = new HashSet<ProductOffer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer4);
				add(offer5);
				add(offer6);

			}
		};
		u2.setOffers(offersOfUser2);

		Set<ProductOffer> offersOfUser3 = new HashSet<ProductOffer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer7);
				add(offer8);
				add(offer9);

			}
		};
		u3.setOffers(offersOfUser3);

		Set<ProductOffer> offersOfUser4 = new HashSet<ProductOffer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer10);
				add(offer11);
				add(offer12);

			}
		};
		u4.setOffers(offersOfUser4);

		Set<ProductOffer> offersOfUser5 = new HashSet<ProductOffer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer13);
				add(offer14);
				add(offer15);

			}
		};
		u5.setOffers(offersOfUser5);

		Set<ProductOffer> offersOfUser6 = new HashSet<ProductOffer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			{

				add(offer16);
				add(offer17);
				add(offer18);

			}
		};
		u6.setOffers(offersOfUser6);

		/// AÑADIDO DE USUARIOS
		userService.addUser(u1);
		userService.addUser(u2);
		userService.addUser(u3);
		userService.addUser(u4);
		userService.addUser(u5);
		userService.addUser(u6);
		userService.addUser(adminUser);

		// AÑADIDO DE OFERTAS

		prodOfferService.addOffer(offer1, u1);
		prodOfferService.addOffer(offer2, u1);
		prodOfferService.addOffer(offer3, u1);
		prodOfferService.addOffer(offer4, u2);
		prodOfferService.addOffer(offer5, u2);
		prodOfferService.addOffer(offer6, u2);
		prodOfferService.addOffer(offer7, u3);
		prodOfferService.addOffer(offer8, u3);
		prodOfferService.addOffer(offer9, u3);
		prodOfferService.addOffer(offer10, u4);
		prodOfferService.addOffer(offer11, u4);
		prodOfferService.addOffer(offer12, u4);
		prodOfferService.addOffer(offer13, u5);
		prodOfferService.addOffer(offer14, u5);
		prodOfferService.addOffer(offer15, u5);
		prodOfferService.addOffer(offer16, u6);
		prodOfferService.addOffer(offer17, u6);
		prodOfferService.addOffer(offer18, u6);

		// AÑADIDO DE COMPRAS MÍNIMAS EFECTUADAS

		prodPurchaseService.buyProduct(u1, offer4);
		prodPurchaseService.buyProduct(u1, offer6);
		prodPurchaseService.buyProduct(u2, offer1);
		prodPurchaseService.buyProduct(u2, offer2);
		prodPurchaseService.buyProduct(u3, offer3);
		prodPurchaseService.buyProduct(u3, offer10);
		prodPurchaseService.buyProduct(u4, offer8);
		prodPurchaseService.buyProduct(u4, offer9);
		prodPurchaseService.buyProduct(u5, offer17);
		prodPurchaseService.buyProduct(u5, offer18);
		prodPurchaseService.buyProduct(u6, offer11);
		prodPurchaseService.buyProduct(u6, offer15);
	}

	public void restartDBData() {
		userRepository.deleteAll();
		prodOfferRepository.deleteAll();
		prodPurchaseRepository.deleteAll();
	}

	// TODO mirar constructores de Date para poder incluir parsing de fechas en
	// string

}
