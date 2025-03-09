/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toystore.form;

import com.toystore.dao.HoaDonChiTietDao;
import com.toystore.dao.HoaDonDao;
import com.toystore.dao.KhachHangDao;
import com.toystore.model.HoaDon;
import com.toystore.utils.MsgBox;
import com.toystore.utils.XDate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.toystore.model.HoaDonChiTiet;
import com.toystore.model.KhachHang;
import com.toystore.utils.Auth;


/**
 *
 * @author RAVEN
 */
public class HoaDonView extends javax.swing.JPanel {

    HoaDonDao dao = new HoaDonDao() {
    };
    List<HoaDon> listHD = new ArrayList<>();

    List<HoaDonChiTiet> listHDCT = new ArrayList<>();
    HoaDonChiTietDao daoHDCT = new HoaDonChiTietDao() {
    };

    List<KhachHang> listKH = new ArrayList<>();
    KhachHangDao daoKH = new KhachHangDao();

    Date ngay = XDate.now();
    double totalAmount = 0.0;  // Variable to keep track of the total amount
    String maHD = null;
    int row = 01;//vị trí của nhân viên đang hiển thị trên form
    
   
    
    String maNV = "NV001";
    int k = -1;

    public HoaDonView() {
        initComponents();
        Int();
    }

    public void Int() {
        fillTable(listHD);
         if(Auth.isLogin()){
             maNV = Auth.user.getMaNV();
         }else{
             maNV = "NV001";
         }
    }

    // hàm tự tăng mã hóa đơn
    public void TuTang() {
        try {
            List<Object[]> list = dao.max();
            if (!list.isEmpty()) {
                Object[] maxValues = list.get(0);
                int maxValue = (int) maxValues[0];

                // Kiểm tra giá trị tối đa để quyết định liệfu có thể tăng thêm hay không
                if (maxValue < 99999999) {
                    // Tăng giá trị và tạo mã hóa đơn mới
                    int newNumber = maxValue + 1;
                    String nextInvoice = "HD" + newNumber;

                    // Sử dụng nextInvoice theo nhu cầu của bạn (có thể làm gì đó với nó)
                    System.out.println("Next Invoice: " + nextInvoice);
                    maHD  = nextInvoice;
                    txtMaHD.setText(nextInvoice);

                    // Nếu bạn muốn lưu giá trị mới vào cơ sở dữ liệu, thêm logic ở đây
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

    public void filltenKH(int k) {
        listKH = daoKH.selectAll();
        for (KhachHang kh : listKH) {
            if (k == kh.getMaKH()) {
                lblTenKH.setText(kh.getTenKH());
                return;
            }
        }
        lblTenKH.setText("");
    }

    public void fillTable(List<HoaDon> list) {
        String row[] = {"Mã HD", "Mã KH", "Mã NV", " Ngày tạo", "Tổng tiền", "Trạng Thái"};
        DefaultTableModel model = new DefaultTableModel(row, 0);

        listHD = dao.selectAll();
        for (HoaDon hd : listHD) {
            model.addRow(new Object[]{
                hd.getMaHD(),
                hd.getMaKH(),
                hd.getMaNV(),
                hd.getNgayTao(),
                hd.getTongTien(), //    
                hd.getTrangThai()
            });
        }
        tblHoaDon9.setModel(model);
    }

    public void setForm(HoaDon model) {
        txtMaHD.setText(model.getMaHD());
        txtMaKH.setText(model.getMaKH());
        lblTenNV.setText(model.getMaNV());
        lblNgayTao.setText(XDate.toString(model.getNgayTao(), "dd-MM-yyyy"));
        lblTongTien.setText(Double.toString(model.getTongTien()));
    }

    public HoaDon getFormHD() {
        Date date = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
        String textDate = formater.format(date);
        HoaDon hd = new HoaDon();
        hd.setMaHD(txtMaHD.getText().trim());
        hd.setMaKH(txtMaKH.getText().trim());
        hd.setMaNV("NV001");
        hd.setNgayTao(XDate.now());
        hd.setTongTien(Double.valueOf(lblTongTien.getText().trim()));
        String matt = "CHƯA THANH TOÁN";    
        Object selectedLoai = cboHTTT.getSelectedItem();
          if (selectedLoai != null) {
//            row = cboHTTT.getSelectedIndex() - 1;
//            if(cboMaGG.getSelectedItem()){
//            }
            // Chuyển đổi và đặt giá trị cho thuộc tính maLoai
            hd.setTrangThai(selectedLoai.toString());
        } else {
            // Xử lý khi không có mục nào được chọn trong ComboBox
            hd.setTrangThai(matt); // hoặc giá trị mặc định khác tùy vào logic của bạn
//             row = cboMaGG.getSelectedIndex() - 1;
        }
        return hd;
    }

    void edit(int index) {

//        if (!listHD.isEmpty()) {
        // Đảm bảo danh sách không rỗng trước khi truy cập phần tử
        // Tiếp tục xử lý obj
        HoaDon kh = listHD.get(index);
        setForm(kh);
//    } else {
//        // Xử lý trường hợp danh sách rỗng
//        System.out.println("Danh sách rỗng, không thể truy cập phần tử.");
//    }
    }

// tính tổng
    public void fillTableHDCT1(String ma) {
//        lblMaHD.setText(ma);
        String row[] = {"Mã HDCT", "Mã HD", "Mã SP", "Số lượng", "Ghi chú", "ma gg", "Thành tiền"};
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
            double TT = hd.getThanhTien();
            totalAmount += TT;  // Update the total amount
            System.out.println(hd.getThanhTien());
        }
        lblTongTien.setText(Double.toString(totalAmount));
//                lblTongTien.setText(Double.toString(totalAmount)+" Đ");
        // Print or use the total amount as needed
        System.out.println("Total Amount: " + totalAmount);
    }

//
//
    public void insertHD() {
        try {
            
            dao.insert(getFormHD());
            MsgBox.alert(null, "Thêm sản phẩm  Thành Công !");
            fillTable(listHD);
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "Thêm sản phẩm Thất Bại !");
        }
    }

    public void updateHD() {
        try {
            dao.update(getFormHD());
            MsgBox.alert(null, "sửa sản phẩm  Thành Công !");
            fillTable(listHD);
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "sửa sản phẩm Thất Bại !");
        }
    }

    public void deleteHD() {
        try {
            System.out.println(maHD);
            dao.delete(maHD);
            MsgBox.alert(null, "xóa sản phẩm  Thành Công !");
            fillTable(listHD);
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(null, "xóa sản phẩm Thất Bại !");
        }
    }

    public void clearForm() {
        txtMaHD.setText("");
        txtMaKH.setText("1");
        lblTenNV.setText(maNV);
        lblNgayTao.setText(XDate.toString(ngay, "dd-MM-yyyy"));
        lblTongTien.setText("0.0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.toystore.swing.PanelBorder();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        lblTenKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTongSoLuong = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboHTTT = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        lblTenNV = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblNgayTao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaHD = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon9 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));
        panelBorder1.setPreferredSize(new java.awt.Dimension(950, 580));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(950, 580));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setText("Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("Tên KH");

        lblTenKH.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTenKH.setText("Nguyen Van A");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });
        txtMaKH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMaKHKeyReleased(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Mã KH");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Tổng số lượng");

        lblTongSoLuong.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTongSoLuong.setText("0");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("Tổng tiền: ");

        lblTongTien.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTongTien.setText("0.000.000");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Hình thức TT");

        cboHTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Tiền mặt", "Chuyển khoản" }));

        jButton6.setText("Xóa");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblTenNV.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTenNV.setText("NV001");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText("Mã NV: ");

        lblNgayTao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblNgayTao.setText("dd-MM-yyy");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Ngày tạo");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Mã hóa đơn:");

