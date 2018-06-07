package com.selenium.model;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="testcase")

public class TestCase implements Serializable,Comparable<TestCase>,Cloneable{
	
	private static final long serialVersionUID = -8767337896773261247L;
	
	private String title;
	
	private int testCaseId;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String description;
	
	private int folderId;
	
	private String browser;
	
	private String resultpath;
	
	private String lastrunstatus;
	
	private boolean onerror;
	
	private List<TestCaseStep> stepList = new ArrayList<TestCaseStep>();

	@Id
	@GeneratedValue
	@Column(name="testcaseId")
	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}
	
	@Column(name="resultpath")
	public String getResultPath() {
		return resultpath;
	}

	public void setResultPath(String resultpath) {
		this.resultpath = resultpath;
	}
	
	@Column(name="lastrunstatus")
	public String getLastRunStatus() {
		return lastrunstatus;
	}

	public void setLastRunStatus(String lastrunstatus) {
		this.lastrunstatus = lastrunstatus;
	}

	@Column(name="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="onerror")
	public boolean getOnError() {
		return onerror;
	}

	public void setOnError(boolean onerror) {
		this.onerror = onerror;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="createdDate")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="modifiedBy")
	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="modifiedDate")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

		
	@Column(name="folderId")
	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	@Column(name="title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@OneToMany(fetch = FetchType.EAGER,orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="testcaseId", referencedColumnName="testcaseId")
	@Cascade({CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public List<TestCaseStep> getStepList() {
		return stepList;
	}

	public void setStepList(List<TestCaseStep> stepList) {
		this.stepList = stepList;
	}

	@Column(name="browser")
	public String getBrowser() {
		return browser;
	}

	
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Override
	public int compareTo(TestCase other) {
	    return (this.title.compareTo( other.title));
	}
	
	 public Object clone() {
		 TestCase clonedTestCase = null;
	        try {
	        	clonedTestCase =  (TestCase)super.clone();
	        }
	        catch (CloneNotSupportedException e) {
	            System.out.println(e);
	        }
	        if(clonedTestCase!=null){
	        	List<TestCaseStep> clonedList = new ArrayList<TestCaseStep>();
	        	for(TestCaseStep step:this.getStepList()){
	        		clonedList.add((TestCaseStep)step.clone());
	        	}
	        	Collections.sort(clonedList);
	        	clonedTestCase.setStepList(clonedList);
	        	 return clonedTestCase;
	        }
	        return this;
	    }

}
