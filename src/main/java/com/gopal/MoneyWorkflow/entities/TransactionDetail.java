package com.gopal.MoneyWorkflow.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class TransactionDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private String username;
	private Date transactionDate;
	private long transactionAmount;
	private String typeOfTransaction;
	private long totalAmount;
	private String description;
	
	
	@ManyToOne
	private User user;

}
