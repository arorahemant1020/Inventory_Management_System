package com.ncuindia.edu.product.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ncuindia.edu.product.dao.productDao;
import com.ncuindia.edu.product.dto.ProductDto;
import com.ncuindia.edu.product.model.Product;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class productService {

    productDao productDao;
    ModelMapper modelMapper;

    @Autowired
    public productService(productDao productDao, ModelMapper modelMapper) {
        this.productDao = productDao;
        this.modelMapper = modelMapper;
    }
    
    public List<ProductDto> getAllProducts()
    {
        List<Product> products = productDao.getAllProducts();
        List<ProductDto> productdtos = new ArrayList<>();

        for (Product product : products){
            productdtos.add(modelMapper.map(product, ProductDto.class));
        }
        
        return productdtos;
    }

    public ProductDto getProductById(int pid){
        Product product = productDao.getProductById(pid);
        return modelMapper.map(product, ProductDto.class);
    }

    public String addProduct(ProductDto productdto){
        Product product = modelMapper.map(productdto, Product.class);
        return productDao.addProduct(product);

    }

    public String updateProduct(ProductDto productdto, int pid){
        Product product = modelMapper.map(productdto, Product.class);
        return productDao.updateProduct(product, pid);
    }

    public String deleteProduct(int pid){
        return productDao.deleteProduct(pid);
    }
}
