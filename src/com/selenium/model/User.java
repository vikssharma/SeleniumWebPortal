package com.selenium.model;

import java.io.Serializable;




import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="User")
public class User implements Serializable{
	private static final long serialVersionUID = -8767337896773261247L;

	private Long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	private String hash;
	private int active;
	private int license;
	private Date startDate;
	private Project project;
	private Boolean loggedIn = false;
	
	
	@Id
	@GeneratedValue
	@Column(name="userid")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="firstname")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="lastname")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="username")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name="hash")
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	@Column(name="active")
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	@Column(name="license")
	public int getLicense() {
		return license;
	}
	public void setLicense(int license) {
		this.license = license;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="startDate")
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="projectId")
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	
	public boolean trialExpired(){
		boolean expired = false;
		if(license==0){
			Calendar expiryDate = Calendar.getInstance();
			expiryDate.setTime(startDate); // Now use today date.
			expiryDate.add(Calendar.DATE, 30); // Adding 30 days
			
			Calendar today = Calendar.getInstance();
			today.setTime(new Date());
			
			if(expiryDate.before(today)){
				expired = true;
			}
			
		}
		
		return expired;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	
	
	
	

}
