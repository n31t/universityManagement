package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.Database;
import enums.Status;
import utility.Complaint;
import utility.Message;
import utility.Request;

public class Dean extends Employee implements InfoRequests {

    private static final long serialVersionUID = 1L;

	public Dean() {
    	super();
    }
	
	public Dean(String name , String password) {
    	super(name, password);
    }
 
    public void viewComplaints() {
        for (Complaint complaint : Database.getInstance().getComplaints()) {
            System.out.println("Complaint id: " + complaint.getComplaintId() + "\n" + complaint.getComplaintReciever().getUserId() + 
            		" " +
            		complaint.getComplaintReciever().getName() + " " + complaint.getUrgencyLevel() + "\n"+
            		complaint.getComplaintText() + "\n"+
            		"Sender: " + complaint.getComplaintSender().getUserId() + " " + complaint.getComplaintSender().getName());
        }
    }
    //Need fix
    public void acceptRequest() {
    	System.out.println("Enter the ID of the request you want to accept:");
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int requestId = Integer.parseInt(reader.readLine());
            Request requestToAccept = Database.getInstance().findRequestById(requestId);
            if (requestToAccept != null) {
                System.out.println("Request accepted successfully!");
                //Need fix (enum change to Accepted)
                Database.getInstance().getManagerRequests().add(requestToAccept);
                requestToAccept.setRequestStatus(Status.ACCEPTED);
                Database.getInstance().getDeanRequests().remove(requestToAccept);
            } else {
                System.out.println("Request with ID " + requestId + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
  //Need fix
    public void declineRequest() {
    	System.out.println("Enter the ID of the request you want to decline:");
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int requestId = Integer.parseInt(reader.readLine());
            Request requestToAccept = Database.getInstance().findRequestById(requestId);
            if (requestToAccept != null) {
                System.out.println("Request declined successfully!");
                //Need fix (enum change to Accepted)
                requestToAccept.setRequestStatus(Status.REJECTED);
            } else {
                System.out.println("Request with ID " + requestId + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
  //Need fix
    public void viewRequests() {
    	for (Request request : Database.getInstance().getDeanRequests()) {
            System.out.println("Request id: " + request.getRequestId() + "\n" + request.getRequestStatus() + "\n" +
            		request.getRequestText()
            		);
        }
    }
    
    //Need fix
    public void kickStudent(Student s) {
    	//sends message to Admin
    	//admin deletes this student
        Message kickMessage = new Message(this, Database.getInstance().getAdmins().get(0), 
        		"Dean wants to kick student with ID: " + s.getUserId());
    }
    
    public void showCommands() {
    	
    }
}
