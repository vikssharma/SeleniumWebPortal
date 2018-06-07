package com.selenium.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.selenium.model.TestCase;
import com.selenium.util.ExecuteTestCaseUtil;

public class ExecuteAction {
	
	private Integer testCaseId;
	
	private String message="";
	private InputStream inputStream;
	
	public String runTestCase() throws Exception{
//		System.out.println(testCaseId);
//		ExecuteTestCaseUtil executeUtil = new ExecuteTestCaseUtil();
//		executeUtil.executeTestCase();
//		inputStream = new ByteArrayInputStream(getMessage().getBytes());  
		
		return "success";
	}

	public Integer getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(Integer testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	

}
