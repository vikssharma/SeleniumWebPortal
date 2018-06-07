package com.selenium.controller;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.selenium.model.User;

public class GlobalAction extends ActionSupport{
	
	public String callFreeTrial(){
		return "success";
	}
	
	public String callLogin(){
		return "success";
	}
	
	public String execute(){
		 System.out.println("callWelcome...................");
		String returnString = "newSession";
		boolean existingSession = false;
		ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();
        System.out.println(1);
       if( session.get("USER")!= null && session.get("USER") instanceof User){
    	   System.out.println(2);
    	   User user = (User)session.get("USER");
    	   System.out.println(3);
    	   if(user.getLoggedIn() != null && user.getLoggedIn()==true){
    		   System.out.println(4);
    		  returnString = "existingSession";
    	   }
       }
       System.out.println(returnString);
		return returnString;
	}
	
	public String callLogout(){
		  ActionContext ctx = ActionContext.getContext();  
		  Map session = ctx.getSession();
		  if (session instanceof org.apache.struts2.dispatcher.SessionMap) {
		      try {
		          ((org.apache.struts2.dispatcher.SessionMap) session).invalidate();
		      } catch (IllegalStateException e) {
		          session.remove("USER");
		      }
		  }
		  addActionMessage("User logged out successfully.");
		  return "success";
		 }

}
