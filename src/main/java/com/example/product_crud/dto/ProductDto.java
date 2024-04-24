package com.example.product_crud.dto;
import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String category;
    private int stock;
    private float price;
}
