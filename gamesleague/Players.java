package gamesleague;

public class Player implements Comparable<Player> {
    private String name;
    private int score;
    private int leaguePoints;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.leaguePoints = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public void addLeaguePoints(int points) {
        this.leaguePoints += points;
    }

    @Override
    public int compareTo(Player other) {
        return Integer.compare(this.score, other.score);
    }
}