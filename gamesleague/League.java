package gamesleague;

import java.util.ArrayList;
import java.util.List;

public class League {
    private String name;
    private List<Team> teams;
    private List<Integer> players;
    private List<Integer> invitedPlayers;
    private List<String> invitedEmails;
    private int id;
    private int ownerId;
    private int totalRounds;
    private int startDate;
    private int closeDate;
    private Status status;



    public League(int id, String name, int ownerId, String gameType) {
        this.name = name;
        this.teams = new ArrayList<>();
        this.id = id;
        this.ownerId = ownerId;
        this.invitedPlayers = new ArrayList<>();
        this.totalRounds = 0;
        this.invitedEmails = new ArrayList<>();
        this.status = Status.PENDING;
        this.startDate = -1;
        this.closeDate = -1;

        players.add(ownerId);
    }

    public String getName() {
        return name;
    }

    public String[] getEmailInvites() {
        return invitedEmails.toArray(new String[0]);
    }
    

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    

    public int getOwnerId() {
        return ownerId;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public int[] getPlayers() {
        return players.stream().mapToInt(Integer::intValue).toArray();
    }

 
    
    public int getId() {
        return id;
    }

    public int[] getPlayerInvites() {
        return invitedPlayers.stream().mapToInt(Integer::intValue).toArray();
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public boolean hasInvite(int playerId) {
        return invitedPlayers.contains(playerId);
    }

    public boolean hasInvite(String email) {
        return invitedEmails.contains(email);
    }
    

    public boolean isPlayerInLeague(int playerId) {
        return players.contains(playerId);
    }

    public void addPlayer(int playerId) {
        if (!players.contains(playerId)) {
            players.add(playerId);
        }
    }
    

    public void invitePlayer(int playerId) {
        if (!invitedPlayers.contains(playerId)) {
            invitedPlayers.add(playerId);
        }
    }
    

    public void invitePlayer(String email) {
        if (!invitedEmails.contains(email)) {
            invitedEmails.add(email);
        }
    }

    public void removeInvite(int playerId) {
        invitedPlayers.remove(Integer.valueOf(playerId));
    }

    public void removeInvite(String email) {
        invitedEmails.remove(email);
    }
    
    public void setStartDate(int day) {
        this.startDate = day;
        this.status = Status.IN_PROGRESS;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setCloseDate(int day) {
        this.closeDate = day;
        this.status = Status.CLOSED;
    }

    public int getCloseDate() {
        return closeDate;
    }

    public void resetLeague() {
        this.status = Status.PENDING;
    }
    

    public int getTotalRounds() {
        return totalRounds;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) {
        this.status = newStatus;
    }

    public void displayTeams() {
        for (Team team : teams) {
            System.out.println(team.getName());
        }
    }
}
