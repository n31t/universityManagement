package database;


import users.*;
import utility.*;
import researchWorks.*;

import java.io.*;
import java.util.Vector;

/**
 * The Database class manages the data for the application and provides methods to access and manipulate the data.
 */
public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	public static Database INSTANCE;
	// Static block to initialize the INSTANCE variable based on file existence
	static {
		if(new File("/Users/adilovamir/eclipse-workspace/appli/database.txt").exists()) {
			try {
				INSTANCE = read();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else INSTANCE = new Database();
	}
	// Definition of various vectors to store different types of data
    private Vector<User> users;
    private Vector<Employee> employees;
    private Vector<Student> students;
    private Vector<GraduateStudent> graduateStudents;
    private Vector<Admin> admins;
    private Vector<Manager> managers;
    private Vector<Teacher> teachers;
    private Vector<Dean> deans;
    private Vector<TechSupportSpecialist> techSupportSpecialists;
    private Vector<Course> courses;
    private Vector <CourseForRegistration>coursesForRegistration; 
    private Vector<News> news;
    private Vector<Organization> organizations;
    private Vector<ResearcherDecorator> researchers;
    private Vector<Complaint> complaints;
    private Vector<ResearchPaper> researchPapers;
    private Vector<ResearchProject> researchProjects;
    private Vector<Journal> journals;
    private Vector<Lesson> lessons;
    private Vector<Order> orders;
    private Vector<String> logs;
    private Vector<Request> deanRequests;
    private Vector<Request> managerRequests;
    
    // ... (similar definitions for other vectors)

    /**
     * Private constructor to initialize the vectors when an instance of the Database class is created.
     */
    private Database() {
        users = new Vector<User>();
        employees = new Vector<Employee>();
        students = new Vector<Student>();
        graduateStudents = new Vector<GraduateStudent>();
        admins = new Vector<Admin>();
        managers = new Vector<Manager>();
        teachers = new Vector<Teacher>();
        deans = new Vector<Dean>();
        techSupportSpecialists = new Vector<TechSupportSpecialist>();
        courses = new Vector<Course>();
        news = new Vector<News>();
        coursesForRegistration = new Vector<CourseForRegistration>();
        organizations = new Vector<Organization>();
        researchers = new Vector<ResearcherDecorator>();
        complaints = new Vector<Complaint>();
        researchPapers = new Vector<ResearchPaper>();
        researchProjects = new Vector<ResearchProject>();
        journals = new Vector<Journal>();
        lessons = new Vector<Lesson>();
        orders = new Vector<Order>();
        logs = new Vector<String>();
        deanRequests = new Vector<Request>();
        managerRequests = new Vector<Request>();
    }
    
    /**
     * Method to get the instance of the Database class (following the Singleton pattern).
     *
     * @return The Database instance.
     */
    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }
    
    public Vector<User> getUsers() {
        return this.users;
    }

    public void setUsers(Vector<User> users) {
        this.users = users;
    }
    
    public Vector<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Vector<Employee> employees) {
        this.employees = employees;
    }

    public Vector<Student> getStudents() {
        return this.students;
    }

    public void setStudents(Vector<Student> students) {
        this.students = students;
    }

    public Vector<GraduateStudent> getGraduateStudents() {
        return this.graduateStudents;
    }

    public void setGraduateStudents(Vector<GraduateStudent> graduateStudents) {
        this.graduateStudents = graduateStudents;
    }

    public Vector<Admin> getAdmins() {
        return this.admins;
    }

    public void setAdmins(Vector<Admin> admins) {
        this.admins = admins;
    }

    public Vector<Manager> getManagers() {
        return this.managers;
    }

    public void setManagers(Vector<Manager> managers) {
        this.managers = managers;
    }

    public Vector<Teacher> getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Vector<Teacher> teachers) {
        this.teachers = teachers;
    }

    public Vector<Dean> getDeans() {
        return this.deans;
    }

    public void setDeans(Vector<Dean> deans) {
        this.deans = deans;
    }

    public Vector<TechSupportSpecialist> getTechSupportSpecialists() {
        return this.techSupportSpecialists;
    }

    public void setTechSupportSpecialists(Vector<TechSupportSpecialist> techSupportSpecialists) {
        this.techSupportSpecialists = techSupportSpecialists;
    }

    public Vector<Course> getCourses() {
        return this.courses;
    }
    public void setCourses(Vector<Course> courses) {
        this.courses = courses;
    }

    public Vector<CourseForRegistration> getCoursesForRegistration() {
        return this.coursesForRegistration;
    }

    public void setCoursesForRegistration(Vector<CourseForRegistration> coursesForRegistration) {
        this.coursesForRegistration = coursesForRegistration;
    }

    public Vector<News> getNews() {
        return this.news;
    }

    public void setNews(Vector<News> news) {
        this.news = news;
    }

    public Vector<Organization> getOrganizations() {
        return this.organizations;
    }

    public void setOrganizations(Vector<Organization> organizations) {
        this.organizations = organizations;
    }

    public Vector<ResearcherDecorator> getResearchers() {
        return this.researchers;
    }

    public void setResearchers(Vector<ResearcherDecorator> researchers) {
        this.researchers = researchers;
    }

    public Vector<Complaint> getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Vector<Complaint> complaints) {
        this.complaints = complaints;
    }
 
    public Vector<ResearchPaper> getResearchPapers() {
        return this.researchPapers;
    }

    public void setResearchPapers(Vector<ResearchPaper> researchPapers) {
        this.researchPapers = researchPapers;
    }
    
    public Vector<ResearchProject> getResearchProjects() {
        return this.researchProjects;
    }

    public void setResearchProjects(Vector<ResearchProject> researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Vector<Journal> getJournals() {
        return this.journals;
    }

    public void setJournals(Vector<Journal> journals) {
        this.journals = journals;
    }

    public Vector<Lesson> getLessons() {
        return this.lessons;
    }

    public void setLessons(Vector<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Vector<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Vector<Order> orders) {
        this.orders = orders;
    }

    public Vector<String> getLogs() {
        return this.logs;
    }

    public void setLogs(Vector<String> logs) {
        this.logs = logs;
    }

    public Vector<Request> getDeanRequests() {
        return this.deanRequests;
    }

    public void setDeanRequests(Vector<Request> deanRequests) {
        this.deanRequests = deanRequests;
    }
    
    public Vector<Request> getManagerRequests() {
        return this.managerRequests;
    }

    public void setManagerRequests(Vector<Request> managerRequests) {
        this.managerRequests = managerRequests;
    }
    
    // Getter and setter methods for various vectors...

    /**
     * Reads the serialized data from a file and returns a Database instance.
     *
     * @return The Database instance read from the file.
     * @throws IOException            If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */                               
    public static Database read() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("/Users/adilovamir/eclipse-workspace/appli/database.txt");
		ObjectInputStream oin = new ObjectInputStream(fis);
		return (Database) oin.readObject();
	}
    
    /**
     * Writes the current Database instance to a file for persistence.
     *
     * @throws IOException If an I/O error occurs.
     */
	public static void write()throws IOException{
		FileOutputStream fos = new FileOutputStream("/Users/adilovamir/eclipse-workspace/appli/database.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(INSTANCE);
		oos.close();
	}
	 /**
     * Checks if a given user is an admin.
     *
     * @param user The user to check.
     * @return True if the user is an admin, false otherwise.
     */
    
    private boolean isAdmin(User user) {
        return user instanceof Admin;
    }
    /**
     * Retrieves a user based on the provided user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     */
    public User getUserById(int userId) {
        Vector<User> users = Database.getInstance().getUsers();
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    /**
     * Adds a log message to the logs vector.
     *
     * @param logMessage The log message to add.
     */
    public void addLog(String logMessage) {
        logs.add(logMessage);
    }
    
    /**
     * Prints logs if the user is an admin.
     *
     * @param user The user attempting to print logs.
     */
    public void printLogs(User user) {
        if (isAdmin(user)) {
            for (String log : logs) {
                System.out.println(log);
            }
        } else {
            System.out.println("Permission denied. Only admins can print logs.");
        }
    }
    
    /**
     * Finds a journal based on the provided journal ID.
     *
     * @param journalId The ID of the journal to find.
     * @return The journal with the specified ID, or null if not found.
     */
    public Journal findJournalById(int journalId) {
        return Database.getInstance().getJournals()
                .stream()
                .filter(journal -> journal.getJournalId() == journalId)
                .findFirst()
                .orElse(null);
    }
    /**
     * Finds a request based on the provided journal ID.
     *
     * @param requestId The ID of the request to find.
     * @return The request with the specified ID, or null if not found.
     */

    public Request findRequestById(int requestId) {
        return Database.getInstance().getDeanRequests()
                .stream()
                .filter(request -> request.getRequestId() == requestId)
                .findFirst()
                .orElse(null);
    }
    /**
     * Finds a research project based on the provided project ID.
     *
     * @param id The ID of the research project to find.
     * @return The research project with the specified ID, or null if not found.
     */
    public ResearchProject findResearchProjectById(int id) {
        return researchProjects.stream()
                .filter(project -> project.getProjectId() == id)
                .findFirst()
                .orElse(null);
    }
    /**
     * Finds a researcher based on the provided researcher ID.
     *
     * @param id The ID of the researcher to find.
     * @return The researcher with the specified ID, or null if not found.
     */
    public ResearcherDecorator findResearcherById(int id) {
        return researchers.stream()
                .filter(res -> res.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    /**
     * Finds a journal based on the provided journal ID.
     *
     * @param journalId The ID of the journal to find.
     * @return The journal with the specified ID, or null if not found.
     */
    public Journal findJournalByAuthorId(int id) {
        return journals.stream()
                .filter(journal -> journal.getAuthor().getUserId() == id)
                .findFirst()
                .orElse(null);
    }
 // ... (similar comments for other find methods)

    /**
     * Finds a student based on the provided user ID.
     *
     * @param id The ID of the student to find.
     * @return The student with the specified ID, or null if not found.
     */
    public Student findStudentById(int id) {
        return students.stream()
                .filter(student -> student.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds a course based on the provided course ID.
     *
     * @param id The ID of the course to find.
     * @return The course with the specified ID, or null if not found.
     */
    public Course findCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds an organization based on the provided organization ID.
     *
     * @param id The ID of the organization to find.
     * @return The organization with the specified ID, or null if not found.
     */
    public Organization findOrganizationById(int id) {
        return organizations.stream()
                .filter(org -> org.getOrganizationId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds a teacher based on the provided user ID.
     *
     * @param id The ID of the teacher to find.
     * @return The teacher with the specified ID, or null if not found.
     */
    public Teacher findTeacherById(int id) {
        return teachers.stream()
                .filter(teacher -> teacher.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    

    /**
     * Finds a graduate student based on the provided user ID.
     *
     * @param id The ID of the graduate student to find.
     * @return The graduate student with the specified ID, or null if not found.
     */
    public GraduateStudent findGraduateStudentById(int id) {
        return graduateStudents.stream()
                .filter(grad -> grad.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds a employee based on the provided user ID.
     *
     * @param id The ID of the employee to find.
     * @return The employee with the specified ID, or null if not found.
     */
    public Employee findEmployeeById(int id) {
        return employees.stream()
                .filter(emp -> emp.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds a dean based on the provided user ID.
     *
     * @param id The ID of the dean to find.
     * @return The dean with the specified ID, or null if not found.
     */
    public Dean findDeanById(int id) {
        return deans.stream()
                .filter(dean -> dean.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds a TechSupportSpecialist based on the provided user ID.
     *
     * @param id The ID of the TechSupportSpecialist to find.
     * @return The TechSupportSpecialist with the specified ID, or null if not found.
     */
    public TechSupportSpecialist findTechSupportSpecialistById(int id) {
        return techSupportSpecialists.stream()
                .filter(tech -> tech.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Finds a manager based on the provided user ID.
     *
     * @param id The ID of the manager to find.
     * @return The manager with the specified ID, or null if not found.
     */
	public Manager findManagerById(int id) {
		// TODO Auto-generated method stub
		return managers.stream()
                .filter(managr -> managr.getUserId() == id)
                .findFirst()
                .orElse(null);
	}
	
	/**
     * Method to get the size of all vectors to obtain unique index.
     *
     * @return unique number.
     */
	public static int nextId() {
	    int totalSize = INSTANCE.users.size() +
	                    INSTANCE.employees.size() +
	                    INSTANCE.students.size() +
	                    INSTANCE.graduateStudents.size() +
	                    INSTANCE.admins.size() +
	                    INSTANCE.managers.size() +
	                    INSTANCE.teachers.size() +
	                    INSTANCE.deans.size() +
	                    INSTANCE.techSupportSpecialists.size() +
	                    INSTANCE.courses.size() +
	                    INSTANCE.coursesForRegistration.size() +
	                    INSTANCE.news.size() +
	                    INSTANCE.organizations.size() +
	                    INSTANCE.researchers.size() +
	                    INSTANCE.complaints.size() +
	                    INSTANCE.researchPapers.size() +
	                    INSTANCE.researchProjects.size() +
	                    INSTANCE.journals.size() +
	                    INSTANCE.lessons.size() +
	                    INSTANCE.orders.size();

	    return totalSize + 1;
	}
	 // End of class definition
}
