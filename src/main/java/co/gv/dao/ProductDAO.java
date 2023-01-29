package co.gv.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.gv.entity.Product;

@Transactional(rollbackFor = { DAOException.class }, readOnly = true)
public interface ProductDAO {
	// CRUD OPERATIONS
	@Transactional(readOnly = false)
	public default void addProduct(Product product) throws DAOException {
		throw new DAOException("method not implemented");
	}

	@Transactional(readOnly = false)
	public default void updateProduct(Product product) throws DAOException {
		throw new DAOException("method not implemented");
	}

	public default Product getProduct(Integer productId) throws DAOException {
		throw new DAOException("method not implemented");
	}

	@Transactional(readOnly = false)
	public default void deleteproduct(Integer productId) throws DAOException {
		throw new DAOException("method not implemented");
	}

	// QUERIES
	public default List<Product> getAllProducts() throws DAOException {
		throw new DAOException("method not implemented");
	}

	public default List<Product> getProductByPriceRange(Double min, Double max) throws DAOException {
		throw new DAOException("method not implemented");
	}

	public default List<Product> getDiscontinuedProducts() throws DAOException {
		throw new DAOException("method not implemented");
	}

	public default long count() throws DAOException {
		throw new DAOException("method not implemented");
	}

}
