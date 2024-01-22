import java.io.*;
        import java.nio.charset.StandardCharsets;
        import java.nio.file.FileSystems;
        import java.nio.file.Files;
        import java.nio.file.Path;
        import java.nio.file.StandardOpenOption;
        import java.util.*;

/**
 * Lars Kellynn
 * CEN 3024C - Software Development 1
 * January 17, 2024
 * LMS_App.java
 * This class displays a menu to the user, reads in the library collection's .txt file,
 * contains methods to add and remove books from user's input to the .txt file,
 * and prints the current .txt file
 */

public class LMS {

    /**
     * method: Main
     * parameters: none
     * return: none
     * purpose: calls the displayMenu method in a while loop,
     * gets the users input for the menu and triggers that method chosen with
     * conditional statements
     */
    public static void main(String[] args) throws IOException {

        int option = 0;

        while (option != 4) {
            displayMenu();
            Scanner userInput = new Scanner(System.in);
            option = userInput.nextInt();

            //Path to choose the file from the local user
            Path libraryCollection = Path.of("/Users/lauren/Desktop/School/SoftwareDev1/librarycollection.txt");
            String listCollection = Files.readString(libraryCollection);


            //if statements to trigger the method the user chooses
            if (option == 1) {
                addNewBooks();
            }
            else if (option == 2) {
                removeBooks();
            }
            else if (option == 3) {
                System.out.println(listCollection);
                System.out.println();
            }
            else {
                if (option != 4) {
                    System.out.println("Please make a valid selection: Options 1, 2, 3, or 4.");
                }
            }
        }

    }

    /**
     * method: addNewBooks
     * parameters: none
     * return: none
     * purpose: Prompts the user for the new book's ID, Title,
     * and Author and adds the string to the .txt file
     */
    public static void addNewBooks() throws IOException {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Please list the book's ID number: ");
        String bookID = userInput.nextLine();

        System.out.println("Please list the title of the book: ");
        String title = userInput.nextLine();

        System.out.println("Please list the Author's Name: ");
        String author = userInput.nextLine();

        Path libraryCollection = Path.of("/Users/lauren/Desktop/School/SoftwareDev1/librarycollection.txt");

        //String joiner to add the string together separated by a comma
        StringJoiner sj = new StringJoiner(",");
        sj.add(bookID);
        sj.add(title);
        sj.add(author);

        //String joiner to string and line separator to make the new string be on it's own line
        String bookEntry = System.lineSeparator() + sj.toString();
        Files.writeString(libraryCollection, bookEntry, StandardOpenOption.APPEND);

    }

    /**
     * method: displayMenu
     * parameters: none
     * return: none
     * purpose: Displays the welcome screen and menu to the user
     */
    public static void displayMenu() {
        System.out.println("Lars's Library Management System");
        System.out.println();
        System.out.println("Welcome!");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println();
        System.out.println("1. Add a new book to the collection.");
        System.out.println("2. Remove a book from the collection.");
        System.out.println("3. List all books currently in our collection.");
        System.out.println("4. Quit");
        System.out.println();
        System.out.println("Please choose option 1, 2, 3, or 4: ");
    }

    /**
     * method: removeBooks
     * parameters: none
     * return: none
     * purpose: reads in the .txt file to a string[], prompts the user for the book's ID,
     * amends the string[], adds the new string to the .txt file
     */
    public static void removeBooks() throws IOException {


        Path libraryCollection = Path.of("/Users/lauren/Desktop/School/SoftwareDev1/librarycollection.txt");
        String listCollection = Files.readString(libraryCollection);
        String[] bookCollection = listCollection.split(System.getProperty("line.separator"));

        Scanner userInput = new Scanner(System.in);

        System.out.println();
        System.out.println("Please type the book's ID number you'd like to have removed: ");
        String bookID = userInput.next();

        for (int i = 0; i < bookCollection.length; i++) {
            if (bookCollection[i].startsWith(bookID)) {
                bookCollection[i] = "";
            }
        }

        StringBuilder newCollection = new StringBuilder("");
        for (String s : bookCollection) {
            if (!s.equals("")) {
                newCollection.append(s).append(System.getProperty("line.separator"));
            }
        }

        String finalString = newCollection.toString();

        Files.writeString(libraryCollection, finalString);
    }}