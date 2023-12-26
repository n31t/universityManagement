package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import database.Database;
import enums.ManagerType;
import researchWorks.Journal;
import researchWorks.ResearchPaper;
import utility.Course;
import utility.CourseForRegistration;
import utility.Lesson;
import utility.Mark;
import utility.News;
import utility.Organization;

public class Manager extends Employee implements InfoTeachers, CanViewCourses, InfoRequests, InfoStudents {

    private static final long serialVersionUID = 1L;
	private ManagerType managerType;

    
    public Manager() {
    	
    }
    
	public Manager(String name , String password, ManagerType managerType) {
		super( name , password);
		this.managerType = managerType;
	}
	public ManagerType getManagerType() {
		return managerType;
	}
	public void setManagerType(ManagerType managerType) {
		this.managerType = managerType;
	}
	@Override
	public void viewStudents() {
        System.out.println("Viewing Students:");
        Database.getInstance().getStudents().stream()
                .forEach(student -> System.out.println("ID: " + student.getUserId() +
                        ", Name: " + student.getName() +
                        ", Study Year: " + student.getStudyYear() +
                        ", School: " + student.getSchool()));
    }
	@Override
	public void viewRequests() {
        System.out.println("Viewing Requests:");
        Database.getInstance().getManagerRequests().stream()
                .forEach(request -> System.out.println("ID: " + request.getRequestId() +
                        ", Request Text: " + request.getRequestText()));
    }

	@Override
	public void viewCourses() {
		 for (Course course : Database.getInstance().getCourses()) {
			System.out.println("Course ID: " + course.getCourseId());
	        System.out.println("Course Name: " + course.getCourseName());
	        System.out.println("Cost in credits: " + course.getCostInCredits());
	        System.out.print("Lesson Days: ");
	        for (Lesson lesson : course.getLessons()) {
	            lesson.viewDays();
	        }
	        System.out.println();
		 }
		
    }

	@Override
    public void viewTeachers() {
        System.out.println("Viewing Teachers:");
        Database.getInstance().getTeachers().stream()
                .forEach(teacher -> System.out.println("ID: " + teacher.getUserId() +
                        ", Name: " + teacher.getName() +
                        ", Teacher Type: " + teacher.getType()));
    }

	public void createStudentOrganization(String orgName, Student head) {
	    Organization organization = new Organization(orgName, head);
	    Database.getInstance().getOrganizations().add(organization);
	    System.out.println("Student organization '" + orgName + "' created successfully.");
	}

