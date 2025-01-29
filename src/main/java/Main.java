package com.connect4;

import java.util.Scanner;

public class Game {
    private final Gameboard board;
    private final Player human;
    private final AIPlayer ai;
    private final Scanner scanner;

    public Game() {
        board = new Gameboard(6, 7); // 6 sor, 7 oszlop (standard méret)
        scanner = new Scanner(System.in);

        System.out.print("Add meg a játékos neved: ");
        String playerName = scanner.nextLine();
        this.human = new Player(playerName, 'X');
        this.ai = new AIPlayer('O');
    }

    public void start() {
        System.out.println("\nÜdvözöllek a Connect 4 játékban, " + human.getName() + "!");
        board.printBoard();

        while (true) {
            playerMove();
            if (isGameOver()) break;
            aiMove();
            if (isGameOver()) break;
        }
    }

    private void playerMove() {
        int column;
        do {
            System.out.print("Válassz egy oszlopot (0-6): ");
            column = scanner.nextInt();
        } while (!isValidMove(column));

        human.makeMove(board, column);
        board.printBoard();
    }

    private void aiMove() {
        ai.makeMove(board);
        board.printBoard();
    }

    private boolean isValidMove(int column) {
        return column >= 0 && column < board.getCols();
    }

    private boolean isGameOver() {
        return board.isFull(); // Ide még győzelmi ellenőrzést is be lehet tenni
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
