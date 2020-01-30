package com.sg.inventory.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Supplier {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY) // field is auto-inncremented
    @Id // field is primary key
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String address;
    
    @Column
    private String contact;
    
    @ManyToMany(mappedBy = "suppliers") // somehow knows to find the class varaiable suppliers in the Product class
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    
}
