import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.*;

public class SearchDao {
		
	public static final String URL = "jdbc:sqlite:DB/library.sqlite";
	
	public Vector<Book> searchBook(String category, String keyword){
        String column = "";
        if (category.equals("Title")) {
        	column = "bookTitle";
        } else if (category.equals("Author")) {
        	column = "bookAuthor";
        } else if (category.equals("BookID")) {
        	column = "bookID";
        }
        Vector<Book> v = new Vector<Book>();

        Connection con;
        Statement stmt;
        String query = "SELECT * FROM book WHERE " + column + " LIKE " + "'%" + keyword + "%'";

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
                		int bookID = rs.getInt("bookID");
                        String bookTitle = rs.getString("bookTitle");
                		String bookAuthor = rs.getString("bookAuthor");
                		String dateAdded = rs.getString("DateAdded");
                        double bookPrice = rs.getDouble("bookPrice");
                        int supplierID = rs.getInt("supplierID");
                        
                        Book book = new Book(bookID,bookTitle, bookAuthor, dateAdded, bookPrice, supplierID);
                        v.add(book);
                        System.out.println(bookID + "\t" + bookTitle + "\t\t" + bookAuthor);
                }

                stmt.close();
                con.close();

        } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
        }
        
        return v;       
	}
	
	public Vector<CustomerBorrowed> getBooksBorrowed(int customerID){
        
        Vector<CustomerBorrowed> v = new Vector<CustomerBorrowed>();

        Connection con;
        Statement stmt;
        String query = "SELECT borrow.bookID, bookTitle, bookAuthor, borrow.dateAdded, fine " +
        			"FROM borrow, book " +
        			"WHERE borrow.bookID = book.bookID " + 
        			"AND borrow.customerID = "+ customerID +
        			" AND (dateReturned = '' OR dateReturned IS NULL)";

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
                BorrowDao dao = new BorrowDao();
                
                while (rs.next()) {
                		int bookID = rs.getInt("bookID");
                        String bookTitle = rs.getString("bookTitle");
                		String bookAuthor = rs.getString("bookAuthor");
                		String dateBorrowed = rs.getString("dateAdded");
                        int bookFine = rs.getInt("fine");
                        int daysBorrowed = dao.daysOut(bookID, customerID);
                        int daysOverdue = dao.daysOverdue(daysBorrowed);
                        
                        CustomerBorrowed cb = new CustomerBorrowed(bookID, bookTitle, bookAuthor, dateBorrowed, daysBorrowed, daysOverdue, bookFine);
                        v.add(cb);
                        System.out.println(bookID + "\t" + bookTitle + "\t\t" + bookAuthor + "\t\t" + dateBorrowed 
                        					+ "\t\t" + daysBorrowed + "\t\t" + daysOverdue + "\t\t" + bookFine);
                }

                stmt.close();
                con.close();

        } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
        }
        
        return v;       
	}
	
	/**
	 * 
	 * The following does not check the validity of the ID, only that it exists
	 * I have therefore changed the method name to reflect this
	 * 
	 * Sherman
	 */
	public boolean customerIDexists(int customerID){
		
		Connection con;
        Statement stmt;
        //String query = "SELECT CustomerID FROM CUSTOMER WHERE CUSTOMERID= " +customerID;
        //String query = "SELECT * FROM CUSToMER WHERE customerID = 1234";
        String query = "SELECT * FROM CUSToMER WHERE customerID = "+customerID; // we should not use LIKE to check ID's
        
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

                	 if (rs.next()){
                		 System.out.println("customerID in");
                		 stmt.close();
                         con.close();
                	return true;}

                stmt.close();
                con.close();
                
        } catch (SQLException ex) {
                System.err.println("SQLException: " + ex.getMessage());
        }
        System.out.println("customerID out");
		return false;    
	}

		// TODO Auto-generated method stub
		
	
	
	/*public static void main(String args[]){
		int customerID = 1234;
		SearchDao dao = new SearchDao();
		 dao.checkCustomerIDValid(customerID);	*/ 
	/**
	 * 
	 * The following does not check the validity of the ID, only that it exists
	 * I have therefore changed the method name to reflect this
	 * 
	 * Sherman
	 */	
		public boolean bookIDExists(int bookid)	{
			
			Connection con;
	        Statement stmt;
	        //String query = "SELECT CustomerID FROM CUSTOMER WHERE CUSTOMERID= " +customerID;
	        //String query = "SELECT * FROM CUSToMER WHERE customerID = 1234";
	        String query = "SELECT * FROM book WHERE bookid = "+bookid; // we should not use LIKE to check ID's
	        
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
	                
	                	
	                
	                	 if (rs.next()){
	                		 System.out.println("bookid in");
	                		 stmt.close();
	                         con.close();
	                	return true;}

	                stmt.close();
	                con.close();
	                
	        } catch (SQLException ex) {
	                System.err.println("SQLException: " + ex.getMessage());
	        }
	        System.out.println("bookid out");
			return false;
			
		}
		
		 
		/*public static void main(String args[]){
			int bookid = 11;
			SearchDao dao = new SearchDao();
			 dao.checkBookIDValid(bookid);
		public static void main(String[] args) {
			SearchDao dao = new SearchDao();
			dao.getBooksBorrowed(1235);
		}*/
	}






