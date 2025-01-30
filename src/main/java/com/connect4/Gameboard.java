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

    public Gameboard(char[][] existingBoard) {
        this.rows = existingBoard.length;
        this.cols = existingBoard[0].length;
        this.board = existingBoard;
    }

    private void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
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
        return false;
    }

    public boolean checkWin(char playerSymbol) {
        // Ez vízszintesen ellenőriz
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= cols - 4; col++) {
                if (board[row][col] == playerSymbol &&
                        board[row][col + 1] == playerSymbol &&
                        board[row][col + 2] == playerSymbol &&
                        board[row][col + 3] == playerSymbol) {
                    return true;
                }
            }
        }

        // Ez függőlegesen
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row <= rows - 4; row++) {
                if (board[row][col] == playerSymbol &&
                        board[row + 1][col] == playerSymbol &&
                        board[row + 2][col] == playerSymbol &&
                        board[row + 3][col] == playerSymbol) {
                    return true;
                }
            }
        }

        // Ez pedig átlósan
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 0; col <= cols - 4; col++) {
                if (board[row][col] == playerSymbol &&
                        board[row + 1][col + 1] == playerSymbol &&
                        board[row + 2][col + 2] == playerSymbol &&
                        board[row + 3][col + 3] == playerSymbol) {
                    return true;
                }
            }
        }


        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 3; col < cols; col++) {
                if (board[row][col] == playerSymbol &&
                        board[row + 1][col - 1] == playerSymbol &&
                        board[row + 2][col - 2] == playerSymbol &&
                        board[row + 3][col - 3] == playerSymbol) {
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard() {
        clearScreen();
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print("| " + cell + " ");
            }
            System.out.println("|");
        }
        System.out.println("-".repeat(cols * 4));
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public char[][] getBoard() {
        return board;
    }

    public boolean isFull() {
        for (int i = 0; i < cols; i++) {
            if (board[0][i] == ' ') {
                return false;
            }
        }
        return true;
    }

    public int getCols() {
        return cols;
    }






public int getRows() {
        return rows;
    }


    public char getCell(int i, int column) {
        return board[i][column];
    }
}
