import java.util.Scanner;

/**
 * Shopping list Application.
 *
 * <P>Various attributes and objects needed to run the application.</P>
 *
 * @author Jose Karsikas
 * @version 1.1
 * @since 1.0
 */
public class Main {

    /**
     * Runs the application.
     *
     * Contains while loop that collects user input using scanner.
     * Application is terminated when user types in the 'exit' command.
     *
     * @param args list of command line arguments
     */
    public static void main(String[] args) {
        boolean programStatus = true;

        Scanner scanner = new Scanner(System.in);
        ShoppingList shoppingList = new ShoppingList("shoppinglist.txt");

        System.out.println("SHOPPING LIST");
        System.out.println("Tampere University of Applied Sciences");

        while (programStatus) {
            System.out.println("Give shopping list " +
                                "(example: 1 milk;2 tomato;3 carrot;)");
            String userInput = scanner.nextLine();

            if (!userInput.equals("exit")) {
                try {
                    shoppingList.parseString(userInput, ";");
                } catch (StringIndexOutOfBoundsException|
                        NumberFormatException ex) {
                    System.out.println("Your input is invalid," +
                            " please try again!");
                }

                shoppingList.printList();
            } else {
                programStatus = false;
                System.exit(0); //This is so you can exit from CLI
            }
        }
    }
}
