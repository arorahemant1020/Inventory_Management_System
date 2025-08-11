package com.ncuindia.edu.IMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ncuindia.edu.IMS.model.Supplier;
import com.ncuindia.edu.IMS.service.supplierService;

@RestController
@RequestMapping("/supplier")
public class supplierController {

    private final supplierService supplierService;
    
     public supplierController(supplierService supplierService) {
        this.supplierService = supplierService;
    }
    
    @GetMapping("/")
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();

    }

    @GetMapping("/{sid}")
    public Supplier getSupplierById(@PathVariable int sid){
        return supplierService.getSupplierById(sid);
    }

    @PostMapping("/add")
    public String addSupplier(@RequestBody Supplier supplier){
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/update/{sid}")
    public String updateSupplier(@RequestBody Supplier supplier, @PathVariable int sid){
        return supplierService.updateProduct(supplier, sid);
    }

    @DeleteMapping("/remove/{sid}")
    public String deleteSupplier(@PathVariable int sid){
        return supplierService.deleteSupplier(sid);
    }

}
