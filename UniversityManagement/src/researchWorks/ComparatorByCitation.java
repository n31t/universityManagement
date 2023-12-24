package researchWorks;


public class ComparatorByCitation extends ResearchPaperComparator{ 
	 public int compare(ResearchPaper rp1, ResearchPaper rp2) { 
	  if(rp1.getCitationsNumber() > rp2.getCitationsNumber()) return 1; 
	  if(rp1.getCitationsNumber() < rp2.getCitationsNumber()) return -1; 
	  return 0; 
	 } 
	}
