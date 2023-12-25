package users;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;

import database.Database;
import enums.School;
import researchWorks.Journal;
import universityManagement.InvalidStudyYearException;
import utility.Lesson;
import utility.Course;
import utility.CourseForRegistration;
import utility.Mark;
import utility.Message;
import utility.Organization;
import utility.Request;
	
public class Student extends User implements InfoTeachers, CanViewCourses {
	private static final long serialVersionUID = 1L;

	private Vector<Organization> organizations;
	
	private int studyYear;
	
	private Vector<Course> passedCourses;
	
	private Vector<Integer> enrolledCourseId;
	
	
	private School school;
	private int credits;
	
		{
			passedCourses = new Vector<Course>();
			organizations = new Vector<Organization>();
			enrolledCourseId = new Vector<Integer>();
			
		}
		public Student() {
			
		}
		public Student(String name , String password) {
	    	super(name, password);
	    }
		public Student(String name , String password, int studyYear) throws InvalidStudyYearException {
	    	super(name, password);
	    	this.studyYear = studyYear;
	    	this.school = School.SCHOOL_OF_IT_AND_ENGINEERING;
	    	switch(studyYear) {
	    	case 1:
	    		credits = 30;
	    		break;
	    	case 2:
	    		credits = 32;
	    		break;
	    	case 3:
	    		credits = 35;
	    		break;
	    	case 4:
	    		credits = 36;
	    		break;
	    	default:
	    		throw new InvalidStudyYearException("Invalid study year: " + studyYear);
	    	}
	    }
		public Student(String name , String password, int studyYear, School school) throws InvalidStudyYearException {
	    	super(name, password);
	    	this.studyYear = studyYear;
	    	this.school = school;
	    	switch(studyYear) {
	    	case 1:
	    		credits = 30;
	    		break;
	    	case 2:
	    		credits = 32;
	    		break;
	    	case 3:
	    		credits = 35;
	    		break;
	    	case 4:
	    		credits = 36;
	    		break;
	    	default:
	    		throw new InvalidStudyYearException("Invalid study year: " + studyYear);
	    	}
	    }
		
		
		
		public Vector<Integer> getEnrolledCourseId() {
			return enrolledCourseId;
		}
		public void setEnrolledCourseId(Vector<Integer> enrolledCourseId) {
			this.enrolledCourseId = enrolledCourseId;
		}
		public School getSchool() {
			return school;
		}
		public void setSchool(School school) {
			this.school = school;
		}
		public Vector<Organization> getOrganizations() {
			return organizations;
		}
		public void setOrganizations(Vector<Organization> organizations) {
			this.organizations = organizations;
		}
		public int getStudyYear() {
			return studyYear;
		}
		public void setStudyYear(int studyYear) {
			this.studyYear = studyYear;
		}
		public Vector<Course> getPassedCourses() {
			return passedCourses;
		}
		public void setPassedCourses(Vector<Course> passedCourses) {
			this.passedCourses = passedCourses;
		}
		public int getCredits() {
			return credits;
		}
		public void setCredits(int credits) {
			this.credits = credits;
		}
		
		public void registerForCourse(Course course) {
			if (passedCourses.contains(course)) {
		        System.out.println("You have already passed the course: " + course.getCourseName());
		        return;
		    }
			
	        boolean prerequisitesMet = true;
	        for (Course prereq : course.getPrereq()) {
	            boolean hasPrereq = false;
	            for (Course passedCourse : passedCourses) {
	                if (prereq.getCourseName().equals(passedCourse.getCourseName())) {
	                    hasPrereq = true;
	                    break;
	                }
	            }
	            if (!hasPrereq) {
	                prerequisitesMet = false;
	                break;
	            }
	        }

	        boolean isSchoolMatched = course.getSchools().contains(this.getSchool());

	        boolean hasEnoughCredits = (this.getCredits() - course.getCostInCredits()) >= 0;

	        if (prerequisitesMet && isSchoolMatched && hasEnoughCredits) {
	            this.setCredits(this.getCredits() - course.getCostInCredits());

	            CourseForRegistration registration = new CourseForRegistration(this, course);
	            Database.getInstance().getCoursesForRegistration().add(registration);

	            System.out.println("Registered for course: " + course.getCourseName());
	        } else {
	            if (!prerequisitesMet) {
	                System.out.println("Prerequisites not met for course: " + course.getCourseName());
	            }
	            if (!isSchoolMatched) {
	                System.out.println("Course is not available for your school: " + course.getCourseName());
	            }
	            if (!hasEnoughCredits) {
	                System.out.println("Not enough credits to register for course: " + course.getCourseName());
	            }
	        }
	    }
		public void rateTeacher(Teacher t, int rate) {
			if(rate < 4) {
				for(Dean dean : Database.getInstance().getDeans()){
					Message lowrate = new Message(dean,dean, 
			        		"Student: " + this.getUserId() + " put low rating to " + t.getUserId()+" " +t.getName());
			        dean.recieveMessage(lowrate);
				}
			}
		}
		//Need fix
		public void viewTranscript() {
		    if (passedCourses.isEmpty()) {
		        System.out.println("No passed courses to display in the transcript.");
		        return;
		    }

		    System.out.println("Transcript for " + getName() + ":");

		    for (Course passedCourse : passedCourses) {
		        Mark mark = passedCourse.getMarks().get(this);

		        if (mark != null) {
		            double courseGpa = calculateCourseGpa(mark.getFinal() + mark.getFirstAttestation() + mark.getSecondAttestation());

		            System.out.println("Course ID: " + passedCourse.getCourseId());
		            System.out.println("Course Name: " + passedCourse.getCourseName());
		            System.out.println("GPA: " + courseGpa);
		            System.out.println("---------------");
		        } else {
		            System.out.println("Marks not available for passed course: " + passedCourse.getCourseName());
		        }
		    }
		}


