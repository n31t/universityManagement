package researchWorks;


public class ComparatorByArticleLength extends ResearchPaperComparator{ 
	 public int compare(ResearchPaper rp1, ResearchPaper rp2) { 
	  return -rp1.getTitle().compareTo(rp2.getTitle()); 
	 } 
	 
	}
