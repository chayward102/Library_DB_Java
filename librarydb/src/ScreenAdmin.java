
//import the packages for using the classes in them into the program
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

/**
 *A public class
 */
public class ScreenAdmin extends JFrame implements ActionListener {

    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/

    //for creating the JPanel
    private JPanel searchPanel = new JPanel();
    //for creating the JToolBar for the program
    private JToolBar searchToolBar = new JToolBar();
    //for creating the label
    private JLabel searchLabel = new JLabel("Book title: ");
    //for creating the JTextField to use it on the searchToolBar
    private JTextField searchTextField = new JTextField(15);
    //for creating the JButton to use it on the searchToolBar
    private JButton goButton = new JButton("Go");
    //for creating JTabbedPane
    //private JTabbedPane tabbedPane = new JTabbedPane();
    //for creating JDeskTopPane for using JInternalFrame on the desktop
    private JDesktopPane desktop = new JDesktopPane();
    //private JDesktopPane desktop;
    //for creating JSplitPane
    private JSplitPane splitPane;
    //for creating JScrollPane for JDesktopPane
    private JScrollPane desktopScrollPane;
    private JScrollPane treeScrollPane;
    //for creating the background
    //private ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("images/Logo.JPG"));
    //for creating JLabel
    //private JLabel background = new JLabel(icon);
    private LoginDao lDao = new LoginDao();
    /***************************************************************************
     *create objects from another classes for using them in the ActionListener *
     ***************************************************************************/
    private MenubarAdmin menu;

 

    private AddBooks addBooks;
    private UpdateBooks updateBooks;
    private RemoveBooks removeBooks;
    private BorrowBooks borrowBooks;
    private ReturnBooks returnBooks;
    //private BooksInformation booksInformation;
    private AddCustomers addCustomers;
    private RemoveCustomer removeCustomer;

    private SearchBooksAndCustomers search;
private LendingReports showReports;

    //constructor of JLibrary
    public ScreenAdmin(int customerID) {
        //for setting the title for the frame
        super("Library System - JLibrary");
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width-300, screenSize.height - 300);
        setLocationRelativeTo(null);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //for setting resizable to false
        setResizable(true);

   
        menu = new MenubarAdmin();
      
        //for setting the menu bar
        setJMenuBar(menu);
        //for adding the actionListener
        menu.updateBook.addActionListener(this);
        menu.addBook.addActionListener(this);
     
        menu.removeBook.addActionListener(this);
        //menu.bookInformation.addActionListener(this);
        menu.addCustomer.addActionListener(this);

        menu.removeCustomer.addActionListener(this);

        menu.searchBooksAndCustomers.addActionListener(this);
        menu.borrowBook.addActionListener(this);
        menu.returnBook.addActionListener(this);
menu.lendingReports.addActionListener(this);

  menu.lateReturn.addActionListener(this);
  menu.lostBook.addActionListener(this);
  

        //get the graphical user interface components display the desktop
        Container cp = getContentPane();
        desktop.setBackground(Color.GRAY);
        cp.add("Center", desktop);
        //for setting the font
        searchLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        //for setting the font
        searchTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
        goButton.setFont(new Font("Tahoma", Font.BOLD, 9));
        //for adding the searchLable to the searchToolBar
        searchToolBar.add(searchLabel);
        //for adding the searchTextField to searchToolBar
        searchToolBar.add(searchTextField);
        //for adding the goButton to searchToolBar
        searchToolBar.add(goButton);
        //for adding listenerAction for the button
        goButton.addActionListener(this);
        //for setting the layout
        searchPanel.setLayout(new BorderLayout());

        //for adding the searchToolBar to the searchPanel
        //searchPanel.add("South", searchToolBar);
        //for adding the searchPanel to the Container
        cp.add("North", searchPanel);

       

        //for adding WindowListener to the program
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
            	lDao.logout(customerID);
            	String sessionDuration = lDao.lastSessionDuration(customerID);
            	JOptionPane.showMessageDialog(null, "You were logged in for " + sessionDuration);
                System.exit(0);
            }
        });
        //show the program
        show();
    }

    /**
     *this method is invoked when an action occurs.
     *@param ae the action event.
     */
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == menu.addBook ) {
            
                    addBooks = new AddBooks();
                    desktop.add(addBooks);
                  
                        try {
                            addBooks.setSelected(true);
                        } catch (PropertyVetoException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                   
            };
           

       
        if (ae.getSource() == menu.removeBook ) {
        
                    removeBooks = new RemoveBooks();
                    desktop.add(removeBooks);
                
                        try {
                            removeBooks.setSelected(true);
                        } catch (PropertyVetoException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                
            };
            
            if (ae.getSource() == menu.updateBook ) {
                
            	updateBooks = new UpdateBooks();
                desktop.add(updateBooks);
              
                    try {
                    	updateBooks.setSelected(true);
                    } catch (PropertyVetoException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
               
        };

        if (ae.getSource() == menu.addCustomer) {
            
                    addCustomers = new AddCustomers();
                    desktop.add(addCustomers);
                  
                        try {
                            addCustomers.setSelected(true);
                        } catch (PropertyVetoException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                  
        }
       
        if (ae.getSource() == menu.removeCustomer) {
            
                    removeCustomer = new RemoveCustomer();
                    desktop.add(removeCustomer);
                  
                        try {
                            removeCustomer.setSelected(true);
                        } catch (PropertyVetoException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                   
        }
       
        if (ae.getSource() == menu.searchBooksAndCustomers) {
         
                    search = new SearchBooksAndCustomers();
                    desktop.add(search);
                 
                        try {
                            search.setSelected(true);
                        } catch (PropertyVetoException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                  
        }
        if (ae.getSource() == menu.borrowBook ) {
          
                    borrowBooks = new BorrowBooks();
                    desktop.add(borrowBooks);
                   
                      
                            try {
                                borrowBooks.setSelected(true);
                            } catch (PropertyVetoException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                      
        }
        if (ae.getSource() == menu.returnBook ) {
          
                    returnBooks = new ReturnBooks();
                    desktop.add(returnBooks);
                 
                        try {
                            returnBooks.setSelected(true);
                        } catch (PropertyVetoException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                   
        }

       
if(ae.getSource()==menu.lendingReports){
    showReports = new LendingReports();
    desktop.add(showReports);
 
        try {
            showReports.setSelected(true);
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
   
if(ae.getSource()==menu.lateReturn){
    ReportDao report = new ReportDao();
    ArrayList<String[]> rArray = report.fineReport(ReportDao.LATE_RETURN);
    ListReports list =new ListReports(ReportDao.LATE_RETURN, rArray);
    
}
if(ae.getSource()==menu.lostBook){
    ReportDao report = new ReportDao();
    ArrayList<String[]> rArray = report.fineReport(ReportDao.LOST_BOOK);
    ListReports list = new ListReports(ReportDao.LOST_BOOK, rArray);
    
}

        if (ae.getSource() == menu.exit) {
            dispose();
            System.exit(0);
        }

    }
}
