package com.naula.alex.Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naula.alex.Api.entities.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

}
