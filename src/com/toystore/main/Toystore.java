/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.toystore.main;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.toystore.testDao.*;
import com.toystore.dao.store.productDAO;
//import com.toystore.dao.store.productDAO;
import com.toystore.model.User;
import com.toystore.model.store.product;
import com.toystore.utils.BarcodeScanner;
import com.toystore.utils.EmailUtil;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class Toystore {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        maill();
        String code = BarcodeScanner.readBarcode("1741682490350.png");
        System.out.println("code: " + code);

    }

    public static void maill() {
        Scanner scanner = new Scanner(System.in);

        // 1. Kiểm tra email hợp lệ
        System.out.print("Nhập email của bạn: ");
        String email = scanner.nextLine();
        if (!EmailUtil.isValidEmail(email)) {
            System.out.println("Email không hợp lệ!");
            return;
        }

        // 2. Tạo OTP và gửi email
        String otp = EmailUtil.generateOTP(6);
        System.out.println("Mã OTP đã tạo: " + otp);

        boolean sent = EmailUtil.sendOTPEmail(email, otp);
        if (sent) {
            System.out.println("OTP đã được gửi tới email.");
        } else {
            System.out.println("Gửi OTP thất bại.");
        }

        // 3. Kiểm tra nhập OTP
        System.out.print("Nhập mã OTP bạn nhận được: ");
        String userOtp = scanner.nextLine();
        if (userOtp.equals(otp)) {
            System.out.println("Xác nhận OTP thành công!");
        } else {
            System.out.println("Mã OTP không đúng!");
        }

        scanner.close();

    }

    public static String decodeBarcode(BufferedImage image) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);
            return result.getText();
        } catch (NotFoundException e) {
            return null; // Không tìm thấy mã vạch
        }
    }

    public static void webcamera() {
        Webcam webcam = Webcam.getDefault();
        webcam.open();

        while (true) {
            BufferedImage image = webcam.getImage();
            String barcode = decodeBarcode(image);
            if (barcode != null) {
                System.out.println("✅ Mã vạch quét được: " + barcode);
                break;
            }
        }
        webcam.close();
    }

}
