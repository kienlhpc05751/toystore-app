package com.toystore.swing;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class ButtonRendererEditor extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private final JButton button;
    private JTable table;
    private int row;

    public ButtonRendererEditor() {
        button = new JButton("Xử lý");

        // Sự kiện khi bấm vào nút
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table != null) {
                    Object productName = table.getValueAt(row, 1); // Lấy tên sản phẩm
                    Object quantity = table.getValueAt(row, 2); // Lấy số lượng
                    JOptionPane.showMessageDialog(null,
                            "Bạn đã nhấn xử lý sản phẩm: " + productName + "\nSố lượng: " + quantity);

                    // Ví dụ: Cập nhật giá trị trong bảng
                    table.setValueAt("Đã xử lý", row, 4);
                }
                fireEditingStopped(); // Kết thúc chỉnh sửa
            }
        });
    }

    // Renderer: Hiển thị nút
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return button;
    }

    // Editor: Xử lý khi click vào nút
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.table = table;
        this.row = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "Xử lý";
    }
}
