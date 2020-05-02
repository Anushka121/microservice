package org.cap.service;

import org.cap.entities.Product;

import java.util.List;

public interface IProductService {
	void save(Product product);

    List<Product>fetchAllProduct();

    Product findProductById(String productId);
}
