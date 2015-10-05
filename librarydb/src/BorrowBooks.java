//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JTextField;

/**
 *A public class
 */
public class BorrowBooks extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	private JLabel title = new JLabel("BOOK INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel informationPanel = new JPanel();
	//for creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[3];
	//for creating an array of String
	private String[] informationString = {" Book ID:", " Customer ID:",
	                                      " Current Data:", " Return Date:"};
	//for creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[4];
	//for creating the date in the String
	private String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
	//for creating an array of string to store the data
	private String[] data;

	//for creating an Internal Panel in the center panel
	private JPanel borrowButtonPanel = new JPanel();
	//for creating the button
	private JButton borrowButton = new JButton("Borrow");

	//for creating South Panel
	private JPanel southPanel = new JPanel();
	//for creating the button
	private JButton cancelButton = new JButton("Cancel");

	//for creating an object
	private Book book;
	private Customer member;
	private Borrow borrow;
    private String id;
	
	//for checking that the customerID is in the customer ID column of table customer
	public boolean isValidCustomerID(){
		
		int customerID = Integer.parseInt(data[1]);
		SearchDao dao = new SearchDao();
		return dao.customerIDexists(customerID);
		
		 
				
	}
	//for checking that the customerID is in the customer ID column of table customer
	public boolean isValidBookID(){
		
		int bookid = Integer.parseInt(data[0]);
		SearchDao dao = new SearchDao();
		return dao.bookIDExists(bookid);
	}
	//public boolean isValidBookID(){
		//if bookID in book table return true;
		//else return false
	//}
	public boolean isSixBooksOut(){
		
		int customerID = Integer.parseInt(data[1]);
		BorrowDao dao = new BorrowDao();
		return dao.checkIfSixBooksOut(customerID);
		
	}
	//for checking the information from the text field
	public boolean isCorrect() {
		data = new String[4];
		for (int i = 0; i < informationLabel.length; i++) {
			if (!informationTextField[i].getText().equals(""))
				data[i] = informationTextField[i].getText();
				
			else
				return false;
		}
		return true;
	}
	
	 private boolean test(String text) {
	      try {
	         Integer.parseInt(text);
	         return true;
	      } catch (NumberFormatException e) {
	         return false;
	      }
	   }
	

	//for setting the array of JTextField to null
	public void clearTextField() {
		for (int i = 0; i < informationTextField.length; i++)
			if (i != 2)
				informationTextField[i].setText(null);
	}

	//constructor of borrowBooks
	public BorrowBooks() {
		//for setting the title for the internal frame
		super("Borrow Books", false, true, false, true);
		
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//for adding the label to the panel
		northPanel.add(title);
		//for adding the panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the layout for the internal panel
		informationPanel.setLayout(new GridLayout(4, 2, 1, 1));

		/***********************************************************************
		 * for adding the strings to the labels, for setting the font 		   *
		 * and adding these labels to the panel.							   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {
			informationPanel.add(informationLabel[i] = new JLabel(informationString[i]));
	
			if (i == 2) {
				informationPanel.add(informationTextField[i] = new JTextField(date));

				informationTextField[i].setEnabled(false);
			}
			else {
				informationPanel.add(informationTextField[i] = new JTextField());

			}
		}
		centerPanel.add("Center", informationPanel);

		//for setting the layout
		borrowButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//for adding the button to the panel
		borrowButtonPanel.add(borrowButton);
		//for adding the panel to the center panel
		centerPanel.add("South", borrowButtonPanel);
		//for setting the border to the panel
		centerPanel.setBorder(BorderFactory.createTitledBorder("Borrow a book:"));
		//for adding the panel to the container
		cp.add("Center", centerPanel);

		//for adding the layout
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		//for adding the button to the panel
		southPanel.add(cancelButton);
		//for setting the border to the panel
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		//for adding the panel to the container
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database,   *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				// also checks if customer id is in table
				//also checks if book id is in table
				
				//if (isValidCustomerID()) returns true if customerId in customer table
				//if (isValidBookID()) returns true if bookid in book table
				// My problem is if I put them with && to is correct() then i get an error message from 
				//SQLite saying database is locked which means I am doing too much with the data base at once
				
				/*conditions to borrow a book
				 * Must be a valid customerID
				 * Must be a valid bookID
				 * Customer must not have 6 books out already
				 */
			
				if (isCorrect()) {
					
					if  (test(data[0])==true)
						System.out.println("int");
					else
						JOptionPane.showMessageDialog(borrowButton, "Incorrect bookID. Please use the search bar to view available books", "Warning", JOptionPane.WARNING_MESSAGE);
					
					if  (test(data[1])==true)
						System.out.println("int");
					else
						JOptionPane.showMessageDialog(borrowButton, "Incorrect customerID. Please see receptionist if you have forgotten your customerID", "Warning", JOptionPane.WARNING_MESSAGE);
					 
					
					int bookID = Integer.parseInt(data[0]);
					int customerID = Integer.parseInt(data[1]);
					
					boolean isValidBookID = isValidBookID ();
					boolean isValidCustID = isValidCustomerID ();
					boolean issixBooksOut = isSixBooksOut();
					
					 
					
					if (isValidBookID && isValidCustID && issixBooksOut) {
						Borrow borrow = new Borrow(bookID,customerID, date);
			        	BorrowDao dao = new BorrowDao();
			        	dao.borrow(borrow);
			        	
			        	//JOptionPane.showMessageDialog(borrowButton, "You have gotten out book number: "+bookID+"! You have 20 days from todays date: "+date+". Before you start to incur fines" );
					} else {
						JOptionPane.showMessageDialog(borrowButton, "Customer ID and/or Book ID not correct!");
					}
				}
				//if there is a missing data, then display Message Dialog
				else
					JOptionPane.showMessageDialog(borrowButton, "Please fill all fields with valid infomation", "Warning", JOptionPane.WARNING_MESSAGE);
					
			}
		});
		//for adding the action listener for the button to dispose the frame
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		//for setting the visible to true
		setVisible(true);
		//show the internal frame
		pack();
	}
	
	
	public BorrowBooks(Integer id) {
        //for setting the title for the internal frame
        super("Borrow Books", false, true, false, true);
        JFrame frame1 = new JFrame();
        //for getting the graphical user interface components display area
        Container cp = getContentPane();
System.out.println("HI I AM IN BOrRWBOOKS(ID)..SELECTED ID Is " + id);
        //for setting the layout
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //for adding the label to the panel
        northPanel.add(title);
        //for adding the panel to the container
        cp.add("North", northPanel);

        //for setting the layout
        centerPanel.setLayout(new BorderLayout());
        //for setting the layout for the internal panel
        informationPanel.setLayout(new GridLayout(4, 2, 1, 1));

        /***********************************************************************
         * for adding the strings to the labels, for setting the font          *
         * and adding these labels to the panel.                               *
         * finally adding the panel to the container                           *
         ***********************************************************************/
        
            informationPanel.add(informationLabel[0] = new JLabel(informationString[0]));
            informationPanel.add(informationTextField[0] = new JTextField(id.toString()));
            informationPanel.add(informationLabel[1] = new JLabel(informationString[1]));
            informationPanel.add(informationTextField[1] = new JTextField());
            informationPanel.add(informationLabel[2] = new JLabel(informationString[2]));
           
                informationPanel.add(informationTextField[2] = new JTextField(date));

                informationTextField[2].setEnabled(false);
           
        centerPanel.add("Center", informationPanel);

        //for setting the layout
        borrowButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //for adding the button to the panel
        borrowButtonPanel.add(borrowButton);
        //for adding the panel to the center panel
        centerPanel.add("South", borrowButtonPanel);
        //for setting the border to the panel
        centerPanel.setBorder(BorderFactory.createTitledBorder("Borrow a book:"));
        //for adding the panel to the container
        cp.add("Center", centerPanel);

        //for adding the layout
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //for adding the button to the panel
        southPanel.add(cancelButton);
        //for setting the border to the panel
        southPanel.setBorder(BorderFactory.createEtchedBorder());
        //for adding the panel to the container
        cp.add("South", southPanel);
        
        
frame1.add(cp);
frame1.setLocationRelativeTo(null);

frame1.setVisible(true);
frame1.pack();
        /***********************************************************************
         * for adding the action listener to the button,first the text will be *
         * taken from the JTextField[] and make the connection for database,   *
         * after that update the table in the database with the new value      *
         ***********************************************************************/
        borrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //for checking if there is a missing information
                // also checks if customer id is in table
                //also checks if book id is in table
                
                //if (isValidCustomerID()) returns true if customerId in customer table
                //if (isValidBookID()) returns true if bookid in book table
                // My problem is if I put them with && to is correct() then i get an error message from 
                //SQLite saying database is locked which means I am doing too much with the data base at once
                
                /*conditions to borrow a book
                 * Must be a valid customerID
                 * Must be a valid bookID
                 * Customer must not have 6 books out already
                 */
            
                if (isCorrect()) {
                    int bookID = Integer.parseInt(data[0]);
                    int customerID = Integer.parseInt(data[1]);
                    
                    boolean isValidBookID = isValidBookID ();
                    boolean isValidCustID = isValidCustomerID ();
                    boolean issixBooksOut = isSixBooksOut();
                    
                    if (isValidBookID && isValidCustID && issixBooksOut) {
                        Borrow borrow = new Borrow(bookID,customerID, date);
                        BorrowDao dao = new BorrowDao();
                        dao.borrow(borrow);
                        JOptionPane.showMessageDialog(null, "Done!");
                        frame1.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer ID and/or Book ID not correct!");
                    }
                }
                //if there is a missing data, then display Message Dialog
                else
                    JOptionPane.showMessageDialog(null, "Please fill all fields with valid infomation", "Warning", JOptionPane.WARNING_MESSAGE);
                    
            }
        });
        //for adding the action listener for the button to dispose the frame
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame1.dispose();
            }
        });
     
    }
	
	
}