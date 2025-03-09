/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.model;

/**
 *
 * @author Asus
 */
public class HoaDonChiTiet {
    private String maHDCT;
    private String maHDn;
    private String MaBienTheSP;
    private int  soLuong;
    private String ghiChu;
    private String maGG;
    private Double thanhTien;
    
    public HoaDonChiTiet(){
        
    }
        String row[] = {"Mã HDCT", "MaHD", "MaSP", "soluong", "ghi chu", "ma gg", "Thành tiền"};

    public HoaDonChiTiet(String maHDCT, String maHDn, String MaBienTheSP, int soLuong, String ghiChu, String maGG, Double thanhTien) {
        this.maHDCT = maHDCT;
        this.maHDn = maHDn;
        this.MaBienTheSP = MaBienTheSP;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
        this.maGG = maGG;
        this.thanhTien = thanhTien;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHDn() {
        return maHDn;
    }

    public void setMaHDn(String maHDn) {
        this.maHDn = maHDn;
    }

    public String getMaBienTheSP() {
        return MaBienTheSP;
    }

    public void setMaBienTheSP(String MaBienTheSP) {
        this.MaBienTheSP = MaBienTheSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getMaGG() {
        return maGG;
    }

    public void setMaGG(String maGG) {
        this.maGG = maGG;
    }

    public Double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
