package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import utility.Request;
import database.Database;
import researchWorks.Journal;
import utility.Message;
import utility.Order;

public class Employee extends User {
    private static final long serialVersionUID = 1L;
	private int employeeId;
    private static int countForEmployees = 0;
    {
    	employeeId = (countForEmployees++);
    }
    public Employee() {
    	super();
    }
    public int getEmployeeId() {
        return this.employeeId;
    }
                               
    public void sendMessage() {
    	try {
    	String s;
    	int userId;
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter your message text:");
    	s = reader.readLine();
    	System.out.println("Enter user's id that will recieve message:");
    	userId = Integer.parseInt(reader.readLine());
    	
    	User u = Database.getInstance().getUserById(userId);
    	if (u != null) {
            Message m = new Message(this, u, s);
            u.recieveMessage(m);
        } else {
            System.out.println("User with ID " + userId + " not found.");
        }
    	
    	}
    	catch(NumberFormatException |IOException e) {
    		System.out.println("Invalid input. Please enter a valid string and integer.");
    	}
    	
    }  
    
    //Need fix
    public void sendRequestToDean() {
        try {
            String request;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter your request:");
            request = reader.readLine();
            Request r = new Request(request);
            Database.getInstance().getDeanRequests().add(r);
            System.out.println("Your request will be reviewed as soon as possible");

        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid string.");
        }
    }
    public void sendOrder() {
    	try {
            String orderText;

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Enter your order:");
            orderText = reader.readLine();
            Order o = new Order(orderText);
            Database.getInstance().getOrders().add(o);
            System.out.println("Your order will be reviewed as soon as possible");
            
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid string.");
        }
    }
    public void showCommands() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Employee Commands:");
            
            System.out.println("1. View Journals");
            System.out.println("2. Subscribe to Journal");
            System.out.println("3. Unsubscribe from Journal");
            System.out.println("4. View Messages");
            System.out.println("5. Clear Messages");
            System.out.println("6. View News");
            System.out.println("7. Change Language");
            System.out.println("8. Change Password");
            System.out.println("9. Exit");
            System.out.println("10. Send Message"); //!
            System.out.println("11. Send Request to Dean"); //!
            System.out.println("12. Send Order"); //!

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
                        sendRequestToDean();
                        break;
                    case 12:
                    	sendOrder();
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
