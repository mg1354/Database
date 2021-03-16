package MG;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		JLabel f2 = new JLabel("-----");
		f2.setBounds(35, 270, 80, 25);
		panel.add(f2);
		JTextField f3 = new JTextField("Adress");
		f3.setBounds(120, 270, 100, 25);
		panel.add(f3);
		JTextField f4 = new JTextField("Phone1");
		f4.setBounds(225, 270, 45, 25);
		panel.add(f4);
		JTextField f5 = new JTextField("Phone2");
		f5.setBounds(275, 270, 45, 25);
		panel.add(f5);
		JTextField f6 = new JTextField("Salary");
		f6.setBounds(325, 270, 45, 25);
		panel.add(f6);
		JTextField f7 = new JTextField("leeavs");
		f7.setBounds(375, 270, 40, 25);
		panel.add(f7);

		button = new JButton("Change");
		button.setBounds(160, 310, 100, 25);
		panel.add(button);

		JLabel change = new JLabel("");
		change.setBounds(10, 350, 460, 40);
		panel.add(change);

		show(con);
		
		button.addActionListener(e -> {
			int in1 = Integer.parseInt(f1.getText());
			
			String in3 = f3.getText();
			int in4 = Integer.parseInt(f4.getText());
			int in5 = Integer.parseInt(f5.getText());
			int in6 = Integer.parseInt(f6.getText());
			int in7 = Integer.parseInt(f7.getText());

			String query = "update personal set Adress=?,Phone1=?,Phone2=?,Salary=?,Remaining_leaves=? where Id= ?";
			try {
				PreparedStatement ps = con.prepareStatement(query);

				ps.setString(1, in3);
				ps.setInt(2, in4);
				ps.setInt(3, in5);
				ps.setInt(4, in6);
				ps.setInt(5, in7);
				ps.setInt(6, in1);
				ps.execute();

				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			p1.setText("");
			show(con);

		});

		frame.setVisible(true);

	}
	
	static void show(Connection con) {
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
						+ " \nId  *   Name     *          Adress           *  Phone1  *  Phone2   *  Salary  *Remaining leavs "
						+ "\n" + id + "_" + name + "  *  " + adress + "  *  " + phone1 + "  *  " + phone2 + "  *  "
						+ salary + "   *   " + rl);

			}

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
	}

}
