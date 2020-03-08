package br.com.tundrae.graphics;

import br.com.tundrae.config.Configuration;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow extends Canvas {

    public static final int SCALE = 5;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 120;

    public static BufferedImage layer;
    private JFrame frame;

    public GameWindow(String title) {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame(title);
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    public void clearByColor() {
        Graphics g = GameWindow.layer.getGraphics();
        g.setColor(Configuration.SCREEN_CLEARING_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public Graphics getGraphics () {
        return layer.getGraphics();
    }
}
