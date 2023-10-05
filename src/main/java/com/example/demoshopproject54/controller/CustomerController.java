package com.example.demoshopproject54.controller;

import com.example.demoshopproject54.exception.ResourceNotFoundException;
import com.example.demoshopproject54.model.Customer;
import com.example.demoshopproject54.model.Order;
import com.example.demoshopproject54.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("getCustomerById/{id}")
    public ResponseEntity<Customer>readCustomerById(@PathVariable Long id) {
        Customer customerFromDB = customerService.getCustomerById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id +
                " doesn't exist in DB"));

        return new ResponseEntity<>(customerFromDB, HttpStatus.OK);
    }

    @GetMapping("getAllCustomerByName/{name}")
    public ResponseEntity<List<Customer>> readAllCustomerByName(@PathVariable String name) {
        List<Customer> customersListFromDB = customerService.getAllCustomerByName(name);

        if (customersListFromDB.isEmpty()) {
            throw new ResourceNotFoundException("No customer with name " + name + " not found!");
        }

        return new ResponseEntity<>(customersListFromDB, HttpStatus.OK);
    }

    @PostMapping("/addNewCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.saveCustomer(customer);

        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateOrder(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);

        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<?> removeCustomerByID(@PathVariable Long id) {
        Customer customerFromDB = customerService.getCustomerById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id
                + " doesn't exist in DB"));

        customerService.deleteCustomerById(id);
        return new ResponseEntity<>("Customer with id " + id + " Was deleted!", HttpStatus.OK);
    }

}
