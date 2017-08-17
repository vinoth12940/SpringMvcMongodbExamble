package com.spring.webservice.mongodb.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webservice.mongodb.dao.PersonRepository;
import com.spring.webservice.mongodb.model.Person;
import com.spring.webservice.mongodb.responses.BasicResponse;
import com.spring.webservice.mongodb.responses.MultiplePersonResponse;
import com.spring.webservice.mongodb.responses.SinglePersonResponse;



// http://CodeArsenal.net/2015/10/java-spring-restful-web-service-mongodb.html

@Controller
public class PersonController {

	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("This is Default Home REST page.\n\n The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "status";
	}	
	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	@ResponseBody
	public MultiplePersonResponse getAllPersons() {
		logger.info("getAllPersons()...");
		List<Person> persons = personRepository.getAllPersons();
		MultiplePersonResponse resp = new MultiplePersonResponse(true, persons);
		logger.info("...getAllPersons()");
		return resp;
	}	
	
	@RequestMapping(value = "/person/{name}", method = RequestMethod.GET)
	@ResponseBody
	public SinglePersonResponse getPersonByName(@PathVariable("name") String name) {
		logger.info("getPersonByName()...");
		Person myPerson = personRepository.getPersonByName(name);
		if (myPerson != null) {
			logger.info("returned: " + myPerson.toString());
		} else {			
			logger.info("name: " + name + ", NOT FOUND!");
		}
		SinglePersonResponse resp = new SinglePersonResponse(true, myPerson);
		logger.info("...getPersonByName()");
		return resp;
	}
		
	@RequestMapping(value = "/person/delete/{name}", method = RequestMethod.DELETE)
	@ResponseBody
	public BasicResponse deletePersonByName(@PathVariable("name") String name) {
		logger.info("deletePersonByName()...");
		BasicResponse resp;
		Person person = personRepository.deletePerson(name);
		if (person != null) {
			logger.info("deleted: " + person.toString());
			resp = new BasicResponse(true, "Successfully deleted Person: " + person.toString());
		} else {
			logger.info("name: " + name + ", NOT FOUND!");
			resp = new BasicResponse(false, "Failed to delete name: " + name);
		}
		logger.info("...deletePersonByName()");
		return resp;
	}
		
	@RequestMapping(value = "/person/update/{name}", method = RequestMethod.PUT)
	@ResponseBody
	public BasicResponse updatePersonByName(@PathVariable("name") String name, @ModelAttribute("person") Person person) {
		logger.info("updatePersonByName()...");
		BasicResponse resp;
		Person myPerson = personRepository.updatePerson(name, person);
		if (myPerson != null) {
			logger.info("updated: " + myPerson.toString());
			resp = new BasicResponse(true, "Successfully updated Person: " + myPerson.toString());
		} else {
			logger.info("name: " + name + ", NOT FOUND!");
			resp = new BasicResponse(false, "Failed to update Person: " + name);
		}
		logger.info("...updatePersonByName()");
		return resp;
	}
	
	@RequestMapping(value = "/person/addPerson", method = RequestMethod.POST)
	@ResponseBody
	public BasicResponse addPerson(@RequestBody Person person) {
		logger.info("addPerson()...");
		BasicResponse resp;
		if (person.getName() != null && person.getName().length() > 0) {
			logger.info("adding: " + person.toString());
			personRepository.addPerson(person);
			resp = new BasicResponse(true, "Successfully added Person: " + person.getName());
		} else {
			logger.info("Failed to insert...");
			resp = new BasicResponse(false, "Failed to insert...");
		}		
		logger.info("..addPerson()");
		return resp;
	}
}
