package universityManagement;

import users.*;
import database.Database;

public class Tester {
	public static void main( String args[]) {
		User a = new Employee("James" , "pas");
		User b = new Employee("James2" , "pas2");
		Database.getInstance().getUsers().add(a);
		Database.getInstance().getUsers().add(b);
		LoggingSystem.start();
	}
}
