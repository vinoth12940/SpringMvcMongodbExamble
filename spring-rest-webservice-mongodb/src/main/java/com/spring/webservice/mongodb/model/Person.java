package com.spring.webservice.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//	http://CodeArsenal.net/2015/10/java-spring-restful-web-service-mongodb.html

@Document
public class Person {

	@Id
	private String id;
	
	private String name;
	private Integer age;
	private String country;
	
	public Person(){}
	
	public Person(String name, Integer age, String country){
		setName(name);
		setAge(age);
		setCountry(country);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String toString() {
		return "[" + getName() 
				+ ", " + getAge()
				+ ", " + getCountry()
				+ "]";
	}
}
