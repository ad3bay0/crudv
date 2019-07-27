package com.ad3bay0.crudv.demo;

import com.ad3bay0.crudv.demo.models.Customer;
import com.ad3bay0.crudv.demo.repo.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repo){

		return (args)->{

			repo.save(new Customer("Bay","Adeniyan"));
			repo.save(new Customer("Bay1","Adeniyan"));
			repo.save(new Customer("Bay2","Adeniyan"));
			repo.save(new Customer("Bay3","Adeniyan"));


		};

	}

}
