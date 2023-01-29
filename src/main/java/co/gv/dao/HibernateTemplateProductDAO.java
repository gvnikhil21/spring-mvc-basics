package co.gv.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import co.gv.entity.Product;

@Repository("htDAO")
@SuppressWarnings("unchecked")
public class HibernateTemplateProductDAO implements ProductDAO {

	@Autowired
	private HibernateTemplate template;

	@Override
	public void addProduct(Product product) throws DAOException {
		template.persist(product);
	}

	@Override
	public void updateProduct(Product product) throws DAOException {
		template.merge(product);
	}

	@Override
	public Product getProduct(Integer productId) throws DAOException {
		return template.get(Product.class, productId);
	}

	@Override
	public void deleteproduct(Integer productId) throws DAOException {
		Product p = getProduct(productId);
		p.setDiscontinued(1);
		template.merge(p);
	}

	@Override
	public List<Product> getAllProducts() throws DAOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getProductByPriceRange(Double min, Double max) throws DAOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.between("price", min, max));
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public List<Product> getDiscontinuedProducts() throws DAOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.add(Restrictions.eq("discontinued", 1));
		return (List<Product>) template.findByCriteria(dc);
	}

	@Override
	public long count() throws DAOException {
		DetachedCriteria dc = DetachedCriteria.forClass(Product.class);
		dc.setProjection(Projections.rowCount());
		return (long) template.findByCriteria(dc).get(0);

	}

}
