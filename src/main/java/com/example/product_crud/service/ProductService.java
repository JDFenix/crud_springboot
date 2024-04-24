package com.example.product_crud.service;

import com.example.product_crud.dto.ProductDto;
import com.example.product_crud.model.Product;
import com.example.product_crud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void updateProduct(Long id, String name, String category, int stock, float price) {
        Product product = getProductById(id);

        if (product != null) {
            product.setName(name);
            product.setCategory(category);
            product.setStock(stock);
            product.setPrice(price);
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public void createProduct(String name, String category, int stock, float price) {
        ModelAndView modelAndView = new ModelAndView();
        try {

            ProductDto productDto = new ProductDto();
            productDto.setName(name);
            productDto.setCategory(category);
            productDto.setPrice(price);
            productDto.setStock(stock);

            Product product = convertToEntity(productDto);
            productRepository.save(product);


        } catch (Exception e) {

            modelAndView.addObject("errorMessage", e.getMessage());
            modelAndView.setViewName("index");
        }
    }

    private Product convertToEntity(ProductDto productDto) {
        return new Product(
                productDto.getName(),
                productDto.getCategory(),
                productDto.getStock(),
                productDto.getPrice()
        );
    }

}
