//import the packages for using the classes in them into the program

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *A public class
 */
public class AddBooks extends JInternalFrame {

    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/

    //for creating the North Panel
    private JPanel topPanel = new JPanel();
    //for creaing the North Label

    //for creating the Center Panel
    private JPanel centerPanel = new JPanel();
    //for creating an Internal Panel in the center panel
    private JPanel informationLabelPanel = new JPanel();

    //for creating an array of JLabel
    private JLabel[] informationLabel = new JLabel[6];


    //for creating an array of String
    private String[] informationString = {
        " Book ID: ",
        " Title: ", " Author: ",
        " Date when added: ", " Price: ", " Supplier ID: "
    };
    //for creating an Internal Panel in the center panel
    private JPanel informationTextFieldPanel = new JPanel();
    //for creating an array of JTextField
    private JTextField[] informationTextField = new JTextField[6];

    //for creating an Internal Panel in the center panel
    private JPanel addBookButtonPanel = new JPanel();
    //for creating a button
    private JButton addBookButton = new JButton("Add Book");

    //for creating South Panel
    private JPanel bottomPanel = new JPanel();
    //for creating a button
    private JButton OKButton = new JButton("Exit");

    //create objects from another classes for using them in the ActionListener
    private Book book;
    //for creating an array of string to store the data
    //private String[] data;
    //for setting availble option to true
    private boolean availble = true;
    
    private String date;

    //for checking the information from the text field
    public boolean isCorrect() {
        //data = new String[6];
        for (int i = 1; i < informationLabel.length; i++) {
            if (informationTextField[i].getText().equals("")) {
               return false;
            }
        }
        return true;
    }

    //for setting the array of JTextField to empty
    public void clearTextField() {
        for (int i = 0; i < informationTextField.length; i++) {
            informationTextField[i].setText(null);
        }
        informationTextField[3].setText(date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date()));
    }

    //constructor of addBooks
    public AddBooks() {
        //for setting the title for the internal frame
        super("Add Books", false, true, false, true);
        
        //for getting the graphical user interface components display area
        Container cp = getContentPane();

        //for setting the layout
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //for adding the north panel to the container
        cp.add("North", topPanel);

        //for setting the layout
        centerPanel.setLayout(new BorderLayout());
        //for setting the border to the panel
        centerPanel.setBorder(BorderFactory.createTitledBorder("Add a new book:"));
        //for setting the layout
        informationLabelPanel.setLayout(new GridLayout(11, 1, 1, 1));
        /***********************************************************************
         * for adding the strings to the labels, for setting the font 		   *
         * and adding these labels to the panel.							   *
         * finally adding the panel to the container						   *
         ***********************************************************************/
        for (int i = 0; i < informationLabel.length; i++) {
            informationLabelPanel.add(informationLabel[i] = new JLabel(informationString[i]));
        
        }
        centerPanel.add("West", informationLabelPanel);

        //for setting the layout
        informationTextFieldPanel.setLayout(new GridLayout(11, 1, 1, 1));
        /***********************************************************************
         * for adding the strings to the labels, for setting the font 		   *
         * and adding these labels to the panel.							   *
         * finally adding the panel to the container						   *
         ***********************************************************************/
        for (int i = 0; i < informationTextField.length; i++) {
           
            informationTextFieldPanel.add(informationTextField[i] = new JTextField(25));
        }
        informationTextField[0].setEditable(false);
        informationTextField[3].setText(date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date()));
        informationTextField[3].setEditable(false);
        centerPanel.add("East", informationTextFieldPanel);
       
        /***********************************************************************
         * for setting the layout for the panel,setting the font for the button*
         * and adding the button to the panel.								   *
         * finally adding the panel to the container						   *
         ***********************************************************************/
        addBookButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
  
        addBookButtonPanel.add(addBookButton);
        centerPanel.add("South", addBookButtonPanel);
        cp.add("Center", centerPanel);

        /***********************************************************************
         * for setting the layout for the panel,setting the font for the button*
         * adding the button to the panel & setting the border.				   *
         * finally adding the panel to the container						   *
         ***********************************************************************/
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
      
        bottomPanel.add(OKButton);
        bottomPanel.setBorder(BorderFactory.createEtchedBorder());
        cp.add("South", bottomPanel);


        addBookButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                
                //for checking if there is a missing information
                if (isCorrect()) {
                	
                	
                	/**
                	 * Adds a book to the database.
                	 * Note that bookID is not specified here, the database is set to auto-increment this field
                	 * and thus we don't need to set it here.
                	 * I would recommend removing or disabling the bookID text field.
                	 * 
                	 * Sherman
                	 */
                	BookDao dao = new BookDao();
                	double bookPrice = Double.parseDouble(informationTextField[4].getText());
                	int bookSuppID = Integer.parseInt(informationTextField[5].getText());

                	book = new Book();
                	book.setbookTitle(informationTextField[1].getText());
                	book.setbookAuthor(informationTextField[2].getText());
                	book.setdateAdded(informationTextField[3].getText());
                	book.setbookPrice(bookPrice);
                	book.setsupplierID(bookSuppID);
                	
                	//if exists returns T then there is no book already by that id or title
                	if (dao.IfExists(book)) {
                		dao.add(book);
                		JOptionPane.showMessageDialog(addBookButton, "A new book record has been added to the library database");
                	} else {
                		JOptionPane.showMessageDialog(null, "This book already exists!");
                	}
                	
                    clearTextField();
                } //if there is a missing data, then display Message Dialog
                else {
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Warning", JOptionPane.WARNING_MESSAGE);
                }
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