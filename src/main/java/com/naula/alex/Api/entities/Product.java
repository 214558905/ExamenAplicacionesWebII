package com.naula.alex.Api.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product")
public class Product {
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
	
	private String brand, model;
	private double price;
	
	public Product() {
		
	}

	public Product(String id, String brand, String model, double price) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.price = price;
	}
	
   @Override
    public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    Product product = (Product) obj;
	    return Objects.equals(id, product.getId())
	    && Objects.equals(brand, product.getBrand())
	    && Objects.equals(model, product.getModel())
	    && Objects.equals(price, product.getPrice());
	    }

	@Override
	public int hashCode() {
	   return Objects.hash(id, brand, model, price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String modelo) {
		this.model = modelo;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
	

}
