package com.selenium.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.selenium.model.Action;
import com.selenium.model.Folder;
import com.selenium.model.Project;
import com.selenium.model.ScenarioFolder;
import com.selenium.model.TestCase;
import com.selenium.model.TestScenario;
import com.selenium.model.TestScenarioInput;
import com.selenium.model.TestScenarioStep;
import com.selenium.model.User;
import com.selenium.util.HibernateUtil;

import org.hibernate.Query;
import org.hibernate.Session;

public class SeleniumDAOImpl implements SeleniumDAO{

	@Override
	public User addUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(user);
		Folder folder = new Folder();
		folder.setName("root");
		folder.setProjectId(user.getProject().getProjectId());
		session.save(folder);
		
		session.getTransaction().commit();
		session.close();
		return user;
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	 public User findUser(String userName, String password) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        //session.beginTransaction();
	        String sql = " from User u where (u.userName=:userName or u.email=:email) and u.password=:password";
	        Query query = session.createQuery(sql);
	        query.setParameter("userName", userName);
	        query.setParameter("email", userName);
	        query.setParameter("password", password);
	        List<User> list = query.list();
	        if (list.size() > 0) {
	        	User user=list.get(0);
	            session.close();
	            return user;
	        }
	        session.close();
	        return null;
	    }

	@Override
	public boolean findUserExists(String userName, String email) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        //session.beginTransaction();
	        String sql = " from User u where u.userName=:userName or u.email=:email";
	        Query query = session.createQuery(sql);
	        query.setParameter("userName", userName);
	        query.setParameter("email", email);
	        List<User> list = query.list();
	        if (list.size() > 0) {
	            session.close();
	            return true;
	        }
	        session.close();
	       
		return false;
	}



	@Override
	public boolean activateUser(String email, String hash) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	       
	        String sql = " from User u where u.email=:email and u.hash=:hash and u.active=0";
	        Query query = session.createQuery(sql);
	        query.setParameter("email", email);
	        query.setParameter("hash", hash);
	        List<User> list = query.list();
	        if (list.size() > 0) {
	        	System.out.println("user inactive exists");
	        	 session.beginTransaction();
	        	User user = list.get(0);
	        	user.setActive(1);
	        	user.setLicense(0);
	        	user.setStartDate(new Date());
	        	session.save(user);
	        	session.getTransaction().commit();
	            session.close();
	            return true;
	        }
	        session.close();
	       
		return false;
	}

	@Override
	public boolean findApplicationExists(String application) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        //session.beginTransaction();
	        String sql = " from Project p where p.application=:application";
	        Query query = session.createQuery(sql);
	        query.setParameter("application", application);
	       
	        List<User> list = query.list();
	        if (list.size() > 0) {
	            session.close();
	            return true;
	        }
	        session.close();
	       
		return false;
	}
	
	
	public Folder getRootFolder(int projectId){
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        //session.beginTransaction();
	        String sql = " from Folder f where f.projectId=:projectId and (f.parentId is null or f.parentId=0)";
	        Query query = session.createQuery(sql);
	        query.setParameter("projectId", projectId);
	       
	        List<Folder> list = query.list();
	        if (list.size() > 0) {
	        	Folder folder=list.get(0);
	        	
	            session.close();
	            return folder;
	        }
	        
	        session.close();
	        return null;
	}

	@Override
	public Folder addFolder(Folder folder) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(folder);
		
		session.getTransaction().commit();
		session.close();
		return folder;
	}

	@Override
	public void deleteFolder(Folder folder){
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
	    session.delete(folder);
	    
	     session.getTransaction().commit();
	     session.close();
	}
	
	public Folder getFolderById(int folderId){
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        //session.beginTransaction();
	        String sql = " from Folder f where f.folderId=:folderId ";
	        Query query = session.createQuery(sql);
	        query.setParameter("folderId", folderId);
	       
	        List<Folder> list = query.list();
	        if (list.size() > 0) {
	        	Folder folder=list.get(0);
	            session.close();
	            return folder;
	        }
	        session.close();
	        return null;
	}

	@Override
	public TestCase addTestCase(TestCase testCase) {
		
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(testCase);
			
			session.getTransaction().commit();
			session.close();
			return testCase;
		
	}
	
