package com.gopal.MoneyWorkflow.Exceptions;


public class EntityValidationError extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public EntityValidationError(String message) {
		super(String.format(message));
		this.message = message;
	}
}
