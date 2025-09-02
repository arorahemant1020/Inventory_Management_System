package com.ncuindia.edu.supplier.dto;

public class SupplierDto {
    private String name;
    private String email;
    private String contact;

    public SupplierDto() {
    }

    public SupplierDto(String name, String email, String contact) {
        this.name = name;
        this.email = email;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ProductDto [name=" + name + ", email=" + email + ", contact=" + contact + ", getName()=" + getName()
                + ", getEmail()=" + getEmail() + ", getContact()=" + getContact() + ", getClass()=" + getClass()
                + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
    }
    
}
