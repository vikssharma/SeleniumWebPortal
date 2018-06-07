
package com.selenium.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.oreilly.servlet.MultipartRequest;
import com.selenium.dao.SeleniumDAO;
import com.selenium.dao.SeleniumDAOImpl;
import com.selenium.model.ScenarioFolder;
import com.selenium.model.TestCase;
import com.selenium.model.TestCaseStep;
import com.selenium.model.TestScenario;
import com.selenium.model.TestScenarioInput;
import com.selenium.model.TestScenarioStep;
import com.selenium.model.User;
import com.selenium.util.ExecuteTestScenarioUtil;
import com.selenium.util.ScenarioTreeUtil;


public class TestScenarioAction extends ActionSupport implements  ModelDriven<List<TestScenarioStep>>{
	
	private String treeString;
	
	private String folderName;
	
	private Integer folderId;
	
	private Integer parentId;
	
	private String testscenarioTitle;
	
	private Integer testScenarioId;
	
	private String selectedNode;
	
	private int selectedTab;
	
	private TestScenario testScenario;
	
	private List<TestCase> testCaseList;
	
	
	 SeleniumDAO dao = new SeleniumDAOImpl();
	 
	public String execute() throws Exception{
		 	System.out.println("TestscenarioAction execute");
		ScenarioFolder root =  dao.getScenarioRootFolder(getProjectId());
		/**If scenario root folder does not exist, create a new root folder*/
		if(root == null){
			root = dao.addScenarioRootFolder(getProjectId());
		}
		setSelectedNode("f"+root.getFolderId());
		setTreeString(ScenarioTreeUtil.buildTreeString( root));
	       
		return "success";
	}
	
	public String selectTestScenario() throws Exception{
		
		if(getTestScenarioId() !=null){
			TestScenario testScenario = dao.getTestScenarioById(getTestScenarioId());
			setSelectedNode("t"+testScenario.getTestScenarioId());
			setTestScenario(testScenario);
		}
		buildTree();
		buildTestScenarioSteps();
		setTestCaseList(dao.getTestCaseList(getProjectId()));
		return "success";
	}
	
	
	public String saveTestScenario() throws Exception{
		
		save();
		buildTree();
		buildTestScenarioSteps();
		setTestCaseList(dao.getTestCaseList(getProjectId()));
		addActionMessage("Test Scenario saved successfully.");
		setSelectedTab(getSelectedTab());
		return "success";
	}
	
	
	public String addFolder() throws Exception{
		
                
		ScenarioFolder folder = new ScenarioFolder(getFolderName(),getParentId(),getProjectId());
				 
		 folder = dao.addScenarioFolder(folder);
		 
		 buildTree();
		 setSelectedNode("f"+folder.getFolderId());
		return "success";
	}
	

	
	public String deleteFolder() throws Exception{
		if(getFolderId() !=null){
		ScenarioFolder folder = dao.getScenarioFolderById(getFolderId());
		setSelectedNode("f"+folder.getParentId());
		dao.deleteScenarioFolder(folder);
		}
		buildTree();
		
		return "success";
	}
	
	public String addTestScenario() throws Exception{
		ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();
        User user= (User)session.get("USER");
		
		TestScenario testScenario = new TestScenario();
		testScenario.setTitle(getTestscenarioTitle());
		testScenario.setCreatedBy(user.getFirstName()+" "+user.getLastName());
		testScenario.setCreatedDate(new Date());
		testScenario.setModifiedBy(user.getFirstName()+" "+user.getLastName());
		testScenario.setModifiedDate(new Date());
		testScenario.setFolderId(getFolderId());
		testScenario.setProjectId(getProjectId());
		testScenario.setOnError(true);
		testScenario.setBrowser("Chrome");
		
		testScenario = dao.addTestScenario(testScenario);
		
		setSelectedNode("t"+testScenario.getTestScenarioId());
		
		setTestScenario(testScenario);
		
		buildTree();
		buildTestScenarioSteps();
		setTestCaseList(dao.getTestCaseList(getProjectId()));
		
		return "success";
	}
	
	public String deleteTestScenario() throws Exception{
		if(getTestScenarioId() !=null){
			TestScenario testScenario = dao.getTestScenarioById(getTestScenarioId());
			setSelectedNode("f"+testScenario.getFolderId());
			dao.deleteTestScenario(testScenario);
		}
		buildTree();
		
		return "success";
	}
	
	public String runTestScenario() throws Exception{
		//String[] a=null;
		//saveTestScenario();
		save();
		

		
		ExecuteTestScenarioUtil executeUtil = new ExecuteTestScenarioUtil();
		executeUtil.executeTestScenario(getTestScenario());
		setTestScenario(dao.getTestScenarioById(getTestScenario().getTestScenarioId()));

		buildTree();
		buildTestScenarioSteps();
		setTestCaseList(dao.getTestCaseList(getProjectId()));
		setSelectedTab(getSelectedTab());
		
		addActionMessage("Test Scenario saved and executed successfully.");
		return "success";
	}
	
	
	private int getProjectId(){
		ActionContext ctx = ActionContext.getContext();  
        Map session = ctx.getSession();
        User user= (User)session.get("USER");
        return user.getProject().getProjectId();
	}
	
	private void buildTree(){
		 setTreeString(ScenarioTreeUtil.buildTreeString( dao.getScenarioRootFolder(getProjectId())));
	}
	
