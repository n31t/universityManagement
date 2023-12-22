package users;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Vector;

import database.Database;
import researchWorks.*;

import java.util.Vector;

public class ResearcherDecorator extends UserDecorator {
    private static final long serialVersionUID = 1L;
	private Vector<ResearchProject> researchProjects;
    private Vector<ResearchPaper> researchPapers;
    private int hIndex = 0;
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
        // Implementation of H-index calculation
        hIndex = 0;
        int paperCount = researchPapers.size();
        researchPapers.sort(new ComparatorByCitation().reversed());

        for (int i = 0; i < paperCount; i++) {
            int citations = researchPapers.get(i).getCitationsNumber();
            if (citations >= i + 1) {
                hIndex = i + 1;
            } else {
                break;
            }
        }

        return hIndex;
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
    
    void viewResearchProjects() {
        Vector<ResearchProject> allResearchProjects = Database.getInstance().getResearchProjects();

        System.out.println("All Research Projects:");
        for (ResearchProject project : allResearchProjects) {
            System.out.println("Project ID: " + project.getProjectId());
            System.out.println("Topic: " + project.getTopic());
            System.out.println("-----------------------");
        }
    }
    
    void viewResearchPapers() {
        System.out.println("Research Papers:");
        for (ResearchPaper paper : researchPapers) {
        	System.out.println("Paper ID: " + paper.getPaperId());
            System.out.println("Title: " + paper.getTitle());
            System.out.println("-----------------------");
        }
    }
    //Need fix
    void showResearcherCommands() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            System.out.println("Researcher Commands:");

            System.out.println("1. View Research Projects");
            System.out.println("2. Join Research Project");
            System.out.println("3. View Research Papers");
            System.out.println("4. Print Papers");
            System.out.println("5. Calculate H-Index");
            System.out.println("6. Create Journal");
            System.out.println("7. Remove Journal");
            System.out.println("8. Exit");

            try {
                int choice = Integer.parseInt(reader.readLine());

                switch (choice) {
                    case 1:
                        viewResearchProjects();
                        break;
                    case 2:
                        System.out.println("Enter the ID of the project you want to join:");
                        try {
                            int projectIdToJoin = Integer.parseInt(reader.readLine());
                            ResearchProject projectToJoin = Database.getInstance().findResearchProjectById(projectIdToJoin);
                            if (projectToJoin != null) {
                                joinResearchProject(projectToJoin);
                                System.out.println("Joined project successfully!");
                            } else {
                                System.out.println("Project with ID " + projectIdToJoin + " not found.");
                            }
                        } catch (NumberFormatException | IOException e) {
                            System.out.println("Invalid input. Please enter a valid integer.");
                        }
                        break;
                    case 3:
                        viewResearchPapers();
                        break;
                    case 4:
                        printPapers();
                        break;
                    case 5:
                        calculateHIndex();
                        System.out.println("H-Index calculated: " + getHIndex());
                        break;
                    case 6:
                        System.out.println("Enter the name of the journal to create:");
                        try {
                            String journalName = reader.readLine();
                            createJournal(journalName);
                        } catch (IOException e) {
                            System.out.println("Invalid input. Please enter a valid string.");
                        }
                        break;
                    case 7:
                        removeJournal();
                        break;
                    case 8:
                        System.out.println("Exiting Researcher commands.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

}

