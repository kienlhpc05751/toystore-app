/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.toystore.testDao;

import com.toystore.dao.UserDAO;
import com.toystore.model.User;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class Toystore {

    /**
     * @param args the command line arguments
     *
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserDAO userDao;
        try {
            userDao = new UserDAO();
            List<User> list = userDao.getAll();
            for (User user : list) {
                System.out.println("name: " + user.getUsername() + "  " + user.getEmail() + "" + user.getRole().getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Toystore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Toystore.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
