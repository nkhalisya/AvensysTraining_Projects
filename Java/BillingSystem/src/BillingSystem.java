import java.util.ArrayList;
import java.util.Scanner;

public class BillingSystem {
	
//	Design a project to display items in a Cafeteria. 
//	Allow user to add items and 
//	write code that computes the total of items 
//	also add service taxes and tip on a restaurant bill. 
//	The program should ask the user to enter the multiple items from the displayed list. 
//	The tax should be 6.75% of the meal charge. 
//	The tip should be 10% of the total after adding the tax/ and higher number if the user wants to tip. 
//	Display the items charge, tax amount, tip amount, and total bill on the screen.
		
	static double taxPercent = 6.75;
	static double tipPercent = 10.0;
	static double totalAmount;

	public static void main(String[] args) {
		
		// display menu
		Display.generateMenu();
		
		// get user's order
		UserInput.getOrder();
		
		// if items are ordered
		if(UserInput.order.size() > 0) {
			Display.printHeader(3); // View Order
			Display.displayOrder(UserInput.order);
				// total of each item --in Order class
				// total of all items --calculateTotal()
				// tax amount         --calculateTax()
			
			// get user payment
			UserInput.getPayment();
				// get tip amount
				// display receipt
		}
		else {
			Display.printHeader(5); // thanks
		}
		
	} 
	
	// CALCULATIONS
	public static double calculateTotal() {
		// in order list
		double total = 0;
		for(Order ord: UserInput.order) {
			total += ord.subtotal;
		}
		String str = String.format("%.2f", total);
		total = Double.parseDouble(str);
		return total;
	}
	
	public static double calculateTax( double total ) {
		// 6.75% of the meal charge
		double tax = (taxPercent/100) * total;
		String str = String.format("%.2f", tax);
		tax = Double.parseDouble(str);
		return tax;
	}
	
	public static double calculateTip( double amount ) {
		// 10% of the total after adding the tax/ and higher number if the user wants to tip
		double tip = (tipPercent/100) * amount;
		String str = String.format("%.2f", tip);
		tip = Double.parseDouble(str);
		return tip;
	}
	
	public static ArrayList<Order> cleanList(ArrayList<Order> order) {
		// remove duplicates
		ArrayList<Order> cleanOrder = new ArrayList<Order>();
		for( Food food: Display.menu ) {
			boolean add = false;
			int qty = 0;
			for(Order ord: order ) {
				if(food.itemNo == ord.itemNo) {
					qty += ord.quantity;
					add = true;
				}
			}
			if(add) {
				Order o1 = new Order(food.itemNo, qty);
				o1.setSubtotal(qty, food.price);
				cleanOrder.add(o1); 
			}
		}
		return cleanOrder;
	}
}
