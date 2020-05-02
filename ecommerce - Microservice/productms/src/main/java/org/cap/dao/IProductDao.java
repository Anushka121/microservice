package org.cap.dao;

import org.cap.entities.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IProductDao {

	void save(Product product);

    List<Product>fetchAllProduct();

    Product findProductById(String productId);

}
