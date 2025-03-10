/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.toystore.form.store;

//import com.Dao.sanPhamDao;
//import com.component.Item;
//import com.Dao.HoaDonChiTietDAO;
//import com.Dao.HoaDonDAO_1;
//import com.Dao.KhuyenMaiDao;
//import com.Dao.loaiSanPhamDao;
//import com.event.EventItem;
//import static com.form.Calam.scanResult;
//import com.model.HoaDon;
//import com.model.HoaDonChiTiet;
//import com.model.KhuyenMai;
//import com.model.LoaiSanPham;
//import com.model.SanPham;
//import com.swing.ScrollBar;
//import com.untils.XAuth;
import com.toystore.component.Item;
import com.toystore.dao.store.productDAO;
import com.toystore.event.EventItem;
import com.toystore.model.store.product;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author HP
 */
public class Menu extends javax.swing.JPanel {

    DefaultTableModel model, modelHD;
//    HoaDonDAO_1 hddao = new HoaDonDAO_1();
//    HoaDonChiTietDAO hdctdao = new HoaDonChiTietDAO();
//    List<HoaDon> listHD = new ArrayList();
    DefaultCellEditor cellEditor;
//    List<SanPham> listSP = new ArrayList<>();
    List<Integer> listrow = new ArrayList<>();
    List<String> listMaSP = new ArrayList<>();
    List<String> listKM = new ArrayList<>();
//    KhuyenMaiDao kmdao = new KhuyenMaiDao();
//    List<KhuyenMai> listKMSP = new ArrayList();
    List<String> listMaHD = new ArrayList<>();
//    sanPhamDao SPDao = new sanPhamDao();
//    loaiSanPhamDao sanPhamDao = new loaiSanPhamDao();
//    private SanPham itemSelected;
    List<Double> listGia = new ArrayList();
    int row = -1;
    int row2 = -1;
    private JSpinner spinner;
    private int newValue = 0;
    private boolean taoHD = false;
    private String ngayTaoHD = "";
    private String maHDInsert;
    private double giakm = 0;
    private JTextPane bill;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date ngayTao = null;
    Date ngayTao2 = null;
    String alterTien = "";
    String alterPhi = "";
    boolean checkselected = false;
    String ma = "";
    Date bd = null;
    Date kt = null;
    String tNV = "";
    DecimalFormat df = new DecimalFormat("#,###,###,###.000");
    DecimalFormat format = new DecimalFormat();
    String outPutVC = "";
    List<Object[]> MahoaQR = new ArrayList<>();
    int giatrivoucher = 0;
    int maVoucher = 0;

    /**
     * Creates new form Menu1
     */
    public Menu() {
        initComponents();
//        scroll1.setVerticalScrollBar(new ScrollBar());
        init();

//        loadData();
    }

    public void setEvent(EventItem event) {
        this.event = event;
    }
//
    private EventItem event;
//

    public void addItem(product data) {
        Item item = new Item();
        item.setData(data);
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    event.itemClick(item, data);
                }
            }
        });
        panelItem1.add(item);
        panelItem1.repaint();
        panelItem1.revalidate();
    }

    public void setSelected(Component item) {
        for (Component com : panelItem1.getComponents()) {
            Item i = (Item) com;
            if (i.isSelected()) {
                i.setSelected(false);
            }
        }
        ((Item) item).setSelected(true);
    }

    public void fillpanelItem() {
        productDao = new productDAO();
        listSP = productDao.getAllProducts();
        for (product p : listSP) {
            addItem(p);
            System.out.println("fillpanelItem :" + p.getName());
        }
    }

