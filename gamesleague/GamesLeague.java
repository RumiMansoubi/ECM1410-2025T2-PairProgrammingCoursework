package gamesleague;

import java.io.IOException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

/**
 * GamesLeague Class Template
 * You can use this template and add the corect method code.
 * At present it is a skeleton template with placeholder methods
 * so that it can compile and implements all methods required by GamesLeagueInterface
 *
 * @author Philip Lewis
 * @version 0.3.1
 */

public class GamesLeague implements GamesLeagueInterface {


    // Players
    private List<Player> players;

    private List<League> leagues; // Stores leagues
    private List<Score> scores;   // Stores scores


    public GamesLeague() {
        this.players = new ArrayList<>();
    }

    

    /**
     * Get the players currently created in the platform.
     *
     * @return An array of player IDs in the system or an empty array if none exists.
     */
    
    @Override
     public int[] getPlayerIds(){

        if (players.isEmpty()) {  
            return new int[0]; // placeholder so class compiles
        }

        int[] playerIds = new int[players.size()];

        for (int i = 0; i<players.size(); i++) {
            playerIds[i] = players.get(i).getId();
        }

        return playerIds;

    }
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Creates a player entry.
     *
     * @param email The email of the player (unique).
     * @param displayName The name of the player.
     * @param name The name of the player.
     * @param phone The contact phone number of the player or empty string.
     * @return The ID of the rider in the system.
     * @throws InvalidNameException If the displayName/name is null or starts/ends with whitespace,
     *                              or if the name is less than 5 char / more than 50 char.
     *                              or if displayName is less than 1 char/more than 20 char.
     * @throws InvalidEmailException If the email is null, empty, or does not contain an '@' character,
     * @throws IllegalEmailException if it duplicates an existing email of a player
     */
    @Override
     public int createPlayer(String email, String displayName, String name, String phone) 
            throws  InvalidEmailException,   
                IllegalEmailException,
                InvalidNameException {


        // Validate Name
        if (name == null || name.trim().isEmpty() || name.length() < 5 || name.length() > 50) {
            throw new InvalidNameException("Name must be between 5-50 characters & cannot start or end with spaces.");
        }

        // Validate Display Name
        if (displayName == null || displayName.trim().isEmpty() || displayName.length() < 1 || displayName.length() > 20) {
            throw new InvalidNameException("Display name must be between 1-20 characters & cannot start or end with spaces.");
        }

        // Validate Email
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new InvalidEmailException("Invalid email format.");
        }

        for (Player p : players) {
            if (p.getEmail().equalsIgnoreCase(email)) {
                throw new IllegalEmailException("Email already exists in the system.");
            }
        }

        // Generate Unique ID
        int newId = players.size() + 1;
        Player newPlayer = new Player(newId, email, displayName, name, phone);
        players.add(newPlayer);


