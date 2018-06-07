package com.selenium.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="testcase_step")
public class TestCaseStep implements Serializable, Comparable<TestCaseStep>,Cloneable {
	
	private static final long serialVersionUID = -8767337896773261247L;
	
	private Long id;
	
	private int testCaseId;
	
	private int actionId;
	
	private int stepNumber;
	
	private String objectProperty;
	
	private String objectValue;
	
	private boolean toBeExecuted;
	
	private String actionDisplay;
	
//	private Action action;

	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="testcaseId")
	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}

	@Column(name="actionId")
	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	@Column(name="stepNumber")
	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	@Column(name="objectProperty")
	public String getObjectProperty() {
		return objectProperty;
	}

	public void setObjectProperty(String objectProperty) {
		this.objectProperty = objectProperty;
	}

	@Column(name="objectValue")
	public String getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(String objectValue) {
		this.objectValue = objectValue;
	}

	@Column(name="toBeExecuted")
	public boolean isToBeExecuted() {
		return toBeExecuted;
	}

	public void setToBeExecuted(boolean toBeExecuted) {
		this.toBeExecuted = toBeExecuted;
	}
	
	@Override
	public int compareTo(TestCaseStep other) {
	    return Integer.compare(this.stepNumber, other.stepNumber);
	}

	public String getActionDisplay() {
		return actionDisplay;
	}

	public void setActionDisplay(String actionDisplay) {
		this.actionDisplay = actionDisplay;
	}

	 public Object clone() {
	        try {
	            return (TestCaseStep)super.clone();
	        }
	        catch (CloneNotSupportedException e) {
	        	System.out.println(e);
	        }
	        return this;
	    }
	

	
}
