package users;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import database.Database;
import researchWorks.*;

public class ResearcherDecorator extends UserDecorator {
    private static final long serialVersionUID = 1L;
    private Vector<ResearchPaper> researchPapers;
    private int hIndex = 0;
    private Journal journal;


    ResearcherDecorator(User decoratedUser) {
        super(decoratedUser);
        //Kostil that checks is it a viable class to be Researcher
        if (decoratedUser.getClass() != Student.class && decoratedUser.getClass() != GraduateStudent.class
                && decoratedUser.getClass() != Employee.class && decoratedUser.getClass() != Teacher.class) {
            throw new IllegalArgumentException("This user cannot be a researcher");
        }
        researchPapers = new Vector<>();
        journal = null;
    }


    void setResearchPapers(Vector<ResearchPaper> papers) {
        researchPapers = papers;
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
        project.getParticipants().add(this);
    }
    void printPapers() {
        for(ResearchPaper r : researchPapers) {
        	System.out.println(r);        }
    }
    
    @SuppressWarnings("deprecation")
	public void createJournal(String journalName) {
    	if (Database.getInstance().findJournalByAuthorId(this.getUserId()) == null) {
            journal = new Journal(this);
            journal.setJournalName(journalName);
            journal.addObserver(this);
            Database.getInstance().getJournals().add(journal);
            System.out.println("Journal created: " + journalName);
    	}
    	else {
            System.out.println("Error: Journal already exists in the database.");
        }
    }
    @SuppressWarnings("deprecation")
	public void removeJournal() {
    	Journal journalToRemove = Database.getInstance().findJournalByAuthorId(this.getUserId());
        if (journalToRemove != null) {
            journalToRemove.deleteObserver(this);
            Database.getInstance().getJournals().remove(journalToRemove);
            System.out.println("Journal removed: " + journalToRemove.getJournalName());
        } 
        else {
            System.out.println("Error: Journal not found in the database.");
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
    public ResearchPaper findResearchPaperById(int paperId) {
            for (ResearchPaper paper : researchPapers) {
                if (paper.getPaperId() == paperId) {
                    return paper;
                }
        }
        return null; 
    }
    private void addPaperToJournal() {
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the ID of the paper you want to add to the journal:");
            int paperIdToAdd = Integer.parseInt(reader.readLine());
            ResearchPaper paperToAdd = findResearchPaperById(paperIdToAdd);

            if (paperToAdd != null) {
                Journal journal = getJournal();
                if (journal != null) {
                    journal.addPapers(paperToAdd);
                    System.out.println("Paper added to journal successfully!");
                } else {
                    System.out.println("Error: Researcher does not have a journal to add the paper to.");
                }
            } else {
                System.out.println("Paper with ID " + paperIdToAdd + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    private void removePaperFromJournal() {
        try {
        	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter the ID of the paper you want to remove from the journal:");
            int paperIdToRemove = Integer.parseInt(reader.readLine());
            ResearchPaper paperToRemove = findResearchPaperById(paperIdToRemove);

            if (paperToRemove != null) {
                Journal journal = getJournal();
                if (journal != null) {
                    journal.removePapers(paperToRemove);
                    System.out.println("Paper removed from journal successfully!");
                } else {
                    System.out.println("Error: Researcher does not have a journal to remove the paper from.");
                }
            } else {
                System.out.println("Paper with ID " + paperIdToRemove + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    //Need fix
    void showResearcherCommands() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
        	//DB
            try {
				Database.getInstance().write();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //DB
            System.out.println("Researcher Commands:");

            System.out.println("1. View Research Projects");
            System.out.println("2. Join Research Project");
            System.out.println("3. View Research Papers");
            System.out.println("4. Print Papers");
            System.out.println("5. Calculate H-Index");
            System.out.println("6. Create Journal");
            System.out.println("7. Remove Journal");
            System.out.println("8. Exit");
            System.out.println("9. Add Paper to Journal");
            System.out.println("10. Remove Paper from Journal");

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
                    case 9:
                        addPaperToJournal();
                        break;
                    case 10:
                        removePaperFromJournal();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException | IOException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

}

