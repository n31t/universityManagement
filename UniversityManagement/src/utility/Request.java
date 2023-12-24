package utility;

import java.io.Serializable;

import enums.Status;

public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	private int requestId;
	private String requestText;
	private Status requestStatus;
	
	private static int count = 0;
	{
		requestId = (count++);
	}
	public Request(){
		this.requestStatus = Status.UNKNOWN;
	}
	public Request(String requstText) {
		this.requestText = requstText;
		this.requestStatus = Status.UNKNOWN;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public String getRequestText() {
		return requestText;
	}
	public void setRequestText(String requstText) {
		this.requestText = requstText;
	}
	public Status getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(Status requestStatus) {
		this.requestStatus = requestStatus;
	}
		

}
