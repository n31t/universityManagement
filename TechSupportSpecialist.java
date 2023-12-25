package Employees;

import java.util.Scanner;
import java.util.Vector;
// here import of the database, i have no idea how it works tbh


public class TechSupportSpecialist extends Employee{

	public TechSupportSpecialist() {
		super();
	}
	public TechSupportSpecialist(String name , String password) {
    	super(name, password);
    }
	public Vector<Order> getOrders() {
		return orders;
	}
	public void acceptOrder(Order o) {
		Database.getInstance.getOrders.add(o);
		System.out.println("Order succesfully accepted");
	}
	public void rejectOrder(Order o) {
		System.out.println("order rejected");
	}
	public void viewOrders() {
	        for (Order order : Database.getInstance().getOrders()) {
	            System.out.println("Order id: " + order.getComplaintId() + "\n" + 
	            		" " +
	            		order.getOrderReciever().getName() + "\n" +
	            		order.getOrderText() + "\n" +
	            		"Sender: " + order.getOrderSender().getUserId() + " " + order.getOrderSender().getName());
	        }
	    
		
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
            System.out.println("13. Researcher menu");
            System.out.println("14. See the order");
            System.out.println("15. Accept order");
            System.out.println("16. Reject the order");
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
                    case 13: //Researcher
                    	ResearcherDecorator Researcher = Database.getInstance().findResearcherById(this.getUserId());
                    	if(Researcher != null){
                    		Researcher.showResearcherCommands();
                    	}
                    	else {
                    		System.out.println("User have no permission to do that. Firstly became a Researcher");
                    	}
                    	break;
                    
                    case 14:
            	        this.viewOrders();
            	        break;
            
            	    case 15:
            	        System.out.println("Enter the id of the order you want to accept");
            	        int idAccept = in.nextInt();
            	        for (Order or :orders) {
            	            if (or.getId() == idAccept) {
            	                this.acceptOrder(or);
           	            }
            	        }
          	        break;

            	    case 16:
            	        System.out.println("Enter the id of the order you want to reject");
            	        int idReject = in.nextInt();
            	        for (Order or : orders) {
            	            if (or.getId() == idReject) {            	                this.rejectOrder(or);
            	            }
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
		
		