		public void viewGpa() {
		    if (enrolledCourseId.isEmpty() && passedCourses.isEmpty()) {
		        System.out.println("No enrolled or passed courses to calculate GPA.");
		        return;
		    }

		    double totalCredits = 0;
		    double totalGradePoints = 0;

		    //Calculate GPA for enrolled courses
		    for (Integer courseId : enrolledCourseId) {
		        Course enrolledCourse = Database.getInstance().findCourseById(courseId);

		        if (enrolledCourse != null) {
		            Mark mark = enrolledCourse.getMarks().get(this);

		            if (mark != null) {
		                double courseCredits = enrolledCourse.getCostInCredits();
		                double courseGpa = calculateCourseGpa(mark.getFinal()+mark.getFirstAttestation()+mark.getSecondAttestation());

		                totalCredits += courseCredits;
		                totalGradePoints += (courseGpa * courseCredits);
		            } else {
		                System.out.println("Marks not available for enrolled course ID: " + courseId);
		            }
		        } else {
		            System.out.println("Enrolled course with ID " + courseId + " not found.");
		        }
		    }

		    //Calculate GPA for passed courses
		    for (Course passedCourse : passedCourses) {
		        Mark mark = passedCourse.getMarks().get(this);

		        if (mark != null) {
		            double courseCredits = passedCourse.getCostInCredits();
		            double courseGpa = calculateCourseGpa(mark.getFinal()+mark.getFirstAttestation()+mark.getSecondAttestation());

		            totalCredits+= courseCredits;
		            totalGradePoints += (courseGpa * courseCredits);
		        } else {
		            System.out.println("Marks not available for passed course: " + passedCourse.getCourseName());
		        }
		    }

		    if (totalCredits> 0) {
		        double gpa = totalGradePoints / totalCredits;
		        System.out.println("Your GPA: " + gpa);
		    } else {
		        System.out.println("Unable to calculate GPA.");
		    }
		}

		private double calculateCourseGpa(double finalGrade) {
	        if (finalGrade >= 90) {
	            return 4.0;
	        } else if (finalGrade >= 80) {
	            return 3.0;
	        } else if (finalGrade >= 70) {
	            return 2.0;
	        } else if (finalGrade >= 60) {
	            return 1.0;
	        } else {
	            return 0.0;
	        }
	    }

		//Need fix
		public void viewMarks() {
		    System.out.println("Your marks for enrolled courses:");

		    for (Integer courseId : enrolledCourseId) {
		        Course enrolledCourse = Database.getInstance().findCourseById(courseId);

		        if (enrolledCourse != null) {
		            Mark mark = enrolledCourse.getMarks().get(this);

		            if (mark != null) {
		                System.out.println("Course ID: " + enrolledCourse.getCourseId());
		                System.out.println("Course Name: " + enrolledCourse.getCourseName());
		                System.out.println("First Attestation: " + mark.getFirstAttestation());
		                System.out.println("Second Attestation: " + mark.getSecondAttestation());
		                System.out.println("Final: " + mark.getFinal());
		                System.out.println("---------------");
		            } else {
		                System.out.println("Marks not available for course ID: " + courseId);
		            }
		        } else {
		            System.out.println("Enrolled course with ID " + courseId + " not found.");
		        }
		    }
		}
	
