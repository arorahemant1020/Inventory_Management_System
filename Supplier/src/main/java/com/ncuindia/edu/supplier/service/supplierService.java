package com.ncuindia.edu.supplier.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ncuindia.edu.supplier.dao.supplierDao;
import com.ncuindia.edu.supplier.model.Supplier;


@Service
public class supplierService {

    private final supplierDao supplierDao;

    public supplierService(supplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
    
    public List<Supplier> getAllSuppliers(){
        return supplierDao.getAllSuppliers();
    }
    public Supplier getSupplierById(int sid){
        return supplierDao.getSupplierById(sid);
    }
    public String addSupplier(Supplier supplier){
        return supplierDao.addSupplier(supplier);
    }
    public String updateProduct(Supplier supplier, int sid){
        return supplierDao.updateProduct(supplier, sid);
    }
    public String deleteSupplier(int sid){
        return supplierDao.deleteSupplier(sid);
    } 
}
