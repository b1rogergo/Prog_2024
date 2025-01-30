package com.connect4.game;

import com.connect4.Gameboard;

import java.util.Scanner;

public class Game {
    private final Gameboard board;
    private final Scanner scanner;
    private final char playerSymbol = 'X';
    private final char aiSymbol = 'O';

    public Game() {
        board = new Gameboard(6, 7);
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Üdvözöllek a Connect 4 játékban!");
        board.printBoard();

        while (true) {
            playerMove();
            if (isGameOver()) break;
            aiMove();
            if (isGameOver()) break;
        }
    }

    private void playerMove() {
        System.out.print("Válassz egy oszlopot (0-6): ");
        int column = scanner.nextInt();
        while (!board.dropDisc(column, playerSymbol)) {
            System.out.println("Oszlop megtelt, próbáld újra!");
            column = scanner.nextInt();
        }
        board.printBoard();
    }

    private void aiMove() {
        int column = (int) (Math.random() * 7);
        while (!board.dropDisc(column, aiSymbol)) {
            column = (int) (Math.random() * 7);
        }
        System.out.println("Gép lépett.");
        board.printBoard();
    }

    private boolean isGameOver() {
        return board.isFull(); // Ha a tábla tele van, vége a játéknak
    }

    public static void main(String[] args) {
        new Game().start();
    }
}
