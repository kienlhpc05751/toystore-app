/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toystore.form.store;

import com.sun.mail.handlers.text_html;
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
import com.toystore.utils.BarcodeUtil;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.toystore.utils.MsgBox;
import com.toystore.utils.XImage;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author RAVEN
 */
public class productView extends javax.swing.JPanel {

    public int IndexComBox = 0;
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
    Brand brand = new Brand();
    Age age = new Age();
    Category category = new Category();
    Material material = new Material();

    void chonComboBox(int index, String stype) {
        if (index >= 0) {
            if (stype == "brand") {
                brand = listBrand.get(index);
            } else if (stype == "age") {
                age = listAge.get(index);
            } else if (stype == "category") {
                category = listCategory.get(index);
            } else if (stype == "material") {
                material = listMaterial.get(index);
            } else {
                System.out.println("LỖI chonCombox!");
            }
        }
    }

    //fill dữ liệu lên cobombox cboMaloai
    void fillCombobox() {
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
        categoryModel.addElement("Vui lòng chọn Category!");
        brandModel.addElement("Vui lòng chọn Brand!");
        ageModel.addElement("Vui lòng chọn Age!");
        materialModel.addElement("Vui lòng chọn Material!");
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
        String row[] = {"Mã SP", "Tên SP", "Giá SP", "Lượng SP", "Trạng thái SP", "Bar CODE", "___"};
        DefaultTableModel model = new DefaultTableModel(row, 0);
        model.setRowCount(0);
        listSP = pDAO.getAllProducts();
        Collections.reverse(listSP);  // Đảo ngược danh sách sản phẩm
        for (product itempKH : listSP) {
            model.addRow(new Object[]{itempKH.getProductId(), itempKH.getName(), itempKH.getPrice(), itempKH.getQuantity(), itempKH.isStatus(), itempKH.getBarcode()});
        }
        tblSanPham.setModel(model);
    }
// fill lên from dữ liệu trong l

    void setForm(product p) {
        txtMaSP.setText(String.valueOf(p.getProductId()));
        txtTenSP.setText(p.getName());
        txtGiaBan.setText(String.valueOf(p.getPrice()));
        txtSoLuong.setText(String.valueOf(p.getQuantity()));
        txtMota.setText(p.getDescription());

        if (p.getImage() != null) {
            System.out.println("this is image name !: " + p.getImage());
            lblHinhAnh.setToolTipText(p.getImage());
//            ImageIcon icon = XImage.read(p.getImage());  // Tạo ImageIcon từ hình
            ImageIcon icon = read(p.getImage());
            lblHinhAnh.setIcon(icon);
            // Cập nhật kích thước hình cho lblHinh
            int width = lblHinhAnh.getWidth();
            int height = lblHinhAnh.getHeight();
            if (width > 0 && height > 0) {
                Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                lblHinhAnh.setIcon(new ImageIcon(img));
            }
        } else {
            lblHinhAnh.setIcon(null);
        }
        System.out.println("setfrom :" + p.getCategoryId() + p.getAgeId() + p.getBrandId() + p.getMaterialId());

        String selectedCategory = listCategory.stream()
                .filter(cat -> cat.getCategoryId() == p.getCategoryId()) // Lọc danh mục có ID trùng
                .map(Category::getName) // Lấy tên danh mục
                .findFirst() // Lấy kết quả đầu tiên nếu có
                .orElse(null);  // Nếu không tìm thấy, trả về null
        String selectBrand = listBrand.stream().filter(bra -> bra.getBrandId() == p.getBrandId()).map(Brand::getName).findFirst().orElse("không có");
        String selectedAge = listAge.stream().filter(age -> age.getAgeId() == p.getAgeId()).map(Age::getAgeRange).findFirst().orElse(null);
        String selectedMateria = listMaterial.stream().filter(m -> m.getMaterialId() == p.getMaterialId()).map(Material::getName).findFirst().orElse(null);
        CboCategory.setSelectedItem(selectedCategory);
        CboBrand.setSelectedItem(selectBrand);
        CboAge.setSelectedItem(selectedAge);
        CboMaterial.setSelectedItem(selectedMateria);
        //    txtBarcode.setText(product.getBarcode());
    }

