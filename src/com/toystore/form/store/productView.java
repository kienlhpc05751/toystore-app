/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toystore.form.store;

import com.toystore.form.*;
import com.toystore.dao.LoaiSpDAO;
import com.toystore.dao.SanPhamDao;
import com.toystore.dao.store.AgeDAO;
import com.toystore.dao.store.BrandDAO;
import com.toystore.dao.store.CategoryDAO;
import com.toystore.dao.store.MaterialDAO;
import com.toystore.dao.store.productDAO;
import com.toystore.model.LoaiSanPham;
import com.toystore.model.Sanpham;
import com.toystore.model.store.Age;
import com.toystore.model.store.Brand;
import com.toystore.model.store.Category;
import com.toystore.model.store.Material;
import com.toystore.model.store.product;
import com.toystore.utils.Auth;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.toystore.utils.MsgBox;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author RAVEN
 */
public class productView extends javax.swing.JPanel {

    public static int Index = 0;
    String masp = null;
    int row = 0;

    productDAO pDAO = new productDAO();
    List<product> listSP = new ArrayList<>();
    CategoryDAO categoryDAO = new CategoryDAO();
    List<Category> listCategory = new ArrayList<>();
    BrandDAO brandDAO = new BrandDAO();
    List<Brand> listBrand = new ArrayList<>();
    AgeDAO ageDAO = new AgeDAO();
    List<Age> listAge = new ArrayList<>();
    MaterialDAO materialDAO = new MaterialDAO();
    List<Material> listMaterial = new ArrayList<>();

    //        listBrand = brandDAO.selectAll();
//        listAge = ageDAO.selectAll();
//        listMaterial = materialDAO.selectAll();
//    List<Sanpham> listSP = new ArrayList<>();
    LoaiSpDAO daosp = new LoaiSpDAO() {
    };
    List<LoaiSanPham> listLSP = new ArrayList<>();

    public productView() {
        initComponents();
        Int();
        fillTable(listSP);
    }

    public void Int() {
        fillCombobox();
    }

    void chonComboBox(int index) {
        if (index >= 0) {
            LoaiSanPham cd = listLSP.get(index);
        }
    }

    void chonComboBox(int index, String stype) {
        if (index >= 0) {
            if (stype == "brand") {
                Brand brand = listBrand.get(index);
            } else if (stype == "age") {
                Age age = listAge.get(index);
            } else if (stype == "category") {
                Category category = listCategory.get(index);
            } else {
                Material material = listMaterial.get(index);
            }
        }
    }

    //fill dữ liệu lên cobombox cboMaloai
    void fillCombobox() {

//        listLSP = daosp.selectAll();
//        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) CboMaloai.getModel();
//        cboModel.removeAllElements();
//        cboModel.addElement("vui lòng chọn !");
//        for (LoaiSanPham itempCD : listLSP) {
//            
//            cboModel.addElement(itempCD.getMaloaiSP());
//        }
        listCategory = categoryDAO.findAll();
        listBrand = brandDAO.findAll();
        listAge = ageDAO.findAll();
        listMaterial = materialDAO.findAll();
//
        DefaultComboBoxModel categoryModel = (DefaultComboBoxModel) CboCategory.getModel();
        DefaultComboBoxModel brandModel = (DefaultComboBoxModel) CboBrand.getModel();
        DefaultComboBoxModel ageModel = (DefaultComboBoxModel) CboAge.getModel();
        DefaultComboBoxModel materialModel = (DefaultComboBoxModel) CboMaterial.getModel();
//
        categoryModel.removeAllElements();
        brandModel.removeAllElements();
        ageModel.removeAllElements();
        materialModel.removeAllElements();
//
        categoryModel.addElement("Vui lòng chọn!");
        brandModel.addElement("Vui lòng chọn!");
        ageModel.addElement("Vui lòng chọn!");
        materialModel.addElement("Vui lòng chọn!");

        for (Category category : listCategory) {
            categoryModel.addElement(category.getName());
        }
        for (Brand brand : listBrand) {
            brandModel.addElement(brand.getName());
        }
        for (Age age : listAge) {
            ageModel.addElement(age.getAgeRange());
        }
        for (Material material : listMaterial) {
            materialModel.addElement(material.getName());
        }
    }
    // fill dữ liệu lên bảng

