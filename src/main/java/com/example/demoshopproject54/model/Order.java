package com.example.demoshopproject54.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "order_number", nullable = false)
    private int orderNumber;

    @Column(nullable = false)
    private  String status;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;
}
