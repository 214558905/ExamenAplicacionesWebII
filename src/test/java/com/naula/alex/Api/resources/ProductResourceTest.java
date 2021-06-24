package com.naula.alex.Api.resources;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.naula.alex.Api.entities.Product;
import com.naula.alex.Api.resources.ProductResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProductResourceTest {
	
	@Rule
    public ExpectedException thrown = ExpectedException.none();
	
	@Autowired
	private RestService restService;
	private Product product;
	
	@Before
    public void before() {
        product = new Product();
        this.product.setBrand("chevroled");
        this.product.setModel("1060");
        this.product.setPrice(50000.80);
    }
	
	@Test
	public void getAllProduct() {
	     String json = restService
	                .restBuilder(new RestBuilder<String>().clazz(String.class))
	                .path(ProductResource.PRODUCT).get().build();
	     System.out.println(json);
	}
	@Test
	public void getById() {
	     String json = restService
	                .restBuilder(new RestBuilder<String>().clazz(String.class))
	                .path(ProductResource.PRODUCT).path(ProductResource.ID).expand("4c141019-7411-40e0-9e13-4cb8fe9e1792").get().build();
	     System.out.println(json);
	}
	
	@Test
    public void createProduct() {
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductResource.PRODUCT).body(this.product).post().build();
        System.out.println(json);

    }
	
	@Test
    public void editProduct() {
        this.product.setBrand("Honda");
        this.product.setPrice(349.99);
        this.product.setModel("Honda1020");
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductResource.PRODUCT).path(ProductResource.ID)
                .expand("4c141019-7411-40e0-9e13-4cb8fe9e1792").body(product).put().build();
        System.out.println(json);
    }

	@Test
    public void deleteProduct() {
        String json = restService
                .restBuilder(new RestBuilder<String>().clazz(String.class))
                .path(ProductResource.PRODUCT).path(ProductResource.ID).expand("dba11dab-21db-4298-b41c-f3e8465b6f48").delete().build();
        System.out.println(json);
    }


}
