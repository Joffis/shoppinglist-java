/**
 * Shopping list's Product Object.
 *
 * <P>Various attributes and methods to create items for the shopping list</P>
 *
 * <P>Represents product needed to buy from the shop, product/item can
 * have attributes like name and quantity.</P>
 *
 * @author Jose Karsikas
 * @version 1.0
 * @since 1.0
 */
public class ShoppingItem {

    /**
     * Number representing how many of this item is need.
     */
    private int itemQuantity;

    /**
     * Name of the product.
     */
    private String itemName;

    /**
     * Class constructor.
     *
     * Specifies how many pieces of this item is wanted
     * and what is name of the item.
     *
     * @param iName name for the product/item.
     * @param iQty  number of how many items wanted.
     */
    public ShoppingItem(String iName, int iQty) {
        itemName = iName;
        itemQuantity = iQty;
    }

    /**
     * Gets name of this item.
     *
     * @return this item's name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Changes the name of this item.
     *
     * @param itemName This items name.
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Gets number of this items quantity.
     *
     * @return this items amount.
     */
    public int getItemQuantity() {
        return itemQuantity;
    }

    /**
     * Changes this items amount.
     *
     * @param itemQuantity This items quantity.
     */
    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
}
