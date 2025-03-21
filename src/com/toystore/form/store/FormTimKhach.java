/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.form.store;

/**
 *
 * @author Asus
 */
import com.toystore.dao.store.AccountDAO;
import com.toystore.model.store.Account;
import com.toystore.utils.MsgBox;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FormTimKhach extends JDialog {
    
    private JTable table;
    private JTextField txtSoDienThoai;
    private JButton btnTimKiem, btnChon;
    private DefaultTableModel model;
    private String selectedCustomer;
    private List<Account> list;  // Danh sách khách hàng
    private AccountDAO accountDAO = new AccountDAO();
    
    public FormTimKhach(Frame parent) {
        super(parent, "Tìm Khách Hàng", true);
        setSize(500, 400);
        setLayout(new BorderLayout());
        setBackground(Color.decode("AAD3FF"));

        // Panel tìm kiếm
        JPanel panelSearch = new JPanel();
        panelSearch.add(new JLabel("Số điện thoại:"));
        txtSoDienThoai = new JTextField(15);
        panelSearch.add(txtSoDienThoai);
        btnTimKiem = new JButton("Tìm");
        panelSearch.add(btnTimKiem);
        add(panelSearch, BorderLayout.NORTH);

        // Bảng danh sách khách hàng
        model = new DefaultTableModel(new Object[]{"Tên Khách", "Số Điện Thoại"}, 0);
        this.list = accountDAO.findAll();
        
        for (Account object : list) {
            model.addRow(new Object[]{object.getFullname(), object.getPhoneNumber()});
        }
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Nút chọn khách hàng
        btnChon = new JButton("Chọn");
        add(btnChon, BorderLayout.SOUTH);

        // Xử lý sự kiện tìm kiếm khách hàng
        btnTimKiem.addActionListener(e -> timKiemKhach());

        // Xử lý sự kiện chọn khách hàng
        btnChon.addActionListener(e -> chonKhach());

        // Sự kiện double-click để chọn khách nhanh
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    chonKhach();
                }
            }
        });
        
        setLocationRelativeTo(parent);
    }

    // Giả lập dữ liệu tìm kiếm (bạn có thể thay bằng database)
    private void timKiemKhach() {
        String sdt = txtSoDienThoai.getText();
        model.setRowCount(0); // Xóa dữ liệu cũ
        boolean found = false;
        
        for (Account acc : list) {
            if (acc.getPhoneNumber().contains(sdt)) {  // Kiểm tra nếu số điện thoại chứa chuỗi nhập vào
                model.addRow(new Object[]{acc.getFullname(), acc.getPhoneNumber()});
                found = true;
            }
        }
        
        if (!found) {
            MsgBox.alert(this, "Không tìm thấy khách hàng có số điện thoại: " + sdt);
        }
    }
    
    private void chonKhach() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            selectedCustomer = (String) table.getValueAt(row, 1);
            dispose(); // Đóng form sau khi chọn
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng!");
        }
    }
    
    public String getSelectedCustomer() {
        return selectedCustomer;
    }
}
