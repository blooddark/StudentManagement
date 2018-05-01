package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.Student;
import util.DBConnection;

public class StudentDAO {
	private Connection conn = null;
	private Statement st = null;

	/**
	 * 初始化dao
	 */
	public StudentDAO() {
		conn = DBConnection.getConnection();
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取count（*）的结果
	 * @param sql
	 * @return
	 */
	public int getCount(String sql) {
		int count = 0;
		try {
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 按sql语句查找学生的信息
	 * 
	 * @return
	 */
	public ArrayList<Student> list(String sql) {
		ArrayList<Student> students = new ArrayList<>();
		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setId(rs.getInt("id"));
				stu.setClassroom(rs.getString("classroom"));
				stu.setNumber(rs.getString("number"));
				stu.setName(rs.getString("name"));
				stu.setNo(rs.getString("no"));
				stu.setSex(rs.getString("sex"));
				stu.setScore(rs.getString("score"));
				stu.setSchool(rs.getString("school"));
				students.add(stu);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return students;
	}

	/**
	 * 增加学生信息
	 * 
	 * @param stu
	 *            学生信息
	 */
	public void add(Student stu) {
		String sql = "insert into studentInfo values(0,'" + stu.getNo() + "','" + stu.getName() + "','" + stu.getSex()
				+ "','" + stu.getNumber() + "','" + stu.getClassroom() + "','" + stu.getScore() + "','" + stu.getSchool() + "')";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除学生信息
	 * 
	 * @param no
	 *            学号
	 */
	public void del(String no) {
		String sql = "delete from studentInfo where no='" + no + "'";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改学生信息，根据学号修改
	 * 
	 * @param stu
	 *            修改后的学生信息
	 */
	public void alt(Student stu) {
		del(stu.getNo());
		add(stu);
	}
}
