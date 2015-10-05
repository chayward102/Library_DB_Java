

//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

/**
 *A public class
 */
public class LendingReports extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/

	//for creating the North Panel
	private JPanel northPanel = new JPanel();
	//for creating the label
	

	//for creating the center
	private JPanel center = new JPanel();

	//for creating the Center Panel
	private JPanel centerPanel = new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel generatePanel = new JPanel();
	private JPanel textFields=new JPanel();
	//for creating an Internal Panel in the center panel
	private JPanel generateButtonPanel = new JPanel();

	//for creating the table
	private JLabel generateLabel = new JLabel(" Report Type: ");
	//for creating JComboBox
	private JComboBox generateTypes;
	//for creating String[]
	private String[] Types = {"Daily", "Monthly", "Annual"};
	//for creating the label
	private JLabel fromDate = new JLabel(" From (YYYY-MM-DD): ");
	private JLabel toDate = new JLabel(" To (YYYY-MM-DD): ");
	//for cearting the text field

	   private JTextField fromDatetextField = new JTextField();
	    private JTextField toDatetextField = new JTextField();
	//for creating the button
	private JButton generateButton = new JButton("Generate");

	private JPanel southPanel = new JPanel();
    //for creating the button

	private JButton cancelButton = new JButton("Cancel");

	//for creating an array of string to store the data
	private String[] Data;
	private String[] reportData;
	

	//constructor of generateAndCustomers
	public LendingReports() {
		//for setting the title for the internal frame
		super("generate", false, true, false, true);
	
		//for getting the graphical user interface components display area
		Container cp = getContentPane();

		//for setting the layout
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		
		//for adding the north panel to the container
		cp.add("North", northPanel);

		//for setting the layout
		center.setLayout(new BorderLayout());

		//for setting the layout
		centerPanel.setLayout(new BorderLayout());
		//for setting the layout
		generatePanel.setLayout(new GridLayout(3, 1, 1, 1));
		textFields.setLayout(new GridLayout(3,1,1,1) );
		//for adding the label
		generatePanel.add(generateLabel);
		//for adding the JComboBos[]
		textFields.add(generateTypes = new JComboBox(Types));
		//for adding the label
		generatePanel.add(fromDate);
		  textFields.add(fromDatetextField);
		  generatePanel.add(toDate);
		//for adding the text field
		  textFields.add(toDatetextField);
		
		//for adding the internal panel to the panel
		centerPanel.add("West", generatePanel);
		centerPanel.add("East", textFields);
		//for setting the layout
		generateButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		//for adding the button
		generateButtonPanel.add(generateButton);
		//for adding the internal panel to the center panel
		centerPanel.add("South", generateButtonPanel);
		//for setting the border
		centerPanel.setBorder(BorderFactory.createTitledBorder("Lending Reports:"));
		//for adding center panel to the center
		center.add("West", centerPanel);



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
		 * taken from the JTextField and passing them to listgenerate object*
		 ***********************************************************************/
		generateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//for checking if there is a missing information
				// write query to generate the Book.
			  
			    String bookCategory = (String) generateTypes.getSelectedItem();
			    String toDate =toDatetextField.getText();
			    String fromDate =fromDatetextField.getText();
			    if(bookCategory.equalsIgnoreCase("Daily")){
			    ReportDao report = new ReportDao();
			    ArrayList<String[]> rArray = report.lendingReport(ReportDao.DAILY_REPORT, fromDate, toDate);
			    ListReports list = new ListReports(ReportDao.DAILY_REPORT, rArray);}
			    else if(bookCategory.equalsIgnoreCase("Monthly")){ ReportDao report = new ReportDao();
                ArrayList<String[]> rArray = report.lendingReport(ReportDao.MONTHLY_REPORT, fromDate, toDate);
                ListReports list = new ListReports(ReportDao.MONTHLY_REPORT, rArray);}
			    else { ReportDao report = new ReportDao();
                ArrayList<String[]> rArray = report.lendingReport(ReportDao.ANNUAL_REPORT, fromDate, toDate);
                ListReports list = new ListReports(ReportDao.ANNUAL_REPORT, rArray);}
			    
			}
			
					       // Data[0] 
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