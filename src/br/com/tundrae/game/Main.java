package br.com.tundrae.game;

import br.com.tundrae.entities.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("Pong");
        game.start();
    }
}
