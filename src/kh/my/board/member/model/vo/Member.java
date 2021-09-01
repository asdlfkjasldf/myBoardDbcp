package kh.my.board.member.model.vo;

import java.sql.Date;

//----------- -------- ------------- 
//MEMBER_ID   NOT NULL VARCHAR2(30)  
//MEMBER_PWD  NOT NULL VARCHAR2(30)  
//MEMBER_NAME NOT NULL VARCHAR2(30)  
//GENDER               CHAR(1)       
//EMAIL                VARCHAR2(50)  
//PHONE                VARCHAR2(30)  
//ADDRESS              VARCHAR2(100) 
//AGE                  NUMBER        
//ENROLL_DATE          DATE

// TODO:
// 한땀한땀 작성
// getter // settter
// toString  손으로 작성
// constructor (full 인자)
public class Member {
	private String id;
	private String pwd;
	private String name;
	private char gender;
	private String email;
	private String phone;
	private String address;
	private int age;
	private int point;
	private Date enrollDate;
	
	public Member() {}
	public Member(String id, String pwd, String name, char gender, String email, String phone, String address, int age) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.age = age;
	}
	public Member(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getEnroll_date() {
		return enrollDate;
	}

	public void setEnroll_date(Date enroll_date) {
		this.enrollDate = enroll_date;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", gender=" + gender + ", age=" + age + ", point=" + point + ", enroll_date="
				+ enrollDate + "]";
	}
	
}
