/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.utils;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Asus
 */
public class BarcodeScanner {

    public static String readBarcode(String filePath) {
        try {
            System.out.println(" Dang đoc file: " + filePath);
            BufferedImage image = ImageIO.read(new File("src/com/toystore/barcodes/" + filePath));
            System.out.println("Đa tai anh thanh cong!");
            // Chuyển ảnh thành dữ liệu nhị phân
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            // Giải mã mã vạch
            Result result = new MultiFormatReader().decode(bitmap);
            System.out.println("Ma vach đa đuoc đoc: " + result.getText());
            return result.getText();
        } catch (IOException | NotFoundException e) {
            System.out.println("Loi khi quet ma vach: " + e.getMessage());
            return "Khong the quet ma vach!";
        }
    }

}
