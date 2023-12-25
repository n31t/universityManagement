package researchWorks;

import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Vector;

import users.ResearcherDecorator;

public class ResearchProject implements UseResearchPapers, Serializable {
	private static final long serialVersionUID = 1L;
	private int projectId;
	private Vector<ResearchPaper> publishedPapers;
	private String topic;
	private Vector<ResearcherDecorator> participants;

	 private static int count = 0; //Id
	  
	 {
		 projectId = (count++);
		 publishedPapers = new Vector<ResearchPaper>();
		 participants = new Vector<ResearcherDecorator>();
	 }
	
	 public ResearchProject() {
		 
	 }
	
	public ResearchProject(Vector<ResearchPaper> publishedPapers, String topic,
			Vector<ResearcherDecorator> participants) {
		this.publishedPapers = publishedPapers;
		this.topic = topic;
		this.participants = participants;
	}

	public int getProjectId() {
		return projectId;
	}

	public Vector<ResearchPaper> getPublishedPapers() {
		return publishedPapers;
	}

	public String getTopic() {
		return topic;
	}

	public Vector<ResearcherDecorator> getParticipants() {
		return participants;
	}
	

	public void setPublishedPapers(Vector<ResearchPaper> publishedPapers) {
		this.publishedPapers = publishedPapers;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void setParticipants(Vector<ResearcherDecorator> participants) {
		this.participants = participants;
	}

	public void addParticipants(ResearcherDecorator r) {
		this.participants.add(r); 
		System.out.println(r.getName() + " is now a participant of a project with an id of " + this.getProjectId());
	}

	public void RemoveParticipants(ResearcherDecorator r) {
		try {
			this.participants.remove(r);
			System.out.println(
					r.getName() + " is now not a participant of a project with an id of " + this.getProjectId());
		} catch (NoSuchElementException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public void addPapers(ResearchPaper rp) {
		this.publishedPapers.add(rp);
		System.out.println(rp.getTitle() + " is added to a project with an id of " + this.getProjectId());
	}

	public void removePapers(ResearchPaper rp) { 
  try { 
   this.publishedPapers.remove(rp); 
   System.out.println(rp.getTitle() + " is removed from a project with an id of " + this.getProjectId()); 
 
   }  catch(NoSuchElementException ex) { 
    System.out.println("Error: " + ex.getMessage()); 
   } 
}
}