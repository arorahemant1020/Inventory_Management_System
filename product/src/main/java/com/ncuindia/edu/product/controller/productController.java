package com.ncuindia.edu.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncuindia.edu.product.dto.ErrorResponse;
import com.ncuindia.edu.product.dto.ProductDto;
import com.ncuindia.edu.product.exceptions.DatabaseException;
import com.ncuindia.edu.product.exceptions.ResourceNotFoundException;
import com.ncuindia.edu.product.exceptions.ValidationException;
import com.ncuindia.edu.product.service.productService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/product")
public class productController {

    private final productService productService;

    @Autowired
    public productController(productService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProducts(HttpServletRequest request) {
        try {
            List<ProductDto> products = productService.getAllProducts();
            return ResponseEntity.ok(products); // 200 with product list
        } catch (DatabaseException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                    "Database Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
        } catch (Exception ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @GetMapping("/{pid}")
    public ResponseEntity<?> getProductById(@PathVariable int pid, HttpServletRequest request) {
        try {
            ProductDto dto = productService.getProductById(pid);
            return ResponseEntity.ok(dto);
        } catch (ResourceNotFoundException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        } catch (DatabaseException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                    "Database Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
        } catch (Exception ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto, HttpServletRequest request) {
        try {
            String result = productService.addProduct(productDto);
            // you can return a DTO with status if you prefer; returning plain message is OK per your original design
            return ResponseEntity.ok(result);
        } catch (ValidationException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                    "Validation Failed",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.badRequest().body(err);
        } catch (DatabaseException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                    "Database Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
        } catch (Exception ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @PutMapping("/update/{pid}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @PathVariable int pid, HttpServletRequest request) {
        try {
            String result = productService.updateProduct(productDto, pid);
            return ResponseEntity.ok(result);
        } catch (ValidationException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                    "Validation Failed",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.badRequest().body(err);
        } catch (ResourceNotFoundException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        } catch (DatabaseException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                    "Database Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
        } catch (Exception ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }

    @DeleteMapping("/remove/{pid}")
    public ResponseEntity<?> deleteProduct(@PathVariable int pid, HttpServletRequest request) {
        try {
            String result = productService.deleteProduct(pid);
            return ResponseEntity.ok(result);
        } catch (ValidationException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                    "Validation Failed",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.badRequest().body(err);
        } catch (ResourceNotFoundException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                    "Not Found",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
        } catch (DatabaseException ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.SERVICE_UNAVAILABLE.value(),
                    "Database Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(err);
        } catch (Exception ex) {
            ErrorResponse err = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Internal Server Error",
                    ex.getMessage(),
                    request.getRequestURI());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
        }
    }
}
