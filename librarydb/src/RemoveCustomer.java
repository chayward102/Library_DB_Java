//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *A public class
 */
public class RemoveCustomer extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	private JLabel title = new JLabel("CUSTOMER INFORMATION");

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel removePanel = new JPanel();
	//for creating the label
	private JLabel removeLabel = new JLabel(" Customer ID: ");
	//for creating the text field
	private JTextField removeTextField = new JTextField();
	//for creating string to store the data
	private String data;
	//for creating an Internal Panel in the center panel
	private JPanel removeMemberPanel = new JPanel();
	//for creating the button
	private JButton removeButton = new JButton("Remove");

	//for creating the South Panel
	private JPanel southPanel = new JPanel();
	//for adding the button
	private JButton exitButton = new JButton("Exit");

	//create objects from another classes for using them in the ActionListener
	private Customer member;

	//for checking the information from the text field
	public boolean isCorrect() {
		if (!removeTextField.getText().equals("")) {
			data = removeTextField.getText();
			return true;
		}
		else
			return false;
	}

	//constructor of removeMembers
	public RemoveCustomer() {
		//for setting the title for the internal frame
		super("Remove Customer", false, true, false, true);
	
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//for adding the label
		northPanel.add(title);
		//for adding the panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the layout
		removePanel.setLayout(new GridLayout(1, 2, 1, 1));
		//for adding the label
		removePanel.add(removeLabel);
		//for adding the text field
		removePanel.add(removeTextField);
		//for adding the internal panel to the panel
		centerPanel.add("Center", removePanel);

		//for setting the layout
		removeMemberPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		removeMemberPanel.add(removeButton);
		//for addint the internal panel to the center panel
		centerPanel.add("South", removeMemberPanel);
		//for setting the border
		centerPanel.setBorder(BorderFactory.createTitledBorder("Remove a member:"));
		//for adding the center panel to the container
		cp.add("Center", centerPanel);

		
		//for setting the layout
		southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		southPanel.add(exitButton);
		//for setting the border
		southPanel.setBorder(BorderFactory.createEtchedBorder());
		//for add the south panel to the container
		cp.add("South", southPanel);

		/***********************************************************************
		 * for adding the action listener to the button,first the text will be *
		 * taken from the JTextField and make the connection for database,     *
		 * after that update the table in the database with the new value      *
		 ***********************************************************************/
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				if (isCorrect()) {
				    
				
					CustomerDao dao = new CustomerDao();
					   dao.delete(data);
					   JOptionPane.showMessageDialog(removeButton, "You have successfully removed a customer!" );
					
				}
				//if there is a missing data, then display Message Dialog
				else {
					JOptionPane.showMessageDialog(removeButton, "Please provide the customer ID", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		//for adding the action listener for the button to dispose the frame
		exitButton.addActionListener(new ActionListener() {
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