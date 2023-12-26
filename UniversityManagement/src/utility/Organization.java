package utility;


import java.io.Serializable;
import java.util.Vector;

import database.Database;
import users.Student;

/**
 * Represents an organization or club in a university management system.
 * This class encapsulates details of an organization, including its unique identifier, name,
 * head (leader), and the number of members.
 */
public class Organization implements Serializable{
	private static final long serialVersionUID = 1L;
	private int organizationId;
	private String name; 
	private Student head;
	
	 // Initializer block for organizationId
	{
		organizationId = Database.nextId();
	}
	
	/**
     * Default constructor for creating an Organization instance without predefined properties.
     */
    public Organization() {
        super();
    }

    /**
     * Constructs an Organization with a specified name and head (leader).
     *
     * @param name The name of the organization.
     * @param head The student who is the head (leader) of the organization.
     */
    public Organization(String name, Student head) {
        super();
        this.name = name;
        this.head = head;
    }

    /**
     * Retrieves the organization ID.
     *
     * @return The organization ID.
     */
    public int getOrganizationId() {
        return this.organizationId;
    }

    /**
     * Retrieves the name of the organization.
     *
     * @return The name of the organization.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the organization.
     *
     * @param name The name to be set for the organization.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the head (leader) of the organization.
     *
     * @return The student who is the head of the organization.
     */
    public Student getHead() {
        return this.head;
    }

    /**
     * Sets the head (leader) of the organization.
     *
     * @param head The student to be set as the head of the organization.
     */
    public void setHead(Student head) {
        this.head = head;
    }

    // Additional methods for managing organization members can be added here

    // End of class definition
}

