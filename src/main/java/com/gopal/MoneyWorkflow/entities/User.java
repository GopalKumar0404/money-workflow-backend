package com.gopal.MoneyWorkflow.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class User {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private Long phoneNo;
	private String lastTransactionDate;
	private Long lastTransactionAmount;
	private String typeOfTransaction;
	private Long totalAmount;
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TransactionDetail> transactionDetail = new ArrayList<>();
}
