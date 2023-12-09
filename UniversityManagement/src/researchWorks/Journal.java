package researchWorks;

import java.util.Observable;

/**
* @generated
*/
public class Journal extends Observable implements UseResearchPapers {
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
