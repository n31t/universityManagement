package users;

import java.io.*;


import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import database.Database;
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
		for(Journal j : Database.getInstance().getJournals()) {
			System.out.println(j.getJournalId() + ' ' + j.getJournalName());
		}
    }
    
  //---------------------------------------- 
    
	public void viewMessages() {
		for(Message msg : messages) {
			System.out.println("User with id and name: " + msg.getSender().getUserId()+ ' ' + msg.getSender().getName() +
					" sent a message to you [ " + msg.getDate() + " ] : \n "
					+ msg.getMessageText());
		}
	}
	public void recieveMessage(Message m) {
		messages.add(m);
	}
	
	public void clearMessages() {
		messages.clear();
	}
	 //NeedFix
		public void viewNews() {
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
            lang_name = reader.readLine();
            Language.getInstance().setLanguageName(lang_name);
        } catch (IOException | InvalidLanguageException i) {
            System.out.println("Error reading language name. Please try again.");
        }
	}
	
	public void showCommands() {
		System.out.println("ERROR USER HAS NO COMMANDS");
	}
    
}
