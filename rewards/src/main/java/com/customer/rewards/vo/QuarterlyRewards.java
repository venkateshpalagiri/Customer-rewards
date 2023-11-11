package com.customer.rewards.vo;

import java.util.List;

import com.customer.rewards.entity.Transaction;

import lombok.Data;
@Data
public class QuarterlyRewards {

	double quarterlyRewardsAmt;
	String quarterName;
	List<Transaction> transactionList;
	
	
	
}
