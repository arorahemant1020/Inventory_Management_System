package com.ncuindia.edu.supplier.model;

public class Supplier {
    private Integer sid;
    private String name;
    private String email;
    private String contact;
    
    public Supplier(){

    }
    
    public Supplier(int sid, String name, String email, String contact) {
        this.sid = sid;
        this.name = name;
        this.email = email;
        this.contact = contact;
    }
    
    public int getSid() {
        return sid;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getContact() {
        return contact;
    }
    public void setSid(int sid) {
        this.sid = sid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Supplier [sid=" + sid + ", name=" + name + ", email=" + email + ", contact=" + contact + "]";
    }

}
