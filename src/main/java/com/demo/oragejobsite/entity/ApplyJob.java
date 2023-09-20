package com.demo.oragejobsite.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ApplyJob {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int juid;
	private String juname;
	private String jumail;
	private String jucompny;
	private String juresume;
	private String jurelocation;
	private String jueducation;
	private String juexperience;
	private String juexpinjava;
	private String juexpjsp;
	private String juinterviewdate;
	private String jujavavalid;
	private String jujobtitle;
	private String jucompanyname;
	public ApplyJob() {	
		super();
		// TODO Auto-generated constructor stub
	}

	
	public ApplyJob(int juid, String juname, String jumail, String jucompny, String juresume, String jurelocation,
			String jueducation, String juexperience, String juexpinjava, String juexpjsp, String juinterviewdate,
			String jujavavalid, String jujobtitle, String jucompanyname) {
		super();
		this.juid = juid;
		this.juname = juname;
		this.jumail = jumail;
		this.jucompny = jucompny;
		this.juresume = juresume;
		this.jurelocation = jurelocation;
		this.jueducation = jueducation;
		this.juexperience = juexperience;
		this.juexpinjava = juexpinjava;
		this.juexpjsp = juexpjsp;
		this.juinterviewdate = juinterviewdate;
		this.jujavavalid = jujavavalid;
		this.jujobtitle = jujobtitle;
		this.jucompanyname = jucompanyname;
	}



	public int getJuid() {
		return juid;
	}
	public void setJuid(int juid) {
		this.juid = juid;
	}
	public String getJuname() {
		return juname;
	}
	public void setJuname(String juname) {
		this.juname = juname;
	}
	public String getJuresume() {
		return juresume;
	}
	public void setJuresume(String juresume) {
		this.juresume = juresume;
	}
	public String getJurelocation() {
		return jurelocation;
	}
	public void setJurelocation(String jurelocation) {
		this.jurelocation = jurelocation;
	}
	public String getJueducation() {
		return jueducation;
	}
	public void setJueducation(String jueducation) {
		this.jueducation = jueducation;
	}
	public String getJuexperience() {
		return juexperience;
	}
	public void setJuexperience(String juexperience) {
		this.juexperience = juexperience;
	}
	public String getJuexpinjava() {
		return juexpinjava;
	}
	public void setJuexpinjava(String juexpinjava) {
		this.juexpinjava = juexpinjava;
	}
	public String getJuexpjsp() {
		return juexpjsp;
	}
	public void setJuexpjsp(String juexpjsp) {
		this.juexpjsp = juexpjsp;
	}
	public String getJuinterviewdate() {
		return juinterviewdate;
	}
	public void setJuinterviewdate(String juinterviewdate) {
		this.juinterviewdate = juinterviewdate;
	}
	public String getJujavavalid() {
		return jujavavalid;
	}
	public void setJujavavalid(String jujavavalid) {
		this.jujavavalid = jujavavalid;
	}
	public String getJujobtitle() {
		return jujobtitle;
	}
	public void setJujobtitle(String jujobtitle) {
		this.jujobtitle = jujobtitle;
	}
	public String getJucompanyname() {
		return jucompanyname;
	}
	public void setJucompanyname(String jucompanyname) {
		this.jucompanyname = jucompanyname;
	}

	public String getJumail() {
		return jumail;
	}

	public void setJumail(String jumail) {
		this.jumail = jumail;
	}



	public String getJucompny() {
		return jucompny;
	}



	public void setJucompny(String jucompny) {
		this.jucompny = jucompny;
	}
	
	
}

