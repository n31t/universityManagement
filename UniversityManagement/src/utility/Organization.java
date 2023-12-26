package utility;


import java.io.Serializable;
import java.util.Vector;

import users.Student;
public class Organization implements Serializable{
	private static final long serialVersionUID = 1L;
	private int organizationId;
	private static int counterId;
	private String name; 
	private Student head;
	{
		organizationId = counterId++;
	}
	
	public Organization() {
		super();
	}
	public Organization(String name, Student head) {
		super();
		this.name = name;
		this.head = head;
	}
	public int getOrganizationId() {
		return this.organizationId;
	}
	
	public String getName() {
		return this.name;
	}
	public void setname(String name) {
		this.name=name;
	}
	
	public Student getHead() {
        return this.head;
    }
    
    public void setHead(Student head) {
        this.head = head;
    }
	
}

