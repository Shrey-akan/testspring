package com.demo.oragejobsite.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nid;
	private String nhead;
	private String nsubhead;
	private String ndescription;
	private String notisend;
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(int nid, String nhead, String nsubhead, String ndescription, String notisend) {
		super();
		this.nid = nid;
		this.nhead = nhead;
		this.nsubhead = nsubhead;
		this.ndescription = ndescription;
		this.notisend = notisend;
	}

	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNhead() {
		return nhead;
	}
	public void setNhead(String nhead) {
		this.nhead = nhead;
	}
	public String getNsubhead() {
		return nsubhead;
	}
	public void setNsubhead(String nsubhead) {
		this.nsubhead = nsubhead;
	}
	public String getNdescription() {
		return ndescription;
	}
	public void setNdescription(String ndescription) {
		this.ndescription = ndescription;
	}

	public String getNotisend() {
		return notisend;
	}

	public void setNotisend(String notisend) {
		this.notisend = notisend;
	}
	
}

