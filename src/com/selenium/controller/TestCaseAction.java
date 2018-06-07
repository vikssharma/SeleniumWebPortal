package com.selenium.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.Action;
import com.selenium.model.Folder;
import com.selenium.model.TestCase;
import com.selenium.model.TestCaseStep;
import com.selenium.model.User;
import com.selenium.util.ExecuteTestCaseUtil;
import com.selenium.util.TreeUtil;

public class TestCaseAction extends ActionSupport implements  ModelDriven<List<TestCaseStep>>{
	
	private String treeString;
	
	private String folderName;
	
	private Integer folderId;
	
	private Integer parentId;
	
	private String testcaseTitle;
	
	private Integer testCaseId;
	
	private String selectedNode;
	
	private int selectedTab;
	
	private TestCase testCase;
	
	private List<Action> actionsList;
	
	//private String resultpath;
	
	//private String lastrunstatus;
	
	
	 SeleniumDAO dao = new SeleniumDAOImpl();
	 
	public String execute() throws Exception{
		 	System.out.println("TestcaseAction execute");
		Folder root =  dao.getRootFolder(getProjectId());
		setSelectedNode("f"+root.getFolderId());
		setTreeString(TreeUtil.buildTreeString( root));
	       
		return "success";
	}
	
	public String selectTestCase() throws Exception{
		
		if(getTestCaseId() !=null){
			TestCase testCase = dao.getTestCaseById(getTestCaseId());
			setSelectedNode("t"+testCase.getTestCaseId());
			setTestCase(testCase);
		}
		buildTree();
		buildTestCaseSteps();
		setActionsList(dao.getActionsList());
		return "success";
	}
	
	
	public String saveTestCase() throws Exception{
		
		save();
		buildTree();
		buildTestCaseSteps();
		setActionsList(dao.getActionsList());
		addActionMessage("Test Case saved successfully.");
		setSelectedTab(getSelectedTab());
		return "success";
	}
	
	
	public String addFolder() throws Exception{
		
                
		Folder folder = new Folder(getFolderName(),getParentId(),getProjectId());
				 
		 folder = dao.addFolder(folder);
		 
		 buildTree();
		 setSelectedNode("f"+folder.getFolderId());
		return "success";
	}
	

	
	public String deleteFolder() throws Exception{
		if(getFolderId() !=null){
		Folder folder = dao.getFolderById(getFolderId());
		setSelectedNode("f"+folder.getParentId());
		dao.deleteFolder(folder);
		}
		buildTree();
		
		return "success";
	}
	
	public String addTestCase() throws Exception{
		ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();
        User user= (User)session.get("USER");
		
		TestCase testCase = new TestCase();
		testCase.setTitle(getTestcaseTitle());
		testCase.setCreatedBy(user.getFirstName()+" "+user.getLastName());
		testCase.setCreatedDate(new Date());
		testCase.setModifiedBy(user.getFirstName()+" "+user.getLastName());
		testCase.setModifiedDate(new Date());
		testCase.setFolderId(getFolderId());
		testCase.setOnError(true);
		testCase.setBrowser("Chrome");
		
		testCase = dao.addTestCase(testCase);
		
		setSelectedNode("t"+testCase.getTestCaseId());
		
		setTestCase(testCase);
		
		buildTree();
		buildTestCaseSteps();
		setActionsList(dao.getActionsList());
		
		return "success";
	}
	
	public String deleteTestCase() throws Exception{
		if(getTestCaseId() !=null){
			TestCase testCase = dao.getTestCaseById(getTestCaseId());
			setSelectedNode("f"+testCase.getFolderId());
			dao.deleteTestCase(testCase);
		}
		buildTree();
		
		return "success";
	}
	
	public String runTestCase() throws Exception{
		//String[] a=null;
		save();
		ExecuteTestCaseUtil executeUtil = new ExecuteTestCaseUtil();
		executeUtil.executeTestCase(getTestCase());
		
		save(); 
	     
		buildTree();
		buildTestCaseSteps();
		setActionsList(dao.getActionsList());
		setSelectedTab(getSelectedTab());
		addActionMessage("Test Case saved and executed successfully.");
		return "success";
	}
	


