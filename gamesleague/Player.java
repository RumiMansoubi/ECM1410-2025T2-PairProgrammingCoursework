package gamesleague;

public class Player{

    private String name;
    private String email;
    private String displayName;
    private String phone;

    private int id;

    public Player(int id, String name, String email, String displayName, String phone){
        this.id = id;
        this.email = email;
        this.name = name;
        this.displayName = displayName;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
