package com.selenium.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.selenium.model.Folder;
import com.selenium.model.TestCase;
import com.selenium.model.TestCaseStep;

public class JSONUtil {
	
	 public static JSONArray processRequest(Folder root) throws Exception
	             {
		 JSONArray jSONArray = new JSONArray();
	       
	        try {
	           
	           
	                JSONObject rootObject = new JSONObject();                
	                rootObject.put("state","open");
	                rootObject.put("data",root.getName());
	 
	                JSONObject jsonAttr = new JSONObject();                
	                jsonAttr.put("type", "root");
	                rootObject.put("attr", jsonAttr);
	                jsonAttr = null;
	                
	                JSONArray rootChildarray = new JSONArray();
	                
	                for(TestCase testcase:root.getTestCaseList()){
	                	 JSONObject testcaseObject = new JSONObject();                
	                	 testcaseObject.put("data",testcase.getTitle());
	 	 
	 	                jsonAttr = new JSONObject();                
	 	                jsonAttr.put("type", "leaf");
	 	               testcaseObject.put("attr", jsonAttr);
	 	                jsonAttr = null;
	 	               rootChildarray.put(testcaseObject);
	                }
	                
	                for(Folder folder:root.getChildNodes()){
	                	JSONObject folderObject = new JSONObject();                
	                	folderObject.put("data",folder.getName());
	                	folderObject.put("state","open");
	 	                jsonAttr = new JSONObject();                
	 	                jsonAttr.put("type", "folder");
	 	               folderObject.put("attr", jsonAttr);
	 	                jsonAttr = null;
	 	               rootChildarray.put(folderObject);
	                }
	                
	                
	 
	                rootObject.put("children", rootChildarray);
	                    jSONArray.put(rootObject);
	                   
	              
	        }catch(Exception e){
	            System.out.println(e);
	        }
	        finally {
	           System.out.println("executed jsonutil");
	        }
	        
	        return jSONArray;
	    }
	 
	 public static String processTestCase(TestCase testCase,int stepIndex){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("action", "getTestCase");            
	         rootObject.put("stepIndex",stepIndex);
	         
	         JSONArray stepsArray = new JSONArray();
				 for(TestCaseStep step:testCase.getStepList()){
				 JSONObject jo = new JSONObject();
				 jo.put("actionDisplay", step.getActionDisplay());
				 jo.put("objectProperty",step.getObjectProperty());
				 jo.put("objectValue",step.getObjectValue());
				 jo.put("toBeExecuted",step.isToBeExecuted());
				 jo.put("stepNumber",step.getStepNumber());
				 stepsArray.put(jo);
				 }
			rootObject.put("steps", stepsArray);
			jSONArray.put(rootObject);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return rootObject.toString();
	 }
	 
	 public static String processTestCaseScenarios(List<String> scenarios){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("type", "testCase");
			 rootObject.put("action", "delete");
			 if(scenarios == null || scenarios.size()==0){
				 rootObject.put("scenarios", "0");
			 }
			 else{
				 rootObject.put("scenarios", scenarios.toString());
			 }
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return rootObject.toString();
	 }
	 
	 public static String processFolderScenarios(List<String> scenarios){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("type", "folder");
			 rootObject.put("action", "delete");
			 if(scenarios == null || scenarios.size()==0){
				 rootObject.put("scenarios", "0");
			 }
			 else{
				 rootObject.put("scenarios", scenarios.toString());
			 }
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return rootObject.toString();
	 }
	 
	 public static String processTestCaseTitle(boolean isDuplicate){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("type", "testCase");
			 rootObject.put("action", "add");
			rootObject.put("isDuplicate", isDuplicate);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return rootObject.toString();
	 }
	 
	 public static String processFolderTitle(boolean isDuplicate){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("type", "folder");
			 rootObject.put("action", "add");
			rootObject.put("isDuplicate", isDuplicate);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return rootObject.toString();
	 }
	 
	 public static String processScenarioTitle(boolean isDuplicate){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("type", "testScenario");
			 rootObject.put("action", "add");
			rootObject.put("isDuplicate", isDuplicate);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return rootObject.toString();
	 }
	 
	 public static String processScenarioFolderTitle(boolean isDuplicate){
		 JSONArray jSONArray = new JSONArray();
		 JSONObject rootObject = new JSONObject(); 
		 
		 try{
			 rootObject.put("type", "folder");
			 rootObject.put("action", "add");
			rootObject.put("isDuplicate", isDuplicate);
			 
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return rootObject.toString();
	 }

}
