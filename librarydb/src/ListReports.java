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
import java.util.ArrayList;
import java.util.Vector;

/**
 *A public class
 */
public class ListReports extends JInternalFrame {
	/***************************************************************************
	 ***      declaration of the private variables used in the program       ***
	 ***************************************************************************/


	//for creating the table
	private JTable table;
	
	JFrame frame = new JFrame();


	//constructor of listSearchBooks
	public ListReports(int reportType, ArrayList<String[]> reportResults) {
	
super("Search Results", false, true, false, true);
        
        //for getting the graphical user interface components display area
         

        
     
        //for adding the panel to the container
      
		
		/***********************************************************************
		 *for setting the required information for the ResultSetTableModel class*
		 ************************************************************************/
		
		 String[] columnNames;
		 String[][] data;
		 
		 if (reportType == ReportDao.DAILY_REPORT || reportType == ReportDao.MONTHLY_REPORT || reportType == ReportDao.ANNUAL_REPORT) {
			 if  (reportType == ReportDao.DAILY_REPORT) {
				 columnNames = new String[]{"Date", "Number of books borrowed"};
			 } else if (reportType == ReportDao.MONTHLY_REPORT){
				 columnNames = new String[]{"Month", "Number of books borrowed"};
			 } else {
			 	columnNames = new String[]{"Year", "Number of books borrowed"};
			 }
			 data = new String[reportResults.size()][2];
			 for (int i = 0; i < data.length; i++) {
				 data[i][0] = reportResults.get(i)[0];
				 data[i][1] = reportResults.get(i)[1];
			 }
		 } else if(reportType == ReportDao.LATE_RETURN) {
			 columnNames = new String[]{"Customer ID", "First Name", "Last Name", "Outstanding Fine Amount"};
			 data = new String[reportResults.size()][4];
			 for (int i = 0; i < data.length; i++) {
				 data[i][0] = reportResults.get(i)[0];
				 data[i][1] = reportResults.get(i)[1];
				 data[i][2] = reportResults.get(i)[2];
				 data[i][3] = reportResults.get(i)[3];
			 }
		 } else {
			 columnNames = new String[]{"Book ID", "Book Title", "Customer ID", "First Name", "Last Name", "Number of Books Lost"};
			 data = new String[reportResults.size()][6];
			 for (int i = 0; i < data.length; i++) {
				 data[i][0] = reportResults.get(i)[0];
				 data[i][1] = reportResults.get(i)[1];
				 data[i][2] = reportResults.get(i)[2];
				 data[i][3] = reportResults.get(i)[3];
				 data[i][4] = reportResults.get(i)[4];
				 data[i][5] = reportResults.get(i)[5];
			 }
		 }
                          
         table = new JTable(data, columnNames);
         JScrollPane scrollPane = new JScrollPane(table);
         frame.add(scrollPane, BorderLayout.CENTER);
         frame.setSize(300, 150);
         frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
      
         //to show the frame
         frame.pack();
	}
	
//	public static void main(String[] args) {
//    ReportDao report = new ReportDao();
//	    ArrayList<String[]> rArray = report.fineReport(ReportDao.LOST_BOOK);
//	    ListReports list = new ListReports(ReportDao.LOST_BOOK, rArray);
//	}
}