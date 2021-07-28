import java.util.ArrayList;

public class Display {
	
	static ArrayList<Food> menu = new ArrayList<Food>();
	
	// create menu
	public static void generateMenu() {
		// serial no, name, price
		Food f1 = new Food(1, "Sandwich ", 2.5);
		Food f2 = new Food(2, "Water    ", 1);
		Food f3 = new Food(3, "Ice Cream", 1);
		Food f4 = new Food(4, "Meal A   ", 3);
		Food f5 = new Food(5, "Meal B   ", 4);
		
		menu.add(f1);
		menu.add(f2);
		menu.add(f3);
		menu.add(f4);
		menu.add(f5);
		
		displayMenu();
	}

	// DISPLAY
	public static void displayMenu() {
		printHeader(1); // menu
		System.out.println("S/N          Item                  Price  ");
		System.out.println("------------------------------------------");
		for(Food food: menu) {
			System.out.println(food);
		}
		printEnd(1);
	}
	
	// display details after user has made payment
	public static void displayPayment( double pay, double tip ) {
		printHeader(4); // Receipt
		displayOrder(UserInput.order);
		System.out.printf("                              Tip  $%.2f\n", tip);
		System.out.printf("                      Amount PAID  $%.2f\n", pay);
		System.out.println("------------------------------------------");
		System.out.println("Customer: " + UserInput.name + "\nTel     : " + UserInput.phone);
		printHeader(5); // thanks
	}
	
	// display ordered items, subtotal, tax, total
	public static void displayOrder(ArrayList<Order> order) { 
		System.out.println("S/N Item Name       Qty            Price  ");
		System.out.println("------------------------------------------");
		
		int sn = 0, qty = 0;
		String itemName = "";
		double price = 0, subtotal = 0;
		for(Order ord: order) {
			sn = ord.itemNo;
			qty = ord.quantity;
			subtotal = ord.subtotal;
			for(Food food: menu) {
				if(food.itemNo == ord.itemNo) {
					itemName = food.itemName;
					price = food.price;
				}
			}
			System.out.printf(" %d. %s       %d x $%.2f      $%.2f\n", sn, itemName, qty, price, subtotal);
		}
		System.out.println("------------------------------------------");
		
		double total = BillingSystem.calculateTotal();
		double tax = BillingSystem.calculateTax(total);
		BillingSystem.totalAmount = tax + total;
		System.out.printf("                         Subtotal  $%.2f\n", total);
		System.out.printf("                      Tax (%.2f%%)  $%.2f\n", BillingSystem.taxPercent, tax);
		System.out.printf("                            Total  $%.2f\n", BillingSystem.totalAmount);
		System.out.println();
	}
	
	// HEADINGS
	public static void printHeader( int section ) {
		System.out.println("------------------------------------------");
		switch(section) {
			case 1:
				System.out.println("                  MENU                    ");
				break;
				
			case 2:
				System.out.println("                 ORDER                    ");
				break;
			
			case 3:
				System.out.println("               VIEW ORDER                 ");
				break;
				
			case 4:
				System.out.println("                RECEIPT                   ");
				break;
				
			case 5:
				System.out.println("               THANK YOU                  ");
				break;
			
			default:
				System.out.println();
		}
		System.out.println("------------------------------------------");
	}
	
	// ENDS
	public static void printEnd( int section ) {
		switch(section) {
			case 1:
				System.out.println("           xxx End of Menu xxx            \n");
				break;
				
			case 2:
				System.out.println("           xxx End of Order xxx           \n");
				break;
			
			case 3:
				System.out.println("          xxx End of Payment xxx          \n");
				break;
				
			default:
				System.out.println();
		}
	}
}
