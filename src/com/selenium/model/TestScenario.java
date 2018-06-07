package com.selenium.model;

import java.io.Serializable;


import java.util.ArrayList;
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
@Table(name="testscenario")

public class TestScenario implements Serializable,Comparable<TestScenario>{
	
	private static final long serialVersionUID = -8767337896773261247L;
	
	private String title;
	
	private int testScenarioId;
	
	private String createdBy;
	
	private Date createdDate;
	
	private String modifiedBy;
	
	private Date modifiedDate;
	
	private String description;
	
	private int folderId;
	
	private int projectId;
	
	private String browser;
	
	private String resultpath;
	
	private boolean onerror;
	
	private List<TestScenarioStep> stepList = new ArrayList<TestScenarioStep>();

	@Id
	@GeneratedValue
	@Column(name="testscenarioId")
	public int getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(int testScenarioId) {
		this.testScenarioId = testScenarioId;
	}
	
	@Column(name="resultpath")
	public String getResultPath() {
		return resultpath;
	}

	public void setResultPath(String resultpath) {
		this.resultpath = resultpath;
	}
	
	@Column(name="onerror")
	public boolean getOnError() {
		return onerror;
	}

	public void setOnError(boolean onerror) {
		this.onerror = onerror;
	}
	
	/*
	@Column(name="lastrunstatus")
	public String getLastRunStatus() {
		return lastrunstatus;
	}

	public void setLastRunStatus(String lastrunstatus) {
		this.lastrunstatus = lastrunstatus;
	}
	*/
	
	@Column(name="createdBy")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	
	@Column(name="projectId")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
	@JoinColumn(name="testscenarioId", referencedColumnName="testscenarioId")
	@Cascade({CascadeType.ALL,org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	public List<TestScenarioStep> getStepList() {
		return stepList;
	}

	public void setStepList(List<TestScenarioStep> stepList) {
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
	public int compareTo(TestScenario other) {
	    return (this.title.compareTo( other.title));
	}
	
	
	

}
