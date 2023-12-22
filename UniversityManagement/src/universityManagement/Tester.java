package universityManagement;

import users.*;
import database.Database;

public class Tester {
	public static void main( String args[]) {
		User a = new Employee("James" , "pas");
		User b = new Employee("James2" , "pas2");
		System.out.print(a.getUserId());
		Database.getInstance().getUsers().add(a);
		System.out.print(b.getUserId());
		Database.getInstance().getUsers().add(b);
		LoggingSystem.start();
	}
}
