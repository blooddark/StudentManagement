package window;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.User;
import dao.UserDAO;

public class LoginWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private static LoginWindow frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					frame = new LoginWindow();
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
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("用户名：");
		contentPane.add(label);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("密码：");
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserDAO dao = new UserDAO();
				User user = dao.check(textField.getText(), textField_1.getText());
				if (user != null) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								MainWindow window = new MainWindow();
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					frame.setVisible(false);
				}
			}
		});
		contentPane.add(button);
		
		JButton button_1 = new JButton("取消");
		contentPane.add(button_1);
	}

}
