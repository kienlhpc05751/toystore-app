/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.form;

/**
 *
 * @author Asus
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class PasswordHashingExample {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mật khẩu cần mã hóa:");
        String password = scanner.nextLine();

        String hashedPassword = hashPassword(password);
        if (hashedPassword != null) {
            System.out.println("Mật khẩu đã mã hóa: " + hashedPassword);
        } else {
            System.out.println("Đã xảy ra lỗi trong quá trình mã hóa mật khẩu.");
        }
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
