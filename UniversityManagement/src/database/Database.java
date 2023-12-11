package database;

import users.*;
import utility.*;
import researchWorks.*;
import java.io.Serializable;
import java.util.Vector;


public class Database implements Serializable{

	private static final long serialVersionUID = 1L;
    private static Database instance = new Database();
    private Vector users;
    private Vector employees;
    private Vector students;
    private Vector graduateStudents;
    private Vector admins;
    private Vector managers;
    private Vector teachers;
    private Vector deans;
    private Vector techSupportSpecialists;
    private Vector courses;
    private Vector coursesForRegistration;
    private Vector news;
    private Vector organizations;
    private Vector researchers;
    private Vector messages;
    private Vector marks;
    private Vector complaints;
    private Vector researchPapers;
    private Vector researchProjects;
    private Vector journals;
    private Vector lessons;
    private Vector orders;
    private Vector logs;
    private Vector deanRequests;
    private Vector managerRequests;

    public static Database getInstance() {
        return instance;
    }
    
    public Vector getUsers() {
        return this.users;
    }

    public void setUsers(Vector users) {
        this.users = users;
    }
    
    public Vector getEmployees() {
        return this.employees;
    }

    public void setEmployees(Vector employees) {
        this.employees = employees;
    }

    public Vector getStudents() {
        return this.students;
    }

    public void setStudents(Vector students) {
        this.students = students;
    }

    public Vector getGraduateStudents() {
        return this.graduateStudents;
    }

    public void setGraduateStudents(Vector graduateStudents) {
        this.graduateStudents = graduateStudents;
    }

    public Vector getAdmins() {
        return this.admins;
    }

    public void setAdmins(Vector admins) {
        this.admins = admins;
    }

    public Vector getManagers() {
        return this.managers;
    }

    public void setManagers(Vector managers) {
        this.managers = managers;
    }

    public Vector getTeachers() {
        return this.teachers;
    }

    public void setTeachers(Vector teachers) {
        this.teachers = teachers;
    }

    public Vector getDeans() {
        return this.deans;
    }

    public void setDeans(Vector deans) {
        this.deans = deans;
    }

    public Vector getTechSupportSpecialists() {
        return this.techSupportSpecialists;
    }

    public void setTechSupportSpecialists(Vector techSupportSpecialists) {
        this.techSupportSpecialists = techSupportSpecialists;
    }

    public Vector getCourses() {
        return this.courses;
    }
    public void setCourses(Vector courses) {
        this.courses = courses;
    }

    public Vector getCoursesForRegistration() {
        return this.coursesForRegistration;
    }

    public void setCoursesForRegistration(Vector coursesForRegistration) {
        this.coursesForRegistration = coursesForRegistration;
    }

    public Vector getNews() {
        return this.news;
    }

    public void setNews(Vector news) {
        this.news = news;
    }

    public Vector getOrganizations() {
        return this.organizations;
    }

    public void setOrganizations(Vector organizations) {
        this.organizations = organizations;
    }

    public Vector getResearchers() {
        return this.researchers;
    }

    public void setResearchers(Vector researchers) {
        this.researchers = researchers;
    }

    public Vector getMessages() {
        return this.messages;
    }

    public void setMessages(Vector messages) {
        this.messages = messages;
    }

    public Vector getMarks() {
        return this.marks;
    }

    public void setMarks(Vector marks) {
        this.marks = marks;
    }

    public Vector getComplaints() {
        return this.complaints;
    }

    public void setComplaints(Vector complaints) {
        this.complaints = complaints;
    }
 
    public Vector getResearchPapers() {
        return this.researchPapers;
    }

    public void setResearchPapers(Vector researchPapers) {
        this.researchPapers = researchPapers;
    }
    
    public Vector getResearchProjects() {
        return this.researchProjects;
    }

    public void setResearchProjects(Vector researchProjects) {
        this.researchProjects = researchProjects;
    }

    public Vector getJournals() {
        return this.journals;
    }

    public void setJournals(Vector journals) {
        this.journals = journals;
    }

    public Vector getLessons() {
        return this.lessons;
    }

    public void setLessons(Vector lessons) {
        this.lessons = lessons;
    }

    public Vector getOrders() {
        return this.orders;
    }

    public void setOrders(Vector orders) {
        this.orders = orders;
    }

    public Vector getLogs() {
        return this.logs;
    }

    public void setLogs(Vector logs) {
        this.logs = logs;
    }

    public Vector getDeanRequests() {
        return this.deanRequests;
    }

    public void setDeanRequests(Vector deanRequests) {
        this.deanRequests = deanRequests;
    }
    
    public Vector getManagerRequests() {
        return this.managerRequests;
    }

    public void setManagerRequests(Vector managerRequests) {
        this.managerRequests = managerRequests;
    }
       

    //                          Operations                                  

    public boolean isAdmin() {
        //TODO
        return false;
    }
    public void addLog() {

    }
    public void printLogs(User u) {
    	
    };
    
}
