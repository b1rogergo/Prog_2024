package com.connect4;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ResultsManager {
    private static final String RESULTS_FILE = "results.txt";

    public static void saveResult(String playerName, boolean isWinner) {
        Map<String, Integer> results = loadResults();

        if (isWinner) {
            results.put(playerName, results.getOrDefault(playerName, 0) + 1);
        } else {
            results.put(playerName, results.getOrDefault(playerName, 0));
        }

        writeResults(results);
    }

    public static Map<String, Integer> loadResults() {
        Map<String, Integer> results = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RESULTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    results.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Hiba az eredmények betöltésekor: " + e.getMessage());
        }
        return results;
    }

    private static void writeResults(Map<String, Integer> results) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RESULTS_FILE))) {
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Hiba az eredmények mentésekor: " + e.getMessage());
        }
    }

    public static void displayResults() {
        Map<String, Integer> results = loadResults();
        System.out.println("\n--- Eredmények ---");
        if (results.isEmpty()) {
            System.out.println("Még nincs rögzített eredmény.");
        } else {
            for (Map.Entry<String, Integer> entry : results.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue() + " győzelem");
            }
        }
    }
}
