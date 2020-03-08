package br.com.tundrae.entities;

import br.com.tundrae.config.Configuration;
import br.com.tundrae.graphics.GameWindow;

import java.awt.*;

public class Player extends Entity {

    private int acceleration;

    public Player() {
        this.width = Configuration.PLAYER_WIDTH;
        this.height = Configuration.PLAYER_HEIGHT;

        this.y = GameWindow.HEIGHT - this.height;
        this.x = (float) ((GameWindow.WIDTH / 2) - (this.width / 2));

        this.acceleration = Configuration.PLAYER_ACCELERATION;
    }

    public Player(int acceleration) {
        this.acceleration = acceleration;

        this.width = Configuration.PLAYER_WIDTH;
        this.height = Configuration.PLAYER_HEIGHT;

        this.y = GameWindow.HEIGHT - this.height;
        this.x = (float) ((GameWindow.WIDTH / 2) - (this.width / 2));
    }

    public Player(int x, int y, int acceleration) {
        this.x = x;
        this.y = y;

        this.acceleration = acceleration;

        this.width = Configuration.PLAYER_WIDTH;
        this.height = Configuration.PLAYER_HEIGHT;
    }

    @Override
    public void tick() {
        if (left) {
            x -= acceleration;
        } else if (right) {
            x += acceleration;
        }

        if (x + width > GameWindow.WIDTH) {
            x = GameWindow.WIDTH - width;
        } else if (x < 0) {
            x = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Configuration.PLAYER_COLOR);
        g.fillRect((int) x, (int) y, width, height);
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
