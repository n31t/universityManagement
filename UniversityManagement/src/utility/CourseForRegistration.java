package utility;

import java.io.Serializable;

import database.Database;
import users.Student;


/**
 * Represents a registration record for a student enrolling in a course.
 * This class encapsulates the association between a student and a course,
 * identifying each registration with a unique ID.
 */
public class CourseForRegistration implements Serializable {
	private static final long serialVersionUID = 1L;
	private int registrationId;
	private Student student;
	private Course course;
	
	// Initializer block for registrationId
	{
		registrationId = Database.nextId();;
	}
	
	/**
     * Default constructor for creating a CourseForRegistration instance without predefined properties.
     */
	public CourseForRegistration() {
		super();
	}
	
    /**
     * Constructs a CourseForRegistration with a specified student and course.
     *
     * @param student The student enrolling in the course.
     * @param course The course in which the student is enrolling.
     */
	public CourseForRegistration(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	 /**
     * Retrieves the registration ID.
     *
     * @return The registration ID.
     */
    public int getRegistrationId() {
        return registrationId;
    }

    /**
     * Retrieves the student associated with this registration.
     *
     * @return The student enrolled in the course.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Sets the student for this course registration.
     *
     * @param student The student to enroll in the course.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * Retrieves the course associated with this registration.
     *
     * @return The course in which the student is enrolled.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the course for this registration.
     *
     * @param course The course to be associated with this registration.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    // End of class definition
}
