package com.customer.rewards.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.rewards.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>  {

}
