package com.spring.webservice.mongodb.responses;

import java.util.List;

import com.spring.webservice.mongodb.model.Person;



//	http://CodeArsenal.net/2015/10/java-spring-restful-web-service-mongodb.html

public class MultiplePersonResponse {

	private boolean success;
	private List<Person> persons;
	
	public MultiplePersonResponse(boolean success, List<Person> persons) {
		this.success = success;
		this.persons = persons;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<Person> getPersons() {
		return persons;
	}
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
}
