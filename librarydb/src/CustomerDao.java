
import java.util.*;
import java.sql.*;

public class CustomerDao {

	public static String URL = "jdbc:sqlite:DB/library.sqlite";

	public void add(Customer customer){

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
			String query = "INSERT INTO CUSTOMER (PASSWORD,FIRSTNAME,LASTNAME,ADDRESS,CONTACTPHONE,USERTYPE)"
					+ "VALUES ("
					+ "'" + customer.getpassword() + "', "
					+ "'" + customer.getfirstName() + "', "
					+ "'" + customer.getlastName() + "', "
					+ "'" + customer.getaddress() + "', "
					+ "'" + customer.getcontactPhone() + "', "
					+ "'" + customer.getuserType() +"')";


			stmt.executeUpdate(query);
			System.out.println(query);

			stmt.close();

			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
	/**
	 * Checks if a customer already exists in the database. If there is a duplicate match,
	 * returns true, otherwise the method returns false
	 * @param customer
	 * @return
	 */
	public boolean checkDuplicate (Customer customer){

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
			String query = "SELECT * FROM customer " +
					"WHERE firstName = '" + customer.getfirstName() + "' " +
					"AND lastName = '" + customer.getlastName() + "' "+
					"AND address = '" + customer.getaddress() + "' "+
					"AND contactPhone = " + customer.getcontactPhone();

			ResultSet rs = stmt.executeQuery(query);
			System.out.println(query);
			if (rs.next()) {
				stmt.close();
				con.close();
				return true;
			}
			
			stmt.close();
			con.close();
			


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return false;
	}

	public void delete(String customerID){

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
			String query = "DELETE FROM customer WHERE customerID=" + customerID;
			stmt.executeUpdate(query);

			stmt.close();
			con.close();


		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}

	}

	/* public static void main(String args[])
        {
        	Customer customer = new Customer(123,"fdfd","dfd","df","df","5454","C");
        	CustomerDao dao = new CustomerDao();
        	dao.add(customer);

	 */
}


