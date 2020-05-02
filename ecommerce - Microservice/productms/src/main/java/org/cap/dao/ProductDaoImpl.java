package org.cap.dao;

import org.cap.entities.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductDaoImpl implements IProductDao{
    private Map<String,Product> store=new HashMap<>();

   
    @Override
    public void save(Product product) {
        store.put(product.getProductId(),product);
    }

    @Override
    public List<Product> fetchAllProduct() {
       Collection<Product>values= store.values();
       List<Product>list=new ArrayList<>(values);
       return list;
    }

    @Override
    public Product findProductById(String ProductId) {
       Product product= store.get(ProductId);
       return product;
    }
}
