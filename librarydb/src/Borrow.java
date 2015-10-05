

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Borrow {



	private int bookID;
	private int customerID;
	private String dayOfBorrowed;
	private String dayOfReturn;


	public Borrow(int bookID, int customerID, String dayOfBorrowed) {
		this.bookID = bookID;
		this.customerID = customerID;
		this.dayOfBorrowed  = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
		
		
	
	}

	public int getBookID() {
		return bookID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getDayOfBorrowed() {
		return dayOfBorrowed;
	}

	public String getDayOfReturn() {
		return dayOfReturn;
	}


}