	private void buildTestScenarioSteps(){
		System.out.println("inside build steps");
		if(this.getTestScenario()!=null){
		if(this.testScenario.getStepList()==null || this.testScenario.getStepList().size()==0){
			List<TestScenarioStep> testScenarioStepList = new ArrayList<TestScenarioStep>();
			for(int i=1;i<=4;i++){
				TestScenarioStep testScenarioStep = new TestScenarioStep();
				testScenarioStep.setStepNumber(i);
				testScenarioStep.setToBeExecuted(true);
				testScenarioStepList.add(testScenarioStep);
			}
			this.testScenario.setStepList(testScenarioStepList);
		}
		else{
			System.out.println("inside build steps - else condition 1");
			Map<Integer,String> actionMap = dao.getActionMap();
			Collections.sort(this.testScenario.getStepList());
			for(TestScenarioStep step:this.testScenario.getStepList()){
				Map<Integer,String> inputMap = dao.getInputsForScenarioStep(step.getTestScenarioId(),step.getStepNumber());
				TestCase testCase = step.getTestCase();
				
				if(testCase !=null ){
					TestCase clonedTestCase = (TestCase)testCase.clone();
					List<TestCaseStep> testCaseStepList = clonedTestCase.getStepList();
					
					for(TestCaseStep testCaseStep:testCaseStepList){
						int actionId = testCaseStep.getActionId();
						String displayValue = actionMap.get(actionId);
						testCaseStep.setActionDisplay(displayValue);
						String inputValue = inputMap.get(testCaseStep.getStepNumber());
						testCaseStep.setObjectValue(inputValue);
					}
					step.setTestCase(clonedTestCase);
					
				}
			}
		}
		}
	}
	
	private void save() throws Exception{
		if(getTestScenario() !=null){
			
			System.out.println("testscenarioId from testscenario: "+getTestScenario().getTestScenarioId()+" browser: "+getTestScenario().getBrowser());
			Integer id = getTestScenario().getTestScenarioId();
			String description = getTestScenario().getDescription();
			String browser = getTestScenario().getBrowser();
			boolean onerror = getTestScenario().getOnError();
			
			List<TestScenarioInput> inputList = new ArrayList<TestScenarioInput>();
			
			List<TestScenarioStep> newStepList = new ArrayList<TestScenarioStep>();
			if(getTestScenario().getStepList()!=null){
				int stepNumber=0;
				for(TestScenarioStep step:getTestScenario().getStepList()){
					if(step != null && step.getTestCase()!=null){
						if(step.getTestCase().getTestCaseId()!= -1){
							stepNumber++;
							//step.setId(null);
							step.setStepNumber(stepNumber);
							step.setTestScenarioId(getTestScenario().getTestScenarioId());
							System.out.println("save scenario...... testCase Step list : "+step.getTestCase().getStepList().size());
							for(TestCaseStep testCaseStep:step.getTestCase().getStepList()){
								System.out.println("testcase step number: "+testCaseStep.getStepNumber());
								System.out.println("testcase step input : "+testCaseStep.getObjectValue());
								TestScenarioInput input = new TestScenarioInput();
								input.setInputValue(testCaseStep.getObjectValue());
								input.setTestCaseId(step.getTestCase().getTestCaseId());
								input.setTestCaseStepNumber(testCaseStep.getStepNumber());
								input.setTestScenarioId(id);
								input.setTestScenarioStepNumber(step.getStepNumber());
								inputList.add(input);
							}
							TestCase testCase = dao.getTestCaseById(step.getTestCase().getTestCaseId());
							step.setTestCase(testCase);
							newStepList.add(step);
						}
					}
				}
				}
			for(TestScenarioInput input:inputList){
				System.out.println("Input getInputValue..."+input.getInputValue());
				System.out.println("Input getTestCaseId..."+input.getTestCaseId());
				System.out.println("Input getTestCaseStepNumber..."+input.getTestCaseStepNumber());
				System.out.println("Input getTestScenarioId..."+input.getTestScenarioId());
				System.out.println("Input getTestScenarioStepNumber..."+input.getTestScenarioStepNumber());
				
			}
			
			
			TestScenario testScenario1 = dao.getTestScenarioById(id);
			testScenario1.setModifiedDate(new Date());
			testScenario1.setDescription(description);
			testScenario1.setStepList(newStepList);
			testScenario1.setBrowser(browser);
			testScenario1.setOnError(onerror);
			testScenario1 = dao.updateTestScenario(testScenario1);
			dao.addTestScenarioInputs(inputList, id);
			
			setTestScenario(testScenario1);
			setSelectedNode("t"+testScenario1.getTestScenarioId());
			
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


	public String getTestscenarioTitle() {
		return testscenarioTitle;
	}


	public void setTestscenarioTitle(String testscenarioTitle) {
		this.testscenarioTitle = testscenarioTitle;
	}
	
	

	public Integer getFolderId() {
		return folderId;
	}


	public void setFolderId(Integer folderId) {
		this.folderId = folderId;
	}
	

	public Integer getTestScenarioId() {
		return testScenarioId;
	}


	public void setTestScenarioId(Integer testScenarioId) {
		this.testScenarioId = testScenarioId;
	}


	public String getSelectedNode() {
		return selectedNode;
	}


	public void setSelectedNode(String selectedNode) {
		this.selectedNode = selectedNode;
	}


	public TestScenario getTestScenario() {
		return testScenario;
	}


	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
		
	}

	public List<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(List<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}





	@Override
	public List<TestScenarioStep> getModel() {
		if(getTestScenario()!=null){
			return getTestScenario().getStepList();
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
