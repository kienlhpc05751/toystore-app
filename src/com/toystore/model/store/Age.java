/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model.store;

/**
 *
 * @author Asus
 */
public class Age {
      public int ageId;
    public String ageRange;

    public Age() {
    }

    public Age(int ageId, String ageRange) {
        this.ageId = ageId;
        this.ageRange = ageRange;
    }

    public int getAgeId() {
        return ageId;
    }

    public void setAgeId(int ageId) {
        this.ageId = ageId;
    }

    public String getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(String ageRange) {
        this.ageRange = ageRange;
    }
    
    
}
