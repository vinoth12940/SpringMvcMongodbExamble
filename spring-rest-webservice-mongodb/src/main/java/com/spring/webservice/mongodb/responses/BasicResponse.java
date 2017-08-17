package com.spring.webservice.mongodb.responses;

//	http://CodeArsenal.net/2015/10/java-spring-restful-web-service-mongodb.html

public class BasicResponse {
	
	private boolean success;
	private String message;
	
	public BasicResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
