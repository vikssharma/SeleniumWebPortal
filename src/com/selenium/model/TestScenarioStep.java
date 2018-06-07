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
@Table(name="testscenario_step")
public class TestScenarioStep implements Serializable, Comparable<TestScenarioStep>{
	
	private static final long serialVersionUID = -8767337896773261247L;
	
	private Long id;
	
	private int testScenarioId;
	
//	private int testCaseId;
	
	private int stepNumber;
	
	private boolean toBeExecuted;
	
	private TestCase testCase; 
	

	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	@Column(name="testcaseId")
//	public int getTestCaseId() {
//		return testCaseId;
//	}
//
//	public void setTestCaseId(int testCaseId) {
//		this.testCaseId = testCaseId;
//	}

	@Column(name="testScenarioId")
	public int getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(int testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	@Column(name="stepNumber")
	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	@Column(name="toBeExecuted")
	public boolean isToBeExecuted() {
		return toBeExecuted;
	}

	public void setToBeExecuted(boolean toBeExecuted) {
		this.toBeExecuted = toBeExecuted;
	}
	
	@Override
	public int compareTo(TestScenarioStep other) {
	    return Integer.compare(this.stepNumber, other.stepNumber);
	}



	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "testcaseId")
	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	
}
