import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import oracle.jdbc.*;
import java.awt.*;
import java.io.*;
import java.math.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import oracle.jdbc.pool.OracleDataSource;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DisplayPage extends JFrame {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayPage frame = new DisplayPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DisplayPage() {
		getContentPane().setBackground(Color.DARK_GRAY);
		System.out.println("Entered Display");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 636, 390);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 602, 292);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setColumnSelectionAllowed(true);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DisplayTableCombo(e);
			}
		});
		comboBox.setBounds(216, 20, 160, 21);
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Employees", "Customers","Products","Suppliers","Supply","Purchases","Logs"}));
		getContentPane().add(comboBox);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayPageBack(e);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnNewButton.setBounds(10, 10, 112, 31);
		getContentPane().add(btnNewButton);
		
	}
	
	private void DisplayTableCombo(ActionEvent e) {
		
		JComboBox cb1 = (JComboBox) e.getSource();
		String msg = (String) cb1.getSelectedItem();
		
		OracleDataSource ds;
		Connection conn=null;
		try {
			System.out.println("Entered try");
			ds = new oracle.jdbc.pool.OracleDataSource();
			System.out.println("After ds try");
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
		      System.out.println("After seturl try");
		      conn = ds.getConnection("sdesai14", "neelmay");
		      System.out.println("After get con try");
		      System.out.println("Connection Successfull	");
		      switch(msg) {
		      
		      case "Employees":
		    	  try {
					    
					    Statement stmt = conn.createStatement (); 
					    ResultSet rset;
					    CallableStatement cs = conn.prepareCall("{call proj2.show_employees(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }
					  	rset.close();
					  	stmt.close();
		    	  }catch (SQLException ex){
		    		  System.out.println ("\n*** SQLException caught1 in employees ***\n");
	    				System.out.println(ex);
		    		  
		    	  }
		    	  break;
		    	  
		      case "Customers":
		    	  try {
		    		  
		    		  Statement stmt = conn.createStatement (); 
				      ResultSet rset;
				      CallableStatement cs = conn.prepareCall("{call proj2.show_customers(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }
				  	
				  		rset.close();
				  		stmt.close();
		    	  }catch (SQLException ex) {
		    		  System.out.println ("\n*** SQLException caught1 in customers ***\n");
	    				System.out.println(ex);
		    		  
		    	  }
		    	  break;
		    	  
		      case "Products":
		    	  try {
		    		  Statement stmt = conn.createStatement (); 
				      ResultSet rset;
				      CallableStatement cs = conn.prepareCall("{call proj2.show_products(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }
				  	rset.close();
				  	stmt.close();
		    		  
		    	  }catch (SQLException ex){
		    		  System.out.println ("\n*** SQLException caught1 in products ***\n");
	    				System.out.println(ex);
		    		  
		    		  
		    	  }
		    	  break;
		      case "Suppliers":
		    	  try {
		    		  Statement stmt = conn.createStatement (); 
				      ResultSet rset;
				      CallableStatement cs = conn.prepareCall("{call proj2.show_suppliers(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }
				  	rset.close();
				  	stmt.close();
		    		  
		    	  }catch(SQLException ex){
		    		  
		    		  System.out.println ("\n*** SQLException caught1 in suppliers ***\n");
	    				System.out.println(ex);
		    	  }
		    	  break;
		      case "Supply":
		    	  try {
		    		  Statement stmt = conn.createStatement (); 
				      ResultSet rset;
				      CallableStatement cs = conn.prepareCall("{call proj2.show_supply(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }	
				  	rset.close();
				  	stmt.close();
		    		  
		    		  
		    	  }catch(SQLException ex){
		    		  
		    		  System.out.println ("\n*** SQLException caught1 in supply ***\n");
	    				System.out.println(ex);
		    	  }
		    	  break;
		    	  
		      case "Purchases":
		    	  try {
		    		  Statement stmt = conn.createStatement (); 
				      ResultSet rset;
				      CallableStatement cs = conn.prepareCall("{call proj2.show_purchases(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }
				  	rset.close();
				  	stmt.close();
		    		  
		    		  
		    	  }catch(SQLException ex){
		    		  
		    		  System.out.println ("\n*** SQLException caught1 in purchases ***\n");
	    				System.out.println(ex);
		    	  }
		    	  break;
		    	  
		      case "Logs":
		    	  try {
		    		  Statement stmt = conn.createStatement (); 
				      ResultSet rset;
				      CallableStatement cs = conn.prepareCall("{call proj2.show_logs(?)}");
					    cs.registerOutParameter(1,OracleTypes.CURSOR);
					    cs.execute();
					    rset = (ResultSet) cs.getObject(1);
					    if(rset!=null) {
					  	table.setModel(DbUtils.resultSetToTableModel(rset));
					    }
				  	rset.close();
				  	stmt.close();
		    		  
		    		  
		    	  }catch(SQLException ex){
		    		  
		    		  System.out.println ("\n*** SQLException caught1 in logs ***\n");
	    				System.out.println(ex);
		    	  }
		    	  break;
		    	  }
	
		}catch (SQLException ex) { 
			
			System.out.println ("\n*** SQLException caught3 ***\n");
			
			System.out.println(ex);
			}finally {
				try {
					conn.close();
					}catch (SQLException ex){
						
						System.out.println ("\n*** SQLException caught2 ***\n");
						System.out.println(ex);
			  
		  }
		  }
		
	}
	private void DisplayPageBack(MouseEvent evt) {
		System.out.println("Hello From DisplayPage Back btn");
		Start_page sp =new Start_page();
		dispose();
		sp.setVisible(true);
	}
}