        return newId;

    }




    /**
     * Permenantly deactivates player account.
     * <p>
     * Note to preserve the integrity of the league tables the removal process should follow:
     * i) all personal player anonymised with playerId placeholders
     * ii) all player gameplay reports are set to empty strings
     * iii) player is set to in in all league memberships
     *
     * @param playerId The ID of the player to be deactivated.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws IllegalOperationException If the player is the sole owner of a league.
     */

    @Override
    public void deactivatePlayer(int playerId) 
        throws IDInvalidException, IllegalOperationException {

            Player playerToDeactivate = null;

            // find the player id for deactivation
            for (Player p : players) {
                if (p.getId() == playerId) {
                    playerToDeactivate = p;
                    
                    break;
                }
            }
            
            // Throw IDInvalidException if ID doesnt match 
            if (playerToDeactivate == null) {
                throw new IDInvalidException("There is no player found with the given ID," + playerId);
            }

            // throw illegal operation if player is sole owner of a league
            for (League league: leagues) {
                if (league.getOwnerId() == playerId && league.getPlayerCount() == 1) {
                    throw new IllegalOperationException("Player is sole owner of a league");

                }
            }

            // (i) anonymise player details
            playerToDeactivate.setEmail("******" + playerId + "@deactivate.com");
            playerToDeactivate.setDisplayName("****");
            playerToDeactivate.setName("****");
            playerToDeactivate.setPhone("******");

            // set gameplay reports to empty strings
            for (Score score: scores) {
                if (score.getPlayer().getId() == playerId) {
                    score.setReport("");
                }
            }

            // set player to be inactive
            playerToDeactivate.setActive(false);

        return; // placeholder so class compiles
    };


    /**
     * Check if a player has been deactivated.
     * 
     * @param playerId The ID of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     * @return true if player has been deactivated or false otherwise 
     */
    @Override
    public boolean isDeactivatedPlayer(int playerId) 
        throws IDInvalidException{

            // find player by id first
            for (Player player : players) {
                if (player.getId() == playerId) {
                    return !player.isActive();
                }
            }

            throw new IDInvalidException("No player found with ID: " + playerId);

        }


    /**
     * Updates the player's display name.
     * 
     * @param playerId The ID of the player to be updated.
     * @param displayName The new display name of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     */
    public void updatePlayerDisplayName(int playerId, String displayName) 
        throws  IDInvalidException, InvalidNameException {

        if (displayName == null || displayName.trim().isEmpty() || displayName.length() < 1 || displayName.length() > 20) {
            throw new InvalidNameException("Display name must be between 1-20 characters, cannot start or end with spaces.");

        }

        for (Player player : players) {
            if (player.getId() == playerId) {
            player.setDisplayName(displayName);
            return;
        }
    }
        
        throw new IDInvalidException("No player found: " + playerId);
    };

    /**
     * Get the player id from the email.
     *
     * @param email The email of the player.
     * @throws IDInvalidException If the email does not match to any player in the system.
     * @return The ID of the player in the system or null if the player does not exist.
     */

    @Override
    public int getPlayerId(String email) throws IDInvalidException {
        if (email == null || email.trim().isEmpty()) {
            throw new IDInvalidException("email cannot be empty");
        }

        for (Player player: players) {
            if (player.getEmail().equalsIgnoreCase(email)) {
                return player.getId();
            }
        }
        
        throw new IDInvalidException("No player found: " + email);
    };


    /**
     * Get the player's display name.
     * 
     * @param playerId The ID of the player being queried.
     * @return The display name of the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * 
     */

     @Override
    public String getPlayerDisplayName(int playerId) throws IDInvalidException{

        for(Player player: players) {
            if (player.getId() == playerId) {
                return player.getDisplayName();
            }
        }

        throw new IDInvalidException("No ID found: " + playerId);
    };


    /**
     * Get the player's email.
     * 
     * @param playerId The ID of the player being queried.
     * @return The email of the player.
     * @throws IllegalEmailException If the email does not match to any player in the system.
     * 
     */
    @Override
    public String getPlayerEmail(int playerId) throws IllegalEmailException{

        for (Player player: players) {
            if (player.getId() == playerId) {
                return player.getEmail();
            }
        }
        
        throw new IllegalEmailException("No player found with ID: " + playerId);
    
    };


    /**
     * Get the in progress leagues a player is currently a member.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player is in or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    @Override
    public int[] getPlayerLeagues(int playerId) throws IDInvalidException{
        Player player = null;

        // find player
        for (Player player2: players) {
            if (player2.getId() == playerId) {
                player2 = player;
                break;
            }

        // throw error
        if(player==null) {
            throw new IDInvalidException("Player with ID does not exist:" + playerId);
        }

        // get the in progress league the player is in
        List<Integer> leagueIds = new ArrayList<>();
        
        for (League league: leagues) {
            if (league.hasPlayer(player)) {
                leagueIds.add(league.getId());
            }
        }
        return leagueIds.stream().mapToInt(Integer::intValue).toArray();


        }

        return new int[0]; // placeholder so class compiles
    };


    /** 
     * Get the leagues a player owns.
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player owns or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    @Override
    public int[] getPlayerOwnedLeagues(int playerId) throws IDInvalidException{
        
        boolean playerExists = false;

        for (Player player: players) {
            if (player.getId() == playerId) {
                playerExists = true; 
                break;
            }
        }

        if (!playerExists) {
            throw new IDInvalidException("No player found: " + playerId);
        }

        // store ID 
        List<Integer> ownedLeagueIds = new ArrayList<>();

        for (League league: leagues) {
            if (league.getOwnerId() == playerId) {
                ownedLeagueIds.add(league.getId());
            }
        }
        return ownedLeagueIds.stream().mapToInt(Integer::intValue).toArray();

    };

    /**
     * Get the user's invites
     * 
     * @param playerId The ID of the player being queried.
     * @return An array of league IDs the player has invites to or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    @Override
    public int[] getPlayerInvites(int playerId) throws IDInvalidException{

        boolean playerExists = false;

        for (Player player: players) {
            if (player.getId() == playerId) {
                playerExists = true; 
                break;
            }
        }
        if (!playerExists) {
            throw new IDInvalidException("No player found: " + playerId);
        }

        List<Integer> invitedLeagueIds = new ArrayList<>();

        for (League league: leagues) {
            if (league.getOwnerId() == playerId) {
                invitedLeagueIds.add(league.getId());
            }
        }
        return invitedLeagueIds.stream().mapToInt(Integer::intValue).toArray();
    };


    /**
     * Get the user's rounds played game stat across all leagues
     * (includes closed/removed leagues)
     * 
     * @param playerId The ID of the player being queried.
     * @return number of rounds played by the player.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    @Override
    public int getPlayerRoundsPlayed(int playerId) throws IDInvalidException{
        boolean playerExists = false;

        for (Player player: players) {
            if (player.getId() == playerId) {
                playerExists = true; 
                break;
            }
        }
        if (!playerExists) {
            throw new IDInvalidException("No player found: " + playerId);
        }
        
        int totalRounds = 0;
        for (Score score: scores) {
            if (score.getPlayer().getId() == playerId) {
                totalRounds ++;
            }
        }

        return totalRounds; // placeholder so class compiles
    };


    /**
     * Get the user's round participation percentage stat
     *  i.e. if they play all games in all their leagues every day this will be 100
     *  (includes closed/removed leagues)
     * 
     * @param playerId The ID of the player being queried.
     * @return percentage of rounds (0-100) played by the player across all leagues.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */

     @Override
    public double getPlayerRoundsPercentage(int playerId) throws IDInvalidException{

        int totalRoundsPlayed = getPlayerRoundsPlayed(playerId);
        int totalPossibleRounds = 0;

        for (League league : leagues) {
            if (league.isPlayerInLeague(playerId)) {
                totalPossibleRounds += league.getTotalRounds();
            }
        }
    
        if (totalPossibleRounds == 0) {
            return 0;
        }

        return ((double) totalRoundsPlayed/totalPossibleRounds) * 100; // percentage of rounds played by player across every league
    };

    /**
     * Get the date that the user joined the site
     * 
     * @param playerId The ID of the player being queried.
     * @return LocalDate that stores the date the player created their account.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     */
    @Override
    public LocalDate getPlayerJoinDate(int playerId) throws IDInvalidException{

        for (Player player: players) {
            if (player.getId() == playerId) {
                return player.getJoinDate();
            }
        }

        throw new IDInvalidException("No player found: " + playerId);
    };

    // Leagues

    /**
     * Get the leagues currently created in the platform.
     *
     * @return An array of leagues IDs in the system or an empty array if none exists.
     */
    public int[] getLeagueIds(){
        return leagues.stream().mapToInt(League::getId).toArray();

        //return new int[0]; // placeholder so class compiles
    };

    /**
     * Creates a league.
     * <p>
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param owner PlayerId of the league owner.
     * @param name The name of the league.
     * @param gameType The game for which the league is set up.
     * @return The ID of the league in the system.
     * @throws IDInvalidException If the ID does not match to any player in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name
     */
    @Override
    public int createLeague(int owner, String name, GameType gameType ) throws IDInvalidException, InvalidNameException, IllegalNameException{

        if (name == null || name.trim().isEmpty() || name.length() <1 || name.length() >20) {
            throw new InvalidNameException("League name must be within 1-20 characters");
        }


        for (League league :leagues) {
            if (league.getName().equalsIgnoreCase(name)) {
                throw new IllegalNameException("Name already exists");
            }

        }

        int leagueId = leagues.size() +1;
        League newLeague = new League(leagueId, name, owner, gameType);
        leagues.add(newLeague);
        
        return leagueId;
        //return 0;  // placeholder so class compiles
    };

    /**
     * Removes a league and all associated game data from the system.
     * <p>
     * The state of this WordLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param leagueId The ID of the league to be removed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public void removeLeague(int leagueId) throws IDInvalidException{
        League leagueToRemove = null;

        for (League league : leagues) {
            if (league.getId() == leagueId) {
                leagueToRemove = league;
                break;
            }
        }
    
        if (leagueToRemove == null) {
            throw new IDInvalidException("No league found: " + leagueId);
        }
    
        leagues.remove(leagueToRemove);
    };

        //return; // placeholder so class compiles
    //};

    /**
     * Get name of league
     * 
     * @param leagueId The ID of the league being queried.
     * @return The name of the league.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public String getLeagueName(int leagueId) throws IDInvalidException{

        for (League league : leagues) {
            if(league.getId() == leagueId) {
                return league.getName();
            }
        }
        
        throw new IDInvalidException("No league found: " + leagueId);

        //return ""; // placeholder so class compiles
    };

    /**
     * Update the name of a league
     * 
     * @param leagueId The ID of the league to be updated.
     * @param newName The new name of the league.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidNameException If the name is null, starts/ends with whitespace, 
     *                              is less than 1 characters or more than 20 characters.
     * @throws IllegalNameException if it duplicates an existing league name.
     * 
     */
    public void updateLeagueName(int leagueId, String newName) 
        throws IDInvalidException, 
                InvalidNameException, 
                IllegalNameException{

                    if (newName == null || newName.trim().isEmpty() || newName.length()<1 || newName.length() >20) {
                        throw new InvalidNameException("League name must be between 1-20 characters.");
                    }

                    // check if name exists
                    for (League league: leagues) {
                        if (league.getName().equalsIgnoreCase(newName)) {
                            throw new InvalidNameException("Name already exists.");
                        }
                    }

                    for (League league : leagues) {
                        if (league.getId() == leagueId) {
                            league.setName(newName);
                            return;
                        }
                    }

                    throw new IDInvalidException("No league found: " + leagueId);


        //return; // placeholder so class compiles
    };


    /**
     * Invites a potential player (may not yet be site member) to a league.
     * <p>
     * 
     * @param leagueId The ID of the league to invite the player to.
     * @param email The email of the player to be invited.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws InvalidEmailException If the email is null, empty, or does not contain an '@' character.
     */
    @Override
    public void invitePlayerToLeague(int leagueId, String email) 
        throws IDInvalidException, InvalidEmailException{

            if (email == null|| email.trim().isEmpty() || !email.contains("@")) {
                throw new InvalidNameException("Email is invalid");
            }

            for (League league : leagues) {
                if (league.getId() == leagueId) {
                    league.invitePlayer(email);
                    return; 
                }
            }

        throw new IDInvalidException("No league found with ID: " + leagueId);

        //return; // placeholder so class compiles
    };

    /**
     * Accepts an invite to a league.
     *
     * @param leagueId The ID of the league to accept the invite to.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the player email does not have an active invitation.
     */
    @Override
    public void acceptInviteToLeague(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{

            boolean playerExists = false;
            for (Player player : players) {
                if (player.getId() == playerId) {
                    playerExists = true;
                    break;
                }
            }
            if (!playerExists) {
                throw new IDInvalidException("No player found: " + playerId);
            }

            for (League league : leagues) {
                if (league.getId() == leagueId) {
                    if (!league.hasInvite(playerId)) {
                        throw new IllegalOperationException("Player does not have an active invitation to this league.");
                    }
                    league.addPlayer(playerId);
                    league.removeInvite(playerId);
                    return;
                }
                throw new IllegalOperationException("Player does not have an active invitation.");

            }

    throw new IDInvalidException("No league found: " + leagueId);

       // return; // placeholder so class compiles
    };

    /**
     * Removes invite from league
     * 
     * @param leagueId The ID of the league to remove the invite from.
     * @param email The email of the player to remove the invite from.
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalEmailException If the email does not have an active invitation.
     */
    @Override
    public void removeInviteFromLeague(int leagueId, String email) 
        throws IDInvalidException, IllegalEmailException{

            for (League league: leagues) {
                if (league.getId() == leagueId) {
                    if (!league.hasInvite(email)) {
                        throw new IllegalEmailException("No active invitation");
                    }
                    league.removeInvite(email); 
                    return;
                }

            }
        
            throw new IDInvalidException("No league found: " + leagueId);
        //return; // placeholder so class compiles
    };


    /**
     * Get league invitations for non-existing players (email addresses)
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of email addresses of players with pending invites or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public String[] getLeagueEmailInvites(int leagueId) throws IDInvalidException{

        for (League league: leagues) {
            if (league.getId() == leagueId) {
                return league.getEmailInvites();
            }
        }

        throw new IDInvalidException("No league found: " + leagueId);


        //return new String[0]; // placeholder so class compiles
    };


    /**
     * Get league invitations made to existing players (player IDs)
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs who have pending invites or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public int[] getLeaguePlayerInvites(int leagueId) throws IDInvalidException{

        for (League league: leagues) {
            if (league.getId() == leagueId ) {
                return league.getPlayerInvites();
            }
        }
        throw new IDInvalidException("No league found: " + leagueId);

        //return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the players in a league. 
     * The order of players should be the user that created the league,
     * (i.e. original owner), followed by other players in the order they accepted 
     * the league invitations.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs in the league or an empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public int[] getLeaguePlayers(int leagueId) throws IDInvalidException{
        for (League league: leagues) {
            if (league.getId() == leagueId) {
                return league.getPlayers();
            }
        }

        throw new IDInvalidException("No league found: " + leagueId);
        //return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the owners of a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @return An array of player IDs that are owners of the league or empty array if none exists.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public int[] getLeagueOwners(int leagueId) throws IDInvalidException{
        for (League league: leagues) {
            if (league.getId() == leagueId) {
                return new int[] {league.getOwnerId()};
            }
        }
        throw new IDInvalidException("No league found with ID: " + leagueId);

        //return new int[0]; // placeholder so class compiles
    };

    /**
     * Get the status of a league 
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the league as enum
     *          PENDING       not yet played
     *          IN_PROGRESS   active 
     *          CLOSED        ended  
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    @Override
    public Status getLeagueStatus(int leagueId ) throws IDInvalidException {
        for (League league : leagues) {
            if (league.getId() == leagueId) {
                return league.getStatus();
            }
        }
        throw new IDInvalidException("No league found with ID: " + leagueId);

        //return Status.PENDING; // placeholder so class compiles
    };


    /**
     * Start league
     *
     * @param leagueId The ID of the league to start.
     * @param day Should be set to the epoch day when league will be made active.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalOperationException If the league is already started.
     */
    @Override
    public void setLeagueStartDate(int leagueId, int  day) 
        throws IDInvalidException, IllegalOperationException{

            for (League league: leagues) {
                if (league.getId() == leagueId) {
                    if (league.getStatus() != Status.PENDING) {
                        throw new IllegalOperationException("League already started.");
                }

                league.setStartDate(day);
                league.setStatus(Status.IN_PROGRESS);
                return;
            }

            }
        throw new IDInvalidException("No league found: " + leagueId);

        //return; // placeholder so class compiles
    };


    /** 
     * Close league, day specified may be any date after the league start day
     * 
     * @param leagueId The ID of the league to close.
     * @param day Should be set to the epoch day when league will be closed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalOperationException If the league is already closed or invalid day.
     */
    @Override
    public void setLeagueEndDate(int leagueId, int day) throws IDInvalidException{

        for (League league: leagues) {
            if (league.getId() == leagueId) {
                throw new IllegalOperationException("League is closed");
            }

            league.setCloseDate(day);
            league.setStatus(Status.CLOSED);
            return;
        }

        throw new IDInvalidException("No league found: " + leagueId);

        //return; // placeholder so class compiles
    };


    /**
     * Get the league start date (as epoch day).
     * 
     * @param leagueId The ID of the league being queried.
     * @return The start date of the league as epoch day.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int getLeagueStartDate(int leagueId) throws IDInvalidException{

        for (League league : leagues) {
            if (league.getId() == leagueId) {
                return league.getStartDate();
            }

        }
            throw new IDInvalidException("No league found: " + leagueId);
        //return 0; // placeholder so class compiles
    };


    /**
     * Get the date a closed league closed date (as epoch day).
     * 
     * @param leagueId The ID of the league being queried.
     * @return The closure date of the league as epoch day or -1 if not closed.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public int getLeagueCloseDate(int leagueId) throws IDInvalidException{

        for (League league: leagues) {
            if (league.getId() == leagueId) {
                return league.getCloseDate();
            }
        }
        throw new IDInvalidException("No league found: " + leagueId);
        //return 0; // placeholder so class compiles
    };


    /**
     * Reset league by removing all gameplay history i.e. no scores, and gives it pending status. 
     * status of active/inactive players is unchanged.
     * 
     * @param leagueId The ID of the league to reset.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     */
    public void resetLeague(int leagueId) throws IDInvalidException{

        for (League league: leagues) {
            if (league.getId() == leagueId) {
                league.resetLeague();
                return;
            }
        }
        throw new IDInvalidException("No league found: " + leagueId);

        //return; // placeholder so class compiles
    };


    /**
     * Clone a league to make a new league. 
     * Owners of the original league are also owners of the new league.
     * Invitations are created for all players in the original league.
     * League status is set to pending.
     * 
     * @param leagueId The ID of the league to clone.
     * @param newName The name of the new league.
     * @return The ID of the new league.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalNameException If the new name already exists in the platform.
     */
    public int cloneLeague(int leagueId, String newName) throws IDInvalidException{

        for (League league : leagues) {
            if (league.getId() == leagueId) {
                for (League existingLeague : leagues) {
                    if (existingLeague.getName().equalsIgnoreCase(newName)) {
                        throw new IllegalNameException("League name already exists.");
                    }
                }
            
                int newLeagueID = league.size() + 1;
                League clonedLeague = new League(newLeagueID, newName, league.getOwnerId(), league.getGameType());
 
                for (int playerId : league.getPlayers()) {
                    clonedLeague.invitePlayer(playerId);
                }
                
                leagues.add(clonedLeague);
                return newLeagueID;
            }
        }
        throw new IDInvalidException("No league found: " + leagueId);
    }


    /**
     * Checks if player is active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    private League findLeagueById(int leagueId) throws IDInvalidException {
        for (League league : leagues) {
            if (league.getId() == leagueId) {
                return league;
            }
        }
        throw new IDInvalidException("No league found with ID: " + leagueId);
    }
    @Override
    public boolean isLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException{

            League league = findLeagueById(leagueId);
            List<Integer> playerList = new ArrayList<>();
            for (int id : league.getPlayers()) {
                playerList.add(id);
            }
        
            if (!playerList.contains(playerId)) {
                throw new IllegalArgumentException("Player is not part of this league.");
            }
            
            return league.isPlayerActive(playerId); // Check if player is active

                    //return false; // placeholder so class compiles
         };
                
            
            
            
    /** 
     * Sets a player to be registered as inactive in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    public void setLeaguePlayerInactive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException {

        return; // placeholder so class compiles
    };

    /** 
     * Sets a player to be registered as active in the league.
     * 
     * @param leagueId The ID of the league.
     * @param playerId The ID of the player.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the ID does not match to any player in the league.
     */
    public void setLeaguePlayerActive(int leagueId, int playerId) 
        throws IDInvalidException, IllegalArgumentException{

        return; // placeholder so class compiles
    };


    /** 
     * Add owner   
     * 
     * @param leagueId The ID of the league to add the owner to.
     * @param playerId The ID of the player to add as an owner.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the player is not a member of the league.
     * 
     */
    public void addOwner(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{

        return; // placeholder so class compiles
    };

    /** 
     * Remove owner
     * 
     * @param leagueId The ID of the league to remove the owner from.
     * @param playerId The ID of the player to remove as an owner.
     * 
     * @throws IDInvalidException If the ID does not match to any league or player in the system.
     * @throws IllegalOperationException If the process would leave the league without an owner.
     * 
     */
    public void removeOwner(int leagueId, int playerId) 
        throws IDInvalidException, IllegalOperationException{

        return; // placeholder so class compiles
    };


    // Results

    /**
     * Register gameplay by a player in a league. 
     * The status of the player should be set to IN_PROGRESS.
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * @param gameReport A report detailing the gameplay, may be empty if no report made,
     *                   this may be updated e.g. after other players take actions that affect result
     *
     * @throws IDInvalidException If ID do not match to any league & player in the system.
     * @throws IllegalOperationException If the day is not a valid day for the league.
     */
    public void registerGameReport(int day, int leagueId,  int playerId, String gameReport ) 
        throws IDInvalidException, IllegalOperationException{

        return; // placeholder so class compiles
    };


    /** 
     * Get the game report for a player in a league.
     * 
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the player being queried.
     * @param day The epoch day the game was played.
     * 
     * @return The game report for the player in the league on the day, or empty string if no report entry.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public String getGameReport(int day, int leagueId,  int playerId) 
        throws IDInvalidException, InvalidDateException{

        return ""; // placeholder so class compiles
    };


    /**
     * Register day game scores. Will be called when all play in a round is complete.
     * with scored ordered to match player array returned by getLeaguePlayers().
     * Once scores are registered the game status for each player should be set to CLOSED.
     * 
     * @param day The epoch day the game was played.
     * @param leagueId The ID of the league being queried.
     * @param scores The game scores with order to match the array returned by getLeaguePlayers().
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalArgumentException If the day specified has already been closed,
     *                                  or if current date is 2 days or more after the day being voided.
     */
    public void registerDayScores(int day, int leagueId, int[] scores) 
        throws IDInvalidException, IllegalArgumentException{

        return; // placeholder so class compiles
    };


    /**
     * Register a void day for a league - all points set to zero
     *
     * @param leagueId The ID of the league being queried.
     * @param playerId The ID of the league being queried.
     * @param day The epoch day being queried.
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws IllegalArgumentException If the day is not a valid day for the league,
     *                 or the current day is 2 days or more after the day being voided.
     */
    public void voidDayPoints(int day, int leagueId) 
        throws IDInvalidException, IllegalArgumentException{

        return; // placeholder so class compiles
    };  


    /**
     * Get the status of league games for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or month ended   
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public Status getDayStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return Status.PENDING; // placeholder so class compiles
    };


    /**
     * Get the scores of a round for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     *
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return An array of registered score results for each player from the day 
     *         or empty array if no gameplay from any players yet.
     *         (where gameplay is in progress the returned scores will be 0)
     *          
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public int[] getDayScores(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the league points of a league for a given day.
     * with results ordered to match player array returned by getLeaguePlayers().
     *
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return An array of the league points for each player from the day 
     *         or empty array if league points have not been finalised yet. 
     *          
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public int[] getDayPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the player rankings of a league for a given day, 
     * with results ordered to match player array returned by getLeaguePlayers().
     * 
     * @param leagueId The ID of the league being queried.
     * @param day The epoch day being queried.
     * 
     * @return an array containing the rankings of the players
     *         or empty array if rankings not yet available.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not a valid day for the league.
     */
    public int[] getDayRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the status of a league for a given week.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or week ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid week for the league.
     */
    public Status getWeekStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return Status.PENDING; // placeholder so class compiles
    };


    /**
     * Get the league points of a league for a given week,
     * with results ordered to match player array returned by getLeaguePlayers().
     *  
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return An array of the points of each players total for the week (or part week played) 
     *         or empty array if no points yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid week for the league.
     */
    public int[] getWeekPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the ranking of a league for a given week.
     * with results ordered to match player array returned by getLeaguePlayers().
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the week being queried.
     * 
     * @return An array of the rankings of each players for the week (or part week played) 
     *         or empty array if no rankings yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException     If the day is not within a valid week for the league.
     */
    public int[] getWeekRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the status of a league for a given month.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return The status of the month as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or month ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     *         InvalidDateException If the day is not within a valid month for the league.
     */
    public Status getMonthStatus(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return Status.PENDING; // placeholder so class compiles
    };


    /**
     * Get the league points of a league for a given month.
     * with results ordered to match player array returned by getLeaguePlayers().
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return An array of the points of each players total for the month (or part month played) 
     *         or empty array if no points yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid month for the league.
     */
    public int[] getMonthPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the ranking of a league for a given month.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the month being queried.
     * 
     * @return An array of the rankings of each players for the month (or part month played) 
     *         or empty array if no rankings yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException     If the day is not within a valid month for the league.
     */
    public int[] getMonthRanking(int leagueId, int day ) 
            throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the status of a league for a given year.
     * 
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return The status of the day as enum
     *          PENDING       no gameplay yet
     *          IN_PROGRESS   active and still games to play
     *          CLOSED        ended all games played or year has ended    
     *  
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid year for the league.
     */
    public Status getYearStatus(int leagueId, int day ) 
            throws IDInvalidException, InvalidDateException{

        return Status.PENDING; // placeholder so class compiles
    };


    /**
     * Get the league points of a league for a given year.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return An array of the league points of each players total for the year (or part year played) 
     *         or empty array if no points yet.
     * 
     * @throws IDInvalidException If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid month for the league.
     */
    public int[] getYearPoints(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Get the ranking of a league for a given year.
     * 
     * @param leagueId  The ID of the league being queried.
     * @param day       Epoch day that is within the year being queried.
     * 
     * @return An array of the rankings of each players for the year (or part year played) 
     *         or empty array if no rankings yet.
     * 
     * @throws IDInvalidException   If the ID does not match to any league in the system.
     * @throws InvalidDateException If the day is not within a valid year for the league.
     **/
    public int[] getYearRanking(int leagueId, int day ) 
        throws IDInvalidException, InvalidDateException{

        return new int[0]; // placeholder so class compiles
    };


    /**
     * Method empties this GamesLeaguePortal of its contents and resets all
     * internal counters.
     */
    public void eraseGamesLeagueData(){

        return; // placeholder so class compiles
    };


    /**
     * Method saves this GamesLeaguePortal contents into a serialised file,
     * with the filename given in the argument.
     * <p>
     * The state of this GamesLeaguePortal must be unchanged if any
     * exceptions are thrown.
     *
     * @param filename Location of the file to be saved.
     * @throws IOException If there is a problem experienced when trying to save the 
     *                     contents to the file.
     */
    public void saveGamesLeagueData(String filename) throws IOException{

        return; // placeholder so class compiles
    };


    /**
     * Method should load and replace this GamesLeaguePortal contents with the
     * serialised contents stored in the file given in the argument.
     *
     * @param filename Location of the file to be loaded.
     * @throws IOException If there is a problem experienced when trying
     *                     to load the store contents from the file.
     * @throws ClassNotFoundException If required class files cannot be found when loading.
     */
    public void loadGamesLeagueData(String filename) throws IOException, ClassNotFoundException{

        return; // placeholder so class compiles
    };




}