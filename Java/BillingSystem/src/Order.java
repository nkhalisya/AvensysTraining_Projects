
public class Order {
	int itemNo;
	int quantity;
	double subtotal;
	
	Order( int itemNo, int quantity ){
		this.itemNo = itemNo;
		this.quantity = quantity;
	}
	
	public int getItemNo() {
		return this.itemNo;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getSubtotal() {
		return this.subtotal;
	}
	public void setSubtotal(int quanity, double price ) {
		this.subtotal = quantity * price;
	}
	
	@Override
	public String toString() {
        return " " + this.itemNo + " " + this.quantity + " $" + this.subtotal;
    }
}
