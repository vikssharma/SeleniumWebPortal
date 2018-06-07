package com.selenium.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.selenium.model.ScenarioFolder;
import com.selenium.model.TestCase;
import com.selenium.model.TestScenario;

public class ScenarioTreeUtil {
	
	
	
	public static String buildTreeString(ScenarioFolder root){
		StringBuffer sb = new StringBuffer();
		sb.append("<ul><li id=\"f"+root.getFolderId()+"\" class=\"jstree-open\">"+root.getName());
		sb.append(getChildNodes(root));
		sb.append("</li>");
		sb.append("</ul>");
		System.out.println(sb);
		return sb.toString();
	}
	
	private static String getChildNodes(ScenarioFolder scenariofolder){
		StringBuffer sb = new StringBuffer();
		sb.append("<ul>");
		List<TestScenario> scenarioList = new ArrayList<TestScenario>();
		scenarioList.addAll(scenariofolder.getTestScenarioList());
		Collections.sort(scenarioList);
		for(TestScenario testscenario:scenarioList){
			sb.append("<li id=\"t"+testscenario.getTestScenarioId()+"\" data-jstree='{\"icon\":\"./images/document.png\",\"description\":\"file\"}'>");
			sb.append("<a  href=\"#\" onClick=\"javascript: selectTestScenario('"+testscenario.getTestScenarioId()+"')\">"+testscenario.getTitle()+"</a>");
			sb.append("</li>");
		}
		
		List<ScenarioFolder> scenarioFolderList = new ArrayList<ScenarioFolder>();
		scenarioFolderList.addAll(scenariofolder.getChildNodes());
		Collections.sort(scenarioFolderList);
		for(ScenarioFolder childFolder:scenarioFolderList){
			sb.append("<li id=\"f"+childFolder.getFolderId()+"\" data-jstree='{\"description\":\"folder\"}'>");
			sb.append(childFolder.getName());
			sb.append(getChildNodes(childFolder));
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

}
