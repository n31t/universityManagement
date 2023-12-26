package users;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import database.Database;
import enums.DegreesType;
import enums.School;
import exceptions.InvalidStudyYearException;
import exceptions.InvalidSupervisorException;

public class Admin extends Employee {

	private static final long serialVersionUID = 1L;
    
	public Admin() {
    	super();
    }
	
	public Admin(String name , String password) {
    	super(name, password);
    }
	public void viewLogFiles(){
		
	}
    public void updateUser() {
    	
    }
    public void addUser() {
    	
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
    }
  
    public void addTeacher() {
    	
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
    //Need fix
    public void removeTeacher() {
    	
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
    public void showCommands() {
    	
    }
    
}
