package com.gopal.MoneyWorkflow.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String resourceName;
	private String resourceField;
	private Long resourveValue;
	
	
	public ResourceNotFoundException(String resourceName, String resourceField, Long resourveValue) {
		
		super(String.format("%s is not found withh %s : %s",resourceName,resourceField,resourveValue.toString()));
		this.resourceName = resourceName;
		this.resourceField = resourceField;
		this.resourveValue = resourveValue;
	}
	
	

}
