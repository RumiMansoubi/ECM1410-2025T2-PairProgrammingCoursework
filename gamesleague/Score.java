package gamesleague;

public class Score {
    private String playerName;
    private int score;
    private String report;
    private Player player;

    public Score(Player player, String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
