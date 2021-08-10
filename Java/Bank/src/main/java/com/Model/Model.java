package com.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;

public class Model {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet res;
	
	// private variables
	private int id;
	private String name;
	private String email;
	private String username;
	private String password;
	private String account;
	private double balance;
	
	public Model(){
		connect();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	// connect
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
		}
		catch( Exception e) {
			e.printStackTrace();
		}
	}
	
	// insert
	public int register() {
		try {
			String sql = "INSERT INTO user (name, email, username, password, account, balance) VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, username);
			pstmt.setString(4, password);
			pstmt.setString(5, account);
			pstmt.setString(6, Double.toString(balance));
			
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
			pstmt.setString(1, username);
			pstmt.setString(2, password);
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
	public boolean getUser() {
		try {
			String sql = "SELECT * FROM user WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, Integer.toString(id));
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
	public boolean getUserByAccount() {
		try {
			String sql = "SELECT * FROM user WHERE account = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account);
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
	public boolean getUserByEmail() {
		try {
			String sql = "SELECT * FROM user WHERE email = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
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
	public ArrayList<Integer> getAccounts() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		try {
			String sql = "SELECT id, name, account FROM user WHERE NOT account = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account);
			res = pstmt.executeQuery();
			while(res.next() == true ) {
				id = Integer.parseInt(res.getString("id")); // to get details
				ids.add(id);
			}
			
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	// fetch *
	private void getAllStrings(ResultSet res) {
		try {
			id = Integer.parseInt(res.getString("id"));
			name = res.getString("name");
			email = res.getString("email");
			username  = res.getString("username");
			password = res.getString("password");
			account  = res.getString("account");
			balance = Double.parseDouble(res.getString("balance"));
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
				pstmt.setString(1, name);
			}
			else if( colName.equals("email")) {
				pstmt.setString(1, email);
			}
			else if( colName.equals("account")) {
				pstmt.setString(1, account);
			}
			else if( colName.equals("balance")) {
				pstmt.setString(1, Double.toString(balance));
			}
			else if( colName.equals("username")) {
				pstmt.setString(1, username);
			}
			else if( colName.equals("password")) {
				pstmt.setString(1, password);
			}
			pstmt.setString(2, Integer.toString(id));
			
			int updated = pstmt.executeUpdate();
			return updated;
		}
		catch( Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
