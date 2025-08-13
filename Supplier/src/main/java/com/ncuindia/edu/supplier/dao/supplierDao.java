package com.ncuindia.edu.supplier.dao;

import java.util.List;

import com.ncuindia.edu.supplier.model.Supplier;

public interface supplierDao {
    
    public List<Supplier> getAllSuppliers();
    public Supplier getSupplierById(int sid);
    public String addSupplier(Supplier supplier);
    public String updateProduct(Supplier supplier, int sid);
    public String deleteSupplier(int sid); 

}
