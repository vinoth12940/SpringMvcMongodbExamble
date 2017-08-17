package com.spring.webservice.mongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mongodb.MongoClient;

@Configuration
@EnableWebMvc
@ComponentScan({ "com.spring.webservice.mongodb" })
@PropertySource(value="classpath:config.properties", ignoreResourceNotFound=true)
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	private Environment env;
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {

		String db_host = env.getProperty("mongodb.host");		
		String db_port = env.getProperty("mongodb.port");
		String db_name = env.getProperty("mongodb.db");
		
		MongoClient mongo = new MongoClient(db_host, Integer.valueOf(db_port));
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, db_name);
		return new MongoTemplate(mongoDbFactory);

	}
}
