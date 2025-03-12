package com.toystore.component;

import com.toystore.event.EventMenuSelected;
import com.toystore.model.Model_Menu;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

public class Menu extends javax.swing.JPanel {

    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        listMenu2.addEventMenuSelected(event);
    }

    public Menu() {
        initComponents();
        setOpaque(false);
        listMenu2.setOpaque(false);
        init();
    }

    private void init() {
        listMenu2.addItem(new Model_Menu("1", "Trang chủ", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("2", "Khách hàng", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("3", "Đơn hàng", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("4", "Sản phẩm", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("5", "Nhân viên", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("6", "POS menu", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("7", "Category", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("5", "Attributes", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("", "My Data____________", Model_Menu.MenuType.TITLE));

        listMenu2.addItem(new Model_Menu("7", "Đăng xuất", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu2.addItem(new Model_Menu("8", "Thống kê", Model_Menu.MenuType.MENU));
        listMenu2.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu2.addItem(new Model_Menu("", "", Model_Menu.MenuType.EMPTY));
        listMenu2.addItem(new Model_Menu("", " ", Model_Menu.MenuType.EMPTY));
        listMenu2.addItem(new Model_Menu("", " ", Model_Menu.MenuType.EMPTY));
        listMenu2.addItem(new Model_Menu("10", "Thoát", Model_Menu.MenuType.MENU));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listMenu2 = new com.toystore.swing.ListMenu<>();
        panelMoving = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        listMenu2.setToolTipText("");
        listMenu2.setVisibleRowCount(11);

        panelMoving.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/toystore/icon/Logonen1.png"))); // NOI18N
        jLabel1.setText("lightning");
        jLabel1.setAutoscrolls(true);

        javax.swing.GroupLayout panelMovingLayout = new javax.swing.GroupLayout(panelMoving);
        panelMoving.setLayout(panelMovingLayout);
        panelMovingLayout.setHorizontalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovingLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelMovingLayout.setVerticalGroup(
            panelMovingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMovingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMoving, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(listMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMoving, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
//#1CB5E0

    @Override
    // chỉnh màu menu
    protected void paintChildren(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        GradientPaint g = new GradientPaint(0, 0, Color.decode("#2E344E"), 0, getHeight(), Color.decode("#DE116E"));
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#63B8FF"), 0, getHeight(), Color.decode("#5CACEE"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        g2.fillRect(getWidth() - 20, 0, getWidth(), getHeight());
        super.paintChildren(grphcs);
    }

    private int x;
    private int y;

    public void initMoving(JFrame fram) {
        panelMoving.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }

        });
        panelMoving.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                fram.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private com.toystore.swing.ListMenu<String> listMenu2;
    private javax.swing.JPanel panelMoving;
    // End of variables declaration//GEN-END:variables
}
