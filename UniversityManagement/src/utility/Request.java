package utility;

import java.io.Serializable;

import database.Database;
import enums.Status;

/**
 * Represents a request or application in a university management system.
 * This class encapsulates details of a request, including its unique identifier, text, and current status.
 */
public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	private int requestId;
	private String requestText;
	private Status requestStatus;
	// Initializer block for requestId
	
	{
		requestId = Database.nextId();
	}
	/**
     * Default constructor for creating a Request instance with an unknown status.
     */
	public Request(){
		this.requestStatus = Status.UNKNOWN;
	}
	
	/**
     * Constructs a Request with a specified request text.
     * The request status is initialized as unknown.
     *
     * @param requestText The text of the request.
     */
	public Request(String requstText) {
		this.requestText = requstText;
		this.requestStatus = Status.UNKNOWN;
	}
	
    /**
     * Retrieves the request ID.
     *
     * @return The request ID.
     */
	public int getRequestId() {
		return requestId;
	}
	
    /**
     * Sets the request ID.
     *
     * @param requestId The request ID to be set.
     */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
    /**
     * Retrieves the text of the request.
     *
     * @return The request text.
     */
	public String getRequestText() {
		return requestText;

	    /**
	     * Sets the text of the request.
	     *
	     * @param requestText The request text to be set.
	     */
	}
    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    /**
     * Retrieves the current status of the request.
     *
     * @return The status of the request.
     */
    public Status getRequestStatus() {
        return requestStatus;
    }

    /**
     * Sets the status of the request.
     *
     * @param requestStatus The status to be set for the request.
     */
    public void setRequestStatus(Status requestStatus) {
        this.requestStatus = requestStatus;
    }

    // End of class definition
		

}