    void fillTable(List<product> list) {
        String row[] = {"Mã SP", "Tên SP", "Giá SP", "Lượng SP", "Trạng thái SP", "Bar CODE", "gia ban", "soLuong"};
        DefaultTableModel model = new DefaultTableModel(row, 0);
        model.setRowCount(0);
        listSP = pDAO.getAllProducts();
        for (product itempKH : listSP) {
            model.addRow(new Object[]{itempKH.getAgeId(), itempKH.getName(), itempKH.getPrice(), itempKH.getQuantity(), itempKH.isStatus(), itempKH.getBarcode()});
        }
        tblSanPham.setModel(model);
    }
// fill lên from dữ liệu trong l

    void setForm(product p) {
        txtTenSP.setText(p.getName());
        txtGiaBan.setText(String.valueOf(p.getPrice()));
        txtSoLuong.setText(String.valueOf(p.getQuantity()));
        txtMota.setText(p.getDescription());
//    txtBarcode.setText(product.getBarcode());
//    CboCategory.setSelectedItem(product.getCategoryId());
//    CboBrand.setSelectedItem(product.getBrandId());
//    CboAge.setSelectedItem(product.getAgeId());
//    CboMaterial.setSelectedItem(product.getMaterialId());
    }

    product getForm() {
        product product = new product();
        product.setName(txtTenSP.getText());
        product.setPrice(Double.parseDouble(txtGiaBan.getText()));
        product.setQuantity(Integer.parseInt(txtSoLuong.getText()));
//    product.setDescription(txtMota.getText());
//    product.setBarcode(txtBarcode.getText());
//    
//    Object selectedCategory = CboCategory.getSelectedItem();
//    product.setCategoryId(selectedCategory != null ? Integer.parseInt(selectedCategory.toString()) : 0);
//    
//    Object selectedBrand = CboBrand.getSelectedItem();
//    product.setBrandId(selectedBrand != null ? Integer.parseInt(selectedBrand.toString()) : 0);
//    
//    Object selectedAge = CboAge.getSelectedItem();
//    product.setAgeId(selectedAge != null ? Integer.parseInt(selectedAge.toString()) : 0);
//    
//    Object selectedMaterial = CboMaterial.getSelectedItem();
//    product.setMaterialId(selectedMaterial != null ? Integer.parseInt(selectedMaterial.toString()) : 0);

        return product;
    }

    void edit(int index) {
        product kh = listSP.get(index);
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
        if (this.row > tblSanPham.getRowCount() - 2) {
            return;
        } else {
            this.row++;
            this.edit(row);
//            System.out.println(row);
        }
    }

    void last() {
        this.row = tblSanPham.getRowCount() - 1;
        this.edit(row);
    }

    void insert() {

        try {
            pDAO.insertProduct(getForm());
            MsgBox.alert(null, "Thêm sản phẩm  Thành Công !");
            fillTable(listSP);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Thêm sản phẩm Thất Bại !");
        }
//        if (checkFrom()) {
//            try {
////                MsgBox.alert(null, "Thêm Khóa Học Thành Công");
//                daokh.insert(getForm());
////                fillTable(listKH);
////                  listCD = daoCD.selectAll();
//                   fillTable(listKH);
//            } catch (Exception e) {
//                e.printStackTrace();
//                MsgBox.alert(null, "Thêm Khóa Học Thất Bại");
//            }
//        }
    }

    void update() {
        try {
            pDAO.updateProduct(getForm());
            MsgBox.alert(null, "Cập nhật sản phẩm Thành Công !");
            fillTable(listSP);
        } catch (Exception e) {
            MsgBox.alert(null, "Cập nhật sản phẩm Thất Bại !");
        }
    }

