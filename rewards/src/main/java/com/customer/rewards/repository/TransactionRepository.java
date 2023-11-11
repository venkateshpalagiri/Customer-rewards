package com.customer.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.rewards.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


	List<Transaction> getByCustomerId(Long customerId);

}
