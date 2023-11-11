package com.customer.rewards.service;

import java.util.List;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.vo.CustomerRewardsVO;
import com.customer.rewards.vo.RewardsVO;

public interface RewardsApplicationService {
	public Customer saveCustomer(Customer customer);
	public Transaction saveTransaction(Transaction transaction);
	public CustomerRewardsVO fetchCustomerList();
	public List<Transaction> fetchTransactionList();
	public Customer fetchCustomerById(Long customerId);
	public RewardsVO fetchCustomerRewardsList();

}
