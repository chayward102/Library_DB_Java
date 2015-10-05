
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

public class LoginDao {
	public static final String USER_TYPE_CUSTOMER = "C";
	public static final String USER_TYPE_RECEPTIONIST = "R";
	public static final String USER_TYPE_ADMIN = "A";

	public static String URL = "jdbc:sqlite:DB/library.sqlite";
	
	/**
	 * 
	 * @param customerID
	 * @param password
	 * @return
	 * 
	 * The following method checks for a valid username, password and user type in the customer table.
	 * If found, insert a record into the "login" table, and returns true.
	 * Otherwise the method returns false.
	 */
	public boolean login(int customerID, String password, String userType){

		Connection con;

		try {
			Class.forName("org.sqlite.JDBC");

		} catch (java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {

			con = DriverManager.getConnection(URL);
			Statement selectStmt = con.createStatement();
			String checkPass = "SELECT * FROM customer WHERE " +
								"(customerID=" + customerID +
								" AND password='" + password + "'" +
								" AND userType='" + userType + "')";
			
			System.out.println(checkPass);
			ResultSet rs = selectStmt.executeQuery(checkPass);

			if (rs.next()) {
				// rs.next();
				Statement insertStmt = con.createStatement();
				System.out.println(userType);
				String loginDateTime  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new java.util.Date());
				String insertLogin = "INSERT INTO 'login' (customerID, loginDateTime) " +
										"VALUES (" + customerID + ",'" + loginDateTime +"')";
				
				System.out.println(insertLogin);
				insertStmt.executeUpdate(insertLogin);
				
				selectStmt.close();
				insertStmt.close();
				con.close();
				return true;
			}
			selectStmt.close();
			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return false;

	}
	
	public void logout(int customerID) {
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
			String logoutDateTime  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new java.util.Date());
			String query = "UPDATE login "+
							"SET logoutDateTime='"+logoutDateTime+"' "+
							"WHERE customerID="+customerID+" AND loginDateTime = "+
																				"(SELECT MAX(loginDateTime) "+
																				"FROM login "+
																				"WHERE customerID="+customerID+")";
			System.out.println(query);
			
			stmt.executeUpdate(query);
			stmt.close();
			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}public String lastSessionDuration(int customerID) {
		final int DAYS = 86400;
		final int HOURS = 3600;
		final int MINUTES = 60;
		
		int d,h,m,s;
		
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
			
			String query = "SELECT strftime('%s',logoutDateTime) - strftime('%s',loginDateTime) AS sessionDuration " +
							"FROM login "+
							"WHERE customerID="+customerID+" "+
							"AND (logoutDateTime NOT NULL OR logoutDateTime != '') "+
							"AND loginDateTime = (SELECT MAX(loginDateTime) FROM login WHERE customerID="+customerID+")";
			System.out.println(query);
			
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()){
				int numOfSeconds = rs.getInt("sessionDuration");
				stmt.close();
				con.close();
				
				d = numOfSeconds/DAYS;
				h = (numOfSeconds%DAYS)/HOURS;
				m = ((numOfSeconds%DAYS)%HOURS)/MINUTES;
				s = ((numOfSeconds%DAYS)%HOURS)%MINUTES;
				
				String sessionTime = d+" days  "+h+"h:"+m+"m:"+s+"s";
				System.out.println(sessionTime);
				return sessionTime;
			}
			stmt.close();
			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return null;
	}

/*
	public static void main (String[] args) {
		int custID = 26;
		String pass = "password";
		LoginDao dao = new LoginDao();
		boolean testResult = dao.login(custID, pass, USER_TYPE_CUSTOMER);
		System.out.println(testResult);
	}
	*/
	/*
	public static void main (String[] args) {
		LoginDao dao = new LoginDao();
		//dao.logout(100);
		dao.lastSessionDuration(100);
	}
	*/
}

