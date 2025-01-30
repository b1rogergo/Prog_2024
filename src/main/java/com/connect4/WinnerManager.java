package com.connect4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WinnerManager {
    private static final String WINNERS_FILE = "winners.txt";

    public static void saveWinner(String playerName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(WINNERS_FILE, true))) {
            writer.write(playerName);
            writer.newLine();
            System.out.println("Győztes mentve: " + playerName);
        } catch (IOException e) {
            System.err.println("Hiba a győztes mentése közben: " + e.getMessage());
        }
    }

    public static List<String> loadWinners() {
        List<String> winners = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(WINNERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                winners.add(line);
            }
        } catch (IOException e) {
            System.err.println("Hiba a győztesek betöltése közben: " + e.getMessage());
        }
        return winners;
    }

    public static void displayWinners() {
        List<String> winners = loadWinners();
        System.out.println("\n--- Korábbi Győztesek ---");
        if (winners.isEmpty()) {
            System.out.println("Még nincs rögzített győzelem.");
        } else {
            winners.forEach(System.out::println);
        }
    }
}
