package utility;

import users.Student;
import users.Teacher;

public class Complaint {
	private int complaintId;
	private static int counterId = 0;
	private UrgencyLevel urgencyLevel;
	private Teacher complaintSender;
	private Student complaintReciever;
	private String complaintText;
	{
		complaintId = counterId++;
	}
	public Complaint(UrgencyLevel urgencyLevel,Student complaintReciever,
			String complaintText) {
		this.urgencyLevel = urgencyLevel;
		this.complaintReciever = complaintReciever;
		this.complaintText = complaintText;
	}
	public Complaint() {

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
