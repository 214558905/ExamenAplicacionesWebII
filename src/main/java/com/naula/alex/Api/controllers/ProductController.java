package com.naula.alex.Api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.naula.alex.Api.entities.Product;
import com.naula.alex.Api.repositories.ProductRepository;

@Controller
public class ProductController {
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductController(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}
	
	public List<Product> findAllProducts(){
		
		return this.productRepository.findAll();
	}
	
	public Optional<Product> findProductById(String id) {
        return this.productRepository.findById(id);
    }
	
	public void createProduct(Product product) {
	     this.productRepository.save(product);
    }

	public boolean editProductById(String id, Product product) {
	        Optional<Product> productOptional = this.findProductById(id);
	        if (!productOptional.isPresent()) return false;
	        Product productdb = productOptional.get();
	        productdb.setBrand(product.getBrand());
	        productdb.setModel(product.getModel());
	        productdb.setPrice(product.getPrice());
	        productRepository.save(productdb);
	        return true;
	 }
	
	public boolean deleteProductById(String id) {
        Optional<Product> productOptional = this.findProductById(id);
        if (!productOptional.isPresent()) return false;
        productRepository.deleteById(id);
        return true;
    }

}
