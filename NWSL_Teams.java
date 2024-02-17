/**
 * Lars Kellynn
 * CEN 3024C - Software Development 1
 * February 16, 2024
 * NWSL_Teams.java
 * This class creates an object for NWSL teams with getters, and setters
 */

public class NWSL_Teams {
    private String teamName;
    private int wins;
    private int losses;
    private int goals;
    private int shotsOnGoal;

    public NWSL_Teams(String teamName) {
        this.teamName = teamName;
        this.wins = 0;
        this.losses = 0;
        this.goals = 0;
        this.shotsOnGoal = 0;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setShotsOnGoal(int shotsOnGoal) {
        this.shotsOnGoal = shotsOnGoal;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getGoals() {
        return goals;
    }

    public int getShotsOnGoal() {
        return shotsOnGoal;
    }

}

