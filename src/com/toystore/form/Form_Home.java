package com.toystore.form;

import com.toystore.chart.ModelChart;
import com.toystore.dao.ThongKeDao;
import com.toystore.dao.store.OrderDAO;
import com.toystore.model.Model_Card;
import com.toystore.model.StatusType;
import com.toystore.model.store.Order;
import com.toystore.swing.ButtonRendererEditor;
import com.toystore.swing.ScrollBar;
import com.toystore.utils.XDate;
import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

public class Form_Home extends javax.swing.JPanel {

    public Form_Home() {
        initComponents();

        filltable1();
        lineChart1.addLegend("áo", new Color(12, 84, 175), new Color(0, 108, 247));
        lineChart1.addLegend("quần", new Color(54, 4, 143), new Color(104, 49, 200));
        lineChart1.addLegend("áo khóa", new Color(5, 125, 0), new Color(95, 209, 69));
//        lineChart1.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
        lineChart1.addData(new ModelChart("January", new double[]{500, 200, 80}));
        lineChart1.addData(new ModelChart("February", new double[]{600, 750, 90}));
        lineChart1.addData(new ModelChart("March", new double[]{200, 350, 460}));
        lineChart1.addData(new ModelChart("April", new double[]{480, 150, 750}));
        lineChart1.addData(new ModelChart("May", new double[]{350, 540, 300}));
        lineChart1.addData(new ModelChart("June", new double[]{190, 280, 81}));
        lineChart1.start();

        ThongKeDao dao = new ThongKeDao();
        // đã bán
        Date ngay;
        String soLuong = "11212";
        String tongtienString = "12323432";
//        List<Object[]> list = dao.daban();
//        for (Object[] db : list) {
//            // Giả sử vị trí 0 là soLuong và vị trí 1 là tongtienString
//            soLuong = String.valueOf(db[0]);
//            tongtienString = String.valueOf(db[1]);
//        }
        Date n = XDate.now();
        String nn = XDate.toString(n, "dd-MM-yyyy");
        List<Object[]> list1 = dao.theoNgay();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/toystore/icon/stock.png")), "ngày " + nn, tongtienString + " VN", "Số lượng SP " + soLuong + " d/c"));

        if (list1 == null) {
            return;
        }
        for (Object[] db : list1) {

            soLuong = String.valueOf(db[1]);
            tongtienString = String.valueOf(db[2]);
            card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/toystore/icon/stock.png")), "ngày " + nn, tongtienString + " VN", "Số lượng SP " + soLuong + " d/c"));
        }

        List<Object[]> list2 = dao.theoThang();
        if (list2 == null) {
            return;
        }
        for (Object[] db : list2) {
            soLuong = String.valueOf(db[1]);
            tongtienString = String.valueOf(db[2]);
            card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/toystore/icon/stock.png")), "ngày " + nn, tongtienString + " VN", "Số lượng SP " + soLuong + " d/c"));

        }

        List<Object[]> list3 = dao.theoNam();
        if (list3 == null) {
            return;
        }
        for (Object[] db : list3) {
            soLuong = String.valueOf(db[1]);
            tongtienString = String.valueOf(db[2]);
            card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/toystore/icon/stock.png")), "ngày " + nn, tongtienString + " VN", "Số lượng SP " + soLuong + " d/c"));

        }

        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
//            bản lịch sử        
        String row[] = {"Mã HDadaf", " Ngay Mua", "Tên KH", "Ten SP", "Số lượng", " Thành tiền"};
        DefaultTableModel model1 = new DefaultTableModel(row, 0);
        model1.setRowCount(0);
        List<Object[]> listLichsu = dao.lichsu();
        for (Object[] objects : listLichsu) {
            model1.addRow(objects);
        }
//        tblLichSu.setModel(model1);
        //
        String[] cols = {"TenLoaiSanPham", "TongSoLuongTonKho"};
        DefaultTableModel model2 = new DefaultTableModel(cols, 0);
        model2.setRowCount(0);
        List<Object[]> listkho = dao.Tonkho();
        for (Object[] objects : listkho) {
            model2.addRow(objects);
        }

//        tblKho.setModel(model2);
//        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/profit.png")), "Total Profit", "$15000", "Increased by 25%"));
//        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/flag.png")), "Unique Visitors", "$300000", "Increased by 70%"));
        // table
//        spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
//        table.addRow(new Object[]{"kkkkkkk", "mikebhand@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018", StatusType.REJECT});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018", StatusType.REJECT});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        table.addRow(new Object[]{"Andrew Strauss", "andrewstrauss@gmail.com", "Editor", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Ross Kopelman", "rosskopelman@gmail.com", "Subscriber", "25 Apr,2018", StatusType.APPROVED});
//        table.addRow(new Object[]{"Mike Hussy", "mikehussy@gmail.com", "Admin", "25 Apr,2018", StatusType.REJECT});
//        table.addRow(new Object[]{"Kevin Pietersen", "kevinpietersen@gmail.com", "Admin", "25 Apr,2018", StatusType.PENDING});
//        tableCategory.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
//            @Override
//            protected void configureScrollBarColors() {
//                this.thumbColor = new Color(51, 51, 51); // Màu của thanh cuộn
//            }
//        });
//        tableCategory1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
//            @Override
//            protected void configureScrollBarColors() {
//                this.thumbColor = new Color(100, 100, 100); // Màu của thanh cuộn
//            }
//        });
    }

