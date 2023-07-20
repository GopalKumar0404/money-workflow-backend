package com.gopal.MoneyWorkflow.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class TransactionDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private String username;
	private String transactionDate;
	private long transactionAmount;
	private String typeOfTransaction;
	private long totalAmount;
	private String description;
	
	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@ManyToOne
	private User user;

}
