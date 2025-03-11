/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.output.OutputException;

/**
 *
 * @author Asus
 */
public class BarcodeUtil {

    // Tạo mã vạch từ một chuỗi mã sản phẩm
    public static String generateBarcode() {
        long timestamp = System.currentTimeMillis(); // Tạo mã số dựa trên thời gian
//        return "SP" + timestamp; // Ví dụ: SP1710056789123
        return String.valueOf(timestamp);
    }

    // Tạo hình ảnh mã vạch từ barcode và lưu thành file
    public static String generateBarcodeImage(String barcodeText) throws BarcodeException {
        try {
            Barcode barcode = BarcodeFactory.createEAN13(barcodeText.substring(0, 12)); // Tạo barcode 128
            barcode.setBarWidth(2);  // Độ rộng của vạch
            barcode.setBarHeight(60); // Chiều cao của barcode``

            // Chuyển barcode thành BufferedImage
            BufferedImage barcodeImage = new BufferedImage(300, 100, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) barcodeImage.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 300, 100);
            g.setColor(Color.BLACK);
            barcode.draw(g, 10, 20);

            // Lưu file hình ảnh
            String filePath = "src/com/toystore/barcodes/" + barcodeText + ".png"; // Thư mục barcodes/
            File outputFile = new File(filePath);
            outputFile.getParentFile().mkdirs(); // Tạo thư mục nếu chưa có
            ImageIO.write(barcodeImage, "png", outputFile);

            return barcodeText + ".png"; // Trả về đường dẫn file ảnh mã vạch
        } catch (OutputException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
