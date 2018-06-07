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
@Table(name="Folder")
public class Folder implements Serializable,Comparable<Folder>{
	
private static final long serialVersionUID = -8767337896773261247L;
	
	private int folderId;
	
	private String name;
	
	private int projectId;

	private Folder parent;
	
	private Integer parentId;

	
	private Set<Folder> childNodes = new HashSet<Folder>();
	
	
	private Set<TestCase> testCaseList = new HashSet<TestCase>();
	
	/**Empty constructor*/
	public Folder() {}
	
	public Folder(String name,Integer parentId,int projectId){
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
	public Set<Folder> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<Folder> childNodes) {
		this.childNodes = childNodes;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="folderId", referencedColumnName="folderId")
	@Cascade({CascadeType.ALL})
	public Set<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(Set<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}
	
	@Override
	public int compareTo(Folder other) {
	    return (this.name.compareTo( other.name));
	}
	
	
}
