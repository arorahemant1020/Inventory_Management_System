package com.ncuindia.edu.IMS.dao;

import java.util.List;

import com.ncuindia.edu.IMS.model.Supplier;

public interface supplierDao {
    
    public List<Supplier> getAllSuppliers();
    public Supplier getSupplierById(int sid);
    public String addSupplier(Supplier supplier);
    public String updateProduct(Supplier supplier, int sid);
    public String deleteSupplier(int sid); 

}
