package com.placement.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sid;
	private String sname;
	private String course;
	private String branch;
	private Date dob;
	private float s_marks;
	private float hs_marks;
	private float b_marks;
	private float m_marks;
	@Column(nullable = true)
	private String cv_url; 
	@Column(nullable = true)
	private String profile_pic;
	private String address;
	private long contact;
	private String email;
	private String password;
	
	public String getSname() {
		return sname;
	}
	
	public void setSname(String sname) {
		this.sname = sname;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public float getS_marks() {
		return s_marks;
	}
	public void setS_marks(float s_marks) {
		this.s_marks = s_marks;
	}
	public float getHs_marks() {
		return hs_marks;
	}
	public void setHs_marks(float hs_marks) {
		this.hs_marks = hs_marks;
	}
	public float getB_marks() {
		return b_marks;
	}
	public void setB_marks(float b_marks) {
		this.b_marks = b_marks;
	}
	public float getM_marks() {
		return m_marks;
	}
	public void setM_marks(float m_marks) {
		this.m_marks = m_marks;
	}
	public String getCv_url() {
		return cv_url;
	}
	public void setCv_url(String cv_url) {
		this.cv_url = cv_url;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getSid() {
		return sid;
	}
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Student(String sname, String course, String branch, Date dob, float s_marks, float hs_marks, float b_marks, float m_marks, String cv_url, String profile_pic, String address, long contact, String email, String password)
	{
		this.sname = sname;
		this.course = course;
		this.branch = branch;
		this.dob = dob;
		this.s_marks = s_marks;
		this.hs_marks = hs_marks;
		this.b_marks = b_marks;
		this.m_marks = m_marks;
		this.cv_url = cv_url;
		this.profile_pic = profile_pic;
		this.address = address;
		this.contact = contact;
		this.email = email;
		this.password = password;
	}
	

}
