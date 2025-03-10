/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model.store;

/**
 *
 * @author Asus
 */
public class Brand {
       public int brandId;
    public String name;
    public String originBrand;
    public String description;

    public Brand() {
    }

    public Brand(int brandId, String name, String originBrand, String description) {
        this.brandId = brandId;
        this.name = name;
        this.originBrand = originBrand;
        this.description = description;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginBrand() {
        return originBrand;
    }

    public void setOriginBrand(String originBrand) {
        this.originBrand = originBrand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
