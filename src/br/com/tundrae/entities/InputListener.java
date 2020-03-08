package br.com.tundrae.entities;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener<T extends Entity> implements KeyListener {

    private T target;

    public InputListener(T target) {
        this.target = target;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            target.setRight(true);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            target.setLeft(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            target.setRight(false);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            target.setLeft(false);
        }
    }
}
