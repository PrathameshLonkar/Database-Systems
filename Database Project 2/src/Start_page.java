import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.Panel;
import net.proteanit.sql.DbUtils;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;


public class Start_page extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start_page frame = new Start_page();
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
	public Start_page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Display Data");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				displayPanel(e);
			}
		});
		btnNewButton.setBounds(10, 49, 134, 31);
		contentPane.add(btnNewButton);
		
		JButton Search = new JButton("Monthly Report");
		Search.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Search.setBackground(Color.WHITE);
		Search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchPanel(e);
			}
		});
		Search.setBounds(154, 49, 134, 31);
		contentPane.add(Search);
		
		InsertButton = new JButton("Insert Data");
		InsertButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		InsertButton.setBackground(Color.WHITE);
		InsertButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				InsertMouseClickEvent(e);
			}
		});
		InsertButton.setBounds(301, 49, 134, 31);
		contentPane.add(InsertButton);
		
		InsProd = new JButton("Products");
		InsProd.setFont(new Font("Times New Roman", Font.BOLD, 12));
		InsProd.setBackground(Color.WHITE);
		InsProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addProducts(e);
			}
		});
		InsProd.setBounds(281, 90, 85, 21);
		contentPane.add(InsProd);
		InsProd.setVisible(false);
		
		InsPur = new JButton("Purchases");
		InsPur.setFont(new Font("Times New Roman", Font.BOLD, 12));
		InsPur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		InsPur.setBackground(Color.WHITE);
		InsPur.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addPurchases(e);
			}
		});
		InsPur.setBounds(378, 90, 87, 21);
		contentPane.add(InsPur);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SearchData(e);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(445, 49, 134, 31);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Retail Management System");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 10, 579, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("1.)Display The Data in Tables. ");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.ITALIC, 26));
		lblNewLabel_1.setBounds(10, 124, 569, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("2.)Check Monthly Product Sale");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.ITALIC, 26));
		lblNewLabel_2.setBounds(10, 161, 569, 31);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("3.)Insert The Purchase & Product Info.");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.ITALIC, 26));
		lblNewLabel_3.setBounds(10, 202, 569, 31);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("4.)Search For Particular Data.");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.ITALIC, 26));
		lblNewLabel_4.setBounds(10, 243, 569, 38);
		contentPane.add(lblNewLabel_4);
		InsPur.setVisible(false);
	}
	private void displayPanel(MouseEvent evt) {//GEN-FIRST:event_searchPanelMousePressed
	    // TODO add your handling code here:
	
	   System.out.println("Hello from Display");
	   dispose();
		DisplayPage dp = new DisplayPage();
		dp.setVisible(true);
	
	}
	private void searchPanel(MouseEvent evt) {
		System.out.println("Hello From Search");
		dispose();
		SearchPage sp = new SearchPage();
		sp.setVisible(true);
		
	}
	private void InsertMouseClickEvent(MouseEvent evt) {
		System.out.println("Hello From Insert");
		InsProd.setVisible(true);
		InsPur.setVisible(true);
		
	}
	private void addProducts(MouseEvent evt) {
		System.out.println("Hello From add Products");
		dispose();
		AddProducts ap = new AddProducts();
		ap.setVisible(true);
		
	}
	private void addPurchases(MouseEvent evt) {
		System.out.println("Hello From add Purchases");
		dispose();
		AddPurchases apur =new AddPurchases();
		apur.setVisible(true);
		
	}
	
	private void SearchData(MouseEvent evt) {
		System.out.println("Hello From Search Data");
		dispose();
		SearchDataPage sdp = new SearchDataPage();
		sdp.setVisible(true);
		
	}
	private JButton InsProd;
	private JButton InsertButton;
	private JButton InsPur;
}
