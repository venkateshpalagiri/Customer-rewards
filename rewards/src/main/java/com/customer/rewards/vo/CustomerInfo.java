package com.customer.rewards.vo;

import java.util.List;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.Transaction;

import lombok.Data;
@Data

public class CustomerInfo {
	Customer customer;
	List<Transaction> transactionList;

}
