package br.com.tundrae.entities;

import br.com.tundrae.config.Configuration;
import br.com.tundrae.graphics.GameWindow;

import java.awt.*;

public class Ball {
    private float x, y;
    private int radius;

    public Ball(int radius) {
        this.radius = radius;
        this.x = (float) ((GameWindow.WIDTH / 2) - radius);
        this.y = (float) ((GameWindow.HEIGHT / 2) - radius);
    }

    public void tick() {

    }

    public void render(Graphics g) {
        g.setColor(Configuration.BALL_COLOR);
        g.fillOval((int) x, (int) y, radius, radius);
    }
}
