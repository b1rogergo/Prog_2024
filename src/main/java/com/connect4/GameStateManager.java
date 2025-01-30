package com.connect4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameStateManager {
    private static final String SAVE_FILE = "game_state.txt";
    private static char[][] board;

    /**
     * Játékállapot mentése egy szövegfájlba.
     */
    public static void saveGame(Gameboard board) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            char[][] currentBoard = board.getBoard();
            for (char[] row : currentBoard) {
                writer.write(new String(row));
                writer.newLine();
            }
            System.out.println("Játékállapot elmentve.");
        } catch (IOException e) {
            System.err.println("Hiba a játékállapot mentése közben: " + e.getMessage());
        }
    }

    /**
     * Játékállapot betöltése egy szövegfájlból.
     */
    public static Gameboard loadGame(int rows, int cols) {
        char[][] board = new char[rows][cols];
        if (!Files.exists(Paths.get(SAVE_FILE))) {
            System.out.println("Mentett játék nem található, új játék indul.");
            return new Gameboard(rows, cols);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < rows) {
                board[row] = line.toCharArray();
                row++;
            }
            System.out.println("Játékállapot betöltve.");
            return new Gameboard(board);
        } catch (IOException e) {
            System.err.println("Hiba a játékállapot betöltése közben: " + e.getMessage());
            return new Gameboard(rows, cols);
        }
    }
}
