package utility;

import java.util.Vector;

import enums.LessonType;
import enums.WeekDays;
import users.Teacher;
public class Lesson {
	private int lessonId;
	private LessonType lessonType;
	private Vector <WeekDays> days;
	private Teacher teacher;

	
	public Lesson() {
		
	}

	public Lesson(LessonType lessonType, Vector<WeekDays> days, Teacher teacher) {
		super();
		this.lessonType = lessonType;
		this.days = days;
		this.teacher = teacher;
	}
	public int getLeesonId() {
		return this.lessonId;
	}
	public void setLessonId(int lessonId) {
		this.lessonId=lessonId;
	}
	public LessonType getLessonType() {
		return this.lessonType;
	}
	public void setLessonType(LessonType lessonType) {
		this.lessonType=lessonType;
	}
	public Teacher getTeacher() {
		return this.teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher=teacher;
	}
	
	public Vector<WeekDays> getDays() {
	    return this.days;
	}
	public void viewDays() {
		for(WeekDays day : days) {
			System.out.println(lessonType + " " + day);
		}
		System.out.println();
	}
	
	public void setDays(Vector<WeekDays> days) {
	    this.days = days;
	}
	
	
	}