package utility;


import java.io.Serializable;
import java.util.Vector;

import users.Student;
public class Organization implements Serializable{
	private static final long serialVersionUID = 1L;
	private int organizationId;
	private static int counterId;
	private OrganizationName name; //Need fix, is it a good idea? maybe string
	private Student head;
	private int numberOfMembers;
	{
		organizationId = counterId++;
	}
	
	public Organization(OrganizationName name, Student head) {
		super();
		this.name = name;
		this.head = head;
		numberOfMembers = 1;
	}
	public int getOrganizationId() {
		return this.organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId=organizationId;
	}
	
	public OrganizationName getName() {
		return this.name;
	}
	public void setname(OrganizationName name) {
		this.name=name;
	}
	
	public Student getHead() {
        return this.head;
    }
    
    public void setHead(Student head) {
        this.head = head;
    }
	public Vector<Student> getMembers() {
	    return this.members;
	}

	public void setMembers(Vector<Student> members) {
	    this.members = members;
	}

	public void addMembers(Student student) {
	    this.members.add(student);
	}

	public void removeStudents(Student student) {
	    this.members.remove(student);
	}
	
}

