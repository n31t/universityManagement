package researchWorks; 
 
import java.io.Serializable;
import java.util.Date; 
import java.util.Vector;

import enums.Format;
import users.ResearcherDecorator; 
 
public class ResearchPaper implements Serializable{ 
	 private static final long serialVersionUID = 1L;
	private int paperId; 
	 private String title; 
	  
	 private Vector<ResearcherDecorator> authors; 
	 private int NumberOfPages; 
	 private Date publicationDate = new Date(System.currentTimeMillis());; 
	 private int citationsNumber; 
	 private String doi; 
	 
	 private static int count = 0; //Id
	  
	 {
		 paperId = (count++);
		 authors = new Vector<ResearcherDecorator>();
	 }
	 public ResearchPaper() {
		 
	 }
	 public ResearchPaper(String title, Vector<ResearcherDecorator> authors, int numberOfPages, int citationsNumber, String doi) { 
	  this.title = title; 
	  this.authors = authors; 
	  this.NumberOfPages = numberOfPages; 
	  this.citationsNumber = citationsNumber; 
	  this.doi = doi; 
	 } 
	 
	 
	// getters 
	 
	 public String getTitle() { 
	  return title; 
	 } 
	 
	 public int getPaperId() {
		return paperId;
	}


//	public void setPaperid(int paperid) {
//		this.paperid = paperid;
//	}


	public Vector<ResearcherDecorator> getAuthors() {
		return authors;
	}


	public void setAuthors(Vector<ResearcherDecorator> authors) {
		this.authors = authors;
	}


	public int getNumberOfPages() {
		return NumberOfPages;
	}


	public void setNumberOfPages(int numberOfPages) {
		NumberOfPages = numberOfPages;
	}


	public String getDoi() {
		return doi;
	}


	public void setDoi(String doi) {
		this.doi = doi;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}


	public void setCitationsNumber(int citationsNumber) {
		this.citationsNumber = citationsNumber;
	}


	public Date getPublicationDate() { 
	  return publicationDate; 
	 } 
	 
	 public int getCitationsNumber() { 
	  return citationsNumber; 
	 } 
	 //Need fix
	 public String getCitation(Format f) { 
		 citationsNumber++;
		 switch (f) {
         case PLAIN_TEXT:
             return "S: fff A: ddd";
         case BIBTEX:
             return "A: mffmskfdmdsf";
         default:
             return "No such format";
     }
	 } 
  
}