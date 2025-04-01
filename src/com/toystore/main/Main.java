/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.toystore.main;

import com.toystore.event.EventMenuSelected;
import com.toystore.form.Form_1;
import com.toystore.form.Form_3;
import com.toystore.form.Form_Home;
import com.toystore.form.KhachHangView;
import com.toystore.form.Login;
import com.toystore.form.PosForm;
import com.toystore.form.loadJDialog;
import com.toystore.form.store.Menu;
import com.toystore.form.store.Menu1;
import com.toystore.form.store.OrderView;
import com.toystore.form.store.categoryView;
import com.toystore.form.store.productAttributesView;
import com.toystore.form.store.productView;
import com.toystore.form.store.staffView;
import com.toystore.utils.Auth;
import com.toystore.utils.XImage;
import com.toystore.utils.MsgBox;

import java.awt.Color;
import javax.swing.JComponent;

public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private Form_Home home;

    private Form_3 form3;

    private Login form1;
    private PosForm PosForm;

//    private Menu1 posMenu;
    private Menu posMenu;
    private productView pView;
    private categoryView cView;
    private productAttributesView prAView;
    private staffView stView;
    private OrderView orderView;
    private KhachHangView khachhang;

    public Main() {
        initComponents();
        this.setSize(1200, 800);
        this.setLocationRelativeTo(null);
//                setBackground(new Color(0, 0, 0, 0));
//                SwingUtilities.invokeLater(() -> {
//            setExtendedState(Main.MAXIMIZED_BOTH);
//            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//            // Tắt chế độ fullscreen
//            gd.setFullScreenWindow(null);
//        });

        setIconImage(XImage.getAppicon());
        setBackground(new Color(0, 0, 0, 0));
        home = new Form_Home();
        form1 = new Login();
//        form2 = new HoaDonView();
        form3 = new Form_3();
//        form4 = new NhanVienView();
        PosForm = new PosForm();

        khachhang = new KhachHangView();
//        form5 = new SanPhamView1();

        posMenu = new Menu();
        pView = new productView();
        cView = new categoryView();
        prAView = new productAttributesView();
        stView = new staffView();
        orderView = new OrderView();
        menu.initMoving(Main.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    System.out.println("bạn ");
                } else if (index == 1) {
                    setForm(home);
//                    setForm(khachhang);
                } else if (index == 2) {
                    setForm(orderView);
                } else if (index == 3) {
                    if (!Auth.isLoginAccount()) {// if (Auth.isManager()) {
                        setForm(pView); // view product
                    } else {
                        MsgBox.alert(null, "bạn không có quyền thao tác !");
                    }
                } else if (index == 4) {
                    setForm(stView);// form 4 this is from staff
                } else if (index == 5) {
                    setForm(posMenu);  // pos menu view
                } else if (index == 6) {
                    setForm(cView);// superCategory
                } else if (index == 7) {
                    setForm(prAView);// product attributes view
                } else if (index == 8) {
                    setForm(home);
                } else if (index == 9) {
                    if (MsgBox.confirm(null, "bạn có chắc muốn đăng xuất ?")) {
                        Login d = new Login();
                        d.setVisible(true);
                        Auth.clear();
                        dispose();
                    }
                } else if (index == 16) {
                    if (MsgBox.confirm(null, "bạn có chắc muốn thoát ứng dụng ?")) {
                        dispose();
                    }
                }
            }
        });
        setForm(new Form_Home());
    }

    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    void openlogin() {
        new Login().setVisible(true);
    }

    void openWellcom() {
        new loadJDialog(this, true).setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.toystore.swing.PanelBorder();
        menu = new com.toystore.component.Menu();
        mainPanel = new javax.swing.JPanel();
        header11 = new com.toystore.component.Header1();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                    .addComponent(header11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(header11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toystore.component.Header1 header11;
    private javax.swing.JPanel mainPanel;
    private com.toystore.component.Menu menu;
    private com.toystore.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
