package gamesleague;

// In the DiceRoll game the player with the lowest game score wins (i.e. ranking is an order of score low to high)
// The player(s) in the group with the lowest game score are awarded 3 league points, and the player(s) in the group
// with the next lowest score are awarded 1 league point

public class DiceRollGame {
    private List<Player> players;
    private Random random;

    public DiceRollGame(List<Player> players) {
        this.players = players;
        this.random = new Random();
    }

    public void playGame() {
        for (Player player : players) {
            int score = rollDice();
            player.setScore(score);
        }
        determineWinner();
    }

    private int rollDice() {
        return random.nextInt(6) + 1; // Rolls the Dice 
    }

    private void determineWinner() {
        Collections.sort(players);
        int lowestScore = players.get(0).getScore();
        int secondLowestScore = players.get(1).getScore();

        for (Player player : players) {
            if (player.getScore() == lowestScore) {
                player.addLeaguePoints(3);
            } else if (player.getScore() == secondLowestScore) {
                player.addLeaguePoints(1);
            }
        }
    }
}