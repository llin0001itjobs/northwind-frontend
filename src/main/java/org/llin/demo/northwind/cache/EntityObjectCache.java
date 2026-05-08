package org.llin.demo.northwind.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.model.Product;
import org.llin.demo.northwind.model.Supplier;
import org.llin.demo.northwind.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EntityObjectCache<T extends EntityObject> implements _Classes_EntityObject {

	private static final Logger logger = LoggerFactory.getLogger(EntityObjectCache.class);
		
	private Map<String, T[]> cacheObjectArray = new HashMap<>();
		
	/**
	 * Add to cache with key
	 * @param key
	 * @param baseObjects
	 */
	public void add(String key, T[] baseObjects) {
		cacheObjectArray.put(key, baseObjects);
	}

	/*
	 * Get comma seperated values of id, from the link of product
	 *   "product" : {
          "href" : "http://localhost:8080/northwind-data/api/supplier/1/product?id=19,21,52,56,57,82,85,86,97"
          },
       Convert iterate thru ids and match with product id in cache   
          
	 */
	public void distributeProductsPerSupplier() {
		T[] products = cacheObjectArray.get(PRODUCT);
		T[] suppliers = cacheObjectArray.get(SUPPLIER);
		for (T bos : suppliers) {
			Supplier s = (Supplier)bos;
			String csvId = s.getLinks().getLink(PRODUCT.toLowerCase()).getId();
			String[] ids = StringUtil.csvToArray(csvId);
			List<Product> productsPerSupplier = new ArrayList<>();
			for (String id : ids) {
				for (T bop : products) {
					if (id.equals(Integer.valueOf(bop.getId()).toString())) {
						productsPerSupplier.add((Product)bop);
					}
				}
			}
			Product[] array = {};
			s.getProducts().setProducts(productsPerSupplier.toArray(array));
		}
	}

	/*
	 * Get comma seperated values of id, from the link of supplier
	 *   "supplier" : {
          "href" : "http://localhost:8080/northwind-data/api/product/6/supplier?id=2,6"
          },
       Convert iterate thru ids and match with product id in cache   
	 */
	public void distributeSuppliersPerProduct() {
		T[] suppliers = cacheObjectArray.get(SUPPLIER);
		T[] products = cacheObjectArray.get(PRODUCT);
		
		for (T b : products) {
			Product p = (Product)b;
			String csvId = p.getLinks().getLink(SUPPLIER.toLowerCase()).getId();
			String[] ids = StringUtil.csvToArray(csvId);
			List<Supplier> suppliersPerProduct = new ArrayList<>();
			for (String id : ids) {
				for (EntityObject bop : suppliers) {
					if (id.equals(Integer.valueOf(bop.getId()).toString())) {
						suppliersPerProduct.add((Supplier)bop);
					}
				}
			}			
			Supplier[] array = {};
			p.getSuppliers().setSuppliers(suppliersPerProduct.toArray(array));		
		}		

	}
	
	/**
	 * Gets list of T based on key
	 * @param key
	 * @return
	 */
	public T[] getObjectArray(String key) {
		return (T[]) cacheObjectArray.get(key);
	}
	
	public T getById(String key, int id) {
		T baseObject = null;
		try {
			T[] baseObjects = getObjectArray(key);
			for (T bo : baseObjects) {
				if (bo.getId() == id) {
					baseObject = bo;
					break;
				}
			}	
		} catch (NumberFormatException e) {
			
		}
		
		return baseObject;
	}
	
	/**
	 * Returns list of class name of type T in alphabetical order 
	 * @return
	 */
	public List<String> getObjectArrayKeys() {
		return new ArrayList<String>(new TreeSet<String>(cacheObjectArray.keySet()));
	}
		
	/**
	 * Only prints out when logger set to trace
	 * Will printOut with class name in alphabetical order
	 */
	public void printOutObjectArray() {
		T[] array;
		List<String> list = getObjectArrayKeys();
				
		for (String key : list) {		
			array = cacheObjectArray.get(key);
			logger.trace("{{" + key + "}}");
			System.out.println("{{" + key + "}}");
			for (EntityObject bo : array) {
				logger.trace("\t" + bo.toString());
				System.out.println("\t" + bo.toString());
			}
		}
	}

}
