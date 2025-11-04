package com.ncuindia.edu.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private double price;
    
    @JsonProperty("stock")
    private int stock;

    @JsonProperty("supplier_id")
    private int sid;
    
    @JsonProperty("supplierName")
    private String supplierName;

    public ProductDto() {
    }

    public ProductDto(String name, double price, int stock, int sid, String supplierName) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sid = sid;
        this.supplierName = supplierName;
    }

    

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }


}
