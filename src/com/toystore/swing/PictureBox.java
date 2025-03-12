package com.toystore.swing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;

public class PictureBox extends JLayeredPane {

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }

    private Icon image;

    @Override
    protected void paintComponent(Graphics grphcs) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            Rectangle size = getAutoSize(image);
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(toImage(image), size.getLocation().x, size.getLocation().y, size.getSize().width, size.getSize().height, null);
        }
        super.paintComponent(grphcs);
    }

    private Rectangle getAutoSize(Icon image) {
        int w = getWidth();
        int h = getHeight();
        if (w > image.getIconWidth()) {
            w = image.getIconWidth();
        }
        if (h > image.getIconHeight()) {
            h = image.getIconHeight();
        }
        // Hệ số để phóng đại ảnh (1.2 = to hơn 20%, có thể chỉnh thành 1.5 hoặc 2.0 nếu muốn to hơn nữa)
        double scaleFactor = 1.2;

//        double xScale = (double) w / iw;
//        double yScale = (double) h / ih;
//        double scale = Math.min(xScale, yScale) * scaleFactor;  // Nhân với scaleFactor để phóng to
        int iw = image.getIconWidth();
        int ih = image.getIconHeight();
        double xScale = (double) w / iw;
        double yScale = (double) h / ih;
        double scale = Math.max(xScale, yScale);
//        double scale = Math.min(xScale, yScale) * scaleFactor;  // Nhân với scaleFactor để phóng to

        int width = (int) (scale * iw);
        int height = (int) (scale * ih);
        int x = getWidth() / 2 - (width / 2);
        int y = getHeight() / 2 - (height / 2);
        return new Rectangle(new Point(x, y), new Dimension(width, height));
    }

//    private Rectangle getAutoSize(Icon image) {
//        int w = getWidth();
//        int h = getHeight();
//        int iw = image.getIconWidth();
//        int ih = image.getIconHeight();
//
//        double xScale = (double) w / iw;
//        double yScale = (double) h / ih;
//        double scale = Math.max(xScale, yScale);  // Scale lớn nhất để phủ kín khung
//
//        int width = (int) (scale * iw);
//        int height = (int) (scale * ih);
//        int x = 0;  // Gán về 0 để ảnh full khung
//        int y = 0;
//
//        return new Rectangle(new Point(x, y), new Dimension(width, height));
//    }

    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
}