		@Override
		public void viewCourses() {
	        Vector<Course> courses = Database.getInstance().getCourses();

	        System.out.println("Available courses:");

	        for (Course course : courses) {
	            if (course.getSchools().contains(this.getSchool())) {
	                boolean prerequisitesMet = true;
	                for (Course prereq : course.getPrereq()) {
	                    boolean hasPrereq = false;
	                    for (Course passedCourse : passedCourses) {
	                        if (prereq.getCourseName().equals(passedCourse.getCourseName())) {
	                            hasPrereq = true;
	                            break;
	                        }
	                    }
	                    if (!hasPrereq) {
	                        prerequisitesMet = false;
	                        break;
	                    }
	                }

	                if (prerequisitesMet) {
	                    System.out.println("Course ID: " + course.getCourseId());
	                    System.out.println("Course Name: " + course.getCourseName());

	                    System.out.print("Lesson Days: ");
	                    for (Lesson lesson : course.getLessons()) {
	                        lesson.viewDays();
	                    }
	                    System.out.println();
	                }
	            }
	        }
	    }
		//Need fix
		public void viewSchedule() {
		    if (enrolledCourseId.isEmpty()) {
		        System.out.println("No enrolled courses to display schedule.");
		        return;
		    }

		    System.out.println("Schedule for all enrolled courses:");

		    for (Integer courseId : enrolledCourseId) {
		        Course enrolledCourse = Database.getInstance().findCourseById(courseId);

		        if (enrolledCourse != null) {
		            System.out.println("Course ID: " + enrolledCourse.getCourseId());
		            System.out.println("Course Name: " + enrolledCourse.getCourseName());
		            System.out.println("Schedule:");

		            for (Lesson lesson : enrolledCourse.getLessons()) {
		                System.out.println("Lesson Type: " + lesson.getLessonType());
		                System.out.print("Days: ");
		                lesson.viewDays();
		            }

		            System.out.println("---------------");
		        } else {
		            System.out.println("Enrolled course with ID " + courseId + " not found.");
		        }
		    }
		}

		@Override
		public void viewTeachers() {
		    for (Teacher teacher : Database.getInstance().getTeachers()) {
		        System.out.println("Teacher ID: " + teacher.getUserId());
		        System.out.println("Name: " + teacher.getName());
		        System.out.println("Teacher Type: " + teacher.getTeacherType());
		        System.out.println();
		    }
		}
		public void viewOrganizations() {
			for (Organization o : Database.getInstance().getOrganizations()) {
	            System.out.println("Organization id: " + o.getOrganizationId() + "\n" +
	            		"with head: " + o.getHead().getName() + "\n" +
	            		o.getName()
	            		);
	        }
		}
		public void joinOrganization(Organization organization) {
	        if (!organizations.contains(organization)) {
	            organizations.add(organization);
	            System.out.println("Joined organization: " + organization.getName());
	        } else {
	            System.out.println("You are already a member of the organization.");
	        }
	    }

