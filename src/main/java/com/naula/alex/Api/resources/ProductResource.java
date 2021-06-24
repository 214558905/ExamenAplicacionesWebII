package com.naula.alex.Api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import com.naula.alex.Api.controllers.ProductController;
import com.naula.alex.Api.entities.Product;
import com.naula.alex.Api.resources.exceptions.EditProductException;
import com.naula.alex.Api.resources.exceptions.ProductCreateException;

@RestController
@RequestMapping(ProductResource.PRODUCT)
public class ProductResource {
	
	 public static final String PRODUCT = "/product";
	 public static final String ID = "/{id}";
	 public static final String Model = "/{model}";
	 public static final String BRAND = "/{brand}";
	 public static final String PRICE = "/{price}";
	 
	 private ProductController productController;

	 @Autowired
	public ProductResource(ProductController productController) {
		this.productController = productController;
	}
	 
	 @GetMapping
	 public List<Product> getAllProducts(){
		 
		 return this.productController.findAllProducts();
	 }
	 
	 @GetMapping(value = ID)
	 public ResponseEntity<Object> getProductById(@PathVariable String id) {
	        Optional<Product> productOptional = this.productController.findProductById(id);
	        if (productOptional.isPresent()) {
	            return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>("\"El producto no  existe\"", HttpStatus.NOT_FOUND);
	        }

	  }
	 @PostMapping
	  public ResponseEntity<String> createProduct(@RequestBody Product product) throws ProductCreateException {
	        try {
	            this.productController.createProduct(product);
	            return new ResponseEntity<>("\"El producto fue creado\"", HttpStatus.ACCEPTED);
	        } catch (Exception e) {
	            throw new ProductCreateException("los datos enviados no son los correctos");
	        }

	   }

	 @PutMapping(value = ID)
	 public ResponseEntity<String> editProduct(@RequestBody Product product, @PathVariable String id) throws EditProductException {
	        try {
	            if (this.productController.editProductById(id, product))
	                return new ResponseEntity<>("\"El producto fue edito\"", HttpStatus.ACCEPTED);
	            return new ResponseEntity<>("\"El producto no  existe\"", HttpStatus.NOT_FOUND);
	        } catch (Exception e) {
	            throw new EditProductException("los datos enviados no son los correctos");
	        }
	 }
	 @DeleteMapping(value = ID)
	    public ResponseEntity<String> deleteProduct(@PathVariable String id){
	        if (this.productController.deleteProductById(id))
	            return new ResponseEntity<>("\"El producto fue eliminado\"", HttpStatus.ACCEPTED);
	        return new ResponseEntity<>("\"El producto no  existe\"", HttpStatus.NOT_FOUND);
	    }
	 


}
