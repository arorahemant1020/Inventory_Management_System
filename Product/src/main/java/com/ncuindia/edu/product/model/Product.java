package com.ncuindia.edu.product.model;

public class Product {
    private Integer pid;
    private String name;
    private double price;
    private int stock;
    private int sid;


    public Product(){

    }
    
    public Product(int pid, String name, double price, int stock, int sid) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.sid = sid;
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
    public int getSid() {
        return sid;
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
    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Product [pid=" + pid + ", name=" + name + ", price=" + price + ", stock=" + stock + ", sid=" + sid
                + "]";
    }    



}
