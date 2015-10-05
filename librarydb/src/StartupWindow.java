/* Start up Window
 * From this frame splits which database users can access:
 * Admin
 * Receptionist
 * Customer
 */



import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class StartupWindow extends JFrame 

{   
    private JButton cxButton = new JButton("Customer");
    private JButton rxButton = new JButton("Receptionist");
    private JButton adButton = new JButton("Administrator");
    private JLabel  introLabel = new JLabel ("Welcome to the Library!");
    public StartupWindow()
    {
        this.setLayout(new GridLayout(1,1));
        
        
        
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
       p2.setLayout(new GridLayout(4,1));
       p2.setBackground(Color.gray);
       p3.setBackground(Color.gray);
       p4.setBackground(Color.gray);
       p5.setBackground(Color.gray);
        introLabel.setFont(introLabel.getFont().deriveFont(25.0f));
        p2.add(introLabel,SwingConstants.CENTER);
        introLabel.setHorizontalAlignment( SwingConstants.CENTER );
        cxButton.setPreferredSize(new Dimension(200,50));
        p5.add(cxButton);
        cxButton.setFont(cxButton.getFont().deriveFont(20.0f));
        rxButton.setPreferredSize(new Dimension(200,50));
        p4.add(rxButton);
        rxButton.setFont(rxButton.getFont().deriveFont(20.0f));
        adButton.setPreferredSize(new Dimension(200,50));
        p3.add(adButton);
        adButton.setFont(adButton.getFont().deriveFont(20.0f));
       p2.setOpaque(true);
        this.add(p2);
        p2.add(p3);
        p2.add(p4);
        p2.add(p5);
        ButtonHandler handler = new ButtonHandler();
        cxButton.addActionListener(handler);
        cxButton.setActionCommand("Customer");
       
       
        ButtonHandler handler1 = new ButtonHandler();
        rxButton.addActionListener(handler1);
        rxButton.setActionCommand("Receptionist");
        
        ButtonHandler handler2 = new ButtonHandler();
        adButton.addActionListener(handler2);
        adButton.setActionCommand("Administrator");
        
     
        

    }

    
    private class ButtonHandler implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand() == "Customer"){
                LoginPage LoginP= new LoginPage(LoginDao.USER_TYPE_CUSTOMER);
                LoginP.setSize(350,300);
                LoginP.setLocationRelativeTo(null);
                /*Once DAO of LOGIN page is Done
                 * Remove the user Specific screen from here so that it comes up only after user authentication
                 * and uncomment the LoginPage code (above this comment and underneath).
                 * Nataliya 1/06/2015
                 * */
//                new ScreenCustomer();/*Remove this line once DAO of Login page is done*/         
            
            }else if(e.getActionCommand() == "Receptionist"){
            	LoginPage LoginP= new LoginPage(LoginDao.USER_TYPE_RECEPTIONIST);
                LoginP.setSize(350,300);
                LoginP.setLocationRelativeTo(null);
//                new ScreenReceptionist();
                
            }
            else if(e.getActionCommand() == "Administrator"){
            	LoginPage LoginP= new LoginPage(LoginDao.USER_TYPE_ADMIN);
                LoginP.setSize(350,300);
                LoginP.setLocationRelativeTo(null);
//                new ScreenAdmin();  /*Remove this line once DAO of Login page is done*/
        }
        
    }
    }
 
    public static void main(String[] args)
    {
       BorrowDao bDao = new BorrowDao();
       bDao.calcAllFines();
       
       StartupWindow stFrame = new StartupWindow();
       stFrame.setSize(700, 350);
       stFrame.setLocationRelativeTo(null);
       stFrame.setBackground(Color.blue);
       stFrame.setVisible(true);
       stFrame.setTitle("Library Management System");
       
       stFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       long endTime   = System.currentTimeMillis();
       
      
    }
}