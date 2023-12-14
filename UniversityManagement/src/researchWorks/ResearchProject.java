package researchWorks;

import java.util.NoSuchElementException;
import java.util.Vector;

import users.ResearcherDecorator;

public class ResearchProject implements UseResearchPapers {
	private int projectid;
	private Vector<ResearchPaper> publishedPapers;
	private String topic;
	private Vector<ResearcherDecorator> participants;

	public ResearchProject(int projectid, Vector<ResearchPaper> publishedPapers, String topic,
			Vector<ResearcherDecorator> participants) {
		this.projectid = projectid;
		this.publishedPapers = publishedPapers;
		this.topic = topic;
		this.participants = participants;
	}

	public int getProjectid() {
		return projectid;
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

	public void addParticipants(ResearcherDecorator r) {
		this.participants.add(r);
//  is there a limit for a participants number? 
		System.out.println(r.getName() + " is now a participant of a project with an id of " + this.getProjectid());
	}

	public void RemoveParticipants(ResearcherDecorator r) {
		try {
			this.participants.remove(r);
			System.out.println(
					r.getName() + " is now not a participant of a project with an id of " + this.getProjectid());
//   i think the researchProject needs a name 
		} catch (NoSuchElementException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}

	public void addPapers(ResearchPaper rp) {
		this.publishedPapers.add(rp);
		System.out.println(rp.getTitle() + " is added to a project with an id of " + this.getProjectid());
	}

	public void removePapers(ResearchPaper rp) { 
  try { 
   this.publishedPapers.remove(rp); 
   System.out.println(rp.getTitle() + " is removed from a project with an id of " + this.getProjectid()); 
 
   }  catch(NoSuchElementException ex) { 
    System.out.println("Error: " + ex.getMessage()); 
   } 
}
}