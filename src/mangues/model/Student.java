package mangues.model;

import java.io.Serializable;

public class Student implements Serializable{
	private String sno; //学号
	private String sname;//姓名
	private Integer sage; //年龄
	private String ssex; //性别
	public Student() {
		super();
	}
	public Student(String sno, String sname, Integer sage, String ssex) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.sage = sage;
		this.ssex = ssex;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getSage() {
		return sage;
	}
	public void setSage(Integer sage) {
		this.sage = sage;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	
	
}
