package com.gopal.MoneyWorkflow;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gopal.MoneyWorkflow.entities.TransactionDetail;

@SpringBootApplication
public class MoneyWorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoneyWorkflowApplication.class, args);
	}
	
	@Bean
	ModelMapper getModelMapperObject() {
		return new ModelMapper();
	}
	

}