	private int getProjectId(){
		ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();
        User user= (User)session.get("USER");
        return user.getProject().getProjectId();
	}
	
	private void buildTree(){
		 setTreeString(TreeUtil.buildTreeString( dao.getRootFolder(getProjectId())));
	}
	
	private void buildTestCaseSteps(){
		System.out.println("inside build steps");
		if(this.getTestCase()!=null){
		if(this.testCase.getStepList()==null || this.testCase.getStepList().size()==0){
			List<TestCaseStep> testCaseStepList = new ArrayList<TestCaseStep>();
			for(int i=1;i<=10;i++){
				TestCaseStep testCaseStep = new TestCaseStep();
				testCaseStep.setStepNumber(i);
				testCaseStep.setToBeExecuted(true);
				testCaseStepList.add(testCaseStep);
			}
			this.testCase.setStepList(testCaseStepList);
		}
		}
	}
	
	private void save() throws Exception{
		if(getTestCase() !=null){
			
			System.out.println("testcaseId from testcase: "+getTestCase().getTestCaseId()+" browser: "+getTestCase().getBrowser());
			Integer id = getTestCase().getTestCaseId();
			String description = getTestCase().getDescription();
			String browser = getTestCase().getBrowser();
			boolean onerror = getTestCase().getOnError();
			
			List<TestCaseStep> newStepList = new ArrayList<TestCaseStep>();
			if(getTestCase().getStepList()!=null){
				int stepNumber=0;
				for(TestCaseStep step:getTestCase().getStepList()){
					if(step != null){
						if(step.getActionId()!= -1){
							stepNumber++;
							//step.setId(null);
							step.setStepNumber(stepNumber);
							step.setTestCaseId(getTestCase().getTestCaseId());
							
							newStepList.add(step);
						}
					}
				}
				}
			
			TestCase testCase1 = dao.getTestCaseById(id);
			testCase1.setModifiedDate(new Date());
			testCase1.setDescription(description);
			testCase1.setStepList(newStepList);
			testCase1.setBrowser(browser);
			testCase1.setOnError(onerror);
			testCase1 = dao.updateTestCase(testCase1);
			setTestCase(testCase1);
			setSelectedNode("t"+testCase1.getTestCaseId());
			
		}
	}

	public String getTreeString() {
		return treeString;
	}

	public void setTreeString(String treeString) {
		this.treeString = treeString;
	}


	public String getFolderName() {
		return folderName;
	}


	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}


	public Integer getParentId() {
		return parentId;
	}


	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}


	public String getTestcaseTitle() {
		return testcaseTitle;
	}


	public void setTestcaseTitle(String testcaseTitle) {
		this.testcaseTitle = testcaseTitle;
	}
	
	

	public Integer getFolderId() {
		return folderId;
	}


	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	

	public Integer getTestCaseId() {
		return testCaseId;
	}


	public void setTestCaseId(Integer testCaseId) {
		this.testCaseId = testCaseId;
	}


	public String getSelectedNode() {
		return selectedNode;
	}


	public void setSelectedNode(String selectedNode) {
		this.selectedNode = selectedNode;
	}


	public TestCase getTestCase() {
		return testCase;
	}


	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
		
	}

	public List<Action> getActionsList() {
		return actionsList;
	}

	public void setActionsList(List<Action> actionsList) {
		this.actionsList = actionsList;
	}





	@Override
	public List<TestCaseStep> getModel() {
		if(getTestCase()!=null){
			return getTestCase().getStepList();
		}
		return null;
	}

	public int getSelectedTab() {
		return selectedTab;
	}

	public void setSelectedTab(int selectedTab) {
		this.selectedTab = selectedTab;
	}


	public List<String> getBrowsersList() {
	    return Arrays.asList("Chrome", "IE", "Firefox");
	}
	
	public void validate(){
	     
		ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();
        
       if( session.get("USER")== null ){
    	   addActionError("Please log into the system.");
       }
	             
	   
	}
	
	

}
