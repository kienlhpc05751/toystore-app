/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.toystore.form.store;

import com.toystore.dao.store.AgeDAO;
import com.toystore.dao.store.BrandDAO;
import com.toystore.dao.store.CategoryDAO;
import com.toystore.dao.store.MaterialDAO;
import com.toystore.dao.store.SuperCategoryDAO;
import com.toystore.model.StatusType;
import com.toystore.model.store.Age;
import com.toystore.model.store.Brand;
import com.toystore.model.store.Category;
import com.toystore.model.store.Material;
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
    List<Brand> brandList = new ArrayList<>();
    BrandDAO brandDAO = new BrandDAO();

    public productAttributesView() {
        initComponents();
        Init();
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        tableCategory.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        categoryList = categoryDAO.findAll();
        fillTable(categoryList);

        brandList = brandDAO.findAll();
        fillTableBrand(brandList);

        materialList = materialDAO.findAll();
        fillTableMaterial(materialList);

        ageList = ageDAO.findAll();
        fillTableAge(ageList);
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
        txtCategoryDescription.setText(category.getDescription());
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

    void last() {
        this.row = table.getRowCount() - 1;
        this.edit(row);
    }

    void insert() {
        try {
            Category category = getForm();
            System.out.println("name category: " + category.getName());
            System.out.println("name category descriptions: " + category.getDescription());
            System.out.println("name category descriptions: " + category.getSuperCategoryId());
            categoryDAO.insertCategory(category);
            MsgBox.alert(null, "Add Category successfully!");
            categoryList = categoryDAO.findAll();
            fillTable(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Add category failed !");
        }
    }

    void update() {
        try {
            boolean update
                    = categoryDAO.updateCategory(getForm());
            if (update) {
                MsgBox.alert(null, "Category deleted successfully !");

            } else {
                MsgBox.alert(null, "Category FOREIGN KEY !");

            }
            categoryList = categoryDAO.findAll();
            fillTable(categoryList);
        } catch (Exception e) {
            MsgBox.alert(null, "Category deleted failed!");
        }
    }

    void delete() {
        try {
            int categoryID = Integer.parseInt(txtCategoryID.getText());
            boolean delete = categoryDAO.deleteCategory(categoryID);

            if (delete) {
                MsgBox.alert(null, "Category deleted successfully !");
            } else {
                MsgBox.alert(null, "Category FOREIGN KEY !");
            }
            clearForm();
            categoryList = categoryDAO.findAll();
            fillTable(categoryList);
        } catch (Exception e) {
            MsgBox.alert(null, "Category deleted failed");
        }
    }

    void seacher() {
        String input = searchText1.getText().trim(); // Lấy giá trị nhập vào và loại bỏ khoảng trắng
        if (input.isEmpty()) {
            MsgBox.alert(null, "Please enter category code!");
            return;
        }
        if (!input.matches("\\d+")) { // Kiểm tra xem input có phải là số không
            MsgBox.alert(null, "Category code must be a number!");
            return;
        }
        try {
            int MSP = Integer.parseInt(input); // Chuyển chuỗi thành số nguyên
            Category p = categoryDAO.findById(MSP);
            if (p != null) {
                setForm(p);
            } else {
                MsgBox.alert(null, "Do not search with code " + MSP);
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(null, "Number conversion error! Please enter a valid code.");
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
        txtCategoryDescription.setBackground(null);
//    txtMota.setBackground(null);
//    txtBarcode.setBackground(null);

    }

    // brand
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
        JTablePane = new javax.swing.JTabbedPane();
        tabCategory = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        CboSuperCategory = new javax.swing.JComboBox<>();
        txtCategoryID = new javax.swing.JTextField();
        txtCategoryName1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCategoryDescription = new javax.swing.JTextArea();
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
        tabBrand = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        txtBrandID = new javax.swing.JTextField();
        txtOriginBrand = new javax.swing.JTextField();
        txtBrandName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescriptionBrand = new javax.swing.JTextArea();
        lblBrandID = new javax.swing.JLabel();
        lblBrandName = new javax.swing.JLabel();
        lblDescriptionBrand = new javax.swing.JLabel();
        lblOriginBrand = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnSeacherBrand = new javax.swing.JButton();
        txtSeacherBrand = new com.toystore.swing.SearchText();
        btnfirstBrand = new javax.swing.JButton();
        btnPrevBrand = new javax.swing.JButton();
        btnNextBrand = new javax.swing.JButton();
        btnAddBrand = new javax.swing.JButton();
        btnUpdateBrand = new javax.swing.JButton();
        btnDeleteBrand = new javax.swing.JButton();
        btnLastBrand = new javax.swing.JButton();
        btnNewBrand = new javax.swing.JButton();
        tableCategory1 = new javax.swing.JScrollPane();
        tableBrand = new com.toystore.swing.Table();
        tabMaterial = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        txtMaterialID = new javax.swing.JTextField();
        txtMaterialName = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMaterialDescription = new javax.swing.JTextArea();
        lblBrandID1 = new javax.swing.JLabel();
        lblBrandName1 = new javax.swing.JLabel();
        lblDescriptionBrand1 = new javax.swing.JLabel();
        btnAddMaterial = new javax.swing.JButton();
        btnUpdateMaterial = new javax.swing.JButton();
        btnNewMaterial = new javax.swing.JButton();
        btnDeleteMaterial = new javax.swing.JButton();
        btnfirstMaterial = new javax.swing.JButton();
        btnPrevMaterial = new javax.swing.JButton();
        btnNextMaterial = new javax.swing.JButton();
        btnLastMaterial = new javax.swing.JButton();
        txtSeacherMaterial = new com.toystore.swing.SearchText();
        btnSeacherMaterial = new javax.swing.JButton();
        JScrollPane = new javax.swing.JScrollPane();
        tableMaterial = new com.toystore.swing.Table();
        JScrollPane1 = new javax.swing.JScrollPane();
        tableAge = new com.toystore.swing.Table();
        jPanel11 = new javax.swing.JPanel();
        txtAgeID = new javax.swing.JTextField();
        txtAgeName = new javax.swing.JTextField();
        lblBrandID2 = new javax.swing.JLabel();
        lblBrandName2 = new javax.swing.JLabel();
        btnAddAge = new javax.swing.JButton();
        btnUpdateAge = new javax.swing.JButton();
        btnNewAge = new javax.swing.JButton();
        btnDeleteAge = new javax.swing.JButton();
        btnfirstAge = new javax.swing.JButton();
        btnPrevAge = new javax.swing.JButton();
        btnNextAge = new javax.swing.JButton();
        btnLastAge = new javax.swing.JButton();
        txtSeacherAge = new com.toystore.swing.SearchText();
        btnSeacherAge = new javax.swing.JButton();

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

        setBackground(new java.awt.Color(170, 211, 255));
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(1040, 720));
        setPreferredSize(new java.awt.Dimension(960, 683));

        JTablePane.setBackground(new java.awt.Color(2, 63, 129));
        JTablePane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTablePaneMouseClicked(evt);
            }
        });

        tabCategory.setBackground(new java.awt.Color(170, 211, 255));
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

        txtCategoryName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCategoryName1ActionPerformed(evt);
            }
        });

        txtCategoryDescription.setColumns(20);
        txtCategoryDescription.setRows(5);
        jScrollPane1.setViewportView(txtCategoryDescription);

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
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtCategoryID, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(txtCategoryName1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(CboSuperCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(206, 206, 206))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCategoryName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CboSuperCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        tabCategory.add(jPanel5);
        jPanel5.setBounds(10, 20, 500, 190);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        label2.setText("controller");
        jPanel6.add(label2);
        label2.setBounds(255, 390, 52, 20);
        label2.getAccessibleContext().setAccessibleDescription("");

        btnThem1.setBackground(new java.awt.Color(204, 204, 204));
        btnThem1.setText("Seacher");
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
        btnThem.setText("Add");
        btnThem.setBorderPainted(false);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel6.add(btnThem);
        btnThem.setBounds(10, 80, 100, 30);

        btnSua.setBackground(new java.awt.Color(204, 204, 204));
        btnSua.setText("Update");
        btnSua.setBorderPainted(false);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel6.add(btnSua);
        btnSua.setBounds(120, 80, 90, 30);

        btnXoa.setBackground(new java.awt.Color(204, 204, 204));
        btnXoa.setText("Delete");
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
        btnLamMoi.setText("Refresh");
        btnLamMoi.setBorderPainted(false);
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });
        jPanel6.add(btnLamMoi);
        btnLamMoi.setBounds(220, 80, 90, 30);

        tabCategory.add(jPanel6);
        jPanel6.setBounds(520, 20, 500, 190);

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
        tableCategory.setBounds(10, 230, 1010, 450);

        JTablePane.addTab("Category", tabCategory);

        tabBrand.setLayout(null);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtBrandID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrandIDActionPerformed(evt);
            }
        });

        txtOriginBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOriginBrandActionPerformed(evt);
            }
        });

        txtBrandName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBrandNameActionPerformed(evt);
            }
        });

        txtDescriptionBrand.setColumns(20);
        txtDescriptionBrand.setRows(5);
        jScrollPane2.setViewportView(txtDescriptionBrand);

        lblBrandID.setText("Code Brand :");

        lblBrandName.setText("Name Brand :");

        lblDescriptionBrand.setText("Description Brand :");

        lblOriginBrand.setText("OriginBrand :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtBrandName)
                    .addComponent(lblBrandID)
                    .addComponent(lblBrandName)
                    .addComponent(lblOriginBrand, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOriginBrand)
                    .addComponent(txtBrandID, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblDescriptionBrand)
                        .addGap(183, 183, 183))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(16, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBrandID)
                    .addComponent(lblDescriptionBrand))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(txtBrandID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblBrandName)
                        .addGap(8, 8, 8)
                        .addComponent(txtBrandName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(lblOriginBrand)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOriginBrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tabBrand.add(jPanel7);
        jPanel7.setBounds(10, 20, 500, 190);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(null);

        btnSeacherBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnSeacherBrand.setText("Seacher");
        btnSeacherBrand.setBorderPainted(false);
        btnSeacherBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeacherBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnSeacherBrand);
        btnSeacherBrand.setBounds(255, 8, 80, 33);

        txtSeacherBrand.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 51), null));
        txtSeacherBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeacherBrandActionPerformed(evt);
            }
        });
        jPanel8.add(txtSeacherBrand);
        txtSeacherBrand.setBounds(94, 8, 149, 33);

        btnfirstBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnfirstBrand.setText("|<");
        btnfirstBrand.setBorderPainted(false);
        btnfirstBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnfirstBrand);
        btnfirstBrand.setBounds(10, 130, 100, 30);

        btnPrevBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnPrevBrand.setText("<<");
        btnPrevBrand.setBorderPainted(false);
        btnPrevBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnPrevBrand);
        btnPrevBrand.setBounds(120, 130, 90, 30);

        btnNextBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnNextBrand.setText(">>");
        btnNextBrand.setBorderPainted(false);
        btnNextBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnNextBrand);
        btnNextBrand.setBounds(220, 130, 90, 30);

        btnAddBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnAddBrand.setText("Add");
        btnAddBrand.setBorderPainted(false);
        btnAddBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnAddBrand);
        btnAddBrand.setBounds(10, 80, 100, 30);

        btnUpdateBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdateBrand.setText("Update");
        btnUpdateBrand.setBorderPainted(false);
        btnUpdateBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnUpdateBrand);
        btnUpdateBrand.setBounds(120, 80, 90, 30);

        btnDeleteBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnDeleteBrand.setText("Delete");
        btnDeleteBrand.setBorderPainted(false);
        btnDeleteBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnDeleteBrand);
        btnDeleteBrand.setBounds(320, 80, 90, 30);

        btnLastBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnLastBrand.setText(">|");
        btnLastBrand.setBorderPainted(false);
        btnLastBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnLastBrand);
        btnLastBrand.setBounds(320, 130, 90, 30);

        btnNewBrand.setBackground(new java.awt.Color(204, 204, 204));
        btnNewBrand.setText("Refresh");
        btnNewBrand.setBorderPainted(false);
        btnNewBrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewBrandActionPerformed(evt);
            }
        });
        jPanel8.add(btnNewBrand);
        btnNewBrand.setBounds(220, 80, 90, 30);

        tabBrand.add(jPanel8);
        jPanel8.setBounds(520, 20, 490, 190);

        tableBrand.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBrand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBrandMouseClicked(evt);
            }
        });
        tableCategory1.setViewportView(tableBrand);

        tabBrand.add(tableCategory1);
        tableCategory1.setBounds(10, 230, 1000, 390);

        JTablePane.addTab("Brand", tabBrand);

        tabMaterial.setLayout(null);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMaterialID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaterialIDActionPerformed(evt);
            }
        });

        txtMaterialName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaterialNameActionPerformed(evt);
            }
        });

        txtMaterialDescription.setColumns(20);
        txtMaterialDescription.setRows(5);
        jScrollPane3.setViewportView(txtMaterialDescription);

        lblBrandID1.setText("Code Material :");

        lblBrandName1.setText("Name Material :");

        lblDescriptionBrand1.setText("Description Material :");

        btnAddMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnAddMaterial.setText("Add");
        btnAddMaterial.setBorderPainted(false);
        btnAddMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddMaterialActionPerformed(evt);
            }
        });

        btnUpdateMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdateMaterial.setText("Update");
        btnUpdateMaterial.setBorderPainted(false);
        btnUpdateMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMaterialActionPerformed(evt);
            }
        });

        btnNewMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnNewMaterial.setText("Refresh");
        btnNewMaterial.setBorderPainted(false);
        btnNewMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewMaterialActionPerformed(evt);
            }
        });

        btnDeleteMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnDeleteMaterial.setText("Delete");
        btnDeleteMaterial.setBorderPainted(false);
        btnDeleteMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteMaterialActionPerformed(evt);
            }
        });

        btnfirstMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnfirstMaterial.setText("|<");
        btnfirstMaterial.setBorderPainted(false);
        btnfirstMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstMaterialActionPerformed(evt);
            }
        });

        btnPrevMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnPrevMaterial.setText("<<");
        btnPrevMaterial.setBorderPainted(false);
        btnPrevMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevMaterialActionPerformed(evt);
            }
        });

        btnNextMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnNextMaterial.setText(">>");
        btnNextMaterial.setBorderPainted(false);
        btnNextMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextMaterialActionPerformed(evt);
            }
        });

        btnLastMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnLastMaterial.setText(">|");
        btnLastMaterial.setBorderPainted(false);
        btnLastMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastMaterialActionPerformed(evt);
            }
        });

        txtSeacherMaterial.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 51), null));
        txtSeacherMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeacherMaterialActionPerformed(evt);
            }
        });

        btnSeacherMaterial.setBackground(new java.awt.Color(204, 204, 204));
        btnSeacherMaterial.setText("tìm");
        btnSeacherMaterial.setBorderPainted(false);
        btnSeacherMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeacherMaterialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaterialID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaterialName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBrandID1)
                    .addComponent(lblBrandName1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescriptionBrand1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnfirstMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNewMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btnPrevMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNextMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSeacherMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSeacherMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(btnLastMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBrandID1)
                    .addComponent(lblDescriptionBrand1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(txtMaterialID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(lblBrandName1)
                        .addGap(27, 27, 27)
                        .addComponent(txtMaterialName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfirstMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLastMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSeacherMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSeacherMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        tabMaterial.add(jPanel9);
        jPanel9.setBounds(10, 20, 480, 290);

        JScrollPane.setBackground(new java.awt.Color(255, 255, 255));

        tableMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableMaterial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMaterialMouseClicked(evt);
            }
        });
        JScrollPane.setViewportView(tableMaterial);

        tabMaterial.add(JScrollPane);
        JScrollPane.setBounds(10, 330, 480, 340);

        tableAge.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableAge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAgeMouseClicked(evt);
            }
        });
        JScrollPane1.setViewportView(tableAge);

        tabMaterial.add(JScrollPane1);
        JScrollPane1.setBounds(510, 330, 480, 340);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtAgeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeIDActionPerformed(evt);
            }
        });

        txtAgeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeNameActionPerformed(evt);
            }
        });

        lblBrandID2.setText("Code Age :");

        lblBrandName2.setText("Targer Age Group :");

        btnAddAge.setBackground(new java.awt.Color(204, 204, 204));
        btnAddAge.setText("Add Age");
        btnAddAge.setBorderPainted(false);
        btnAddAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAgeActionPerformed(evt);
            }
        });

        btnUpdateAge.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdateAge.setText("Update Age");
        btnUpdateAge.setBorderPainted(false);
        btnUpdateAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateAgeActionPerformed(evt);
            }
        });

        btnNewAge.setBackground(new java.awt.Color(204, 204, 204));
        btnNewAge.setText("Refresh Age");
        btnNewAge.setBorderPainted(false);
        btnNewAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewAgeActionPerformed(evt);
            }
        });

        btnDeleteAge.setBackground(new java.awt.Color(204, 204, 204));
        btnDeleteAge.setText("Delete Age");
        btnDeleteAge.setBorderPainted(false);
        btnDeleteAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAgeActionPerformed(evt);
            }
        });

        btnfirstAge.setBackground(new java.awt.Color(204, 204, 204));
        btnfirstAge.setText("|<");
        btnfirstAge.setBorderPainted(false);
        btnfirstAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnfirstAgeActionPerformed(evt);
            }
        });

        btnPrevAge.setBackground(new java.awt.Color(204, 204, 204));
        btnPrevAge.setText("<<");
        btnPrevAge.setBorderPainted(false);
        btnPrevAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevAgeActionPerformed(evt);
            }
        });

        btnNextAge.setBackground(new java.awt.Color(204, 204, 204));
        btnNextAge.setText(">>");
        btnNextAge.setBorderPainted(false);
        btnNextAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextAgeActionPerformed(evt);
            }
        });

        btnLastAge.setBackground(new java.awt.Color(204, 204, 204));
        btnLastAge.setText(">|");
        btnLastAge.setBorderPainted(false);
        btnLastAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastAgeActionPerformed(evt);
            }
        });

        txtSeacherAge.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 51, 51), null));
        txtSeacherAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSeacherAgeActionPerformed(evt);
            }
        });

        btnSeacherAge.setBackground(new java.awt.Color(204, 204, 204));
        btnSeacherAge.setText("Seacher");
        btnSeacherAge.setBorderPainted(false);
        btnSeacherAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeacherAgeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBrandID2)
                            .addComponent(txtAgeID, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblBrandName2)
                            .addComponent(txtAgeName, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnfirstAge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddAge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateAge, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNewAge, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteAge, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnPrevAge, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnNextAge, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtSeacherAge, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSeacherAge, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(btnLastAge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBrandID2)
                    .addComponent(lblBrandName2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAgeName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAgeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNewAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnfirstAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPrevAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNextAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLastAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSeacherAge, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSeacherAge, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        tabMaterial.add(jPanel11);
        jPanel11.setBounds(510, 20, 480, 290);

        JTablePane.addTab("Material and Target Age Group ", tabMaterial);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JTablePane, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JTablePane)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void JTablePaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTablePaneMouseClicked
        // TODO add your handling code here:
        row = 0;  // rest
    }//GEN-LAST:event_JTablePaneMouseClicked

    private void btnSeacherAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeacherAgeActionPerformed
        // TODO add your handling code here:
        seacherAge();
    }//GEN-LAST:event_btnSeacherAgeActionPerformed

    private void txtSeacherAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeacherAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeacherAgeActionPerformed

    private void btnLastAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastAgeActionPerformed
        // TODO add your handling code here:
        lastAge();
    }//GEN-LAST:event_btnLastAgeActionPerformed

    private void btnNextAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextAgeActionPerformed
        // TODO add your handling code here:
        nextAge();
    }//GEN-LAST:event_btnNextAgeActionPerformed

    private void btnPrevAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevAgeActionPerformed
        // TODO add your handling code here:
        prevAge();
    }//GEN-LAST:event_btnPrevAgeActionPerformed

    private void btnfirstAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstAgeActionPerformed
        // TODO add your handling code here:
        firstAge();
    }//GEN-LAST:event_btnfirstAgeActionPerformed

    private void btnDeleteAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAgeActionPerformed
        // TODO add your handling code here:
        deleteAge();
    }//GEN-LAST:event_btnDeleteAgeActionPerformed

    private void btnNewAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewAgeActionPerformed
        // TODO add your handling code here:
        clearFormAge();
    }//GEN-LAST:event_btnNewAgeActionPerformed

    private void btnUpdateAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateAgeActionPerformed
        // TODO add your handling code here:
        updateAge();
    }//GEN-LAST:event_btnUpdateAgeActionPerformed

    private void btnAddAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAgeActionPerformed
        // TODO add your handling code here:
        insertAge();
    }//GEN-LAST:event_btnAddAgeActionPerformed

    private void txtAgeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeNameActionPerformed

    private void txtAgeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAgeIDActionPerformed

    private void tableAgeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAgeMouseClicked
        // TODO add your handling code here:
        row = tableAge.getSelectedRow();
        this.editAge(row);
    }//GEN-LAST:event_tableAgeMouseClicked

    private void tableMaterialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMaterialMouseClicked
        // TODO add your handling code here:
        row = tableMaterial.getSelectedRow();
        this.editMaterial(row);
        //        tabs.setSelectedIndex(0);
        System.out.println("lỏ" + row);
    }//GEN-LAST:event_tableMaterialMouseClicked

    private void btnSeacherMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeacherMaterialActionPerformed
        // TODO add your handling code here:
        seacherMaterial();
    }//GEN-LAST:event_btnSeacherMaterialActionPerformed

    private void txtSeacherMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeacherMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeacherMaterialActionPerformed

    private void btnLastMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastMaterialActionPerformed
        // TODO add your handling code here:
        lastMaterial();
    }//GEN-LAST:event_btnLastMaterialActionPerformed

    private void btnNextMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextMaterialActionPerformed
        // TODO add your handling code here:
        nextMaterial();
    }//GEN-LAST:event_btnNextMaterialActionPerformed

    private void btnPrevMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevMaterialActionPerformed
        // TODO add your handling code here:
        firstMateril();
    }//GEN-LAST:event_btnPrevMaterialActionPerformed

    private void btnfirstMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstMaterialActionPerformed
        // TODO add your handling code here:
        firstMateril();
    }//GEN-LAST:event_btnfirstMaterialActionPerformed

    private void btnDeleteMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteMaterialActionPerformed
        // TODO add your handling code here:
        deleteMaterial();
    }//GEN-LAST:event_btnDeleteMaterialActionPerformed

    private void btnNewMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewMaterialActionPerformed
        // TODO add your handling code here:
        clearFormMaterial();
    }//GEN-LAST:event_btnNewMaterialActionPerformed

    private void btnUpdateMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMaterialActionPerformed
        // TODO add your handling code here:
        updateMaterial();
    }//GEN-LAST:event_btnUpdateMaterialActionPerformed

    private void btnAddMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddMaterialActionPerformed
        // TODO add your handling code here:
        insertMaterial();
    }//GEN-LAST:event_btnAddMaterialActionPerformed

    private void txtMaterialNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaterialNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaterialNameActionPerformed

    private void txtMaterialIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaterialIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaterialIDActionPerformed

    private void tableBrandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBrandMouseClicked
        // TODO add your handling code here:
        row = tableBrand.getSelectedRow();
        this.editBrand(row);
        //        tabs.setSelectedIndex(0);
        System.out.println("lỏ" + row);
    }//GEN-LAST:event_tableBrandMouseClicked

    private void btnNewBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewBrandActionPerformed
        // TODO add your handling code here:
        clearFormBrand();
    }//GEN-LAST:event_btnNewBrandActionPerformed

    private void btnLastBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastBrandActionPerformed
        // TODO add your handling code here:
        lastBrand();
    }//GEN-LAST:event_btnLastBrandActionPerformed

    private void btnDeleteBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteBrandActionPerformed
        // TODO add your handling code here:
        deleteBrand();
    }//GEN-LAST:event_btnDeleteBrandActionPerformed

    private void btnUpdateBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBrandActionPerformed
        // TODO add your handling code here:
        updateBrand();
    }//GEN-LAST:event_btnUpdateBrandActionPerformed

    private void btnAddBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBrandActionPerformed
        // TODO add your handling code here:
        insertBrand();
    }//GEN-LAST:event_btnAddBrandActionPerformed

    private void btnNextBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextBrandActionPerformed
        // TODO add your handling code here:
        nextBrand();
    }//GEN-LAST:event_btnNextBrandActionPerformed

    private void btnPrevBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevBrandActionPerformed
        // TODO add your handling code here:
        prevBrand();
    }//GEN-LAST:event_btnPrevBrandActionPerformed

    private void btnfirstBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnfirstBrandActionPerformed
        // TODO add your handling code here:
        firstBrand();
    }//GEN-LAST:event_btnfirstBrandActionPerformed

    private void txtSeacherBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSeacherBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSeacherBrandActionPerformed

    private void btnSeacherBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeacherBrandActionPerformed
        // TODO add your handling code here:
        seacherBrand();
    }//GEN-LAST:event_btnSeacherBrandActionPerformed

    private void txtBrandNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrandNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrandNameActionPerformed

    private void txtOriginBrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOriginBrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOriginBrandActionPerformed

    private void txtBrandIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBrandIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBrandIDActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        row = table.getSelectedRow();
        this.edit(row);
        //        tabs.setSelectedIndex(0);
        System.out.println("lỏ" + row);
    }//GEN-LAST:event_tableMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed

        CboSuperCategory.setSelectedItem(2);
        clearForm();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        //        this.index = tblSanPham.getRowCount() - 1;
        //        this.edit();
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        //        if (checkFrom()) {
        delete();
        //        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        //        if (Auth.isManager()) {
        //            if (checkFrom()) {
        update();
        //            }
        //        } else {
        //            MsgBox.alert(null, "Bạn không có quyền cập nhật !");
        //        }
    }//GEN-LAST:event_btnSuaActionPerformed

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

    private void searchText1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchText1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchText1ActionPerformed

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
        seacher();
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void txtCategoryName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryName1ActionPerformed

    private void txtCategoryIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCategoryIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCategoryIDActionPerformed

    private void CboSuperCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CboSuperCategoryActionPerformed

        String stype = "category";
        IndexComBox = CboSuperCategory.getSelectedIndex() - 1;
        System.out.println("CboCategoryActionPerformed");
        System.out.println(IndexComBox);
        chonComboBox(IndexComBox);
    }//GEN-LAST:event_CboSuperCategoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CboSuperCategory;
    private javax.swing.JScrollPane JScrollPane;
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JTabbedPane JTablePane;
    private javax.swing.JButton btnAddAge;
    private javax.swing.JButton btnAddBrand;
    private javax.swing.JButton btnAddMaterial;
    private javax.swing.JButton btnDeleteAge;
    private javax.swing.JButton btnDeleteBrand;
    private javax.swing.JButton btnDeleteMaterial;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnLastAge;
    private javax.swing.JButton btnLastBrand;
    private javax.swing.JButton btnLastMaterial;
    private javax.swing.JButton btnNewAge;
    private javax.swing.JButton btnNewBrand;
    private javax.swing.JButton btnNewMaterial;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnNextAge;
    private javax.swing.JButton btnNextBrand;
    private javax.swing.JButton btnNextMaterial;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnPrevAge;
    private javax.swing.JButton btnPrevBrand;
    private javax.swing.JButton btnPrevMaterial;
    private javax.swing.JButton btnSeacherAge;
    private javax.swing.JButton btnSeacherBrand;
    private javax.swing.JButton btnSeacherMaterial;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
    private javax.swing.JButton btnUpdateAge;
    private javax.swing.JButton btnUpdateBrand;
    private javax.swing.JButton btnUpdateMaterial;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnfirst;
    private javax.swing.JButton btnfirstAge;
    private javax.swing.JButton btnfirstBrand;
    private javax.swing.JButton btnfirstMaterial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label2;
    private javax.swing.JLabel lblBrandID;
    private javax.swing.JLabel lblBrandID1;
    private javax.swing.JLabel lblBrandID2;
    private javax.swing.JLabel lblBrandName;
    private javax.swing.JLabel lblBrandName1;
    private javax.swing.JLabel lblBrandName2;
    private javax.swing.JLabel lblDescriptionBrand;
    private javax.swing.JLabel lblDescriptionBrand1;
    private javax.swing.JLabel lblOriginBrand;
    private javax.swing.JPanel pnButton;
    private com.toystore.swing.SearchText searchText1;
    private javax.swing.JPanel tabBrand;
    private javax.swing.JPanel tabCategory;
    private javax.swing.JPanel tabMaterial;
    private com.toystore.swing.Table table;
    private com.toystore.swing.Table tableAge;
    private com.toystore.swing.Table tableBrand;
    private javax.swing.JScrollPane tableCategory;
    private javax.swing.JScrollPane tableCategory1;
    private com.toystore.swing.Table tableMaterial;
    private javax.swing.JTextField txtAgeID;
    private javax.swing.JTextField txtAgeName;
    private javax.swing.JTextField txtBrandID;
    private javax.swing.JTextField txtBrandName;
    private javax.swing.JTextArea txtCategoryDescription;
    private javax.swing.JTextField txtCategoryID;
    private javax.swing.JTextField txtCategoryName1;
    private javax.swing.JTextArea txtDescriptionBrand;
    private javax.swing.JTextArea txtMaterialDescription;
    private javax.swing.JTextField txtMaterialID;
    private javax.swing.JTextField txtMaterialName;
    private javax.swing.JTextField txtOriginBrand;
    private com.toystore.swing.SearchText txtSeacherAge;
    private com.toystore.swing.SearchText txtSeacherBrand;
    private com.toystore.swing.SearchText txtSeacherMaterial;
    // End of variables declaration//GEN-END:variables
   void fillTableBrand(List<Brand> brandList) {
        DefaultTableModel model = new DefaultTableModel(row, 0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Code brand", "Name Brand", "Origin Brand", "Descriptions"});
        tableBrand.setModel(model);
        for (Brand brand : brandList) {
            model.addRow(new Object[]{brand.getBrandId(),
                brand.getName(),
                brand.getOriginBrand(),
                brand.getDescription(), //                StatusType.PENDING
        });
        }
        tableBrand.setModel(model);
    }

    void setFormBrand(Brand brand) {
        txtBrandID.setText(String.valueOf(brand.getBrandId()));
        txtBrandName.setText(brand.getName());
        txtOriginBrand.setText(brand.getOriginBrand());
        txtDescriptionBrand.setText(brand.getDescription());

    }

    Brand getFormBrand() {
        Brand brand = new Brand();
        brand.setBrandId(Integer.parseInt(txtBrandID.getText()));
        brand.setName(txtBrandName.getText());
        brand.setOriginBrand(txtOriginBrand.getText());
        brand.setDescription(txtDescriptionBrand.getText());
        return brand;
    }

    void insertBrand() {
        try {
            Brand brand = getFormBrand();
            System.out.println("name category: " + brand.getName());
            System.out.println("name category descriptions: " + brand.getDescription());
            System.out.println("name category descriptions: " + brand.getOriginBrand());
            brandDAO.insertBrand(brand);
            MsgBox.alert(null, "Added brand Success !");
            brandList = brandDAO.findAll();
            fillTableBrand(brandList);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Added brand Failed !");
        }
    }

    void updateBrand() {
        try {
            brandDAO.updateBrand(getFormBrand());
            MsgBox.alert(null, "Product Update Successfully!");
            brandList = brandDAO.findAll();
            fillTableBrand(brandList);
        } catch (Exception e) {
            MsgBox.alert(null, "Product Update Failed!");
        }
    }

    void deleteBrand() {
        try {
            int brandID = Integer.parseInt(txtBrandID.getText());
            boolean detete = brandDAO.deleteBrand(brandID);
            clearFormBrand();
            if (detete) {
                MsgBox.alert(null, "Delete brand Successfully!");
            } else {
                MsgBox.alert(null, "Delete brand FOREIGN KEY!");

            }
            brandList = brandDAO.findAll();
            fillTableBrand(brandList);
        } catch (Exception e) {
            MsgBox.alert(null, "Delete brand Failed!");
        }
    }

    void seacherBrand() {
        String input = txtSeacherBrand.getText().trim(); // Lấy giá trị nhập vào và loại bỏ khoảng trắng
        if (input.isEmpty()) {
            MsgBox.alert(null, "Please enter brand code!");
            return;
        }
        if (!input.matches("\\d+")) { // Kiểm tra xem input có phải là số không
            MsgBox.alert(null, "Brand code must be a number!");
            return;
        }
        try {
            int MSP = Integer.parseInt(input); // Chuyển chuỗi thành số nguyên
            Brand brand = brandDAO.findById(MSP);
            if (brand != null) {
                setFormBrand(brand);
            } else {
                MsgBox.alert(null, "Brand not found with code: " + MSP);
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(null, "Number conversion error! Please enter a valid code.");
        }
    }

    void clearFormBrand() {
        Brand brand = new Brand();
        this.setFormBrand(brand);
//        fillCombobox();
        this.row = -1;
        txtBrandID.setBackground(null);
        txtBrandName.setBackground(null);
        txtOriginBrand.setBackground(null);
        txtDescriptionBrand.setBackground(null);

    }

    void firstBrand() {
        this.row = 0;
        this.editBrand(row);
    }

    void nextBrand() {
        if (this.row > table.getRowCount() - 2) {
            return;
        } else {
            this.row++;
            this.editBrand(row);
        }
    }

    void prevBrand() {
        if (this.row < 1) {
            return;
        } else {
            this.row--;
            this.editBrand(row);
        }
    }

    void editBrand(int index) {
        Brand kh = brandList.get(index);
        setFormBrand(kh);
    }

    void lastBrand() {
        this.row = table.getRowCount() - 1;
        this.editBrand(row);
    }

    List<Material> materialList = new ArrayList<>();
    MaterialDAO materialDAO = new MaterialDAO();

    void fillTableMaterial(List<Material> material) {
        DefaultTableModel model = new DefaultTableModel(row, 0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Code Material", "Name Material", "Descriptions"});
        tableMaterial.setModel(model);
        for (Material material1 : material) {
            model.addRow(new Object[]{material1.getMaterialId(),
                material1.getName(),
                material1.getDescription(), //                StatusType.PENDING
        });
        }
        tableMaterial.setModel(model);
    }

    void setFormMaterial(Material material) {
        txtMaterialID.setText(String.valueOf(material.getMaterialId()));
        txtMaterialName.setText(material.getName());
        txtMaterialDescription.setText(material.getDescription());

    }

    Material getFormMaterial() {
        Material material = new Material();
        material.setMaterialId(Integer.parseInt(txtMaterialID.getText()));
        material.setName(txtMaterialName.getText());
        material.setDescription(txtMaterialDescription.getText());
        return material;
    }

    void insertMaterial() {
        try {
            Material material = getFormMaterial();
            System.out.println("name category: " + material.getName());
            System.out.println("name category descriptions: " + material.getDescription());
            materialDAO.insertMaterial(material);
            MsgBox.alert(null, "Added material successfully!");
            materialList = materialDAO.findAll();
            fillTableMaterial(materialList);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Added material failed!");
        }
    }

    void updateMaterial() {
        try {
            materialDAO.updateMaterial(getFormMaterial());
            MsgBox.alert(null, "Update material Success!");
            materialList = materialDAO.findAll();
            fillTableMaterial(materialList);
        } catch (Exception e) {
            MsgBox.alert(null, "Update material Failed !");
        }
    }

    void deleteMaterial() {
        try {
            int MaterialID = Integer.parseInt(txtMaterialID.getText());
            boolean delete = materialDAO.deleteMaterial(MaterialID);
            clearFormBrand();
            if (delete) {
                MsgBox.alert(null, "Delete material Success !");

            } else {
                MsgBox.alert(null, "Delete material FOREIGN key !");

            }

            materialList = materialDAO.findAll();
            fillTableMaterial(materialList);
        } catch (Exception e) {
            MsgBox.alert(null, "Delete material Failed !");
        }
    }

    void seacherMaterial() {
        String input = txtSeacherMaterial.getText().trim(); // Lấy giá trị nhập vào và loại bỏ khoảng trắng
        if (input.isEmpty()) {
            MsgBox.alert(null, "Please enter Material code!");
            return;
        }
        if (!input.matches("\\d+")) { // Kiểm tra xem input có phải là số không
            MsgBox.alert(null, "Material code must be numeric!");
            return;
        }
        try {
            int materialID = Integer.parseInt(input); // Chuyển chuỗi thành số nguyên
            Material material = materialDAO.findById(materialID);
            if (material != null) {
                setFormMaterial(material);
            } else {
                MsgBox.alert(null, "Brand not found with code: " + materialID);
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(null, "Number conversion error! Please enter a valid code.");
        }
    }

    void clearFormMaterial() {
        Material material = new Material();
        this.setFormMaterial(material);
        this.row = -1;
        txtMaterialID.setBackground(null);
        txtMaterialName.setBackground(null);
        txtMaterialDescription.setBackground(null);

    }

    void firstMateril() {
        this.row = 0;
        this.editMaterial(row);
    }

    void nextMaterial() {
        if (this.row > tableMaterial.getRowCount() - 2) {
            return;
        } else {
            this.row++;
            this.editMaterial(row);
        }
    }

    void prevMaterial() {
        if (this.row < 1) {
            return;
        } else {
            this.row--;
            this.editMaterial(row);
        }
    }

    void editMaterial(int index) {
        Material material = materialList.get(index);
        setFormMaterial(material);
    }

    void lastMaterial() {
        this.row = tableMaterial.getRowCount() - 1;
        this.editMaterial(row);
    }

    List<Age> ageList = new ArrayList<>();
    AgeDAO ageDAO = new AgeDAO();

    void fillTableAge(List<Age> ages) {
        DefaultTableModel model = new DefaultTableModel(row, 0);
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"Code Age", "Targer Age Group"});
        tableAge.setModel(model);
        for (Age age : ageList) {
            model.addRow(new Object[]{age.getAgeId(),
                age.getAgeRange()
            });
        }
        tableAge.setModel(model);
    }

    void setFormAge(Age age) {
        txtAgeID.setText(String.valueOf(age.getAgeId()));
        txtAgeName.setText(age.getAgeRange());
    }

    Age getFormAge() {
        Age age = new Age();
        age.setAgeId(Integer.parseInt(txtAgeID.getText()));
        age.setAgeRange(txtAgeName.getText());
        return age;
    }

    void insertAge() {
        try {
            Age age = getFormAge();
            System.out.println("name category: " + age.getAgeRange());
            ageDAO.insertAge(age);
            MsgBox.alert(null, "Added Age successfully!");
            ageList = ageDAO.findAll();
            fillTableAge(ageList);
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Added Age Failed !");
        }
    }

    void updateAge() {
        try {
            ageDAO.updateAge(getFormAge());
            MsgBox.alert(null, "Update Age successfully !");
            ageList = ageDAO.findAll();
            fillTableAge(ageList);
        } catch (Exception e) {
            MsgBox.alert(null, "Update Age Failed !");
        }
    }

    void deleteAge() {
        try {
            int ageID = Integer.parseInt(txtAgeID.getText());
            boolean delete = ageDAO.deleteAge(ageID);
            clearFormAge();
            if (delete) {
                MsgBox.alert(null, "Delete Age successfully !");

            } else {
                MsgBox.alert(null, "Delete Age FOREIGN key !");

            }
            ageList = ageDAO.findAll();
            fillTableAge(ageList);
        } catch (Exception e) {
            MsgBox.alert(null, "Delete Age Failed !");
        }
    }

    void seacherAge() {
        String input = txtSeacherAge.getText().trim(); // Lấy giá trị nhập vào và loại bỏ khoảng trắng
        if (input.isEmpty()) {
            MsgBox.alert(null, "Please code age!");
            return;
        }
        if (!input.matches("\\d+")) { // Kiểm tra xem input có phải là số không
            MsgBox.alert(null, "Age code must be numeric!");
            return;
        }
        try {
            int ageID = Integer.parseInt(input); // Chuyển chuỗi thành số nguyên
            Age age = ageDAO.findById(ageID);
            if (age != null) {
                setFormAge(age);
            } else {
                MsgBox.alert(null, "Brand with code not found: " + ageID);
            }
        } catch (NumberFormatException e) {
            MsgBox.alert(null, "Number conversion error! Please enter a valid code.");
        }
    }

    void clearFormAge() {
        Age age = new Age();
        this.setFormAge(age);
//        fillCombobox();
        this.row = -1;
        txtAgeID.setBackground(null);
        txtAgeName.setBackground(null);
    }

    void firstAge() {
        this.row = 0;
        this.editAge(row);
    }

    void nextAge() {
        if (this.row > tableAge.getRowCount() - 2) {
            return;
        } else {
            this.row++;
            this.editAge(row);
        }
    }

    void prevAge() {
        if (this.row < 1) {
            return;
        } else {
            this.row--;
            this.editAge(row);
        }
    }

    void editAge(int index) {
        Age age = ageList.get(index);
        setFormAge(age);
    }

    void lastAge() {
        this.row = tableAge.getRowCount() - 1;
        this.editAge(row);
    }
}
