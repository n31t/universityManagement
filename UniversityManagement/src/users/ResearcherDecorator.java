package users;

import java.util.Date;
import java.util.Vector;

import researchWorks.*;

import java.util.Vector;

public class ResearcherDecorator extends UserDecorator {
    private static final long serialVersionUID = 1L;
	private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;
    private int hIndex = 1;
    private Journal journal;


    ResearcherDecorator(User decoratedUser) {
        super(decoratedUser);
        researchProjects = new Vector<>();
        researchPapers = new Vector<>();
        journal = null;
    }

    void setResearchProjects(Vector<ResearchProject> projects) {
        researchProjects = projects;
    }

    void setResearchPapers(Vector<ResearchPaper> papers) {
        researchPapers = papers;
    }

    public Vector<ResearchProject> getResearchProjects() {
		return researchProjects;
	}

	public Vector<ResearchPaper> getResearchPapers() {
		return researchPapers;
	}    
	int getHIndex() {
    	return this.hIndex;
    }

//	public void setHindex(int hindex) {
//		this.hindex = hindex;
//	}
	
	public Journal getJournal() {
		return journal;
	}

	public void setJournal(Journal journal) {
		this.journal = journal;
	}

	// Need Fix
	public int calculateHIndex() {
		return 0;
	}

	void joinResearchProject(ResearchProject project) {
        researchProjects.add(project);
    }
    void printPapers() {
        for(ResearchPaper r : researchPapers) {
        	System.out.println(r);        }
    }
    
    @SuppressWarnings("deprecation")
	public void createJournal(String journalName) {
        if (journal == null) {
            journal = new Journal();
            journal.setJournalName(journalName);
            // Add the researcher (this) as an observer to the journal
            journal.addObserver(this);
            System.out.println("Journal created: " + journalName);
        } else {
            System.out.println("Error: Researcher already has a journal.");
        }
    }
    @SuppressWarnings("deprecation")
	public void removeJournal() {
        if (journal != null) {
            // Remove the researcher (this) as an observer from the journal
            journal.deleteObserver(this);
            System.out.println("Journal removed: " + journal.getJournalName());
            journal = null;
        } else {
            System.out.println("Error: Researcher does not have a journal to remove.");
        }
    }
    //Need fix
    void showResearcherCommands() {
    	
    }
}

