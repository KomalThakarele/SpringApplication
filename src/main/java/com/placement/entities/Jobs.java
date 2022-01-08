package com.placement.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Jobs 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jid;	
	private String company;
	@Column(length= 500)
	private String jd;	
	private long rid;
	private Date pdate;
	private Date edate;
	@Column(nullable=true)
	private String status;
	private String jlocation;
	private long salary;
	private String jrole;
	private String rlogo;
	
	public String getCompany() {
		return company;
	}
	public void setJid(long jid) {
		this.jid = jid;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getRlogo() {
		return rlogo;
	}
	public void setRlogo(String rlogo) {
		this.rlogo = rlogo;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJlocation() {
		return jlocation;
	}
	public void setJlocation(String jlocation) {
		this.jlocation = jlocation;
	}
	public long getSalary() {
		return salary;
	}
	public void setSalary(long salary) {
		this.salary = salary;
	}
	public String getJrole() {
		return jrole;
	}
	public void setJrole(String jrole) {
		this.jrole = jrole;
	}
	public long getJid() {
		return jid;
	}

	public Jobs(long jid, String company, String jd, long rid, Date pdate, Date edate, String status, String jlocation,
			long salary, String jrole, String rlogo) {
		super();
		this.jid = jid;
		this.company = company;
		this.jd = jd;
		this.rid = rid;
		this.pdate = pdate;
		this.edate = edate;
		this.status = status;
		this.jlocation = jlocation;
		this.salary = salary;
		this.jrole = jrole;
		this.rlogo = rlogo;
	}
	
	public Jobs() {
		super();
		// TODO Auto-generated constructor stub
	}


}
