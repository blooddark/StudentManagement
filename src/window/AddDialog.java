package window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bean.Student;

public class AddDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Create the dialog.
	 */
	public AddDialog(Frame owner) {
		super(owner, "增加或修改" , true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 270, 354);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				JLabel label = new JLabel("学号：");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("姓名：");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("性别：");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("身份证号：");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("班级：");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("成绩：");
				panel.add(label);
			}
			{
				JLabel label = new JLabel("学校：");
				panel.add(label);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				textField_2 = new JTextField();
				panel.add(textField_2);
				textField_2.setColumns(10);
			}
			{
				textField_3 = new JTextField();
				panel.add(textField_3);
				textField_3.setColumns(10);
			}
			{
				textField_4 = new JTextField();
				textField_4.setColumns(10);
				panel.add(textField_4);
			}
			{
				textField_5 = new JTextField();
				textField_5.setColumns(10);
				panel.add(textField_5);
			}
			{
				textField_6 = new JTextField();
				textField_6.setColumns(10);
				panel.add(textField_6);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Student stu = new Student();
						stu.setNo(textField.getText());
						stu.setName(textField_1.getText());
						stu.setSex(textField_2.getText());
						stu.setNumber(textField_3.getText());
						stu.setClassroom(textField_4.getText());
						stu.setScore(textField_5.getText());
						stu.setSchool(textField_6.getText());
						MainWindow.tempStu = stu;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		setVisible(true);
	}

}