//	@Override
//	public void deleteTestCase(int testCaseId){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		System.out.println("testCaseId :"+testCaseId);
//		String sql = " from TestCase t where t.testcaseId=:testCaseId";
//		 Query query = session.createQuery(sql);
//	     query.setParameter("testCaseId", testCaseId);
//	     List<TestCase> list = query.list();
//	     if (list.size() > 0) {
//	    	 TestCase testCase=list.get(0);
//	        	session.delete(testCase);
//	     }
//	     session.getTransaction().commit();
//	     session.close();
//	}

	@Override
	public TestCase getTestCaseById(int testCaseId) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
	        //session.beginTransaction();
		 String sql = " from TestCase t where t.testCaseId=:testCaseId";
	        Query query = session.createQuery(sql);
	        query.setParameter("testCaseId", testCaseId);
	       
	        List<TestCase> list = query.list();
		     if (list.size() > 0) {
		    	 TestCase testCase=list.get(0);
	            session.close();
	            return testCase;
	        }
	        session.close();
	        return null;
	}

	@Override
	public void deleteTestCase(TestCase testCase) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(testCase);
		 session.getTransaction().commit();
	     session.close();
	}
	
	@Override
	public TestCase updateTestCase(TestCase testCase) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		testCase = (TestCase)session.merge(testCase);
		
		 session.getTransaction().commit();
	     session.close();
	     return testCase;
	}

	@Override
	public List<Action> getActionsList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = " from Action a ";
        Query query = session.createQuery(sql);
        List<Action> list = query.list();
       
        session.close();
       
        return list;
	}

	@Override
	public TestScenario getTestScenarioById(int testScenarioId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
		String sql = " from TestScenario t where t.testScenarioId=:testScenarioId";
        Query query = session.createQuery(sql);
        query.setParameter("testScenarioId", testScenarioId);
       
        List<TestScenario> list = query.list();
	     if (list.size() > 0) {
	    	 TestScenario testScenario=list.get(0);
	    	 
            session.close();
            return testScenario;
        }
        session.close();
        return null;
	}

	@Override
	public TestScenario addTestScenario(TestScenario testScenario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(testScenario);
		
		session.getTransaction().commit();
		session.close();
		return testScenario;
	}

	@Override
	public void deleteTestScenario(TestScenario testScenario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(testScenario);
		 session.getTransaction().commit();
	     session.close();
		
	}

	@Override
	public TestScenario updateTestScenario(TestScenario testScenario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		testScenario = (TestScenario)session.merge(testScenario);
		
		 session.getTransaction().commit();
	     session.close();
	     return testScenario;
	}

	@Override
	public List<TestCase> getTestCaseList(int projectId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = " from Folder f where projectId=:projectId";
        Query query = session.createQuery(sql);
        query.setParameter("projectId", projectId);
        List<Folder> folderList = query.list();
        List<TestCase> testCaseList = new ArrayList<TestCase>();
	     if (folderList!=null && folderList.size() > 0) {
	    	 for(Folder folder:folderList){
	    		 if(folder.getTestCaseList()!=null){
	    			 testCaseList.addAll(folder.getTestCaseList());
	    		 }
	    	 }
	     }
	     Collections.sort(testCaseList);
        session.close();
       
        return testCaseList;
	}

	@Override
	public ScenarioFolder getScenarioRootFolder(int projectId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = " from ScenarioFolder f where f.projectId=:projectId and (f.parentId is null or f.parentId=0)";
        Query query = session.createQuery(sql);
        query.setParameter("projectId", projectId);
       
        List<ScenarioFolder> list = query.list();
        if (list.size() > 0) {
        	ScenarioFolder folder=list.get(0);
        	
            session.close();
            return folder;
        }
        
        session.close();
        return null;
	}

	@Override
	public ScenarioFolder getScenarioFolderById(int folderId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = " from ScenarioFolder f where f.folderId=:folderId ";
        Query query = session.createQuery(sql);
        query.setParameter("folderId", folderId);
       
        List<ScenarioFolder> list = query.list();
        if (list.size() > 0) {
        	ScenarioFolder folder=list.get(0);
            session.close();
            return folder;
        }
        session.close();
        return null;
	}

	@Override
	public ScenarioFolder addScenarioFolder(ScenarioFolder folder) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(folder);
		
		session.getTransaction().commit();
		session.close();
		return folder;
	}

	@Override
	public void deleteScenarioFolder(ScenarioFolder folder) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
	    session.delete(folder);
	    
	     session.getTransaction().commit();
	     session.close();
		
	}

	@Override
	public ScenarioFolder addScenarioRootFolder(int projectId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		ScenarioFolder scenarioFolder = new ScenarioFolder();
		scenarioFolder.setName("root");
		scenarioFolder.setProjectId(projectId);
		session.save(scenarioFolder);
		session.getTransaction().commit();
		session.close();
		return scenarioFolder;
	}

	@Override
	public Map getActionMap() {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = " from Action a ";
        Query query = session.createQuery(sql);
        List<Action> list = query.list();
       Map<Integer,String> actionMap = new HashMap<Integer,String>();
       for(Action action:list){
    	   actionMap.put(action.getActionId(), action.getDisplay());
       }
        session.close();
       
        return actionMap;
	}

	@Override
	public void addTestScenarioInputs(List<TestScenarioInput> inputList, Integer testScenarioId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String sql = "DELETE from TestScenarioInput input where input.testScenarioId = :testScenarioId ";
				
        Query query = session.createQuery(sql);
        query.setParameter("testScenarioId", testScenarioId);
		int result = query.executeUpdate();
		for(TestScenarioInput input : inputList){
		session.save(input);
		}
		session.getTransaction().commit();
		session.close();
		
	}

	@Override
	public Map<Integer, String> getInputsForScenarioStep(int testScenarioId,int testScenarioStepNumber) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
        String sql = " from TestScenarioInput a where a.testScenarioId = :testScenarioId and a.testScenarioStepNumber = :testScenarioStepNumber";
        Query query = session.createQuery(sql);
        query.setParameter("testScenarioId", testScenarioId);
        query.setParameter("testScenarioStepNumber", testScenarioStepNumber);
        List<TestScenarioInput> list = query.list();
       Map<Integer,String> inputMap = new HashMap<Integer,String>();
       for(TestScenarioInput input:list){
    	   inputMap.put(input.getTestCaseStepNumber(), input.getInputValue());
       }
        session.close();
       
        return inputMap;
	}

	@Override
	public boolean isTestCaseValidForDelete(int testCaseId) {
		boolean isValid = false;
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = " from TestScenarioStep step where step.testCase.testCaseId = :testCaseId ";
        Query query = session.createQuery(sql);
        query.setParameter("testCaseId", testCaseId);
        List<TestScenarioStep> list = query.list();
       if(list == null || list.size()==0){
    	   isValid = true;
       }
        session.close();
		return isValid;
	}
	
	
	public List<String> getTestCaseScenarios(int testCaseId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = "SELECT scenario.title from TestScenario scenario where scenario.testScenarioId IN "
        		+ "(SELECT step.testScenarioId from TestScenarioStep step where step.testCase.testCaseId = :testCaseId )";
        Query query = session.createQuery(sql);
        query.setParameter("testCaseId", testCaseId);
        List<String> list = query.list();
       
        session.close();
		return list;
	}

	@Override
	public List<String> getFolderScenarios(int folderId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        //session.beginTransaction();
        String sql = "SELECT scenario.title from TestScenario scenario where scenario.testScenarioId IN "
        		+ "(SELECT step.testScenarioId from TestScenarioStep step where step.testCase.testCaseId IN "
        		+ "(SELECT testCase.testCaseId from TestCase testCase where testCase.folderId = :folderId) )";
        Query query = session.createQuery(sql);
        query.setParameter("folderId", folderId);
        List<String> list = query.list();
       
        session.close();
		return list;
	}

	@Override
	public boolean isTestCaseTitleDuplicate(int projectId, String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
        String sql = " from TestCase testCase where testCase.title = :title AND testCase.folderId IN "
        		+ "(SELECT folder.folderId from Folder folder where folder.projectId = :projectId)  ";
        		
        Query query = session.createQuery(sql);
        query.setParameter("title", title);
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        session.close();
        if(list == null || list.isEmpty() || list.size()==0){
		return false;
        }
        else{
        	return true;
        }
	}

	@Override
	public boolean isFolderTitleDuplicate(int projectId, String title) {
Session session = HibernateUtil.getSessionFactory().openSession();
        
        String sql = " from Folder folder where folder.projectId = :projectId AND folder.name = :title  ";
        		
        Query query = session.createQuery(sql);
        query.setParameter("title", title);
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        session.close();
        if(list == null || list.isEmpty() || list.size()==0){
		return false;
        }
        else{
        	return true;
        }
	}
	 
	@Override
	public boolean isScenarioTitleDuplicate(int projectId, String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
        String sql = " from TestScenario scenario where scenario.title = :title AND scenario.projectId = :projectId";
        		
        Query query = session.createQuery(sql);
        query.setParameter("title", title);
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        session.close();
        if(list == null || list.isEmpty() || list.size()==0){
		return false;
        }
        else{
        	return true;
        }
	}

	@Override
	public boolean isScenarioFolderTitleDuplicate(int projectId, String title) {
		Session session = HibernateUtil.getSessionFactory().openSession();
        
        String sql = " from ScenarioFolder folder where folder.projectId = :projectId AND folder.name = :title  ";
        		
        Query query = session.createQuery(sql);
        query.setParameter("title", title);
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        session.close();
        if(list == null || list.isEmpty() || list.size()==0){
		return false;
        }
        else{
        	return true;
        }
	}
	 

}
