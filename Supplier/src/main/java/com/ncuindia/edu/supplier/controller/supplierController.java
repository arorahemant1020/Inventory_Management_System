package com.ncuindia.edu.supplier.controller;

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

import com.ncuindia.edu.supplier.dto.ErrorResponse;
import com.ncuindia.edu.supplier.dto.SupplierDto;
import com.ncuindia.edu.supplier.exceptions.DatabaseException;
import com.ncuindia.edu.supplier.exceptions.ResourceNotFoundException;
import com.ncuindia.edu.supplier.exceptions.ValidationException;
import com.ncuindia.edu.supplier.service.supplierService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/supplier")
public class supplierController {

    private final supplierService supplierService;

    @Autowired
    public supplierController(supplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllSuppliers(HttpServletRequest request) {
        try {
            List<SupplierDto> dtos = supplierService.getAllSuppliers();
            return ResponseEntity.ok(dtos);
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

    @GetMapping("/{sid}")
    public ResponseEntity<?> getSupplierById(@PathVariable int sid, HttpServletRequest request) {
        try {
            SupplierDto dto = supplierService.getSupplierById(sid);
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
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDto supplierDto, HttpServletRequest request) {
        try {
            String result = supplierService.addSupplier(supplierDto);
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

    @PutMapping("/update/{sid}")
    public ResponseEntity<?> updateSupplier(@RequestBody SupplierDto supplierDto, @PathVariable int sid, HttpServletRequest request) {
        try {
            String result = supplierService.updateProduct(supplierDto, sid);
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

    @DeleteMapping("/remove/{sid}")
    public ResponseEntity<?> deleteSupplier(@PathVariable int sid, HttpServletRequest request) {
        try {
            String result = supplierService.deleteSupplier(sid);
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