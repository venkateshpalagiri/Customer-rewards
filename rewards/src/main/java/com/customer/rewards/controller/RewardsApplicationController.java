package com.customer.rewards.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.service.RewardsApplicationService;
import com.customer.rewards.vo.CustomerInfo;
import com.customer.rewards.vo.CustomerRewardsVO;
import com.customer.rewards.vo.RewardsVO;

@RestController
public class RewardsApplicationController {
	@Autowired
	private RewardsApplicationService rewardsApplicationService;
	
	@PostMapping("/customer")
	public Customer saveCustomer(@RequestBody Customer customer) {
		return rewardsApplicationService.saveCustomer(customer);
	}
	@PostMapping("/transaction")
	public Transaction saveTransaction(@RequestBody Transaction transaction) {
		return rewardsApplicationService.saveTransaction(transaction);
	}
	@GetMapping("/customer")
	public CustomerRewardsVO fetchCustomerList(){
		return rewardsApplicationService.fetchCustomerList();
	}
	@GetMapping("/transaction")
	public List<Transaction> fetchTransactionList(){
		return rewardsApplicationService.fetchTransactionList();
	}
	@GetMapping("/customer/{id}")
	public Customer fetchCustomerById(@PathVariable("id") Long customerId) {
		return rewardsApplicationService.fetchCustomerById(customerId);
	}
	
	@GetMapping("/rewards")
	public RewardsVO fetchCustomerRewardsList(){
		return rewardsApplicationService.fetchCustomerRewardsList();
	}
	
}
