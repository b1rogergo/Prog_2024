package com.connect4;

public class MoveValidator {
    public static boolean isValidMove(Gameboard board, int column) {
        return column >= 0 && column < board.getCols() && board.getCell(0, column) == ' ';
    }
}
