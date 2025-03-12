/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.toystore.form.store;

import com.toystore.dao.store.CategoryDAO;
import com.toystore.dao.store.SuperCategoryDAO;
import com.toystore.model.StatusType;
import com.toystore.model.store.Category;
import com.toystore.model.store.SuperCategory;
import com.toystore.swing.ButtonRendererEditor;
import com.toystore.utils.MsgBox;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Asus
 */
public class productAttributesView extends javax.swing.JPanel {

    public int IndexComBox = 0;
    public int row = 0;

    /**
     * Creates new form categoryView
     */
    public productAttributesView() {
        initComponents();
        Init();
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        tableCategory.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        categoryList = categoryDAO.findAll();
        fillTable(categoryList);
    }

    public void Init() {
        fillCombobox();
    }
    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> categoryList = new ArrayList<>();

    void fillTable(List<Category> categoryList) {
        String row[] = {"Mã SP", "Tên SP", "Giá SP", "Lượng SP", "Trạng thái SP", "Bar CODE", "___"};
        DefaultTableModel model = new DefaultTableModel(row, 0);
        model.setRowCount(0);
//        DefaultTableModel model = new DefaultTableModel();

        // Thêm các cột
        model.setColumnIdentifiers(new Object[]{"ID", "Name Category", "Descriptions", "Trạng Thái"});
        table.setModel(model);

        // Thêm dữ liệu mẫu
        for (Category category : this.categoryList) {
            model.addRow(new Object[]{category.getCategoryId(),
                category.getName(),
                category.getCreatedAt(),
                category.getDescription(), //                StatusType.PENDING
        });
        }
        // Áp dụng Renderer & Editor cho cột nút
//        table.getColumnModel().getColumn(4).setCellRenderer(new ButtonRendererEditor());
//        table.getColumnModel().getColumn(4).setCellEditor(new ButtonRendererEditor());
        table.setModel(model);
    }

    List<SuperCategory> listSuperCategorys = new ArrayList<>();
    SuperCategory superCategory = new SuperCategory();
    SuperCategoryDAO superCategoryDAO = new SuperCategoryDAO();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    void chonComboBox(int index) {
        if (index >= 0) {
            SuperCategory cd = listSuperCategorys.get(index);
        }
    }

    void fillCombobox() {
        listSuperCategorys = superCategoryDAO.findAll();
        DefaultComboBoxModel SuperCategoryModel = (DefaultComboBoxModel) CboSuperCategory.getModel();
        SuperCategoryModel.removeAllElements();
        SuperCategoryModel.addElement("Category please!");
        for (SuperCategory superCategory : listSuperCategorys) {
            SuperCategoryModel.addElement(superCategory.getName());
        }
    }

    Category getForm() {
        Category category = new Category();
        category.setCategoryId(Integer.parseInt(txtCategoryID.getText()));
        category.setName(txtCategoryName1.getText());
        category.setDescription(txtCategoryDescription.getText());
        Object selectedCategory = CboSuperCategory.getSelectedItem();
        category.setCreatedAt(sdf.format(new Date()));
        listSuperCategorys.stream()
                .filter(c -> c.getName().equalsIgnoreCase(selectedCategory.toString()))
                .findFirst()
                .ifPresent(c -> category.setSuperCategoryId(c.getSuperCategoryId()));
        category.toString();
        return category;
    }

    void setForm(Category category) {
        txtCategoryID.setText(String.valueOf(category.getCategoryId()));
        txtCategoryName1.setText(category.getName());
        txtCategoryDescription.setText(category.getDescription());
        jTextAreaDescription.setText(category.getDescription());
        String selectedSuperCategory = listSuperCategorys.stream()
                .filter(cat -> cat.getSuperCategoryId() == category.getSuperCategoryId()) // Lọc danh mục có ID trùng
                .map(SuperCategory::getName) // Lấy tên danh mục
                .findFirst() // Lấy kết quả đầu tiên nếu có
                .orElse(null);  // Nếu không tìm thấy, trả về null
        CboSuperCategory.setSelectedItem(selectedSuperCategory);

    }

    void edit(int index) {
        Category kh = categoryList.get(index);
        setForm(kh);
    }

    void first() {
        this.row = 0;
        this.edit(row);
    }

    void prev() {
        if (this.row < 1) {
            return;
        } else {
            this.row--;
            this.edit(row);
        }
    }

    void next() {
        if (this.row > table.getRowCount() - 2) {
            return;
        } else {
            this.row++;
            this.edit(row);
        }
    }