    OrderDAO orderDao = new OrderDAO();
    DecimalFormat df = new DecimalFormat("#,###,###,###");
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void filltable1() {
        String row1[] = {"Mã SP", "Tên SP", "Giá SP", "Lượng SP", "Trạng thái SP", "Bar CODE", "___"};
        DefaultTableModel model = new DefaultTableModel(row1, 0);
        model.setRowCount(0);
//        DefaultTableModel model = new DefaultTableModel();
        tableCategory1.getVerticalScrollBar().setPreferredSize(new Dimension(2, 0));
        tableCategory1.getHorizontalScrollBar().setPreferredSize(new Dimension(2, 0));

        // Thêm các cột
        model.setColumnIdentifiers(new Object[]{"ID", "Date", "TotalAmount"});
        table1.setModel(model);
//        table.setBackground(Color.BLUE);
        table1.setForeground(Color.WHITE);

        // Thêm dữ liệu mẫu
//        table1.addRow(new Object[]{1, "Gundam RX-78", 10, 500000, 500000});
//        table1.addRow(new Object[]{2, "Lego Star Wars", 5, 1200000, 500000});
//        table1.addRow(new Object[]{3, "Hot Wheels Ferrari", 15, 200000, 500000});
//        table1.addRow(new Object[]{4, "Gundam RX-78", 10, 500000, 500000});
//        table1.addRow(new Object[]{5, "Lego Star Wars", 5, 1200000, 500000});
//        table1.addRow(new Object[]{6, "Hot Wheels Ferrari", 15, 200000, 500000});
//        table1.addRow(new Object[]{7, "Gundam RX-78", 10, 500000, 500000});
//        table1.addRow(new Object[]{8, "Lego Star Wars", 5, 1200000, 500000});
//        table1.addRow(new Object[]{9, "Hot Wheels Ferrari", 15, 200000, 500000});
        // Áp dụng Renderer & Editor cho cột nút
        List<Order> list = orderDao.findAll();

        Collections.reverse(list);
//        table1.getColumnModel().getColumn(4).setCellRenderer(new ButtonRendererEditor());
//        table1.getColumnModel().getColumn(4).setCellEditor(new ButtonRendererEditor());
        for (Order o : list) {
            model.addRow(new Object[]{
                o.getOrderId(),
                o.getOrderDate(),
                df.format(o.getTotalAmount()) + " VND", //                StatusType.PENDING
            });
        }
        table1.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.toystore.component.Card();
        card2 = new com.toystore.component.Card();
        card3 = new com.toystore.component.Card();
        panelBorder1 = new com.toystore.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        panelBorder2 = new com.toystore.swing.PanelBorder();
        lineChart1 = new com.toystore.chart.LineChart();
        tableCategory1 = new javax.swing.JScrollPane();
        table1 = new com.toystore.swing.Table();

        setBackground(new java.awt.Color(170, 211, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setEnabled(false);
        setMinimumSize(new java.awt.Dimension(1040, 720));

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(170, 211, 255));
        panelBorder1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Purchase History");
        panelBorder1.add(jLabel1);
        jLabel1.setBounds(80, 0, 180, 30);
        jLabel1.getAccessibleContext().setAccessibleName("Lịch sử mau hàng");

        panelBorder2.setBackground(new java.awt.Color(51, 51, 51));
        panelBorder2.setLayout(new javax.swing.BoxLayout(panelBorder2, javax.swing.BoxLayout.LINE_AXIS));

        lineChart1.setBackground(new java.awt.Color(0, 0, 51));
        lineChart1.setForeground(new java.awt.Color(0, 0, 0));
        panelBorder2.add(lineChart1);

        panelBorder1.add(panelBorder2);
        panelBorder2.setBounds(357, 44, 670, 430);

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        tableCategory1.setViewportView(table1);

        panelBorder1.add(tableCategory1);
        tableCategory1.setBounds(10, 40, 330, 430);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addComponent(panelBorder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_table1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toystore.component.Card card1;
    private com.toystore.component.Card card2;
    private com.toystore.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private com.toystore.chart.LineChart lineChart1;
    private javax.swing.JLayeredPane panel;
    private com.toystore.swing.PanelBorder panelBorder1;
    private com.toystore.swing.PanelBorder panelBorder2;
    private com.toystore.swing.Table table1;
    private javax.swing.JScrollPane tableCategory1;
    // End of variables declaration//GEN-END:variables
}
