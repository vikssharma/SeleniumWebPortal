package com.selenium.model;

import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Project")
public class Project implements Serializable{
	
	private static final long serialVersionUID = -8767337896773261247L;
	
	private int projectId;
	
	private String domain;
	
	private String application;
	


	@Id
	@GeneratedValue
	@Column(name="projectId")
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	@Column(name="domain")
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(name="application")
	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}



}
