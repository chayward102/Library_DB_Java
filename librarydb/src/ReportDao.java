import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.util.*;

public class ReportDao {
	
	/**
	 * Set the following flags for methods to use
	 */
	public static final int DAILY_REPORT = 1;
	public static final int MONTHLY_REPORT = 2;
	public static final int ANNUAL_REPORT = 3;
	public static final int LATE_RETURN = 4;
	public static final int LOST_BOOK = 5;

	public static final String URL = "jdbc:sqlite:DB/library.sqlite";
	
	/**
	 * This method returns a basic lending report, which can either be daily, monthly, or annually
	 * depending on the flag provided.
	 * it returns an ArrayList of String arrays. 
	 * 
	 * @param reportType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public ArrayList<String[]> lendingReport(int reportType, String startDate, String endDate) {
		ArrayList<String[]> al = new ArrayList<String[]>();

		Connection con;
		Statement stmt;
		String query = "";
		switch(reportType) {
		case DAILY_REPORT:		query = "SELECT dateAdded AS dayMonthYear, COUNT(*) AS booksBorrowed "+
				"FROM borrow "+
				"WHERE dateAdded BETWEEN '"+startDate+"' AND '"+endDate+"' "+
				"GROUP BY dayMonthYear";
		break;
		case MONTHLY_REPORT:	query = "SELECT strftime('%m', dateAdded) AS dayMonthYear, "+
				"count(*) AS booksBorrowed "+
				"FROM borrow "+
				"WHERE dateAdded BETWEEN '"+startDate+"' AND '"+endDate+"' "+
				"GROUP BY dayMonthYear";
		break;
		case ANNUAL_REPORT:		query = "SELECT strftime('%Y', dateAdded) AS dayMonthYear, "+
				"count(*) AS booksBorrowed "+
				"FROM borrow "+
				"WHERE dateAdded BETWEEN '"+startDate+"' AND '"+endDate+"' "+
				"GROUP BY dayMonthYear";
		break;
		}


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
			String[] result;

			while (rs.next()) {
				result = new String[2];
				if (reportType == MONTHLY_REPORT) {
					int month = rs.getInt("dayMonthYear");
					String monthString = new DateFormatSymbols().getMonths()[month-1];
					result[0] = monthString;
				} else {
					result[0] = rs.getString("dayMonthYear");
				}
				result[1] = rs.getString("booksBorrowed");

				al.add(result);
				System.out.println(result[0] + "\t\t" + result[1]);
			}

			stmt.close();
			con.close();

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return al;
	}
	
	/**
	 * This method provides a basic fine report.
	 * It either provides a report of what customers have outstanding fines, and the amount of fines still owing.
	 * Alternatively, it provides a report of which customers have lost a book, and how many they have lost.
	 * 
	 * @param reportType
	 * @return
	 */
	public ArrayList<String[]> fineReport(int reportType) {
		ArrayList<String[]> al = new ArrayList<String[]>();

		Connection con;
		Statement stmt;
		String query = "";
		switch(reportType) {
		case LATE_RETURN:	query = "SELECT b.customerID, firstName, lastname, SUM(fine) AS outstandingFine "+
				"FROM borrow AS b, customer AS c "+
				"WHERE b.customerID = c.customerID "+
				"AND fineCleared = 0 "+
				"GROUP BY b.customerID "+
				"ORDER BY outstandingFine DESC";
		break;
		case LOST_BOOK:		query = "SELECT br.bookID, bookTitle, br.customerID, firstName, lastName, COUNT(*) AS booksLost "+
				"FROM borrow AS br, book AS b, customer AS c "+
				"WHERE br.bookID = b.bookID "+
				"AND br.customerID = c.customerID "+
				"AND dateReturned = '9999-12-31' "+
				"GROUP BY br.customerID "+
				"ORDER BY booksLost DESC";
		break;
		}
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

			if (reportType == LATE_RETURN) {
				String[] result = new String[4];
				while (rs.next()) {
					for (int i = 0; i < result.length; i++) {
						result[i] = rs.getString(i+1);
						System.out.print(result[i] + "\t\t");
					}
					System.out.print("\n");
					al.add(result);
				}
				stmt.close();
				con.close();
			} else {
				String[] result = new String[6];
				while (rs.next()) {
					for (int i = 0; i < result.length; i++) {
						result[i] = rs.getString(i+1);
						System.out.print(result[i] + "\t\t");
					}
					System.out.print("\n");
					al.add(result);
				}
				stmt.close();
				con.close();
			}

		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return al;
	}
	
	public ArrayList<String[]> customerReport(int customerID) {
		ArrayList<String[]> al = new ArrayList<String[]>();

		Connection con;
		Statement stmt;
		String query = "SELECT bookTitle, bookAuthor, br.dateAdded, fine, br.bookID "+
						"FROM borrow AS br, book AS b "+
						"WHERE br.bookID = b.bookID "+
						"AND customerID = "+customerID+" "+
						"AND (dateReturned IS NULL OR dateReturned = '')";
		
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
			BorrowDao bDao = new BorrowDao();
			while(rs.next()) {
				String[] result = new String[6];
				int bookID = rs.getInt(5);
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = Integer.toString(bDao.daysOverdue(bDao.daysOut(bookID, customerID)));
				result[4] = rs.getString(4);
				result[5] = Integer.toString(bookID);
				al.add(result);
			}
			stmt.close();
			con.close();
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

		return al;
	}
	
	/*
	public static void main(String[] args){
		ReportDao dao = new ReportDao();
		dao.lendingReport(ANNUAL_REPORT, "2015-04-01", "2015-06-03");
	}*/
	/*
	public static void main(String[] args){
		ReportDao dao = new ReportDao();
		dao.fineReport(LOST_BOOK);
	}*/
	/*
	public static void main(String[] args){
		ReportDao dao = new ReportDao();
		
		ArrayList<String[]> al = dao.lendingReport(DAILY_REPORT, "2015-04-01", "2015-06-03");
		for (int i = 0; i < al.size(); i++) {
			String[] a = new String[2];
			a = al.get(i);
			System.out.println(a[0]);
			System.out.println(a[1]);
		}
	}*/
}
