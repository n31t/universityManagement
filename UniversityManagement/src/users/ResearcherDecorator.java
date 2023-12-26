package users;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import database.Database;
import enums.Format;
import researchWorks.*;
import utility.News;

public class ResearcherDecorator extends UserDecorator {
    private static final long serialVersionUID = 1L;
    private Vector<ResearchPaper> researchPapers;
    private int hIndex = 0;


    public ResearcherDecorator(User decoratedUser) {
        super(decoratedUser);

        if (decoratedUser.getClass() != Student.class && decoratedUser.getClass() != GraduateStudent.class
                && decoratedUser.getClass() != Employee.class && decoratedUser.getClass() != Teacher.class) {
            throw new IllegalArgumentException("This user cannot be a researcher");
        }
        researchPapers = new Vector<>();
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
	

	// Need Fix
	public int calculateHIndex() {
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
	    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	    System.out.println("Choose a sorting option:");
	    System.out.println("1. By Citation");
	    System.out.println("2. By Article Length");
	    System.out.println("3. By Date");

	    try {
	        int sortingOption = Integer.parseInt(reader.readLine());

	        switch (sortingOption) {
	            case 1:
	                researchPapers.sort(new ComparatorByCitation().reversed());
	                break;
	            case 2:
	                researchPapers.sort(new ComparatorByArticleLength());
	                break;
	            case 3:
	                researchPapers.sort(new ComparatorByDate());
	                break;
	            default:
	                System.out.println("Invalid choice. Papers will be printed without sorting.");
	        }

	        System.out.println("Research Papers:");

	        for (ResearchPaper paper : researchPapers) {
	            System.out.println("Paper ID: " + paper.getPaperId());
	            System.out.println("Title: " + paper.getTitle());
	            System.out.println("-----------------------");
	        }
	    } catch (NumberFormatException | IOException e) {
	        System.out.println("Invalid input. Please enter a valid integer.");
	    }
	}
    
    @SuppressWarnings("deprecation")
	public void createJournal(String journalName) {
    	if (Database.getInstance().findJournalByAuthorId(this.getUserId()) == null) {
            Journal journal = new Journal(this);
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
    
    void createResearchProject() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the topic of the research project:");
            String topic = reader.readLine();

            ResearchProject project = new ResearchProject();
            project.setTopic(topic);
            project.addParticipants(this);

            Database.getInstance().getResearchProjects().add(project);

            System.out.println("Research project created successfully!");
        } catch (IOException e) {
            System.out.println("Invalid input. Please enter a valid string.");
        }
    }
    
    void addOrRemovePapersFromProject(ResearchProject project, boolean add) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the ID of the paper you want to " + (add? "add to" : "remove from") + " the project:");
            int paperId = Integer.parseInt(reader.readLine());
            ResearchPaper paper = findResearchPaperById(paperId);

            if (paper != null) {
                if (add) {
                    project.addPapers(paper);
                    System.out.println("Paper added to the project successfully!");
                } else {
                    project.removePapers(paper);
                    System.out.println("Paper removed from the project successfully!");
                }
            } else {
                System.out.println("Paper with ID " + paperId + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
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
                Journal journal = Database.getInstance().findJournalByAuthorId(this.getUserId());
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
                Journal journal = Database.getInstance().findJournalByAuthorId(this.getUserId());
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
    
    void createResearchPaper() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the title of the research paper:");
            String title = reader.readLine();

            System.out.println("Enter the number of pages:");
            int numberOfPages = Integer.parseInt(reader.readLine());

            System.out.println("Enter the DOI:");
            String doi = reader.readLine();

            System.out.println("Enter the number of authors:");
            int numAuthors = Integer.parseInt(reader.readLine());
            Vector<ResearcherDecorator> authors = new Vector<ResearcherDecorator>();
            authors.add(this);

            ResearchPaper paper = new ResearchPaper(title, authors, numberOfPages, 0, doi);
            researchPapers.add(paper);

            System.out.println("Research paper created successfully!");
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter valid values.");
        }
    }

    
    private void viewCitation() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the ID of the paper for which you want to view the citation:");
            int paperId = Integer.parseInt(reader.readLine());
            ResearchPaper paper = findResearchPaperById(paperId);

            if (paper != null) {
                System.out.println("Choose the citation format:");
                System.out.println("1. Plain Text");
                System.out.println("2. Bibtex");

                int formatChoice = Integer.parseInt(reader.readLine());

                switch (formatChoice) {
                    case 1:
                        System.out.println("Plain Text Citation: " + paper.getCitation(Format.PLAIN_TEXT));
                        break;
                    case 2:
                        System.out.println("Bibtex Citation: " + paper.getCitation(Format.BIBTEX));
                        break;
                    default:
                        System.out.println("Invalid choice. Citation not generated.");
                }
            } else {
                System.out.println("Paper with ID " + paperId + " not found.");
            }
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
        }
    }
    
    void publishNews() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter the ID of the research paper you want to include in the news:");
            int paperId = Integer.parseInt(reader.readLine());
            ResearchPaper paper = findResearchPaperById(paperId);

            if (paper != null) {
                News news = new News();
                news.setTopic("New Research Paper Published");
                news.setContent("Check out the latest research paper: " + paper.getTitle());
                news.setPinned(true);

                Database.getInstance().getNews().add(news);

                System.out.println("News published successfully!");
            } else {
                System.out.println("Research Paper with ID " + paperId + " not found.");
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
            System.out.println("11. Create Research Project");
            System.out.println("12. Add Paper to Project");
            System.out.println("13. Remove Paper from Project");
            System.out.println("14. View Citations");
            System.out.println("15. Create Research Paper");
            System.out.println("16. Publish News");

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
                    case 11:
                        createResearchProject();
                        break;
                    case 12:
                        System.out.println("Enter the ID of the project you want to add a paper to:");
                        int projectIdToAddPaper = Integer.parseInt(reader.readLine());
                        ResearchProject projectToAddPaper = Database.getInstance().findResearchProjectById(projectIdToAddPaper);
                        if (projectToAddPaper != null) {
                            addOrRemovePapersFromProject(projectToAddPaper, true);
                        } else {
                            System.out.println("Project with ID " + projectIdToAddPaper + " not found.");
                        }
                        break;
                    case 13:
                        System.out.println("Enter the ID of the project you want to remove a paper from:");
                        int projectIdToRemovePaper = Integer.parseInt(reader.readLine());
                        ResearchProject projectToRemovePaper = Database.getInstance().findResearchProjectById(projectIdToRemovePaper);
                        if (projectToRemovePaper != null) {
                            addOrRemovePapersFromProject(projectToRemovePaper, false);
                        } else {
                            System.out.println("Project with ID " + projectIdToRemovePaper + " not found.");
                        }
                        break;
                    case 14:
                        viewCitation();
                        break;
                    case 15:
                        createResearchPaper();
                        break;
                    case 16:
                        publishNews();
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

