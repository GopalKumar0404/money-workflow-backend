package com.gopal.MoneyWorkflow.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gopal.MoneyWorkflow.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{

}
