<<<<<<< HEAD
package users;

import enums.Status;
import enums.TeacherType;
import enums.UrgencyLevel;
import researchWorks.Journal;
import utility.Complaint;
import utility.Course;
import utility.Mark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import database.Database;
public class Teacher extends Employee implements CanViewCourses, GiveMarks, InfoStudents {
	
    private static final long serialVersionUID = 1L;
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
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Putting Marks:");
            System.out.print("Enter the ID of the student: ");
            int studentId = Integer.parseInt(reader.readLine());

            Student student = Database.getInstance().findStudentById(studentId);
            if (student == null) {
                System.out.println("Student with ID " + studentId + " not found.");
                return;
            }

            System.out.print("Enter the Course ID: ");
            int courseId = Integer.parseInt(reader.readLine());

            Course course = Database.getInstance().getCourses().stream()
                    .filter(c -> c.getCourseId() == courseId)
                    .findFirst()
                    .orElse(null);

            if (course == null) {
                System.out.println("Course with ID " + courseId + " not found.");
                return;
            }
            if (!course.getInstructors().contains(this)) {
                System.out.println("You are not the instructor for this course.");
                return;
            }
            if (!course.getMarks().containsKey(student)) {
                System.out.println("Student with ID " + studentId + " is not enrolled in the course.");
                return;
            }

            System.out.println("Choose the type of mark to enter:");
            System.out.println("1. First Attestation");
            System.out.println("2. Second Attestation");
            System.out.println("3. Final Mark");

            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter marks for the first attestation: ");
                    int firstAttestation = Integer.parseInt(reader.readLine());
                    course.getMarks().get(student).setFirstAttestation(firstAttestation);
                    System.out.println("First attestation marks added successfully.");
                    break;

                case 2:
                        System.out.print("Enter marks for the second attestation: ");
                        int secondAttestation = Integer.parseInt(reader.readLine());
                        course.getMarks().get(student).setSecondAttestation(secondAttestation);
                        System.out.println("Second attestation marks added successfully.");

                    break;

                case 3:
                    if (course.getMark(student).getFirstAttestation()
                            + course.getMark(student).getSecondAttestation() >= 30) {
                        System.out.print("Enter final marks: ");
                        int finalMark = Integer.parseInt(reader.readLine());
                        course.getMarks().get(student).setFinal(finalMark);
                        System.out.println("Final marks added successfully.");
                        
                        if(course.getMark(student).getFinal()>=20) {
                        	System.out.println("Student passed the course.");
                        	student.getPassedCourses().add(course);
                        	student.getEnrolledCourseId().remove(course.getCourseId());
                        }
                        else if(course.getMark(student).getFinal()>=10) {
                        	System.out.println("Student got FX.");
                        }
                        else if(course.getMark(student).getFinal()<10) {
                        	System.out.println("Student got retake. All his Marks are 0 now");
                        	course.getMarks().get(student).setFirstAttestation(0);
                        	course.getMarks().get(student).setSecondAttestation(0);
                        	course.getMarks().get(student).setFinal(0);
                        }
                    } else {
                        System.out.println("Cannot enter final marks. Both attestations scores are less than 30.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }


    @Override
    public void viewStudents() {
        System.out.println("Viewing Students:");
        Vector<Course> coursesTaught = Database.getInstance().getCourses().stream()
                .filter(course -> course.getInstructors().contains(this))
                .collect(Vector<Course>::new, Vector::add, Vector::addAll);

        if (coursesTaught.isEmpty()) {
            System.out.println("You are not instructing any courses.");
            return;
        }

        System.out.println("Courses you are instructing:");
        coursesTaught.forEach(course -> System.out.println("Course ID: " + course.getCourseId() +
                ", Course Name: " + course.getCourseName()));

        System.out.print("Enter the Course ID to view students: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int courseId = Integer.parseInt(reader.readLine());
            Course selectedCourse = coursesTaught.stream()
                    .filter(course -> course.getCourseId() == courseId)
                    .findFirst()
                    .orElse(null);

            if (selectedCourse != null) {
                System.out.println("Students enrolled in course " + selectedCourse.getCourseName() + ":");
                selectedCourse.getMarks().keySet().forEach(student -> {
                    Mark mark = selectedCourse.getMark(student);
                    System.out.println("ID: " + student.getUserId() +
                            ", Name: " + student.getName() +
                            ", Mark: " + (mark != null ? mark.getFirstAttestation() + " " + mark.getSecondAttestation() +
                            		mark.getFinal(): "Not graded"));
                });
            } else {
                System.out.println("Course with ID " + courseId + " not found or you are not instructing it.");
            }

        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
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
            System.out.println("17. Researcher menu");
            System.out.println("18. View Students");
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
                    	        if (urgL != null) {
                    	            final UrgencyLevel finalUrgL = urgL;
                    	            Database.getInstance().getStudents().stream()
                    	                    .filter(student -> student.getUserId() == idStudent)
                    	                    .findFirst()
                    	                    .ifPresent(student -> this.sendComplaint(student, finalUrgL));
                    	        } else {
                    	            System.out.println("No such Urgency level, try again");
                    	        }
                    	    } catch (NumberFormatException | IOException e) {
                    	        System.out.println("Invalid input. Please enter a valid integer.");
                    	    }
                    	    break;
                    case 16: 
                    	this.putMarks(); 
                    case 17:
                    	ResearcherDecorator Researcher = Database.getInstance().findResearcherById(this.getUserId());
                    	if(Researcher != null){
                    		Researcher.showResearcherCommands();
                    	}
                    	else {
                    		System.out.println("User have no permission to do that. Firstly became a Researcher");
                    	}
                    	break;
                    case 18:
                    	viewStudents();
            	    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
	}


}
=======
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
>>>>>>> c20d933fa70c3a9d7629578e37ce775de886fba4
