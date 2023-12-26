package universityManagement;

import users.*;

import java.util.Arrays;
import java.util.Vector;

import database.Database;
import enums.School;
import exceptions.InvalidStudyYearException;
import exceptions.InvalidSupervisorException;
import researchWorks.*;

public class Tester {
	public static void main( String args[]) throws InvalidStudyYearException, InvalidSupervisorException {
		Employee a = new Employee("James" , "pas");
		
		Dean c = new Dean("Dean" , "dean");
		Database.getInstance().getUsers().add(a);
		Employee b = new Employee("Joe" , "pas2");
		Database.getInstance().getUsers().add(b);
		Database.getInstance().getUsers().add(c);
		
		Database.getInstance().getEmployees().add(a);
		Database.getInstance().getEmployees().add(b);
		Database.getInstance().getEmployees().add(c);
		
		Database.getInstance().getDeans().add(c);
		
		ResearchPaper paper2 = new ResearchPaper();
		Vector <ResearchPaper> rp = new Vector<ResearchPaper>();
		rp.add(paper2);
		ResearcherDecorator researcherDecorator = new ResearcherDecoratorBuilder(a)
		        .withResearchPapers(rp)
		        .build();
		
		Database.getInstance().getResearchers().add(researcherDecorator);
		Admin admin = new Admin("admin", "admin");
		Employee employee1 = new Employee("James", "pas1");
		
        Dean dean1 = new Dean("Dean1", "deanPass1");
        System.out.println("dean1.getUserId()"+dean1.getUserId());/////////////////////////////////////////////

        Teacher teacher1 = new Teacher(null, "Teacher1", "teacherPass1");

        TechSupportSpecialist techSupport1 = new TechSupportSpecialist("TechSupport1", "techSupportPass1");

        Student student1 = new Student("Student1", "studentPass1", 2, School.SCHOOL_OF_IT_AND_ENGINEERING);

        Manager manager1 = new Manager("Manager1", "managerPass1", null);

       
        Database.getInstance().getUsers().addAll(Arrays.asList(admin, employee1, dean1, teacher1,
                techSupport1, student1, manager1));

        Database.getInstance().getEmployees().addAll(Arrays.asList(admin, employee1, dean1, teacher1, techSupport1, manager1));

        Database.getInstance().getDeans().add(dean1);

        Database.getInstance().getTeachers().add(teacher1);

        Database.getInstance().getTechSupportSpecialists().add(techSupport1);

        Database.getInstance().getStudents().add(student1);
        
        Student student = new Student("Amanzhol", "aaa", 1); 
        System.out.println("student.getUserId()"+student.getUserId());/////////////////////////////////////////////
        
        Database.getInstance().getUsers().add(student);
        admin.viewUsers();
		LoggingSystem.start();
	}
}
