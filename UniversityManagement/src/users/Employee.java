package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import database.Database;
import utility.Message;
import utility.Order;

public class Employee extends User {
    private static final long serialVersionUID = 1L;
	private int employeeId;
    private static int countForEmployees = 0;
    {
    	employeeId = (countForEmployees++);
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
            System.out.println("1. Send Message");
            System.out.println("2. Send Request to Dean");
            System.out.println("3. Send Order");
            System.out.println("4. Exit");

            try {
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        sendMessage();
                        break;
                    case 2:
                        sendRequestToDean();
                        break;
                    case 3:
                        sendOrder();
                        break;
                    case 4:
                        System.out.println("Exiting Employee commands.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    
}
