package com.ncuindia.edu.product.service;


import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.ncuindia.edu.product.dao.productDao;
import com.ncuindia.edu.product.dto.ProductDto;
import com.ncuindia.edu.product.dto.SupplierDto;
import com.ncuindia.edu.product.exceptions.DatabaseException;
import com.ncuindia.edu.product.exceptions.ResourceNotFoundException;
import com.ncuindia.edu.product.exceptions.ValidationException;
import com.ncuindia.edu.product.model.Product;

@Service
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class productService {

    private final productDao productDao;
    private final ModelMapper modelMapper;
    private final RestClient restClient;
    final String _SupplierCred;
    final String _SupplierAuth;
    @Value("${apigateway.shared.secret}")
    String _SharedSecret;

    @Autowired
    public productService(productDao productDao,
                          ModelMapper modelMapper,
                          RestClient.Builder restClientBuilder) {
        this.productDao = productDao;
        this.modelMapper = modelMapper;
        this.restClient = restClientBuilder
                .baseUrl("http://localhost:8002/supplier")
                .build();
        
        _SupplierCred = "supplierservice:supplierservice";
        _SupplierAuth = "Basic " + Base64.getEncoder().encodeToString(_SupplierCred.getBytes(StandardCharsets.UTF_8));

    }

    public List<ProductDto> getAllProducts() {
        try {
            List<Product> products = productDao.getAllProducts();
            List<ProductDto> productDtos = new ArrayList<>();

            for (Product product : products) {
                ProductDto dto = modelMapper.map(product, ProductDto.class);

                try {
                    SupplierDto supplier = restClient.get()
                            .uri("/{sid}", product.getSid())
                            .header(HttpHeaders.AUTHORIZATION, _SupplierAuth)
                            .header("X-API-GATEWAY-SECRET", _SharedSecret)
                            .retrieve()
                            .body(SupplierDto.class);

                    if (supplier != null) {
                        dto.setSupplierName(supplier.getName());
                    } else {
                        dto.setSupplierName("Unknown Supplier");
                    }
                } catch (Exception ex) {
                    dto.setSupplierName("Unknown Supplier");
                }

                productDtos.add(dto);
            }

            return productDtos;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to read products from DB", ex);
        }
    }

    public ProductDto getProductById(int pid) {
        try {
            Product product = productDao.getProductById(pid);

            if (product == null) {
                throw new ResourceNotFoundException("Product not found with id: " + pid);
            }

            ProductDto dto = modelMapper.map(product, ProductDto.class);

            try {
                SupplierDto supplier = restClient.get()
                        .uri("/{sid}", product.getSid())
                        .header(HttpHeaders.AUTHORIZATION, _SupplierAuth)
                        .header("X-API-GATEWAY-SECRET", _SharedSecret)
                        .retrieve()
                        .body(SupplierDto.class);
                
                if (supplier != null) {
                    dto.setSupplierName(supplier.getName());
                    System.out.println("Supplier is: "+supplier.getName());
                } else {
                    System.out.println("Supplier is null");
                    dto.setSupplierName("Unknown Supplier");
                }
            } catch (Exception ex) {
                System.out.println("Exception is: "+ex.getMessage());
                dto.setSupplierName("Unknown Supplier");
            }

            return dto;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to fetch product with id: " + pid, ex);
        }
    }

    public String addProduct(ProductDto productDto) {
        if (productDto == null) {
            throw new ValidationException("Product payload cannot be null");
        }
        if (productDto.getname() == null || productDto.getname().trim().isEmpty()) {
            throw new ValidationException("Product name is required");
        }
        if (productDto.getprice() < 0) {
            throw new ValidationException("Product price must be non-negative");
        }
        if (productDto.getstock() < 0) {
            throw new ValidationException("Product stock must be non-negative");
        }

        try {
            Product product = modelMapper.map(productDto, Product.class);
            String result = productDao.addProduct(product);
            return result;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to add product", ex);
        }
    }

    public String updateProduct(ProductDto productDto, int pid) {
        if (productDto == null) {
            throw new ValidationException("Product payload cannot be null");
        }
        if (productDto.getname() == null || productDto.getname().trim().isEmpty()) {
            throw new ValidationException("Product name is required");
        }
        if (pid <= 0) {
            throw new ValidationException("Invalid product id");
        }

        try {
            Product existing = productDao.getProductById(pid);
            if (existing == null) {
                throw new ResourceNotFoundException("Product not found with id: " + pid);
            }

            Product toUpdate = modelMapper.map(productDto, Product.class);
            String result = productDao.updateProduct(toUpdate, pid);
            return result;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to update product with id: " + pid, ex);
        }
    }

    public String deleteProduct(int pid) {
        if (pid <= 0) {
            throw new ValidationException("Invalid product id");
        }

        try {
            Product existing = productDao.getProductById(pid);
            if (existing == null) {
                throw new ResourceNotFoundException("Product not found with id: " + pid);
            }
            String result = productDao.deleteProduct(pid);
            return result;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception ex) {
            throw new DatabaseException("Failed to delete product with id: " + pid, ex);
        }
    }
}

