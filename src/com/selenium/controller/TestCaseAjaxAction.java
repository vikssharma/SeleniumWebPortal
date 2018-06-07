package com.selenium.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.TestCase;
import com.selenium.model.TestCaseStep;
import com.selenium.model.User;
import com.selenium.util.JSONUtil;


public class TestCaseAjaxAction {
	
	private int testCaseId;
	
	private int folderId;
	
	private InputStream inputStream;
	 
	private String title;
		
		
		public String getTestCaseScenarios() throws Exception{
			SeleniumDAO dao = new SeleniumDAOImpl();
			List<String> list = dao.getTestCaseScenarios(testCaseId);
			String response = JSONUtil.processTestCaseScenarios(list);
						
			 	inputStream = new ByteArrayInputStream(response.getBytes()); 
			return "success";
		}
		
		public String getFolderScenarios() throws Exception{
			SeleniumDAO dao = new SeleniumDAOImpl();
			List<String> list = dao.getFolderScenarios(folderId);
			String response = JSONUtil.processFolderScenarios(list);
						
			 	inputStream = new ByteArrayInputStream(response.getBytes()); 
			return "success";
		}
		
		public String checkTestCaseTitle() throws Exception{
			SeleniumDAO dao = new SeleniumDAOImpl();
			boolean isDuplicate = dao.isTestCaseTitleDuplicate(getProjectId(), getTitle());
			String response = JSONUtil.processTestCaseTitle(isDuplicate);
						
			 	inputStream = new ByteArrayInputStream(response.getBytes()); 
			return "success";
		}
		
		public String checkFolderTitle() throws Exception{
			SeleniumDAO dao = new SeleniumDAOImpl();
			boolean isDuplicate = dao.isFolderTitleDuplicate(getProjectId(), getTitle());
			String response = JSONUtil.processFolderTitle(isDuplicate);
						
			 	inputStream = new ByteArrayInputStream(response.getBytes()); 
			return "success";
		}
		
		private int getProjectId(){
			ActionContext ctx = ActionContext.getContext();  
	        Map session = ctx.getSession();
	        User user= (User)session.get("USER");
	        return user.getProject().getProjectId();
		}

		public int getTestCaseId() {
			return testCaseId;
		}

		public void setTestCaseId(int testCaseId) {
			this.testCaseId = testCaseId;
		}

		public InputStream getInputStream() {
			return inputStream;
		}

		public void setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		public int getFolderId() {
			return folderId;
		}

		public void setFolderId(int folderId) {
			this.folderId = folderId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	
}
