package edu.hfu.broadcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

@SpringBootApplication

public class BroadcastApplication {

	public static void main(String[] args) {
		SpringApplication.run(BroadcastApplication.class, args);
	}
	
	@Bean
	public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
	   return new OpenEntityManagerInViewFilter();
	}

}
