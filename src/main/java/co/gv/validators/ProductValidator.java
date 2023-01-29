package co.gv.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import co.gv.entity.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Product.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.empty", "prouct name is mandatory");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "price.empty", "product price is mandatory");

		Product pr = (Product) target;
		Double price = pr.getPrice();
		if (price != null && price < 0) {
			errors.rejectValue("price", "price.invalid", "price must be greater than zero");
		}

		Integer discontinued = pr.getDiscontinued();
		if (discontinued == null) {
			errors.rejectValue("discontinued", "discontinued.not.selected", "please select an option");
		}
	}

}
