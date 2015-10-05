
import java.util.*;
import java.sql.*;

import javax.swing.JOptionPane;

public class BorrowDao {

	public static String URL = "jdbc:sqlite:DB/library.sqlite";
	
	
//This method finds the max(dateAdded) this is needed for finding pirmary key in Borrow table since PK = 
//dateAdded, bookid, customerid
	
	public String maxDateBorrowed(int bookid,int customerid){
		Connection con;
		String MaxDate="";
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(URL);
			Statement stmt = con.createStatement();
			String query = "SELECT MAX(dateAdded) FROM borrow WHERE bookID="+bookid+" AND customerid= "+customerid;
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			if (rs.next()) {
				MaxDate = rs.getString("MAX(dateAdded)");
				System.out.println(MaxDate);
				
			}
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}



		return MaxDate;

	}
	//This method will add fine 60cents per day overdue to fine column in borrow table if book has been
	//borrowed for between 20 and 90 days
	public void addFine( int bookid,int customerid){
		int days=0;

		Connection con;
		Statement stmt;

		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			days=daysOut(bookid,customerid);
			String maxDate= maxDateBorrowed(bookid, customerid);
			con = DriverManager.getConnection(URL);
			stmt = con.createStatement();
			if (days>20 && days<90){
				double fine=0;
				days=days-20;
				fine=days*0.6;
				String query = "Update borrow Set fine = "+ fine + " where bookid =" +bookid+ " AND customerid="+customerid+" AND dateAdded = '"+maxDate+"'" ;
				System.out.println(query);
				stmt.executeUpdate(query);
				stmt.close();
				con.close();
			}
			

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	}
	
	public void calcAllFines() {
		Connection con;
		Statement stmt;
		String query = "SELECT * FROM borrow "+
						"WHERE fineCleared = 0";
		int[] idArray;
		ArrayList<int[]> idAL = new ArrayList<int[]>();
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(URL);
			stmt = con.createStatement();
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				idArray = new int[2];
				idArray[0] = rs.getInt(1);
				idArray[1] = rs.getInt(2);
				idAL.add(idArray);
			}
			stmt.close();
			con.close();
			for (int i=0; i < idAL.size(); i++) {
				this.addFine(idAL.get(i)[0], idAL.get(i)[1]);
			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	//If days borrowed is over 90 then this method will add to the book column a 1 
	public int isLost( int bookid,int customerid){

		int days=0;
		Connection con;
		Statement stmt;
		String query = "SELECT julianday(datereturned) - julianday(dateadded) "
				+ "return_added_subtraction FROM borrow WHERE bookid = "+bookid 
				+" AND CustomerID ="+customerid;

		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {

			con = DriverManager.getConnection(URL);

			stmt = con.createStatement();
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				days = rs.getInt("return_added_subtraction");
				System.out.println(days);

			}




			if (days>90){
				stmt = con.createStatement();
				String queryOne = "Update book set bookStatus=1 WHERE bookID=" +bookid;
				stmt.executeUpdate(queryOne);
				System.out.println(queryOne);
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return 0;       
	}
	/**
	 * Calculates and returns the number of days a book has been, or was, borrowed.
	 * If the book has not yet been returned, the current day will be used.
	 */
	public int daysOut( int bookid,int customerid){
		String maxDate= maxDateBorrowed( bookid, customerid);
		int days =0;
		Connection con;
		Statement stmt;
		String query = "SELECT CASE WHEN (dateReturned IS NULL OR dateReturned ='') " 
				+ "THEN julianday('now') - julianday(dateAdded) "
				+ "ELSE julianday(datereturned) - julianday(dateadded) END "
				+ "return_added_subtraction FROM borrow WHERE bookid = "+bookid 
				+" AND CustomerID ="+customerid
				+" AND dateAdded='" +maxDate+"'";

		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(URL);
			stmt = con.createStatement();
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				days = rs.getInt("return_added_subtraction");
				System.out.println(days);
			}
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return days;       
	}
	
	/**
	 * This evaluates if book is overdue will return 0 if borrow length is less than 20 days
	 * Else if borrow date is greater than 20 days will return the number of days over 20 days
	 */
	public int daysOverdue(int days) {
		if (days>20){
			return days-20;
		} else {
			return 0;
		}
	}

	//Borrows a book object
	public void borrow(Borrow borrow){

		Connection con;

		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {

			con = DriverManager.getConnection(URL);
			Statement stmt = con.createStatement();
			String query = "INSERT INTO BORROW (BOOKID,CUSTOMERID,dateAdded)"
					+ "VALUES ("
					+  borrow.getBookID() + ", "
					+  borrow.getCustomerID() +", "
					+ "'" + borrow.getDayOfBorrowed() + "')";


			System.out.println(query);

			stmt.executeUpdate(query);


			stmt.close();

			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			String exception = ex.getMessage();
			JOptionPane.showMessageDialog(null, "Please contact administrator for assistance \n"+exception, "Warning", JOptionPane.WARNING_MESSAGE);
		}

	}


	public void returnBook(int customerid, int bookid, String returnDate){

		Connection con;
		
		String maxDate= maxDateBorrowed( bookid, customerid);
		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {

			con = DriverManager.getConnection(URL);
			Statement stmt = con.createStatement();
			String query = "Update borrow set "
					+ "dateReturned='" + returnDate + "' "
					+ " where bookID=" + bookid +  ""
					+ " AND customerID=" + customerid + ""
					+" AND dateAdded='" +maxDate+"'";
			//remember to set condition that date returned can only be updated
			//only if it is null 

			System.out.println(query);

			stmt.executeUpdate(query);


			stmt.close();

			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	}


	//public boolean IsOverFive()
	/*public static void main(String args[])
        {
        	int customerid = 1238;
        	boolean t;

        	BorrowDao dao = new BorrowDao();
        	t =dao.checkIfSixBooksOut(customerid);
        }
        /
	 * */

	//checks if more than six books are out
	public boolean checkIfSixBooksOut(int customerid){

		Connection con;

		Statement stmt;
		String query = "SELECT Count("+customerid+") as customer FROM Borrow WHERE"
				+ " customerid= " + customerid + " "
				+ " AND dateReturned is null";

		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {

			con = DriverManager.getConnection(URL);
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			System.out.println(query);

			int id = rs.getInt("customer");
			System.out.println(id);
			if (id>6){
				stmt.close();
				con.close();
				return false;
			}
			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		System.out.print("no chance to print");
		return true;

	}

	public static void main(String args[]){

		BorrowDao dao = new BorrowDao();
		System.out.println(dao.daysOut(8,1235));
		
		//expected result: 1 day and false === correct!!
		//System.out.println(dao.isBookOver20Days(1,1235));
		//expected result: 31 day and true === correct!!
	}
}

