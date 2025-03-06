package gamesleague;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

//implements GamesLeagueInterface, manages players leagues and scores
public class GamesLeague implements GamesLeagueInterface{

    public GamesLeague(List<League> leagues, List<Players> players) {
        this.leagues = leagues;
        this.players = players;
    }

    private List<League> leagues;
    private List<Players> players;

    @Override
    public int[] getPlayerIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerIds'");
    }

    @Override
    public int createPlayer(String email, String displayName, String name, String phone)
            throws InvalidEmailException, IllegalEmailException, InvalidNameException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createPlayer'");
    }

    @Override
    public void deactivatePlayer(int playerId) throws IDInvalidException, IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deactivatePlayer'");
    }

    @Override
    public boolean isDeactivatedPlayer(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isDeactivatedPlayer'");
    }

    @Override
    public void updatePlayerDisplayName(int playerId, String displayName)
            throws IDInvalidException, InvalidNameException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePlayerDisplayName'");
    }

    @Override
    public int getPlayerId(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerId'");
    }

    @Override
    public String getPlayerDisplayName(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerDisplayName'");
    }

    @Override
    public String getPlayerEmail(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerEmail'");
    }

    @Override
    public int[] getPlayerLeagues(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerLeagues'");
    }

    @Override
    public int[] getPlayerOwnedLeagues(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerOwnedLeagues'");
    }

    @Override
    public int[] getPlayerInvites(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerInvites'");
    }

    @Override
    public int getPlayerRoundsPlayed(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerRoundsPlayed'");
    }

    @Override
    public double getPlayerRoundsPercentage(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerRoundsPercentage'");
    }

    @Override
    public LocalDate getPlayerJoinDate(int playerId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPlayerJoinDate'");
    }

    @Override
    public int[] getLeagueIds() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueIds'");
    }

    @Override
    public int createLeague(int owner, String name, GameType gameType) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createLeague'");
    }

    @Override
    public void removeLeague(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLeague'");
    }

    @Override
    public String getLeagueName(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueName'");
    }

    @Override
    public void updateLeagueName(int leagueId, String newName)
            throws IDInvalidException, InvalidNameException, IllegalNameException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateLeagueName'");
    }

    @Override
    public void invitePlayerToLeague(int leagueId, String email) throws IDInvalidException, InvalidEmailException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'invitePlayerToLeague'");
    }

    @Override
    public void acceptInviteToLeague(int leagueId, int playerId) throws IDInvalidException, IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptInviteToLeague'");
    }

    @Override
    public void removeInviteFromLeague(int leagueId, String email) throws IDInvalidException, IllegalEmailException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeInviteFromLeague'");
    }

    @Override
    public String[] getLeagueEmailInvites(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueEmailInvites'");
    }

    @Override
    public int[] getLeaguePlayerInvites(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeaguePlayerInvites'");
    }

    @Override
    public int[] getLeaguePlayers(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeaguePlayers'");
    }

    @Override
    public int[] getLeagueOwners(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueOwners'");
    }

    @Override
    public Status getLeagueStatus(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueStatus'");
    }

    @Override
    public void setLeagueStartDate(int leagueId, int day) throws IDInvalidException, IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLeagueStartDate'");
    }

    @Override
    public void setLeagueEndDate(int leagueId, int day) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLeagueEndDate'");
    }

    @Override
    public int getLeagueStartDate(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueStartDate'");
    }

    @Override
    public int getLeagueCloseDate(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLeagueCloseDate'");
    }

    @Override
    public void resetLeague(int leagueId) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'resetLeague'");
    }

    @Override
    public int cloneLeague(int leagueId, String newName) throws IDInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'cloneLeague'");
    }

    @Override
    public boolean isLeaguePlayerActive(int leagueId, int playerId)
            throws IDInvalidException, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isLeaguePlayerActive'");
    }

    @Override
    public void setLeaguePlayerInactive(int leagueId, int playerId)
            throws IDInvalidException, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLeaguePlayerInactive'");
    }

    @Override
    public void setLeaguePlayerActive(int leagueId, int playerId) throws IDInvalidException, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLeaguePlayerActive'");
    }

    @Override
    public void addOwner(int leagueId, int playerId) throws IDInvalidException, IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOwner'");
    }

    @Override
    public void removeOwner(int leagueId, int playerId) throws IDInvalidException, IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOwner'");
    }

    @Override
    public void registerGameReport(int day, int leagueId, int playerId, String gameReport)
            throws IDInvalidException, IllegalOperationException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerGameReport'");
    }

    @Override
    public String getGameReport(int day, int leagueId, int playerId) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getGameReport'");
    }

    @Override
    public void registerDayScores(int day, int leagueId, int[] scores)
            throws IDInvalidException, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registerDayScores'");
    }

    @Override
    public void voidDayPoints(int day, int leagueId) throws IDInvalidException, IllegalArgumentException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'voidDayPoints'");
    }

    @Override
    public Status getDayStatus(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDayStatus'");
    }

    @Override
    public int[] getDayScores(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDayScores'");
    }

    @Override
    public int[] getDayPoints(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDayPoints'");
    }

    @Override
    public int[] getDayRanking(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDayRanking'");
    }

    @Override
    public Status getWeekStatus(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeekStatus'");
    }

    @Override
    public int[] getWeekPoints(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeekPoints'");
    }

    @Override
    public int[] getWeekRanking(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getWeekRanking'");
    }

    @Override
    public Status getMonthStatus(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMonthStatus'");
    }

    @Override
    public int[] getMonthPoints(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMonthPoints'");
    }

    @Override
    public int[] getMonthRanking(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMonthRanking'");
    }

    @Override
    public Status getYearStatus(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getYearStatus'");
    }

    @Override
    public int[] getYearPoints(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getYearPoints'");
    }

    @Override
    public int[] getYearRanking(int leagueId, int day) throws IDInvalidException, InvalidDateException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getYearRanking'");
    }

    @Override
    public void eraseGamesLeagueData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eraseGamesLeagueData'");
    }

    @Override
    public void saveGamesLeagueData(String filename) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGamesLeagueData'");
    }

    @Override
    public void loadGamesLeagueData(String filename) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadGamesLeagueData'");
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
    
}
