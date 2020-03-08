package br.com.tundrae.graphics;

import br.com.tundrae.config.Configuration;
import br.com.tundrae.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class GameWindow extends Canvas implements Runnable, KeyListener {

    public static final int SCALE = 3;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 120;

    private boolean isRunning;

    private BufferedImage layer;
    private JFrame frame;
    private Thread thread;

    private Player player;

    public GameWindow(String title) {
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        addKeyListener(this);

        frame = new JFrame(title);
        frame.add(this);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        player = new Player();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        player.tick();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();

        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = layer.getGraphics();
        g.setColor(Configuration.SCREEN_CLEARING_COLOR);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        player.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);

        bs.show();
    }

    @Override
    public void run() {
        /* Essential to make the game playable right when it is started */
        requestFocus();

        long lastTime = System.nanoTime();

        final double amountOfTicks = 60.0;
        final double ns = 1000000000 / amountOfTicks;

        int frames = 0;
        double delta = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
//                System.out.println("FPS: " + frames);
                frames = 0;
                timer = System.currentTimeMillis();
            }
        }

        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(true);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(false);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(false);
        }
    }
}
