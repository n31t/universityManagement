package utility;

import java.io.Serializable;
import java.util.Date;

import database.Database;
import users.Employee;
import users.User;


public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private int messageId;
	private Employee sender;
	private User reciever;
	private String messageText;
	private Date date = new Date(System.currentTimeMillis());
	{
		messageId = Database.nextId();
	}

	public Message() {

	}

	public Message(User reciever, String messageText) {
		this.reciever = reciever;
		this.messageText = messageText;
	}

	public Message(Employee sender, User reciever, String messageText) {
		this.sender = sender;
		this.reciever = reciever;
		this.messageText = messageText;
	}

	public int getMessageId() {
		return this.messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public User getReciever() {
		return this.reciever;
	}

	public void setReiever(User reciever) {
		this.reciever = reciever;
	}

	public Employee getSender() {
		return this.sender;
	}

	public void setReiever(Employee sender) {
		this.sender = sender;
	}

	public String getMessageText() {
		return this.messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}