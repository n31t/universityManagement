package users;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import database.Database;
import utility.Complaint;
import utility.Message;
import utility.Request;

public class Dean extends Employee implements InfoRequests {

    private static final long serialVersionUID = 1L;

	public Dean() {
    	super();
    }
    
    public void viewComplaints() {
        for (Complaint complaint : Database.getInstance().getComplaints()) {
            System.out.println("Complaint id: " + complaint.getComplaintId() + "\n" + complaint.getComplaintReciever().getUserId() + 
            		' ' +
            		complaint.getComplaintReciever().getName() + ' ' + complaint.getUrgencyLevel() + "\n"+
            		complaint.getComplaintText() + "\n"+
            		"Sender: " + complaint.getComplaintSender().getUserId() + ' ' + complaint.getComplaintSender().getName());
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
            } else {
                System.out.println("Request with ID " + requestId + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
  //Need fix
    public void declineRequest() {
    	
    }
  //Need fix
    public void viewRequests() {
    	
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
