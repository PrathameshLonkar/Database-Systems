import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;

public class SearchDataPage extends JFrame {
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchDataPage frame = new SearchDataPage();
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
	public SearchDataPage() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 460);
		getContentPane().setLayout(null);
		
		SearchEmpBtn = new JButton("Search Employees");
		SearchEmpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayEmpInfo(e);
			}
		});
		SearchEmpBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		SearchEmpBtn.setBounds(389, 116, 129, 42);
		getContentPane().add(SearchEmpBtn);
		
		txtSearchEmp = new JTextField();
		txtSearchEmp.setText("");
		txtSearchEmp.setBounds(627, 117, 96, 41);
		getContentPane().add(txtSearchEmp);
		txtSearchEmp.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("EID :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setBounds(548, 115, 69, 42);
		getContentPane().add(lblNewLabel);
		
		SearchCustBtn = new JButton("Search Customers");
		SearchCustBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayCustInfo(e);
			}
		});
		SearchCustBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		SearchCustBtn.setBounds(10, 116, 129, 42);
		getContentPane().add(SearchCustBtn);
		
		txtSearchCust = new JTextField();
		txtSearchCust.setBounds(228, 117, 96, 42);
		getContentPane().add(txtSearchCust);
		txtSearchCust.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CID :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_1.setBounds(149, 115, 71, 42);
		getContentPane().add(lblNewLabel_1);
		
		SearchPurBtn = new JButton("Search Purchases");
		SearchPurBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DisplayPurInfo(e);
			}
		});
		SearchPurBtn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		SearchPurBtn.setBounds(389, 10, 129, 42);
		getContentPane().add(SearchPurBtn);
		
		txtSearchPur = new JTextField();
		txtSearchPur.setBounds(627, 10, 96, 42);
		getContentPane().add(txtSearchPur);
		txtSearchPur.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CID :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblNewLabel_2.setBounds(528, 10, 88, 42);
		getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 168, 770, 245);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchDataPageBack(e);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnNewButton.setBounds(10, 10, 106, 33);
		getContentPane().add(btnNewButton);
	}
	private void DisplayEmpInfo(MouseEvent e){
		String eid= txtSearchEmp.getText();
		OracleDataSource ds;
		Connection conn=null;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			conn = ds.getConnection("sdesai14", "neelmay");
			System.out.println("Connection Successfull	");
			
			Statement stmt = conn.createStatement ();
			
			CallableStatement cs = conn.prepareCall("{call proj2.show_EmployeeInfo(?,?,?)}");
			cs.setString(2, eid);
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			cs.registerOutParameter(3,OracleTypes.VARCHAR);
			cs.execute();
			
			ResultSet rset = null;
			String msg =cs.getString(3);
			
			rset = (ResultSet) cs.getObject(1);
			if(msg!=null) {
				JOptionPane.showMessageDialog(null,
	                    msg,
	                    "Message",
	                    JOptionPane.ERROR_MESSAGE);
				
			}
			if(rset!=null) {
			table.setModel(DbUtils.resultSetToTableModel(rset));
			}
			System.out.println(msg);
			
			txtSearchEmp.setText("");
		  	stmt.close();
		  	conn.close();
			
		}catch (SQLException ex){
  		  System.out.println ("\n*** SQLException caught1 in Emp Info ***\n");
				System.out.println(ex);
  		  
  	  }
		
	}
	
	private void DisplayCustInfo(MouseEvent e){
		String cid= txtSearchCust.getText();
		OracleDataSource ds;
		Connection conn=null;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			conn = ds.getConnection("sdesai14", "neelmay");
			System.out.println("Connection Successfull	");
			
			Statement stmt = conn.createStatement ();
			
			CallableStatement cs = conn.prepareCall("{call proj2.show_CustomerInfo(?,?,?)}");
			cs.setString(2, cid);
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			cs.registerOutParameter(3,OracleTypes.VARCHAR);
			cs.execute();
			
			ResultSet rset;
			rset = (ResultSet) cs.getObject(1);
			String msg =cs.getString(3);
			if(msg!=null) {
				JOptionPane.showMessageDialog(null,
	                    msg,
	                    "Message",
	                    JOptionPane.ERROR_MESSAGE);
				
			}
			if(rset!=null) {
			table.setModel(DbUtils.resultSetToTableModel(rset));
			}
			System.out.println(msg);
			txtSearchCust.setText("");
		  	stmt.close();
		  	conn.close();
			
		}catch (SQLException ex){
  		  System.out.println ("\n*** SQLException caught1 in Cust Info ***\n");
				System.out.println(ex);
  		  
  	  }
		
	}
	private void DisplayPurInfo(MouseEvent e){
		String cid1= txtSearchPur.getText();
		OracleDataSource ds;
		Connection conn=null;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			conn = ds.getConnection("sdesai14", "neelmay");
			System.out.println("Connection Successfull	");
			
			Statement stmt = conn.createStatement ();
			
			CallableStatement cs = conn.prepareCall("{call proj2.show_PurchaseInfo(?,?,?)}");
			cs.setString(2, cid1);
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			cs.registerOutParameter(3,OracleTypes.VARCHAR);
			cs.execute();
			
			ResultSet rset;
			rset = (ResultSet) cs.getObject(1);
			String msg =cs.getString(3);
			if(msg!=null) {
				JOptionPane.showMessageDialog(null,
	                    msg,
	                    "Message",
	                    JOptionPane.ERROR_MESSAGE);
			}
			if(rset!=null) {
			table.setModel(DbUtils.resultSetToTableModel(rset));
			}
			System.out.println(msg);
			txtSearchPur.setText("");
		  	stmt.close();
		  	conn.close();
			
		}catch (SQLException ex){
  		  System.out.println ("\n*** SQLException caught1 in purchase Info ***\n");
				System.out.println(ex);
  		  
  	  }
	
}
	private void SearchDataPageBack(MouseEvent evt) {
		System.out.println("Hello from Search Page Back Btn");
		Start_page sp = new Start_page();
		dispose();
		sp.setVisible(true);
		
	}
	private JTextField txtSearchEmp;
	private JTextField txtSearchCust;
	private JTextField txtSearchPur;
	private JButton SearchEmpBtn;
	private JButton SearchCustBtn;
	private JButton SearchPurBtn;
	private JTable table;
}
