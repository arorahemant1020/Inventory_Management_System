package com.ncuindia.edu.product.dao;

import java.util.List;

import com.ncuindia.edu.product.model.Product;

public interface productDao {

    public List<Product> getAllProducts();
    public Product getProductById(int pid);
    public String addProduct(Product product);
    public String updateProduct(Product product, int pid);
    public String deleteProduct(int pid); 
}
