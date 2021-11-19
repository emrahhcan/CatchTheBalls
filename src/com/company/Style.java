package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class Style {
    // Button properties and styles
    public void changeButtonColors(JButton btn) {
        // Hover affect by using mouse listener
        btn.addMouseListener(new MouseAdapter() {
            // The properties when we hover on buttons
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(Color.decode("#FFFFFF"));
                btn.setOpaque(true);
                btn.setBorderPainted(false);
                btn.setForeground(Color.decode("#082032"));
                btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Let us work with pointer as a cursor
            }
            // The properties when we don't hover on buttons
            public void mouseExited(MouseEvent e) {
                btn.setBackground(Color.decode("#082032"));
                btn.setOpaque(true);
                btn.setBorderPainted(false);
                btn.setForeground(Color.decode("#FFFFFF"));
            }
        });
        // Default Options
        btn.setBackground(Color.decode("#082032"));
        btn.setOpaque(true);            // Helps us to make it transparent
        btn.setBorderPainted(false);    // Helps us to make it transparent
        btn.setForeground(Color.decode("#FFFFFF"));
    }

    // Label properties and styles
    public void changeLabelColors(JLabel lbl) {
        lbl.setForeground(Color.decode("#FFFFFF"));
    }

    // Text Field properties and styles
    public void changeTextFieldsProperties(JTextField txtField) {
        txtField.setBackground(Color.decode("#082032"));
        txtField.setColumns(3);
    }

    // Margin for JPanel
    public void giveMargin(JPanel pnl) {
        Border border, margin;

        border = pnl.getBorder();
        margin = new EmptyBorder(0, 25, 0, 0);

        pnl.setBorder(new CompoundBorder(border, margin));
    }

}
