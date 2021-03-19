package com.wallapop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.User;
import com.wallapop.services.ProductOfferService;
import com.wallapop.services.ProductPurchaseService;
import com.wallapop.services.UserService;
import com.wallapop.validators.AddProductOfferValidator;

@Controller
public class ProductOfferController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductOfferService prodOfferService;

	@Autowired
	private ProductPurchaseService prodPurchaseService;

	@Autowired
	private AddProductOfferValidator addProdOfferValidator;
	
	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, @Validated ProductOffer prodOffer, BindingResult result, @RequestParam(value = "id", required=false) String id) {
		addProdOfferValidator.validate(prodOffer, result);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		if (result.hasErrors()) {
			return "productoffer/add";
		}
		//TODO método para añadir oferta a la repo y enlazar con el usuario dueño
		//Después de añadir la oferta volver a la vista principal
		prodOfferService.addOffer(prodOffer,user);
		return "redirect:/home";
	}
	
	//La request GET redirecciona a POST como en el lab
	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("offer", new ProductOffer());
		return "productoffer/add";
	}
	
	
	
}
