import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchPage extends JFrame {
	private JTable table;
	private JTextField txtEnterTheProduct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchPage frame = new SearchPage();
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
	public SearchPage() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 420);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 687, 247);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtEnterTheProduct = new JTextField();
		txtEnterTheProduct.setBounds(274, 38, 174, 35);
		getContentPane().add(txtEnterTheProduct);
		txtEnterTheProduct.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter The Product ID to calculate the monthly sale");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(146, 10, 408, 18);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reportMonthlySale(e);
			}
		});
		btnNewButton.setBounds(301, 83, 118, 35);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchPageBack(e);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnNewButton_1.setBounds(10, 11, 106, 35);
		getContentPane().add(btnNewButton_1);
		
	}
	private void reportMonthlySale(MouseEvent e) {
		String pid= txtEnterTheProduct.getText();
		OracleDataSource ds;
		Connection conn=null;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			conn = ds.getConnection("sdesai14", "neelmay");
			System.out.println("Connection Successfull	");
			
			Statement stmt = conn.createStatement ();
			
			CallableStatement cs = conn.prepareCall("{call proj2.show_monthly_sale(?,?,?)}");
			cs.setString(2, pid);
			cs.registerOutParameter(1,OracleTypes.CURSOR);
			cs.registerOutParameter(3,OracleTypes.VARCHAR);
			cs.execute();
			
			
			ResultSet rset;
			rset = (ResultSet) cs.getObject(1);
			if(rset!=null) {
			table.setModel(DbUtils.resultSetToTableModel(rset));
			}
			String msg = cs.getString(3);
			System.out.println(msg);
			if(msg!=null) {
				JOptionPane.showMessageDialog(null,
                        msg,
                        "Message",
                        JOptionPane.ERROR_MESSAGE);
				
			}
			txtEnterTheProduct.setText("");
		  	stmt.close();
		  	conn.close();
			
		}catch (SQLException ex){
  		  System.out.println ("\n*** SQLException caught1 in monthly sale ***\n");
				System.out.println(ex);
  		  
  	  }
		
	}
	private void SearchPageBack(MouseEvent evt) {
		System.out.println("Hello from Search Page Back btn");
		Start_page sp = new Start_page();
		dispose();
		sp.setVisible(true);
	}
}
