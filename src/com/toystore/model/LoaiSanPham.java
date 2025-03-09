/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model;

/**
 *
 * @author Asus
 */
public class LoaiSanPham {
    private String MaloaiSP;
    private String TenLoaiSP;
    
    public LoaiSanPham(){
        
    }

    public String getMaloaiSP() {
        return MaloaiSP;
    }

    public void setMaloaiSP(String MaloaiSP) {
        this.MaloaiSP = MaloaiSP;
    }

    public String getTenLoaiSP() {
        return TenLoaiSP;
    }

    public void setTenLoaiSP(String TenLoaiSP) {
        this.TenLoaiSP = TenLoaiSP;
    }
    
    @Override
    public String toString(){
        return this.MaloaiSP +"("+ TenLoaiSP + ")" ;
    }
    
}
