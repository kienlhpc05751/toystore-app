/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.toystore.form;

import com.toystore.dao.HoaDonChiTietDao;
import com.toystore.dao.HoaDonDao;
import com.toystore.db.DBHelper;
import com.toystore.model.HoaDonChiTiet;
import com.toystore.model.HoaDon;
import com.toystore.utils.XDate;
import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Asus
 */
public class BillPrintingApp extends javax.swing.JFrame implements Printable {

    private JTextArea billTextArea;
    private JTable billTable;

    HoaDonChiTietDao daoHDCT = new HoaDonChiTietDao() {
    };
    List<HoaDonChiTiet> listhdct = new ArrayList<>();

    public void ma(String ma) {
        this.madh = ma;
    }
    String madh = "";
    
    

    public BillPrintingApp() {
        initComponents();
//        ma();
        setLocationRelativeTo(null);
        setTitle("Bill Printing App");
        setSize(500, 500);
        
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        billTextArea = new JTextArea();
//        billTextArea.setEditable();
        JScrollPane scrollPane = new JScrollPane(billTextArea);
        Object[][] data = {
            {"    ", "SHOP THỜI TRANG LIGHTING STRIKE", "   \n"},
            {"     ", "_____________________________", "    \n"},
            {"     ", "_____________________________", "    \n"},
            {"     ", "ngày :" + XDate.now(), "    \n"},};
        String[] columnNames = {"Item", "Quantity", "Price"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        billTable = new JTable(model);
        // nút in
        JButton printButton = new JButton("Print Bill");
        printButton.addActionListener(e -> printBill());
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(printButton, BorderLayout.SOUTH);

    }

    public class BillDetail {

        private String maHDCT;
        private String productName;
        private double unitPrice;
        private String maGG;
        private double thanhTien;

        public String getMaHDCT() {
            return maHDCT;
        }

        public void setMaHDCT(String maHDCT) {
            this.maHDCT = maHDCT;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public String getMaGG() {
            return maGG;
        }

        public void setMaGG(String maGG) {
            this.maGG = maGG;
        }

        public double getThanhTien() {
            return thanhTien;
        }

        public void setThanhTien(double thanhTien) {
            this.thanhTien = thanhTien;
        }

        // Constructors, getters, and setters for the fields...
        public BillDetail(String maHDCT, String productName, double unitPrice, String maGG, double thanhTien) {
            this.maHDCT = maHDCT;
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.maGG = maGG;
            this.thanhTien = thanhTien;
        }
    }

    private void printBill() {
        // Clear previous content
        billTextArea.setText("");

        // Append header
        StringBuilder billContent = new StringBuilder();
        for (int row = 0; row < billTable.getRowCount(); row++) {
            for (int col = 0; col < billTable.getColumnCount(); col++) {
                billContent.append(billTable.getValueAt(row, col)).append("\t");
            }
            billContent.append("\n");
        }

        // Append details from listhdct based on the invoice code (madh)
        billContent.append("\nDetails:\n");
        // Tạo lớp BillDetail để lưu trữ thông tin chi tiết hóa đơn

// Trong phần code của bạn
        List<BillDetail> billDetails = new ArrayList<>();

// Thực hiện truy vấn SQL để lấy thông tin từ cả hai bảng
        String sqlQuery = "SELECT hdct.MaHDCT, sp.TenSP, sp.GiaBan, hdct.MaGG, hdct.ThanhTien "
                + "FROM HoaDonChiTiet hdct "
                + "JOIN SanPham sp ON hdct.BienTheSP = sp.BienTheSP "
                + "WHERE hdct.MaHD = ?";
        try (java.sql.Connection con = DBHelper.getDBConnection(); PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, madh);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BillDetail detail = new BillDetail(madh, sqlQuery, NORMAL, madh, ICONIFIED);
                detail.setMaHDCT(resultSet.getString("MaHDCT"));
                detail.setProductName(resultSet.getString("TenSP"));
                detail.setUnitPrice(resultSet.getDouble("GiaBan"));
                detail.setMaGG(resultSet.getString("MaGG"));
                detail.setThanhTien(resultSet.getDouble("ThanhTien"));

                billDetails.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý lỗi nếu cần
        }

        billContent.append("Mã hóa đơn : " + madh + " \n");
        billContent.append("Mã HDCT______|tên SP______|giá SP______|MaGG______|Thành Tiền______ \n");

// Sử dụng danh sách billDetails để tạo billContent
        for (BillDetail detail : billDetails) {
            billContent.append(detail.getMaHDCT()).append("____\t")
                    .append(detail.getProductName()).append("____\t")
                    .append(detail.getUnitPrice()).append("____\t")
                    .append(detail.getMaGG()).append("____\t")
                    .append(detail.getThanhTien()).append("\n");
        }

        // Append additional information or formatting as needed
        billContent.append(
                "\nThank you for shopping with us!\n");

        billTextArea.append(billContent.toString());

        // In hóa đơn
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        printerJob.setPrintable(
                this);

        if (printerJob.printDialog()) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        billTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        FontMetrics metrics = g2d.getFontMetrics();
        String[] lines = billTextArea.getText().split("\\n");
        int y = 50; // Vị trí y của dòng đầu tiên

        for (String line : lines) {
            int stringWidth = metrics.stringWidth(line);
            int x = (int) ((pageFormat.getImageableWidth() - stringWidth) / 2) + 50;
            g2d.drawString(line, x, y);
            y += metrics.getHeight() + 5; // Cách giữa các dòng (có thể điều chỉnh)
        }

        return PAGE_EXISTS;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillPrintingApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillPrintingApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillPrintingApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillPrintingApp.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillPrintingApp().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
