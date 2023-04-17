/**
 * LinkedList Object.
 *
 * <P>Various attributes and methods to create and manage list
 * for the shopping list application.</P>
 *
 * @author Jose Karsikas
 * @version 1.0
 * @since 1.0
 */
public class MyLinkedList {

    /**
     * Counter of how large the list currently is.
     */
    private int counter;

    /**
     * Node representing first item in the list.
     *
     * @see MyLinkedList.Node
     */
    private Node first;

    /**
     * Gets size counter of the list.
     *
     * @return number representing list size.
     */
    private int getCounter() {
        return counter;
    }

    /**
     * Changes the list size counter.
     *
     * @param amount What to set the list size count for.
     */
    private void setCounter(int amount) {
        counter = amount;
    }

    /**
     * Gets the size of the list.
     *
     * @return size/length of this list.
     */
    public int length() {
        return getCounter();
    }

    /**
     * Adds new ShoppingItem to the list.
     *
     * Nodes are used to save data and move in the list.
     *
     * @param data item information needed to add in the list.
     * @see MyLinkedList.Node
     */
    public void add(ShoppingItem data) {

        // tmpNode is our new Object and currentNode is first one.
        Node tmpNode = new Node(data);
        Node currentNode = first;

        // Make sure first Node is not null.
        if (currentNode != null) {

            // We move in list till we hit node that contains data but has null
            // node next to it.
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }

            // Next node is null now, so we save information over it.
            currentNode.setNext(tmpNode);
        }

        // Only happens when first item is added to the list.
        if (first == null) {
            first = new Node(data);
        }

        // Increase counter because we added new data to the empty node.
        setCounter(getCounter() + 1);
    }

    /**
     * Removes item from the list.
     *
     * Skips over index Node and sets its next Node over it,
     * this way removing the item.
     *
     * @param index point in the list where we want to remove data from.
     */
    public void remove(int index) {
        if (index > 0 && index <= length()) {
            Node currentNode = first;

            if (first != null) {
                // Move in list till you hit node that has index as next of it
                for (int i = 1; i < index; i++) {
                    // Make sure we didn't go to the last Node
                    if (currentNode.getNext() != null)
                        currentNode = currentNode.getNext();
                }

                // We are at index-1 position and we now set next Node to one
                // after index, skipping the index Node and that way removing
                // it from the list.
                currentNode.setNext(currentNode.getNext().getNext());
                setCounter(getCounter() - 1);
            }
        } else if (index == 0 && first != null) {
            // If there is only 1 node left (first) list gets fully removed.
            if (first.getNext() == null) {
                first = null;
            } else {
                first = first.getNext();
            }

            setCounter(getCounter() - 1);
        }
    }

    /**
     * Removes the whole list.
     *
     * The list is removed by setting counter to 0 and pointing first
     * Node to null.
     */
    public void resetAll() {
        setCounter(0);
        first = null;
    }

    /**
     * Returns data from certain point of the list.
     *
     * @param index point in the list that we want to access.
     * @return data from wanted point of the list.
     */
    public ShoppingItem get(int index) {
        if (index >= 0 && index < length()) {
            if (first != null) {
                Node currentNode = first;
                // Move in list till you hit node that has index as next of it
                // and set that index Node to currentNode
                for (int i = 0; i < index; i++) {
                    // Make sure we didn't go to the last Node
                    if (currentNode.getNext() != null)
                        currentNode = currentNode.getNext();
                }
                // Return with the data
                return currentNode.getData();
            }
        }

        return null;
    }

    /**
     * Contains data saved into the instance called Node.
     *
     * Instance with information about ShoppingItem data and reference
     * to next Node.
     */
    private class Node {

        /**
         * The actual ShoppingItem data saved here.
         */
        ShoppingItem data;

        /**
         * Reference to next Node.
         */
        Node next;

        /**
         * Class constructor.
         *
         * Sets given data to this Node and initializes next Node by
         * setting it null.
         *
         * @param data Instance of ShoppingItem object.
         */
        public Node(ShoppingItem data) {
            next = null;
            this.data = data;
        }

        /**
         * Gets information about next Node.
         *
         * @return reference to next Node.
         */
        public Node getNext() {
            return next;
        }

        /**
         * Changes reference of next Node.
         *
         * @param next Another instance of Node.
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Returns all ShoppingItem data saved into this Node.
         *
         * @return Saved ShoppingItem data.
         */
        public ShoppingItem getData() {
            return data;
        }
    }
}
