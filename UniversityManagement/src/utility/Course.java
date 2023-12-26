package utility;

import java.util.Vector;

import database.Database;
import enums.ElectiveType;
import enums.School;
import users.Student;
import users.Teacher;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an academic course in a university management system.
 * This class contains details about the course, including its instructors, prerequisites,
 * type, associated schools, credit cost, marks, and lesson schedules.
 */
public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	private int courseId;
	private String courseName;
	private Vector<Teacher> instructors;
	private Vector<Course> prereq;
	private ElectiveType elective;
	private Vector<School> schools;
	private int costInCredits;
	private Map <Student,Mark> marks;
	private Vector<Lesson> lessons;
	
	// Initializer block for course attributes
	{
		courseId = Database.nextId();
		lessons = new Vector<Lesson>();
		instructors = new Vector<Teacher>();
		prereq = new Vector<Course>();
		marks = new HashMap<Student, Mark>();
	}
	
	/**
     * Default constructor for creating a Course instance without predefined properties.
     */
	public Course() {
		
	}
	
	/**
     * Constructs a Course with specified attributes.
     *
     * @param courseName The name of the course.
     * @param elective The elective type of the course.
     * @param schools The vector of schools associated with the course.
     * @param costInCredits The credit cost of the course.
     * @param lessons The vector of lessons scheduled for the course.
     */
    public Course(String courseName, ElectiveType elective, Vector<School> schools, int costInCredits,
			Vector<Lesson> lessons) {
		super();
		this.courseName = courseName;
		this.elective = elective;
		this.schools = schools;
		this.costInCredits = costInCredits;
		this.lessons = lessons;
	}
    
    /**
     * Retrieves the course ID.
     *
     * @return The course ID.
     */
	public int getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    
    /**
     * Retrieves the course name.
     *
     * @return The course name.
     */
    public String getCourseName() {
        return this.courseName;
    }
    
    /**
     * Sets the course name.
     *
     * @param courseName The new course name.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    /**
     * Retrieves the instructors associated with the course.
     *
     * @return The vector of instructors.
     */
    
    public Vector<Teacher> getInstructors() {
        return this.instructors;
    }
    /**
     * Sets the instructors for the course.
     *
     * @param instructors The vector of instructors.
     */
    
    public void setInstructors(Vector<Teacher> instructors) {
        this.instructors = instructors;
    }
    
    /**
     * Adds a teacher as an instructor for the course.
     *
     * @param teacher The teacher to be added as an instructor.
     */
    public void addInstructors(Teacher teacher) {
        this.instructors.add(teacher);
    }
    /**
     * Removes a teacher from the list of instructors for the course.
     *
     * @param teacher The teacher to be removed.
     */
    public void removeInstructor(Teacher teacher) {
        this.instructors.remove(teacher);
    }
    /**
     * Retrieves the prerequisites for the course.
     *
     * @return The vector of prerequisites.
     */
    public Vector<Course> getPrereq() {
        return this.prereq;
    }

    	
    /**
     * Sets the prerequisites for the course.
     *
     * @param prereq The vector of prerequisites.
     */
    public void setPrereq(Vector<Course> prereq) {
        this.prereq = prereq;
    }
    
    /**
     * Adds a prerequisite course for the current course.
     *
     * @param prereq The prerequisite course to be added.
     */
    public void addPrereq(Course prereq) {
        this.prereq.add(prereq);
    }
    
    /**
     * Removes a prerequisite course from the current course.
     *
     * @param prereq The prerequisite course to be removed.
     */
    public void removePrereq(Course prereq) {
        this.prereq.remove(prereq);
    }
    
    /**
     * Retrieves the elective type of the course.
     *
     * @return The elective type.
     */
    public ElectiveType getElective() {
        return this.elective;
    }
   
    /**
     * Sets the elective type for the course.
     *
     * @param elective The new elective type.
     */
    public void setElective(ElectiveType elective) {
        this.elective = elective;
    }
    
    /**
     * Retrieves the vector of schools associated with the course.
     *
     * @return The vector of schools.
     */
    public Vector<School> getSchools() {
        return this.schools;
    }
    
    /**
     * Sets the vector of schools associated with the course.
     *
     * @param schools The new vector of schools.
     */
    public void setSchools(Vector<School> schools) {
        this.schools = schools;
    }
    
    /**
     * Retrieves the credit cost of the course.
     *
     * @return The credit cost.
     */
    public int getCostInCredits() {
        return this.costInCredits;
    }
    
    /**
     * Sets the credit cost for the course.
     *
     * @param costInCredits The new credit cost.
     */
    public void setCostInCredits(int costInCredits) {
        this.costInCredits = costInCredits;
    }
    
    /**
     * Retrieves the map of student marks for the course.
     *
     * @return The map of student marks.
     */
    public Map<Student, Mark> getMarks() {
        return this.marks;
    }
    /**
     * Sets the map of student marks for the course.
     *
     * @param marks The new map of student marks.
     */
   
    /**
     * Retrieves the vector of lessons scheduled for the course.
     *
     * @return The vector of lessons.
     */
    public Vector<Lesson> getLessons() {
        return this.lessons;
    }

    /**
     * Sets the vector of lessons for the course.
     *
     * @param lessons The new vector of lessons.
     */
    public void setLessons(Vector<Lesson> lessons) {
        this.lessons = lessons;
    }

    /**
     * Adds a lesson to the vector of lessons for the course.
     *
     * @param lesson The lesson to be added.
     */
    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    /**
     * Retrieves the mark for a specific student.
     *
     * @param student The student for whom to retrieve the mark.
     * @return The mark for the student.
     */
    public Mark getMark(Student student) {
        return this.marks.get(student);
    }
}
