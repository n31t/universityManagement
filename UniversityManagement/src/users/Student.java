package users;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import utility.Lesson;
import utility.Course;
import utility.Mark;
import utility.Organization;
	
public class Student extends User implements InfoTeachers, CanViewCourses {
	private static final long serialVersionUID = 1L;

	private Vector<Organization> organizations;
	
	private int studyYear;
	
	private Vector<Course> passedCourses;
	
	private Vector<Lesson> schedule;
	
	private int credits;
	
	private HashMap<Course,Mark> courses;
	
		{
			courses = new HashMap<Course,Mark>();
			passedCourses = new Vector<Course>();
			organizations = new Vector<Organization>();
			schedule = new Vector<Lesson>();
			
		}
		public Student() {
			
		}
		
		public boolean addCourse(Course c){
			//check prereq, credits, faculty
	        courses.put(c, new Mark());
	        return true;
		}
		public String toString(){
			return name+ ", id is "+id+", registered courses:  "+(courses.size()==0?"No courses yet ":courses);
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((courses == null) ? 0 : courses.hashCode());
			result = prime * result + id;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Student other = (Student) obj;
			return this.id == other.id;
		}

		@Override
		public void viewCourses() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void viewTeachers() {
			// TODO Auto-generated method stub
			
		}
	}

}
