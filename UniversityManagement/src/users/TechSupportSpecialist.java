package users;

import java.util.Scanner; 
import java.util.Vector;

import utility.Order; 
 
public class TechSupportSpecialist extends Employee{ 
	//Need fix MASSIVE REWORK
	 private Vector<Order> orders; 
	 
	 public TechSupportSpecialist(Vector<Order> orders) { 
	  this.orders = orders; 
	 } 
	  
	 public Vector<Order> getOrders() { 
	  return orders; 
	 } 
	 public void acceptOrder(Order o) { 
	  this.orders.add(o); 
	  System.out.println("Order succesfully accepted"); 
	 } 
	 public void rejectOrder(Order o) { 
	  System.out.println("order rejected"); 
	 } 
	 public void viewOrders() { 
	  for(Order o: this.orders) { 
	   System.out.println(o.getOrderId()); 
	//   i think we need a toString in the Order class 
	  } 
	   
	 } 
	 public void showCommands() { 
	  System.out.println("press 1 to see the orders, " 
	    + "press 2 to accept order, " 
	    + "press 3 to reject the order"); 
	  Scanner in = new Scanner(System.in); 
	  int a = in.nextInt(); 
	  if(a == 1) { 
	   this.viewOrders(); 
	  } 
	  else if(a == 2){ 
	   System.out.println("Enter the id of the order you want to accept"); 
	   int id = in.nextInt(); 
	   for (Order or : this.orders) { 
	             if (order.getId() == id) { 
	                 this.acceptOrder(or); 
	             } 
	         } 
	  } 
	  else if(a == 3){ 
	   System.out.println("Enter the id of the order you want to reject"); 
	   int id = in.nextInt(); 
	   for (Order or : this.orders) { 
	             if (order.getId() == id) { 
	                 this.rejectOrder(or); 
	             } 
	   } 
	  } 
	  else { 
	   System.out.println("such command does not exist"); 
	  } 
	 } 
}