//    public void showItem(ModelItem data) {
//        lbItemName.setText(data.getItemName());
//        txtDescription.setText(data.getDescription());
//        lbBrand.setText(data.getBrandName());
//        DecimalFormat df = new DecimalFormat("$#,##0.00");
//        lbPrice.setText(df.format(data.getPrice()));
//    }
    public Point getPanelItemLocation() {
        Point p = scroll1.getLocation();
        return new Point(p.x, p.y - scroll1.getViewport().getViewPosition().y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnuPopXoa = new javax.swing.JMenuItem();
        mnuPopRemoveAll = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel5 = new javax.swing.JPanel();
        scroll1 = new javax.swing.JScrollPane();
        panelItem1 = new com.toystore.swing.PanelItem();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cboLoaiSP = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        btnVC = new javax.swing.JButton();
        lblvoucher = new javax.swing.JLabel();
        lblGiaTriVC = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtTienSP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtChiPhiKhac = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtTienNhan = new javax.swing.JTextField();
        txtTienThua = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        rdoTienMat = new javax.swing.JRadioButton();
        rdoChuyenKhoang = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblThongBaoPhi = new javax.swing.JLabel();
        lblThongBaoTienNhan = new javax.swing.JLabel();
        btnThanhToan1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblHoaDonCho = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();

        mnuPopXoa.setText("jMenuItem1");
        mnuPopXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPopXoaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnuPopXoa);

        mnuPopRemoveAll.setText("jMenuItem1");
        mnuPopRemoveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPopRemoveAllActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnuPopRemoveAll);

        setBackground(new java.awt.Color(244, 154, 157));
        setPreferredSize(new java.awt.Dimension(950, 580));

        jPanel5.setBackground(new java.awt.Color(244, 154, 157));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(959, 615));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scroll1.setBorder(null);
        scroll1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                scroll1ComponentAdded(evt);
            }
        });
        scroll1.setViewportView(panelItem1);

        jPanel5.add(scroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 590, 480));

        jPanel6.setBackground(new java.awt.Color(244, 154, 157));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setText("Tên sản phẩm");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cboLoaiSP.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cboLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiSPActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Loại sản phẩm");

        btnVC.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVC.setText("Voucher");
        btnVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVCActionPerformed(evt);
            }
        });

        lblvoucher.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblvoucher.setText("Giá trị voucher(nghìn VNĐ):");

        lblGiaTriVC.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblGiaTriVC.setText("-----------------");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(37, 37, 37)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(cboLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblvoucher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGiaTriVC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(btnVC))
                    .addComponent(jLabel14))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiSP)
                            .addComponent(btnVC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblvoucher)
                            .addComponent(lblGiaTriVC))))
                .addContainerGap())
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 70));

        jPanel7.setBackground(new java.awt.Color(244, 154, 157));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên SP", "Giá SP", "Số Lượng", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setGridColor(new java.awt.Color(204, 204, 204));
        tblHoaDon.setSelectionBackground(new java.awt.Color(244, 126, 130));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseReleased(evt);
            }
        });
        tblHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblHoaDonKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblHoaDon.getColumnModel().getColumn(2).setPreferredWidth(38);
        }

        jPanel7.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 6, 430, 206));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Tiền sản phẩm");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 218, -1, -1));

        txtTienSP.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienSPCaretUpdate(evt);
            }
        });
        jPanel7.add(txtTienSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 244, 193, 36));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Chi phí khác");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 218, -1, -1));

        txtChiPhiKhac.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtChiPhiKhacCaretUpdate(evt);
            }
        });
        jPanel7.add(txtChiPhiKhac, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 244, 197, 36));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Tiền nhận*");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 292, -1, -1));

        txtTienNhan.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTienNhanCaretUpdate(evt);
            }
        });
        txtTienNhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTienNhanKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTienNhanKeyTyped(evt);
            }
        });
        jPanel7.add(txtTienNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 318, 193, 36));
        jPanel7.add(txtTienThua, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 318, 197, 36));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Phương thức thanh toán:");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, -1, -1));

        buttonGroup1.add(rdoTienMat);
        rdoTienMat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoTienMat.setText("Tiền mặt");
        jPanel7.add(rdoTienMat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        buttonGroup1.add(rdoChuyenKhoang);
        rdoChuyenKhoang.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rdoChuyenKhoang.setText("Chuyển khoản");
        jPanel7.add(rdoChuyenKhoang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Trạng thái");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, -1, -1));

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTrangThai.setForeground(new java.awt.Color(0, 153, 153));
        lblTrangThai.setText("Đang xử lý");
        jPanel7.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Ghi chú:");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 464, -1, -1));

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane5.setViewportView(txtGhiChu);

        jPanel7.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 490, 438, 87));

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setText("Mới");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 96, 40));

        btnInHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnInHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/toystore/icon/icons8-print-25.png"))); // NOI18N
        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });
        jPanel7.add(btnInHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 590, -1, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Tổng tiền:");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, -1));

        lblTongTien.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(198, 47, 52));
        lblTongTien.setText("100.000vnd");
        jPanel7.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, 210, -1));

        lblThongBaoPhi.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblThongBaoPhi.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoPhi.setText("Thông báo");
        jPanel7.add(lblThongBaoPhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 216, 100, 30));

        lblThongBaoTienNhan.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblThongBaoTienNhan.setForeground(new java.awt.Color(204, 0, 0));
        lblThongBaoTienNhan.setText("Thông báo");
        jPanel7.add(lblThongBaoTienNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 120, -1));

        btnThanhToan1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhToan1.setText("Thanh toán");
        btnThanhToan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToan1ActionPerformed(evt);
            }
        });
        jPanel7.add(btnThanhToan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 590, -1, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel22.setText("Tiền thừa");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 292, -1, -1));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 450, 650));

        tblHoaDonCho.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblHoaDonCho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Người Tạo", "Thời Gian", "Tổng Tiền", "Trạng Thái", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDonCho.setSelectionBackground(new java.awt.Color(244, 126, 130));
        tblHoaDonCho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHoaDonChoMousePressed(evt);
            }
        });
        jScrollPane6.setViewportView(tblHoaDonCho);
        if (tblHoaDonCho.getColumnModel().getColumnCount() > 0) {
            tblHoaDonCho.getColumnModel().getColumn(1).setPreferredWidth(110);
            tblHoaDonCho.getColumnModel().getColumn(2).setPreferredWidth(60);
            tblHoaDonCho.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        jLabel24.setBackground(new java.awt.Color(244, 154, 157));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Hóa Đơn Chờ");
        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 590, 170));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 80, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1040, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        if (tblHoaDon.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một hóa đơn chờ hoặc thanh toán hóa đơn mới để in!");
            return;
        }
        if (checkselected) {
            JOptionPane.showMessageDialog(null, "Hãy chọn một hóa đơn chờ hoặc thanh toán hóa đơn hiện tại để in!");
            return;
        }
//        prinBill();
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void tblHoaDonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tblHoaDonMouseReleased

    private void mnuPopXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPopXoaActionPerformed
        for (int rowsp : tblHoaDon.getSelectedRows()) {
            listrow.add(rowsp);
        }
        Collections.sort(listrow);
        int number = -1;

        while (true) {
            if (listrow.size() == 0) {
                break;
            }
            number = listrow.get(listrow.size() - 1);
            listMaSP.remove(number);
            model.removeRow(listrow.get(listrow.size() - 1));
            listrow.remove(listrow.size() - 1);
            if (tblHoaDon.getRowCount() == 0) {
                txtTienThua.setText("0");
                txtTienNhan.setText("0");
            }
        }
    }//GEN-LAST:event_mnuPopXoaActionPerformed

    private void mnuPopRemoveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPopRemoveAllActionPerformed
