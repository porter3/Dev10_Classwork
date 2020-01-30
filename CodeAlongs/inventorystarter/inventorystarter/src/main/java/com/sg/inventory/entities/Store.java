package com.sg.inventory.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // this class is represented in the database
public class Store implements Serializable {

    @GeneratedValue(strategy=GenerationType.IDENTITY) // it's an auto-incremented field the DB will handle
    @Id // it's the class's primary key
    private int id;
    
    @Column(nullable=false) // is a column in the table, is not nullable
    private String name;
    
    @Column
    private String location;
    
    @Column
    private String manager;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    
    
}
