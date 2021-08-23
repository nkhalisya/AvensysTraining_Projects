package com.Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Model {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet res;
	
	private User user;
	private Product product;
	private Cart cart;
	private Checkout checkout;
	private Detail detail;

	public Model(){
		connect();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Checkout getCheckout() {
		return checkout;
	}
	public void setCheckout(Checkout checkout) {
		this.checkout = checkout;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	// connect
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jewel", "root", "root");
		}
		catch( Exception e) {
			e.printStackTrace();
		}
	}

	// insert
	public int register() {
		try {
			String sql = "INSERT INTO user (name, email, username, password, cardno, cardtype) VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.name);
			pstmt.setString(2, user.email);
			pstmt.setString(3, user.username);
			pstmt.setString(4, user.password);
			pstmt.setString(5, user.cardno);
			pstmt.setString(6, user.cardtype);
			
			int inserted = pstmt.executeUpdate();
			return inserted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// fetch
	public boolean login() {
		try {
			String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.username);
			pstmt.setString(2, user.password);
			res = pstmt.executeQuery();
			if(res.next() == true ) {
				getAllStrings(res);
				return true;
			}
			else {
				return false;
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// fetch
	public boolean getUser( String colname ) {
		try {
			String sql = "SELECT * FROM user WHERE " + colname + " = ?";
			pstmt = con.prepareStatement(sql);
			if( colname.equals("id") ) {
				pstmt.setString(1, Integer.toString(user.id));
			}
			res = pstmt.executeQuery();
			if(res.next() == true ) {
				getAllStrings(res);
				return true;
			}
			else {
				return false;
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// fetch
	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		try {
			String sql = "SELECT * FROM user WHERE NOT id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user.id);
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				User u = new User();
				setUser(u);
				getAllStrings(res);
				users.add(user);
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	
	// fetch *
	private void getAllStrings(ResultSet res) {
		try {
			user.id = Integer.parseInt(res.getString("id"));
			user.name = res.getString("name");
			user.email = res.getString("email");
			user.username  = res.getString("username");
			user.password = res.getString("password");
			user.cardno  = res.getString("cardno");
			user.cardtype = res.getString("cardtype");
			user.role = res.getString("role");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// update
	public int edit( String colName ) {
		try {
			String sql = "UPDATE user SET " + colName + " = ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			if( colName.equals("name")) {
				pstmt.setString(1, user.name);
			}
			else if( colName.equals("email")) {
				pstmt.setString(1, user.email);
			}
			else if( colName.equals("username")) {
				pstmt.setString(1, user.username);
			}
			else if( colName.equals("password")) {
				pstmt.setString(1, user.password);
			}
			else if( colName.equals("cardno")) {
				pstmt.setString(1, user.cardno);
			}
			else if( colName.equals("cardtype")) {
				pstmt.setString(1, user.cardtype);
			}
			else if( colName.equals("role")) {
				pstmt.setString(1, user.role);
			}
			pstmt.setInt(2, user.id);
			
			int updated = pstmt.executeUpdate();
			return updated;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// delete
	public int delete() {
		try {
			String sql = "DELETE FROM user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, user.id);
			
			int deleted = pstmt.executeUpdate();
			
			sql = "ALTER TABLE user AUTO_INCREMENT=1;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			return deleted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// insert
	public int addProduct() {
		try {
			String sql = "INSERT INTO product (name, img, type, price) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.name);
			pstmt.setString(2, product.img);
			pstmt.setString(3, product.type);
			pstmt.setDouble(4, product.price);
			
			int inserted = pstmt.executeUpdate();
			return inserted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// fetch
	public boolean getProduct( String colname ) {
		try {
			String sql = "SELECT * FROM product WHERE " + colname + " = ?";
			pstmt = con.prepareStatement(sql);
			if( colname.equals("id") ) {
				pstmt.setString(1, Integer.toString(product.id));
			}
			res = pstmt.executeQuery();
			if(res.next() == true ) {
				getAllProductStrings(res);
				return true;
			}
			else {
				return false;
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// fetch
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			String sql = "SELECT * FROM product";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				Product p = new Product();
				setProduct(p);
				getAllProductStrings(res);
				products.add(product);
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	// fetch - based on TYPE
	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		try {
			String sql = "SELECT * FROM product WHERE type = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, product.type);
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				Product p = new Product();
				setProduct(p);
				getAllProductStrings(res);
				products.add(product);
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return products;
	}
	
	// fetch *
	private void getAllProductStrings(ResultSet res) {
		try {
			product.id = Integer.parseInt(res.getString("id"));
			product.name = res.getString("name");
			product.img = res.getString("img");
			product.type  = res.getString("type");
			product.price = Double.parseDouble(res.getString("price"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// update
	public int editProduct( String colName ) {
		try {
			String sql = "UPDATE product SET " + colName + " = ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			if( colName.equals("name")) {
				pstmt.setString(1, user.name);
			}
			else if( colName.equals("img")) {
				pstmt.setString(1, product.img);
			}
			else if( colName.equals("type")) {
				pstmt.setString(1, product.type);
			}
			else if( colName.equals("price")) {
				pstmt.setDouble(1, product.price);
			}
			pstmt.setInt(2, product.id);
			
			int updated = pstmt.executeUpdate();
			return updated;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// delete
	public int deleteProduct() {
		try {
			String sql = "DELETE FROM product WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, product.id);
			
			int deleted = pstmt.executeUpdate();
			
			sql = "ALTER TABLE product AUTO_INCREMENT=1;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			return deleted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// insert
	public int addCart() {
		try {
			String sql = "INSERT INTO cart (user_id, product_id, qty) VALUES (?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.userid);
			pstmt.setInt(2, cart.productid);
			pstmt.setInt(3, cart.qty);
			
			int inserted = pstmt.executeUpdate();
			return inserted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// fetch
	public boolean getCart( int pid, int uid ) {
		try {
			String sql = "SELECT * FROM cart WHERE product_id = ? AND user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(pid));
			pstmt.setString(2, Integer.toString(uid));
			res = pstmt.executeQuery();
			if(res.next() == true ) {
				getAllCartStrings(res);
				return true;
			}
			else {
				return false;
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// fetch
	public ArrayList<Cart> getCartItems() {
		ArrayList<Cart> carts = new ArrayList<Cart>();
		try {
			String sql = "SELECT * FROM cart WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(user.id));
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				Cart c = new Cart();
				setCart(c);
				getAllCartStrings(res);
				carts.add(cart);
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return carts;
	}

	// fetch
	public boolean inCart( int productid, int userid ) {
		try {
			String sql = "SELECT * FROM cart WHERE product_id = ? AND user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(productid));
			pstmt.setString(2, Integer.toString(userid));
			res = pstmt.executeQuery();
			if(res.next() == true ) {
				getAllCartStrings(res);
				return true;
			}
			else {
				return false;
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// fetch *
	private void getAllCartStrings(ResultSet res) {
		try {
			cart.id = Integer.parseInt(res.getString("id"));
			cart.userid = Integer.parseInt(res.getString("user_id"));
			cart.productid = Integer.parseInt(res.getString("product_id"));
			cart.qty = Integer.parseInt(res.getString("qty"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// update
	public int updateQty() {
		try {
			String sql = "UPDATE cart SET qty = ? WHERE product_id = ? AND user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.qty);
			pstmt.setInt(2, cart.productid);
			pstmt.setInt(3, cart.userid);
			
			int updated = pstmt.executeUpdate();
			return updated;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// delete
	public int deleteCart() {
		try {
			String sql = "DELETE FROM cart WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.id);
			
			int deleted = pstmt.executeUpdate();
			
			sql = "ALTER TABLE cart AUTO_INCREMENT=1;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			return deleted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// insert
	public int addCheckout( int uid, double price) {
		try {
			String sql = "INSERT INTO checkout (user_id, total) VALUES (?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setDouble(2, price);
			
			int inserted = pstmt.executeUpdate();
			return inserted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// insert
	public int addCheckoutDetails(int cid, int uid, int pid, int qty, double price) {
		try {
			String sql = "INSERT INTO checkoutDetail (checkout_id, user_id, product_id, qty, price) VALUES (?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.setInt(2, uid);
			pstmt.setInt(3, pid);
			pstmt.setInt(4, qty);
			pstmt.setDouble(5, price);
			
			int inserted = pstmt.executeUpdate();
			return inserted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// recently added record
	public int getCheckoutId() {
		int id = 0;
		try {
			String sql = "SELECT * FROM checkout ORDER BY id DESC LIMIT 1";
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			if(res.next() == true ) {
				id = Integer.parseInt(res.getString("id"));
			}
			return id;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	
	// fetch
	public ArrayList<Checkout> getCheckout( int userid ) {
		ArrayList<Checkout> checkouts = new ArrayList<Checkout>();
		try {
			String sql = "SELECT * FROM checkout WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(userid));
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				Checkout c = new Checkout();
				setCheckout(c);
				getCheckoutStrings(res);
				checkouts.add(checkout);
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return checkouts;
	}
	
	// fetch *
	private void getCheckoutStrings(ResultSet res) {
		try {
			checkout.id = Integer.parseInt(res.getString("id"));
			checkout.userid = Integer.parseInt(res.getString("user_id"));
			checkout.price = Double.parseDouble(res.getString("total"));
			checkout.datetime = res.getString("date");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// fetch
	public ArrayList<Detail> getCheckoutDetails( int checkoutid ) {
		ArrayList<Detail> details = new ArrayList<Detail>();
		try {
			String sql = "SELECT * FROM checkoutDetail WHERE checkout_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(checkoutid));
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				Detail d = new Detail();
				setDetail(d);
				getCheckoutDetailStrings(res);
				details.add(detail);
			}
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return details;
	}
	
	// fetch *
	private void getCheckoutDetailStrings(ResultSet res) {
		try {
			detail.checkoutid = Integer.parseInt(res.getString("checkout_id"));
			detail.userid = Integer.parseInt(res.getString("user_id"));
			detail.productid = Integer.parseInt(res.getString("product_id"));
			detail.qty = Integer.parseInt(res.getString("qty"));
			detail.price = Double.parseDouble(res.getString("price"));
			detail.datetime = res.getString("date");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// remove from cart table
	public int deleteUserCart(int userid) {
		try {
			String sql = "DELETE FROM cart WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
			
			int deleted = pstmt.executeUpdate();
			
			sql = "ALTER TABLE cart AUTO_INCREMENT=1;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			return deleted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	// delete
	public int deleteCheckout( int checkoutid) {
		try {
			String sql = "DELETE FROM checkout WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, checkoutid);
			
			int deleted = pstmt.executeUpdate();
			
			sql = "ALTER TABLE checkout AUTO_INCREMENT=1;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
			return deleted;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
