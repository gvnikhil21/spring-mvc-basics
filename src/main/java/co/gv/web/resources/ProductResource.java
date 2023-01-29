package co.gv.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.gv.dao.DAOException;
import co.gv.dao.ProductDAO;
import co.gv.entity.ErrorResponse;
import co.gv.entity.Product;
import co.gv.entity.ProductList;

@RequestMapping("/api/products")
@RestController
public class ProductResource {

	@Autowired
	ProductDAO htDAO;

	@RequestMapping(method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	public ResponseEntity<ProductList> getAllProducts() throws DAOException {
		ProductList pl = new ProductList();
		pl.setProducts(htDAO.getAllProducts());
		return ResponseEntity.ok(pl);
	}

	@RequestMapping(path = "/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<?> getProductByID(@PathVariable("id") Integer id) throws DAOException {
		Product pr = htDAO.getProduct(id);
		if (pr == null) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage("No product found");
			er.setData(id);
			return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(pr);
	}

	@RequestMapping(method = RequestMethod.POST, produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<?> addProduct(@RequestBody Product pr) {
		try {
			htDAO.addProduct(pr);
			return ResponseEntity.ok(pr);
		} catch (DAOException e) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage(e.getMessage());
			er.setData(pr);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/{id}", produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product pr) {
		try {
			pr.setProductNo(id);
			htDAO.updateProduct(pr);
			return ResponseEntity.ok(pr);
		} catch (DAOException e) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage(e.getMessage());
			er.setData(pr);
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE, produces = { "application/json", "application/xml" })
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		try {
			Product pr = htDAO.getProduct(id);
			if (pr == null) {
				ErrorResponse er = new ErrorResponse();
				er.setMessage("No product found");
				er.setData(id);
				return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
			}
			htDAO.deleteproduct(id);
			pr = htDAO.getProduct(id);
			return ResponseEntity.ok(pr);
		} catch (DAOException e) {
			ErrorResponse er = new ErrorResponse();
			er.setMessage(e.getMessage());
			return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
