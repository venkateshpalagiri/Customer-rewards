package com.customer.rewards.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	private Long transactionAmount;
	private Integer transactionMonth;
    private Integer transactionQuarter;
	private Date transactionDate;
    private long  customerId;
    private double rewards;
   

}
