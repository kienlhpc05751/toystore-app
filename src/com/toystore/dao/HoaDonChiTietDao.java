/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

import static com.toystore.dao.NhanVienDao.SELECT_BY_ID_SQL;
import com.toystore.db.DBHelper;
import com.toystore.model.HoaDonChiTiet;
import com.toystore.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

/**
 *
 * @author Asus
 */
abstract public class HoaDonChiTietDao extends StoreDao<HoaDonChiTiet, String> {

    public List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> max() {
        String sql2 = "SELECT MAX(CAST(SUBSTRING(MaHDCT, 4, LEN(MaHDCT) - 2) AS INT)) AS MaxValue FROM hoadonchitiet;";
        String[] cols = {"MaxValue"};
        return this.getListOfArray(sql2, cols);
    }

    @Override
    protected List<HoaDonChiTiet> selectBySql(String sql, Object... args) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {

            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    String INSERT_SQL = "INSERT INTO HoadonChitiet Values(?,?,?,?,?,?,?)";
    String INSERT_SQL1 = "DECLARE @MaHDCT NVARCHAR(10);\n"
            + "DECLARE @MaHD NVARCHAR(10);\n"
            + "DECLARE @BienTheSP NVARCHAR(10);\n"
            + "DECLARE @SoLuong INT;\n"
            + "DECLARE @GhiChu NVARCHAR(100);\n"
            + "DECLARE @MaGG NVARCHAR(10);\n"
            + "DECLARE @ThanhTien FLOAT;\n"
            + "SET @MaHDCT = ?; \n"
            + "SET @MaHD = ?; \n"
            + "SET @BienTheSP = ?;\n"
            + "SET @SoLuong = ?; \n"
            + "SET @GhiChu = ?; \n"
            + "SET @MaGG = ?;\n"
            + "SET @ThanhTien = ?; \n"
            + "BEGIN TRANSACTION;\n"
            + "-- Step 1: Insert new record into hoadonchitiet\n"
            + "INSERT INTO hoadonchitiet (MaHDCT, MaHD, BienTheSP, SoLuong, GhiChu, MaGG, ThanhTien)\n"
            + "VALUES (@MaHDCT, @MaHD, @BienTheSP, @SoLuong, @GhiChu, @MaGG, @ThanhTien);\n"
            + "-- Step 2: Update quantity in sanpham\n"
            + "UPDATE sanpham\n"
            + "SET SoLuong = SoLuong - @SoLuong\n"
            + "WHERE BienTheSP = @BienTheSP;\n"
            + "UPDATE hoadon--Step3: Update\n"
            + "SET TongTien = TongTien + @ThanhTien\n"
            + "WHERE MaHD = @MaHD;"
            + "COMMIT;";
    String UPDATE_SQL = "UPDATE sanpham SET MaLoai=?, TenSP=?,KichCo=?,MauSac=?,GiaBan=?,SoLuong=? WHERE BienTheSP=?";
    String DELETE_SQL = "DELETE FROM sanpham WHERE BienTheSP=?";
    String SELECT_ALL_SQL = "SELECT * FROM HoaDonChiTiet";
    String SELECT_BY_ID_SQL = "SELECT * FROM  hoadonchitiet where mahd=?";
    String sql = " select * from san "
            + "where HoTen like ? AND "
            + "MaNH NOT IN (select MaNH from HocVien where MaKH = ?)";
    //
    String sqlNH1 = "select * from NguoiHoc where MaNH not in ( select MaNH from HocVien)";
    String sqlNH = "select * from nguoihoc where maNH not in (select maNH from hocvien where makh = ?)";

    @Override
    public void insert(HoaDonChiTiet enity) {

        DBHelper.update(INSERT_SQL1, enity.getMaHDCT(), enity.getMaHDn(), enity.getMaBienTheSP(), enity.getSoLuong(),
                enity.getGhiChu(), enity.getMaGG(), enity.getThanhTien());
    }

    @Override
    public void update(HoaDonChiTiet enity) {
        DBHelper.update(UPDATE_SQL, enity.getMaHDCT(), enity.getMaHDn(), enity.getMaBienTheSP(), enity.getMaGG(),
                enity.getGhiChu(), enity.getSoLuong(), enity.getThanhTien());
    }

    @Override
    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HoaDonChiTiet> selectById1(String k) {

        List<HoaDonChiTiet> listt = selectBySql(SELECT_BY_ID_SQL, k);
        if (listt.isEmpty()) {
            return null;
        }
        return listt;
    }

    @Override
    public HoaDonChiTiet selectById(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDonChiTiet> getAllByID(String ma) {
        List<HoaDonChiTiet> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDonchitiet WHERE maHD = ?";
        Connection con = DBHelper.getDBConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, ma);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    HoaDonChiTiet enity = new HoaDonChiTiet();
                    enity.setMaHDCT(rs.getString(1));
                    enity.setMaHDn(rs.getString(2));
                    enity.setMaBienTheSP(rs.getString(3));
                    enity.setSoLuong(rs.getInt(4));
                    enity.setGhiChu(rs.getString(5));
                    enity.setMaGG(rs.getString(6));
                    enity.setThanhTien(rs.getDouble(7));
                    list.add(enity);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
            // Hoặc ghi log lỗi
        }
        return list;
    }

    @Override
    public List<HoaDonChiTiet> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);

//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDonChiTiet selectByName(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    private List<HoaDonChiTiet> selectBysql(String SELECT_BY_ID_SQL, String k) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
}
