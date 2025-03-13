package com.toystore.utils;

//import com.raven.dao.NhanVienDao;
import com.toystore.model.NhanVien;
import com.toystore.model.store.Account;
import java.awt.Component;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Auth {

    public static NhanVien user = null;

    public static Account account = null;

    public static void clear() {
        Auth.user = null;
        Auth.account = null;
    }

    public static boolean isLogin() {
        return Auth.user != null;

    }

    public static boolean isLoginAccount() {
        return Auth.account != null;

    }

    public static boolean isManager() {
        return user.getVaiTro();
    }

    public static boolean isManagerAccount() {
//        return account.getRoleId();
        return account.getRoleId() == 1;
    }

    public static boolean acceptAccount(Component fram) {
        if (!Auth.isManagerAccount()) {
            MsgBox.alert(fram, "Bạn Không Có Quyền Thực Hiện Chức Năng Này !");
            return false;
        }
        return true;
    }

    public static boolean accept(Component fram) {
        if (!Auth.isManager()) {
            MsgBox.alert(fram, "Bạn Không Có Quyền Thực Hiện Chức Năng Này !");
            return false;
        }
        return true;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
