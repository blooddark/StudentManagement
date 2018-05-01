package bean;

public class Student {
	private int id;
	private String no;
	private String name;
	private String sex;
	private String number;
	private String classroom;
	private String score;
	private String school;
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", no=" + no + ", name=" + name + ", sex=" + sex + ", number=" + number
				+ ", classroom=" + classroom + ", score=" + score + ", school=" + school + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
}