	public void deleteStudentOrganization(String orgName) {
	    Organization organizationToRemove = Database.getInstance().getOrganizations().stream()
	            .filter(org -> org.getName().equalsIgnoreCase(orgName))
	            .findFirst()
	            .orElse(null);

	    if (organizationToRemove != null) {
	        Database.getInstance().getOrganizations().remove(organizationToRemove);
	        System.out.println("Student organization '" + orgName + "' deleted successfully.");
	    } else {
	        System.out.println("Student organization '" + orgName + "' not found.");
	    }
	}
	public void publishNews() {
		try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                News news = new News();
                System.out.println("Enter news title:");
                String title = reader.readLine();

                System.out.println("Enter content:");
                String content = reader.readLine();
                news.setTopic(title);
                news.setContent(content);
                news.setPinned(false);

                Database.getInstance().getNews().add(news);

                System.out.println("News published successfully!");
            }catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
	}
	public void clearNews() {
	    if (Database.getInstance().getNews().isEmpty()) {
	        System.out.println("No news to clear.");
	        return;
	    }
	    System.out.println("Clearing News:");
	    System.out.println("Do you really want to clear all news? (yes/no)");
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
	        String confirmation = reader.readLine().trim().toLowerCase();

	        if (confirmation.equals("yes")) {
	            Database.getInstance().getNews().clear();
	            System.out.println("All news cleared successfully.");
	        } else {
	            System.out.println("Clearing news canceled.");
	        }
	    } catch (IOException e) {
	        System.out.println("Error reading input. Please try again.");
	    }
	}
	
	public void viewCoursesForRegistration() {
	    System.out.println("Courses Available for Registration:");
	    Database.getInstance().getCoursesForRegistration().forEach(registration -> {
	        System.out.println("Registration ID: " + registration.getRegistrationId());
	        System.out.println("Student ID: " + registration.getStudent().getUserId());
	        System.out.println("Student Name: " + registration.getStudent().getName());
	        System.out.println("Course ID: " + registration.getCourse().getCourseId());
	        System.out.println("Course Name: " + registration.getCourse().getCourseName());
	        System.out.println();
	    });

	    if (Database.getInstance().getCoursesForRegistration().isEmpty()) {
	        System.out.println("No courses available for registration.");
	    }
	}
	public void assignCourseToTeacher(Course course, Teacher teacher) {
	    if (course == null || teacher == null) {
	        System.out.println("Invalid course or teacher.");
	        return;
	    }

	    course.addInstructors(teacher);
	    course.getLessons().forEach(lesson -> lesson.setTeacher(teacher));

	    System.out.println("Course '" + course.getCourseName() + "' assigned to teacher '" + teacher.getName() + "'.");
	}
	public void rejectStudentRegistration(int registrationId) {
        CourseForRegistration registrationToRemove = Database.getInstance().getCoursesForRegistration().stream()
                .filter(registration -> registration.getRegistrationId() == registrationId)
                .findFirst()
                .orElse(null);

        if (registrationToRemove != null) {
            Database.getInstance().getCoursesForRegistration().remove(registrationToRemove);

            Student student = registrationToRemove.getStudent();
            int refundedCredits = registrationToRemove.getCourse().getCostInCredits();
            student.setCredits(student.getCredits() + refundedCredits);

            System.out.println("Registration for course '" + registrationToRemove.getCourse().getCourseName() +
                    "' rejected for student '" + student.getName() + "'. Refunded " + refundedCredits + " credits.");
        } else {
            System.out.println("Registration with ID " + registrationId + " not found.");
        }
    }
	public void approveStudentRegistration(int registrationId) {
        CourseForRegistration registrationToApprove = Database.getInstance().getCoursesForRegistration().stream()
                .filter(registration -> registration.getRegistrationId() == registrationId)
                .findFirst()
                .orElse(null);

        if (registrationToApprove != null) {
            Database.getInstance().getCoursesForRegistration().remove(registrationToApprove);
            Student student = registrationToApprove.getStudent();
            Course courseToAdd = registrationToApprove.getCourse();

            student.getEnrolledCourseId().add(courseToAdd.getCourseId());
            Mark mark = new Mark();
            courseToAdd.getMarks().put(student, mark);

            System.out.println("Registration for course '" + courseToAdd.getCourseName() +
                    "' approved for student '" + student.getName() + "'.");
        } else {
            System.out.println("Registration with ID " + registrationId + " not found.");
        }
    }
    @SuppressWarnings("static-access")
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
            System.out.println("14. View Students");
            System.out.println("15. View Requests");
            System.out.println("16. View Courses");
            System.out.println("17. View Teachers");
            System.out.println("18. Create Student Organization");
            System.out.println("19. Delete Student Organization");
            System.out.println("20. Publish News");
            System.out.println("21. Clear News");
            System.out.println("22. View Courses for Registration");
            System.out.println("23. Assign Course to Teacher");
            System.out.println("24. Reject Student Registration");
            System.out.println("25. Approve Student Registration");


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
                        viewStudents();
                        break;
                    case 15:
                        viewRequests();
                        break;
                    case 16:
                        viewCourses();
                        break;
                    case 17:
                        viewTeachers();
                        break;
                    case 18:
                        System.out.println("Enter the name of the organization:");
                        String orgName = reader.readLine();
                        System.out.println("Enter the ID of the head student:");
                        int headStudentId = Integer.parseInt(reader.readLine());
                        Student headStudent = Database.getInstance().findStudentById(headStudentId);
                        if (headStudent != null) {
                            createStudentOrganization(orgName, headStudent);
                        } else {
                            System.out.println("Student with ID " + headStudentId + " not found.");
                        }
                        break;
                    case 19:
                        System.out.println("Enter the name of the organization to delete:");
                        String orgToDelete = reader.readLine();
                        deleteStudentOrganization(orgToDelete);
                        break;
                    case 20:
                        publishNews();
                        break;
                    case 21:
                        clearNews();
                        break;
                    case 22:
                        viewCoursesForRegistration();
                        break;
                    case 23:
                        System.out.println("Enter the ID of the course to assign:");
                        int courseIdToAssign = Integer.parseInt(reader.readLine());
                        Course courseToAssign = Database.getInstance().findCourseById(courseIdToAssign);
                        System.out.println("Enter the ID of the teacher to assign:");
                        int teacherIdToAssign = Integer.parseInt(reader.readLine());
                        Teacher teacherToAssign = Database.getInstance().findTeacherById(teacherIdToAssign);
                        assignCourseToTeacher(courseToAssign, teacherToAssign);
                        break;
                    case 24:
                        System.out.println("Enter the registration ID to reject:");
                        int registrationIdToReject = Integer.parseInt(reader.readLine());
                        rejectStudentRegistration(registrationIdToReject);
                        break;
                    case 25:
                        System.out.println("Enter the registration ID to approve:");
                        int registrationIdToApprove = Integer.parseInt(reader.readLine());
                        approveStudentRegistration(registrationIdToApprove);
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
