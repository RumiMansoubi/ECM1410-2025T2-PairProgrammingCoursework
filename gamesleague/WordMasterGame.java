package gamesleague;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WordMasterGame {
    private List<Player> players;

    public WordMasterGame() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void playRound() {
        for (Player player : players) {
            int score = player.play();
            player.addScore(score);
        }
        awardLeaguePoints();
    }

    private void awardLeaguePoints() {
        Collections.sort(players, Comparator.comparingInt(Player::getScore));
        for (Player player : players) {
            player.addLeaguePoints(player.getScore());
        }
    }

    public void displayScores() {
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }
}
