import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.*;

public class LoginPage extends JFrame {
    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/
	private String userType = ""; // This value should be passed into the constructor --SP

    //for creating the North Panel
    private JPanel northPanel = new JPanel();
    //for creating the label
    private JLabel title = new JLabel("");

    //for creating the Center Panel
    private JPanel centerPanel = new JPanel();
    //for creating an Internal Panel in the center panel
    private JPanel informationPanel = new JPanel();
    //for creating an array of JLabel
    private JLabel[] informationLabel = new JLabel[2];
    //for creating an array of String
    private String[] informationString = {" UserID:", " Password :",
                                          };
    //for creating an array of JTextField
    private JTextField[] informationTextField = new JTextField[2];
  //for creating JPasswordField
    private JPasswordField informationPasswordField = new JPasswordField();
    
    //for creating the date in the String
   
    private String[] data;

    //for creating an Internal Panel in the center panel
    private JPanel loginButtonPanel = new JPanel();
    //for creating the button
    private JButton loginButton = new JButton("Login");

    //for creating South Panel
    private JPanel southPanel = new JPanel();
    //for creating the button
    private JButton cancelButton = new JButton("Cancel");


    //for checking the password
    @SuppressWarnings("deprecation")
    public boolean isPasswordCorrect() {
        if (informationPasswordField.equals(informationPasswordField))
            data[1] = informationPasswordField.getText();
        else if (!informationPasswordField.equals(informationPasswordField))
            return false;

        return true;
    }

    //for checking the information from the text field
    public boolean isCorrect() {
        data = new String[2];
        for (int i = 0; i < informationLabel.length; i++) {
            if (i == 0) {
                if (!informationTextField[i].getText().equals("")) {
                    data[i] = informationTextField[i].getText();
                }
                else
                    return false;
            }
            if (i == 1 ) {
                if (informationPasswordField.equals(""))
                    return false;
            }
        
        }
        return true;
    }
  //for setting the array of JTextField & JPasswordField to null
    public void clearTextField() {
        for (int i = 0; i < informationLabel.length; i++) {
            if (i == 0)
                informationTextField[i].setText(null);
            if (i == 1)
                informationPasswordField.setText(null);
         
        }
    }

    //constructor of LoginPage
    public LoginPage(String userType) {
        //for setting the title for the internal frame
        super("Enter your Login Credentials");
        
        // Get userType from StartupWindow
        this.userType = userType;
        
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
         * for adding the strings to the labels, for setting the font          *
         * and adding these labels to the panel.                               *
         * finally adding the panel to the container                           *
         ***********************************************************************/
       
        
        for (int i = 0; i < informationLabel.length; i++) {
            if (i == 1) {
                informationPanel.add(informationLabel[i] = new JLabel(informationString[i]));
                informationPanel.add(informationPasswordField = new JPasswordField(25));
                informationPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 11));
            }
            if (i == 0) {
                informationPanel.add(informationLabel[i] = new JLabel(informationString[i]));
                informationPanel.add(informationTextField[i] = new JTextField());
            }
            
        }
       
        centerPanel.add("Center", informationPanel);

        //for setting the layout
        loginButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        //for adding the button to the panel
        loginButtonPanel.add(loginButton);
        //for adding the panel to the center panel
        centerPanel.add("South", loginButtonPanel);
        //for setting the border to the panel
        centerPanel.setBorder(BorderFactory.createTitledBorder("Login Credentials:"));
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
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //for checking if there is a missing information
                if (isCorrect()) {
                   ///LOGIN PAGE DAO
                    
                    /*Add Dao for Login Page here and after 
                     * Validation of User show respective Screen
                     * Code for Respective Screen is below. 
                     * Nataliya 1/06/2015 
                     * */
//                    new ScreenCustomer();
//                    new ScreenReceptionist();
//                    new ScreenAdmin();
if(isPasswordCorrect()){
                	LoginDao dao = new LoginDao();
                	boolean validUserAndPass = dao.login(Integer.parseInt(data[0]), data[1], userType);
                	int customerID = Integer.parseInt(data[0]);
                	if (validUserAndPass) {
                		if (userType.equals(LoginDao.USER_TYPE_CUSTOMER)) {
                		    
                			new ScreenCustomer(customerID);
                			dispose();
                		} else if (userType.equals(LoginDao.USER_TYPE_RECEPTIONIST)) {
                		    
                			new ScreenReceptionist(customerID);
                			dispose();
                		} else if (userType.equals(LoginDao.USER_TYPE_ADMIN)) {
                			new ScreenAdmin(customerID);
                			dispose();
                		}
                	} else {
                		JOptionPane.showMessageDialog(null, "Incorrect username or password!", "Warning", JOptionPane.WARNING_MESSAGE);
                	}
        	
                	
                }
                //if there is a missing data, then display Message Dialog
                else
                    JOptionPane.showMessageDialog(null, "Please fill all fields", "Warning", JOptionPane.WARNING_MESSAGE);
            }}
        });
        //for adding the action listener for the button to dispose the frame
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });
        //for setting the visible to true
        show();
        setVisible(true);
        //show the internal frame
        pack();
    }
}
