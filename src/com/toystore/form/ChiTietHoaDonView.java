/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.toystore.form;

import com.toystore.dao.GiamGiaDao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.toystore.dao.HoaDonChiTietDao;
import com.toystore.dao.HoaDonDao;
import com.toystore.dao.SanPhamDao;
import com.toystore.model.GiamGia;
import com.toystore.model.HoaDon;
import com.toystore.model.HoaDonChiTiet;
import com.toystore.model.Sanpham;
import com.toystore.utils.MsgBox;


public class ChiTietHoaDonView extends javax.swing.JDialog {

    List<GiamGia> listGG = new ArrayList<>();
    GiamGiaDao daoGG = new GiamGiaDao() {
    };

    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    HoaDonChiTietDao daoHDCT = new HoaDonChiTietDao() {
    };

    List<Sanpham> listSP = new ArrayList<>();
    SanPhamDao daoSP = new SanPhamDao() {
    };
    //
    int row = 0;
    int index = 0;
    double thanhtien = 0;
    double giamgia = 0;

    public ChiTietHoaDonView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        fillCombobox();
        HoaDonView k = new HoaDonView();
//        spnSL.setValue(1);

    }

    // hàm tự tăng mã hóa đơn
    public void TuTang() {
        try {
            List<Object[]> list = daoHDCT.max();
            if (!list.isEmpty()) {
                Object[] maxValues = list.get(0);
                int maxValue = (int) maxValues[0];
                // Kiểm tra giá trị tối đa để quyết định liệu có thể tăng thêm hay không
                if (maxValue < 9999999) {
                    // Tăng giá trị và tạo mã hóa đơn mới
                    int newNumber = maxValue + 1;
                    String nextInvoice = "HDC" + newNumber;
                    // In ra để kiểm tra giá trị mới
                    MsgBox.alert(null, "Next Invoice: " + nextInvoice);
                    // Gán giá trị mới vào trường hiển thị hoặc bảng điều khiển của bạn
                    txtMaHDCT.setText(nextInvoice);
                    // Nếu muốn lưu giá trị mới vào cơ sở dữ liệu, thêm logic ở đây
                    // Ví dụ: dao.insertNewInvoice(nextInvoice);
                } else {
                    System.out.println("Đã đạt đến giá trị tối đa, không thể tăng thêm.");
                }
            } else {
                System.out.println("Không có giá trị max nào được trả về từ cơ sở dữ liệu.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// lấy dữ liệu đưa lên form
    public void SetFromSP(Sanpham sp) {
//        txtMaSP.setText(sp.getBienTheSP());
        lblTenSP.setText(sp.getTenSP());
        lblMau.setText(sp.getMauSac());
        lblSoluongSP.setText(String.valueOf(sp.getSoLuong()));
        lblSize.setText(sp.getKichCo());
        lblGia.setText(String.valueOf(sp.getGia()));

    }

// lấy dữ liệu HDCT lên from
    public void SetFromHDCT(HoaDonChiTiet hd) {
        txtMaHDCT.setText(hd.getMaHDCT());
        txtMaSP.setText(hd.getMaBienTheSP());
        spnSL.setValue(hd.getSoLuong());
        txtGhiChu.setText(hd.getGhiChu());
        //         Giả sử ComboBox có kiểu dữ liệu là String
        String selectChonGG = hd.getMaGG();
        cboMaGG.setSelectedItem(selectChonGG);
        lblThanhTien.setText(String.valueOf(hd.getThanhTien()));
    }

    //đưa lên from
    void edit(int index) {
        HoaDonChiTiet kh = listHDCT.get(index);
        SetFromHDCT(kh);
    }

    // lấy dữ liệu về
    public HoaDonChiTiet getFormHDCT() {
        String maGG = "GG001";
        HoaDonChiTiet hd = new HoaDonChiTiet();
        hd.setMaHDCT(txtMaHDCT.getText());
        hd.setMaHDn(lblMaHD.getText().trim());
        hd.setMaBienTheSP(txtMaSP.getText());
        hd.setSoLuong((int) spnSL.getValue());
        hd.setGhiChu(txtGhiChu.getText());
        Object selectedLoai = cboMaGG.getSelectedItem();

        if (selectedLoai != null) {
            row = cboMaGG.getSelectedIndex() - 1;
//            if(cboMaGG.getSelectedItem()){
//            }
            // Chuyển đổi và đặt giá trị cho thuộc tính maLoai
            hd.setMaGG(selectedLoai.toString());
        } else {
            // Xử lý khi không có mục nào được chọn trong ComboBox
            hd.setMaGG(maGG); // hoặc giá trị mặc định khác tùy vào logic của bạn
//             row = cboMaGG.getSelectedIndex() - 1;
        }
        hd.setThanhTien(Double.valueOf(lblThanhTien.getText()));
        return hd;
    }

    // thêm HDCT
    public void insert() {
        daoHDCT.insert(getFormHDCT());
        MsgBox.alert(null, "Thêm sản phẩm  Thành Công !");
        String ma1 = lblMaHD.getText();
        fillTableHDCT1(ma1);
    }

    // load và fill lên bản HDCT
    public void fillTableHDCT(String ma) {
        lblMaHD.setText(ma);
        listHDCT = daoHDCT.selectById1(ma);

    }

// chọn ComBoBox giảm giá
    void chonComboBox(int index) {
        if (index >= 0) {
            GiamGia cd = listGG.get(index);
        }
    }

// Fill dữ liệu lên combobox giam giá
    final void fillCombobox() {
        listGG = daoGG.selectAll();
        DefaultComboBoxModel cboModel = (DefaultComboBoxModel) cboMaGG.getModel();
        cboModel.removeAllElements();
        cboModel.addElement("vui lòng chọn !");

        for (GiamGia itempCD : listGG) {
            cboModel.addElement(itempCD.getMaGG());
        }
    }

//    private List<String> getSearchResults(String searchTerm) {
//        // Replace this with your actual search logic
//        List<String> dummyData = new ArrayList<>();
//        dummyData.add("Result 1");
//        dummyData.add("Result 2");
//        dummyData.add("Result 3");
//        return dummyData;
//    }
// 
    int soluong = 1;

    public void timkiem1(String ma) {
        try {
            String rowSP[] = {"MaSP", "LoaiSP", "TenSP", "Kichco", "Mausac", "gia ban", "soLuong"};
            Sanpham sp = daoSP.selectById(ma);
//            listSP = (List<Sanpham>) daoSP.selectById(ma);
            SetFromSP(sp);
            soluong = sp.getSoLuong();
            spnSL.setValue(1);
        } catch (Exception e) {
//            e.printStackTrace();// mở lên là hiện lỗi khi chạy
        }
    }
// fill bản hóa đơn chi tiết  = mahd bên bản hóa đơn

    public void fillTableHDCT1(String ma) {
        lblMaHD.setText(ma);
        String row[] = {"Mã HDCT", "Mã HD", "Mã SP", "Số lượng", "ghi chú", "Mã gg", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel(row, 0);
        listHDCT = daoHDCT.getAllByID(ma);
        for (HoaDonChiTiet hd : listHDCT) {
            model.addRow(new Object[]{
                hd.getMaHDn(),
                hd.getMaHDCT(),
                hd.getMaBienTheSP(),
                hd.getSoLuong(),
                hd.getGhiChu(),
                hd.getMaGG(),
                hd.getThanhTien()
            });
        }
        tblbanHDCT.setModel(model);
        cboMaGG.setSelectedIndex(3);
    }

    public void thanhtien() {
        thanhtien = Double.parseDouble(lblGia.getText()) * index * (1 - (giamgia / 100));
        lblThanhTien.setText(String.valueOf(thanhtien));
    }
    List<HoaDon> listHD = new ArrayList<>();
    HoaDonDao dao = new HoaDonDao() {
    };

    @SuppressWarnings("unchecked")

//    HoaDonView g = new HoaDonView();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        ListSoLuong = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        spnSL = new javax.swing.JSpinner();
        jLabel31 = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        lblSize = new javax.swing.JLabel();
        lblMau = new javax.swing.JLabel();
        txtMaHDCT = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        cboMaGG = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel33 = new javax.swing.JLabel();
        lblSoluongSP = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblbanHDCT = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Mã hóa đơn:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Mã sản phẩm:");

        txtMaSP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSPActionPerformed(evt);
            }
        });
        txtMaSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaSPKeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Màu:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Size:");

        ListSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ListSoLuong.setText("Số Lượng:");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("Giá:");

        lblGia.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblGia.setText("0.0");

        spnSL.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnSLStateChanged(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("Tên sản phẩm:");

        lblTenSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTenSP.setText("SP A");

        jButton1.setText("Thêm sản phẩm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel63.setText("Giảm giá:");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel64.setText("Thành tiền");

        lblThanhTien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblThanhTien.setText("20000");

        lblMaHD.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblMaHD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSize.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSize.setText("XX");

        lblMau.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblMau.setText("Xanh");

        txtMaHDCT.setText("HDC001");
        txtMaHDCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHDCTActionPerformed(evt);
            }
        });

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        txtGhiChu.setText("ki");
        jScrollPane1.setViewportView(txtGhiChu);

        cboMaGG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMaGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaGGActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel33.setText("Hiện còn:");

        lblSoluongSP.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblSoluongSP.setText("0.0");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblMau, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblSize, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblSoluongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(366, 366, 366))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(236, 236, 236)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel64)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(cboMaGG, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(ListSoLuong)
                                        .addGap(18, 18, 18)
                                        .addComponent(spnSL, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                            .addGap(122, 122, 122)
                            .addComponent(txtMaSP))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtMaHDCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboMaGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ListSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(spnSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSize))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMau)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSoluongSP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        tblbanHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblbanHDCT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbanHDCTMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(tblbanHDCT);

        jButton2.setText("In hóa đơn");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSPActionPerformed

    private void txtMaSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaSPKeyReleased
