package com.ncuindia.edu.product.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ncuindia.edu.product.dao.productDao;
import com.ncuindia.edu.product.dto.ProductDto;
import com.ncuindia.edu.product.dto.SupplierDto;
import com.ncuindia.edu.product.model.Product;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class productService {

    productDao productDao;
    ModelMapper modelMapper;
    RestClient restClient;

    @Autowired
    public productService(productDao productDao, ModelMapper modelMapper, RestClient.Builder restClientBuilder) {
        this.productDao = productDao;
        this.modelMapper = modelMapper;
         this.restClient = restClientBuilder
                .baseUrl("http://SUPPLIER/supplier") 
                .build();
    }
    
    public List<ProductDto> getAllProducts()
    {
        List<Product> products = productDao.getAllProducts();
        List<ProductDto> productdtos = new ArrayList<>();

        for (Product product : products){
            ProductDto dto = (modelMapper.map(product, ProductDto.class));
        
        try {
                SupplierDto supplier = restClient.get()
                        .uri("/{sid}", product.getSid())
                        .header("Authorization", "Basic aGVtYW50QGdtYWlsLmNvbTpoZW1hbnQ=")
                        .retrieve()
                        .body(SupplierDto.class);

                if (supplier != null) {
                    dto.setSupplierName(supplier.getName()); 
                }
            } catch (Exception e) {
                dto.setSupplierName("Unknown Supplier"); 
            }

            productdtos.add(dto);
        }
        return productdtos;
        
    }

    public ProductDto getProductById(int pid){
        Product product = productDao.getProductById(pid);
        ProductDto dto = modelMapper.map(product, ProductDto.class);
        try {
            SupplierDto supplier = restClient.get()
                    .uri("/{sid}", product.getSid())
                    .retrieve()
                    .body(SupplierDto.class);

            if (supplier != null) {
                dto.setSupplierName(supplier.getName());
            }
        } catch (Exception e) {
            dto.setSupplierName("Unknown Supplier");
        }

        return dto;
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
