package com.selenium.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="testscenario_input")
public class TestScenarioInput implements Serializable{

	private static final long serialVersionUID = -8767337896773261247L;
	
	private Long id;
	
	private int testScenarioId;
	
	private int testScenarioStepNumber;
	
	private int testCaseId;
	
	private int testCaseStepNumber;
	
	private String inputValue;
	
	private boolean toBeExecuted;


	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="testscenarioId")
	public int getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(int testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	@Column(name="testscenariostepNumber")
	public int getTestScenarioStepNumber() {
		return testScenarioStepNumber;
	}

	public void setTestScenarioStepNumber(int testScenarioStepNumber) {
		this.testScenarioStepNumber = testScenarioStepNumber;
	}

	@Column(name="testcaseId")
	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}

	
	

	@Column(name="InputValue")
	public String getInputValue() {
		return inputValue;
	}

	@Column(name="testcasestepNumber")
	public int getTestCaseStepNumber() {
		return testCaseStepNumber;
	}

	public void setTestCaseStepNumber(int testCaseStepNumber) {
		this.testCaseStepNumber = testCaseStepNumber;
	}

	public void setInputValue(String inputValue) {
		this.inputValue = inputValue;
	}

	@Column(name="toBeExecuted")
	public boolean isToBeExecuted() {
		return toBeExecuted;
	}

	public void setToBeExecuted(boolean toBeExecuted) {
		this.toBeExecuted = toBeExecuted;
	}
	
	
}
