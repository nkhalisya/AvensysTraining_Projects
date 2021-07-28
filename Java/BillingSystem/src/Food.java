
public class Food {
	int itemNo;
	String itemName;
	double price;
	
	Food( int itemNo, String itemName, double price ){
		this.itemNo = itemNo;
		this.itemName = itemName;
		this.price = price;
	}
	
	public int getItemNo() {
		return this.itemNo;
	}
	
	public String getItemName() {
		return this.itemName;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public String toString() {
        return " " + this.itemNo + ".          " + this.itemName + "             $" + String.format("%.2f",this.price);
    }
}
