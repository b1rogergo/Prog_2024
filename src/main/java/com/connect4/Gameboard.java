package com.connect4;

public class Gameboard {
    private final char[][] board;
    private final int rows;
    private final int cols;

    public Gameboard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' '; // Üres mezők
            }
        }
    }

    public boolean dropDisc(int column, char playerSymbol) {
        for (int i = rows - 1; i >= 0; i--) {
            if (board[i][column] == ' ') {
                board[i][column] = playerSymbol;
                return true;
            }
        }
        return false; // Oszlop megtelt
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(cols * 4));
    }

    public boolean isFull() {
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public char[][] getBoard() {
        return board;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char getCell(int i, int column) {
        return board[i][column];
    }
}
