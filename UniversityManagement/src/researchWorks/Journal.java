package researchWorks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

import database.Database;
import users.ResearcherDecorator;

@SuppressWarnings("deprecation")
public class Journal extends Observable implements UseResearchPapers, Serializable {
	private static final long serialVersionUID = 1L;
	private int journalId;
    private List<ResearchPaper> publishedPapers;
    private String journalName;
    private ResearcherDecorator author;
    {
    	journalId = Database.nextId();
    	this.publishedPapers = new ArrayList<>();
    }
    public Journal() {
    }
    public Journal( ResearcherDecorator author) {
    	this.author = author;
    }
    public Journal(String journalName, ResearcherDecorator author) {
        this.journalName = journalName;
        this.author = author;
    }
    
    public ResearcherDecorator getAuthor() {
		return author;
	}
	public void setAuthor(ResearcherDecorator author) {
		this.author = author;
	}
	public int getJournalId() {
		return journalId;
	}
//	public void setJournalId(int journalId) {
//		this.journalId = journalId;
//	}
	public List<ResearchPaper> getPublishedPapers() {
		return publishedPapers;
	}
	public void setPublishedPapers(List<ResearchPaper> publishedPapers) {
		this.publishedPapers = publishedPapers;
	}
	public String getJournalName() {
		return journalName;
	}
	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}
    

	public void addPapers(ResearchPaper paper) {
        publishedPapers.add(paper);
        setChanged();
        notifyObservers("Journal : "+ this.getJournalName() + " new research paper added: " + paper.getTitle());
    }
    

	public void removePapers(ResearchPaper rp) { 
		  try { 
		   this.publishedPapers.remove(rp); 
		   setChanged();
	       notifyObservers("Journal : "+ this.getJournalName() + " removed: " + rp.getTitle());
		 
		   }  catch(NoSuchElementException ex) { 
		    System.out.println("Error: " + ex.getMessage()); 
		   }
	}
}
