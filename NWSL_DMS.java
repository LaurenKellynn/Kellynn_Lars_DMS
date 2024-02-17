import java.io.*;
import java.lang.System;
import java.util.*;

/**
 * Lars Kellynn
 * CEN 3024C - Software Development 1
 * February 15, 2024
 * NWSL_DMS.java
 * This class creates a player database, takes a .txt file from the user to update player database,
 * walks the user through a menu of options, prints the player database, removes players from the database,
 * updates a player's injury status, and updates a team's statistics based on user input
 */

public class NWSL_DMS {

    private Scanner scanner;
    private List<String[]> database;
    private NWSL_DMS playerDatabase;
    private Managing_Teams managingTeams;

    public NWSL_DMS() {
        database = new ArrayList<>();
        scanner = new Scanner(System.in);
        playerDatabase = this;
        managingTeams = new Managing_Teams();
    }

    /**
     * method: addPlayer
     * parameters: String for the player's name and a string for if the player is out on injury
     * return: none
     * purpose: Adds players to the database
     */
    public void addPlayer(String name, String injuryStatus) {
        database.add(new String[]{name, injuryStatus});
    }

    /**
     * method: displayMenu
     * parameters: none
     * return: none
     * purpose: Walks the user through a menu of questions to update the players database or update team statistics
     */
    public void displayMenu() {

        int removeOption = 0;
        System.out.println("Would you like to remove a player from the database?");
        System.out.println();
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("Please select option 1 or 2: ");

        Scanner userInput = new Scanner(System.in);
        removeOption = userInput.nextInt();

        if (removeOption == 1) {
            System.out.print("Enter the name of the player you want to remove: ");
            String playerToRemove = scanner.nextLine();
            playerDatabase.removePlayer(playerToRemove);
        } else {
            int injuryOption = 0;
            System.out.print("Would you like to change the injury status of a player?");
            System.out.println();
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Please select option 1 or 2: ");

            Scanner newUserInput = new Scanner(System.in);
            injuryOption = newUserInput.nextInt();

            if (injuryOption == 1) {
                System.out.print("Enter the name of the player whose injury status you want to update: ");
                String playerName = scanner.nextLine();
                System.out.print("Enter the new injury status (Y/N): ");
                String injuryStatus = scanner.nextLine();

                playerDatabase.updateInjuryStatus(playerName, injuryStatus);
                playerDatabase.printPlayerDatabase();
            } else {
                int teamOption = 0;
                System.out.print("Would you like to update a team's statistics?");
                System.out.println();
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.println("Please select option 1 or 2: ");

                Scanner nextUserInput = new Scanner(System.in);
                teamOption = nextUserInput.nextInt();

                if (teamOption == 1) {
                    managingTeams.displayTeamStatistics();
                    updateTeamStatistics();
                    managingTeams.displayTeamStatistics();
                } else {
                    scanner.close();
                }
            }
        }
    }

    /**
     * method: main
     * parameters: none
     * return: none
     * purpose: Asks the user for a file name, opens the file,
     * reads the information, and adds it to the database, then moves the user to the displayMenu method
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NWSL_DMS playerDatabase = new NWSL_DMS();

        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();

        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String injuryStatus = parts[1].trim();
                    playerDatabase.addPlayer(name, injuryStatus);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
            return;
        }
        playerDatabase.printPlayerDatabase();
        playerDatabase.displayMenu();
        scanner.close();
    }

    /**
     * method: printPlayerDatabase
     * parameters: none
     * return: none
     * purpose: Prints the current contents of the Player database
     */
    public void printPlayerDatabase() {
        System.out.println("Printing NWSL Player List:");
        System.out.println("Player Name \t Out on Injury? Y/N");
        for (String[] player : database) {
            System.out.println(player[0] + "\t" + player[1]);
        }
        System.out.println();
    }

    /**
     * method: removePlayer
     * parameters: String for the player's name to remove from the database
     * return: none
     * purpose: Removes the player name entered by the user from the database
     */
    public void removePlayer(String name) {
        Iterator<String[]> iterator = database.iterator();
        while (iterator.hasNext()) {
            String[] player = iterator.next();
            if (player[0].equalsIgnoreCase(name)) {
                iterator.remove();
                System.out.println("Player '" + name + "' has been removed from the system.");
                return;
            }
        }
        System.out.println("Player '" + name + "' was not found in the system.");
    }

    /**
     * method: updateInjuryStatus
     * parameters: String for the player's name and a string for if the player is out on injury
     * return: none
     * purpose: Updates the injury status of a player based on the user's input
     */
    public void updateInjuryStatus(String playerName, String newInjuryStatus) {
        boolean found = false;
        for (String[] player : database) {
            if (player[0].equalsIgnoreCase(playerName)) {
                player[1] = newInjuryStatus;
                found = true;
                System.out.println("Injury status for player '" + playerName + "' has been updated to '" + newInjuryStatus + "'.");
                break;
            }
        }
        if (!found) {
            System.out.println("Player '" + playerName + "' not found in the database.");
        }
    }

    /**
     * method: updateTeamStatistics
     * parameters: none
     * return: none
     * purpose: Updates a team's number of wins, losses, goals, shots on goal based on user input
     */
    private void updateTeamStatistics() {
        System.out.print("Enter the name of the team: ");
        String teamName = scanner.nextLine();

        System.out.print("Enter the number of wins: ");
        int wins = scanner.nextInt();

        System.out.print("Enter the number of losses: ");
        int losses = scanner.nextInt();

        System.out.print("Enter the number of goals: ");
        int goals = scanner.nextInt();

        System.out.print("Enter the number of shots on goal attempted: ");
        int shotsOnGoal = scanner.nextInt();

        managingTeams.updateTeamStatistics(teamName, wins, losses, goals, shotsOnGoal);
    }
}
