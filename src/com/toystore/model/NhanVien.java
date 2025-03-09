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
public class NhanVien {
    String MaNV;
    String TenNV;
    String MatKhau;
    Date NgaySinh;
    String Email;
    String SDT;
    boolean VaiTro = false;
    String HinhAnh;

    public NhanVien() {
    }

    public NhanVien(String MaNV, String TenNV, String MatKhau, Date NgaySinh, String Email, String SDT, boolean VaiTro, String HinhAnh) {
        this.MaNV = MaNV;
        this.TenNV = TenNV;
        this.MatKhau = MatKhau;
        this.NgaySinh = NgaySinh;
        this.Email = Email;
        this.SDT = SDT;
        this.VaiTro = VaiTro;
        this.HinhAnh = HinhAnh;
    }
    
  

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String TenNV) {
        this.TenNV = TenNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
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

    public boolean getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }
    
     @Override
    public String toString() {
        return "NhanVien{" + "MaNV=" + MaNV + ", TenNV=" + TenNV + ", MatKhau=" + MatKhau + ", NgaySinh=" + NgaySinh + ", Email=" + Email +  ", SDT=" + SDT  + ", VaiTro=" + VaiTro +  ", HinhAnh=" + HinhAnh + '}';
    }
    
    
    
}
