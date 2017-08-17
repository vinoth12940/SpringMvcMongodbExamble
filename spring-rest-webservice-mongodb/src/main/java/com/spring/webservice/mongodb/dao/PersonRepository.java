package com.spring.webservice.mongodb.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.spring.webservice.mongodb.model.Person;



// 		http://CodeArsenal.net/2015/10/java-spring-restful-web-service-mongodb.html

@Repository
public class PersonRepository {
	
	public static final String PERSON_COLLECTION_NAME = "person";

	@Autowired
    private MongoTemplate mongo;
	
	public void addPerson(Person person){
		if(!mongo.collectionExists(Person.class)){
			mongo.createCollection(Person.class);
		}
		mongo.insert(person, PERSON_COLLECTION_NAME);
	}
	
	public Person getPersonByName(String name) {
		return mongo.findOne(Query.query(Criteria.where("name").is(name)), Person.class, PERSON_COLLECTION_NAME);
	}

	public List<Person> getAllPersons() {
        return mongo.findAll(Person.class, PERSON_COLLECTION_NAME);
    }
	
	public Person deletePerson(String name) {
		Person person = mongo.findOne(Query.query(Criteria.where("name").is(name)), Person.class, PERSON_COLLECTION_NAME);
		mongo.remove(person, PERSON_COLLECTION_NAME);
		
		return person;
	}
	
	public Person updatePerson(String name, Person person) {
    	Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
 
		Update update = new Update();
		update.set("name", person.getName());
		update.set("age", person.getAge());
		update.set("country", person.getCountry());
 
        mongo.updateFirst(query, update, Person.class);
        
        return person;
    }
}
