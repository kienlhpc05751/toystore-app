package com.toystore.utils;

//import Entities.EmployeeEntity;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Phan Tuấn Linh (LinhPTPC04737)
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
public class XShareHelper {

  
    /*
     * Sao chép file logo chuyên đề vào thư mục logo
     * @param file là đối tượng file ảnh
     * @return chép được hay không
     */
    public static boolean saveLogo(File file, String nameFile) {
        String path = "";
        File f = new File("src/Image");

        if (!f.exists()) {
            f.mkdirs();
        }
        try {
            path = file.getAbsolutePath();
            int indexLastExtension = path.lastIndexOf(".");
            String extensionFile = path.substring(indexLastExtension);
            copyFileToProject(new File(path), new File("src\\Image\\" + nameFile + extensionFile));
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void copyFileToProject(File file, File file_copy) throws IOException {
        Files.copy(file.toPath(), file_copy.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Căn chỉnh ảnh với label
     *
     * @param fileName tên file cần chỉnh
     * @param label label chứa ảnh
     * @return ảnh đã resize
     */
    public static ImageIcon ScaledImage(String fileName, JLabel label) {
        label.setText(null);
        Image image = readLogo(fileName).getImage();
        ImageIcon imageScaled = new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        return imageScaled;
    }

    /*
     * Đọc hình ảnh logo chuyên đề
     * @param fileName  là tên file logo
     * @return ảnh đọc được
     */
    public static ImageIcon readLogo(String fileName) {
        File path = new File("Image", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }

    public static ImageIcon scaledImage(String fileName, JLabel label) {
        ImageIcon icon = new ImageIcon("src/Image/" + fileName);
        Image image = icon.getImage();
        ImageIcon imageScaled = new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        return imageScaled;
    }

}
