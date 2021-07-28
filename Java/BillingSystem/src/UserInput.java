import java.util.ArrayList;
import java.util.Scanner;

public class UserInput {
	
	static Scanner sc = new Scanner(System.in);
	static String name;
	static String phone;
	
	static ArrayList<Order> order = new ArrayList<Order>();
	
	// user's order
	public static void getOrder() {
		// user input, s/n and qty
		Display.printHeader(2); // order
		System.out.print("Enter Name: ");
		name = sc.nextLine();
		System.out.print("Enter Tel : ");
		phone = sc.nextLine();
		System.out.println("            *** Enter '0' to terminate ***");
		System.out.println("------------------------------------------");
		
		//loop
		while(true){
			System.out.print("Enter the Item's S/N: ");
			int item = sc.nextInt();
			if( item == 0 ) {
				break;
			}
			// TRUE: good, FALSE: bad
			boolean validate = validateItemNo(item);
			if( !validate ) { // if FALSE: re-loop
				continue;
			}
			
			System.out.print("Enter the quantity: ");
			int qty = sc.nextInt();
			
			double price = 0;
			for(Food food: Display.menu) {
				if(food.itemNo == item) {
					price = food.price;
				}
			}
			
			if( qty != 0 ) {
				Order o1 = new Order(item, qty);
				o1.setSubtotal(qty, price);
				order.add(o1);
			}
			System.out.println();
		}
		order = BillingSystem.cleanList(order);
		Display.printEnd(2);
	}
	
	// user payment
	public static void getPayment() {
		double pay = 0;
		double minTip = BillingSystem.calculateTip(BillingSystem.totalAmount);
		System.out.printf("NOTE: Tip is 10%% of Total - $%.2f\n", minTip);
		double tipAmount = getTip(minTip);
		pay = BillingSystem.totalAmount + tipAmount;
		Display.printEnd(3);
		Display.displayPayment(pay, tipAmount);
	}
	
	public static double getTip( double minTip ) {
		boolean valTip = true;
		double tipAmount = 0;
		System.out.print("Would you like to tip Y/N: ");
		String yn = sc.next();
		if( yn.equals("y") || yn.equals("Y") ) {
			
			while(true) {
				System.out.print("Enter amount to tip $: ");
				tipAmount = sc.nextDouble();
				valTip = validateTip(tipAmount, minTip);
				
				// TRUE: sufficient amount, FALSE: insufficient
				if(valTip) {
					break;
				}
			}
			
		}
		else if( yn.equals("n") || yn.equals("N") ) {
			tipAmount = minTip;
		}
		else {
			System.out.println("Only key in Y or N !!!");
			getTip(minTip); //self-call
		}
		return tipAmount;
	}
	
	// VALIDATION
	public static boolean validateItemNo( int no ) {
		if( !(no > 0 && no <= Display.menu.size()) ) {
			System.out.println("Item does not exist");
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean validateTip( double tip, double minTip ) {
		if( tip < minTip ) {
			System.out.println("Insufficient Amount !!!");
			return false;
		}
		else {
			return true;
		}
	}
}
