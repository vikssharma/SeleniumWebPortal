package com.selenium.controller;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.codec.digest.DigestUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.Project;
import com.selenium.model.User;
import com.selenium.util.ExecuteTestCaseUtil;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SignupAction extends ActionSupport{
	
	SeleniumDAO dao = new SeleniumDAOImpl();
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String applicationName;
	
	private String message;
	
	public String execute(){
		
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setEmail(email);
			user.setUserName(username);
			user.setPassword(password);
			String hash =  DigestUtils.md5Hex(String.valueOf(Math.random()*32));
			user.setHash(hash);
			user.setActive(0);
			System.out.println( hash );
			
			Project project = new Project();
			project.setDomain("com");
			project.setApplication(applicationName);
			user.setProject(project);
			
			dao.addUser(user);
			sendMail(hash);
			setMessage("Your account has been created. Please verify it by clicking the activation link sent to your mail.");
		return "success";
	}
	
	public String callSignup(){
		return "success";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void validate(){
	     
		boolean userExists = dao.findUserExists(getUsername(), getEmail());
		boolean applicationExists = dao.findApplicationExists(applicationName);
		if(userExists){
			
				 addActionError( "User already exists with the Username / Email." );
		}
		else if(applicationExists){
			 addActionError( "Application with this name already exists in the database. Please select another application name." );
		}  
		else{
			validateUsername();
		}
		
	         
	 }
	
	private void validateUsername(){
		if(username == null || username.length()==0){
			addActionError("Username cannot be empty.");
		}
		else if(username.length()<4){
			addActionError("Username length cannot be less than 4 characters.");
		}
		
	}
	
	
	
	 private void sendMail(String hash) {
	    	
	    	final String username = "seleniumwebportal@gmail.com";
			final String password = "seleniumwebportal1";


			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("from-email@gmail.com"));
				message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(getEmail()));
				message.setSubject("Welcome to Selenium Web Portal");
				StringBuffer text = new StringBuffer();
				text.append("Dear "+getFirstName()+" "+getLastName()+",");
				text.append("\n\n Thanks for signing up!");
				text.append("\n Your account has been created, you can login with the following credentials.")
					.append("\n\n")
					.append("Username : "+getUsername())
					.append("\n Password : "+getPassword())
					.append("\n\n")
					.append("Please click this link to activate your account:")
					.append("\n")
	//				.append("http://111.118.214.245:8080/SeleniumWebPortal/verify?email="+getEmail()+"&hash="+hash);
					.append(applicationURL()+"/verify?email="+getEmail()+"&hash="+hash);
				//.append("./verify?email="+getEmail()+"&hash="+hash);
				message.setText(text.toString());
				Transport.send(message);
				
			} catch (Exception e) {
				setMessage("Error in sending mail");
				System.out.println("Email Action 4 "+e.getMessage());
			}
		}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	private  String applicationURL() throws Exception{
		 InputStream inputStream = SignupAction.class.getClassLoader().getResourceAsStream( "/selenium.properties" );
	        Properties properties = new Properties();
	        
	            properties.load( inputStream );
	            String applicationURL = properties.getProperty( "application_url" );
	            return applicationURL;
	}





}
