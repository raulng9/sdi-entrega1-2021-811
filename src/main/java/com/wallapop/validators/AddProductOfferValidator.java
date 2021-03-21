package com.wallapop.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.wallapop.entities.ProductOffer;
import com.wallapop.entities.User;

@Component
public class AddProductOfferValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductOffer offer = (ProductOffer) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "Error.empty");

		if (offer.getDescription().length() < 5 || offer.getDescription().length() > 30) {
			errors.rejectValue("description", "Error.offer.description.length");
		}

		if (offer.getPrice() <= 0 || offer.getPrice() > 9999) {
			errors.rejectValue("price", "Error.pricerange");
		}

	}
}
