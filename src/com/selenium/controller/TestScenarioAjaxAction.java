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


public class TestScenarioAjaxAction {
	
	private int testCaseId;
	
	private int stepIndex;
	
	 SeleniumDAO dao = new SeleniumDAOImpl();
	 
	 private InputStream inputStream;
	 
	 private String jsonString;
	 
	 private String title;

	 
		public String execute() throws Exception{
			 	System.out.println("TestscenarioAjaxAction execute");
			 	System.out.println("TestscenarioAjaxAction execute testcaseId :"+testCaseId);
			 	
			 	if(testCaseId !=0 && testCaseId != -1){
			 		TestCase testCase = dao.getTestCaseById(testCaseId);
			 		if(testCase !=null){
						System.out.println("inside build steps - else condition 4");
						List<TestCaseStep> testCaseStepList = testCase.getStepList();
						Map<Integer,String> actionMap = dao.getActionMap();
						for(TestCaseStep testCaseStep:testCaseStepList){
							int actionId = testCaseStep.getActionId();
							String displayValue = actionMap.get(actionId);
							testCaseStep.setActionDisplay(displayValue);
						}
						jsonString = JSONUtil.processTestCase(testCase,stepIndex);
					}
			 	}
			 	else{
			 		jsonString="{\"stepIndex\":"+stepIndex+"}";
			 	}
			 	 System.out.println(jsonString);
			 	inputStream = new ByteArrayInputStream(getJsonString().getBytes()); 
		       
			return "success";
		}
		
		public String checkScenarioTitle() throws Exception{
			SeleniumDAO dao = new SeleniumDAOImpl();
			boolean isDuplicate = dao.isScenarioTitleDuplicate(getProjectId(), getTitle());
			String response = JSONUtil.processScenarioTitle(isDuplicate);
						
			 	inputStream = new ByteArrayInputStream(response.getBytes()); 
			return "success";
		}
		
		public String checkScenarioFolderTitle() throws Exception{
			SeleniumDAO dao = new SeleniumDAOImpl();
			boolean isDuplicate = dao.isScenarioFolderTitleDuplicate(getProjectId(), getTitle());
			String response = JSONUtil.processScenarioFolderTitle(isDuplicate);
						
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

		public String getJsonString() {
			return jsonString;
		}

		public void setJsonString(String jsonString) {
			this.jsonString = jsonString;
		}

		public int getStepIndex() {
			return stepIndex;
		}

		public void setStepIndex(int stepIndex) {
			this.stepIndex = stepIndex;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
	

}
