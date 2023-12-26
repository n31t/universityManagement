package utility;

import java.io.Serializable;

import database.Database;

public class News implements Serializable{
	private static final long serialVersionUID = 1L;
	private int newsId;
	private String topic;
    private String content;

    private boolean isPinned;
    {
    	newsId = Database.nextId();
    } 
	public News() {
		
	}
	
    public News(int newsId, String topic, String content, boolean isPinned) {
		this.topic = topic;
		this.content = content;
		this.isPinned = isPinned;
	}
                              
    
    public String getTopic() {
		return topic;
	}



	public void setTopic(String topic) {
		this.topic = topic;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}



	public boolean isPinned() {
        //TODO
        return this.isPinned;
    }



	public int getNewsId() {
		return newsId;
	}



	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
    
    
}
