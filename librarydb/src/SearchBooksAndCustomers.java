

//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 *A public class
 */
public class SearchBooksAndCustomers extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	private JLabel title = new JLabel("Search for Books and Customers");

	//for creating the center
	private JPanel center = new JPanel();

	//for creating the Center Panel
	private JPanel centerBooksPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchBooksPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchBooksButtonPanel = new JPanel();

	//for creating the table
	private JLabel searchBooksLabel = new JLabel(" Search by: ");
	//for creating JComboBox
	private JComboBox searchBooksTypes;
	//for creating String[]
	private String[] booksTypes = {"BookID", "Category", "Title", "Author"};
	//for creating the label
	private JLabel booksKey = new JLabel(" Write the Keyword: ");
	//for cearting the text field
	private JTextField booksKeyTextField = new JTextField();
	//for creating the button
	private JButton searchBooksButton = new JButton("Search");

	//for creating the Center Panel
	private JPanel centerCustomersPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchCustomersPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel searchCustomersButtonPanel = new JPanel();

	//for creating the table
	private JLabel searchCustomersLabel = new JLabel(" Search by: ");
	//for creating JComboBox
	private JComboBox searchCustomersTypes;
	//for creating String[]
	private String[] CustomersTypes = {"CustomerID", "First Name", "Last Name", "Contact Ph Number"};
	//for creating the label
	private JLabel CustomersKey = new JLabel(" Write the Keyword: ");
	//for cearting the text field
	private JTextField CustomersKeyTextField = new JTextField();
	//for creating the button
	private JButton searchCustomersButton = new JButton("Search");

	//for creating the south panel
	private JPanel southPanel = new JPanel();
	//for creating the button
	private JButton cancelButton = new JButton("Cancel");

	//for creating an array of string to store the data
	private String[] booksData;
	private String[] CustomersData;
	//create objects from another classes for using them in the ActionListener

	private Book book;
	private Customer member;

	//constructor of searchBooksAndCustomers
	public SearchBooksAndCustomers() {
		//for setting the title for the internal frame
		super("Search", false, true, false, true);
	
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//for adding the label
		northPanel.add(title);
		//for adding the north panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		center.setLayout(new BorderLayout());

		//for setting the layout
		centerBooksPanel.setLayout(new BorderLayout());
		//for setting the layout
		searchBooksPanel.setLayout(new GridLayout(2, 2, 1, 1));
		//for adding the label
		searchBooksPanel.add(searchBooksLabel);
		//for adding the JComboBos[]
		searchBooksPanel.add(searchBooksTypes = new JComboBox(booksTypes));
		//for adding the label
		searchBooksPanel.add(booksKey);
		//for adding the text field
		searchBooksPanel.add(booksKeyTextField);
		//for adding the internal panel to the panel
		centerBooksPanel.add("North", searchBooksPanel);

		//for setting the layout
		searchBooksButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		searchBooksButtonPanel.add(searchBooksButton);
		//for adding the internal panel to the center panel
		centerBooksPanel.add("South", searchBooksButtonPanel);
		//for setting the border
		centerBooksPanel.setBorder(BorderFactory.createTitledBorder("Search for a books:"));
		//for adding center panel to the center
		center.add("West", centerBooksPanel);

		//for setting the layout
		centerCustomersPanel.setLayout(new BorderLayout());
		//for setting the layout
		searchCustomersPanel.setLayout(new GridLayout(2, 2, 1, 1));
		//for adding the label
		searchCustomersPanel.add(searchCustomersLabel);
		//for adding the JComboBos[]
		searchCustomersPanel.add(searchCustomersTypes = new JComboBox(CustomersTypes));
		//for adding the label
		searchCustomersPanel.add(CustomersKey);
		//for adding the text field
		searchCustomersPanel.add(CustomersKeyTextField);
		//for adding the internal panel to the panel
		centerCustomersPanel.add("North", searchCustomersPanel);

		//for setting the layout
		searchCustomersButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		searchCustomersButtonPanel.add(searchCustomersButton);
		//for adding the internal panel to the center panel
		centerCustomersPanel.add("South", searchCustomersButtonPanel);
		//for setting the border
		centerCustomersPanel.setBorder(BorderFactory.createTitledBorder("Search for a Customers:"));
		//for adding center panel to the center
		center.add("East", centerCustomersPanel);

		//for adding the center to the container
		cp.add("Center", center);



		//for setting the layout
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		southPanel.add(cancelButton);
		//for setting the border
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		//for adding the south panel to the container
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		searchBooksButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				// write query to Search the Book.
			  
			    String bookCategory = (String) searchBooksTypes.getSelectedItem();
			    String keyword = booksKeyTextField.getText();
			    SearchDao dao = new SearchDao();
			    Vector<Book> vBook = new Vector<Book>();
			    vBook=dao.searchBook(bookCategory, keyword);
			  
			     new ListSearchBooks(vBook);
			   
			
					       // booksData[0] 
			}			
		});
		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and passing them to listSearchBooks object*
		 ***********************************************************************/
		searchCustomersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			    
			    // Write query to Search the Customer
				// CustomersData[0] 
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
}