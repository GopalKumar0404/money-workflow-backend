package com.gopal.MoneyWorkflow.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestBodyNotSupported extends RuntimeException {
	
	String message;

	public RequestBodyNotSupported(String message) {
		super("Request Body with Id not supported for creation");
		this.message = message;
	}
	
	

}