//        cleadTable();

    }//GEN-LAST:event_mnuPopRemoveAllActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked


    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
//        SearchProduct();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void tblHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblHoaDonKeyReleased

    }//GEN-LAST:event_tblHoaDonKeyReleased

    private void txtChiPhiKhacCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtChiPhiKhacCaretUpdate
//        tinhPhiKhac();
    }//GEN-LAST:event_txtChiPhiKhacCaretUpdate

    private void txtTienNhanCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienNhanCaretUpdate
//        tinhTienNhan();

    }//GEN-LAST:event_txtTienNhanCaretUpdate

    private void txtTienSPCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTienSPCaretUpdate
        try {
            double tongtien = 0;
            double tienThua = 0;
            double tienSP = Double.parseDouble(txtTienSP.getText());
            double phi = Double.parseDouble(txtChiPhiKhac.getText());
            double tienNhan = Double.parseDouble(txtTienNhan.getText());
            if (!txtChiPhiKhac.getText().isEmpty() && !txtTienNhan.getText().isEmpty()) {

                tongtien = tienSP + phi - giatrivoucher;
                if (tongtien < 0) {
                    tongtien = 0;
                    lblTongTien.setText("0" + " VNĐ");
                } else {
                    lblTongTien.setText(df.format(tongtien) + " VNĐ");
                }
                tienThua = tienNhan - (tongtien);
                if (tienThua <= 0) {
                    txtTienThua.setText("0");
                } else {
                    String TienThuadf = String.format("%.0f", tienThua);
                    txtTienThua.setText("" + TienThuadf);
                }

                if (tienNhan < (phi + tienSP - giatrivoucher)) {
                    txtTienThua.setForeground(Color.red);
                    txtTienNhan.setForeground(Color.red);
                    lblThongBaoTienNhan.setText("(*)Chưa đủ tiền");
                    lblThongBaoTienNhan.setVisible(true);
                } else {
                    txtTienThua.setForeground(Color.black);
                    txtTienNhan.setForeground(Color.black);
                    lblThongBaoTienNhan.setVisible(false);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtTienSPCaretUpdate

    private void btnVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVCActionPerformed
//        QRScanne rScanne = new QRScanne(null, true);
//        rScanne.addWindowListener(new java.awt.event.WindowAdapter() {
//            @Override
//            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
//                outPutVC = scanResult;
//                if (!outPutVC.isEmpty()) {
//                    checkVC();
//                }
//            }
//        });
//        rScanne.setVisible(true);

    }//GEN-LAST:event_btnVCActionPerformed

    private void cboLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiSPActionPerformed
//        if (cboLoaiSP.getSelectedIndex() != 0) {
//            try {
//                listSP = SPDao.selectByLoaiSP(cboLoaiSP.getSelectedItem().toString());
//                if (listSP.size() == 0) {
//                    panelItem1.removeAll();
//                    panelItem1.repaint();
//                }
//                panelItem1.removeAll();
//                testData();
//            } catch (Exception e) {
//            }
//        } else {
//            fillPanelSP();
//        }


    }//GEN-LAST:event_cboLoaiSPActionPerformed

    private void tblHoaDonChoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMouseClicked
        checkselected = false;
        if (!checkselected) {
//            fillTableHoaDonCT();
        }
    }//GEN-LAST:event_tblHoaDonChoMouseClicked

    private void tblHoaDonChoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonChoMousePressed
//        if (evt.getClickCount() == 2) {
//            fillTableHoaDonCT();
//        }
    }//GEN-LAST:event_tblHoaDonChoMousePressed

    private void txtTienNhanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienNhanKeyTyped

    }//GEN-LAST:event_txtTienNhanKeyTyped

    private void txtTienNhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienNhanKeyPressed

    }//GEN-LAST:event_txtTienNhanKeyPressed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
//        cleadTable();
//        moForm();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnThanhToan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToan1ActionPerformed
//        if (checkThanhToan()) {
//            insertHD();
//            fillToTableHoaDon();
//        }

    }//GEN-LAST:event_btnThanhToan1ActionPerformed

    private void scroll1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_scroll1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_scroll1ComponentAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnThanhToan1;
    private javax.swing.JButton btnVC;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboLoaiSP;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblGiaTriVC;
    private javax.swing.JLabel lblThongBaoPhi;
    private javax.swing.JLabel lblThongBaoTienNhan;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblvoucher;
    private javax.swing.JMenuItem mnuPopRemoveAll;
    private javax.swing.JMenuItem mnuPopXoa;
    private com.toystore.swing.PanelItem panelItem1;
    private javax.swing.JRadioButton rdoChuyenKhoang;
    private javax.swing.JRadioButton rdoTienMat;
    private javax.swing.JScrollPane scroll1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonCho;
    private javax.swing.JTextField txtChiPhiKhac;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTienNhan;
    private javax.swing.JTextField txtTienSP;
    private javax.swing.JTextField txtTienThua;
    // End of variables declaration//GEN-END:variables

    public void init() {
//        fillComBoBoxLoaiSP();
//        fillToTableHoaDon();
        fillpanelItem();
        rdoTienMat.setSelected(true);
        txtTienSP.setEditable(false);
        txtTienThua.setEditable(false);
        txtChiPhiKhac.setText("0");
        txtTienNhan.setText("0");
        txtTienSP.setText("0");
        lblTongTien.setText("0 ");
        txtTienThua.setText("0");
        lblThongBaoTienNhan.setVisible(false);
        lblThongBaoPhi.setVisible(false);
        lblvoucher.setVisible(false);
        lblGiaTriVC.setVisible(false);
    }

