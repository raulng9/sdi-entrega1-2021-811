package com.wallapop.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wallapop.entities.User;
import com.wallapop.services.ConversationService;
import com.wallapop.services.ProductOfferService;
import com.wallapop.services.ProductPurchaseService;
import com.wallapop.services.SecurityService;
import com.wallapop.services.UserService;
import com.wallapop.validators.SignUpValidator;

@Controller
public class UserController {
	@Autowired
	private UserService usersService;

	@Autowired
	private ProductOfferService pOfferService;

	@Autowired
	private ProductPurchaseService pPurchaseService;

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private SignUpValidator signUpValidator;

	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/user/list")
	public String getListado(Model model, Pageable pageable, Principal principal) {
		User user = usersService.getUserByEmail(principal.getName());
		model.addAttribute("usersList", usersService.getUsers(pageable, user));
		return "user/list";
	}
	
	//Creación de un nuevo usuario si la validación es correcta
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(@Validated User user, BindingResult result) {
		signUpValidator.validate(user, result);
		if (result.hasErrors()) {
			return "signup";
		}
		usersService.addUser(user);
		securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
		return "redirect:home";
	}
	
	//Envío a la página de signup
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}


}
