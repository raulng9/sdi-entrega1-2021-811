package com.wallapop.controllers;

import java.util.LinkedList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

	private static final Logger logger = LogManager.getLogger(ProductOfferController.class);

	@RequestMapping(value = "/offer/add", method = RequestMethod.POST)
	public String setOffer(Model model, @ModelAttribute("offer") ProductOffer prodOffer, BindingResult result,
			@RequestParam(value = "id", required = false) String id) {
		addProdOfferValidator.validate(prodOffer, result);
		if (result.hasErrors()) {
			return "productoffer/add";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		prodOfferService.addOffer(prodOffer, user);
		logger.debug(String.format("The user with email %s has added a new offer to the market", user.getEmail()));
		return "redirect:/home";
	}

	@RequestMapping(value = "/offer/add")
	public String getOffer(Model model) {
		model.addAttribute("offer", new ProductOffer());
		return "productoffer/add";
	}

	@RequestMapping("/offer/delete/{id}")
	public String deleteMark(@PathVariable Long id) {
		logger.debug(String.format("The user %s has deleted one of his offers from the market",
				prodOfferService.getOffer(id).getUser().getEmail()));
		prodOfferService.deleteOffer(id);
		return "redirect:/home";
	}

	@RequestMapping(value = "/market", method = RequestMethod.GET)
	public String showCatalogue(Model model, Pageable pageable,
			@RequestParam(value = "", required = false) String textToSearch) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByEmail(auth.getName());
		Page<ProductOffer> offers = new PageImpl<ProductOffer>(new LinkedList<ProductOffer>());
		if (textToSearch != null && !textToSearch.isEmpty()) {
			offers = prodOfferService.searchOffersByTitle(pageable, textToSearch, user);
		} else {
			offers = prodOfferService.getNotBoughtOffers(pageable, user);
		}

		model.addAttribute("offerList", offers);
		model.addAttribute("List", offers);
		model.addAttribute("page", offers);
		model.addAttribute("user", user);

		return "market";
	}

	@RequestMapping(value = "/market/buyOffer/{id}", method = RequestMethod.POST)
	public String buyOffer(Model model, @PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = userService.getUserByEmail(auth.getName());
		prodPurchaseService.buyProduct(activeUser, prodOfferService.getOffer(id));
		logger.debug(String.format("The user with email %s has bought the offer with id %s", activeUser.getEmail(),
				String.valueOf(id)));
		return "redirect:/market/update";

	}

	@RequestMapping("/market/update")
	public String updateMarketAfterBuy(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = userService.getUserByEmail(auth.getName());
		model.addAttribute("offerList", prodOfferService.getNotBoughtOffers(pageable, activeUser));
		model.addAttribute("user", activeUser);
		return "market :: tableOffers";
	}

	@RequestMapping("/market/updateuser")
	public String updateUserInfoAfterBuy(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User activeUser = userService.getUserByEmail(auth.getName());
		model.addAttribute("user", activeUser);
		return "market :: userData";
	}

}