    void delete() {
        try {
//            pDAO.deleteProduct(String.valueOf(masp));
            MsgBox.alert(null, "Xóa sản phẩm Thành Công !");
            fillTable(listSP);
        } catch (Exception e) {
            MsgBox.alert(null, "Xóa sản phẩm Thất Bại !");
        }
//        try {
//            if (!Auth.isManager()) {
//                MsgBox.alert(null, "Bạn Không Có Quyền Xóa Khóa Học");
//                return;
//            } else {
//                MsgBox.alert(null, "Delete Khóa Học Thành Công");
//                daokh.delete(String.valueOf(maKH));
//            }
//        } catch (Exception e) {
//            MsgBox.alert(null, "Delete Khóa Học Thất Bại");
//        }
    }

    void clearForm() {
        product product = new product();
        this.setForm(product);
        fillCombobox();
        this.row = -1;
        this.updateStatus();
        txtTenSP.setBackground(null);
        txtGiaBan.setBackground(null);
        txtSoLuong.setBackground(null);
//    txtMota.setBackground(null);
//    txtBarcode.setBackground(null);

    }

    void updateStatus() {
        boolean edit = (this.row >= 0);
        boolean first = (this.row == 0);
        boolean last = (this.row == tblSanPham.getRowCount() - 1);
//        txtMaNV.setEditable(!edit);
//        btnThem.setEnabled(!edit);
//        btnSua.setEnabled(edit);
//        btnXoa.setEnabled(edit);
//        btnFrist.setEnabled(edit && !first);
//        btnPrev.setEnabled(edit && !first);
//        btnNext.setEnabled(edit && !last);
//        btnLast.setEnabled(edit && !last);
    }

    public boolean checkFrom() {
//          String ma = txtMaNV
        // List to store error messages
        List<String> errorMessages = new ArrayList<>();

        // có thể thay đổi
        if (txtKichCo.getText().isEmpty()) {
            errorMessages.add("Vui lòng nhập kích cở Sản phẩm !");
            txtKichCo.setBackground(Color.red);
            txtKichCo.requestFocus();
        } else {
            txtKichCo.setBackground(null);  // Reset background to white if not empty
        }

        if (txtMaSP.getText().isEmpty()) {
            errorMessages.add("Vui lòng nhập mã đăng nhập SP !");
            txtMaSP.setBackground(Color.red);
            txtMaSP.requestFocus();
        } else if (txtMaSP.getText().length() > 10) {
            errorMessages.add("Vui lòng nhập dưới 10 kí tự !");
            txtMaSP.setBackground(Color.red);
            txtMaSP.requestFocus();
        } else {
            txtMaSP.setBackground(null);  // Reset background to white if not empty
        }

        if (txtMota.getText().isEmpty()) {
            errorMessages.add("Vui lòng nhập màu !");
            txtMota.setBackground(Color.red);
            txtMota.requestFocus();
        } else {
            txtMota.setBackground(null);  // Reset background to white if not empty
        }

        if (txtTenSP.getText().isEmpty()) {
            errorMessages.add("Vui lòng nhập tên san phẩm !");
            txtTenSP.setBackground(Color.red);
            txtTenSP.requestFocus();
        } else {
            txtTenSP.setBackground(null);  // Reset background to white if not empty
        }

        try {

            int soluong = Integer.parseInt(txtSoLuong.getText());
            txtTenSP.setBackground(null);  // Reset background to white if not empty

            if (soluong <= 0) {
                txtSoLuong.setBackground(Color.BLUE);
                errorMessages.add("vui lòng nhập số lượng lớn hơn 0 !");

            } else {
                txtSoLuong.setBackground(null);
            }

        } catch (NumberFormatException e) {
            // Handle the exception (e.g., display an error message or log it)
            errorMessages.add("vui long nhập số lượng !");
            txtSoLuong.setBackground(Color.red);
            txtSoLuong.requestFocus();
        }

        try {
            double gia = Double.parseDouble(txtGiaBan.getText());
            if (gia <= 0) {
                txtGiaBan.setBackground(Color.red);
                errorMessages.add("vui lòng nhập giá lớn hơn 0 !");
            } else {
                txtGiaBan.setBackground(null);
            }
        } catch (NumberFormatException e) {
            errorMessages.add("vui long nhập giá !");
            txtGiaBan.setBackground(Color.red);
            txtGiaBan.requestFocus();
        }

        int d = CboBrand.getSelectedIndex();
        System.out.println("chọn:" + d);
        if (d != 0) {
//            row = CboMaloai.getSelectedIndex() - 1;

        } else {
            errorMessages.add("vui lòng chọn loại sản phẩm !");

        }

        // Check if there were any errors
        if (!errorMessages.isEmpty()) {
            // Display error messages
            StringBuilder errorMessage = new StringBuilder("Lỗi:\n");
            for (String error : errorMessages) {
                errorMessage.append("- ").append(error).append("\n");
            }
            MsgBox.alert(this, errorMessage.toString());

            // Return false indicating errors
            return false;
        }

        // Return true if no errors
        return true;
    }

