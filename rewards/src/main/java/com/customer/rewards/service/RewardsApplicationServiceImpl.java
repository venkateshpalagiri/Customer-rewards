package com.customer.rewards.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.rewards.entity.Customer;
import com.customer.rewards.entity.Transaction;
import com.customer.rewards.repository.CustomerRepository;
import com.customer.rewards.repository.TransactionRepository;
import com.customer.rewards.vo.CustomerInfo;
import com.customer.rewards.vo.CustomerRewardsVO;
import com.customer.rewards.vo.QuarterlyRewards;
import com.customer.rewards.vo.RewardsVO;
import com.customer.rewards.vo.TransactionVo;

@Service
public class RewardsApplicationServiceImpl implements RewardsApplicationService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private TransactionRepository transactionRepository;

//	@Autowired
//	private RewardsVO rewardsVO;
//	@Autowired
//	private QuarterlyRewards quarterlyRewards;
//	@Autowired
//	private TransactionVo transactionVO;
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Transaction saveTransaction(Transaction txn) {

		double rewards = 0.0;
		if (txn.getTransactionAmount() > 50 && txn.getTransactionAmount() <= 100) {
			rewards = ((txn.getTransactionAmount() - 50) * 1);

		} else if (txn.getTransactionAmount() > 100) {
			rewards = (((txn.getTransactionAmount() - 50) * 1) + ((txn.getTransactionAmount() - 100) * 1));

		}
		int month = txn.getTransactionDate().getMonth();
		txn.setTransactionMonth(month + 1);
		if (month >= 1 && month <= 3) {
			txn.setTransactionQuarter(1);

		} else if (month >= 4 && month <= 6) {
			txn.setTransactionQuarter(2);

		} else if (month >= 7 && month <= 9) {
			txn.setTransactionQuarter(3);
		} else {
			txn.setTransactionQuarter(4);
		}

		txn.setRewards(rewards);

		return transactionRepository.save(txn);

	}

	@Override
	public CustomerRewardsVO fetchCustomerList() {
		List<Customer> customerList = customerRepository.findAll();
		Iterator ir = customerList.iterator();
		CustomerRewardsVO customerRewardsVO = new CustomerRewardsVO();
		List<CustomerInfo> customerInfoList = new ArrayList<CustomerInfo>();
		while (ir.hasNext()) {
			Customer customer = (Customer) ir.next();
			Long customerId = customer.getCustomerId();
			List<Transaction> transactionsList = transactionRepository.getByCustomerId(customerId);
			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomer(customer);
			customerInfo.setTransactionList(transactionsList);
			customerInfoList.add(customerInfo);
		}
		customerRewardsVO.setCustomerInfo(customerInfoList);

		return customerRewardsVO;
	}

	@Override
	public List<Transaction> fetchTransactionList() {
		return transactionRepository.findAll();
	}

	@Override
	public Customer fetchCustomerById(Long customerId) {
		return customerRepository.findById(customerId).get();
	}

	@Override
	public RewardsVO fetchCustomerRewardsList() {

		List<Customer> customerList = customerRepository.findAll();
		Iterator ir = customerList.iterator();
		RewardsVO rewardsVO = new RewardsVO();
		List<TransactionVo> transactionVoList = new ArrayList<TransactionVo>();
		while (ir.hasNext()) {
			Customer customer = (Customer) ir.next();
			Long customerId = customer.getCustomerId();
			String customerName = customer.getCustomerName();
			List<Transaction> transactionsList = transactionRepository.getByCustomerId(customerId);
			int firstQuarter = 1;
			int secondQuarter = 2;
			int thirdQuarter = 3;
			int fourthQuarter = 4;

			List<Transaction> firstQuarterList = (List<Transaction>) transactionsList.stream()
					.filter(line -> line.getTransactionQuarter() == firstQuarter).collect(Collectors.toList());
			List<Transaction> secondQuarterList = (List<Transaction>) transactionsList.stream()
					.filter(line -> line.getTransactionQuarter() == secondQuarter).collect(Collectors.toList());
			List<Transaction> thirdQuarterList = (List<Transaction>) transactionsList.stream()
					.filter(line -> line.getTransactionQuarter() == thirdQuarter).collect(Collectors.toList());
			List<Transaction> fourthQuarterList = (List<Transaction>) transactionsList.stream()
					.filter(line -> line.getTransactionQuarter() == fourthQuarter).collect(Collectors.toList());

			Iterator firstQuarterIterator = firstQuarterList.iterator();
			Iterator secondQuarterIterator = secondQuarterList.iterator();
			Iterator thirdQuarterIterator = thirdQuarterList.iterator();
			Iterator fourthQuarterIterator = fourthQuarterList.iterator();

			List<Transaction> transactionList = new ArrayList<Transaction>();
			TransactionVo transactionVO = new TransactionVo();
			List<QuarterlyRewards> quarterlyRewardsList = new ArrayList<QuarterlyRewards>();
			double transactionTotalAmt = 0.0;
			QuarterlyRewards rewards = new QuarterlyRewards();
			transactionList = new ArrayList<Transaction>();
			while (firstQuarterIterator.hasNext()) {
				
				Transaction tr = (Transaction) firstQuarterIterator.next();
				transactionTotalAmt = transactionTotalAmt + tr.getRewards();
				
				transactionList.add(tr);
				
			}
			rewards.setQuarterlyRewardsAmt(transactionTotalAmt);
			rewards.setQuarterName("FirstQuarter");
			rewards.setTransactionList(transactionsList);
			quarterlyRewardsList.add(rewards);
			transactionTotalAmt = 0.0;
			 transactionList = new ArrayList<Transaction>();
			  rewards = new QuarterlyRewards();
			while (secondQuarterIterator.hasNext()) {
				

				Transaction tr = (Transaction) secondQuarterIterator.next();
				transactionTotalAmt = transactionTotalAmt + tr.getRewards();
				
				transactionList.add(tr);
				
			}
			rewards.setQuarterlyRewardsAmt(transactionTotalAmt);
			rewards.setQuarterName("SecondQuarter");
			rewards.setTransactionList(transactionsList);
			quarterlyRewardsList.add(rewards);
			transactionTotalAmt = 0.0;
			rewards = new QuarterlyRewards();
			transactionList = new ArrayList<Transaction>();
			while (thirdQuarterIterator.hasNext()) {
				Transaction tr = (Transaction) thirdQuarterIterator.next();
				transactionTotalAmt = transactionTotalAmt + tr.getRewards();
				
				transactionList.add(tr);
				
			}
			rewards.setQuarterlyRewardsAmt(transactionTotalAmt);
			rewards.setQuarterName("ThirdQuarter");
			rewards.setTransactionList(transactionsList);
			quarterlyRewardsList.add(rewards);
			transactionTotalAmt = 0.0;
			 rewards = new QuarterlyRewards();
			 transactionList = new ArrayList<Transaction>();
			while (fourthQuarterIterator.hasNext()) {
				
				Transaction tr = (Transaction) fourthQuarterIterator.next();
				transactionTotalAmt = transactionTotalAmt + tr.getRewards();
				
				transactionList.add(tr);
				
			}
			rewards.setQuarterlyRewardsAmt(transactionTotalAmt);
			rewards.setQuarterName("FourthQuarter");
			rewards.setTransactionList(transactionsList);
			quarterlyRewardsList.add(rewards);
			transactionVO.setCustomerId(customerId);
			transactionVO.setCustomerName(customerName);
			transactionVO.setQuarterlyRewards(quarterlyRewardsList);
			transactionVoList.add(transactionVO);
		}
		rewardsVO.setTransactionVo(transactionVoList);
		// TODO Auto-generated method stub
		return rewardsVO;
	}

}
