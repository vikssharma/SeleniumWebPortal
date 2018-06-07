package com.selenium.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.User;

public class VerifyAction extends ActionSupport{
	
	private String email;
	
	private String hash;
	
	private String message;
	
	public String execute(){
		
		return "success";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void validate(){
		
		SeleniumDAO dao = new SeleniumDAOImpl();
		boolean verify = dao.activateUser(email, hash);
		if(verify){
			addActionMessage("Your account has been activated, you can now login.");
		}
		else{
			addActionError("Your account could not be activated. Please check if it is already active.");
		}
	     
	   
	   
	}
	

}
