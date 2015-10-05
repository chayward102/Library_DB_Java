
import java.util.*;
import java.sql.*;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class BookDao {

	public static String URL = "jdbc:sqlite:DB/library.sqlite";

	public void add(Book book){

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
			String query = "INSERT INTO BOOK (BOOKTITLE,BOOKAUTHOR,DATEADDED,BOOKPRICE,SUPPLIERID)"
					+ " VALUES ("
					//+ "'" + book.getbookID() + "', "
					+ "'" + book.getbookTitle() + "', "
					+ "'" + book.getbookAuthor() + "', "
					+ "'" + book.getdateAdded() + "', "
					+ book.getbookPrice() + ", "
					+ book.getsupplierID()+")";



			stmt.executeUpdate(query);
			System.out.println(query);

			stmt.close();

			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}


	public void delete(String bookID){

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
			//a little strange don't know how to change to supplierid but I guess it works
			String query = "DELETE FROM book where bookID=" + bookID;
			stmt.executeUpdate(query);

			stmt.close();
			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	}

	public void update(Book book){

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
			String query = "Update Book set "
					+ "bookID='" + book.getbookID() + "', "
					+ "booktitle='" + book.getbookTitle() + "', "
					+ "bookAuthor='" + book.getbookAuthor() + "', "
					+ "DateAdded='" + book.getdateAdded() + "', "
					+ "bookPrice=" + book.getbookPrice()
					+ " where bookID=" + book.getbookID();






			System.out.println(query);
			stmt.executeUpdate(query);

			stmt.close();
			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	} 

	public boolean IfExists(Book book) {

		boolean bookExists = false; 
		Connection con;
		Statement stmt;
		String query = "SELECT COUNT(*) FROM Book "+
				"WHERE (bookTitle = " + "'"+book.getbookTitle()+"'" +
				"AND bookAuthor = " + "'"+book.getbookAuthor()+"'" + ")";

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

			int count = rs.getInt(1);
			if (count > 0) {
				stmt.close();
				con.close();
				bookExists = false;
			} else {
				stmt.close();
				con.close();
				bookExists = true;
			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return bookExists;
	}

	//check if the book id exists so that it can be updated so you cannot update a book that does not exist
	public boolean IfExistsUpdate(Book book) {

		boolean bookExists = false; 
		Connection con;
		Statement stmt;
		String query = "SELECT COUNT(*) FROM Book "+
				"WHERE (bookID = " + "'"+book.getbookID()+"'" + ")";

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

			int count = rs.getInt(1);
			if (count > 0) {
				stmt.close();
				con.close();
				bookExists = false;
			} else {
				stmt.close();
				con.close();
				bookExists = true;
			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return bookExists;
	}


	public Book getSingleBook (int bookID) {

		Connection con;
		Statement stmt;
		String query = "SELECT * FROM book WHERE bookID = " + bookID;
		Book book = null;
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

			if (rs.next()) {
				int bookid = rs.getInt("bookID");
				String bookTitle = rs.getString("bookTitle");
				String bookAuthor = rs.getString("bookAuthor");
				String dateAdded = rs.getString("DateAdded");
				double bookPrice = rs.getDouble("bookPrice");
				int supplierID = rs.getInt("supplierID");

				book = new Book(bookid,bookTitle, bookAuthor, dateAdded, bookPrice, supplierID);

			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return book;
	}

	public Vector getAllBook(){

		Vector v = new Vector();

		Connection con;
		Statement stmt;
		String query = "select bookid, booktitle,bookAuthor, dateAdded, bookPrice, supplierid from Book";

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

			while (rs.next()) {
				int bookid = rs.getInt("bookID");
				String bookTitle = rs.getString("bookTitle");
				String bookAuthor = rs.getString("bookAuthor");
				String dateAdded = rs.getString("DateAdded");
				double bookPrice = rs.getDouble("bookPrice");
				int supplierID = rs.getInt("supplierID");

				Book book = new Book(bookid,bookTitle, bookAuthor, dateAdded, bookPrice, supplierID);

				v.add(book);
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return v;       
	}


	/*
        public static void main(String args[]){

        	Book book = new Book(11,"somebook","James","2015-11-05",45,11);
        	BookDao dao = new BookDao();
        	dao.update(book);*/
}



