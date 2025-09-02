package com.ncuindia.edu.supplier.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncuindia.edu.supplier.dao.supplierDao;
import com.ncuindia.edu.supplier.dto.SupplierDto;
import com.ncuindia.edu.supplier.model.Supplier;


@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class supplierService {

    supplierDao supplierDao;
    ModelMapper modelMapper;

    @Autowired
    public supplierService(supplierDao supplierDao, ModelMapper modelMapper) {
        this.supplierDao = supplierDao;
        this.modelMapper = modelMapper;
    }
    
    public List<SupplierDto> getAllSuppliers(){
        List<Supplier> suppliers = supplierDao.getAllSuppliers();
        List<SupplierDto> supplierdtos = new ArrayList<>();

        for (Supplier supplier : suppliers){
            supplierdtos.add(modelMapper.map(supplier, SupplierDto.class));
        }
        return supplierdtos;
    }


    public SupplierDto getSupplierById(int sid){
        Supplier supplier = supplierDao.getSupplierById(sid);
        return modelMapper.map(supplier, SupplierDto.class);
    }

    public String addSupplier(SupplierDto supplierdto){
        Supplier supplier = modelMapper.map(supplierdto, Supplier.class);
        return supplierDao.addSupplier(supplier);
    }

    public String updateProduct(SupplierDto supplierdto, int sid){
        Supplier supplier = modelMapper.map(supplierdto, Supplier.class);
        return supplierDao.updateProduct(supplier, sid);
    }

    public String deleteSupplier(int sid){
        return supplierDao.deleteSupplier(sid);
    } 
}
