//import the packages for using the classes in them into the program

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.TableColumn;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

/**
 *A public class
 */
public class CustomerPortal extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/


	//for creating the table
	private JTable table;
	private ArrayList<Integer> rowReturned = new ArrayList<Integer>();
	JFrame frame = new JFrame();
	
	public JButton bReturnBook = new JButton("Return Book");


	//constructor of listSearchBooks
	public CustomerPortal(int customerID, ArrayList<String[]> reportResults) {
	
super("Search Results", false, true, false, true);
        
        //for getting the graphical user interface components display area
         

        
     
        //for adding the panel to the container
      
		
		/***********************************************************************
		 *for setting the required information for the ResultSetTableModel class*
		 ************************************************************************/
		
		 String[] columnNames;
		 String[][] data;
		 
		 columnNames = new String[]{"Book Title", "Book Author", "Date Borrowed", "Days Overdue", "Book Fine"};
		 data = new String[reportResults.size()][5];
		 for (int i = 0; i < data.length; i++) {
			 data[i][0] = reportResults.get(i)[0];
			 data[i][1] = reportResults.get(i)[1];
			 data[i][2] = reportResults.get(i)[2];
			 data[i][3] = reportResults.get(i)[3];
			 data[i][4] = reportResults.get(i)[4];
		 }
         table = new JTable(data, columnNames);
         JScrollPane scrollPane = new JScrollPane(table);
         frame.add(scrollPane, BorderLayout.CENTER);
         JPanel buttonsPanel = new JPanel();
         buttonsPanel.add(bReturnBook);
         frame.add(buttonsPanel, BorderLayout.SOUTH);
         frame.setSize(150, 150);
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);
        
      
         //to show the frame
         frame.pack();
         
         bReturnBook.addActionListener(new ActionListener() {
     		public void actionPerformed(ActionEvent ae) {
     			boolean rowPrevSelected = false;
     			for (int i : rowReturned) {
     				if (table.getSelectedRow() == i)
     					rowPrevSelected = true;
     			}
     			if (rowPrevSelected) {
     				JOptionPane.showMessageDialog(null, "You've already returned that book!");
     			} else if (table.getSelectedRow() == -1) {
     				JOptionPane.showMessageDialog(null, "No Book Selected!");
     			} else {
     				int selectedRowIndex = table.getSelectedRow();
	     		    int bookID = Integer.parseInt(reportResults.get(selectedRowIndex)[5]);
	     		    BorrowDao dao = new BorrowDao();
	     		    String returnDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new java.util.Date());
	     		    dao.returnBook(customerID, bookID, returnDate);
	     		    JOptionPane.showMessageDialog(null, "The book was returned!");
	     		    rowReturned.add(selectedRowIndex);
     			}
     		}
     	});
	}
	
	
/*
	public static void main(String[] args) {
		ReportDao report = new ReportDao();
	    ArrayList<String[]> rArray = report.fineReport(ReportDao.LOST_BOOK);
	    ListReports list = new ListReports(ReportDao.LOST_BOOK, rArray);
	}
*/
}