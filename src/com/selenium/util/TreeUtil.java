package com.selenium.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.selenium.model.Folder;
import com.selenium.model.TestCase;
import com.selenium.model.TestScenario;

public class TreeUtil {
	
	
	
	public static String buildTreeString(Folder root){
		StringBuffer sb = new StringBuffer();
		sb.append("<ul><li id=\"f"+root.getFolderId()+"\" class=\"jstree-open\">"+root.getName());
		sb.append(getChildNodes(root));
		sb.append("</li>");
		sb.append("</ul>");
		System.out.println(sb);
		return sb.toString();
	}
	
	private static String getChildNodes(Folder folder){
		StringBuffer sb = new StringBuffer();
		sb.append("<ul>");
		List<TestCase> testCaseList = new ArrayList<TestCase>();
		testCaseList.addAll(folder.getTestCaseList());
		Collections.sort(testCaseList);
		for(TestCase testcase:testCaseList){
			sb.append("<li id=\"t"+testcase.getTestCaseId()+"\" data-jstree='{\"icon\":\"./images/document.png\",\"description\":\"file\"}'>");
			sb.append("<a  href=\"#\" onClick=\"javascript: selectTestCase('"+testcase.getTestCaseId()+"')\">"+testcase.getTitle()+"</a>");
			sb.append("</li>");
		}
		
		List<Folder> folderList = new ArrayList<Folder>();
		folderList.addAll(folder.getChildNodes());
		Collections.sort(folderList);
		for(Folder childFolder:folderList){
			sb.append("<li id=\"f"+childFolder.getFolderId()+"\" data-jstree='{\"description\":\"folder\"}'>");
			sb.append(childFolder.getName());
			sb.append(getChildNodes(childFolder));
			sb.append("</li>");
		}
		sb.append("</ul>");
		return sb.toString();
	}

}
