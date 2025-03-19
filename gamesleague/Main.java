package gamesleague;

public class Main {
    public static void main(String[] args) {}
        GamesLeague league = new GamesLeague();

        try {
            int id_1 = league.createPlayer("ashley@gmail.com", "Ashley", "Pheng", "1214");
        
            int id_2 = league.createPlayer("rumi@gmail.com", "Rumi", "rumi", "2005");
        
        
        System.out.println("Player IDs: " + id1 + ", " + id2);
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    }

