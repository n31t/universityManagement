package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import database.Database;
import enums.DegreesType;
import enums.School;
import researchWorks.Journal;
import researchWorks.ResearchPaper;
import researchWorks.ResearchProject;
import universityManagement.InvalidStudyYearException;
import universityManagement.InvalidSupervisorException;
import utility.Course;
import utility.Message;
import utility.Organization;

public class GraduateStudent extends Student {
    
    private static final long serialVersionUID = 1L;

	private DegreesType degreeType;

    private ResearcherDecorator researchSupervisor;

    private ResearchProject diplomaProject;
    
    private int diplomaProjectMark;
    
    {
    	researchSupervisor = null;
    	diplomaProject = null;    	
    }
    public GraduateStudent() {
    	
    }
    public GraduateStudent(String name , String password, int studyYear, School school,ResearcherDecorator researchSupervisor) throws InvalidStudyYearException, InvalidSupervisorException {
    	super(name , password, studyYear, school);
    	this.researchSupervisor = researchSupervisor;
    	if(researchSupervisor.calculateHIndex()<3) {
    		throw new InvalidSupervisorException();
    	}
    	ResearcherDecorator rd = new ResearcherDecorator(this);
    	Database.getInstance().getResearchers().add(rd);
    }
    public GraduateStudent(String name , String password, int studyYear, School school,
    		ResearcherDecorator researchSupervisor, ResearchProject diplomaProject) throws InvalidStudyYearException, InvalidSupervisorException {
    	super(name , password, studyYear, school);
    	this.researchSupervisor = researchSupervisor;
    	if(researchSupervisor.calculateHIndex()<3) {
    		throw new InvalidSupervisorException();
    	}
    	this.diplomaProject = diplomaProject;
    	ResearcherDecorator rd = new ResearcherDecorator(this);
    	Database.getInstance().getResearchers().add(rd);
    }
    
	public int getDiplomaProjectMark() {
		return diplomaProjectMark;
	}
	public void setDiplomaProjectMark(int diplomaProjectMark) {
		this.diplomaProjectMark = diplomaProjectMark;
	}
	public DegreesType getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(DegreesType degreeType) {
		this.degreeType = degreeType;
	}
	public ResearcherDecorator getResearchSupervisor() {
		return researchSupervisor;
	}
	public void setResearchSupervisor(ResearcherDecorator researchSupervisor) {
		this.researchSupervisor = researchSupervisor;
	}
	public ResearchProject getDiplomaProject() {
		return diplomaProject;
	}
	public void setDiplomaProject(ResearchProject diplomaProject) {
		this.diplomaProject = diplomaProject;
	}
	public void createDiplomaProject() {
		try {
			if(diplomaProject==null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the topic of the research project:");
            String topic = reader.readLine();

            ResearchProject project = new ResearchProject();
            project.setTopic(topic);
            
            Database.getInstance().getResearchers().stream()
					.filter(researcher -> researcher.getUserId() == this.getUserId())
					.findFirst()
					.ifPresent(researcher -> project.addParticipants(researcher));;
					
					diplomaProject = project;
            Database.getInstance().getResearchProjects().add(project);

            System.out.println("Diploma project created successfully!");
			}
			else {
				System.out.println("Diploma project already exist!");
			}
        } catch (IOException e) {
            System.out.println("Invalid input. Please enter a valid string.");
        }
		
	}
    
	public void defendDiplomaProject(Teacher t) {
	    if (diplomaProject != null) {
	        String projectDetails = "Diploma Project Details:\n" +
	                "Project ID: " + diplomaProject.getProjectId() + "\n" +
	                "Topic: " + diplomaProject.getTopic() + "\n" +
	                "Participants: " + getParticipantsDetails(diplomaProject.getParticipants()) + "\n" +
	                "Published Papers: " + getPublishedPapersDetails(diplomaProject.getPublishedPapers());

	        Message defending = new Message(t, t,
	                "Graduate Student: " + this.getUserId() + " wants you to rate his diploma work.\n" + projectDetails);
	        t.recieveMessage(defending);
	    } else {
	        System.out.println("Error: Diploma project not found for defense.");
	    }
	}  
	private String getParticipantsDetails(Vector<ResearcherDecorator> participants) {
	    StringBuilder details = new StringBuilder();
	    for (ResearcherDecorator participant : participants) {
	        details.append(participant.getName()).append(", ");
	    }
	    if (details.length() > 2) {
	        details.setLength(details.length() - 2);
	    }
	    return details.toString();
	}
	private String getPublishedPapersDetails(Vector<ResearchPaper> papers) {
	    StringBuilder details = new StringBuilder();
	    for (ResearchPaper paper : papers) {
	        details.append("Paper ID: ").append(paper.getPaperId()).append(", Title: ").append(paper.getTitle()).append("\n");
	    }
	    return details.toString();
	}
	
