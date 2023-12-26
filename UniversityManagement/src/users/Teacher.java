package users;

import enums.Status;
import enums.TeacherType;
import enums.UrgencyLevel;
import researchWorks.Journal;
import utility.Complaint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import database.Database;
public class Teacher extends Employee implements CanViewCourses, GiveMarks, InfoStudents {
	
    private TeacherType teacherType;

    public Teacher() {}
    
    public Teacher(TeacherType teachertp, String name , String password) {
    	super(name, password);
    	this.teacherType = teachertp;
    }
    
    public void viewCourses() {
    	System.out.println("The teacher with this id: " + this.getUserId() 
    						+ "\n" +" teaches these courses: ");
    	Database.getInstance().getCourses().stream().
    	filter(course -> course.getInstructors().contains(this)).
    	forEach(course -> {
    		System.out.println(course.getCourseName() + "\n");
    	});
    }
    public TeacherType getType() {
    	return this.teacherType;
    }
    
    public void putMarks() {
//    	idk what to do here
    }
    
    public void sendComplaint(Student stu, UrgencyLevel lvl) {
    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Describe a complaint for the student: ");
            String complaintText = reader.readLine();
            
            Complaint com = new Complaint(lvl, stu, complaintText);
        } catch (IOException e) {
            System.out.println("Invalid input. Please enter a valid text.");
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
            System.out.println("13. See The Type Of Teacher");
            System.out.println("14. View Courses");
            System.out.println("15. Send Complaint");
            System.out.println("16. Put the Marks");
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
                    	this.getType();
            	        break;
                    case 14:
                    	this.viewCourses();
            	        break;
                    case 15:
                    	try {
            	    		System.out.println("Enter the id of the Student you want to complain about: ");
            	    		int idStudent = Integer.parseInt(reader.readLine());
            	    		System.out.println("Enter the urgency level of the complaint you are making: " + "\n"
            	    		+ "1.Low" + "\n" + "2. Medium" + "\n" + "3.High");
            	    		Map<Integer, UrgencyLevel> urgencyMap = new HashMap<>();
            	            urgencyMap.put(1, UrgencyLevel.LOW);
            	            urgencyMap.put(2, UrgencyLevel.MEDIUM);
            	            urgencyMap.put(3, UrgencyLevel.HIGH);
            	            UrgencyLevel urgL = null;
            	            try {
            	            	int UrgencyLvl = Integer.parseInt(reader.readLine());
            	            	urgL = urgencyMap.get(UrgencyLvl);
            	            	
            	            } catch (NumberFormatException | IOException e) {
                                System.out.println("Invalid input. Please enter a valid integer.");
                            }
            	    		if(urgL!= null) {
            	    			Database.getInstance().getStudents().stream().
            	    			filter(student -> student.getUserId() == idStudent).findFirst().
            	    			ifPresent(student -> this.sendComplaint(student, urgL));
            	    		}
            	    		else {
            	    			System.out.println("No such Urgency level, try again");
//            	    			 idk how to improve this part ((
            	    		}
            	        }catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                    case 16: 
                    	this.putMarks(); //???
            	    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
	}
}
