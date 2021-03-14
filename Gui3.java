package MG;

import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui3 {

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel personal1;
	private static JTextArea p1;

	private static JButton button;

	
	public Gui3(Connection con) {
		frame = new JFrame();
		panel = new JPanel();
		frame.setSize(480, 450);
		frame.setTitle("Personal");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(1050, 0);
		frame.add(panel);

		panel.setLayout(null);

		personal1 = new JLabel("Personal1:");
		personal1.setBounds(10, 10, 80, 25);
		panel.add(personal1);

		p1 = new JTextArea();
		p1.setBounds(10, 40, 460, 200);
		panel.add(p1);

		JTextField f1 = new JTextField("ID");
		f1.setBounds(10, 270, 20, 25);
		panel.add(f1);
		JTextField f2 = new JTextField("column name");
		f2.setBounds(40, 270, 100, 25);
		panel.add(f2);
		JTextField f3 = new JTextField("new value");
		f3.setBounds(160, 270, 100, 25);
		panel.add(f3);

		button = new JButton("Save");
		button.setBounds(160, 310, 100, 25);
		panel.add(button);

		JLabel change = new JLabel("");
		change.setBounds(10, 350,460, 40);
		panel.add(change);

		try {

			String query = "select * from personal";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String adress = rs.getString("Adress");
				int phone1 = rs.getInt("Phone1");
				int phone2 = rs.getInt("Phone2");
				int salary = rs.getInt("Salary");
				int rl = rs.getInt("Remaining_leaves");

				String x = p1.getText();
				p1.setText(x
						+ "\n \nId  *   Name     *          Adress           *  Phone1  *  Phone2   *  Salary  *Remaining leavs "
						+ "\n" + id + "_" + name + "  *  " + adress + "  *  " + phone1 + "  *  " + phone2 + "  *  "
						+ salary + "   *   " + rl);

			}

			st.close();

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		
		button.addActionListener(e -> {

			
		});

		frame.setVisible(true);

	}

}
