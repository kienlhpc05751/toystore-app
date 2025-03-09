/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class KhachHang {
    Integer MaKH;
    String TenKH;
    String Email;
    String SDT;
    String DiaChi;

    public KhachHang() {
    }

    public KhachHang(Integer MaKH, String TenKH, String Email, String SDT, String DiaChi) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.Email = Email;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
    }

    public Integer getMaKH() {
        return MaKH;
    }

    public void setMaKH(Integer MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    
    
     @Override
    public String toString() {
        return "KhachHang" + "MaKH=" + MaKH + ", TenKH=" + TenKH + ", Email=" + Email +  ", SDT=" + SDT  + ", DiaChi=" + DiaChi + '}';
    }
    
    
    
}
