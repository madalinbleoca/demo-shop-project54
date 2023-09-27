package com.example.demoshopproject54.service;

import com.example.demoshopproject54.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    //Read all products from database -> GET HTTP ENDPOINT
    List<Product> getAllProducts();

    //Read data for a product by id -> GET (by id) HTTP ENDPOINT
    Optional<Product> getProductById(Long id);

    //Create new product and save it to database -> POST HTTP ENDPOINT
    Product saveProduct(Product product);

    //Update a product by id -> PUT HTTP ENDPOINT
    Product updateProduct(Product product);

    //Delete a product by id -> DELETE HTTP ENDPOINT
    void deleteProductById(Long id);

    //Custom  CRUD methods logic
    List<Product> getAllProductsByName(String name);
    List<Product> getAllProductsByNameAndPrice(String name, double price);
}
