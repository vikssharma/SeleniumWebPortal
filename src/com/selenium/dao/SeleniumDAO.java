package com.selenium.dao;

import java.util.List;
import java.util.Map;

import com.selenium.model.Action;
import com.selenium.model.Folder;
import com.selenium.model.Project;
import com.selenium.model.ScenarioFolder;
import com.selenium.model.TestCase;
import com.selenium.model.TestScenario;
import com.selenium.model.TestScenarioInput;
import com.selenium.model.User;

public interface SeleniumDAO {
	
	public User addUser(User user);
	
	public User getUser(User user);
	
	 public User findUser(String username, String password);
	 
	 public boolean findUserExists(String username,String email);
	 
	 public boolean activateUser(String email,String hash);
	 
	 public boolean findApplicationExists(String application);
	 
	 public Folder getRootFolder(int projectId);
	 
	 public Folder getFolderById(int folderId);
	 
	 public Folder addFolder(Folder folder);
	 
	 public void deleteFolder(Folder folder);
	 
	 public TestCase getTestCaseById(int testCaseId);
	 
	 public TestCase addTestCase(TestCase testCase);
	 
	 public void deleteTestCase(TestCase testCase);
	 
	 public TestCase updateTestCase(TestCase testCase);
	 
	 public List<Action> getActionsList();
	 
	 public ScenarioFolder addScenarioRootFolder(int projectId);
	 
	 public ScenarioFolder getScenarioRootFolder(int projectId);
	 
	 public ScenarioFolder getScenarioFolderById(int folderId);
	 
	 public ScenarioFolder addScenarioFolder(ScenarioFolder folder);
	 
	 public void deleteScenarioFolder(ScenarioFolder folder);

	 public TestScenario getTestScenarioById(int testScenarioId);
	 
	 public TestScenario addTestScenario(TestScenario testScenario);
	 
	 public void deleteTestScenario(TestScenario testScenario);
	 
	 public TestScenario updateTestScenario(TestScenario testScenario);
	 
	 public List<TestCase> getTestCaseList(int projectId);
	 
	 public Map getActionMap();
	 
	 public void addTestScenarioInputs(List<TestScenarioInput> inputList,Integer testScenarioId);
	 
	 public Map<Integer,String> getInputsForScenarioStep(int testScenarioStepId,int testScenarioStepNumber);
	 
	 public boolean isTestCaseValidForDelete(int testCaseId);
	 
	 public List<String> getTestCaseScenarios(int testCaseId);
	 
	 public List<String> getFolderScenarios(int folderId);
	 
	 public boolean isTestCaseTitleDuplicate(int projectId,String title);
	 
	 public boolean isFolderTitleDuplicate(int projectId,String title);
	 
	 public boolean isScenarioTitleDuplicate(int projectId,String title);
	 
	 public boolean isScenarioFolderTitleDuplicate(int projectId,String title);
	
}
