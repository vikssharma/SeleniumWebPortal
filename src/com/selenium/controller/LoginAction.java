package com.selenium.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.User;

public class LoginAction extends ActionSupport{
	
	private String username;
	
	private String password;
	
	private User user;
	
	SeleniumDAO dao = new SeleniumDAOImpl();
	
	
	public String execute(){
		 ActionContext ctx = ActionContext.getContext();  
	        Map session = ctx.getSession();
	        getUser().setLoggedIn(true);
	        session.put("USER",getUser());
		return "success";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void validate(){
	     
	    if ( username!=null && username.length()>0 
	    		&& password!=null && password.length()>0){ 
	    	
	    	User user = dao.findUser(username, password);
	    	setUser(user);
			if(user==null){
				 addActionError( "Invalid Username or Password." );
			}
			else if(user.getActive()==0){
				addActionError( "User is not active." );
			}
			else if(user.trialExpired()){
				addActionError( "The free trial has expired. Please buy license for further use." );
			}
	         
	    }
	     
	             
	   
	}
	

}
