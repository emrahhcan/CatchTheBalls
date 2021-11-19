package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    // Defined random method(s)
    private Random random, randomVelocity;

    // Set Timer(s)
    private Timer timer;

    // The class which I keep styling methods
    private Style styleForPanel = new Style();

    // Methods for my ball
    private int ballX = 0, ballY = 40, ballDirY = 2;
    private int basketX = 0, basketY = 720, basketDirX = 70, basketWidth = 70; // Basket Coordinates, and properties
    private int counter = 0;

    // Initialize JFrame items
    private JLabel resultLabel, counterLabel;
    private JButton btnHowToPlay, btnStart, btnStop;

    // Validation method if user cannot catch the ball
    public boolean validation() {
        int rectWidth = 730;
        int rectHeight = 10;

        // Init. two rectangles on the left and right side of the basket.
        // If these two rectangles (empty spaces on the left and right of the basket) intersect with the ball return true
        boolean rec = new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle((basketX + 80), basketY, rectWidth, rectHeight));
        boolean rec2 = new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle((basketX - 740), basketY, rectWidth, rectHeight));

        if (rec || rec2) {
            return true;
        }

        return false;
    }

    // Constructor for Game class
    public Game() {
        setLayout(new BorderLayout());

        // Initialization and declaration of label(s)
        resultLabel = new JLabel("Result:");
        styleForPanel.changeLabelColors(resultLabel);
        counterLabel = new JLabel(String.valueOf(counter)); // Convert int counter to String, and pass it
        styleForPanel.changeLabelColors(counterLabel);

        // Initialization and declaration of button(s)
        btnHowToPlay = new JButton("How to Play?");
        styleForPanel.changeButtonColors(btnHowToPlay);
        btnHowToPlay.addActionListener(this);
        btnHowToPlay.setFocusable(false);
        btnStart = new JButton("Start/Continue");
        styleForPanel.changeButtonColors(btnStart);
        btnStart.addActionListener(this);
        btnStart.setFocusable(false);
        btnStop = new JButton("Stop");
        styleForPanel.changeButtonColors(btnStop);
        btnStop.addActionListener(this);
        btnStop.setFocusable(false);

        /* Wrapper starts */
        // Add Menu
            // JPanel starts for Menu
            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            menuPanel.setBackground(Color.decode("#082032"));
            menuPanel.add(btnHowToPlay);
            menuPanel.add(btnStart);
            menuPanel.add(btnStop);
            // JPanel ends for Menu
        add(menuPanel, BorderLayout.NORTH);

        // Add Game Area
            // JPanel starts for Info Section
            JPanel gamePanel = new JPanel();
            gamePanel.setLayout(new FlowLayout());
            gamePanel.setBackground(Color.decode("#334756"));
        add(gamePanel, BorderLayout.CENTER);

        // Add Info Section
            // JPanel starts for Info Section
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            infoPanel.setBackground(Color.decode("#082032"));
            styleForPanel.giveMargin(infoPanel);
            infoPanel.add(resultLabel);
            infoPanel.add(counterLabel);
            // JPanel ends for Info Section
        add(infoPanel, BorderLayout.SOUTH);

        // Init. Key Listener
        addKeyListener(this);
        /* Wrapper ends */

        // Timer for all listeners
        timer = new Timer(5, this);

        setSize(800, 800);
        setVisible(true);
    }

    // Paint method to paint our basket and apples
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        // Draw the ball
        g.setColor(Color.GREEN);
        g.fillOval(ballX, ballY, 20, 20);

        // Draw the basket
        g.setColor(Color.decode("#E6DDC4"));
        g.fillRect(basketX, basketY, basketWidth, 10);

        // Apply validation method if the ball intersects with the empty spaces around basket on x axis
        if (validation()) {
            // Stop timer, and open a message dialog
            timer.stop();
            String message = "You Lost\n" +
                             "\nScore: " + String.valueOf(counter) +
                             "\nWarning: The game will terminate itself";
            String dialogTitle = "Game Over";
            JOptionPane.showMessageDialog(this, message, dialogTitle, JOptionPane.WARNING_MESSAGE);

            // Close the game
            System.exit(0);
        }
    }

    // Action listener
    @Override
    public void actionPerformed(ActionEvent e) {
        ballY += ballDirY;

        if (ballY >= 715) {
            random = new Random();

            ballX = random.nextInt(770);
            ballY = 40;

            // Increment counter
            counter++;

            // Pass incremented value of counter
            counterLabel.setText(String.valueOf(counter));

            // Changing the velocity of the ball depends on the score
            if ((counter >= 5) && (counter < 10)) {
                timer.stop();

                timer = new Timer(4, this);
                timer.start();
            }
            else if ((counter >= 10) && (counter < 15)) {
                timer.stop();

                timer = new Timer(3, this);
                timer.start();
            }
            else if ((counter >= 15) && (counter < 20)) {
                timer.stop();

                timer = new Timer(2, this);
                timer.start();
            }
            else if ((counter >= 20)) {
                randomVelocity = new Random();
                timer.stop();

                // Take random velocity for ball after score hits 20
                timer = new Timer(randomVelocity.nextInt(10), this);
                timer.start();
            }
        }

        // Buttons Functionality
        if (e.getSource().equals(btnHowToPlay)) {
            String howToPlayTitle = "How to Play?";
            String howToPlay =  "You need to catch balls as much as you can.\n" +
                                "Game will terminate if the ball touch the empty spaces.\n" +
                                "Use left and right arrow keys to move the basket\n" +
                                "After 5, 10, 15, 20 balls speed will change regularly\n" +
                                "After 20 balls, ball speed will be change randomly";

            timer.stop();

            JOptionPane.showMessageDialog(this, howToPlay, howToPlayTitle, JOptionPane.PLAIN_MESSAGE);
        }
        else if (e.getSource().equals(btnStop)) {
            String stopTitle = "Paused";
            String stopMessage = "You've stopped the game\n" +
                                 "Please click OK or Press Enter\n" +
                                 "Then use arrow keys, or click the continue button";

            timer.stop();

            JOptionPane.showMessageDialog(this, stopMessage, stopTitle, JOptionPane.WARNING_MESSAGE);


        }
        else if (e.getSource().equals(btnStart)) {
            timer.start();
        }

        repaint();
    }

    // Key Listeners
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        timer.start();

        // If left arrow key pressed
        if (key == KeyEvent.VK_LEFT) {
            if (basketX <= 0) {
                basketX = 0;
            }
            else {
                basketX -= basketDirX;
            }
        }
        // If right arrow key pressed
        else if (key == KeyEvent.VK_RIGHT) {
            if (basketX >= 730) {
                basketX = 730;
            }
            else {
                basketX += basketDirX;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
