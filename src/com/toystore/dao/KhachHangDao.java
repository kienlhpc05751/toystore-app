/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

import com.toystore.db.DBHelper;
import com.toystore.model.KhachHang;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hongk
 */
public class KhachHangDao extends StoreDao<KhachHang, Integer> {

    public static KhachHangDao getInstant() {
        return new KhachHangDao();
    }
    final String SELECT_ALL_SQL = "select * from khachhang";
    final String SELECT_BY_ID_SQL = "select * from khachhang where MaKH = ?";

    @Override
    public void insert(KhachHang e) {
     String sql="INSERT INTO khachhang (MaKH, TenKH, Email, Phone, DiaChi) values (?, ?, ?, ?, ?)";
        DBHelper.update(sql, 
                e.getMaKH(),
                e.getTenKH(),
                e.getEmail(),
                e.getSDT(),
                e.getDiaChi()
                );    
    }

    @Override
    public void update(KhachHang e) {
    String sql="update khachhang set TenKH = ?, Email = ?, Phone = ?, DiaChi = ? where MaKH = ?";
        DBHelper.update(sql, 
                e.getTenKH(),
                e.getEmail(),
                e.getSDT(),
                e.getDiaChi(),
                e.getMaKH());    
    }

    @Override
    public void delete(Integer k) {
        String sql="delete from khachhang where MaKH = ?";
        DBHelper.update(sql, k);
    }

    @Override
    public List<KhachHang> selectAll() {
    return selectBySql(SELECT_ALL_SQL);    
    }

    @Override
    public KhachHang selectById(Integer k) {
        return  (KhachHang) selectBySql(SELECT_BY_ID_SQL, k);

    }
    //
//    public KhachHang getbyid(Integer k){
//        return  (KhachHang) selectBySql(SELECT_BY_ID_SQL, k);
//    }
    
      @Override
    public List<KhachHang> selectById1(Integer k) {
            return   selectBySql(SELECT_BY_ID_SQL, k);
  
    }

    @Override
    public KhachHang selectByName(Integer k) {
        return null;
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
    List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while(rs.next()){
                KhachHang model = new KhachHang();
                model.setMaKH(rs.getInt("MaKH"));
                model.setTenKH(rs.getString("TenKH"));
                model.setEmail(rs.getString("Email"));
                model.setSDT(rs.getString("Phone"));
                model.setDiaChi(rs.getString("DiaChi"));
                list.add(model);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;    
    }
    public KhachHang findByID(Integer ID) throws SQLException {
        String sql = "SELECT * FROM khachhang WHERE MaKH=?";
        List<KhachHang> list = select(sql, ID);
        return !list.isEmpty() ? list.get(0) : null;
    }

//    private List<KhachHang> select(String sql, String ID) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
    @SuppressWarnings("null")
    private List<KhachHang> select(String query, Object... objects) throws SQLException {
        List<KhachHang> list = new ArrayList<>();
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        try {
            rs = DBHelper.query(query, objects);
            while (rs.next()) {
                KhachHang e = getModel(rs);
                list.add(e);
            }
        } finally {
//                rs.getStatement().getConnection().close();
        }

        return list;
    }

    private KhachHang getModel(ResultSet rs) throws SQLException {
    KhachHang kh = new KhachHang();
        kh.setMaKH(rs.getInt("MaKH"));
        kh.setTenKH(rs.getString("TenKH"));
        kh.setEmail(rs.getString("Email"));
        kh.setSDT(rs.getString("Phone"));
        kh.setDiaChi(rs.getString("DiaChi"));
       return kh;    
    }
    
    

}
