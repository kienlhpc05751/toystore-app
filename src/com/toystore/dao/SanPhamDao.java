/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

import com.toystore.db.DBHelper;
import com.toystore.model.Sanpham;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Asus
 */
abstract public class SanPhamDao extends StoreDao<Sanpham, String> {

    String INSERT_SQL = "INSERT INTO sanpham(BienTheSP,Maloai,TenSP,KichCo,MauSac,GiaBan,SoLuong)Values(?,?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE sanpham SET MaLoai=?, TenSP=?,KichCo=?,MauSac=?,GiaBan=?,SoLuong=? WHERE BienTheSP=?";
    String DELETE_SQL = "DELETE FROM sanpham WHERE BienTheSP=?";
    String SELECT_ALL_SQL = "SELECT * FROM sanpham";
    String SELECT_BY_ID_SQL = "SELECT * FROM sanpham WHERE BienTheSP=?";
    String sql = " select * from san "
            + "where HoTen like ? AND "
            + "MaNH NOT IN (select MaNH from HocVien where MaKH = ?)";
    //
    String sqlNH1 = "select * from NguoiHoc where MaNH not in ( select MaNH from HocVien)";
    String sqlNH = "select * from nguoihoc where maNH not in (select maNH from hocvien where makh = ?)";

    @Override
    public void insert(Sanpham enity) {

        DBHelper.update(INSERT_SQL, enity.getBienTheSP(), enity.getMaLoai(), enity.getTenSP(), enity.getKichCo(),
                enity.getMauSac(), enity.getGia(), enity.getSoLuong());
    }

    @Override
    public void update(Sanpham enity) {
        DBHelper.update(UPDATE_SQL, enity.getMaLoai(), enity.getTenSP(), enity.getKichCo(),
                enity.getMauSac(), enity.getGia(), enity.getSoLuong(), enity.getBienTheSP());
    }

    @Override
    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public Sanpham selectById(String k) {

        List<Sanpham> list = this.selectBySql(SELECT_BY_ID_SQL, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<Sanpham> selectById1(String ma) {
        List<Sanpham> list = this.selectBySql(SELECT_BY_ID_SQL, ma);
        return list;
    }

    @Override
    protected List<Sanpham> selectBySql(String sql, Object... args) {
        List<Sanpham> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                Sanpham enity = new Sanpham();
                enity.setBienTheSP(rs.getString(1));
                enity.setMaLoai(rs.getString(2));
                enity.setTenSP(rs.getString(3));
                enity.setKichCo(rs.getString(4));
                enity.setMauSac(rs.getString(5));
//                 DecimalFormat decimalFormat = new DecimalFormat("#,##");
//                 DecimalFormat df = new DecimalFormat("#.######");
//                DecimalFormat df = new DecimalFormat("#,###,###");
//                String gia = df.format(rs.getDouble(6));
//                enity.setGia(Double.parseDouble(gia));

                enity.setGia(rs.getDouble(6));
                enity.setSoLuong(rs.getInt(7));
                list.add(enity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Sanpham selectByid(String ma) {
        List<Sanpham> list = new ArrayList();
        Connection con = DBHelper.getDBConnection();
        try (PreparedStatement pstm = con.prepareStatement(SELECT_BY_ID_SQL)) {
            pstm.setString(1, ma);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Sanpham enity = new Sanpham();
                    enity.setBienTheSP(rs.getString(1));
                    enity.setMaLoai(rs.getString(2));
                    enity.setTenSP(rs.getString(3));
                    enity.setKichCo(rs.getString(4));
                    enity.setMauSac(rs.getString(5));
                    enity.setGia(rs.getDouble(6));
                    enity.setSoLuong(rs.getInt(7));
                    list.add(enity);
                }
                return list.get(0);
            } catch (Exception e) {
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

//    @Override
//    protected List<Sanpham> selectBySql1(String sql, Object... args) {
//        List<Sanpham> list = new ArrayList<>();
//        try {
//            ResultSet rs = DBHelper.query(sql, args);
//            while (s.next()) {
//                Sanpham enity = new Sanpham();
//             
//                enity.setMaLoai(rs.getString("ma"));
////                enity.setTenSP(rs.getString(3));
//              
//                list.add(enity);
//            }
//            rs.getStatement().getConnection().close();
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
    @Override
    public List<Sanpham> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);

    }
    String SELECT_ALL_LOAI_SQL = "Select * from Sanpham where BienTheSP = ?";

    public List<Sanpham> selectAllLoai(String id) {
        return this.selectBySql(SELECT_ALL_LOAI_SQL, id);
    }

//    String SELECT_ALL_LOAI_SQL1 = "SELECT DISTINCT sp.Maloai, l.tenloai\n"
//            + "FROM sanpham sp\n"
//            + "JOIN Loaisanpham l ON sp.maloai = l.maloai;";
    String SELECT_ALL_LOAI_SQL2 = "EXEC GetProductInfo;";

    public List<Sanpham> selectAllLoai1() {
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
    public List<Sanpham> selectNotlnCourse(int makh, String keyword) {

        return this.selectBySql(sql, "%" + keyword + "%", makh);
    }
//

    public List<Sanpham> selectNotlnCourse1(int makh) {

        return this.selectBySql(sqlNH, makh);
    }

    @Override
    public Sanpham selectByName(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

//    String sqla = "Select * from NguoiHoc Where HoTen LIKE ?";
//    public List<Sanpham> selectByKeyword(String keyword) {
//        return this.selectBySql(sqla, "%" + keyword + "%");
//
//    }
}
