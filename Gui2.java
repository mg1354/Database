package MG;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Gui2 {

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel customerAndBooks;
	private static JTextArea cb;
		
//	public static void main(String[] args) {

	public Gui2(Connection conn) throws ClassNotFoundException {
		
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(300, 400);
		frame.setTitle("Customers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(740, 0);
		frame.add(panel);

		panel.setLayout(null);

		customerAndBooks = new JLabel("User and barrowed books:");
		customerAndBooks.setBounds(10, 20, 200, 25);
		panel.add(customerAndBooks);

		cb = new JTextArea();
		cb.setBounds(10,50,370,400);
		panel.add(cb);
		
		
        try {
			
		    String query = "select Name , Title from customer , books where Barrowed_books = Bok_id";
		    Statement st = conn.createStatement();
		    ResultSet rs = st.executeQuery(query);
		    int i =1;
		    while (rs.next()) {
		    	String name = rs.getString("Name");
		    	String book = rs.getString("Title");
		    	String x = cb.getText();
		    	cb.setText(  x +"\n" + i+ "- "+ name +"   ;    " + book+"\n----------------------"+"\n");
//		    	System.out.println(name +"  ," + book);
		    	i++;
		    }
		    
		st.close();
		
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		frame.setVisible(true);
		
	}

}
