package com.toystore.dao;

import com.toystore.db.DBHelper;
import com.toystore.model.HoaDon;
import java.util.List;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

abstract public class HoaDonDao extends StoreDao<HoaDon, String> {

    public List<HoaDon> getAllByID(String ma) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon WHERE maHD = ?";
        Connection con = DBHelper.getDBConnection();
        try (PreparedStatement stm = con.prepareStatement(sql)) {
            stm.setString(1, ma);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    HoaDon enity = new HoaDon();

                    enity.setMaKH(rs.getString("maKH"));
                    enity.setTongTien(rs.getDouble("TongTien"));

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
        String sql2 = "SELECT MAX(CAST(SUBSTRING(maHD, 3, LEN(maHD) - 2) AS INT)) AS MaxValue FROM hoadon;";
        String[] cols = {"MaxValue"};
        return this.getListOfArray(sql2, cols);
    }

    String sql1 = "SELECT top 1 maHD FROM hoadon ORDER BY maHD DESC";
    String sql2 = "SELECT MAX(CAST(SUBSTRING(maHD, 3, LEN(maHD) - 2) AS INT)) AS MaxValue FROM hoadon;";

//      public List<HoaDon> selectBySql2(String sql){
//          List<Object[]> list;
//          try {
//              ResultSet rs = DBHelper.query(sql);
//              while (rs.next()) {                  
//                  HoaDon model = new HoaDon();
//                  model.setMaHD(sql);
//                  list.add();
//              }
//          } catch (Exception e) {
//          }
//      }
    protected List<HoaDon> selectBySql1(String sqll) {
        List<HoaDon> list;
        list = new ArrayList<>();

        try {
            ResultSet rs = DBHelper.query(sqll);
            while (rs.next()) {
                HoaDon model = new HoaDon();
                model.setMaHD(rs.getString("maHD"));
                list.add(model);
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = DBHelper.query(sql, args);
            while (rs.next()) {
                HoaDon enity = new HoaDon();
                enity.setMaHD(rs.getString("maHD"));
                enity.setMaKH(rs.getString("maKH"));
                enity.setMaNV(rs.getString("maNV"));
                enity.setNgayTao(rs.getDate("NgayTao"));
//                DecimalFormat formatter = new DecimalFormat("###,###,###");// định dạng
//                String tien = formatter.format(rs.getDouble("TongTien
                enity.setTongTien(rs.getDouble("TongTien"));
//                enity.setTongTien(Double.parseDouble(tien));
                enity.setTrangThai(rs.getString("TrangThai"));

                list.add(enity);
            }

            rs.getStatement().getConnection().close();
            return list;
//                        System.out.println(List);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    String INSERT_SQL = "insert into hoadon values(?,?,?,?,?,?)";

    String UPDATE_SQL = "UPDATE hoadon SET MaKH=?, MaNV=?,NgayTao=?,TongTien=?,TrangThai=? WHERE MaHD=?";

//    String DELETE_SQL = "DELETE FROM hoadon WHERE MaHD=?";
    String DELETE_SQL = "DECLARE @HoaDonID NVARCHAR(10);\n"
            + "SET @HoaDonID = ?;\n"
            + "\n"
            + "BEGIN TRANSACTION;\n"
            + "\n"
            + "DELETE FROM hoadonchitiet WHERE MaHD = @HoaDonID;\n"
            + "\n"
            + "DELETE FROM hoadon WHERE MaHD = @HoaDonID; COMMIT;";

    String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHD=?";
    String sql = " select * from san "
            + "where HoTen like ? AND "
            + "MaNH NOT IN (select MaNH from HocVien where MaKH = ?)";
    //
    String sqlNH1 = "select * from NguoiHoc where MaNH not in ( select MaNH from HocVien)";
    String sqlNH = "select * from nguoihoc where maNH not in (select maNH from hocvien where makh = ?)";

    @Override
    public void insert(HoaDon enity) {

        DBHelper.update(INSERT_SQL, enity.getMaHD(), enity.getMaKH(), enity.getMaNV(), enity.getNgayTao(),
                enity.getTongTien(), enity.getTrangThai());
    }

    @Override
    public void update(HoaDon enity) {
        DBHelper.update(UPDATE_SQL, enity.getMaKH(), enity.getMaNV(), enity.getNgayTao(),
                enity.getTongTien(), enity.getTrangThai(), enity.getMaHD());
    }

    @Override
    public void delete(String id) {
        DBHelper.update(DELETE_SQL, id);
    }

    @Override
    public HoaDon selectById(String k) {

        List<HoaDon> list = this.selectBySql(SELECT_BY_ID_SQL, k);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<HoaDon> selectById1(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);

//            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public HoaDon selectByName(String k) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDon> selectById1() {
        return this.selectBySql1(sql1);

    }

}
