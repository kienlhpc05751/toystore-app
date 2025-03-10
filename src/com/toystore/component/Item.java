package com.toystore.component;

//import com.Dao.KhuyenMaiDao;
//import com.model.KhuyenMai;
//import com.model.SanPham;
import com.toystore.model.store.product;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Item extends javax.swing.JPanel {

//    KhuyenMaiDao kmdao = new KhuyenMaiDao();
//    List<KhuyenMai> listKM = new ArrayList();
//    double giaKM = 0;

    public product getData() {
        return data;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        repaint();
    }

    private boolean selected;

    public Item() {
        initComponents();
        setOpaque(false);
        jPanel2.setOpaque(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private product data;
    private double GiaSP;

    public void setData(product data) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                addOverlay(jPanel2);
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                addOverlay(jPanel2);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeOverlay(jPanel2);
            }
        });
//        listKM = kmdao.selectAll();
//        this.data = data;
//        this.GiaSP = data.getGia();
        ImageIcon imgIcon = new ImageIcon(getClass().getResource("/com/toystore/image/" + data.getImage()));
//        Image img = imgIcon.getImage();
        pictureBox1.setImage(imgIcon);
        String name = data.getName();
        if(name.length()>17){
            name = name.substring(0, 18) + "...";
        }
        lbItemName.setText(name);
//        lbDescription.setText(data.getDescription());
//        lbBrand.setText(data.getBrandName());
        DecimalFormat df = new DecimalFormat("#,###0.000");
        lbPrice.setText(df.format(data.getPrice()));
            lbPrice1.setText("");
//        if (data.getKhuyenMai() != null) {
//            for (KhuyenMai km : listKM) {
//                if (data.getKhuyenMai().equals(km.getMaKM()) && km.isTrangThai() == true) {
//                    if (km.isLoaiKM() == false) {
//                        giaKM = GiaSP - km.getGiaKM();
//                    } else {
//                        giaKM =Math.round( GiaSP - (GiaSP * (km.getGiaKM()/ 100)));
//                    }
//
//                }
//            }
//            lbPrice1.setHorizontalAlignment(SwingConstants.RIGHT);
//            lbPrice.setText(df.format(giaKM));
//            lbPrice1.setText("<html><s>" + df.format(data.getGia()) + "</s></html>");
//        } 
//        else {
//            lbPrice.setText(df.format(data.getGia()));
//            lbPrice1.setText("");
//        }
    }

    private void addOverlay(JPanel panel) {
        panel.setOpaque(true);
        JLabel overlayLabel = new JLabel();
        overlayLabel.setText("+");
        overlayLabel.setForeground(Color.WHITE);
        overlayLabel.setFont(new Font("Arial", Font.PLAIN, 100));
        overlayLabel.setHorizontalAlignment(JLabel.CENTER);
        overlayLabel.setVerticalAlignment(JLabel.CENTER);
//        ImageIcon icon = new ImageIcon(getClass().getResource("/com/icon/1.png"));
//        overlayLabel.setIcon(icon);
        overlayLabel.setSize(165, 165);
        panel.add(overlayLabel);
        panel.setBackground(new Color(0, 0, 0, 50));
        panel.repaint();
    }

    private void removeOverlay(JPanel panel) {
        panel.removeAll();
        panel.setOpaque(false);
        panel.repaint();
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(242, 242, 242));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        if (selected) {
            g2.setColor(new Color(94, 156, 255));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        pictureBox1 = new com.toystore.swing.PictureBox();
        lbItemName = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbPrice1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 165, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        lbItemName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbItemName.setForeground(new java.awt.Color(76, 76, 76));
        lbItemName.setText("Item Name");

        lbPrice.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(76, 76, 76));
        lbPrice.setText("$0.00");

        lbPrice1.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        lbPrice1.setForeground(new java.awt.Color(76, 76, 76));
        lbPrice1.setText("0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPrice1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(lbItemName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPrice)
                    .addComponent(lbPrice1))
                .addContainerGap(7, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(53, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pictureBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbPrice1;
    private com.toystore.swing.PictureBox pictureBox1;
    // End of variables declaration//GEN-END:variables
}
