package com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;




public class Payment {
	//create connection method
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
}
	//validate the date format,it should be dd-MM-yyyy
	public static boolean valDate(String date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		
		
		df.setLenient(false);
		
		try {
			
			df.parse(date.trim());
			
			
			
			
			
			
		}catch(Exception e) {
			return false;
		}
		return true;
		
		
	}
	public static boolean validateNIC(String NIC) {
		//check if length is 10
		int length = NIC.length();
		if(length != 10) {
			return false;
		}
		
		//check last character for v
		char lastChar = NIC.charAt(length-1);
		if(lastChar != 'V' || lastChar != 'v') {
			return false;
		}
		//check first 9 characters are digits
		for(int i=0;i<length-2;i++) {
			char currentChar = NIC.charAt(i);
			if(currentChar<'0' || '9' <currentChar) {
				return false;
			}
		}
		return true;
	}
	
	//insert method
	public String insertPayment(String NIC, String productID, String creditNumber, String cvv,String expireDate,String date,String amount) {
		String output = "";
		try
		 {
		 Connection con = connect();
		 if (con == null)
		 {
		 return "Error while connecting to the database";
		 }
		 
		 // create a prepared statement
		 String query = " insert into payments (paymentID,NIC,productID,creditNumber,cvv,expireDate,date,amount)" + " values (?, ?, ?, ?, ?,?,?,?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, NIC);
		 preparedStmt.setString(3, productID);
		 preparedStmt.setString(4, creditNumber); 
		 preparedStmt.setString(5, cvv); 
		 preparedStmt.setString(6, expireDate);
		 preparedStmt.setString(7, date); 
		 preparedStmt.setDouble(8, Double.parseDouble(amount));
		
		 
		//execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		catch (Exception e)
		 {
		 output = "Error while inserting";
		 System.err.println(e.getMessage());
		 }
		return output;
	}
		
		 	
	//get payment details according to the NIC
	public String readPayment()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the html table to be displayed
	 output = "<table border=\"1\"><tr><th>NIC</th><th>Product ID</th><th>Credit Number</th><th>cvv</th><th>ExpireDate</th><th>Date</th><th>Amount</th><th>Update</th><th>Remove</th></tr>";
	 String query = "select * from payments";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String paymentID = Integer.toString(rs.getInt("paymentID"));
	 String NIC = rs.getString("NIC");
	 String productID = rs.getString("productID");
	 String creditNumber= rs.getString("creditNumber");
	 String cvv = rs.getString("cvv");
	 String expireDate = rs.getString("expireDate");
	 String date = rs.getString("date");
	 String amount= Double.toString(rs.getDouble("amount"));
	 
	 // Add into the html table
	 output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + paymentID + "'>"+ NIC + "</td>";
	 output += "<td>" + productID + "</td>";
	 output += "<td>" + creditNumber + "</td>";
	 output += "<td>" + cvv + "</td>";
	 output += "<td>" + expireDate+ "</td>";
	 output += "<td>" + date + "</td>";
	 output += "<td>" + amount + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate'type='button' value='Update'class=' btnUpdate btn btn-secondary'></td><td><form method='post' action='payments.jsp'><input name='btnRemove' type='submit'value='Remove' class='btn btn-danger'><input name='hidItemIDDelete' type='hidden' value='" + paymentID + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 } 
	
	catch (Exception e)
	 {
	 output = "Error while reading the items.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	//update payment details
public String updatePayment(String paymentID,String NIC, String productID, String creditNumber, String cvv,String expireDate,String date,String amount) {
		
		String output = "";
		try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database";
			 }
			 
			// create a prepared statement
			 String query = "update payments set NIC=?, productID=?, creditNumber=?, cvv=? , expireDate=?,date=?,amount=? where paymentID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 preparedStmt.setString(1,NIC);
			 preparedStmt.setString(2, productID);
			 preparedStmt.setString(3, creditNumber);
			 preparedStmt.setString(4, cvv);
			 preparedStmt.setString(5, expireDate);
			 preparedStmt.setString(6, date);
			 preparedStmt.setDouble(7, Double.parseDouble(amount));
			 
			 
			 preparedStmt.setInt(8, Integer.parseInt(paymentID));
			 
			 
			 //execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated successfully";
			 
			 }
		catch (Exception e)
		 {
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		return output;
	}
	public String deletePayment(String paymentID)
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for deleting.";
	}
	// create a prepared statement
	String query = "delete from payments where paymentID=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(paymentID));
	// execute the statement
	preparedStmt.execute();
	con.close();
	output = "Deleted successfully";
	}
	catch (Exception e)
	{
	output = "Error while deleting the item.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	}
	
	
	 

