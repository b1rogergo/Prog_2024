package com.connect4;

import java.util.Scanner;

public class Game {
    private Gameboard board;
    private final Player human;
    private final AIPlayer ai;
    private final Scanner scanner;

    public Game() {
        scanner = new Scanner(System.in);

        System.out.print("Add meg a játékos neved: ");
        String playerName = scanner.nextLine();
        this.human = new Player(playerName, 'X');
        this.ai = new AIPlayer('O');

        // Játékállapot betöltése, ha létezik
        this.board = GameStateManager.loadGame(6, 7);
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
                    ResultsManager.displayResults();
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

    public void start() {
        System.out.println("\nÚj játék kezdése...");
        board.printBoard();

        while (true) {
            if (playerMove()) break;
            if (aiMove()) break;
        }

        System.out.println("\nJáték véget ért.");
        GameStateManager.saveGame(board);
    }

    private boolean playerMove() {
        int column;

        while (true) {
            System.out.print("Válassz egy oszlopot (0-6): ");

            // Ellenőrizzük, hogy számot adott-e meg a felhasználó
            if (!scanner.hasNextInt()) {
                System.out.println("Hibás bemenet! Csak számokat adj meg.");
                scanner.next(); // Rossz bemenet eldobása
                continue;
            }

            column = scanner.nextInt();
            scanner.nextLine(); // Enter lenyelése

            // Ellenőrizzük, hogy az oszlop érvényes-e
            if (column < 0 || column >= board.getCols()) {
                System.out.println("Érvénytelen oszlop! Csak 0 és " + (board.getCols() - 1) + " közötti számot adj meg.");
                continue;
            }

            // Ellenőrizzük, hogy az oszlop nem telt-e meg
            if (board.getBoard()[0][column] != ' ') {
                System.out.println("Ez az oszlop tele van! Válassz másikat.");
                continue;
            }

            // Ha minden rendben van, kilépünk a ciklusból
            break;
        }

        human.makeMove(board, column);
        board.printBoard();

        if (board.checkWin(human.getSymbol())) {
            System.out.println("\n A nyertes: " + human.getName());
            WinnerManager.saveWinner(human.getName());
            ResultsManager.saveResult(human.getName(), true);
            return true;
        }

        return board.isFull();
    }


    private boolean aiMove() {
        ai.makeMove(board);
        board.printBoard();

        if (board.checkWin(ai.getSymbol())) {
            System.out.println("\n A gép nyert! ");
            WinnerManager.saveWinner("Gép");
            ResultsManager.saveResult(human.getName(), false);
            return true;
        }

        return board.isFull();
    }

    private boolean isValidMove(int column) {
        return column >= 0 && column < board.getCols();
    }

}
