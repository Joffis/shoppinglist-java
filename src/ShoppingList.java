import java.io.*;

/**
 * Shopping list Object.
 *
 * <P>Various attributes and methods pick items from user input and adds
 * them to the list.</P>
 *
 * <P>Represents the shopping list and contains everything needed to create list
 * from given user input in {@link Main} class.</P>
 *
 * @author Jose Karsikas
 * @version 1.1
 * @since 1.0
 */
public class ShoppingList {

    /**
     * Linked list for ShoppingItem objects.
     */
    private MyLinkedList myList;

    /**
     * Graphical User Interface.
     *
     * @see ShoppingGUI class
     */
    private ShoppingGUI myTable;

    /**
     * File name where to save the shopping list.
     */
    public String fileName;

    /**
     * Class constructor.
     *
     * @param fileName file where items are saved.
     */
    public ShoppingList(String fileName) {
        myList = new MyLinkedList();
        myTable = new ShoppingGUI(myList, this);

        this.fileName = fileName;

        if (new File(fileName).isFile()) {
            try {
                loadList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Add loaded data to GUI
        myTable.updateTable();
    }

    /**
     * Splits given input String using another String as splitter.
     *
     * Strings are then saved to list called tokens and inside
     * foreach loop each String is parsed and Integer in front of the String
     * is saved as itemQuantity and rest of the String is saved as itemName
     * String.
     *
     * @param input    String that has to be splitted, using the splitter.
     * @param splitter String that acts as splitter, used to split the input.
     */
    public void parseString(String input, String splitter) {
        String[] tokens = input.split(splitter);

        for (String t : tokens) {
            // Remove all possible white spaces in beginning of string
            while (t.substring(0, 1).equals(" ")) {
                t = t.substring(1);
            }

            // Check where next space is
            int nextSpace = t.indexOf(" ");

            // Get the number in beginning of String = itemQuantity
            int itemQuantity = Integer.parseInt(t.substring(0, nextSpace));

            // Get the rest of string after space = itemName
            String itemName = t.substring(nextSpace + 1, t.length());

            // Send to list
            updateShoppingList(itemName, itemQuantity);
        }
    }

    /**
     * Goes through list and checks if Item with same name is found.
     *
     * Increases itemQuantity if item with same name is found.
     * If there is no item found with the same name add it as new
     * ShoppingItem to the list. Subtraction is also possible,
     * when item qty is 0 or below it gets removed.
     *
     * @param itemName     String used to check if its already in the list.
     * @param itemQuantity Integer used to increase items amount.
     */
    public void updateShoppingList(String itemName, int itemQuantity) {
        boolean alreadyInList = false;

        // Checks if item with same name is already found from the list
        for (int i = 0; i < myList.length(); i++) {
            if (myList.get(i).getItemName().equals(itemName) &&
                                                !alreadyInList) {
                myList.get(i).setItemQuantity(myList.get(i).getItemQuantity() +
                                                itemQuantity);
                alreadyInList = true;
            }

            // If some item in the list is 0 or less it gets removed.
            if (myList.get(i).getItemQuantity() <= 0) {
                myList.remove(i);
            }
        }

        // If completely new item add to list
        if (!alreadyInList) {
            ShoppingItem item = new ShoppingItem(itemName, itemQuantity);
            myList.add(item);
        }

        // Save to file
        try {
            saveList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the all items quantity and names found from the linked list.
     */
    public void printList() {
        System.out.println("Your Shopping List now:");

        for (int i = 0; i < myList.length(); i++) {
            System.out.println("  " + myList.get(i).getItemQuantity() +
                                " " + myList.get(i).getItemName());
        }

        System.out.println();

        // Update GUI
        myTable.updateTable();
    }

    /**
     * Goes through list of all items and saves them to file.
     *
     * @throws IOException if can't use file for writing.
     */
    public void saveList() throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        for (int i = 0; i < myList.length(); i++) {
            bw.write(myList.get(i).getItemQuantity()+" "+
                    myList.get(i).getItemName());
            bw.newLine();
        }

        bw.close();
    }

    /**
     * Goes through list of all items and saves them to file.
     *
     * @throws IOException if no file to load from.
     */
    public void loadList() throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String inputLine;

        while ((inputLine = br.readLine()) != null) {
            parseString(inputLine, ";"); // Send to list
        }

        br.close();
    }
}
