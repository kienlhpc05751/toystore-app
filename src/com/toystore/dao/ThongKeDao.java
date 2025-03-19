package com.toystore.dao;

import java.util.ArrayList;
import java.util.List;
//import com.toystore.db.DBHelper;
import java.sql.*;

public class ThongKeDao {

    public List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
//        try {
//            List<Object[]> list = new ArrayList<>();
//            ResultSet rs = DBHelper.query(sql, args);
//            while (rs.next()) {
//                Object[] vals = new Object[cols.length];
//                for (int i = 0; i < cols.length; i++) {
//                    vals[i] = rs.getObject(cols[i]);
//                }
//                list.add(vals);
//            }
//            rs.getStatement().getConnection().close();
//            return list;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    public List<Object[]> Tonkho() {
//        String sql = "SELECT\n"
//                + "    lsp.TenLoai AS TenLoaiSanPham,\n"
//                + "    SUM(sp.Soluong) AS TongSoLuongTonKho\n"
//                + "FROM\n"
//                + "    sanpham sp\n"
//                + "JOIN\n"
//                + "    Loaisanpham lsp ON sp.Maloai = lsp.Maloai\n"
//                + "GROUP BY\n"
//                + "    lsp.TenLoai;";
//        String[] cols = {"TenLoaiSanPham", "TongSoLuongTonKho"};
//        return this.getListOfArray(sql, cols);
        return null;
    }

    public List<Object[]> lichsu() {
//        String sql = "	SELECT h.MaHD AS MaHoaDon, h.NgayTao AS NgayMua, kh.TenKH AS TenKhachHang, sp.TenSP AS TenSanPham, hdt.SoLuong AS SoLuong, hdt.thanhTien AS ThanhTien\n"
//                + "FROM\n"
//                + "    hoadon h\n"
//                + "JOIN\n"
//                + "    hoadonchitiet hdt ON h.MaHD = hdt.MaHD\n"
//                + "JOIN\n"
//                + "    sanpham sp ON hdt.BienTheSP = sp.BienTheSP\n"
//                + "JOIN\n"
//                + "    khachhang kh ON h.MaKH = kh.MaKH\n"
//                + "ORDER BY\n"
//                + "    h.NgayTao DESC;";
//        String[] cols = {"MahoaDon", "NgayMua", "TenKhachHang", "TenSanPham", "SoLuong", "ThanhTien"};
//        return this.getListOfArray(sql, cols);
        return null;
    }

    public List<Object[]> theoNgay() {
//        String sql = "SELECT h.NgayTao AS NgayBan,SUM(hdt.SoLuong) AS TongSoLuongBan,SUM(h.TongTien) AS TongTienBanHang\n"
//                + "FROM hoadon h JOIN hoadonchitiet hdt ON h.MaHD = hdt.MaHD\n"
//                + "WHERE CONVERT(date, h.NgayTao) = CONVERT(date, GETDATE())\n"
//                + "GROUP BY h.NgayTao;";
//        String[] cols = {"NgayBan", "TongSoLuongBan", "TongTienBanHang"};
//        return this.getListOfArray(sql, cols);
        return null;

    }

    public List<Object[]> theoThang() {
////        String sql = "SELECT h.NgayTao AS NgayBan,SUM(hdt.SoLuong) AS TongSoLuongBan,SUM(h.TongTien) AS TongTienBanHang\n"
////                + "FROM hoadon h JOIN hoadonchitiet hdt ON h.MaHD = hdt.MaHD\n"
////                + "WHERE CONVERT(date, h.NgayTao) = CONVERT(date, GETDATE())\n"
////                + "GROUP BY h.NgayTao;";
//
//        String sql = "SELECT MONTH(h.NgayTao) AS ThangBan, SUM(hdt.SoLuong) AS TongSoLuongBan, SUM(h.TongTien) AS TongTienBanHang\n"
//                + "FROM hoadon h JOIN hoadonchitiet hdt ON h.MaHD = hdt.MaHD\n"
//                + "WHERE YEAR(h.NgayTao) = YEAR(GETDATE()) AND MONTH(h.NgayTao) = MONTH(GETDATE())\n"
//                + "GROUP BY MONTH(h.NgayTao);";
//        String[] cols = {"ThangBan", "TongSoLuongBan", "TongTienBanHang"};
//        return this.getListOfArray(sql, cols);
        return null;

    }

    public List<Object[]> theoNam() {
////        String sql = "SELECT h.NgayTao AS NgayBan,SUM(hdt.SoLuong) AS TongSoLuongBan,SUM(h.TongTien) AS TongTienBanHang\n"
////                + "FROM hoadon h JOIN hoadonchitiet hdt ON h.MaHD = hdt.MaHD\n"
////                + "WHERE CONVERT(date, h.NgayTao) = CONVERT(date, GETDATE())\n"
////                + "GROUP BY h.NgayTao;";
//
//        String sql = "SELECT YEAR(h.NgayTao) AS NamBan, SUM(hdt.SoLuong) AS TongSoLuongBan, SUM(h.TongTien) AS TongTienBanHang\n"
//                + "FROM hoadon h JOIN hoadonchitiet hdt ON h.MaHD = hdt.MaHD\n"
//                + "WHERE YEAR(h.NgayTao) = YEAR(GETDATE())\n"
//                + "GROUP BY YEAR(h.NgayTao);";
//        String[] cols = {"NamBan", "TongSoLuongBan", "TongTienBanHang"};
//        return this.getListOfArray(sql, cols);
        return null;

    }

    public List<Object[]> daban() {
//        String sql = "SELECT SUM(hct.SoLuong) AS SoLuongDaBan, SUM(hct.thanhTien) as tong FROM hoadonchitiet hct";
//        String[] cols = {"SoLuongDaBan", "tong"};
//        return this.getListOfArray(sql, cols);
        return null;

    }

    public List<Object[]> gettopsp(Integer top) {
//        String sql = "EXEC TopSellingProducts @TopN = ?;";
//        String[] cols = {"bienthesp", "tenSP", "TotalQuantitySold"};
//        return this.getListOfArray(sql, cols, top);
        return null;

    }

    public List<Object[]> getBangDiem(Integer makh) {
//        String sql = "{CALL sp_BangDiem(?)}";
//        String[] cols = {"MaNH", "HoTen", "Diem"};
//        return this.getListOfArray(sql, cols, makh);
        return null;

    }

    public List<Object[]> getBangDiemChuyenDe() {
//        String sql = "{CALL sp_ThongKeDiem}";
//        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
//        return this.getListOfArray(sql, cols);
        return null;

    }

    public List<Object[]> getLuongNguoiHoc() {
//        String sql = "{CALL sp_ThongKeNguoiHoc}";
//        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
//        return this.getListOfArray(sql, cols);
        return null;

    }

    public List<Object[]> getDoanhThu(Integer nam) {
//        String sql = "{CALL  sp_ThongKeDoanhThu(?)}";
//        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
//        return this.getListOfArray(sql, cols, nam); 
        return null;

    }

    public List<Integer> selectYears() {
//        String sql = "Select DISTINCT year(NgayKG) from KhoaHoc ORDER BY year DESC";
//        List<Integer> list = new ArrayList<>();
//        try {
//            ResultSet rs = DBHelper.query(sql);
//            while (rs.next()) {
//                list.add(rs.getInt(1));
//            }
//            rs.getStatement().getConnection().close();
//            return list;
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
        return null;
    }

}
