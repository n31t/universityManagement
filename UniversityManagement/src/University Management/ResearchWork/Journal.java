package University Management;


/**
* @generated
*/
public class Journal implements UseResearchPapers, Observable {
    
    /**
    * @generated
    */
    private String journalName;
    
    /**
    * @generated
    */
    private Vector publishedPapers;
    
    
    /**
    * @generated
    */
    private ResearchPaper researchPaper;
    
    

    /**
    * @generated
    */
    private String getJournalName() {
        return this.journalName;
    }
    
    /**
    * @generated
    */
    private String setJournalName(String journalName) {
        this.journalName = journalName;
    }
    
    
    /**
    * @generated
    */
    private Vector getPublishedPapers() {
        return this.publishedPapers;
    }
    
    /**
    * @generated
    */
    private Vector setPublishedPapers(Vector publishedPapers) {
        this.publishedPapers = publishedPapers;
    }
    
    
    
    /**
    * @generated
    */
    public ResearchPaper getResearchPaper() {
        return this.researchPaper;
    }
    
    /**
    * @generated
    */
    public ResearchPaper setResearchPaper(ResearchPaper researchPaper) {
        this.researchPaper = researchPaper;
    }
    
    
    

    //                          Operations                                  
    
    
}