	    public void leaveOrganization(Organization organization) {
	        if (organizations.contains(organization)) {
	            organizations.remove(organization);
	            System.out.println("Left organization: " + organization.getName());
	        } else {
	            System.out.println("You are not a member of the organization.");
	        }
	    }
	    public void showCommands() {
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	        while (true) {
	            //DB
	            try {
	                Database.getInstance().write();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            //DB

	            System.out.println("Student Commands:");

	            System.out.println("1. View Journals");
	            System.out.println("2. Subscribe to Journal");
	            System.out.println("3. Unsubscribe from Journal");
	            System.out.println("4. View Messages");
	            System.out.println("5. Clear Messages");
	            System.out.println("6. View News");
	            System.out.println("7. Change Language");
	            System.out.println("8. Change Password");
	            System.out.println("9. View Marks");
	            System.out.println("10. View GPA");
	            System.out.println("11. View Transcript");
	            System.out.println("12. Rate Teacher");
	            System.out.println("13. Register for Course");
	            System.out.println("14. Leave Organization");
	            System.out.println("15. Join Organization");
	            System.out.println("16. View Organizations");
	            System.out.println("17. View Teachers");
	            System.out.println("18. View Schedule");
	            System.out.println("19. View Courses");
	            System.out.println("20. Exit");
	            System.out.println("21. Researcher menu");

	            try {
	                int choice = Integer.parseInt(reader.readLine());

	                switch (choice) {
	                    case 1:
	                        viewJournals();
	                        break;
	                    case 2:
	                        System.out.println("Enter the ID of the journal you want to subscribe to:");
	                        try {
	                            int journalIdToSubscribe = Integer.parseInt(reader.readLine());
	                            Journal journalToSubscribe = Database.getInstance().findJournalById(journalIdToSubscribe);
	                            if (journalToSubscribe != null) {
	                                subscribeToJournal(journalToSubscribe);
	                                System.out.println("Subscribed to journal successfully!");
	                            } else {
	                                System.out.println("Journal with ID " + journalIdToSubscribe + " not found.");
	                            }
	                        } catch (NumberFormatException | IOException e) {
	                            System.out.println("Invalid input. Please enter a valid integer.");
	                        }
	                        break;
	                    case 3:
	                        System.out.println("Enter the ID of the journal you want to unsubscribe from:");
	                        try {
	                            int journalIdToUnsubscribe = Integer.parseInt(reader.readLine());
	                            Journal journalToUnsubscribe = Database.getInstance().findJournalById(journalIdToUnsubscribe);
	                            if (journalToUnsubscribe != null) {
	                                unsubscribeFromJournal(journalToUnsubscribe);
	                                System.out.println("Unsubscribed from journal successfully!");
	                            } else {
	                                System.out.println("Journal with ID " + journalIdToUnsubscribe + " not found.");
	                            }
	                        } catch (NumberFormatException | IOException e) {
	                            System.out.println("Invalid input. Please enter a valid integer.");
	                        }
	                        break;
	                    case 4:
	                        viewMessages();
	                        break;
	                    case 5:
	                        clearMessages();
	                        break;
	                    case 6:
	                        viewNews();
	                        break;
	                    case 7:
	                        changeLanguage();
	                        break;
	                    case 8:
	                        System.out.println("Enter your new password:");
	                        String newPassword = reader.readLine();
	                        setPassword(newPassword);
	                        System.out.println("Password changed successfully!");
	                        break;
	                    case 9:
	                        viewMarks();
	                        break;
	                    case 10:
	                        viewGpa();
	                        break;
	                    case 11:
	                        viewTranscript();
	                        break;
	                    case 12:
	                        System.out.println("Enter the ID of the teacher you want to rate:");
	                        try {
	                            int teacherId = Integer.parseInt(reader.readLine());
	                            Teacher teacherToRate = Database.getInstance().findTeacherById(teacherId);
	                            if (teacherToRate != null) {
	                                System.out.println("Enter your rating (1-5):");
	                                int rating = Integer.parseInt(reader.readLine());
	                                rateTeacher(teacherToRate, rating);
	                                System.out.println("Teacher rated successfully!");
	                            } else {
	                                System.out.println("Teacher with ID " +teacherId + " not found.");
	                            }
	                        } catch (NumberFormatException | IOException e) {
	                            System.out.println("Invalid input. Please enter a valid integer.");
	                        }
	                        break;
	                    case 13:
	                        System.out.println("Enter the ID of the course you want to register for:");
	                        try {
	                            int courseId = Integer.parseInt(reader.readLine());
	                            Course courseToRegister = Database.getInstance().findCourseById(courseId);
	                            if (courseToRegister != null) {
	                                registerForCourse(courseToRegister);
	                            } else {
	                                System.out.println("Course with ID " +courseId + " not found.");
	                            }
	                        } catch (NumberFormatException | IOException e) {
	                            System.out.println("Invalid input. Please enter a valid integer.");
	                        }
	                        break;
	                    case 14:
	                        System.out.println("Enter the ID of the organization you want to leave:");
	                        try {
	                            int orgId = Integer.parseInt(reader.readLine());
	                            Organization organizationToLeave = Database.getInstance().findOrganizationById(orgId);
	                            if (organizationToLeave!= null) {
	                                leaveOrganization(organizationToLeave);
	                            } else {
	                                System.out.println("Organization with ID " + orgId +" not found.");
	                            }
	                        } catch (NumberFormatException | IOException e) {
	                            System.out.println("Invalid input. Please enter a valid integer.");
	                        }
	                        break;
	                    case 15:
	                        System.out.println("Enter the ID of the organization you want to join:");
	                        try {
	                            int orgId = Integer.parseInt(reader.readLine());
	                            Organization organizationToJoin = Database.getInstance().findOrganizationById(orgId);
	                            if (organizationToJoin!= null) {
	                                joinOrganization(organizationToJoin);
	                            } else {
	                                System.out.println("Organization with ID " + orgId + " not found.");
	                            }
	                        } catch (NumberFormatException | IOException e) {
	                            System.out.println("Invalid input. Please enter a valid integer.");
	                        }
	                        break;
	                    case 16:
	                        viewOrganizations();
	                        break;
	                    case 17:
	                        viewTeachers();
	                        break;
	                    case 18:
	                        viewSchedule();
	                        break;
	                    case 19:
	                        viewCourses();
	                        break;
	                    case 20:
	                        System.out.println("Exiting Student commands.");
	                        return;
	                    case 21:
	                    	ResearcherDecorator Researcher = Database.getInstance().findResearcherById(this.getUserId());
	                    	if(Researcher != null){
	                    		Researcher.showResearcherCommands();
	                    	}
	                    	else {
	                    		System.out.println("User have no permission to do that. Firstly became a Researcher");
	                    	}
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            } catch (NumberFormatException | IOException e) {
	                System.out.println("Invalid input. Please enter a valid integer.");
	            }
	        }
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
			return this.getUserId() == other.getUserId();
		}
}
