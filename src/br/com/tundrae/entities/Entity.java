package br.com.tundrae.entities;

import java.awt.*;

public abstract class Entity {
    protected float x, y;
    protected int width, height;
    protected boolean left, right;

    protected abstract void tick();
    protected abstract void render(Graphics g);

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}
