package com.example.demoshopproject54.service;

import com.example.demoshopproject54.model.Customer;
import com.example.demoshopproject54.model.Order;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    //Read all customer from database -> get HTTP ENDPOINT
    List<Customer> getAllCustomer();

    //Read data for a customer by id -> GET (by id)
    Optional<Customer> getCustomerById(Long id);

    //Create new customer and save it to database;
    Customer saveCustomer(Customer customer);

    //Update a customer by id
    Customer updateCustomer(Customer customer);

    //Delete a customer by id
    void deleteCustomerById(Long id);

    //Custom CRUD method logic
    List<Customer> getAllCustomerByName(String name);

}
