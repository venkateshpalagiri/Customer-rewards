package com.customer.rewards.vo;

import java.util.List;

import lombok.Data;
@Data
public class TransactionVo {
	List<QuarterlyRewards> quarterlyRewards;
	String customerName;
	Long customerId;

}