        jButton8.setText("Thêm");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setText("Sửa");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("Thêm SP");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaHD))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(32, 32, 32)
                                .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(31, 31, 31)
                                .addComponent(lblTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTongSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8)
                            .addComponent(jButton5)
                            .addComponent(jButton7)
                            .addComponent(jButton6)
                            .addComponent(jButton9)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenKH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboHTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 921, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        tblHoaDon9.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon9MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon9);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 921, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setText("Hóa Đơn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 918, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaKHActionPerformed

    private void txtMaKHKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMaKHKeyReleased
        try {
            k = Integer.parseInt(txtMaKH.getText());
            System.out.println("maKh: " + k);
            filltenKH(k);
        } catch (Exception e) {
            lblTenKH.setText("");
        }

    }//GEN-LAST:event_txtMaKHKeyReleased

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        clearForm();
        TuTang();
        if (MsgBox.confirm(null, "bạn thêm muốn thêm Hóa đơn " + maHD)) {
            insertHD();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        updateHD();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        ChiTietHoaDonView cthd = new ChiTietHoaDonView(null, false);
        maHD = txtMaHD.getText();
        cthd.fillTableHDCT1(maHD);
        
        
        cthd.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Window closed successfully");
//                System.out.println(oke); // Make sure 'oke' is defined and has a value
                clearForm();
                listHD.clear();
                fillTable(listHD);
            }
        });
        cthd.setVisible(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void tblHoaDon9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon9MouseClicked
        this.row = tblHoaDon9.getSelectedRow();
        System.out.println(row);
        this.edit(row);
        totalAmount = 0.0;
        maHD = txtMaHD.getText();
        fillTableHDCT1(maHD);
        String ma = (String) tblHoaDon9.getValueAt(row, 1); // ép một lượt bị lỗi
        k = Integer.parseInt(ma);
        System.out.println("ma" + k);
        filltenKH(k);
    }//GEN-LAST:event_tblHoaDon9MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        deleteHD();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboHTTT;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JLabel lblNgayTao;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTongSoLuong;
    private javax.swing.JLabel lblTongTien;
    private com.toystore.swing.PanelBorder panelBorder1;
    private javax.swing.JTable tblHoaDon9;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaKH;
    // End of variables declaration//GEN-END:variables
}
