package com.placement.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recruiter 
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long rid;	
	private String rname;
	private String company;
	private String address;
	private long pno;
	private String email;
	private String website;
	private String password;
	@Column(nullable=true)
	private String logo;
	
	@Override
	public String toString() 
	{
		return "Recruiter [rid=" + rid + ", company=" + company + ", address=" + address + ", pno=" + pno + ", email="
				+ email + ", website=" + website + ", password=" + password + "]";
	}
	
	public Recruiter() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	

	public Recruiter(String rname, String company, String address, long pno, String email, String website, String password) 
	{
		this.rname = rname;
		this.company = company;
		this.address = address;
		this.pno = pno;
		this.email = email;
		this.website = website;
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPno() {
		return pno;
	}
	public void setPno(long pno) {
		this.pno = pno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getRid() {
		return rid;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}

}
