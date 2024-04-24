package com.example.product_crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private int stock;
    @Column
    private float price;


    public Product(String name, String category, int stock, float price) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.price = price;
    }


    public Product() {

    }
}
