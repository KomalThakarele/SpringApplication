package com.placement.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JobApplicant 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jaid;
	private long sid;
	private String aname;
	private long jid;
	private String jrole;
	private String company;
	private String location;
	private String status;
	private String raction;
	
	public long getJaid() {
		return jaid;
	}
	public String getRaction() {
		return raction;
	}
	public void setRaction(String raction) {
		this.raction = raction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setJaid(long jaid) {
		this.jaid = jaid;
	}
	public long getSid() {
		return sid;
	}
	public String getJrole() {
		return jrole;
	}
	public void setJrole(String jrole) {
		this.jrole = jrole;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setSid(long sid) {
		this.sid = sid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public long getJid() {
		return jid;
	}
	public void setJid(long jid) {
		this.jid = jid;
	}
	

	
	public JobApplicant(long jaid, long sid, String aname, long jid, String jrole, String company, String location,
			String status, String raction) {
		super();
		this.jaid = jaid;
		this.sid = sid;
		this.aname = aname;
		this.jid = jid;
		this.jrole = jrole;
		this.company = company;
		this.location = location;
		this.status = status;
		this.raction = raction;
	}
	public JobApplicant() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "JobApplicant [jaid=" + jaid + ", sid=" + sid + ", aname=" + aname + ", jid=" + jid + ", jrole=" + jrole
				+ ", company=" + company + ", location=" + location + ", status=" + status + ", raction=" + raction
				+ "]";
	}

	

}
