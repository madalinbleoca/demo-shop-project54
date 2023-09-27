package com.example.demoshopproject54.controller;

import com.example.demoshopproject54.exception.ResourceNotFoundException;
import com.example.demoshopproject54.model.Product;
import com.example.demoshopproject54.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controller vs RestController -> learn
@RequiredArgsConstructor
@RequestMapping("/api/products") // base URL
public class ProductController {

    // Implement HTTP REST APIs: GET, POST, PUT, DELETE.

    // DI using @RequiredArgsConstructor
    private final ProductService productService;

    // GET endpoint
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/getProductById/{id}")
    public ResponseEntity<Product> readProductById(@PathVariable Long id) {
        Product productFromDB = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id" + id
                + " doesn't exist in DB"));

        return new ResponseEntity<>(productFromDB, HttpStatus.OK);

    }

    @GetMapping("/getAllProductByName/{name}")
    public ResponseEntity<List<Product>> readAllProductsByName(@PathVariable String name) {
        List<Product> productListFromDB = productService.getAllProductsByName(name);

        if (productListFromDB.isEmpty()) {
            throw new ResourceNotFoundException("No products with name " + name + " not found!");
        }

        return new ResponseEntity<>(productListFromDB, HttpStatus.OK);
    }

    @GetMapping("/getAllProductByNameAndPrice/{name}/{price}")
    public ResponseEntity<List<Product>> readAllProductsByNameAndPrice(@PathVariable String name, @PathVariable double price) {
        List<Product> productListFromDB = productService.getAllProductsByNameAndPrice(name, price);

        if (productListFromDB.isEmpty()) {
            throw new ResourceNotFoundException("No products with name " + name + " and " + price + " not found!");
        }

        return new ResponseEntity<>(productListFromDB, HttpStatus.OK);
    }

    @PostMapping("/addNewProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {

        Product newProduct = productService.saveProduct(product);

        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updateProduct = productService.updateProduct(product);

        return new ResponseEntity<>(updateProduct, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProductById/{id}")
    public ResponseEntity<?> removeProductById(@PathVariable Long id) {

        Product productFromDB = productService.getProductById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id"
                + id + " doesn't exist in DB"));

        productService.deleteProductById(id);
        return new ResponseEntity<>("Product with id " + id + " deleted", HttpStatus.OK);
    }
}
