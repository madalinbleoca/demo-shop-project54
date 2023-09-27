package com.example.demoshopproject54.service.impl;

import com.example.demoshopproject54.model.Product;
import com.example.demoshopproject54.repository.ProductRepository;
import com.example.demoshopproject54.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();

    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return productRepository.findAllProductByName(name);
    }

    @Override
    public List<Product> getAllProductsByNameAndPrice(String name, double price) {
        return productRepository.findAllProductByNameAndPrice(name, price);
    }
}
