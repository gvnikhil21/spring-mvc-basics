package co.gv.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
//if class name is different than the table name then you need @Table annotation
@Table(name = "products")
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Product {
	// if column name is different than the field name here, then use @Column(name =
	// "column_name" for all such fields)
	//
	/*
	 * @Column(name = "supplier_id", insertable = false, updatable = false) private
	 * Integer supplierId; //as there are two same columnName fields for supplier to
	 * avoid conflict of column mapping
	 * 
	 * @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.PERSIST}) //for
	 * many-to-one mapping a supplier may have many products (for supplier)
	 * alongwith
	 * 
	 * @JoinColumn(name= "supplier_id)" private Supplier supplier;
	 */
	//
	@Id // primary key
	@GeneratedValue(generator = "increment")
	private Integer productNo;
	private String name;
	private Double price;
	private Integer discontinued;
}