//    public void loadData() {
//        listSP = SPDao.selectAll();
//        mnuPopXoa.setText("Xóa sản phẩm");
//        mnuPopRemoveAll.setText("Xóa tất cả");
//
//        // Tạo một DefaultCellEditor để chứa JSpinner
//    }
    private product itemSelected;

    public void testData() {

        setEvent(new EventItem() {
            @Override
            public void itemClick(Component com, product item) {
//                if (itemSelected != null) {
//                    //        mainPanel.setImageOld(itemSelected.getImage());
//                }
//                if (itemSelected != item) {
                //         if (!animator.isRunning()) {
                itemSelected = item;
                //         animatePoint = getLocationOf(com);
//                    mainPanel.setImage(item.getImage());
//                    //        mainPanel.setImageLocation(animatePoint);
//                    mainPanel.setImageSize(new Dimension(180, 120));
//                    mainPanel.repaint();
                setSelected(com);
//                    JOptionPane.showMessageDialog(null, "" + item.getMaSP() + " " + item.getTenSP());
                if (!checkselected) {
//                    cleadTable();
//                    moForm();
                    checkselected = true;
                }
//                if (checkSP(item)) {
//                    fillToTable(item);
//                }

                //home.showItem(item);
                //     animator.start();
                //    }
                //}
            }
        });
        for (product p : listSP) {
            if (p.isStatus()) {
                addItem(new product(
                        p.getProductId(),
                        p.getCategoryId(),
                        p.getBrandId(),
                        p.getAgeId(),
                        p.getMaterialId(),
                        p.getName(),
                        p.getPrice(),
                        p.getOriginalPrice(),
                        p.getCreatedAt(),
                        p.isStatus(),
                        p.getDescription(),
                        p.isSex(),
                        p.getImage(),
                        p.getQuantity(),
                        p.getBarcode(),
                        p.getUrlBarcode()
                ));
            }
        }
    }

    public void fillToTable(product sanPham) {
    }