    public boolean CheckMa() {
        List<String> list = new ArrayList<>();
        for (product p : listSP) {
            if (txtMaSP.getText() == String.valueOf(p.getProductId())) {
                list.add("Mã Sp đã có");
                txtMaSP.setBackground(Color.blue);
            } else {
                txtMaSP.setBackground(null);
            }
        }
        if (!list.isEmpty()) {
            // Display error messages
            StringBuilder errorMessage = new StringBuilder("Lỗi:\n");
            for (String error : list) {
                errorMessage.append("- ").append(error).append("\n");
            }
            MsgBox.alert(this, errorMessage.toString());

            // Return false indicating errors
            return false;
        }
        return true;
    }

//    
//  00002202708
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        pnUpdate = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblMaSP = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblMaNV1 = new javax.swing.JLabel();
        lblMaNV6 = new javax.swing.JLabel();
        lblMaNV7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        txtKichCo = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        txtMota = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblGiaBan = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblMaLoai = new javax.swing.JLabel();
        CboBrand = new javax.swing.JComboBox<>();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        CboCategory = new javax.swing.JComboBox<>();
        CboAge = new javax.swing.JComboBox<>();
        CboMaterial = new javax.swing.JComboBox<>();
        pnButton = new javax.swing.JPanel();
        btnfirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        lblTitle = new javax.swing.JLabel();

        jPanel1.setLayout(new java.awt.BorderLayout());

