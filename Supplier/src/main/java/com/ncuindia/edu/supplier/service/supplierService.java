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
import com.ncuindia.edu.supplier.exceptions.DatabaseException;
import com.ncuindia.edu.supplier.exceptions.ResourceNotFoundException;
import com.ncuindia.edu.supplier.exceptions.ValidationException;
import com.ncuindia.edu.supplier.model.Supplier;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class supplierService {

    private final supplierDao supplierDao;
    private final ModelMapper modelMapper;

    @Autowired
    public supplierService(supplierDao supplierDao, ModelMapper modelMapper) {
        this.supplierDao = supplierDao;
        this.modelMapper = modelMapper;
    }

    public List<SupplierDto> getAllSuppliers() {
        try {
            List<Supplier> suppliers = supplierDao.getAllSuppliers();
            List<SupplierDto> dtos = new ArrayList<>();
            for (Supplier s : suppliers) {
                dtos.add(modelMapper.map(s, SupplierDto.class));
            }
            return dtos;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to read suppliers from DB", ex);
        }
    }

    public SupplierDto getSupplierById(int sid) {
        try {
            Supplier supplier = supplierDao.getSupplierById(sid);
            if (supplier == null) {
                throw new ResourceNotFoundException("Supplier not found with id: " + sid);
            }
            return modelMapper.map(supplier, SupplierDto.class);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to fetch supplier with id: " + sid, ex);
        }
    }

    public String addSupplier(SupplierDto supplierDto) {
        if (supplierDto == null) {
            throw new ValidationException("Supplier payload cannot be null");
        }
        if (supplierDto.getName() == null || supplierDto.getName().trim().isEmpty()) {
            throw new ValidationException("Supplier name is required");
        }
        if (supplierDto.getEmail() == null || supplierDto.getEmail().trim().isEmpty()) {
            throw new ValidationException("Supplier email is required");
        }
        // add more validations as needed (email format, contact length, etc.)

        try {
            Supplier supplier = modelMapper.map(supplierDto, Supplier.class);
            return supplierDao.addSupplier(supplier);
        } catch (Exception ex) {
            throw new DatabaseException("Failed to add supplier", ex);
        }
    }

    public String updateProduct(SupplierDto supplierDto, int sid) {
        if (supplierDto == null) {
            throw new ValidationException("Supplier payload cannot be null");
        }
        if (sid <= 0) {
            throw new ValidationException("Invalid supplier id");
        }

        try {
            Supplier existing = supplierDao.getSupplierById(sid);
            if (existing == null) {
                throw new ResourceNotFoundException("Supplier not found with id: " + sid);
            }
            Supplier toUpdate = modelMapper.map(supplierDto, Supplier.class);
            return supplierDao.updateProduct(toUpdate, sid);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to update supplier with id: " + sid, ex);
        }
    }

    public String deleteSupplier(int sid) {
        if (sid <= 0) {
            throw new ValidationException("Invalid supplier id");
        }
        try {
            Supplier existing = supplierDao.getSupplierById(sid);
            if (existing == null) {
                throw new ResourceNotFoundException("Supplier not found with id: " + sid);
            }
            return supplierDao.deleteSupplier(sid);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to delete supplier with id: " + sid, ex);
        }
    }
}
