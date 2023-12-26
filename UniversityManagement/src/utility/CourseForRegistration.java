package utility;

import java.io.Serializable;

import database.Database;
import users.Student;

public class CourseForRegistration implements Serializable {
	private static final long serialVersionUID = 1L;
	private int registrationId;
	private Student student;
	private Course course;
	
	{
		registrationId = Database.nextId();;
	}
	public CourseForRegistration() {
		super();
	}
	public CourseForRegistration(Student student, Course course) {
		super();
		this.student = student;
		this.course = course;
	}
	public int getRegistrationId() {
		return registrationId;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
