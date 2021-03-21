package com.wallapop.controllers;

import java.security.Principal;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

import com.wallapop.entities.User;
import com.wallapop.services.ProductOfferService;
import com.wallapop.services.ProductPurchaseService;
import com.wallapop.services.SecurityService;
import com.wallapop.services.UserService;
import com.wallapop.validators.SignUpValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private ProductOfferService prodOfferService;

	@Autowired
	private ProductPurchaseService prodPurchaseService;


	@Autowired
	private SignUpValidator signUpValidator;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);


	// TODO hará falta un validador para postear nuevas ofertas de producto

	@RequestMapping("/user/list")
	public String getListado(Model model, Principal principal) {
		User user = userService.getUserByEmail(principal.getName());
		model.addAttribute("usersList", userService.getUsers(user));
		return "user/list";
	}

	// Creación de un nuevo usuario si la validación es correcta
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		userService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		logger.debug(String.format("Registration and login as user %s done with success", user.getEmail()));
		return "redirect:home";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping("/user/delete")
	public String delete(@RequestParam("id") String[] ids) {
		System.out.println(ids);
		userService.deleteUser(ids);
		return "redirect:/user/list";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user = userService.getUserByEmail(email);
		model.addAttribute("saldo", user.getSaldo());
		model.addAttribute("offerList", prodOfferService.getOffersByUser(user));
		model.addAttribute("purchasedList", prodPurchaseService.getOffersPurchasedByUser(user));
		model.addAttribute("completeName", user.getFullName());
		return "home";
	}
}
