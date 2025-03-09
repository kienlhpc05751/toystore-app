/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.swing.jframe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
class POSPanel extends javax.swing.JPanel {
    private JTable cartTable;
    private DefaultTableModel cartTableModel;
    private JTextField searchField;
    private JLabel totalPriceLabel;
    
    public POSPanel() {
        setLayout(new BorderLayout());
        
        // Top panel - Search bar
        JPanel topPanel = new JPanel(new BorderLayout());
        searchField = new JTextField("Nhập mã barcode hoặc tên hàng hóa (F2)");
        topPanel.add(searchField, BorderLayout.CENTER);
        
        // Tabbed Pane for multiple invoices
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Hóa đơn 1", createCartPanel());
        
        // Product panel - Placeholder for product list
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(2, 5, 10, 10));
        for (int i = 0; i < 10; i++) {
            JButton productButton = new JButton("Sản phẩm " + (i + 1));
            productPanel.add(productButton);
        }
        
        // Main layout
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tabbedPane, productPanel);
        splitPane.setDividerLocation(600);
        
        add(topPanel, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);
    }
    
    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Cart table
        cartTableModel = new DefaultTableModel(new String[]{"Hàng hóa", "SL", "Đơn giá", "Thành tiền"}, 0);
        cartTable = new JTable(cartTableModel);
        panel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        
        // Payment panel
        JPanel paymentPanel = new JPanel(new GridLayout(3, 2));
        paymentPanel.add(new JLabel("Tiền hàng: "));
        totalPriceLabel = new JLabel("0 VND");
        paymentPanel.add(totalPriceLabel);
        paymentPanel.add(new JLabel("Giảm giá: "));
        paymentPanel.add(new JTextField("0"));
        paymentPanel.add(new JLabel("Thanh toán: "));
        paymentPanel.add(new JLabel("0 VND"));
        
        panel.add(paymentPanel, BorderLayout.SOUTH);
        return panel;
    }
}