        pnUpdate.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        lblMaSP.setBackground(new java.awt.Color(102, 0, 204));
        lblMaSP.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaSP.setForeground(new java.awt.Color(27, 51, 61));
        lblMaSP.setText("Mã SP");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSP.setBorder(null);
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });

        lblMaNV1.setBackground(new java.awt.Color(102, 0, 204));
        lblMaNV1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaNV1.setForeground(new java.awt.Color(27, 51, 61));
        lblMaNV1.setText("Tên KH");

        lblMaNV6.setBackground(new java.awt.Color(102, 0, 204));
        lblMaNV6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaNV6.setForeground(new java.awt.Color(27, 51, 61));
        lblMaNV6.setText("Kích Cở");

        lblMaNV7.setBackground(new java.awt.Color(102, 0, 204));
        lblMaNV7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaNV7.setForeground(new java.awt.Color(27, 51, 61));
        lblMaNV7.setText("Mô tả");

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSP.setBorder(null);
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        txtKichCo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtKichCo.setBorder(null);

        txtMota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMota.setBorder(null);
        txtMota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMotaActionPerformed(evt);
            }
        });

        lblGiaBan.setBackground(new java.awt.Color(102, 0, 204));
        lblGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblGiaBan.setForeground(new java.awt.Color(27, 51, 61));
        lblGiaBan.setText("Giá bán");

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaBan.setBorder(null);

        lblMaLoai.setBackground(new java.awt.Color(102, 0, 204));
        lblMaLoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaLoai.setForeground(new java.awt.Color(27, 51, 61));
        lblMaLoai.setText("MaLoai");

        CboBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboBrandActionPerformed(evt);
            }
        });

        lblSoLuong.setBackground(new java.awt.Color(102, 0, 204));
        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(27, 51, 61));
        lblSoLuong.setText("Số Lượng");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.setBorder(null);

        CboCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboCategoryActionPerformed(evt);
            }
        });

        CboAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboAgeActionPerformed(evt);
            }
        });

        CboMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboMaterialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaNV6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(241, 241, 241))
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator7)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaNV7, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CboMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(CboAge, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(531, 531, 531))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblMaSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNV1)
                    .addComponent(lblMaLoai))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMaNV6)
                            .addComponent(CboBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CboAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNV7)
                    .addComponent(CboMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMota, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblGiaBan)
                .addGap(1, 1, 1)
                .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSoLuong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnButton.setBackground(new java.awt.Color(255, 255, 255));
        pnButton.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnfirst.setBackground(new java.awt.Color(204, 204, 204));
        btnfirst.setText("|<");
        btnfirst.setBorderPainted(false);
        btnfirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstActionPerformed(evt);
            }
        });

        btnPrev.setBackground(new java.awt.Color(204, 204, 204));
        btnPrev.setText("<<");
        btnPrev.setBorderPainted(false);
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setText(">>");
        btnNext.setBorderPainted(false);
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setBackground(new java.awt.Color(204, 204, 204));
        btnLast.setText(">|");
        btnLast.setBorderPainted(false);
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnThem.setBackground(new java.awt.Color(204, 204, 204));
        btnThem.setText("Thêm");
        btnThem.setBorderPainted(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setText("Sửa");
        btnSua.setBorderPainted(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setText("Xóa");
        btnXoa.setBorderPainted(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(204, 204, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnButtonLayout = new javax.swing.GroupLayout(pnButton);
        pnButton.setLayout(pnButtonLayout);
        pnButtonLayout.setHorizontalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnfirst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnButtonLayout.setVerticalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnfirst))
                .addGap(27, 27, 27)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua)
                    .addComponent(btnPrev))
                .addGap(23, 23, 23)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoa)
                    .addComponent(btnNext))
                .addGap(28, 28, 28)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi)
                    .addComponent(btnLast))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnUpdateLayout = new javax.swing.GroupLayout(pnUpdate);
        pnUpdate.setLayout(pnUpdateLayout);
        pnUpdateLayout.setHorizontalGroup(
            pnUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUpdateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(pnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnUpdateLayout.setVerticalGroup(
            pnUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUpdateLayout.createSequentialGroup()
                .addGroup(pnUpdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnUpdateLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(pnButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnUpdateLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        tabs.addTab("CẬP NHẬT", pnUpdate);

        tblSanPham.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblSanPham.setFont(new java.awt.Font("UTM BryantLG", 1, 14)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", " Giá SP", "Trang Thái SP", "Bar CODE"
            }
        ));
        tblSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblSanPham.setGridColor(new java.awt.Color(204, 204, 204));
        tblSanPham.setRowHeight(30);
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        javax.swing.GroupLayout btnListLayout = new javax.swing.GroupLayout(btnList);
        btnList.setLayout(btnListLayout);
        btnListLayout.setHorizontalGroup(
            btnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnListLayout.setVerticalGroup(
            btnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnListLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        tabs.addTab("DANH SÁCH", btnList);

        jPanel1.add(tabs, java.awt.BorderLayout.CENTER);

        lblTitle.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(102, 102, 102));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("QUẢN LÝ SẢN PHẨM");
        lblTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(lblTitle, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseReleased

    }//GEN-LAST:event_tblSanPhamMouseReleased

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (checkFrom()) {
            delete();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        if (Auth.isManager()) {
            if (checkFrom()) {
                update();
            }
        } else {
            MsgBox.alert(null, "Bạn không có quyền cập nhật !");
        }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (Auth.isLogin()) {
            if (CheckMa()) {
                if (checkFrom()) {
                    insert();
                }
            }
        } else {
            MsgBox.alert(null, "Bạn không có quyền  thêm sp !");

        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
//        this.index = tblSanPham.getRowCount() - 1;
//        this.edit();
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
//        this.index++;
//        this.edit();
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
//        this.index--;
//        this.edit();
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnfirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstActionPerformed
//        this.index = 0;
//        this.edit();
        first();
    }//GEN-LAST:event_btnfirstActionPerformed

    private void txtMotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMotaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        CboBrand.setSelectedItem(2);
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
//        if (evt.getClickCount() == 2) {
//            this.index = tblSanPham.rowAtPoint(evt.getPoint());
//            if (this.index >= 0) {
//                this.edit();
//                tabs.setSelectedIndex(0);
//                
//            }
//        }
        this.row = tblSanPham.getSelectedRow();
        this.edit(row);
        tabs.setSelectedIndex(0);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed

    private void CboBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboBrandActionPerformed
        // TODO add your handling code here:
        String stype = "brand";
        Index = CboBrand.getSelectedIndex() - 1;
        System.out.println("CboBrandActionPerformed");

        System.out.println(Index);
        chonComboBox(row, stype);
    }//GEN-LAST:event_CboBrandActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void CboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboCategoryActionPerformed
        // TODO add your handling code here:
        String stype = "category";
        Index = CboCategory.getSelectedIndex() - 1;
        System.out.println("CboCategoryActionPerformed");

        System.out.println(Index);
        chonComboBox(Index, stype);
    }//GEN-LAST:event_CboCategoryActionPerformed

    private void CboAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboAgeActionPerformed
        String stype = "age";
        Index = CboAge.getSelectedIndex() - 1;
        System.out.println("CboAgeActionPerformed");

        System.out.println(Index);
        chonComboBox(Index, stype);
    }//GEN-LAST:event_CboAgeActionPerformed

    private void CboMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboMaterialActionPerformed
        String stype = "age";
        Index = CboMaterial.getSelectedIndex() - 1;
        System.out.println("CboMaterialActionPerformed");

        System.out.println(Index);
        chonComboBox(Index, stype);
    }//GEN-LAST:event_CboMaterialActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboAge;
    private javax.swing.JComboBox<String> CboBrand;
    private javax.swing.JComboBox<String> CboCategory;
    private javax.swing.JComboBox<String> CboMaterial;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JPanel btnList;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnfirst;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblMaLoai;
    private javax.swing.JLabel lblMaNV1;
    private javax.swing.JLabel lblMaNV6;
    private javax.swing.JLabel lblMaNV7;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnUpdate;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtKichCo;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMota;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
//    int index = -1; // vị trí của khách hàng đang hiển thị trên form
//    KhachHangDao khDao = new KhachHangDao();
//
//    void load() {
//        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
//        model.setRowCount(0);
//        try {
//            List<KhachHang> list = khDao.selectAll();
//            for (KhachHang kh : list) {
//                Object[] row = {
//                    kh.getMaKH(),
//                    kh.getTenKH(),
//                    kh.getEmail(),
//                    kh.getSDT(),
//                    kh.getDiaChi()
//                };
//                model.addRow(row);
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Lỗi truy vấn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            System.err.println("Lỗi truy vấn: " + e.getMessage());
//        }
//    }
//
//    void edit() {
//        try {
//            Integer makh = (Integer) tblSanPham.getValueAt(this.index, 0);
//            KhachHang model = khDao.findByID(makh);
//            if (model != null) {
//                this.setModel(model);
////                tabs.setSelectedIndex(0);
//                this.setStatus(false);
//            }
//        } catch (Exception e) {
//            MsgBox.alert(this, "Lỗi truy vấn!");
//            System.err.println("Lỗi truy vấn: " + e.getMessage());
//        }
//    }
//
//    void setModel(KhachHang model) {
//        txtMaSP.setText(String.valueOf(model.getMaKH()));
//        txtTenSP.setText(model.getTenKH());
//        txtKichCo.setText(model.getEmail());
//        txtMauSac.setText(model.getSDT());
//        txtGiaBan.setText(model.getDiaChi());
//    }
//    
//    KhachHang getModel() {
//        KhachHang model = new KhachHang();
//        model.setMaKH(Integer.valueOf(txtMaSP.getText()));
//        model.setTenKH(txtTenSP.getText());
//        model.setEmail(txtKichCo.getText());
//        model.setSDT(txtMauSac.getText());
//        model.setDiaChi(txtGiaBan.getText());
//        return model;
//    }
//
//    void setStatus(boolean insertable) {
//        txtMaSP.setEditable(insertable);
//        btnThem.setEnabled(insertable);
//        btnSua.setEnabled(!insertable);
//        btnXoa.setEnabled(!insertable);
//
//        boolean first = this.index > 0;
//        boolean last = this.index < tblSanPham.getRowCount() - 1;
//        btnfirst.setEnabled(!insertable && first);
//        btnPrev.setEnabled(!insertable && first);
//        btnNext.setEnabled(!insertable && last);
//        btnLast.setEnabled(!insertable && last);
//    }
//
//    void insert() {
//       if (!validator()) {
//            return;
//        }
//
//        KhachHang nv = getModel();
//
//        if (nv == null) {
//            return;
//        }
//
//        KhachHangDao.getInstant().insert(nv);
//        MsgBox.alert(this, "Thêm thành công " + " " + nv.getTenKH());
//        clear();
//        load();
//
//    }
//
//    void update() {
//        if (!validator()) {
//            return;
//        }
//
//        KhachHang kh = getModel();
//
//        if (kh == null) {
//            return;
//        }
//
//        KhachHangDao.getInstant().update(kh);
//        MsgBox.alert(this, "Sửa thành công " + " " + kh.getTenKH());
//        clear();
//
////        this.dispose();
//        load();
//
//    }
//
//    void delete() {
//        if (MsgBox.confirm(this, "Ban that su muon xoa nhan vien nay khong?")) {
//           Integer maKh = Integer.valueOf(txtMaSP.getText());
//            try {
//                khDao.delete(maKh);
//                this.load();
//                this.clear();
//                MsgBox.alert(this, "Xoa thanh cong!");
//            } catch (Exception e) {
//                MsgBox.alert(this, "Xoa that bai!");
//            }
//        }
////
////int[] list = tblNhanVien.getSelectedRows();
////
////        if (list.length <= 0) {
////            return;
////        }
////
////        boolean check = MsgBox.confirm(this, "Điều này sẽ làm mất đi " + list.length + " nhân viên của cửa hàng đó >.<");
////
////        if (!check) {
////            tblNhanVien.clearSelection();
////            return;
////        }
////
////        for (int selectedRow : list) {
////            String id = (String) tblNhanVien.getValueAt(selectedRow, 1);
////            if (id.endsWith(Auth.user.getMaNV())) {
////                MsgBox.alert(this, "Không thể xóa tài khoản đang đăng nhập !");
////                return;
////            }
////            NhanVienDao.getInstant().delete(id);
////
////        }
////        load();
////        tblNhanVien.clearSelection();
//    }
//
//    void clear() {
//        this.setModel(new KhachHang());
//        this.setStatus(true);
//        this.index = -1;
//    }
//
//    private boolean validator() {
//        boolean flag = true;
//        String mess = "";
//
//        if (Validator.isEmpty(txtTenSP)) {
//            mess += "Bạn chưa nhập tên cho khách hàng \n";
//            flag = false;
//        } else {
//            if (!Validator.isValidName(txtTenSP.getText())) {
//                mess += "Tên không hợp lệ \n";
//                flag = false;
//            }
//        }
//
//        if (Validator.isEmpty(txtMauSac)) {
//            mess += "Bạn chưa nhập số điện thoại cho khách hàng \n";
//            flag = false;
//        } else {
//            if (!Validator.isTel(txtMauSac.getText())) {
//                mess += "Số điện thoại không hợp lệ \n";
//                flag = false;
//            }
//        }
//        if (Validator.isEmpty(txtKichCo)) {
//            mess += "Bạn chưa nhập email cho khách hàng \n";
//            flag = false;
//        } else {
//            if (!Validator.isEmail(txtKichCo.getText())) {
//                mess += "Email không hợp lệ \n";
//                flag = false;
//            }
//        }
//
//        return flag;
//    }
}
