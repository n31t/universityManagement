package utility;

import java.io.Serializable;
import java.util.Date;

import database.Database;
import users.Employee;
import users.User;

/**
 * Represents a message in a university management system.
 * This class encapsulates details of a message including the sender, receiver, message text, and the timestamp.
 */
public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private int messageId;
	private Employee sender;
	private User reciever;
	private String messageText;
	private Date date = new Date(System.currentTimeMillis());
	
	 // Initializer block for messageId
	{
		messageId = Database.nextId();
	}

	/**
     * Default constructor for creating a Message instance without predefined properties.
     */
    public Message() {
    }

    /**
     * Constructs a Message with a specified receiver and message text.
     * The sender is not set in this constructor.
     *
     * @param receiver The user who is the receiver of the message.
     * @param messageText The text of the message.
     */
    public Message(User receiver, String messageText) {
        this.reciever = receiver;
        this.messageText = messageText;
    }

    /**
     * Constructs a Message with specified sender, receiver, and message text.
     *
     * @param sender The employee who is the sender of the message.
     * @param receiver The user who is the receiver of the message.
     * @param messageText The text of the message.
     */
    public Message(Employee sender, User receiver, String messageText) {
        this.sender = sender;
        this.reciever = receiver;
        this.messageText = messageText;
    }

    /**
     * Retrieves the message ID.
     *
     * @return The message ID.
     */
    public int getMessageId() {
        return this.messageId;
    }

    /**
     * Sets the message ID.
     *
     * @param messageId The message ID to be set.
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    /**
     * Retrieves the receiver of the message.
     *
     * @return The receiver of the message.
     */
    public User getReceiver() {
        return this.reciever;
    }

    /**
     * Sets the receiver of the message.
     *
     * @param receiver The receiver to be set for the message.
     */
    public void setReceiver(User receiver) {
        this.reciever = receiver;
    }

    /**
     * Retrieves the sender of the message.
     *
     * @return The sender of the message.
     */
    public Employee getSender() {
        return this.sender;
    }

    /**
     * Sets the sender of the message.
     *
     * @param sender The sender to be set for the message.
     */
    public void setSender(Employee sender) {
        this.sender = sender;
    }

    /**
     * Retrieves the text of the message.
     *
     * @return The message text.
     */
    public String getMessageText() {
        return this.messageText;
    }

    /**
     * Sets the text of the message.
     *
     * @param messageText The message text to be set.
     */
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * Retrieves the timestamp of the message.
     *
     * @return The date and time the message was created.
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Sets the timestamp of the message.
     *
     * @param date The date and time to be set for the message.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    // End of class definition
}