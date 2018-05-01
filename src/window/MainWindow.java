package window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bean.Student;
import dao.StudentDAO;
import javax.swing.JLabel;

public class MainWindow {

	public static Student tempStu = null; // 临时学生对象
	public static String tempNo = null; // 临时学号
	public static String tempName = null; // 临时姓名
	public static String tempClassroom = null; // 临时班级
	public static int page = 1; // 当前是第几页
	public static final int PERPAGE = 15; // 分页，每页多少条数据

	JFrame frame;
	private JTable table;
	private String[] headers = new String[] { "学号", "姓名", "性别", "身份证号", "班级", "成绩", "学校名称" };
	private JLabel label;
	private JLabel label_1;
	private int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel_north = new JPanel();
		frame.getContentPane().add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new GridLayout(1, 4, 0, 0));

		JButton btnSelect = new JButton("查找学生信息");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tempNo = null;
				tempName = null;
				tempClassroom = null;
				page = 1;
				
				new SelectDialog(frame);

				String sql = "select * from studentInfo where 1=1 ";
				if (tempNo != null) {
					sql += "and no='" + tempNo + "' ";
				}
				if (tempName != null) {
					sql += "and name='" + tempName + "' ";
				}
				if (tempClassroom != null) {
					sql += "and classroom='" + tempClassroom + "' ";
				}

				sql += "limit " + (page * PERPAGE - PERPAGE) + "," + (page * PERPAGE);

				updateTable(sql);
			}
		});
		panel_north.add(btnSelect);

		JButton btnAdd = new JButton("添加学生信息");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddDialog(frame);
				if (tempStu != null) {
					StudentDAO dao = new StudentDAO();
					dao.add(tempStu);
					String sql = "select * from studentinfo where no='" + tempStu.getNo() + "'";
					updateTable(sql);
					dao.close();
					tempStu = null;
				}
			}
		});
		panel_north.add(btnAdd);

		JButton btnAlt = new JButton("修改学生信息");
		btnAlt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddDialog(frame);
				if (tempStu != null) {
					StudentDAO dao = new StudentDAO();
					dao.alt(tempStu);
					String sql = "select * from studentinfo where no='" + tempStu.getNo() + "'";
					updateTable(sql);
					dao.close();
					tempStu = null;
				}
			}
		});
		panel_north.add(btnAlt);

		JButton btnDel = new JButton("删除学生信息");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DelDialog(frame);
				if (tempNo != null) {
					StudentDAO dao = new StudentDAO();
					dao.del(tempNo);
					dao.close();
					tempNo = null;
				}
			}
		});
		panel_north.add(btnDel);

		JPanel panel_center = new JPanel();
		frame.getContentPane().add(panel_center, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, headers));
		panel_center.add(new JScrollPane(table));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);

		label = new JLabel("共 0 条");
		panel.add(label);

		label_1 = new JLabel(" 0 / 0 页");
		panel.add(label_1);

		JButton button_1 = new JButton("上一页");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page > 1) {
					page--;
				}
				String sql = "select * from studentInfo where 1=1 ";
				if (tempNo != null) {
					sql += "and no='" + tempNo + "' ";
				}
				if (tempName != null) {
					sql += "and name='" + tempName + "' ";
				}
				if (tempClassroom != null) {
					sql += "and classroom='" + tempClassroom + "' ";
				}

				sql += "limit " + (page * PERPAGE - PERPAGE) + "," + (page * PERPAGE);

				updateTable(sql);
			}
		});
		panel.add(button_1);

		JButton button = new JButton("下一页");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (page < count / PERPAGE + 1) {
					page++;
				}
				String sql = "select * from studentInfo where 1=1 ";
				if (tempNo != null) {
					sql += "and no='" + tempNo + "' ";
				}
				if (tempName != null) {
					sql += "and name='" + tempName + "' ";
				}
				if (tempClassroom != null) {
					sql += "and classroom='" + tempClassroom + "' ";
				}

				sql += "limit " + (page * PERPAGE - PERPAGE) + "," + (page * PERPAGE);

				updateTable(sql);
			}
		});
		panel.add(button);
	}

	private void updateTable(String sql) {
		String countsql = sql.split("limit")[0].replace("*", "count(*)");
		StudentDAO dao = new StudentDAO();
		ArrayList<Student> students = dao.list(sql);
		String[][] datas = new String[students.size()][7];
		for (int i = 0; i < students.size(); i++) {
			datas[i][0] = students.get(i).getNo();
			datas[i][1] = students.get(i).getName();
			datas[i][2] = students.get(i).getSex();
			datas[i][3] = students.get(i).getNumber();
			datas[i][4] = students.get(i).getClassroom();
			datas[i][5] = students.get(i).getScore();
			datas[i][6] = students.get(i).getSchool();
		}
		table.setModel(new DefaultTableModel(datas, headers));
		count = dao.getCount(countsql);
		dao.close();

		label.setText("共 " + count + " 条");
		label_1.setText(" " + page + " / " + (count / PERPAGE + 1) + " 页");
	}
}
