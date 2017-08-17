package com.spring.webservice.mongodb.responses;

import com.spring.webservice.mongodb.model.Person;


public class SinglePersonResponse {

	private boolean success;
	private Person person;
	
	public SinglePersonResponse(boolean success, Person person) {
		this.success = success;
		this.person = person;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Person getPersons() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
