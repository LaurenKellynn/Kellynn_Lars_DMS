import java.util.*;

/**
 * Lars Kellynn
 * CEN 3024C - Software Development 1
 * February 16, 2024
 * Managing_Teams.java
 * This class creates a database for NWSL teams, displays a teams statistics, initializes the set list of known teams,
 * updates a team's statistics, and find teams by name
 */
public class Managing_Teams {

    private List<NWSL_Teams> teamDatabase;

    public Managing_Teams() {
        teamDatabase = new ArrayList<>();
        nwslTeams();
    }

    /**
     * method: addTeam
     * parameters: String for a team's name
     * return: none
     * purpose: Initializes the team names
     */
    private void addTeam(String teamName) {
        teamDatabase.add(new NWSL_Teams(teamName));
    }

    /**
     * method: displayTeamStatistics
     * parameters: none
     * return: none
     * purpose: Prints each team's wins, losses, goals, and shots on goal
     */
    public void displayTeamStatistics() {
        System.out.println("Team Statistics:");
        for (NWSL_Teams team : teamDatabase) {
            System.out.println("Team: " + team.getTeamName());
            System.out.println("Wins: " + team.getWins());
            System.out.println("Losses: " + team.getLosses());
            System.out.println("Goals: " + team.getGoals());
            System.out.println("Shots on Goal: " + team.getShotsOnGoal());
            System.out.println();
        }
    }

    /**
     * method: findTeamByName
     * parameters: String for the team's name
     * return: The team from the database
     * purpose: Finds the team by name based on the user's input
     */
    private NWSL_Teams findTeamByName(String teamName) {
        for (NWSL_Teams team : teamDatabase) {
            if (team.getTeamName().equalsIgnoreCase(teamName)) {
                return team;
            }
        }
        return null;
    }

    /**
     * method: nwslTeams
     * parameters: none
     * return: none
     * purpose: Initializes the team names
     */
    private void nwslTeams() {
        addTeam("Angel City FC");
        addTeam("Bay FC");
        addTeam("Chicago Red Stars");
        addTeam("Houston Dash");
        addTeam("Kansas City Current");
        addTeam("NJ/NY Gotham FC");
        addTeam("North Carolina Courage");
        addTeam("Orlando Pride");
        addTeam("Portland Thorns FC");
        addTeam("Racing Louisville FC");
        addTeam("San Diego Wave FC");
        addTeam("Seattle Reign FC");
        addTeam("Utah Royals");
        addTeam("Washington Spirit");
    }

    /**
     * method: updateTeamStatistics
     * parameters: String for a team's name, integers for wins, losses, goals, and shots on goal
     * return: none
     * purpose: Sets the new team statistics based on the user's input
     */
    public void updateTeamStatistics(String teamName, int wins, int losses, int goals, int shotsOnGoal) {
        NWSL_Teams team = findTeamByName(teamName);
        if (team != null) {
            team.setWins(wins);
            team.setLosses(losses);
            team.setGoals(goals);
            team.setShotsOnGoal(shotsOnGoal);
            System.out.println("Team stats updated for " + teamName);
        } else {
            System.out.println("Team " + teamName + " not found.");
        }
    }
}
