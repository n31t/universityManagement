package users;

import database.Database;
import enums.DegreesType;
import enums.School;
import researchWorks.ResearchProject;
import universityManagement.InvalidStudyYearException;
import universityManagement.InvalidSupervisorException;

public class GraduateStudent extends Student {
    
    private static final long serialVersionUID = 1L;

	private DegreesType degreeType;

    private ResearcherDecorator researchSupervisor;

    private ResearchProject diplomaProject;
    
    {
    	researchSupervisor = null;
    	diplomaProject = null;    	
    }
    public GraduateStudent() {
    	
    }
    public GraduateStudent(String name , String password, int studyYear, School school,ResearcherDecorator researchSupervisor) throws InvalidStudyYearException, InvalidSupervisorException {
    	super(name , password, studyYear, school);
    	this.researchSupervisor = researchSupervisor;
    	if(researchSupervisor.calculateHIndex()<3) {
    		throw new InvalidSupervisorException();
    	}
    	ResearcherDecorator rd = new ResearcherDecorator(this);
    	Database.getInstance().getResearchers().add(rd);
    }
    public GraduateStudent(String name , String password, int studyYear, School school,
    		ResearcherDecorator researchSupervisor, ResearchProject diplomaProject) throws InvalidStudyYearException, InvalidSupervisorException {
    	super(name , password, studyYear, school);
    	this.researchSupervisor = researchSupervisor;
    	if(researchSupervisor.calculateHIndex()<3) {
    		throw new InvalidSupervisorException();
    	}
    	this.diplomaProject = diplomaProject;
    	ResearcherDecorator rd = new ResearcherDecorator(this);
    	Database.getInstance().getResearchers().add(rd);
    }
	public DegreesType getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(DegreesType degreeType) {
		this.degreeType = degreeType;
	}
	public ResearcherDecorator getResearchSupervisor() {
		return researchSupervisor;
	}
	public void setResearchSupervisor(ResearcherDecorator researchSupervisor) {
		this.researchSupervisor = researchSupervisor;
	}
	public ResearchProject getDiplomaProject() {
		return diplomaProject;
	}
	public void setDiplomaProject(ResearchProject diplomaProject) {
		this.diplomaProject = diplomaProject;
	}
	
    
    
    
    
   
    
              
    
    
}
