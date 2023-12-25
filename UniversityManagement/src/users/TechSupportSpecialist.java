package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Vector;

import database.Database;
import enums.Status;
import researchWorks.Journal;
import utility.Order;
public class TechSupportSpecialist extends Employee{

	private static final long serialVersionUID = 1L;
	public TechSupportSpecialist() {
		super();
	}
	public TechSupportSpecialist(String name , String password) {
    	super(name, password);
    }
	public void viewOrders() {
		Database.getInstance().getOrders().stream()
        .forEach(order -> {
            System.out.println("Order id: " + order.getOrderId() + "\n" +
                    " " +
                    order.getOrderText() + "\n");
        });
	    
		
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
            System.out.println("13. See the order");
            System.out.println("14. Accept order");
            System.out.println("15. Reject the order");
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
                    
                    case 13:
                    	this.viewOrders();
            	        break;
            
            	    case 14:       
            	    	try {
            	    		System.out.println("Enter the id of the order you want to accept");
            	    		int idAccept = Integer.parseInt(reader.readLine());
            	    		Database.getInstance().getOrders().stream().
							filter(order -> order.getOrderId() == idAccept).findFirst().
							ifPresent(order -> order.setOrderStatus(Status.ACCEPTED));
            	        }catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
          	        break;

            	    case 15:
            	    	try {
            	    		System.out.println("Enter the id of the order you want to reject");
            	    		int idReject = Integer.parseInt(reader.readLine());
            	    		Database.getInstance().getOrders().stream()
            	    								.filter(order -> order.getOrderId() == idReject).findFirst()
            	    								.ifPresent(order -> order.setOrderStatus(Status.REJECTED));
            	    		
            	        }catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
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
		
		
