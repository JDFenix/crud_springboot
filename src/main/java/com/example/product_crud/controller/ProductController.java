package com.example.product_crud.controller;

import com.example.product_crud.model.Product;
import org.springframework.ui.Model;
import com.example.product_crud.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "update";
    }


    @GetMapping("/createForm")
    public String create() {
        return "create";
    }





    @GetMapping("/index")
    public String index(Model model) {
        List<Product> productList = null;
        try {
            productList = productService.getAllProducts();
            System.out.println("Product List: " + productList);
            model.addAttribute("products", productList);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            model.addAttribute("errorMessage", "Error al obtener la lista de productos: " + e.getMessage());
        }
        if (productList == null || productList.isEmpty()) {
            System.out.println("La lista de productos está vacía");
        }
        return "index";
    }


    @PostMapping("/updateProduct")
    public String updateProduct(@RequestParam Long id,@RequestParam String name, @RequestParam String category, @RequestParam int stock, @RequestParam Float price, Model model){

        try {
            productService.updateProduct(id,name,category,stock,price);
            model.addAttribute("messageSuccesful", "Se actualizo correctamente");

        }catch (Exception e){
            model.addAttribute("messageError","No se actualizo" + e.getMessage());
        }
        return "redirect:/product/index";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/product/index";
    }



    @PostMapping("/create")
    public String createProduct(@RequestParam String name, @RequestParam String category, @RequestParam int stock, @RequestParam Float price, Model model) {
        try {
            productService.createProduct(name, category, stock, price);
            model.addAttribute("sussesful", "se registró correctamente");
            return "redirect:/product/index";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            List<Product> productList = productService.getAllProducts();
            model.addAttribute("products", productList);
            return "redirect:/product/index";
        }
    }

}

