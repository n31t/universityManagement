package database;


import users.*;
import utility.*;
import researchWorks.*;

import java.io.*;
import java.util.Vector;


public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
	public static Database INSTANCE;
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
    // Add, Delete
//    public void addUser(User user) {
//        users.add(user);
//    }
//
//    public void deleteUser(User user) {
//        users.remove(user);
//    }
//    
//    public void addEmployee(Employee employee) {
//        employees.add(employee);
//    }
//
//    public void deleteEmployee(Employee employee) {
//        employees.remove(employee);
//    }
//    
//    public void addStudent(Student student) {
//        students.add(student);
//    }
//
//    public void deleteStudent(Student student) {
//        students.remove(student);
//    }
//    
//    public void addGraduateStudent(GraduateStudent graduateStudent) {
//        graduateStudents.add(graduateStudent);
//    }
//
//    public void deleteGraduateStudent(GraduateStudent graduateStudent) {
//        graduateStudents.remove(graduateStudent);
//    }
//    
//    // Methods for 'admins' vector
//    public void addAdmin(Admin admin) {
//        admins.add(admin);
//    }
//
//    public void deleteAdmin(Admin admin) {
//        admins.remove(admin);
//    }
//
//    // Methods for 'managers' vector
//    public void addManager(Manager manager) {
//        managers.add(manager);
//    }
//
//    public void deleteManager(Manager manager) {
//        managers.remove(manager);
//    }
//
//    // Methods for 'teachers' vector
//    public void addTeacher(Teacher teacher) {
//        teachers.add(teacher);
//    }
//
//    public void deleteTeacher(Teacher teacher) {
//        teachers.remove(teacher);
//    }
//
//    // Methods for 'deans' vector
//    public void addDean(Dean dean) {
//        deans.add(dean);
//    }
//
//    public void deleteDean(Dean dean) {
//        deans.remove(dean);
//    }
//
//    // Methods for 'techSupportSpecialists' vector
//    public void addTechSupportSpecialist(TechSupportSpecialist specialist) {
//        techSupportSpecialists.add(specialist);
//    }
//
//    public void deleteTechSupportSpecialist(TechSupportSpecialist specialist) {
//        techSupportSpecialists.remove(specialist);
//    }
//
//    // Methods for 'courses' vector
//    public void addCourse(Course course) {
//        courses.add(course);
//    }
//
//    public void deleteCourse(Course course) {
//        courses.remove(course);
//    }
//
//    // Methods for 'news' vector
//    public void addNews(News newsItem) {
//        news.add(newsItem);
//    }
//
//    public void deleteNews(News newsItem) {
//        news.remove(newsItem);
//    }
//
//    // Continue the pattern for other vectors...
//
//    // Methods for 'managerRequests' vector
//    public void addManagerRequest(Request request) {
//        managerRequests.add(request);
//    }
//
//    public void deleteManagerRequest(Request request) {
//        managerRequests.remove(request);
//    }
//
//    // Methods for 'deanRequests' vector
//    public void addDeanRequest(Request request) {
//        deanRequests.add(request);
//    }
//
//    public void deleteDeanRequest(Request request) {
//        deanRequests.remove(request);
//    }
//    

    //                          Operations                                  
    public static Database read() throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream("/Users/adilovamir/eclipse-workspace/appli/database.txt");
		ObjectInputStream oin = new ObjectInputStream(fis);
		return (Database) oin.readObject();
	}
	public static void write()throws IOException{
		FileOutputStream fos = new FileOutputStream("/Users/adilovamir/eclipse-workspace/appli/database.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(INSTANCE);
		oos.close();
	}
    
    
    private boolean isAdmin(User user) {
        return user instanceof Admin;
    }
    public User getUserById(int userId) {
        Vector<User> users = Database.getInstance().getUsers();
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
    public void addLog(String logMessage) {
        logs.add(logMessage);
    }
    public void printLogs(User user) {
        if (isAdmin(user)) {
            for (String log : logs) {
                System.out.println(log);
            }
        } else {
            System.out.println("Permission denied. Only admins can print logs.");
        }
    }
    public Journal findJournalById(int journalId) {
        return Database.getInstance().getJournals()
                .stream()
                .filter(journal -> journal.getJournalId() == journalId)
                .findFirst()
                .orElse(null);
    }

    public Request findRequestById(int requestId) {
        return Database.getInstance().getDeanRequests()
                .stream()
                .filter(request -> request.getRequestId() == requestId)
                .findFirst()
                .orElse(null);
    }

    public ResearchProject findResearchProjectById(int id) {
        return researchProjects.stream()
                .filter(project -> project.getProjectId() == id)
                .findFirst()
                .orElse(null);
    }

    public ResearcherDecorator findResearcherById(int id) {
        return researchers.stream()
                .filter(res -> res.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public Journal findJournalByAuthorId(int id) {
        return journals.stream()
                .filter(journal -> journal.getAuthor().getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public Student findStudentById(int id) {
        return students.stream()
                .filter(student -> student.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public Course findCourseById(int id) {
        return courses.stream()
                .filter(course -> course.getCourseId() == id)
                .findFirst()
                .orElse(null);
    }

    public Organization findOrganizationById(int id) {
        return organizations.stream()
                .filter(org -> org.getOrganizationId() == id)
                .findFirst()
                .orElse(null);
    }

    public Teacher findTeacherById(int id) {
        return teachers.stream()
                .filter(teacher -> teacher.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public GraduateStudent findGraduateStudentById(int id) {
        return graduateStudents.stream()
                .filter(grad -> grad.getUserId() == id)
                .findFirst()
                .orElse(null);
    }

    public Employee findEmployeeById(int id) {
        return employees.stream()
                .filter(emp -> emp.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    public Dean findDeanById(int id) {
        return deans.stream()
                .filter(dean -> dean.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
    public TechSupportSpecialist findTechSupportSpecialistById(int id) {
        return techSupportSpecialists.stream()
                .filter(tech -> tech.getUserId() == id)
                .findFirst()
                .orElse(null);
    }
	public Manager findManagerById(int id) {
		// TODO Auto-generated method stub
		return managers.stream()
                .filter(managr -> managr.getUserId() == id)
                .findFirst()
                .orElse(null);
	}
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
}
