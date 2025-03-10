/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model.store;

/**
 *
 * @author Asus
 */
public class SuperCategory {
        public int superCategoryId;
    public String name;
    public String description;
    public String createdAt;

    public SuperCategory() {
    }

    public SuperCategory(int superCategoryId, String name, String description, String createdAt) {
        this.superCategoryId = superCategoryId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    public int getSuperCategoryId() {
        return superCategoryId;
    }

    public void setSuperCategoryId(int superCategoryId) {
        this.superCategoryId = superCategoryId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
