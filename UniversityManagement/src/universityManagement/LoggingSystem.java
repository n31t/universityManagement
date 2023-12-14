package universityManagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import database.Database;
import users.User;

public class LoggingSystem {
    private static boolean isLogged = false;

    public static User searchUserByName(int userId, String password) {
        Vector<User> users = Database.getInstance().getUsers();
        for (User user : users) {
            if (user.getUserId() == userId && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int userId = 0;
        String password;

        while (true) {
            System.out.println("Enter your ID and password:");
            User user;
            try {
                userId = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.\n");
                continue;
            }

            try {
                password = reader.readLine();
            } catch (IOException e) {
                System.out.println("Error reading password. Please try again.\n");
                continue;
            }
            
            try {
            	user = searchUserByName(userId, password);
            } catch (Exception e) {
                System.out.println("Error searching User. Please try again.\n");
                user = null;
                continue;
            }

            if (user != null) {
                isLogged = true;
                System.out.println("Login successful!");
                //News shows
                user.showJournalUpdates();
                while (isLogged) {
                    System.out.println("Type 1 to logout");
                    System.out.println("Type 2 to show commands");

                    try {
                        int choice = Integer.parseInt(reader.readLine());

                        switch (choice) {
                            case 1:
                                isLogged = false;
                                System.out.println("Logged out successfully!");
                                break;
                            case 2:
                            	try {
                                user.showCommands();
                            	}
                            	catch(Exception e) {
                            		System.out.println("Some error occurred.");
                            	}
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    } catch (NumberFormatException | IOException e) {
                        System.out.println("Invalid input. Please enter a valid integer.");
                    }
                }
            } else {
                System.out.println("Wrong login or password. Please try again.\n");
            }
        }
    }
}