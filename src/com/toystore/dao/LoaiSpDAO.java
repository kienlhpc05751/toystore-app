/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

import com.toystore.db.DBHelper;
import com.toystore.model.LoaiSanPham;
import com.toystore.model.Sanpham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
abstract public class LoaiSpDAO extends StoreDao<LoaiSanPham, String> {

    String SELECT_ALL_SQL = "select * from LoaiSanPham";
    String SELECT_ALL_ID_SQL = "select * from LoaiSanPham where maloai = ?";


    @Override
    protected List<LoaiSanPham> selectBySql(String sql, Object... args) {
        List<LoaiSanPham> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                LoaiSanPham enity = new LoaiSanPham();
                enity.setMaloaiSP(rs.getString(1));
                enity.setTenLoaiSP(rs.getString(2));
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
    public List<LoaiSanPham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);

    }

    @Override
    public void insert(LoaiSanPham e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(LoaiSanPham e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    @Override
    @Override
    public   List<LoaiSanPham> selectById1(String k) {
//        return  this.selectBySql(SELECT_ALL_ID_SQL, args)
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
      public LoaiSanPham selectById(String k) {

        List<LoaiSanPham> list = this.selectBySql(SELECT_ALL_ID_SQL, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public LoaiSanPham selectByName(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
