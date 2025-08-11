package com.ncuindia.edu.IMS.model;

public class Product {
    private int pid;
    private String name;
    private double price;
    private int stock;
    private int supplier_id;


    public Product(){

    }
    
    public Product(int pid, String name, double price, int stock, int supplier_id) {
        this.pid = pid;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.supplier_id = supplier_id;
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
    public int getSupplier_id() {
        return supplier_id;
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
    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Product{");
        sb.append("pid=").append(pid);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", supplier_id=").append(supplier_id);
        sb.append('}');
        return sb.toString();
    }

}
