package com.selenium.model;

import java.io.Serializable;
import java.util.ArrayList;
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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="ScenarioFolder")
public class ScenarioFolder implements Serializable,Comparable<ScenarioFolder>{
	
private static final long serialVersionUID = -8767337896773261247L;
	
	private int folderId;
	
	private String name;
	
	private int projectId;

	private ScenarioFolder parent;
	
	private Integer parentId;

	
	private Set<ScenarioFolder> childNodes = new HashSet<ScenarioFolder>();
	
	
	private Set<TestScenario> testScenarioList = new HashSet<TestScenario>();
	
	/**Empty constructor*/
	public ScenarioFolder() {}
	
	public ScenarioFolder(String name,Integer parentId,int projectId){
		this.name=name;
		this.parentId = parentId;
		this.projectId  = projectId;
	}

	@Id
	@GeneratedValue
	@Column(name="folderId")
	public int getFolderId() {
		return folderId;
	}

	public void setFolderId(int folderId) {
		this.folderId = folderId;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="projectId")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	
	@Column(name="parentId")
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}



	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="parentId", referencedColumnName="folderId")
	@Cascade({CascadeType.ALL})
	public Set<ScenarioFolder> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<ScenarioFolder> childNodes) {
		this.childNodes = childNodes;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="folderId", referencedColumnName="folderId")
	@Cascade({CascadeType.ALL})
	public Set<TestScenario> getTestScenarioList() {
		return testScenarioList;
	}

	public void setTestScenarioList(Set<TestScenario> testScenarioList) {
		this.testScenarioList = testScenarioList;
	}
	
	@Override
	public int compareTo(ScenarioFolder other) {
	    return (this.name.compareTo( other.name));
	}
	
}
