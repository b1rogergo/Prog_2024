package com.connect4;

import java.util.HashMap;
import java.util.Map;

public class Scoremanager {
    private final Map<String, Integer> playerScores = new HashMap<>();

    public void recordWin(String playerName) {
        playerScores.put(playerName, playerScores.getOrDefault(playerName, 0) + 1);
    }

    public void displayScores() {
        System.out.println("\nHigh Score:");
        playerScores.forEach((player, wins) -> System.out.println(player + ": " + wins + " győzelem"));
    }
}
