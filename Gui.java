package MG;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Gui {

	private static JFrame frame;
	private static JPanel panel;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField password;
	private static JButton button;
	private static JLabel success;
	private static String user;
	private static String pass;
	private static Connection con;

//    public static void main(String[] args) {

	@SuppressWarnings("deprecation")
	public Gui() {

		frame = new JFrame("Wellcome to bibliotek!");
		panel = new JPanel();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(null);

		userLabel = new JLabel("User name");
		userLabel.setBounds(10, 60, 80, 25);
		panel.add(userLabel);

		userText = new JTextField();
		userText.setBounds(100, 60, 150, 25);
		panel.add(userText);

		passwordLabel = new JLabel("password");
		passwordLabel.setBounds(10, 100, 80, 25);
		panel.add(passwordLabel);

		password = new JPasswordField();
		password.setBounds(100, 100, 150, 25);
		panel.add(password);

		button = new JButton("Login");
		button.setBounds(100, 140, 150, 25);
		panel.add(button);

		button.addActionListener(e -> {
			user = getUser();
			pass = password.getText();

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");

				final String url = "jdbc:mysql:///bibliotek";

				con = DriverManager.getConnection(url, user, pass);

				Gui.success.setText("Wellcome to bibliotek!");

				Gui.frame.setVisible(false);

				if (user.equals("Viggo") || user.equals("Pelle") || user.equals("Bosse") || user.equals("Explorer")
						|| user.equals("Elof")) {
					new Gui1(con);
				} else if (user.equals("Asta") || user.equals("Farbror")) {
					new Gui2(con);
				} else if (user.equals("Ebba")) {
					new Gui2(con);
					new Gui3(con);
				}

			}

			catch (Exception e1) {
				Gui.success.setText("Login unsuccessful!");

			}

		});

		success = new JLabel("");
		success.setBounds(10, 180, 200, 25);
		panel.add(success);

		frame.setVisible(true);

	}

	public static String getUser() {
		Gui.user = userText.getText();
		return user;
	}

}
