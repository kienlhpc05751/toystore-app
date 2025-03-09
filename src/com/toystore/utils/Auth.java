package com.toystore.utils;

//import com.raven.dao.NhanVienDao;
import com.toystore.model.NhanVien;
import java.awt.Component;

public class Auth {

    public static NhanVien user = null;

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static boolean isManager() {
        return user.getVaiTro();
    }
    public static boolean accept(Component fram) {
        if (!Auth.isManager()) {
            MsgBox.alert(fram, "Bạn Không Có Quyền Thực Hiện Chức Năng Này !");
            return false;
        }
        return true;
    }
}