//    public void fillToTable(SanPham sanPham) {
//        String giaFD = "";
//        model = (DefaultTableModel) tblHoaDon.getModel();
//        listKMSP = kmdao.selectAll();
//        if (sanPham.getKhuyenMai() != null) {
//            for (KhuyenMai km : listKMSP) {
//                if (sanPham.getKhuyenMai().equals(km.getMaKM()) && km.isTrangThai() == true) {
//                    if (km.isLoaiKM() == false) {
//                        giakm = sanPham.getGia() - km.getGiaKM();
//
//                    } else {
//                        giakm = Math.round(sanPham.getGia() - (sanPham.getGia() * (km.getGiaKM() / 100)));
//                    }
//
//                }
//            }
//        } else {
//            giakm = sanPham.getGia();
//        }
//        giaFD = df.format(giakm);
//        try {
//
//            Object[] row = {
//                sanPham.getTenSP(),
//                giaFD,
//                1,
//                giaFD
//            };
//            model.addTableModelListener(new TableModelListener() {
//                @Override
//                public void tableChanged(TableModelEvent e) {
//
//                    format.setParseBigDecimal(true);
//                    if (tblHoaDon.getRowCount() == 0) {
//                        taoHD = false;
//                    } else {
//                        if (taoHD == false && tblHoaDon.getRowCount() != 0) {
//                            taoHD = true;
//                            LocalDateTime currentDate2 = LocalDateTime.now();
//                            ngayTaoHD = formatter.format(currentDate2);
//                        }
//                    }
//
//                    double tongtien = 0;
//                    double Tien = 0;
//
//                    for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
//
//                        try {
//                            double gia2 = format.parse(tblHoaDon.getValueAt(i, 3).toString().replaceAll(",", "")).doubleValue();
//                            tongtien += gia2;
//                        } catch (ParseException ex) {
//                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//
//                    }
//
//                    Tien = tongtien / 1000;
//
//                    String Tiendf = String.format("%.0f", Tien);
//                    txtTienSP.setText(String.valueOf(Tiendf));
//
//                }
//
//            });
//
//            listMaSP.add(sanPham.getMaSP());
//            listGia.add(giakm);
//            listKM.add(sanPham.getKhuyenMai());
//            model.addRow(row);
//            tblHoaDon.getColumnModel().getColumn(2).setCellEditor((TableCellEditor) new SpinnerEditor());
//            tblHoaDon.getColumnModel().getColumn(2).setCellRenderer(new SpinnerRenderer());
//            tblHoaDon.setRowHeight(25);
//            tinhTienNhan();
//            tinhPhiKhac();
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//    }
    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

        public SpinnerEditor() {
            spinner = new JSpinner();
            spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
            spinner.setBorder(null);
            spinner.addChangeListener(new ChangeListener() {

                @Override
                public void stateChanged(ChangeEvent e) {
                    row = tblHoaDon.getEditingRow();
                    if (row != -1) {
                        newValue = (int) spinner.getValue();
                        tblHoaDon.setValueAt(newValue, row, 2);
                        double tongTien = listGia.get(row) * newValue;

                        tblHoaDon.setValueAt(df.format(tongTien), row, 3);
                    }

                    fireEditingStopped();
                    repaint();
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value);
            return spinner;
        }
    }

    class SpinnerRenderer extends JSpinner implements TableCellRenderer {

        public SpinnerRenderer() {
            setModel(new SpinnerNumberModel(1, 1, 100, 1));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setValue(value);
            return this;
        }
    }

//    public boolean checkSP(SanPham item) {
//        String giaFD2 = "";
//        for (int i = 0; i < listMaSP.size(); i++) {
//            if (item.getMaSP().equals(listMaSP.get(i))) {
//                if (item.getGia() != listGia.get(listGia.size() - 1)) {
//                    if (item.getKhuyenMai() != null) {
//                        for (KhuyenMai km : listKMSP) {
//                            if (item.getKhuyenMai().equals(km.getMaKM()) && km.isTrangThai() == true) {
//                                if (km.isLoaiKM() == false) {
//                                    giakm = item.getGia() - km.getGiaKM();
//
//                                } else {
//                                    giakm = Math.round(item.getGia() - (item.getGia() * (km.getGiaKM() / 100)));
//                                }
//
//                            }
//                        }
//                    } else {
//                        giakm = item.getGia();
//                    }
//
//                    listGia.add(giakm);
//                }
//
//                int currentValue = (int) tblHoaDon.getValueAt(i, 2);
//                tblHoaDon.setValueAt((currentValue + 1), i, 2);
//                double tongTien = giakm * (currentValue + 1);
//                giaFD2 = df.format(tongTien);
//                tblHoaDon.setValueAt(giaFD2, i, 3);
//                return false;
//            }
//        }
//        return true;
//    }
//    public void fillToTableHoaDon() {
//        modelHD = (DefaultTableModel) tblHoaDonCho.getModel();
//        modelHD.setRowCount(0);
//        listHD = hddao.selectAll();
//        listMaHD.clear();
//        List<Object[]> list = hddao.getHoaDon();
//        for (Object[] row : list) {
//            if (row[8].equals("Đang xử lý")) {
//                String maHD = row[0].toString();
//                listMaHD.add(maHD);
//                modelHD.addRow(new Object[]{row[0], row[9], row[1], String.format("%,.0f", row[4]), row[7]});
//            }
//
//        }
//
//    }
//
//    HoaDon getForm() {
//        HoaDon modelGF = new HoaDon();
//        listHD = hddao.selectAll();
//        if (listHD.size() == 0) {
//            modelGF.setMaHD("HD1");
//            maHDInsert = "HD1";
//        } else {
//            String mahd = listHD.get(listHD.size() - 1).getMaHD();
//            mahd = mahd.substring(2);
//            int mahdint = Integer.parseInt(mahd);
//            mahdint += 1;
//            mahd = String.valueOf("HD" + mahdint);
//            modelGF.setMaHD(mahd);
//            maHDInsert = mahd;
//        }
//
//        LocalDateTime currentDate = LocalDateTime.now();
//        String ngayCT = formatter.format(currentDate);
//        LocalDateTime parsedDate = LocalDateTime.parse(ngayCT, formatter);
//        ngayTao = convertLocalDateTimeToDate(parsedDate);
//        LocalDateTime parsedDate2 = LocalDateTime.parse(ngayTaoHD, formatter);
//        ngayTao2 = convertLocalDateTimeToDate(parsedDate2);
//        modelGF.setThoiGianTao(ngayTao2);
//        modelGF.setThoiGianThanhToan(ngayTao);
//        modelGF.setNguoiTao(XAuth.user.getMaNV());
//        String tongTien = lblTongTien.getText().replaceAll("VNĐ", "");
//        String tongTien2 = tongTien.replaceAll(",", "");
//        modelGF.setTongTien(Double.parseDouble(tongTien2));
//        modelGF.setHinhThucThanhToan(rdoTienMat.isSelected() ? "Tiền Mặt" : "Chuyển Khoảng");
//        modelGF.setGhiChu(txtGhiChu.getText());
//        modelGF.setTrangThai(lblTrangThai.getText());
//        modelGF.setTenNguoiTao(XAuth.user.getTenNV());
//        modelGF.setTienNhan(Double.parseDouble(txtTienNhan.getText()));
//        modelGF.setChiPhiKhac(Double.parseDouble(txtChiPhiKhac.getText()));
//        if (giatrivoucher != 0) {
//            modelGF.setVoucher("Voucher " + String.valueOf(giatrivoucher));
//        } else {
//            modelGF.setVoucher("");
//        }
//        return modelGF;
//    }
//
//    private static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
//        return java.sql.Timestamp.valueOf(localDateTime);
//    }
//
//    public void addHDCT() {
//        for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
//            HoaDonChiTiet hdct = new HoaDonChiTiet();
//            hdct.setMaHD(maHDInsert);
//            hdct.setMaSP(listMaSP.get(i));
//            hdct.setSoLuong((int) tblHoaDon.getValueAt(i, 2));
//            hdct.setGiaSP(listGia.get(i));
//            String TT = String.format("%.0f", Double.parseDouble(tblHoaDon.getValueAt(i, 3).toString().replaceAll(",", "")));
//            hdct.setTongTien(Double.parseDouble(TT));
//            hdct.setMaKM(listKM.get(i));
//            hdctdao.insert(hdct);
//        }
//    }
//
//    public void cleadTable() {
//        model = (DefaultTableModel) tblHoaDon.getModel();
//        while (model.getRowCount() > 0) {
//            model.removeRow(0);
//            listMaSP.clear();
//            listGia.clear();
//            listKM.clear();
//        }
//        maHDInsert = "";
//        ngayTaoHD = "";
//        txtTienNhan.setText("0");
//        txtTienThua.setText("0");
//        txtTienSP.setText("0");
//        lblTongTien.setText("0");
//        maVoucher = 0;
//        lblvoucher.setVisible(false);
//        lblGiaTriVC.setVisible(false);
//    }
//
//    public void insertHD() {
//        HoaDon hd = getForm();
//        prinBill();
//        try {
//            hddao.insert(hd);
//            addHDCT();
//            fillToTableHoaDon();
//            if (maVoucher != 0) {
//                kmdao.deleteVoucher(maVoucher);
//            }
//            cleadTable();
////            System.out.println("ma insert: " + maVoucher);
//
//            txtTienNhan.setText("0");
//            txtTienThua.setText("0");
//            txtGhiChu.setText("");
//            maVoucher = 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
    productDAO productDao;
    List<product> listSP = new ArrayList<>();

    public void fillPanelSP() throws SQLException {
        listSP = productDao.getAllProducts();
        panelItem1.removeAll();
        testData();
    }
//
//    public void SearchProduct() {
//        listSP = SPDao.selectByKeywordProduct(txtSearch.getText());
//        if (listSP.size() == 0) {
//            panelItem1.removeAll();
//            panelItem1.repaint();
//        }
//        panelItem1.removeAll();
//        testData();
//    }
//
//    public void fillComBoBoxLoaiSP() {
//        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboLoaiSP.getModel();
//        boxModel.removeAllElements();
//        List<LoaiSanPham> listloaiSP = sanPhamDao.selectAll();
//        boxModel.addElement("Tất cả");
//        for (LoaiSanPham loaiSanPham : listloaiSP) {
//            boxModel.addElement(loaiSanPham);
//        }
//        cboLoaiSP.setSelectedIndex(0);
//
//    }
//
//    public void prinBill() {
//        listHD = hddao.selectAll();
//        Date taoLuc = null;
//        Date thanhToanLuc = null;
//        String nameNV = "";
//        if (!checkselected) {
//            maHDInsert = ma;
//            taoLuc = bd;
//            thanhToanLuc = kt;
//            nameNV = tNV;
//        } else {
//            if (listHD.size() == 0) {
//                maHDInsert = "HD1";
//            } else {
//                taoLuc = ngayTao2;
//                thanhToanLuc = ngayTao;
//                nameNV = XAuth.user.getTenNV();
//                String mahd = listHD.get(listHD.size() - 1).getMaHD();
//                mahd = mahd.substring(2);
//                int mahdint = Integer.parseInt(mahd);
//                mahdint += 1;
//                mahd = String.valueOf("HD" + mahdint);
//                maHDInsert = mahd;
//            }
//        }
//
//        Date dateBill = new Date();
//        String DateBill = sdf.format(dateBill);
//        String TT = lblTongTien.getText().replaceAll("VNĐ", "");
//        String phiKhac = "0";
//        if (txtChiPhiKhac.getText().equals("0")) {
//            phiKhac = "0";
//        } else {
//            phiKhac = df.format(Double.parseDouble(txtChiPhiKhac.getText()));
//        }
//        try {
//            bill = new JTextPane();
//
//            bill.setContentType("text/html");
//
//            // Nội dung hóa đơn của bạn
//            StringBuilder billContent = new StringBuilder("<html><head>\n"
//                    + "<style>\n"
//                    + "body {\n"
//                    + "    width: 100%;\n"
//                    + "height: auto;"
//                    + "}\n"
//                    + "</style>\n"
//                    + "</head><body>");
//            billContent.append("<p align='center'; ><b>PEACH COFFEE<br>ĐC: 789/JQK Đường s ố 1, Cái Răng, CT<br>STĐ: 098712345</b>"
//                    + "<br>--------------------"
//                    + "<br><b>HÓA ĐƠN THANH TOÁN<br>Mã Hóa Đơn: ").append(maHDInsert).append("<br>Ngày: ").append(DateBill).append("</b></p>");
//            billContent.append("<p><b>Tạo lúc: </b>").append(taoLuc).append("<br><b>Thanh toán lúc: </b>").append(thanhToanLuc).append("<br><b>Thu ngân: </b>").append(nameNV).append("</p>");
//            billContent.append("<hr>");
//            billContent.append("<table width='100%'>");
//
//// Thêm hàng tiêu đề với canh lề cụ thể cho từng cột
//            billContent.append("<tr><th style='width:65%; text-align:left;'>Tên sản phẩm</th><th style='width:10%; text-align:center;'>SL</th><th style='width:25%; text-align:right;'>TT</th></tr>");
//
//            if (tblHoaDon.getRowCount() != 0) {
//                for (int i = 0; i < tblHoaDon.getRowCount(); i++) {
//                    String tenSp = tblHoaDon.getValueAt(i, 0).toString();
//                    String soluong = tblHoaDon.getValueAt(i, 2).toString();
//                    String tongTien = tblHoaDon.getValueAt(i, 3).toString();
//
//                    // Áp dụng canh lề trực tiếp cho dữ liệu cột
//                    billContent.append("<tr><td style='text-align:left;'>").append(tenSp)
//                            .append("</td><td style='text-align:center;'>").append(soluong)
//                            .append("</td><td style='text-align:right;'>").append(tongTien.toString()).append("</td></tr>");
//                }
//
//            }
//
//            billContent.append("</table>");
//            billContent.append("<hr>");
//            billContent.append("<table width='100%'>");
//            billContent.append("<tr><td style='text-align:left;'><b>").append("Tiền sản phẩm: ").append("</b></td><td style='text-align:right;'><b>").append(df.format(Double.parseDouble(txtTienSP.getText()))).append("</b></td></tr>");
//            billContent.append("<tr><td style='text-align:left;'><b>").append("Chi phí khác: ").append("</b></td><td style='text-align:right;'><b>").append(phiKhac).append("</b></td></tr>");
//            billContent.append("<tr><td style='text-align:left;'><b>").append("Tổng tiền: ").append("</b></td><td style='text-align:right;'><b>").append(TT).append("</b></td></tr>");
//            billContent.append("<tr><td style='text-align:left;'><b>").append("Tiền nhận: ").append("</b></td><td style='text-align:right;'><b>").append(df.format(Double.parseDouble(txtTienNhan.getText()))).append("</b></td></tr>");
//            billContent.append("<tr><td style='text-align:left;'><b>").append("Tiền thừa: ").append("</b></td><td style='text-align:right;'><b>").append(df.format(Double.parseDouble(txtTienThua.getText()))).append("</b></td></tr>");
//            billContent.append("</table>");
//            billContent.append("<p align='center'>--------------------"
//                    + "<br><b>Pass wifi: thanbai888</b>"
//                    + "<br<b>Xin cảm ơn, hẹn gặp lại quý khách!</b></p>");
//            billContent.append("</body></html>");
//            Font font = new Font("Arial", Font.PLAIN, 8);
//
//            bill.setText(billContent.toString());
//            bill.setFont(font);
//            JOptionPane.showMessageDialog(null, bill);
//            bill.print();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void fillTableHoaDonCT() {
//        DefaultTableModel dtm = (DefaultTableModel) tblHoaDon.getModel();
//        dtm.setRowCount(0);
//        double tienSp = 0;
//        row2 = tblHoaDonCho.getSelectedRow();
//        System.out.println("" + row2);
//        ma = listMaHD.get(row2);
//        System.out.println("" + ma);
//        List<Object[]> listCT = hddao.getHoaDonCT(ma);
//        List<Object[]> listHDBill = hddao.getHoaDonMenu(ma);
//        System.out.println("" + ma);
//        for (Object[] listHD : listCT) {
//            double lst3 = (double) listHD[3];
//            Object[] row = {
//                listHD[0],
//                df.format(listHD[1]),
//                listHD[2],
//                df.format(lst3 / 1000)
//            };
//            double tienSPlist = (double) listHD[3];
//            tienSp += tienSPlist;
//            dtm.addRow(row);
//        }
//        for (Object[] hoaDon : listHDBill) {
//            bd = (Date) hoaDon[1];
//            kt = (Date) hoaDon[2];
//            tNV = (String) hoaDon[9];
//            double phiKhac = (double) hoaDon[5];
//            txtTienSP.setText(String.valueOf(String.format("%.0f", tienSp / 1000)));
//            txtChiPhiKhac.setText(String.valueOf(String.format("%.0f", phiKhac)));
//            if (hoaDon[6].toString().equals("Tiền Mặt")) {
//                rdoTienMat.setSelected(true);
//            } else {
//                rdoChuyenKhoang.setSelected(true);
//            }
//            lblTongTien.setText(String.valueOf(df.format(hoaDon[4])));
//            txtTienNhan.setText(String.valueOf(String.format("%.0f", hoaDon[10])));
//            String gt = String.valueOf(hoaDon[12]);
//            System.out.println("" + gt);
//            if (gt.equals("null")) {
//                System.out.println("if" + gt);
//                lblvoucher.setVisible(true);
//                lblGiaTriVC.setVisible(true);
//                lblGiaTriVC.setText("Không có");
//            } else {
//                System.out.println("else" + gt);
//                lblvoucher.setVisible(true);
//                lblGiaTriVC.setVisible(true);
//                lblGiaTriVC.setText(gt);
//            }
//        }
//        khoaForm();
//
//    }
//
//    public boolean checkTienNhan() {
//        try {
//            double tienNhan = Double.parseDouble(txtTienNhan.getText());
//            txtTienThua.setForeground(Color.black);
//            txtTienNhan.setForeground(Color.black);
//            lblThongBaoTienNhan.setVisible(false);
//            alterTien = "";
//        } catch (Exception e) {
//            alterTien = "Tiền nhận chưa đúng định dạng";
//            txtTienThua.setForeground(Color.red);
//            txtTienNhan.setForeground(Color.red);
//            lblThongBaoTienNhan.setText("(*)Sai định dạng");
//            lblThongBaoTienNhan.setVisible(true);
//            return false;
//        }
//        return true;
//    }
//
//    public boolean checkPhiKhac() {
//        try {
//            double phiKhac = Double.parseDouble(txtChiPhiKhac.getText());
//            if (phiKhac < 0) {
//                alterPhi = "Chi phí khác đang âm";
//                txtChiPhiKhac.setForeground(Color.red);
//                lblThongBaoPhi.setText("(*)Phí đang âm");
//                lblThongBaoPhi.setVisible(true);
//                return false;
//            }
//            alterPhi = "";
//            txtChiPhiKhac.setForeground(Color.black);
//            lblThongBaoPhi.setVisible(false);
//            alterPhi = "";
//        } catch (Exception e) {
//            alterPhi = "Chi phí khác chưa đúng định dạng";
//            txtChiPhiKhac.setForeground(Color.red);
//            lblThongBaoPhi.setText("(*)Sai định dạng");
//            lblThongBaoPhi.setVisible(true);
//            return false;
//        }
//        return true;
//    }
//
//    public void tinhTienNhan() {
//        if (tblHoaDon.getRowCount() != 0) {
//            if (checkTienNhan()) {
//                try {
//                    double tongtien = 0;
//                    double tienThua = 0;
//                    double tienSP = Double.parseDouble(txtTienSP.getText());
//                    double phi = Double.parseDouble(txtChiPhiKhac.getText());
//                    double tienNhan = Double.parseDouble(txtTienNhan.getText());
//
//                    if (!txtChiPhiKhac.getText().isEmpty() && !txtTienNhan.getText().isEmpty()) {
//                        if (tienNhan < (phi + tienSP - giatrivoucher)) {
//                            alterTien = "Tiền nhận chưa đủ";
//                            txtTienThua.setForeground(Color.red);
//                            txtTienNhan.setForeground(Color.red);
//                            lblThongBaoTienNhan.setText("(*)Chưa đủ tiền");
//                            lblThongBaoTienNhan.setVisible(true);
//                        } else {
//                            alterTien = "";
//                            txtTienThua.setForeground(Color.black);
//                            txtTienNhan.setForeground(Color.black);
//                            lblThongBaoTienNhan.setVisible(false);
//                        }
//                        tongtien = tienSP + phi - giatrivoucher;
//                        if (tongtien < 0) {
//                            tongtien = 0;
//                            lblTongTien.setText("0" + " VNĐ");
//                        } else {
//                            lblTongTien.setText(df.format(tongtien) + " VNĐ");
//                        }
//                        tienThua = tienNhan - tongtien;
//                        if (tienThua <= 0) {
//                            txtTienThua.setText("0");
//                        } else {
//                            String TienThuadf = String.format("%.0f", tienThua);
//                            txtTienThua.setText("" + TienThuadf);
//                        }
////                        String Thua = String.format("%.0f", tienThua);
//
////                        txtTienThua.setText("" + Thua);
//                    }
//                } catch (Exception e) {
//                }
//            }
//        } else {
//            txtTienThua.setForeground(Color.black);
//            txtTienNhan.setForeground(Color.black);
//            lblThongBaoTienNhan.setVisible(false);
//        }
//    }
//
//    public void tinhPhiKhac() {
//        if (tblHoaDon.getRowCount() != 0) {
//            if (checkPhiKhac()) {
//                try {
//                    double tongtien = 0;
//                    double tienThua = 0;
//                    double tienSP = Double.parseDouble(txtTienSP.getText());
//                    double phi = Double.parseDouble(txtChiPhiKhac.getText());
//                    double tienNhan = Double.parseDouble(txtTienNhan.getText());
//                    if (!txtChiPhiKhac.getText().isEmpty() && !txtTienNhan.getText().isEmpty()) {
//                      
//                        tongtien = tienSP + phi - giatrivoucher;
//                          tienThua = tienNhan - tongtien;
//                        if (tongtien < 0) {
//                            tongtien = 0;
//                            lblTongTien.setText("0" + " VNĐ");
//                        } else {
//                            lblTongTien.setText(df.format(tongtien) + " VNĐ");
//                        }
//                        if (tienThua <= 0) {
//                            txtTienThua.setText("0");
//                        } else {
//                            String TienThuadf = String.format("%.0f", tienThua);
//                            txtTienThua.setText("" + TienThuadf);
//                        }
////                        String Thua = String.format("%.0f", tienThua);
////                        txtTienThua.setText("" + Thua);
////                        lblTongTien.setText(df.format(tongtien) + " VNĐ");
//                        if (tienNhan < (phi + tienSP - giatrivoucher)) {
//                            alterTien = "Tiền nhận chưa đủ";
//                            txtTienThua.setForeground(Color.red);
//                            txtTienNhan.setForeground(Color.red);
//                            lblThongBaoTienNhan.setText("(*)Chưa đủ tiền");
//                            lblThongBaoTienNhan.setVisible(true);
//                        } else {
//                            alterTien = "";
//                            txtTienThua.setForeground(Color.black);
//                            txtTienNhan.setForeground(Color.black);
//                            lblThongBaoTienNhan.setVisible(false);
//                        }
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//    }
//
//    public boolean checkThanhToan() {
//        if (tblHoaDon.getRowCount() == 0) {
//            JOptionPane.showMessageDialog(null, "Hãy thêm sản phẩm vào hóa đơn!");
//            return false;
//        }
//        if (!alterPhi.isEmpty()) {
//            JOptionPane.showMessageDialog(null, alterPhi);
//            return false;
//        }
//        if (!alterTien.isEmpty()) {
//            JOptionPane.showMessageDialog(null, alterTien);
//            return false;
//        }
//        return true;
//    }
//
//    public void khoaForm() {
//        tblHoaDon.setEnabled(false);
//        txtTienNhan.setEditable(false);
//        txtChiPhiKhac.setEditable(false);
//        btnVC.setEnabled(false);
//        btnThanhToan1.setEnabled(false);
//        rdoChuyenKhoang.setEnabled(false);
//        rdoTienMat.setEnabled(false);
//    }
//
//    public void moForm() {
//        tblHoaDon.setEnabled(true);
//        txtTienNhan.setEditable(true);
//        txtChiPhiKhac.setEditable(true);
//        btnVC.setEnabled(true);
//        rdoChuyenKhoang.setEnabled(true);
//        rdoTienMat.setEnabled(true);
//        btnThanhToan1.setEnabled(true);
//    }
//
//    public void checkVC() {
//        try {
//            MahoaQR = kmdao.getMaHoaQR();
//            for (Object[] vc : MahoaQR) {
//                if (outPutVC.equals(vc[2])) {
//                    String giaTriVC = String.valueOf(vc[1]);
//                    lblvoucher.setVisible(true);
//                    lblGiaTriVC.setVisible(true);
//                    lblGiaTriVC.setText(giaTriVC);
//                    giatrivoucher = Integer.parseInt(vc[1].toString());
//                    maVoucher = Integer.parseInt(vc[0].toString());
//                    System.out.println("ma them" + maVoucher);
//                    tinhTienNhan();
//                    JOptionPane.showMessageDialog(null, "Áp dụng voucher thành công");
//                    return;
//                }
//            }
//            JOptionPane.showMessageDialog(null, "Không tìm thấy voucher");
//        } catch (Exception e) {
////            lblvoucher.setVisible(false);
////            lblGiaTriVC.setVisible(false);
////            lblGiaTriVC.setText("");
////            giatrivoucher = 0;
//
//            e.printStackTrace();
//        }
//
//    }
}
