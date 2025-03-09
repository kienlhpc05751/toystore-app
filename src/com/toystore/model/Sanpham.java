/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model;

/**
 *
 * @author Asus
 */
public class Sanpham {
    private  String bienTheSP;
    private  String maLoai;
    private String tenSP;
    private String kichCo;
    private String mauSac;
    private double  gia;
    private int soLuong;

    public Sanpham(String bienTheSP, String maLoai, String tenSP, String kichCo, String mauSac, double gia, int soLuong) {
        this.bienTheSP = bienTheSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.kichCo = kichCo;
        this.mauSac = mauSac;
        this.gia = gia;
        this.soLuong = soLuong;
    }
    
    public Sanpham(){
        
    }

    public String getBienTheSP() {
        return bienTheSP;
    }

    public void setBienTheSP(String bienTheSP) {
        this.bienTheSP = bienTheSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
}
