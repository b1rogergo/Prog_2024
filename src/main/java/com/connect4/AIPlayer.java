package com.connect4;

import java.util.Random;

public class AIPlayer extends Player {
    private final Random random;

    public AIPlayer(char symbol) {
        super("Gép", symbol);
        this.random = new Random();
    }

    public int makeMove(Gameboard board) {
        int column;
        do {
            column = random.nextInt(board.getCols()); // Véletlenül választja ki az oszlopot
        } while (!board.dropDisc(column, getSymbol()));

        System.out.println("A gép " + column + ". oszlopba dobott.");
        return column;
    }
}
