package com.demo.oragejobsite.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employer {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empid;
	private String empfname;
	private String emplname;
	private String empcompany;
	private String empmailid;
	private String emppass;
	private Long empphone;
	private String empcountry;
	private String empstate;
	private String empcity;
	private String descriptionemp;
	public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employer(int empid, String empfname, String emplname, String empcompany, String empmailid, String emppass,
			Long empphone, String empcountry, String empstate, String empcity, String descriptionemp) {
		super();
		this.empid = empid;
		this.empfname = empfname;
		this.emplname = emplname;
		this.empcompany = empcompany;
		this.empmailid = empmailid;
		this.emppass = emppass;
		this.empphone = empphone;
		this.empcountry = empcountry;
		this.empstate = empstate;
		this.empcity = empcity;
		this.descriptionemp = descriptionemp;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpfname() {
		return empfname;
	}
	public void setEmpfname(String empfname) {
		this.empfname = empfname;
	}
	public String getEmplname() {
		return emplname;
	}
	public void setEmplname(String emplname) {
		this.emplname = emplname;
	}
	public String getEmpcompany() {
		return empcompany;
	}
	public void setEmpcompany(String empcompany) {
		this.empcompany = empcompany;
	}
	public String getEmpmailid() {
		return empmailid;
	}
	public void setEmpmailid(String empmailid) {
		this.empmailid = empmailid;
	}
	public String getEmppass() {
		return emppass;
	}
	public void setEmppass(String emppass) {
		this.emppass = emppass;
	}
	public Long getEmpphone() {
		return empphone;
	}
	public void setEmpphone(Long empphone) {
		this.empphone = empphone;
	}
	public String getEmpcountry() {
		return empcountry;
	}
	public void setEmpcountry(String empcountry) {
		this.empcountry = empcountry;
	}
	public String getEmpstate() {
		return empstate;
	}
	public void setEmpstate(String empstate) {
		this.empstate = empstate;
	}
	public String getEmpcity() {
		return empcity;
	}
	public void setEmpcity(String empcity) {
		this.empcity = empcity;
	}
	public String getDescriptionemp() {
		return descriptionemp;
	}
	public void setDescriptionemp(String descriptionemp) {
		this.descriptionemp = descriptionemp;
	}
	
	

}

