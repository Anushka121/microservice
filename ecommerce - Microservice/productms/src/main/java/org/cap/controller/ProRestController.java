package org.cap.controller;

import org.cap.entities.Product;
import org.cap.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  java.util.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/products")
public class ProRestController {

    @Autowired
    private IProductService productService;
    
    @PostConstruct
    public void addProducts() {
    	Product product1 = new Product();
    	product1.setProductId("p1");
    	product1.setProductName("Tv");
    	product1.setProductPrice(60000);
    	product1.setProductCount(20);
    	productService.save(product1);
    	
    	Product product2 = new Product();
    	product2.setProductId("p2");
    	product2.setProductName("AC");
    	product2.setProductPrice(350000);
    	product2.setProductCount(30);
    	
    }
    

    @GetMapping
    public ResponseEntity<List<Product>>fetchAll(){
      List<Product>products =productService.fetchAllProduct();
      ResponseEntity<List<Product>>response=new ResponseEntity<>(products, HttpStatus.OK);
      return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product>findProductById(@PathVariable("id") String productId){
       Product product= productService.findProductById(productId);
       ResponseEntity<Product>response=new ResponseEntity<>(product,HttpStatus.OK);
       return response;
    }



}
