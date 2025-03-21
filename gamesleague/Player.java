package gamesleague;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Player{

    private String name;
    private String email;
    private String displayName;
    private String phone;
    private boolean isActive; 
    private List<Integer> ownedLeagues;
    private int ownedLeagueId;
    private int totalRounds;
    private LocalDate joinDate;

    private int id;

    public Player(int id, String name, String email, String displayName, String phone){
        this.id = id;
        this.email = email;
        this.name = name;
        this.displayName = displayName;
        this.phone = phone;
        this.isActive = true;
        this.ownedLeagues = new ArrayList<>();
        this.ownedLeagueId = -1;
        this.totalRounds = 0;
        this.joinDate = LocalDate.now();
    }
    
    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void addOwnedLeague(int leagueId) {
        ownedLeagues.add(leagueId);
    }

    public List<Integer> getOwnedLeagues() {
        return ownedLeagues;
    }

    public void setOwnedLeagueId(int leagueId) {
        this.ownedLeagueId = leagueId;
    }

    public int getOwnedLeagueId() {
        return ownedLeagueId;
    }

    public int getTotalRounds() {
        return totalRounds;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }
}
