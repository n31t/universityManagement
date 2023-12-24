package universityManagement;

import users.*;

import java.util.Vector;

import database.Database;
import researchWorks.*;

public class Tester {
	public static void main( String args[]) {
		Employee a = new Employee("James" , "pas");
		Employee b = new Employee("Joe" , "pas2");
		Dean c = new Dean("Dean" , "dean");
		Database.getInstance().getUsers().add(a);
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
		
		LoggingSystem.start();
	}
}
