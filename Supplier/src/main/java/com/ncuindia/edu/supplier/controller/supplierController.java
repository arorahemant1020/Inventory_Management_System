package com.ncuindia.edu.supplier.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncuindia.edu.supplier.dto.SupplierDto;
import com.ncuindia.edu.supplier.service.supplierService;

@RestController
@RequestMapping("/supplier")
public class supplierController {

    private final supplierService supplierService;
    
     public supplierController(supplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    @GetMapping("/")
    public List<SupplierDto> getAllSuppliers(){
        return supplierService.getAllSuppliers();

    }

    @GetMapping("/{sid}")
    public SupplierDto getSupplierById(@PathVariable int sid){
        return supplierService.getSupplierById(sid);
    }

    @PostMapping("/add")
    public String addSupplier(@RequestBody SupplierDto supplierdto){
        return supplierService.addSupplier(supplierdto);
    }

    @PutMapping("/update/{sid}")
    public String updateSupplier(@RequestBody SupplierDto supplierdto, @PathVariable int sid){
        return supplierService.updateProduct(supplierdto, sid);
    }

    @DeleteMapping("/remove/{sid}")
    public String deleteSupplier(@PathVariable int sid){
        return supplierService.deleteSupplier(sid);
    }

}
