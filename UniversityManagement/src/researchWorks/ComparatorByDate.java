package researchWorks;


public class ComparatorByDate extends ResearchPaperComparator{ 
	 public int compare(ResearchPaper rp1, ResearchPaper rp2) { 
	  return rp1.getPublicationDate().compareTo(rp2.getPublicationDate()); 
	 } 
	}
