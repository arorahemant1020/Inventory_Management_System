package com.ncuindia.edu.product.dto;

public class ProductDto {

    private String name;
    private double price;
    private int stock;

    public ProductDto() {
    }

    public ProductDto(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    

    public String getname() {
        return name;
    }

    public double getprice() {
        return price;
    }

    public int getstock() {
        return stock;
    }

    public void setname(String name) {
        this.name = name;
    }

    public void setprice(double price) {
        this.price = price;
    }

    public void setstock(int stock) {
        this.stock = stock;
    }



}
