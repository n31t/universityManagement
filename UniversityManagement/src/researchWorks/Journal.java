package researchWorks;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;

public class Journal extends Observable implements UseResearchPapers {
	private int journalId;
	private static int idCounter = 0;
    private List<ResearchPaper> publishedPapers;
    private String journalName;
    {
    	journalId = (idCounter++);
    }
    public Journal() {
        this.publishedPapers = new ArrayList<>();
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
