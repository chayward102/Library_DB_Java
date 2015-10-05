//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *A public class
 */
public class AddCustomers extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creaing the North Label
	private JLabel northLabel = new JLabel("CUSTOMER INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel informationLabelPanel = new JPanel();
	//for creating an array of JLabel
	private JLabel[] informationLabel = new JLabel[7];
	//for creating an array of String
	private String[] informaionString = {" Customer ID: "/*0*/, " Password: "/*1*/,
			" First Names: "/*2*/, " Last Name: "/*3*/, " Address:"/*4*/, " Contact Ph Number: "/*5*/,"User Type: "/*6*/};

	//for creating an Internal Panel in the center panel
	private JPanel informationTextFieldPanel = new JPanel();
	//for creating an array of JTextField
	private JTextField[] informationTextField = new JTextField[7];


	//for creating an Internal Panel in the center panel
	private JPanel insertInformationButtonPanel = new JPanel();
	//for creating a button
	private JButton insertInformationButton = new JButton("Add Customer");

	//for creating the South Panel
	private JPanel southPanel = new JPanel();
	//for creating a button
	private JButton OKButton = new JButton("Exit");

	//create objects from another classes for using them in the ActionListener
	private Customer customer;
	//for creating an array of string to store the data
	private String[] data;



	//for checking the information from the text field
	public boolean isCorrect() {
		data = new String[7];
		for (int i = 1; i < informationLabel.length; i++) {
			if (i == 0) {
				if (!informationTextField[i].getText().equals("")) {
					data[i] = informationTextField[i].getText();
				}
				else
					return false;
			}
		}
		return true;
	}

	//for setting the array of JTextField & JPasswordField to null
	public void clearTextField() {
		for (int i = 0; i < informationLabel.length; i++) {

			informationTextField[i].setText(null);

		}
	}

	//constructor of addMembers
	public AddCustomers() {
		//for setting the title for the internal frame
		super("Add Customers", false, true, false, true);

		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//for adding the label to the panel
		northPanel.add(northLabel);
		//for adding the panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the border to the panel
		centerPanel.setBorder(BorderFactory.createTitledBorder("Add a new customer:"));
		//for setting the layout
		informationLabelPanel.setLayout(new GridLayout(8, 1, 1, 1));
		//for setting the layout
		informationTextFieldPanel.setLayout(new GridLayout(8, 1, 1, 1));
		/***********************************************************************
		 * for adding the strings to the labels, for setting the font		   *
		 * and adding these labels to the panel.							   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {
			informationLabelPanel.add(informationLabel[i] = new JLabel(informaionString[i]));

		}

		//for adding the panel to the centerPanel
		centerPanel.add("West", informationLabelPanel);

		/***********************************************************************
		 * for adding the JTextField and JPasswordField to the panel and       *
		 * setting the font to the JTextField and JPasswordField. Finally      *
		 * adding the panel to the centerPanel                                 *
		 ***********************************************************************/
		for (int i = 0; i < informationLabel.length; i++) {

			informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));


		}
		informationTextField[0].setEditable(false);
		centerPanel.add("East", informationTextFieldPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * and adding the button to the panel.								   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		insertInformationButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		insertInformationButtonPanel.add(insertInformationButton);
		centerPanel.add("South", insertInformationButtonPanel);
		cp.add("Center", centerPanel);

		/***********************************************************************
		 * for setting the layout for the panel,setting the font for the button*
		 * adding the button to the panel & setting the border.				   *
		 * finally adding the panel to the container						   *
		 ***********************************************************************/
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		southPanel.add(OKButton);
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField[] and make the connection for database,   *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		insertInformationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isCorrect()) {

					customer = new Customer();
					customer.setpassword(informationTextField[1].getText());
					customer.setfirstName(informationTextField[2].getText());
					customer.setlastName(informationTextField[3].getText());
					customer.setaddress(informationTextField[4].getText());
					customer.setcontactPhone(informationTextField[5].getText());
					customer.setuserType(informationTextField[6].getText());


					CustomerDao dao = new CustomerDao();
					if (!dao.checkDuplicate(customer)) {
						dao.add(customer);
						JOptionPane.showMessageDialog(insertInformationButton, "A new customer record has been added to the library database");
					} else {
						JOptionPane.showMessageDialog(insertInformationButton, "This customer already exists in the library database");
					}
					
				}
				//if there is a missing data, then display Message Dialog
				else
					JOptionPane.showMessageDialog(null, "Please fill all fields", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		});
		//for adding the action listener for the button to dispose the frame
		OKButton.addActionListener(new ActionListener() {
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