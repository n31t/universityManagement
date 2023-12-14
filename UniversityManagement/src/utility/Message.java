package utility;

import java.util.Date;

import users.Employee;
import users.User;

import java.util.Date;

public class Message {
	private int messageId;
	private static int counterId = 0;
	private Employee sender;
	private User reciever;
	private String messageText;
	private Date date;
	{
		messageId = counterId++;
	}
	public Message() {
		
	}
	public Message(User reciever, String messageText) {
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