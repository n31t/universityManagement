package utility;

import java.io.Serializable;

import database.Database;

/**
 * Represents a news item in a university management system.
 * This class encapsulates details of a news item including its ID, topic, content, and a flag for its pinned status.
 */
public class News implements Serializable{
	private static final long serialVersionUID = 1L;
	private int newsId;
	private String topic;
    private String content;

    private boolean isPinned;
 // Initializer block for newsId
    {
    	newsId = Database.nextId();
    } 
    
    /**
     * Default constructor for creating a News instance without predefined properties.
     */
	public News() {
		
	}
	
	 /**
     * Constructs a News item with specified attributes.
     *
     * @param newsId The ID of the news item.
     * @param topic The topic of the news item.
     * @param content The content of the news item.
     * @param isPinned Flag indicating whether the news item is pinned.
     */
    public News(int newsId, String topic, String content, boolean isPinned) {
		this.topic = topic;
		this.content = content;
		this.isPinned = isPinned;
	}
                              
    
    /**
     * Retrieves the topic of the news item.
     *
     * @return The topic of the news item.
     */
    public String getTopic() {
        return topic;
    }

    /**
     * Sets the topic of the news item.
     *
     * @param topic The topic to be set for the news item.
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Retrieves the content of the news item.
     *
     * @return The content of the news item.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the news item.
     *
     * @param content The content to be set for the news item.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Sets the pinned status of the news item.
     *
     * @param isPinned Flag indicating whether the news item should be pinned.
     */
    public void setPinned(boolean isPinned) {
        this.isPinned = isPinned;
    }

    /**
     * Checks if the news item is pinned.
     *
     * @return True if the news item is pinned, false otherwise.
     */
    public boolean isPinned() {
        return this.isPinned;
    }

    /**
     * Retrieves the ID of the news item.
     *
     * @return The ID of the news item.
     */
    public int getNewsId() {
        return newsId;
    }

    /**
     * Sets the ID for the news item.
     *
     * @param newsId The ID to be set for the news item.
     */
    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    // End of class definition
    
    
}
