//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *A public class
 */
public class ReturnBooks extends JInternalFrame implements ActionListener {

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
    private JLabel[] informationLabel = new JLabel[2];
    //for creating an array of String
    private String[] informationString = {" Book ID:", " Customer ID:"};
    //for creating an array of JTextField
    private JTextField[] informationTextField = new JTextField[2];
    //for creating an array of string to store the data
    private String[] data;
    private JLabel lblTotalFineAmt = new JLabel("Total fine amount ");
    private JTextField txtTotalFineAmt = new JTextField();
    //for creating an Internal Panel in the center panel
    private JPanel returnButtonPanel = new JPanel();
    //for creating the buton
    private JButton returnButton = new JButton("Return");

    //for creating the panel
    private JPanel southPanel = new JPanel();
    //for creating the button
    private JButton cancelButton = new JButton("Cancel");

    //for creating an object
    private Book book;
    private Customer member;
    private Borrow borrow;


    //for checking the information from the text field
    public boolean isCorrect() {
        data = new String[2];
        for (int i = 0; i < informationLabel.length; i++) {
            if (!informationTextField[i].getText().equals("")) {
                data[i] = informationTextField[i].getText();
            } else {
                return false;
            }
        }
        return true;
    }

    //for setting the array of JTextField to null
    public void clearTextField() {
        for (int i = 0; i < informationTextField.length; i++) {
            if (i != 2) {
                informationTextField[i].setText(null);
            }
          
            txtTotalFineAmt.setText(null);
        }
    }

    //constructor of returnBooks
    public ReturnBooks() {
        //for setting the title for the internal frame
        super("Return books", false, true, false, true);
       
        //for getting the graphical user interface components display area
        Container cp = getContentPane();

        //for setting the layout
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //for adding the label
        northPanel.add(title);
        //for adding the north panel to the container
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
        
            informationPanel.add(informationTextField[i] = new JTextField());
            informationTextField[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
        }
    
        informationPanel.add(lblTotalFineAmt);
        informationPanel.add(txtTotalFineAmt);
        txtTotalFineAmt.setEditable(false);
   
        centerPanel.add("Center", informationPanel);
        //for setting the layout
        returnButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //for adding the button
        returnButtonPanel.add(returnButton);

        //for adding the internal panel to the panel
        centerPanel.add("South", returnButtonPanel);
        //for setting the border
        centerPanel.setBorder(BorderFactory.createTitledBorder("Return a book:"));
        //for adding the center panel to the container
        cp.add("Center", centerPanel);

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
         * taken from the JTextField and make the connection for database,     *
         * after that update the table in the database with the new value      *
         ***********************************************************************/
        returnButton.addActionListener(this);
        //for adding the action listener for the button to dispose the frame
        cancelButton.addActionListener(this);
        //for setting the visible to true
        setVisible(true);
        //show the internal frame
        pack();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == returnButton) {
            //for checking if there is a missing information
            if (isCorrect()) {
            	String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
            	BorrowDao dao = new BorrowDao();
            	int bookID = Integer.parseInt(data[0]);
				int customerID = Integer.parseInt(data[1]);
            	
            	dao.returnBook( customerID,bookID, date);
            	JOptionPane.showMessageDialog(returnButton, "You have successfully returned a book!" );
            	
            	
            	
                // Write query to Return the Book.
                // write the amount of fine in -->>> txtTotalFineAmt.setText()
                
                
            } //if there is a missing data, then display Message Dialog
            else {
                JOptionPane.showMessageDialog(returnButton, "Please fill all details.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (ae.getSource() == cancelButton) {
            dispose();
        }
    }

   
}//class closed
