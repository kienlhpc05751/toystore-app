package com.toystore.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MsgBox {

    // Hàm tạo cửa sổ thông báo tùy chỉnh
    private static void showCustomDialog(Component parent, String message, String title) {
        Frame frame = (parent != null) ? (Frame) SwingUtilities.getWindowAncestor(parent) : null;
        JDialog dialog = new JDialog(frame, title, true); // Nếu parent null thì frame cũng là null

        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(parent); // Vẫn có thể đặt vị trí dialog ở giữa màn hình
        dialog.setLayout(new BorderLayout());

        // Tạo JLabel hiển thị nội dung
        JLabel lblMessage = new JLabel(message, SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        dialog.add(lblMessage, BorderLayout.CENTER);

        // Tạo nút đóng
        JButton btnOK = new JButton("OK");
        btnOK.setFont(new Font("Arial", Font.BOLD, 12));
        btnOK.setBackground(new Color(52, 152, 219)); // Màu xanh dương
        btnOK.setForeground(Color.WHITE);
        btnOK.setFocusPainted(false);
        btnOK.addActionListener(e -> dialog.dispose());

        // Panel chứa nút
        JPanel panel = new JPanel();
        panel.add(btnOK);
        dialog.add(panel, BorderLayout.SOUTH);

        // Hiển thị dialog
        dialog.setVisible(true);
    }

    // Thông báo đơn giản
    public static void alert(Component parent, String message) {
        // Kiểm tra parent để tránh lỗi NullPointerException
        Frame frame = (parent != null) ? (Frame) SwingUtilities.getWindowAncestor(parent) : null;
        JDialog dialog = new JDialog(frame, "Thông báo", true); // true = modal dialog

        // Thiết lập kích thước và vị trí
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(parent);
        dialog.setLayout(new BorderLayout());

        // Tạo JLabel để hiển thị thông báo
        JLabel lblMessage = new JLabel(message, SwingConstants.CENTER);
        lblMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        dialog.add(lblMessage, BorderLayout.CENTER);

        // Tạo nút OK
        JButton btnOK = new JButton("OK");
        btnOK.setFont(new Font("Arial", Font.BOLD, 12));
        btnOK.setBackground(new Color(52, 152, 219)); // Màu xanh dương
        btnOK.setForeground(Color.WHITE);
        btnOK.setFocusPainted(false);
        btnOK.addActionListener(e -> dialog.dispose()); // Đóng dialog khi bấm OK

        // Thêm nút vào panel và dialog
        JPanel panel = new JPanel();
        panel.add(btnOK);
        dialog.add(panel, BorderLayout.SOUTH);

        // Hiển thị dialog
        dialog.setVisible(true);
    }

    // Xác nhận YES/NO
    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    // Hộp thoại nhập dữ liệu
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Nhập liệu", JOptionPane.QUESTION_MESSAGE);
    }

//    public static void alert(Component parent, String message) {
//        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//    }
//
//    public static boolean confirm(Component parent, String message) {
//        int result = JOptionPane.showConfirmDialog(parent, message, "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//        return result == JOptionPane.YES_NO_OPTION;
//    }
//
//    public static String prompt(Component parent, String message) {
//        return JOptionPane.showInputDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//    }
}
