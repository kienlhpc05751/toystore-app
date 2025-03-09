/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.utils;

import com.toystore.component.ButtonRound;
import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author Dell
 */
public class UI {

    public static void changeTransBG(Color color, JTextField... txts) {
        for (JTextField txt : txts) {
            txt.setOpaque(false);
            txt.setBackground(color);
        }
    }

    public static void accept(ButtonRound... button) {
        if (!Auth.isManager()) {
            for (ButtonRound btn : button) {
                btn.setBackground(Color.decode("#686D76"));
            }
        }
    }
}
