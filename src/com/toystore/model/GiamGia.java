/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model;

/**
 *
 * @author Asus
 */
public class GiamGia {
    private String MaGG;
    private String TienGG;
    
    public GiamGia(String maGG, String tienGG){
        this.MaGG = maGG;
        this.TienGG = tienGG;
    }
    public GiamGia(){
        
    }

    public String getMaGG() {
        return MaGG;
    }

    public void setMaGG(String MaGG) {
        this.MaGG = MaGG;
    }

    public String getTienGG() {
        return TienGG;
    }

    public void setTienGG(String TienGG) {
        this.TienGG = TienGG;
    }
    
}
