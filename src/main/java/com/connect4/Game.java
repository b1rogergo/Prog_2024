package com.connect4;

import java.util.Scanner;

public class Game {
    private Gameboard board;
    private final Player human;
    private final AIPlayer ai;
    private final Scanner scanner;
    private final Scoremanager scoreManager;

    public Game() {
        scanner = new Scanner(System.in);
        scoreManager = new Scoremanager();

        System.out.print("Add meg a játékos neved: ");
        String playerName = scanner.nextLine();
        this.human = new Player(playerName, 'X');
        this.ai = new AIPlayer('O');

        // Játékállapot betöltése
        this.board = GameStateManager.loadGame(6, 7);
    }



    public void start() {
        System.out.println("\nÚj játék kezdése...");
        board.printBoard();

        while (true) {
            playerMove();
            if (isGameOver()) break;
            aiMove();
            if (isGameOver()) break;
        }

        System.out.println("\nJáték véget ért.");
        scoreManager.recordWin(human.getName());

        // Játékállapot mentése
        GameStateManager.saveGame(board);
    }

    private void playerMove() {
        int column;
        do {
            System.out.print("Válassz egy oszlopot (0-6): ");
            column = scanner.nextInt();
            scanner.nextLine(); // Enter lenyelése
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
        return board.isFull();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.showMenu();
    }




    private boolean isGameOver(char playerSymbol) {
        if (board.checkWin(playerSymbol)) {
            System.out.println("\nA nyertes: " + (playerSymbol == human.getSymbol() ? human.getName() : "Gép"));
            WinnerManager.saveWinner(playerSymbol == human.getSymbol() ? human.getName() : "Gép");
            return true;
        }
        return board.isFull();
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n--- Connect 4 Menü ---");
            System.out.println("(N) Új játék");
            System.out.println("(L) Játék betöltése");
            System.out.println("(E) Eredmények");
            System.out.println("(W) Győztesek");
            System.out.println("(Q) Kilépés");
            System.out.print("Választás: ");

            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "N":
                    this.board = new Gameboard(6, 7);
                    start();
                    break;
                case "L":
                    this.board = GameStateManager.loadGame(6, 7);
                    start();
                    break;
                case "E":
                    scoreManager.displayScores();
                    break;
                case "W":
                    WinnerManager.displayWinners();
                    break;
                case "Q":
                    System.out.println("Kilépés...");
                    return;
                default:
                    System.out.println("Érvénytelen választás. Próbáld újra.");
            }
        }
    }

}
