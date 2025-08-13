package com.ncuindia.edu.product.model;

public class Product {
    private int pid;
    private String name;
    private double price;
    private int stock;


    public Product(){

    }
    
    public Product(int pid, String name, double price, int stock, int supplier_id) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


    public int getPid() {
        return pid;
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

    
    public void setPid(int pid) {
        this.pid = pid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append('}');
        return sb.toString();
    }

}
