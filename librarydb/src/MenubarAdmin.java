
//import the packages for using the classes in them into the program
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.management.JMException;

public class MenubarAdmin extends JMenuBar {

    /***************************************************************************
     ***      declaration of the private variables used in the program       ***
     ***************************************************************************/

    //for creating the JMenu for the program
    public JMenu   bookMenu,  memberMenu,  searchMenu,  loanMenu,  reserveMenu,reportMenu;
    //for creating the JMenuItem for JMenu
    public JMenuItem printBook,  exit,  addBook,  listBook,  listAvailbleBook,  listBorrowedBook, updateBook;
    public JMenuItem  removeBook, addCustomer ;
    public JMenuItem  removeCustomer,  searchBooksAndCustomers,  borrowBook,  returnBook;
    public JMenuItem lendingReports,lostBook,lateReturn;


    public MenubarAdmin() {
        //for adding book, member, search, loan & help Menus to the menu bar
        this.add(searchMenu = new JMenu("Search"));
        this.add(loanMenu = new JMenu("Borrow"));
        this.add(memberMenu = new JMenu("Customer"));
        this.add(bookMenu = new JMenu("Books"));
        this.add(reportMenu = new JMenu("Reports"));
     
        
     
     
 
        //for adding add, edit & remove Books and book information to the bookMenu
        bookMenu.add(addBook = new JMenuItem("Add Book"));
        bookMenu.add(removeBook = new JMenuItem("Remove Book"));
        bookMenu.add(updateBook = new JMenuItem("Update Book"));

        //for adding add, list, edit & remove Members and member information to the memberMenu
        memberMenu.add(addCustomer = new JMenuItem("Add Customer"));

        memberMenu.add(removeCustomer = new JMenuItem("Remove Customer"));
      

        //for adding add, list & remove Members to the memberMenu
        searchMenu.add(searchBooksAndCustomers = new JMenuItem("Search"));

        //for adding borrow & return books to the loanMenu
        loanMenu.add(borrowBook = new JMenuItem("Borrow a Book"));
        loanMenu.add(returnBook = new JMenuItem("Return a Book"));
           
        reportMenu.add(lendingReports=new JMenuItem("Lending Reports"));
       
        reportMenu.add(lostBook=new JMenuItem("Lost Book"));
        reportMenu.add(lateReturn=new JMenuItem("Late Return"));
    }
}
