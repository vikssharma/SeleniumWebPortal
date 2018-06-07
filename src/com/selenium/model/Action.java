package com.selenium.model;
import java.io.Serializable;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="action")
public class Action implements Serializable{

	private static final long serialVersionUID = -8767337896773261247L;
	
	private int actionId;
	
	private String display;
	
	private String value;
	
	private boolean inputRequired;
	
	@Id
	@GeneratedValue
	@Column(name="actionId")
	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	@Column(name="value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name="display")
	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	@Column(name="inputRequired")
	public boolean isInputRequired() {
		return inputRequired;
	}

	public void setInputRequired(boolean inputRequired) {
		this.inputRequired = inputRequired;
	}
	
	
	

}
