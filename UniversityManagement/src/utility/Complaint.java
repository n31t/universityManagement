package utility;

import java.io.Serializable;

import database.Database;
import enums.UrgencyLevel;
import users.Student;
import users.Teacher;

/**
 * Represents a complaint in a university management system.
 * This class encapsulates details of a complaint such as its urgency level, sender, receiver, and the complaint text.
 */
public class Complaint implements Serializable{
	private static final long serialVersionUID = 1L;

	private int complaintId;

	private UrgencyLevel urgencyLevel;
	private Teacher complaintSender;
	private Student complaintReciever;
	private String complaintText;
	// Initializer block for complaintId
	{
		complaintId = Database.nextId();
	}
	  /**
     * Constructs a Complaint with specified urgency level, receiver, and complaint text.
     * The sender is not set in this constructor.
     *
     * @param urgencyLevel The urgency level of the complaint.
     * @param complaintReceiver The student who is the receiver
     * @param complaintText The student who is the receiver
     */
	public Complaint(UrgencyLevel urgencyLevel,Student complaintReciever,
			String complaintText) {
		this.urgencyLevel = urgencyLevel;
		this.complaintReciever = complaintReciever;
		this.complaintText = complaintText;
	}
	public Complaint() {

	}
	
	public UrgencyLevel getUrgencyLevel() {
		return urgencyLevel;
	}
	public void setUrgencyLevel(UrgencyLevel urgencyLevel) {
		this.urgencyLevel = urgencyLevel;
	}
	
	public int getComplaintId() {
		return this.complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public Teacher getComplaintSender() {
		return this.complaintSender;
	}

	public void setComplaintSender(Teacher complaintSender) {
		this.complaintSender = complaintSender;
	}

	public Student getComplaintReciever() {
		return this.complaintReciever;
	}

	public void setComplaintReciever(Student complaintReciever) {
		this.complaintReciever = complaintReciever;
	}

	public String getComplaintText() {
		return this.complaintText;
	}

	public void setComplaintText(String complaintText) {
		this.complaintText = complaintText;
	}
}
