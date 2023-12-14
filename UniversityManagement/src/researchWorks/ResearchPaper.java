package researchWorks; 
 
import java.util.Date; 
import java.util.Vector;

import users.ResearcherDecorator; 
 
public class ResearchPaper { 
	 private int paperid; 
	 private String title; 
	  
	 private Vector<ResearcherDecorator> authors; 
	 private int NumberOfPages; 
	 private Date publicationDate; 
	 private int citationsNumber; 
	 private String doi; 
	  
	 public ResearchPaper(int paperid, String title, Vector<ResearcherDecorator> authors, int numberOfPages, 
	   Date publicationDate, int citationsNumber, String doi) { 
	  this.paperid = paperid; 
	  this.title = title; 
	  this.authors = authors; 
	  this.NumberOfPages = numberOfPages; 
	  this.publicationDate = publicationDate; 
	  this.citationsNumber = citationsNumber; 
	  this.doi = doi; 
	 } 
	 public int getCitation(String s) { 
	//  we could add here the algorithm that would search the given string in the text, 
	//  if found, citationsNumber will be incremented, well, or we could just increment the citationsNumber 
	//  it's not like we have a text in the papers, so why even bother with returning String? 
	//  for now i will just increment the citationsNumber 
	  return this.citationsNumber++; 
	 } 
	// getters 
	 public String getTitle() { 
	  return title; 
	 } 
	 
	 public Date getPublicationDate() { 
	  return publicationDate; 
	 } 
	 
	 public int getCitationsNumber() { 
	  return citationsNumber; 
	 } 
 
  
}