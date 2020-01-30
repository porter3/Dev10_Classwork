package com.sg.inventory.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

    @GeneratedValue(strategy=GenerationType.IDENTITY) // it's an auto-incremented field that the DB will handle
    @Id // denotes that it's the primary key for the Product table
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @Column
    private String description;
    
    @Column(nullable = false)
    private int quantity = 0;

    @ManyToOne // many products to one store
    /* proper casing of 'name' is super important:
    JPA will add an underscore if the JoinColumn name is camelCase */
    @JoinColumn(name = "storeid", nullable = false) 
    private Store store;
    
    @ManyToMany
    @JoinTable(name = "product_supplier",
            joinColumns = {@JoinColumn(name = "productid")}, // the column of this object that's being joined on
            inverseJoinColumns = {@JoinColumn(name = "supplierid")}) // the column of the supplier table that's being joined on
    private List<Supplier> suppliers;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
    
    
}
