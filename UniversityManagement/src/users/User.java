package users;

import java.io.*;


import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import database.Database;
import exceptions.InvalidLanguageException;
import languageManagement.*;
import utility.*;
import researchWorks.Journal;

@SuppressWarnings("deprecation")
public class User implements Observer, Serializable {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
    private int userId;
    private String name;
    private String password;
    private String journalNews ="";
    private Vector<Message> messages;
    {
    	userId = (count++);
    	messages = new Vector<Message>();
    }
    
    public User() {
	}
    
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
    public int getUserId() {
        return this.userId;
    }

//    private void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public String getName() {
        return this.name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
//--------------- JOURNAL --------------- 
	public void showJournalUpdates() {
		System.out.println(journalNews);
		journalNews = "";
	}
	
	public void update(Observable o, Object arg) {
        journalNews+= arg + "\n";
    }
	public void subscribeToJournal(Journal journal) {
        journal.addObserver(this);
    }

	public void unsubscribeFromJournal(Journal journal) {
        journal.deleteObserver(this);
    }
    public void viewJournals() {
    	System.out.println("Journals: ");
		for(Journal j : Database.getInstance().getJournals()) {
			System.out.println(j.getJournalId() + " " + j.getJournalName());
			System.out.println("-----------------------");
		}
    }
    
  //---------------------------------------- 
    
	public void viewMessages() {
		System.out.println("Messages: ");
		for(Message msg : messages) {
			System.out.println("User with id and name: " + msg.getSender().getUserId()+ " " + msg.getSender().getName() +
					" sent a message to you [ " + msg.getDate() + " ] : \n "
					+ msg.getMessageText());
			System.out.println("-----------------------");
		}
	}
	public void recieveMessage(Message m) {
		messages.add(m);
	}
	
	public void clearMessages() {
		messages.clear();
		System.out.println("Messages are cleared");
	}
	 //NeedFix
		public void viewNews() {
			System.out.println("News: ");
			for(News n : Database.getInstance().getNews()) {
				if(n.isPinned()) {
					System.out.println(n.getTopic());
					System.out.println(n.getContent() + "\n");
				}
			}
			for(News n : Database.getInstance().getNews()) {
				if(!n.isPinned()) {
					System.out.println(n.getTopic());
					System.out.println(n.getContent() + "\n");
				}
			}
		}
	public void changeLanguage() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			String lang_name;
			System.out.println("Input Language name: ");
            lang_name = reader.readLine();
            Language.getInstance().setLanguageName(lang_name);
            System.out.println("Successful!");
        } catch (IOException | InvalidLanguageException i) {
            System.out.println("Error reading language name. Please try again.");
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
                        System.out.println("Exiting Student commands.");
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
