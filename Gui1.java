package MG;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui1 {

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel bookName;
	private static JTextField bn;
	private static JButton bookInfo;
	private static JTextArea bi;
	private static JLabel journalName;
	private static JTextField jn;
	private static JLabel journalDate;
	private static JTextField jd;
	private static JButton journalShelf;
	private static JTextField js;
	private static JButton barrow;
	private static JLabel bb;
	
	public Gui1(Connection con) {
		
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(400, 450);
		frame.setTitle("Books");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(320, 0);
		frame.add(panel);

		panel.setLayout(null);

		bookName = new JLabel("Book name:");
		bookName.setBounds(10, 20, 100, 25);
		panel.add(bookName);

		bn = new JTextField();
		bn.setBounds(120,20,200,25);
		panel.add(bn);

		bookInfo = new JButton("Show Information");
		bookInfo.setBounds(10, 50, 100, 25);
		panel.add(bookInfo);

		bi = new JTextArea();
		bi.setBounds(120, 50, 200, 100);
		panel.add(bi);
		
		barrow = new JButton("Barrow book");
		barrow.setBounds(150, 180, 120, 25);
		panel.add(barrow);
		
		bb =new JLabel("");
		bb.setBounds(120,220,200,25);
		panel.add(bb);
		
		
		journalName = new JLabel(" Journal name:");
		journalName.setBounds(10,270,100,25); 
		panel.add(journalName);
		
		jn = new JTextField();
		jn.setBounds(120,270,200,25);
		panel.add(jn);
		
		journalDate = new JLabel("Journal date:");
		journalDate.setBounds(10,310,100,25);
		panel.add(journalDate);
		
		jd = new JTextField();
		jd.setBounds(120,310,200,25);
		panel.add(jd);
		
        journalShelf = new JButton("Show shelf");
        journalShelf.setBounds(10,350,100,25);
		panel.add(journalShelf);
		
		js= new JTextField();
		js.setBounds(120,350,200,25);
		panel.add(js);
		
		
		bookInfo.addActionListener(e -> {
			String x = bn.getText();
			String query = "select  Writer, Pages, Classification from  books,class  where title like ?";
			 try { 
				    PreparedStatement ps = con.prepareStatement( query);
				    ps.setString(1, x);
				    ResultSet rs = ps.executeQuery();
				    
				    while (rs.next()) {
				    	
				    	String writer = rs.getString("Writer");
				    	int pages = rs.getInt("Pages");
				    	String Class = rs.getString("Classification");
				    	bi.setText("Writer: "+writer + "\n" + "Pages:  "+ pages + "\n" + "Class:  " + Class);
	
				    }
				    
				ps.close();
				
				
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

		});
		
		barrow.addActionListener(e -> {
			String x = bn.getText();
			String user = Gui.getUser();
			
					
			 try { 
				 
				 String query = "INSERT INTO customer (ID, Name, Adress, Phone, Kard_num , Barrowed_books ) "
							+ "(SELECT ID, Name, Adress, Phone, Kard_num , Bok_id FROM  customer, books "
							+ " where Title like ? and Name like ?% and Bok_id not exists (SELECT Barrowed_books FROM customer )";
				   
				 PreparedStatement ps = con.prepareStatement( query);
				    ps.setString(1, x);
				    ps.setString(1, user);
				    
				    ResultSet rs = ps.executeQuery();
				    
				    while (rs.next()) {
				    	
				    	bb.setText(" You borrowed the book." );
	
				    }
				    
				ps.close();
				
				
				} catch (SQLException e1) {
					bb.setText(" You can not borrow the book." );
					e1.printStackTrace();
				}

		});

		journalShelf.addActionListener(e -> {
			String x = jn.getText();
			String y = jd.getText();
			String query = "select  Shelf from  journals  where title like ? and date like ?";
			 try { 
				    PreparedStatement ps = con.prepareStatement( query);
				    ps.setString(1, x);
				    ps.setString(2, y);
				    ResultSet rs = ps.executeQuery();
				    
				    while (rs.next()) {
				    	
				    	String shelf = rs.getString("Shelf");
				    	
				    	
				    	js.setText(shelf);
	
				    }
				    
				ps.close();
				
				
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}

		});

		frame.setVisible(true);

	}
	}