    void insert() {
        try {
            Category category = getForm();
            System.out.println("name category: " + category.getName());
            System.out.println("name category descriptions: " + category.getDescription());
            System.out.println("name category descriptions: " + category.getSuperCategoryId());
            categoryDAO.insertCategory(category);
            MsgBox.alert(null, "Thêm sản phẩm  Thành Công !");
            categoryList = categoryDAO.findAll();
            fillTable(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Thêm sản phẩm Thất Bại !");
        }
    }

    void update() {
        try {
            categoryDAO.updateCategory(getForm());
            MsgBox.alert(null, "Cập nhật sản phẩm Thành Công !");
            categoryList = categoryDAO.findAll();
            fillTable(categoryList);
        } catch (Exception e) {
            MsgBox.alert(null, "Cập nhật sản phẩm Thất Bại !");
        }
    }

    void delete() {
        try {
            int categoryID = Integer.parseInt(txtCategoryID.getText());
            categoryDAO.deleteCategory(categoryID);
            clearForm();
            MsgBox.alert(null, "Xóa sản phẩm Thành Công !");
            categoryList = categoryDAO.findAll();
            fillTable(categoryList);
        } catch (Exception e) {
            MsgBox.alert(null, "Xóa sản phẩm Thất Bại !");
        }
    }

    void last() {
        this.row = table.getRowCount() - 1;
        this.edit(row);
    }

    void seacher() {
        String input = searchText1.getText().trim(); // Lấy giá trị nhập vào và loại bỏ khoảng trắng
        if (input.isEmpty()) {
            MsgBox.alert(null, "Vui lòng nhập mã sản phẩm!");
            return;
        }
        if (!input.matches("\\d+")) { // Kiểm tra xem input có phải là số không
            MsgBox.alert(null, "Mã sản phẩm phải là số!");
            return;
        }
        try {
            int MSP = Integer.parseInt(input); // Chuyển chuỗi thành số nguyên
            Category p = categoryDAO.findById(MSP);
            if (p != null) {
                setForm(p);
            } else {
                MsgBox.alert(null, "Không tìm thấy sản phẩm với mã: " + MSP);
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(null, "Lỗi chuyển đổi số! Vui lòng nhập mã hợp lệ.");
        }
    }

    void clearForm() {
        Category category = new Category();
        this.setForm(category);
        fillCombobox();
        this.row = -1;
//        this.updateStatus();
        txtCategoryID.setBackground(null);
        txtCategoryID.setBackground(null);
        txtCategoryName1.setBackground(null);
        txtCategoryDescription.setBackground(null);
        jTextAreaDescription.setBackground(null);
//    txtMota.setBackground(null);
//    txtBarcode.setBackground(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnButton = new javax.swing.JPanel();
        btCategory = new javax.swing.JTabbedPane();
        tabCategory = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        CboSuperCategory = new javax.swing.JComboBox<>();
        txtCategoryID = new javax.swing.JTextField();
        txtCategoryDescription = new javax.swing.JTextField();
        txtCategoryName1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaDescription = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        btnThem1 = new javax.swing.JButton();
        searchText1 = new com.toystore.swing.SearchText();
        btnfirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        tableCategory = new javax.swing.JScrollPane();
        table = new com.toystore.swing.Table();
        panelBorder1 = new com.toystore.swing.PanelBorder();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(391, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(858, Short.MAX_VALUE))
        );

        pnButton.setBackground(new java.awt.Color(255, 255, 255));
        pnButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout pnButtonLayout = new javax.swing.GroupLayout(pnButton);
        pnButton.setLayout(pnButtonLayout);
        pnButtonLayout.setHorizontalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );
        pnButtonLayout.setVerticalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        setMinimumSize(new java.awt.Dimension(1040, 720));
        setPreferredSize(new java.awt.Dimension(960, 683));

        tabCategory.setLayout(null);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        CboSuperCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboSuperCategoryActionPerformed(evt);
            }
        });

        txtCategoryID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryIDActionPerformed(evt);
            }
        });

        txtCategoryDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryDescriptionActionPerformed(evt);
            }
        });

        txtCategoryName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryName1ActionPerformed(evt);
            }
        });

        jTextAreaDescription.setColumns(20);
        jTextAreaDescription.setRows(5);
        jScrollPane1.setViewportView(jTextAreaDescription);

        jLabel2.setText("ID");

        jLabel3.setText("Name");

        jLabel4.setText("Description");

        jLabel5.setText("Super Category");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoryName1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoryDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboSuperCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CboSuperCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCategoryDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCategoryName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabCategory.add(jPanel5);
        jPanel5.setBounds(10, 20, 500, 180);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        label2.setText("controller");
        jPanel6.add(label2);
        label2.setBounds(255, 390, 52, 20);
        label2.getAccessibleContext().setAccessibleDescription("");

        btnThem1.setBackground(new java.awt.Color(204, 204, 204));
        btnThem1.setText("tìm");
        btnThem1.setBorderPainted(false);
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnThem1);
        btnThem1.setBounds(255, 8, 80, 33);

        searchText1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 51), null));
        searchText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText1ActionPerformed(evt);
            }
        });
        jPanel6.add(searchText1);
        searchText1.setBounds(94, 8, 149, 33);

        btnfirst.setBackground(new java.awt.Color(204, 204, 204));
        btnfirst.setText("|<");
        btnfirst.setBorderPainted(false);
        btnfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstActionPerformed(evt);
            }
        });
        jPanel6.add(btnfirst);
        btnfirst.setBounds(10, 130, 100, 30);

        btnPrev.setBackground(new java.awt.Color(204, 204, 204));
        btnPrev.setText("<<");
        btnPrev.setBorderPainted(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        jPanel6.add(btnPrev);
        btnPrev.setBounds(120, 130, 90, 30);

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setText(">>");
        btnNext.setBorderPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel6.add(btnNext);
        btnNext.setBounds(220, 130, 90, 30);

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel6.add(btnThem);
        btnThem.setBounds(10, 80, 100, 30);

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel6.add(btnSua);
        btnSua.setBounds(120, 80, 90, 30);

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel6.add(btnXoa);
        btnXoa.setBounds(320, 80, 90, 30);

        btnLast.setBackground(new java.awt.Color(204, 204, 204));
        btnLast.setText(">|");
        btnLast.setBorderPainted(false);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel6.add(btnLast);
        btnLast.setBounds(320, 130, 90, 30);

        btnLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel6.add(btnLamMoi);
        btnLamMoi.setBounds(220, 80, 90, 30);

        tabCategory.add(jPanel6);
        jPanel6.setBounds(520, 20, 490, 180);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        tableCategory.setViewportView(table);

        tabCategory.add(tableCategory);
        tableCategory.setBounds(10, 210, 1000, 410);

        btCategory.addTab("Category", tabCategory);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1036, Short.MAX_VALUE)
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 685, Short.MAX_VALUE)
        );

        btCategory.addTab("tab2", panelBorder1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btCategory)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        row = table.getSelectedRow();
        this.edit(row);
