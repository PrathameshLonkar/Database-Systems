import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPurchases extends JFrame {
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPurchases frame = new AddPurchases();
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
	public AddPurchases() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 498);
		getContentPane().setLayout(null);
		
		txtEid = new JTextField();
		txtEid.setBounds(377, 28, 201, 38);
		getContentPane().add(txtEid);
		txtEid.setColumns(10);
		
		txtPid = new JTextField();
		txtPid.setBounds(377, 76, 201, 38);
		getContentPane().add(txtPid);
		txtPid.setColumns(10);
		
		txtCid = new JTextField();
		txtCid.setBounds(377, 124, 201, 38);
		getContentPane().add(txtCid);
		txtCid.setColumns(10);
		
		txtQty = new JTextField();
		txtQty.setBounds(377, 172, 201, 38);
		getContentPane().add(txtQty);
		txtQty.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter Eid :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(202, 28, 165, 38);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Pid :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(202, 76, 165, 38);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Cid :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(202, 124, 165, 38);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Quantity :");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(202, 172, 165, 38);
		getContentPane().add(lblNewLabel_3);
		
		InsPurchases = new JButton("Insert");
		InsPurchases.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addPurchaseInfo(e);
			}
		});
		InsPurchases.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		InsPurchases.setBounds(418, 220, 123, 38);
		getContentPane().add(InsPurchases);
		
		btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddPurchaseBack(e);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnNewButton.setBounds(10, 10, 111, 30);
		getContentPane().add(btnNewButton);
	}
	private void addPurchaseInfo(MouseEvent evt) {
		System.out.println("Hello from add purchases page");
		String eid = txtEid.getText();
		String pid = txtPid.getText();
		String cid = txtCid.getText();
		int qty = Integer.parseInt(txtQty.getText());
		cleartext();
		OracleDataSource ds;
		Connection conn=null;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			conn = ds.getConnection("sdesai14", "neelmay");
			System.out.println("Connection Successfull	");
			
			Statement stmt = conn.createStatement ();
			
			CallableStatement cs = conn.prepareCall("{call proj2.insert_in_purchase(?,?,?,?,?,?)}");
			cs.setString(1, pid);
			cs.setString(2, eid);
			cs.setString(3, cid);
			cs.setInt(4, qty);
			cs.registerOutParameter(5,OracleTypes.VARCHAR);
			cs.registerOutParameter(6,OracleTypes.VARCHAR);
			cs.execute();
			String msg = cs.getString(5);
			String msg1 = cs.getString(6);
			System.out.println(msg);
			System.out.println(msg1);
			if(msg!=null) {
				JOptionPane.showMessageDialog(null,
	                    msg,
	                    "Message",
	                    JOptionPane.ERROR_MESSAGE);
			}
			if(msg1!=null) {
				JOptionPane.showMessageDialog(null,
	                    msg1,
	                    "Message",
	                    JOptionPane.INFORMATION_MESSAGE);
			}
			JOptionPane.showMessageDialog(null,
                    "Added Successfully",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
			
		  	stmt.close();
		  	conn.close();
		}catch (SQLException ex){
	  		  System.out.println ("\n*** SQLException caught1 in  adding purchases ***\n");
					System.out.println(ex);
					
	  		  
	  	  }
		
	}
	private void cleartext() {
		txtPid.setText("");
		txtEid.setText("");
		txtCid.setText("");
		txtQty.setText("");
		}
	
	private void AddPurchaseBack(MouseEvent e) {
		System.out.println("Hello from Add Purchase Back");
		Start_page sp =new Start_page();
		dispose();
		sp.setVisible(true);
		
	}
	private JTextField txtEid;
	private JTextField txtPid;
	private JTextField txtCid;
	private JTextField txtQty;
	private JButton InsPurchases;
	private JButton btnNewButton;
}
