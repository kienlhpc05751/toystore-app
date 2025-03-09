/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

import com.toystore.db.DBHelper;
import com.toystore.model.GiamGia;
import com.toystore.model.Sanpham;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
abstract public class GiamGiaDao extends StoreDao<GiamGia, String> {

    String INSERT_SQL = "INSERT INTO sanpham(BienTheSP,Maloai,TenSP,KichCo,MauSac,GiaBan,SoLuong)Values(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE sanpham SET MaLoai=?, TenSP=?,KichCo=?,MauSac=?,GiaBan=?,SoLuong=? WHERE BienTheSP=?";
    String DELETE_SQL = "DELETE FROM sanpham WHERE BienTheSP=?";
    String SELECT_ALL_SQL = "SELECT * FROM magiamgia ";
    String SELECT_BY_ID_SQL = "SELECT * FROM sanpham WHERE BienTheSP=?";
    String sql = " select * from san "
            + "where HoTen like ? AND "
            + "MaNH NOT IN (select MaNH from HocVien where MaKH = ?)";
    //
    String sqlNH1 = "select * from NguoiHoc where MaNH not in ( select MaNH from HocVien)";
    String sqlNH = "select * from nguoihoc where maNH not in (select maNH from hocvien where makh = ?)";

    @Override
    public void insert(GiamGia enity) {
        DBHelper.update(INSERT_SQL, enity.getMaGG(), enity.getTienGG());
    }

    @Override
    public void update(GiamGia enity) {
        DBHelper.update(UPDATE_SQL, enity.getMaGG(), enity.getTienGG());

    }

    @Override
    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public GiamGia selectById(String k) {

        List<GiamGia> list = this.selectBySql(SELECT_BY_ID_SQL, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
       @Override
    public List<GiamGia> selectById1(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    
    ///
    
    public GiamGia getAllByID(String ma) {
        List<GiamGia> list = new ArrayList<>();
        String sql = "SELECT * FROM maGiamgia WHERE maGG = ?";
        Connection con = DBHelper.getDBConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, ma);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                     GiamGia enity = new GiamGia();
                enity.setMaGG(rs.getString(1));
                enity.setTienGG(rs.getString(2));

                list.add(enity);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi SQL: " + ex.getMessage());
            ex.printStackTrace();
            // Hoặc ghi log lỗi
        }
        return list.get(0);
    }
    
    
    

    @Override
    protected List<GiamGia> selectBySql(String sql, Object... args) {
        List<GiamGia> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                GiamGia enity = new GiamGia();
                enity.setMaGG(rs.getString(1));
                enity.setTienGG(rs.getString(2));

                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<GiamGia> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);

    }
    String SELECT_ALL_LOAI_SQL = "Select * from Sanpham where BienTheSP = ?";

    public List<GiamGia> selectAllLoai(String id) {
        return this.selectBySql(SELECT_ALL_LOAI_SQL, id);
    }

//    String SELECT_ALL_LOAI_SQL1 = "SELECT DISTINCT sp.Maloai, l.tenloai\n"
//            + "FROM sanpham sp\n"
//            + "JOIN Loaisanpham l ON sp.maloai = l.maloai;";
    String SELECT_ALL_LOAI_SQL2 = "EXEC GetProductInfo;";

    public List<GiamGia> selectAllLoai1() {
        return this.selectBySql(SELECT_ALL_LOAI_SQL2);
    }

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

    public List<Object[]> getco() {
        String sql = "EXEC GetProductInfo;";
        String[] cols = {"Maloai"};
        return this.getListOfArray(sql, cols);
    }

//
    public List<GiamGia> selectNotlnCourse(int makh, String keyword) {

        return this.selectBySql(sql, "%" + keyword + "%", makh);
    }
//

    public List<GiamGia> selectNotlnCourse1(int makh) {

        return this.selectBySql(sqlNH, makh);
    }

    @Override
    public GiamGia selectByName(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
}