//        tabs.setSelectedIndex(0);
        System.out.println("lỏ" + row);
    }//GEN-LAST:event_tableMouseClicked

    private void CboSuperCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboSuperCategoryActionPerformed

        String stype = "category";
        IndexComBox = CboSuperCategory.getSelectedIndex() - 1;
        System.out.println("CboCategoryActionPerformed");
        System.out.println(IndexComBox);
        chonComboBox(IndexComBox);
    }//GEN-LAST:event_CboSuperCategoryActionPerformed

    private void txtCategoryIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryIDActionPerformed

    private void txtCategoryDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryDescriptionActionPerformed

    private void txtCategoryName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryName1ActionPerformed

    private void btnfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstActionPerformed
        //        this.index = 0;
        //        this.edit();
        first();
    }//GEN-LAST:event_btnfirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        //        this.index--;
        //        this.edit();
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        //        this.index++;
        //        this.edit();
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
//        this.index = tblSanPham.getRowCount() - 1;
//        this.edit();
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
//        if (Auth.isLogin()) {
//            if (CheckMa()) {
//                if (checkFrom()) {
        insert();
//                }
//            }
//        } else {
//            MsgBox.alert(null, "Bạn không có quyền  thêm sp !");
//
//        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

//        if (Auth.isManager()) {
//            if (checkFrom()) {
        update();
//            }
//        } else {
//            MsgBox.alert(null, "Bạn không có quyền cập nhật !");
//        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
//        if (checkFrom()) {
        delete();
//        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        CboSuperCategory.setSelectedItem(2);
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        seacher();
    }//GEN-LAST:event_btnThem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboSuperCategory;
    private javax.swing.JTabbedPane btCategory;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnfirst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaDescription;
    private java.awt.Label label2;
    private com.toystore.swing.PanelBorder panelBorder1;
    private javax.swing.JPanel pnButton;
    private com.toystore.swing.SearchText searchText1;
    private javax.swing.JPanel tabCategory;
    private com.toystore.swing.Table table;
    private javax.swing.JScrollPane tableCategory;
    private javax.swing.JTextField txtCategoryDescription;
    private javax.swing.JTextField txtCategoryID;
    private javax.swing.JTextField txtCategoryName1;
    // End of variables declaration//GEN-END:variables
}
