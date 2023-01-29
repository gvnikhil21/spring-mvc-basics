package co.gv.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.gv.dao.DAOException;
import co.gv.dao.ProductDAO;
import co.gv.entity.Product;
import co.gv.validators.ProductValidator;

@Controller
public class ProductController {

	@Autowired
	ProductDAO htDAO;

	@RequestMapping(method = RequestMethod.GET, path = "/all-products")
	public String getAllProducts(Model model) throws DAOException {
		model.addAttribute("pageTitle", "Product List");
		model.addAttribute("products", htDAO.getAllProducts());
		return "show-products";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/products-by-price-range")
	public String getProductsByPriceRange(Model model, @RequestParam Double min, @RequestParam Double max)
			throws DAOException {
		model.addAttribute("pageTitle", "Products Priced between Rs." + min + " and Rs." + max);
		model.addAttribute("products", htDAO.getProductByPriceRange(min, max));
		return "show-products";
	}

	@RequestMapping("/product-details")
	public String getProductDetails(@RequestParam Integer id, Model model) throws DAOException {
		model.addAttribute("pr", htDAO.getProduct(id));
		return "product-details";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/add-product")
	public String addProduct(Model model) {
		model.addAttribute("pr", new Product());
		return "product-form";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/edit-product")
	public String editProduct(Model model, @RequestParam Integer id) throws DAOException {
		model.addAttribute("pr", htDAO.getProduct(id));
		return "product-form";
	}

	@RequestMapping(method = RequestMethod.POST, path = "/save-product")
	public String saveProduct(@ModelAttribute("pr") Product pr, BindingResult errors) throws DAOException {
		ProductValidator pv = new ProductValidator();
		pv.validate(pr, errors);
		if (errors.hasErrors()) {
			return "product-form";
		}
		if (pr.getProductNo() == null) {
			htDAO.addProduct(pr);
		} else {
			htDAO.updateProduct(pr);
		}
		return "redirect:product-details?id=" + pr.getProductNo();
	}
}
