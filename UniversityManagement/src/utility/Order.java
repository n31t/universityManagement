package utility;

import java.io.Serializable;

import database.Database;
import enums.Status;

/**
 * Represents an order or request in a university management system.
 * This class encapsulates details of an order, including its unique identifier, text, and current status.
 */
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private int orderId;
	private String orderText;
	private Status orderStatus;
	
	// Initializer block for orderId
	{
		orderId = Database.nextId();
	}
	/**
     * Default constructor for creating an Order instance without predefined properties.
     */
    public Order() {
    }

    /**
     * Constructs an Order with a specified order text.
     *
     * @param orderText The text of the order.
     */
    public Order(String orderText) {
        this.orderText = orderText;
    }

    /**
     * Retrieves the order ID.
     *
     * @return The order ID.
     */
    public int getOrderId() {
        return this.orderId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderId The order ID to be set.
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Retrieves the text of the order.
     *
     * @return The order text.
     */
    public String getOrderText() {
        return this.orderText;
    }

    /**
     * Sets the text of the order.
     *
     * @param orderText The order text to be set.
     */
    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    /**
     * Retrieves the current status of the order.
     *
     * @return The status of the order.
     */
    public Status getStatus() {
        return this.orderStatus;
    }

    /**
     * Sets the status of the order.
     *
     * @param orderStatus The status to be set for the order.
     */
    public void setOrderStatus(Status orderStatus) {
        this.orderStatus = orderStatus;
    }

    // End of class definition
}
