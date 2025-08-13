package com.ncuindia.edu.IMS.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ncuindia.edu.IMS.dao.productDao;
import com.ncuindia.edu.IMS.model.Product;

@Service
public class productService {

    private final productDao productDao;

    public productService(productDao productDao) {
        this.productDao = productDao;
    }
    
    public List<Product> getAllProducts(){
        return productDao.getAllProducts();
    }
    public Product getProductById(int pid){
        return productDao.getProductById(pid);
    }
    public String addProduct(Product product){
        return productDao.addProduct(product);
    }
    public String updateProduct(Product product, int pid){
        return productDao.updateProduct(product, pid);
    }
    public String deleteProduct(int pid){
        return productDao.deleteProduct(pid);
    }
    public List<Product> getProductBySupplier(int supplier_id){
        return productDao.getProductBySupplier(supplier_id);
    }
}
