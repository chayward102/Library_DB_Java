//import the packages for using the classes in them into the program

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *A public class
 */
public class ListSearchBooks extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/


	//for creating the table
	private JTable table;
	
	JFrame frame = new JFrame();


	//constructor of listSearchBooks
	public ListSearchBooks(Vector<Book> SearchedBook) {
	
super("Search Results", false, true, false, true);
JButton borrowBook = new JButton("Borrow Selected Book");
       
		/***********************************************************************
		 *for setting the required information for the ResultSetTableModel class*
		 ************************************************************************/
		
		Object columnNames[] = { "Book ID", "Book Name", "Author"};
		 Object[][] data = new Object[SearchedBook.size()][3];
         for(int i=0; i<SearchedBook.size();i++){
                 Book c = (Book) SearchedBook.elementAt(i);
                 data[i][0] = c.getbookID();
                 data[i][1] = c.getbookTitle();
                 data[i][2] = c.getbookAuthor();
         }
      
         borrowBook.addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent ae) {
                 int selectedRowIndex = table.getSelectedRow();
                 Object selectedObject = (Object) table.getModel().getValueAt(selectedRowIndex, 0);
                 dispose();
                 new BorrowBooks( (int) selectedObject);
                 
                 
             }
         });   
         
         table = new JTable(data, columnNames);
         JScrollPane scrollPane = new JScrollPane(table);
         frame.add(scrollPane, BorderLayout.CENTER);
         
frame.add(borrowBook,BorderLayout.PAGE_START);
         frame.setSize(300, 150);
         frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
      
         //to show the frame
         frame.pack();
	}
}