package br.com.tundrae.entities;

import br.com.tundrae.config.Configuration;
import br.com.tundrae.graphics.GameWindow;

import java.awt.*;

public class Enemy extends Entity {

    public Enemy() {
        this.width = Configuration.ENEMY_WIDTH;
        this.height = Configuration.ENEMY_HEIGHT;

        this.y = 0f;
        this.x = (float) ((GameWindow.WIDTH / 2) - (this.width / 2));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Configuration.ENEMY_COLOR);
        g.fillRect((int) x, (int) y, width, height);
    }
}
