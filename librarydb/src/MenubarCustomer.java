
//import the packages for using the classes in them into the program
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.management.JMException;

public class MenubarCustomer extends JMenuBar {

    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/

    //for creating the JMenu for the program
    public JMenu   bookMenu,  memberMenu,  searchMenu,  loanMenu,  reserveMenu;
    //for creating the JMenuItem for JMenu
    public JMenuItem printBook,  exit,  addBook,  listBook,  listAvailbleBook,  listBorrowedBook;
    public JMenuItem  removeBook, addCustomer ;
    public JMenuItem  removeCustomer,  searchBooksAndCustomers,  borrowBook,  returnBook;
    


    public MenubarCustomer() {
        //for adding book, member, search, loan & help Menus to the menu bar
        this.add(searchMenu = new JMenu("Search"));
        this.add(loanMenu = new JMenu("Borrow"));


      
     
        
     
     
 
    


        //for adding add, list & remove Members to the memberMenu
        searchMenu.add(searchBooksAndCustomers = new JMenuItem("Search"));

        //for adding borrow & return books to the loanMenu
        loanMenu.add(borrowBook = new JMenuItem("Borrow a Book"));
        loanMenu.add(returnBook = new JMenuItem("Return a Book"));
      

    



    }
}
