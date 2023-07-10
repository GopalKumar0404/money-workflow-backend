package com.gopal.MoneyWorkflow.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gopal.MoneyWorkflow.entities.TransactionDetail;
import com.gopal.MoneyWorkflow.entities.User;


@Repository
public interface TransactionDetailRepo extends JpaRepository<TransactionDetail, Long> {
	
	List<TransactionDetail> findByUser(User user);

}
