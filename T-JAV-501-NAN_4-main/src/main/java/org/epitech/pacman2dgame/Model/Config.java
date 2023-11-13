package org.epitech.pacman2dgame.Model;

public class Config {
    private String username = "Player 1";

    private int lastScore = 0;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setLastScore(int lastScore) {
        this.lastScore = lastScore;
    }

    public int getLastScore() {
        return lastScore;
    }
}
