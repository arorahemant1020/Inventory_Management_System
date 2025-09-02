package com.ncuindia.edu.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncuindia.edu.product.dto.ProductDto;
import com.ncuindia.edu.product.service.productService;

@RestController
@RequestMapping("/product")
public class productController {

    private final productService productService;

    public productController(productService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();

    }

    @GetMapping("/{pid}")
    public ProductDto getProductById(@PathVariable int pid){
        return productService.getProductById(pid);
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductDto productdto){
        return productService.addProduct(productdto);
    }

    @PutMapping("/update/{pid}")
    public String updateProduct(@RequestBody ProductDto productdto, @PathVariable int pid){
        return productService.updateProduct(productdto, pid);
    }

    @DeleteMapping("/remove/{pid}")
    public String deleteProduct(@PathVariable int pid){
        return productService.deleteProduct(pid);
    }
}
