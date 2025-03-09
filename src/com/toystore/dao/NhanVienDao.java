package com.toystore.dao;

import com.toystore.db.DBHelper;
import com.toystore.model.NhanVien;
import java.awt.Font;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

abstract public class NhanVienDao extends StoreDao<NhanVien, String> {
    
    
     public  String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
    
    String SELECT_ALL_SQL = "SELECT * FROM Nhanvien";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM nhanvien WHERE MaNV=?";

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = DBHelper.query(sql, args);
                while (rs.next()) {
                    NhanVien entity = new NhanVien();
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setMatKhau(rs.getString("MatKhau"));
                    entity.setTenNV(rs.getString("tenNV"));
                    entity.setNgaySinh(rs.getDate("ngaysinh"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setSDT(rs.getString("phone"));
                    entity.setVaiTro(rs.getBoolean("VaiTro"));
                    entity.setHinhAnh(rs.getString("hinh"));

                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
 


    @Override
    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, TenNV, Matkhau, NgaySinh, Email, Phone, vaiTro, Hinh) VALUES (?, ?, ?, ?,?, ?, ?, ?)";
        DBHelper.update(sql,
                model.getMaNV(),
                model.getTenNV(),
                model.getMatKhau(),
                model.getNgaySinh(),
                model.getEmail(),
                model.getSDT(),
                model.getVaiTro(),
                model.getHinhAnh());
    }
    
    
        public void updatedp(NhanVien model) {
        String sql = "update nhanvien set Matkhau = ? where MaNV = ?";
        DBHelper.update(sql,
//                model.getMaNV(),
           
                model.getMatKhau(),
                
                model.getMaNV());


    }

    @Override
    public void update(NhanVien model) {
        String sql = "update nhanvien set TenNV = ?, Matkhau = ?, NgaySinh = ?, Email = ?, Phone =?, vaiTro =?, Hinh =? where MaNV = ?";
        DBHelper.update(sql,
//                model.getMaNV(),
                model.getTenNV(),
                model.getMatKhau(),
                model.getNgaySinh(),
                model.getEmail(),
                model.getSDT(),
                model.getVaiTro(),
                model.getHinhAnh(),
                model.getMaNV());


    }

    @Override
    public void delete(String MaNV) {
        String sql = "delete from nhanvien where MaNV = ?";
        DBHelper.update(sql, MaNV);
    }

    public List<NhanVien> select() throws SQLException {
        String sql = "select * from nhanvien";
        return select(sql);
    }

    public NhanVien findByID(String ID) throws SQLException {
        String sql = "SELECT * FROM nhanvien WHERE MaNV=?";
        List<NhanVien> list = select(sql, ID);
        return !list.isEmpty() ? list.get(0) : null;
    }

    @SuppressWarnings("null")
    private List<NhanVien> select(String query, Object... objects) throws SQLException {
        List<NhanVien> list = new ArrayList<>();
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        try {
            rs = DBHelper.query(query, objects);
            while (rs.next()) {
                NhanVien e = getModel(rs);
                list.add(e);
            }
        } finally {
//                rs.getStatement().getConnection().close();
        }

        return list;
    }

    private NhanVien getModel(ResultSet rs) throws SQLException {
        NhanVien nv = new NhanVien();
        nv.setMaNV(rs.getString("MaNV"));
        nv.setTenNV(rs.getString("TenNV"));
        nv.setMatKhau(rs.getString("Matkhau"));
        nv.setNgaySinh(rs.getDate("NgaySinh"));
        nv.setSDT(rs.getString("Phone"));
        nv.setVaiTro(rs.getBoolean("vaiTro"));
        nv.setHinhAnh(rs.getString("Hinh") == null ? "Username.png" : rs.getString("Hinh"));
        return nv;
    }

    public void setTable(JTable table) {
        //Thiết lập lại font chữ
        table.setFont(new Font("Roboto", Font.PLAIN, 18));
        table.setRowHeight(30);

        // Thiết lập chỉ được chọn 1 dòng trong bảng
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

//    @Override
    @Override
    public List<NhanVien> selectAll() {
        List<NhanVien> list = new ArrayList<>();
        return list = selectBySql(SELECT_ALL_SQL);
//        return this.selectBySql(SELECT_ALL_SQL);
//        return this.selectBysql(SELECT_ALL_SQL);

    }

    @Override
//    public NhanVien selectById(String k) {
//        return null;
//    }
    public NhanVien selectById(String key) {

        List<NhanVien> list = selectBySql(SELECT_BY_ID_SQL, key);
        try {

            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
//        return list.size() > 0 ? list.get(0) : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public NhanVien selectByName(String k) {
        return null;
    }

//    @Override
//    protected List<NhanVien> selectBySql(String sql, Object... args) {
//        return null;
//    }
        @Override
        public List<NhanVien> selectById1(String k) {
//            return  List
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
}