    public static ImageIcon read(String fileName) {
        try {
            File file = new File("src/com/toystore/image/" + fileName); // Kiểm tra đường dẫn
            System.out.println("Đang đọc ảnh từ: " + file.getAbsolutePath()); // Debug
            if (file.exists()) {
                return new ImageIcon(ImageIO.read(file));
            } else {
                System.out.println("File không tồn tại: " + file.getAbsolutePath());
                return new ImageIcon("src/com/toystore/image/noimg.jpg");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu có lỗi
    }
    // lê hoàng kiên 

    product getForm() {
        product product = new product();
        product.setProductId(Integer.parseInt(txtMaSP.getText()));
        product.setName(txtTenSP.getText());
        product.setPrice(Double.parseDouble(txtGiaBan.getText()));
        product.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        product.setImage(lblHinhAnh.getToolTipText());
        product.setDescription(txtMota.getText());
        product.setBarcode("txtBarcode.getText()");
        product.setSex(true);
        product.setStatus(true);
        // Định dạng ngày tháng nếu cần
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        product.setCreatedAt(sdf.format(new Date())); // Gán ngày hiện tại cho sản phẩm
//        product.setCategoryId(1);
//        product.setAgeId(1);
//        product.setBrandId(1);
//        product.setMaterialId(1);
        Object selectedCategory = CboCategory.getSelectedItem();
        listCategory.stream()
                .filter(c -> c.getName().equalsIgnoreCase(selectedCategory.toString()))
                .findFirst()
                .ifPresent(c -> product.setCategoryId(c.getCategoryId()));
        Object selectedBrand = CboBrand.getSelectedItem();
        listBrand.stream()
                .filter(b -> b.getName().equalsIgnoreCase((String) selectedBrand)) // Tìm brand theo tên
                .findFirst() // Lấy brand đầu tiên nếu có
                .ifPresent(b -> product.setBrandId(b.getBrandId())); // Gán thẳng categoryId vào product
        Object selectedAge = CboAge.getSelectedItem();
        listAge.stream()
                .filter(a -> a.getAgeRange().equalsIgnoreCase(selectedAge.toString()))
                .findFirst()
                .ifPresent(a -> product.setAgeId(a.getAgeId()));

        Object selectedMaterial = CboMaterial.getSelectedItem();
        listMaterial.stream()
                .filter(m -> m.getName().equalsIgnoreCase(selectedMaterial.toString()))
                .findFirst()
                .ifPresent(m -> product.setMaterialId(m.getMaterialId()));

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
            product p = getForm();
            p.setBarcode(BarcodeUtil.generateBarcode());
            String code = p.getBarcode();
            p.setUrlBarcode(BarcodeUtil.generateBarcodeImage(p.getBarcode()));
            pDAO.insertProduct(p);
            MsgBox.alert(null, "Thêm sản phẩm  Thành Công !");
            listSP = pDAO.findAll();
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
            listSP = pDAO.findAll();
            fillTable(listSP);
        } catch (Exception e) {
            MsgBox.alert(null, "Cập nhật sản phẩm Thất Bại !");
        }
    }

    void delete() {
        try {
            int MSP = Integer.parseInt(txtMaSP.getText());
            pDAO.deleteProduct(MSP);
            clearForm();
            MsgBox.alert(null, "Xóa sản phẩm Thành Công !");
//            listSP.stream()
//                    .filter(p -> p.getProductId() == MSP)
//                    .findFirst()
//                    .ifPresent(p -> listSP.remove(p));
            // chỉ một máy dùng
            listSP = pDAO.findAll();
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
            product p = pDAO.findById(MSP);
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
//        if (txtKichCo.getText().isEmpty()) {
//            errorMessages.add("Vui lòng nhập kích cở Sản phẩm !");
//            txtKichCo.setBackground(Color.red);
//            txtKichCo.requestFocus();
//        } else {
//            txtKichCo.setBackground(null);  // Reset background to white if not empty
//        }
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

        if (txtMota1.getText().isEmpty()) {
            errorMessages.add("Vui lòng nhập màu !");
            txtMota1.setBackground(Color.red);
            txtMota1.requestFocus();
        } else {
            txtMota1.setBackground(null);  // Reset background to white if not empty
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
        lblMaNV7 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        txtMota1 = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblGiaBan = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        lblMaLoai = new javax.swing.JLabel();
        CboBrand = new javax.swing.JComboBox<>();
        lblSoLuong = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        CboCategory = new javax.swing.JComboBox<>();
        CboAge = new javax.swing.JComboBox<>();
        CboMaterial = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        lblHinhAnh = new javax.swing.JLabel();
        lblMaLoai1 = new javax.swing.JLabel();
        lblMaLoai2 = new javax.swing.JLabel();
        lblMaLoai3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        pnButton = new javax.swing.JPanel();
        btnfirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        searchText1 = new com.toystore.swing.SearchText();
        btnThem1 = new javax.swing.JButton();
        btnList = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        searchText2 = new com.toystore.swing.SearchText();
        btnTimkiem = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(960, 683));

        jPanel1.setMinimumSize(new java.awt.Dimension(1040, 720));
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 680));
        jPanel1.setLayout(new java.awt.BorderLayout());

        pnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        pnUpdate.setMinimumSize(new java.awt.Dimension(1040, 720));
        pnUpdate.setPreferredSize(new java.awt.Dimension(950, 580));
        pnUpdate.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        lblMaSP.setBackground(new java.awt.Color(102, 0, 204));
        lblMaSP.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaSP.setForeground(new java.awt.Color(27, 51, 61));
        lblMaSP.setText("Mã SP");
        jPanel2.add(lblMaSP);
        lblMaSP.setBounds(33, 24, 50, 18);

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaSP.setBorder(null);
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });
        jPanel2.add(txtMaSP);
        txtMaSP.setBounds(33, 45, 246, 29);
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(33, 80, 246, 10);

        lblMaNV1.setBackground(new java.awt.Color(102, 0, 204));
        lblMaNV1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaNV1.setForeground(new java.awt.Color(27, 51, 61));
        lblMaNV1.setText("Tên SP");
        jPanel2.add(lblMaNV1);
        lblMaNV1.setBounds(33, 102, 50, 18);

        lblMaNV7.setBackground(new java.awt.Color(102, 0, 204));
        lblMaNV7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaNV7.setForeground(new java.awt.Color(27, 51, 61));
        lblMaNV7.setText("Mô tả");
        jPanel2.add(lblMaNV7);
        lblMaNV7.setBounds(640, 210, 106, 18);

        txtTenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTenSP.setBorder(null);
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });
        jPanel2.add(txtTenSP);
        txtTenSP.setBounds(33, 126, 242, 29);
        jPanel2.add(jSeparator6);
        jSeparator6.setBounds(33, 155, 247, 10);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(33, 245, 247, 10);

        txtMota1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMota1.setBorder(null);
        txtMota1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMota1ActionPerformed(evt);
            }
        });
        jPanel2.add(txtMota1);
        txtMota1.setBounds(919, 417, 243, 29);
        jPanel2.add(jSeparator5);
        jSeparator5.setBounds(33, 336, 243, 10);

        lblGiaBan.setBackground(new java.awt.Color(102, 0, 204));
        lblGiaBan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblGiaBan.setForeground(new java.awt.Color(27, 51, 61));
        lblGiaBan.setText("Giá bán");
        jPanel2.add(lblGiaBan);
        lblGiaBan.setBounds(33, 267, 80, 18);

        txtGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGiaBan.setBorder(null);
        txtGiaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaBanActionPerformed(evt);
            }
        });
        jPanel2.add(txtGiaBan);
        txtGiaBan.setBounds(33, 303, 243, 29);

        lblMaLoai.setBackground(new java.awt.Color(102, 0, 204));
        lblMaLoai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaLoai.setForeground(new java.awt.Color(27, 51, 61));
        lblMaLoai.setText("Thương hiệu");
        jPanel2.add(lblMaLoai);
        lblMaLoai.setBounds(292, 25, 87, 18);

        CboBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboBrandActionPerformed(evt);
            }
        });
        jPanel2.add(CboBrand);
        CboBrand.setBounds(292, 49, 267, 30);

        lblSoLuong.setBackground(new java.awt.Color(102, 0, 204));
        lblSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSoLuong.setForeground(new java.awt.Color(27, 51, 61));
        lblSoLuong.setText("Số Lượng");
        jPanel2.add(lblSoLuong);
        lblSoLuong.setBounds(33, 183, 80, 18);

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoLuong.setBorder(null);
        jPanel2.add(txtSoLuong);
        txtSoLuong.setBounds(33, 212, 247, 29);
        jPanel2.add(jSeparator7);
        jSeparator7.setBounds(6, 456, 1162, 10);

        CboCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboCategoryActionPerformed(evt);
            }
        });
        jPanel2.add(CboCategory);
        CboCategory.setBounds(290, 310, 267, 30);

        CboAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboAgeActionPerformed(evt);
            }
        });
        jPanel2.add(CboAge);
        CboAge.setBounds(292, 124, 267, 30);

        CboMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CboMaterialActionPerformed(evt);
            }
        });
        jPanel2.add(CboMaterial);
        CboMaterial.setBounds(292, 217, 267, 30);
        jPanel2.add(jSeparator2);
        jSeparator2.setBounds(286, 6, 0, 444);

        lblHinhAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblHinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhAnhMouseClicked(evt);
            }
        });
        jPanel2.add(lblHinhAnh);
        lblHinhAnh.setBounds(640, 30, 267, 161);

        lblMaLoai1.setBackground(new java.awt.Color(102, 0, 204));
        lblMaLoai1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaLoai1.setForeground(new java.awt.Color(27, 51, 61));
        lblMaLoai1.setText("Độ tuổi");
        jPanel2.add(lblMaLoai1);
        lblMaLoai1.setBounds(292, 100, 50, 18);

        lblMaLoai2.setBackground(new java.awt.Color(102, 0, 204));
        lblMaLoai2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaLoai2.setForeground(new java.awt.Color(27, 51, 61));
        lblMaLoai2.setText("Chất liệu");
        jPanel2.add(lblMaLoai2);
        lblMaLoai2.setBounds(292, 178, 68, 18);

        lblMaLoai3.setBackground(new java.awt.Color(102, 0, 204));
        lblMaLoai3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMaLoai3.setForeground(new java.awt.Color(27, 51, 61));
        lblMaLoai3.setText("Loại SP");
        jPanel2.add(lblMaLoai3);
        lblMaLoai3.setBounds(290, 270, 50, 18);

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane2.setViewportView(txtMota);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(640, 240, 370, 94);

        pnUpdate.add(jPanel2);
        jPanel2.setBounds(0, 30, 1111, 380);

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

        searchText1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 51), null));
        searchText1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText1ActionPerformed(evt);
            }
        });

        btnThem1.setBackground(new java.awt.Color(204, 204, 204));
        btnThem1.setText("tìm");
        btnThem1.setBorderPainted(false);
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnButtonLayout = new javax.swing.GroupLayout(pnButton);
        pnButton.setLayout(pnButtonLayout);
        pnButtonLayout.setHorizontalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnfirst, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPrev, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNext, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(btnLast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 111, Short.MAX_VALUE))
        );
        pnButtonLayout.setVerticalGroup(
            pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnButtonLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchText1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfirst, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(149, Short.MAX_VALUE))
        );

        pnUpdate.add(pnButton);
        pnButton.setBounds(6, 418, 1028, 296);

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

        searchText2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 51), null));
        searchText2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchText2ActionPerformed(evt);
            }
        });

        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout btnListLayout = new javax.swing.GroupLayout(btnList);
        btnList.setLayout(btnListLayout);
        btnListLayout.setHorizontalGroup(
            btnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(btnListLayout.createSequentialGroup()
                        .addComponent(searchText2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimkiem)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        btnListLayout.setVerticalGroup(
            btnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimkiem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
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

    private void txtMota1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMota1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMota1ActionPerformed

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
        IndexComBox = CboBrand.getSelectedIndex() - 1;
        System.out.println("CboBrandActionPerformed");

        System.out.println(IndexComBox);
        chonComboBox(IndexComBox, stype);
    }//GEN-LAST:event_CboBrandActionPerformed

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void CboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboCategoryActionPerformed
        // TODO add your handling code here:
        String stype = "category";
        IndexComBox = CboCategory.getSelectedIndex() - 1;
        System.out.println("CboCategoryActionPerformed");

        System.out.println(IndexComBox);
        chonComboBox(IndexComBox, stype);
    }//GEN-LAST:event_CboCategoryActionPerformed

    private void CboAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboAgeActionPerformed
        String stype = "age";
        IndexComBox = CboAge.getSelectedIndex() - 1;
        System.out.println("CboAgeActionPerformed");

        System.out.println(IndexComBox);
        chonComboBox(IndexComBox, stype);
    }//GEN-LAST:event_CboAgeActionPerformed

    private void CboMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboMaterialActionPerformed
        String stype = "material";
        IndexComBox = CboMaterial.getSelectedIndex() - 1;
        System.out.println("CboMaterialActionPerformed");

        System.out.println(IndexComBox);
        chonComboBox(IndexComBox, stype);
    }//GEN-LAST:event_CboMaterialActionPerformed

    private void lblHinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhAnhMouseClicked

//        JFileChooser fileChooser = new JFileChooser();
//        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            XImage.save(file);
//            ImageIcon img = XImage.read(file.getName());
//            Image img2 = XImage.resize(img.getImage(), lblHinhAnh.getWidth(), lblHinhAnh.getHeight());
//            ImageIcon img3 = new ImageIcon(img2);
//            lblHinhAnh.setIcon(img3);
//            lblHinhAnh.setToolTipText(file.getName());
//        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg", "gif"));

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getName();
            File destination = new File("src/com/toystore/image/", fileName);

            // Nếu ảnh chưa tồn tại thì sao chép vào thư mục
            if (!destination.exists()) {
                try {
                    Files.copy(selectedFile.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                    MsgBox.alert(this, "Lỗi khi sao chép ảnh!");
                    return;
                }
            }
            // Hiển thị ảnh lên JLabel
            ImageIcon img = new ImageIcon(destination.getAbsolutePath());
            Image resizedImg = img.getImage().getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
            lblHinhAnh.setIcon(new ImageIcon(resizedImg));
            lblHinhAnh.setToolTipText(fileName); // Lưu tên file vào tooltip để lưu vào DB
        }
    }//GEN-LAST:event_lblHinhAnhMouseClicked

    private void txtGiaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaBanActionPerformed

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText1ActionPerformed

    private void searchText2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText2ActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimkiemActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        seacher();
    }//GEN-LAST:event_btnThem1ActionPerformed


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
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnfirst;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblMaLoai;
    private javax.swing.JLabel lblMaLoai1;
    private javax.swing.JLabel lblMaLoai2;
    private javax.swing.JLabel lblMaLoai3;
    private javax.swing.JLabel lblMaNV1;
    private javax.swing.JLabel lblMaNV7;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnButton;
    private javax.swing.JPanel pnUpdate;
    private com.toystore.swing.SearchText searchText1;
    private com.toystore.swing.SearchText searchText2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtMota1;
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
