package utility;

import java.io.Serializable;

import enums.Status;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	private int orderId;
	private static int counterId;
	private String orderText;
	private Status orderStatus;
	{
		orderId = counterId++;
	}
	
	public Order() {
	}
	public Order(String orderText) {
		this.orderText = orderText;
	}
	public int getOrderId() {
		return this.orderId;
	}
	public void setMessageId(int orderId) {
		this.orderId=orderId;
	}
	
	public String getOrderText() {
		return this.orderText;
	}
	public void setOrderText(String orderText) {
		this.orderText=orderText;
	}
	public Status getStatus() {
		return this.orderStatus;
	}
	public void setOrderText(Status orderStatus ) {
		this.orderStatus=orderStatus;
	}
}