//
        String ma1 = txtMaSP.getText();
        timkiem1(ma1);
    }//GEN-LAST:event_txtMaSPKeyReleased

    private void spnSLStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnSLStateChanged
        index = (int) spnSL.getValue();
//        || index > soluong
        if (index < 0) {
            spnSL.setValue(1);
            JOptionPane.showMessageDialog(this, "Số lượng không được nhỏ hơn 0 hoăc lớn hớn soluong đã có !");
        } else {
            thanhtien();
            System.out.println(" index: " + index);
        }
    }//GEN-LAST:event_spnSLStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TuTang();

        insert();
        HoaDonView cthd = new HoaDonView();
        if (listHD.isEmpty()) {
            listHD = dao.selectAll();
        } else {
            System.out.println("lisst roongx");
        }
        cthd.fillTable(listHD);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        BillPrintingApp bill = new BillPrintingApp();
//        bill.ma(lblMaHD.getText());
////        this.dispose();
//        bill.setVisible(true);
              BillJDialog bill = new BillJDialog(null, true);
             bill.madh = lblMaHD.getText();
             bill.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtMaHDCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHDCTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHDCTActionPerformed

    private void cboMaGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaGGActionPerformed
        // TODO add your handling code here:
        try {
            String magg = (String) cboMaGG.getSelectedItem();
            System.out.println(magg);
            if (cboMaGG.getSelectedIndex() == 0) {
                giamgia = 0;
            } else {
                GiamGia gg = daoGG.getAllByID(magg);
                giamgia = Double.parseDouble(gg.getTienGG());
                System.out.println(giamgia);
            }
            thanhtien();
            // com
            row = cboMaGG.getSelectedIndex() - 1;
            System.out.println(row);
            chonComboBox(row);
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cboMaGGActionPerformed

    private void tblbanHDCTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbanHDCTMouseClicked
        this.row = tblbanHDCT.getSelectedRow();
        this.edit(row);
    }//GEN-LAST:event_tblbanHDCTMouseClicked

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
            java.util.logging.Logger.getLogger(ChiTietHoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietHoaDonView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChiTietHoaDonView dialog = new ChiTietHoaDonView(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ListSoLuong;
    private javax.swing.JComboBox<String> cboMaGG;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JLabel lblMau;
    private javax.swing.JLabel lblSize;
    private javax.swing.JLabel lblSoluongSP;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JSpinner spnSL;
    private javax.swing.JTable tblbanHDCT;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtMaHDCT;
    private javax.swing.JTextField txtMaSP;
    // End of variables declaration//GEN-END:variables

}