	public void showCommands() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            //DB
            try {
                Database.getInstance().write();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //DB

            System.out.println("Student Commands:");

            System.out.println("1. View Journals");
            System.out.println("2. Subscribe to Journal");
            System.out.println("3. Unsubscribe from Journal");
            System.out.println("4. View Messages");
            System.out.println("5. Clear Messages");
            System.out.println("6. View News");
            System.out.println("7. Change Language");
            System.out.println("8. Change Password");
            System.out.println("9. View Marks");
            System.out.println("10. View GPA");
            System.out.println("11. View Transcript");
            System.out.println("12. Rate Teacher");
            System.out.println("13. Register for Course");
            System.out.println("14. Leave Organization");
            System.out.println("15. Join Organization");
            System.out.println("16. View Organizations");
            System.out.println("17. View Teachers");
            System.out.println("18. View Schedule");
            System.out.println("19. View Courses");
            System.out.println("20. Exit");
            System.out.println("21. Researcher menu");
            System.out.println("22. Create Diploma Project");
            System.out.println("23. Defend Diploma Project");

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
                        viewMarks();
                        break;
                    case 10:
                        viewGpa();
                        break;
                    case 11:
                        viewTranscript();
                        break;
                    case 12:
                        System.out.println("Enter the ID of the teacher you want to rate:");
                        try {
                            int teacherId = Integer.parseInt(reader.readLine());
                            Teacher teacherToRate = Database.getInstance().findTeacherById(teacherId);
                            if (teacherToRate != null) {
                                System.out.println("Enter your rating (1-5):");
                                int rating = Integer.parseInt(reader.readLine());
                                rateTeacher(teacherToRate, rating);
                                System.out.println("Teacher rated successfully!");
                            } else {
                                System.out.println("Teacher with ID " +teacherId + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 13:
                        System.out.println("Enter the ID of the course you want to register for:");
                        try {
                            int courseId = Integer.parseInt(reader.readLine());
                            Course courseToRegister = Database.getInstance().findCourseById(courseId);
                            if (courseToRegister != null) {
                                registerForCourse(courseToRegister);
                            } else {
                                System.out.println("Course with ID " +courseId + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 14:
                        System.out.println("Enter the ID of the organization you want to leave:");
                        try {
                            int orgId = Integer.parseInt(reader.readLine());
                            Organization organizationToLeave = Database.getInstance().findOrganizationById(orgId);
                            if (organizationToLeave!= null) {
                                leaveOrganization(organizationToLeave);
                            } else {
                                System.out.println("Organization with ID " + orgId +" not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 15:
                        System.out.println("Enter the ID of the organization you want to join:");
                        try {
                            int orgId = Integer.parseInt(reader.readLine());
                            Organization organizationToJoin = Database.getInstance().findOrganizationById(orgId);
                            if (organizationToJoin!= null) {
                                joinOrganization(organizationToJoin);
                            } else {
                                System.out.println("Organization with ID " + orgId + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 16:
                        viewOrganizations();
                        break;
                    case 17:
                        viewTeachers();
                        break;
                    case 18:
                        viewSchedule();
                        break;
                    case 19:
                        viewCourses();
                        break;
                    case 20:
                        System.out.println("Exiting Student commands.");
                        return;
                    case 21:
                    	ResearcherDecorator Researcher = Database.getInstance().findResearcherById(this.getUserId());
                    	if(Researcher != null){
                    		Researcher.showResearcherCommands();
                    	}
                    	else {
                    		System.out.println("User have no permission to do that. Firstly became a Researcher");
                    	}
                    case 22:
                        createDiplomaProject();
                        break;
                    case 23:
                        System.out.println("Enter the ID of the teacher you want to defend your diploma project:");
                        try {
                            int teacherId = Integer.parseInt(reader.readLine());
                            Teacher teacherToDefend = Database.getInstance().findTeacherById(teacherId);
                            if (teacherToDefend != null) {
                                defendDiplomaProject(teacherToDefend);
                            } else {
                                System.out.println("Teacher with ID " + teacherId + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
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
