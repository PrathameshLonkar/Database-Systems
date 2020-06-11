import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.pool.OracleDataSource;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddProducts extends JFrame {
	private JTextField textPid;
	private JTextField txtPname;
	private JTextField txtQoh;
	private JTextField txtThreshold;
	private JTextField txtPrice;
	private JTextField txtDiscnt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProducts frame = new AddProducts();
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
	public AddProducts() {
		getContentPane().setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 447);
		getContentPane().setLayout(null);
		
		textPid = new JTextField();
		textPid.setBounds(305, 39, 174, 36);
		getContentPane().add(textPid);
		textPid.setColumns(10);
		
		txtPname = new JTextField();
		txtPname.setBounds(305, 85, 174, 36);
		getContentPane().add(txtPname);
		txtPname.setColumns(10);
		
		txtQoh = new JTextField();
		txtQoh.setBounds(305, 131, 174, 36);
		getContentPane().add(txtQoh);
		txtQoh.setColumns(10);
		
		txtThreshold = new JTextField();
		txtThreshold.setBounds(305, 177, 174, 36);
		getContentPane().add(txtThreshold);
		txtThreshold.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(305, 223, 174, 36);
		getContentPane().add(txtPrice);
		txtPrice.setColumns(10);
		
		txtDiscnt = new JTextField();
		txtDiscnt.setBounds(305, 272, 174, 36);
		getContentPane().add(txtDiscnt);
		txtDiscnt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter ProductId :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(137, 36, 158, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Product Name :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(106, 85, 189, 36);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Enter QOH :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(137, 131, 158, 36);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Enter Qoh_Threshold :");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(89, 177, 206, 36);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Price :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setBounds(137, 223, 158, 36);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Enter Discount Rate :");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(137, 272, 158, 36);
		getContentPane().add(lblNewLabel_5);
		
		JButton AddProd = new JButton("Insert");
		AddProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addProductToTable(e);
			}
		});
		AddProd.setBackground(Color.WHITE);
		AddProd.setBounds(348, 333, 102, 36);
		getContentPane().add(AddProd);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AddProductsBack(e);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.ITALIC, 16));
		btnNewButton.setBounds(10, 10, 102, 30);
		getContentPane().add(btnNewButton);
	}
	private void addProductToTable(MouseEvent evt) {
		System.out.println("Hello from add product page");
		String pid = textPid.getText();
		String pname = txtPname.getText();
		int qoh = Integer.parseInt(txtQoh.getText());
		int  threshold= Integer.parseInt(txtThreshold.getText());
		double price = Double.parseDouble(txtThreshold.getText());
		double discnt = Double.parseDouble(txtDiscnt.getText());
		cleartext();
		OracleDataSource ds;
		Connection conn=null;
		try {
			ds = new oracle.jdbc.pool.OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111");
			conn = ds.getConnection("sdesai14", "neelmay");
			System.out.println("Connection Successfull	");
			
			Statement stmt = conn.createStatement ();
			
			CallableStatement cs = conn.prepareCall("{call proj2.insert_in_products(?,?,?,?,?,?,?)}");
			cs.setString(1, pid);
			cs.setString(2, pname);
			cs.setInt(3, qoh);
			cs.setInt(4, threshold);
			cs.setDouble(5, price);
			cs.setDouble(6, discnt);
			cs.registerOutParameter(7,OracleTypes.VARCHAR);
			cs.execute();
			String msg = cs.getString(7);
			if(msg!=null) {
				JOptionPane.showMessageDialog(null,
	                    msg,
	                    "Message",
	                    JOptionPane.ERROR_MESSAGE);
			}else {
			JOptionPane.showMessageDialog(null,
                    "Added Succesfully",
                    "Message",
                    JOptionPane.INFORMATION_MESSAGE);
			}
			
			
			
		  	stmt.close();
		  	conn.close();
		}catch (SQLException ex){
	  		  System.out.println ("\n*** SQLException caught1 in  adding products ***\n");
					System.out.println(ex);
	  		  
	  	  }
		
	}
	private void cleartext() {
		textPid.setText("");
		txtPname.setText("");
		txtQoh.setText("");
		txtThreshold.setText("");
		txtPrice.setText("");
		txtDiscnt.setText("");
		
		
		
		
		
	}
	private void AddProductsBack(MouseEvent evt) {
		System.out.println("Hello From Add Products Back btn");
		Start_page sp = new Start_page();
		dispose();
		sp.setVisible(true);
		
	}
}
