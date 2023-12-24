package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.Database;
import enums.Status;
import researchWorks.Journal;
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
        		"Dean wants to kick student with ID: " + s.getUserId() + " because he got too much complaints");
        Database.getInstance().getAdmins().get(0).recieveMessage(kickMessage);;    
        }
    
    public void showCommands() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
        	//DB
            try {
				Database.getInstance().write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //DB
        	
            System.out.println("Dean Commands:");
            
            System.out.println("1. View Journals");
            System.out.println("2. Subscribe to Journal");
            System.out.println("3. Unsubscribe from Journal");
            System.out.println("4. View Messages");
            System.out.println("5. Clear Messages");
            System.out.println("6. View News");
            System.out.println("7. Change Language");
            System.out.println("8. Change Password");
            System.out.println("9. Exit");
            System.out.println("10. Send Message"); 
            System.out.println("11. Send Order"); 
            System.out.println("12. Kick Student");
            System.out.println("13. View Requests");
            System.out.println("14. Accept Request");
            System.out.println("15. Decline Request");
            System.out.println("16. View Complaints");

            try {
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        viewJournals();
                        break;
                    case 2:
                    	System.out.println("Enter the ID of the journal you want to subscribe to:");
                        try {
                            int journalIdToSubscribe = Integer.parseInt(reader.readLine());
                            Journal journalToSubscribe = Database.getInstance().findJournalById(journalIdToSubscribe);
                            if (journalToSubscribe != null) {
                                subscribeToJournal(journalToSubscribe);
                                System.out.println("Subscribed to journal successfully!");
                            } else {
                                System.out.println("Journal with ID " + journalIdToSubscribe + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 3:
                        System.out.println("Enter the ID of the journal you want to unsubscribe from:");
                        try {
                            int journalIdToUnsubscribe = Integer.parseInt(reader.readLine());
                            Journal journalToUnsubscribe = Database.getInstance().findJournalById(journalIdToUnsubscribe);
                            if (journalToUnsubscribe != null) {
                                unsubscribeFromJournal(journalToUnsubscribe);
                                System.out.println("Unsubscribed from journal successfully!");
                            } else {
                                System.out.println("Journal with ID " + journalIdToUnsubscribe + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 4:
                    	viewMessages();
                        break;
                    case 5:
                    	clearMessages();
                        break;
                    case 6:
                    	viewNews();
                        break;
                    case 7:
                    	changeLanguage();
                        break;
                    case 8:
                    	System.out.println("Enter your new password:");
                        String newPassword = reader.readLine();
                        setPassword(newPassword);
                        System.out.println("Password changed successfully!");
                        break;
                    case 9:
                        System.out.println("Exiting Employee commands.");
                        return;
                    case 10:
                        sendMessage();
                        break;
                    case 11:
                    	sendOrder();
                    	break;
                    case 12:
                        System.out.println("Enter the ID of the student you want to kick:");
                        try {
                            int studentIdToKick = Integer.parseInt(reader.readLine());
                            Student studentToKick = Database.getInstance().findStudentById(studentIdToKick);
                            if (studentToKick != null) {
                                kickStudent(studentToKick);
                            } else {
                                System.out.println("Student with ID " + studentIdToKick + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 13:
                        viewRequests();
                        break;
                    case 14:
                        acceptRequest();
                        break;
                    case 15:
                        declineRequest();
                        break;
                    case 16:
                        viewComplaints();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }	
    }
}
