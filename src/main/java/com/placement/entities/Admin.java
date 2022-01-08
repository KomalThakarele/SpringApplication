package com.placement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin 
{
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long aid;
	private String aname;
	private String cname;
	private String address;
	private String website;
	private long contact;
	private String email;
	private String password;
	private String logo;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
		this.aid = aid;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
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

	public Admin(long aid, String aname, String cname, String address, String website, long contact, String email,
			String password, String logo) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.cname = cname;
		this.address = address;
		this.website = website;
		this.contact = contact;
		this.email = email;
		this.password = password;
		this.logo = logo;
	}
	@Override
	public String toString() 
	{
		return "Admin [aid=" + aid + ", aname=" + aname + ", cname=" + cname + ", address=" + address + ", website="
				+ website + ", contact=" + contact + ", email=" + email + ", password=" + password + "]";
	}
	
}
