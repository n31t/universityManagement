package utility;

import java.util.Vector;

import database.Database;
import enums.LessonType;
import enums.WeekDays;
import users.Teacher;

/**
 * Represents a lesson or class session in a university management system.
 * This class encapsulates details of a lesson, including its type, schedule, and the teacher responsible for it.
 */
public class Lesson {
	private int lessonId;
	private LessonType lessonType;
	private Vector <WeekDays> days;
	private Teacher teacher;
	{
		lessonId = Database.nextId();
	}
	  /**
     * Default constructor for creating a Lesson instance without predefined properties.
     */
	public Lesson() {
		
	}
	/**
     * Constructs a Lesson with specified lesson type, days, and teacher.
     *
     * @param lessonType The type of the lesson (e.g., lecture, lab).
     * @param days The days of the week when the lesson is scheduled.
     * @param teacher The teacher responsible for the lesson.
     */
	public Lesson(LessonType lessonType, Vector<WeekDays> days, Teacher teacher) {
		super();
		this.lessonType = lessonType;
		this.days = days;
		this.teacher = teacher;
	}
	
	 /**
     * Retrieves the lesson ID.
     *
     * @return The lesson ID.
     */
	public int getLeesonId() {
		return this.lessonId;
	}
	
	/**
     * Sets the lesson ID.
     *
     * @param lessonId The lesson ID to be set.
     */
	public void setLessonId(int lessonId) {
		this.lessonId=lessonId;
	}
	
	/**
     * Retrieves the type of the lesson.
     *
     * @return The lesson type.
     */
    /**
     * Sets the type of the lesson.
     *
     * @param lessonType The lesson type to be set.
     */
    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }

    /**
     * Retrieves the teacher responsible for the lesson.
     *
     * @return The teacher.
     */
    public Teacher getTeacher() {
        return this.teacher;
    }

    /**
     * Sets the teacher for the lesson.
     *
     * @param teacher The teacher to be set for the lesson.
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Retrieves the days of the week when the lesson is scheduled.
     *
     * @return A vector of days.
     */
    public Vector<WeekDays> getDays() {
        return this.days;
    }
    /**
     * Prints the days of the week when the lesson is scheduled along with the lesson type.
     */
	public void viewDays() {
		for(WeekDays day : days) {
			System.out.println(lessonType + " " + day);
		}
		System.out.println();
	}
	/**
     * Sets the days of the week for the lesson schedule.
     *
     * @param days A vector of days to be set for the lesson.
     */
	public void setDays(Vector<WeekDays> days) {
	    this.days = days;
	}
	
	 // End of class definition
	}