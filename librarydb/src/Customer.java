

//import the packages for using the classes in them into the program

import java.sql.*;


/***************************************************************************
 ***      declaration of the private variables used in the program       ***
 ***************************************************************************/


public class Customer {


	private int customerID;
	private String password;
	private String firstName;
	private String lastName;
	private String address;
	private String contactPhone;
	private String userType;

	public Customer() {

		this.customerID = -1;
		this.password = "";    
		this.firstName = "";
		this.lastName = "";
		this.address = "";
		this.contactPhone = "";
		this.userType = "";
	}

	public Customer(int customerID, String password,String firstName,String lastName,String address,String contactPhone, String userType){

		this.customerID = customerID;
		this.password = password;    
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contactPhone = contactPhone;
		this.userType = userType;
	}

	public int getcustomerID(){
		return customerID; 
	}

	public void setcustomerID(int customerID){
		this.customerID = customerID;
	}

	public String getpassword(){
		return password; 
	}

	public void setpassword(String password){
		this.password = password;
	}

	public String getfirstName(){
		return firstName; 
	}

	public void setfirstName(String firstName){
		this.firstName = firstName;
	}

	public String getlastName(){
		return lastName; 
	}

	public void setlastName(String lastName){
		this.lastName = lastName;
	}

	public String getaddress(){
		return address; 
	}

	public void setaddress(String address){
		this.address = address;
	}

	public String getcontactPhone(){
		return contactPhone; 
	}

	public void setcontactPhone(String contactPhone){
		this.contactPhone = contactPhone;
	}    

	public String getuserType(){
		return userType; 
	}

	public void setuserType(String userType){
		this.userType = userType;
	}  




}
