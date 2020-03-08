package br.com.tundrae.entities;

import br.com.tundrae.graphics.GameWindow;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    public static boolean isRunning;

    private Thread thread;
    private GameWindow gameWindow;

    private Player player;
    private Enemy enemy;
    private Ball ball;

    private InputListener<Player> playerInputListener;

    public Game(String title) {
        thread = new Thread(this);
        gameWindow = new GameWindow(title);

        player = new Player();
        enemy = new Enemy();
        ball = new Ball(7);

        playerInputListener = new InputListener<>(player);
        gameWindow.addKeyListener(playerInputListener);
    }

    public void start() {
        if (thread != null) {
            thread.start();
            isRunning = true;
        }
    }

    public synchronized void stop() {
        try {
            thread.join();
            isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tick() {
        player.tick();
        enemy.tick();
        ball.tick();
    }

    private void render() {
        BufferStrategy bs = gameWindow.getBufferStrategy();

        if (bs == null) {
            gameWindow.createBufferStrategy(3);
            bs = gameWindow.getBufferStrategy();
        }

        Graphics g = gameWindow.getGraphics();
        gameWindow.clearByColor();

        player.render(g);
        enemy.render(g);
        ball.render(g);

        g = bs.getDrawGraphics();
        g.drawImage(GameWindow.layer, 0, 0, GameWindow.WIDTH * GameWindow.SCALE, GameWindow.HEIGHT * GameWindow.SCALE, null);

        bs.show();
    }

    @Override
    public void run() {
        gameWindow.requestFocus();

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
                System.out.println("FPS: " + frames);
                frames = 0;
                timer = System.currentTimeMillis();
            }
        }

        stop();
    }
}
