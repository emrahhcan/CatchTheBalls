package com.company;

import javax.swing.*;

public class Test extends JFrame {
    // JFrame constructor
    public Test(String title) {
        super(title);
    }

    public static void main(String[] args) {
        // Initialize JFrame
        Test gamePnl = new Test("CMPE 261 HW");

        // Initialize JFrame properties
        gamePnl.setResizable(false);
        gamePnl.setFocusable(false);
        gamePnl.setSize(800, 800);
        gamePnl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize JPanel
        Game myGame = new Game();

        // Initialize JFrame properties
        myGame.requestFocus();
        myGame.setFocusable(true);
        myGame.setFocusTraversalKeysEnabled(false);

        // Add JPanel which is located in Game class
        gamePnl.add(myGame);

        // Keep the JPanel visible to be able to open the window
        gamePnl.setVisible(true);

    }
}
