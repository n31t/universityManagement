package users;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import database.Database;
import enums.DegreesType;
import enums.ElectiveType;
import enums.ManagerType;
import enums.School;
import enums.TeacherType;
import exceptions.InvalidStudyYearException;
import exceptions.InvalidSupervisorException;
import researchWorks.Journal;
import utility.Course;

public class Admin extends Employee {

	private static final long serialVersionUID = 1L;
    
	public Admin() {
    	super();
    }
	
	public Admin(String name , String password) {
    	super(name, password);
    }
	public void viewLogFiles(){
		Database.getInstance().printLogs(this);
	}
	public void addUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Choose the type of user to add:");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("3. Admin");
            System.out.println("4. Dean");
            System.out.println("5. Tech Support Specialist");
            System.out.println("6. Student");
            System.out.println("7. Graduate Student");
            System.out.println("8. Teacher");

            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    addManager();
                    break;
                case 3:
                    addAdmin();
                    break;
                case 4:
                    addDean();
                    break;
                case 5:
                    addTechSupportSpecialist();
                    break;
                case 6:
                    addStudent();
                    break;
                case 7:
                    addGraduateStudent();
                    break;
                case 8:
                    addTeacher();
                    break;
                default:
                    System.out.println("Invalid choice. User not added.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to add user.");
        }
    }

    public void addStudent()  {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the student's name:");
            String name = reader.readLine();

            System.out.println("Enter the student's password:");
            String password = reader.readLine();

            System.out.println("Enter the student's study year:");
            int studyYear = Integer.parseInt(reader.readLine());
            
            System.out.println("Enter the student's school (BUSINESS_SCHOOL, SCHOOL_OF_IT_AND_ENGINEERING)");
            String schoolStr = reader.readLine();
            School school = School.valueOf(schoolStr.toUpperCase());
            
            System.out.println("Is the student also a researcher? (yes/no):");
            String isResearcher = reader.readLine();

            Student newStudent = new Student(name, password, studyYear, school);
            if (isResearcher.equalsIgnoreCase("yes")) {
            addResearcher(newStudent);
            }
            Database.getInstance().getUsers().add(newStudent);
            Database.getInstance().getStudents().add(newStudent);

            System.out.println("Student added successfully!");
        } catch (NumberFormatException | IOException | InvalidStudyYearException e) {
            System.out.println("Invalid input. Unable to add student.");
        }
    }

    public void addGraduateStudent() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the student's name:");
            String name = reader.readLine();

            System.out.println("Enter the student's password:");
            String password = reader.readLine();

            System.out.println("Enter the student's study year:");
            int studyYear = Integer.parseInt(reader.readLine());

            System.out.println("Enter the student's school (BUSINESS_SCHOOL, SCHOOL_OF_IT_AND_ENGINEERING):");
            String schoolStr = reader.readLine();
            School school = School.valueOf(schoolStr.toUpperCase());
            
            System.out.println("Enter the student's degree type (BACHELORS, MASTERS, DOCTORATE):");
            String degreeTypeStr = reader.readLine();
            DegreesType degreeType = DegreesType.valueOf(degreeTypeStr.toUpperCase());
            
            
            System.out.println("List of eligible researchers as supervisors:");
            Database.getInstance().getResearchers().stream()
                    .filter(researcher -> researcher.calculateHIndex() > 3)
                    .forEach(researcher -> System.out.println("ID: " + researcher.getUserId() +
                            ", Name: " + researcher.getName() +
                            ", H-Index: " + researcher.calculateHIndex()));
            
            System.out.println("Enter the ID of the chosen research supervisor:");
            int supervisorId = Integer.parseInt(reader.readLine());
            ResearcherDecorator researchSupervisor = Database.getInstance().findResearcherById(supervisorId);
            if (researchSupervisor == null || researchSupervisor.calculateHIndex() <= 3) {
                System.out.println("Invalid supervisor selection. Exiting graduate student addition.");
                return;
            }
            
            GraduateStudent newGraduateStudent;
            newGraduateStudent = new GraduateStudent(name, password, studyYear, school, researchSupervisor);

            newGraduateStudent.setDegreeType(degreeType);
            Database.getInstance().getUsers().add(newGraduateStudent);
            Database.getInstance().getStudents().add(newGraduateStudent);
            Database.getInstance().getGraduateStudents().add(newGraduateStudent);
            addResearcher(newGraduateStudent);
            System.out.println("Graduate student added successfully!");
        } catch (NumberFormatException | IOException | InvalidSupervisorException | InvalidStudyYearException e) {
            System.out.println("Invalid input. Unable to add graduate student.");
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
  
    public void addTeacher() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the teacher's name:");
            String name = reader.readLine();
            System.out.println("Enter the teacher's password:");
            String password = reader.readLine();

            System.out.println("Enter the teacher's type:");
            String teacherTypeStr = reader.readLine();
            TeacherType teacherType = TeacherType.valueOf(teacherTypeStr.toUpperCase());

            Teacher newTeacher = new Teacher(teacherType, name, password);
            Database.getInstance().getUsers().add(newTeacher);
            Database.getInstance().getEmployees().add(newTeacher);
            Database.getInstance().getTeachers().add(newTeacher);

            System.out.println("Teacher added successfully!");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to add teacher.");
        }
    }
    public void addEmployee() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the employee's name:");
            String name = reader.readLine();

            System.out.println("Enter the employee's password:");
            String password = reader.readLine();
            
            System.out.println("Is the employee also a researcher? (yes/no):");
            String isResearcher = reader.readLine();

            Employee newEmployee = new Employee(name, password);
            Database.getInstance().getUsers().add(newEmployee);
            Database.getInstance().getEmployees().add(newEmployee);
            if (isResearcher.equalsIgnoreCase("yes")) {
                addResearcher(newEmployee);
                }

            System.out.println("Employee added successfully!");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to add employee.");
        }
    }
    public void addManager() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the manager's name:");
            String name = reader.readLine();

            System.out.println("Enter the manager's password:");
            String password = reader.readLine();

            System.out.println("Enter the manager's type:");
            String managerTypeStr = reader.readLine();
            ManagerType managerType = ManagerType.valueOf(managerTypeStr.toUpperCase());

            Manager newManager = new Manager(name, password, managerType);
            Database.getInstance().getUsers().add(newManager);
            Database.getInstance().getEmployees().add(newManager);
            Database.getInstance().getManagers().add(newManager);

            System.out.println("Manager added successfully!");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to add manager.");
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
    public void addAdmin() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the admin's name:");
            String name = reader.readLine();

            System.out.println("Enter the admin's password:");
            String password = reader.readLine();

            Admin admin = new Admin(name, password);
            Database.getInstance().getUsers().add(admin);
            Database.getInstance().getAdmins().add(admin);
            System.out.println("Admin added successfully!");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to add employee.");
        }
    } 
    public void addDean() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
    	 System.out.println("Enter the dean's name:");
         String name = reader.readLine();

         System.out.println("Enter the dean's password:");
         String password = reader.readLine();
         
         Dean newDean = new Dean(name, password);
         Database.getInstance().getUsers().add(newDean);
         Database.getInstance().getEmployees().add(newDean);
         Database.getInstance().getDeans().add(newDean);
         System.out.println("Dean added successfully!");
        } catch (NumberFormatException |IOException e) {
            System.out.println("Invalid input. Unable to add employee.");
        }
    }
    private void addResearcher(User u) {
    	ResearcherDecorator researcher = new ResearcherDecorator(u);
        Database.getInstance().getResearchers().add(researcher);
    }
    public void addTechSupportSpecialist() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
    	 System.out.println("Enter the techSupportSpecialist's name:");
         String name = reader.readLine();

         System.out.println("Enter the techSupportSpecialist's password:");
         String password = reader.readLine();
         
         TechSupportSpecialist newTechSupportSpecialist = new TechSupportSpecialist(name, password);
         Database.getInstance().getUsers().add(newTechSupportSpecialist);
         Database.getInstance().getEmployees().add(newTechSupportSpecialist);
         Database.getInstance().getTechSupportSpecialists().add(newTechSupportSpecialist);
         System.out.println("TechSupportSpecialist added successfully!");
        } catch (NumberFormatException |IOException e) {
            System.out.println("Invalid input. Unable to add employee.");
        }
    }
    public void removeUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Choose the type of user to remove:");
            System.out.println("1. Employee");
            System.out.println("2. Manager");
            System.out.println("3. Dean");
            System.out.println("4. Tech Support Specialist");
            System.out.println("5. Student");
            System.out.println("6. Graduate Student");
            System.out.println("7. Teacher");

            int choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    removeEmployee();
                    break;
                case 2:
                    removeManager();
                    break;
                case 3:
                    removeDean();
                    break;
                case 4:
                    removeTechSupportSpecialist();
                    break;
                case 5:
                    removeStudent();
                    break;
                case 6:
                    removeGraduateStudent();
                    break;
                case 7:
                    removeTeacher();
                    break;
                default:
                    System.out.println("Invalid choice. User not removed.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to remove user.");
        }
    }
    public void removeStudent() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    try {
	        System.out.println("Enter the ID of the student you want to remove:");
	        int studentIdToRemove = Integer.parseInt(reader.readLine());

	        Student studentToRemove = Database.getInstance().findStudentById(studentIdToRemove);
	        if (studentToRemove != null) {
	            Database.getInstance().getUsers().remove(studentToRemove);

	            Database.getInstance().getStudents().remove(studentToRemove);

	            removeResearcher(studentToRemove);

	            System.out.println("Student removed successfully!");
	        } else {
	            System.out.println("Student with ID " + studentIdToRemove + " not found.");
	        }
	    } catch (NumberFormatException | IOException e) {
	        System.out.println("Invalid input. Please enter a valid integer.");
	    }
    } 
    public void removeGraduateStudent() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the ID of the graduate student you want to remove:");
            int graduateStudentIdToRemove = Integer.parseInt(reader.readLine());

            GraduateStudent graduateStudentToRemove = Database.getInstance().findGraduateStudentById(graduateStudentIdToRemove);
            if (graduateStudentToRemove != null) {
                Database.getInstance().getUsers().remove(graduateStudentToRemove);
                Database.getInstance().getStudents().remove(graduateStudentToRemove);
                Database.getInstance().getGraduateStudents().remove(graduateStudentToRemove);

                removeResearcher(graduateStudentToRemove);

                System.out.println("Graduate student removed successfully!");
            } else {
                System.out.println("Graduate student with ID " + graduateStudentIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    } 
    public void removeTeacher() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the ID of the teacher you want to remove:");
            int teacherIdToRemove = Integer.parseInt(reader.readLine());

            Teacher teacherToRemove = Database.getInstance().findTeacherById(teacherIdToRemove);
            if (teacherToRemove != null) {
                Database.getInstance().getUsers().remove(teacherToRemove);
                Database.getInstance().getEmployees().remove(teacherToRemove);
                Database.getInstance().getTeachers().remove(teacherToRemove);

                System.out.println("Teacher removed successfully!");
            } else {
                System.out.println("Teacher with ID " + teacherIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    public void removeEmployee() {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the ID of the employee you want to remove:");
            int employeeIdToRemove = Integer.parseInt(reader.readLine());

            Employee employeeToRemove = Database.getInstance().findEmployeeById(employeeIdToRemove);
            if (employeeToRemove!= null) {
                Database.getInstance().getUsers().remove(employeeToRemove);
                Database.getInstance().getAdmins().remove(employeeToRemove);

                System.out.println("Employee removed successfully!");
            } else {
                System.out.println("Employee with ID " + employeeIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    } 
    public void removeManager() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the ID of the manager you want to remove:");
            int managerIdToRemove = Integer.parseInt(reader.readLine());

            Manager managerToRemove = Database.getInstance().findManagerById(managerIdToRemove);
            if (managerToRemove != null) {
                Database.getInstance().getUsers().remove(managerToRemove);
                Database.getInstance().getEmployees().remove(managerToRemove);
                Database.getInstance().getManagers().remove(managerToRemove);

                System.out.println("Manager removed successfully!");
            } else {
                System.out.println("Manager with ID " + managerIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    } 
    public void removeDean() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the ID of the dean you want to remove:");
            int deanIdToRemove = Integer.parseInt(reader.readLine());

            Dean deanToRemove = Database.getInstance().findDeanById(deanIdToRemove);
            if (deanToRemove != null) {
                Database.getInstance().getUsers().remove(deanToRemove);
                Database.getInstance().getEmployees().remove(deanToRemove);
                Database.getInstance().getDeans().remove(deanToRemove);

                System.out.println("Dean removed successfully!");
            } else {
                System.out.println("Dean with ID " + deanIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    private void removeResearcher(User u) {
    	ResearcherDecorator researcherDecorator = Database.getInstance().findResearcherById(u.getUserId());
        if (researcherDecorator != null) {
            Database.getInstance().getResearchers().remove(researcherDecorator);
        }
    }
    public void removeTechSupportSpecialist() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the ID of the tech support specialist you want to remove:");
            int techSupportIdToRemove = Integer.parseInt(reader.readLine());

            TechSupportSpecialist techSupportToRemove = Database.getInstance().findTechSupportSpecialistById(techSupportIdToRemove);
            if (techSupportToRemove != null) {
                Database.getInstance().getUsers().remove(techSupportToRemove);
                Database.getInstance().getEmployees().remove(techSupportToRemove);
                Database.getInstance().getTechSupportSpecialists().remove(techSupportToRemove);

                System.out.println("Tech Support Specialist removed successfully!");
            } else {
                System.out.println("Tech Support Specialist with ID " + techSupportIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    public void createCourse() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Enter the course name:");
            String courseName = reader.readLine();

            System.out.println("Enter the elective type (FREE, MAJOR, MINOR)");
            String electiveTypeStr = reader.readLine();
            ElectiveType electiveType = ElectiveType.valueOf(electiveTypeStr.toUpperCase());

            System.out.println("Enter the schools for the course (separated by commas, for example , BUSINESS_SCHOOL,SCHOOL_OF_ENERGY,):");
            String[] schoolStrArray = reader.readLine().split(",");
            Vector<School> schools = new Vector<>();
            for (String schoolStr : schoolStrArray) {
                schools.add(School.valueOf(schoolStr.toUpperCase()));
            }

            System.out.println("Enter the cost in credits for the course:");
            int costInCredits = Integer.parseInt(reader.readLine());

            Course newCourse = new Course(courseName, electiveType, schools, costInCredits, new Vector<>());
            System.out.println("Does this course have prerequisite courses? (yes or no)");
            String yesOrNo= reader.readLine();
            if(yesOrNo.equals("yes")) {
            	 System.out.println("Enter the IDs of the prerequisite courses for the course (separated by commas):");
            	 String[] prereqIds = reader.readLine().split(",");
            	 for (String prereqId : prereqIds) {
                Course prereqCourse = Database.getInstance().findCourseById(Integer.parseInt(prereqId));
                if (prereqCourse != null) {
                    newCourse.addPrereq(prereqCourse);
                } else {
                    System.out.println("Prerequisite course with ID " + prereqId + " not found.");
                }
            }
            }
            Database.getInstance().getCourses().add(newCourse);

            System.out.println("Course created successfully!");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Unable to create the course.");
        }
        catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
    
    public void viewUsers() {
        System.out.println("Viewing Users:");
        Database.getInstance().getUsers().stream()
                .forEach(user -> System.out.println("ID: " + user.getUserId() +
                        ", Name: " + user.getName()));
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
        	
            System.out.println("Admin Commands:");
            
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
            System.out.println("13. Add User");
            System.out.println("14. Remove User");
            System.out.println("15. Create course");
            System.out.println("16. View logs");
            System.out.println("17. View users");

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
                    	addUser();
                    	break;
                    case 14:
                    	removeUser();
                    	break;
                    case 15:
                    	createCourse();
                    	break;
                    case 16:
                    	this.viewLogFiles();
                    	break;
                    case 17:
                    	this.viewUsers();
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
