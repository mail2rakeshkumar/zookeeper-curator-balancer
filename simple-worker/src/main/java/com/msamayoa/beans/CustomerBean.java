package com.msamayoa.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.msamayoa.entity.CustomerEntity;

/**
 * 
 * @author msamayoa
 * CustomerBean class
 */
public final class CustomerBean {
	
	private List<CustomerEntity> customerList;
	private final AtomicInteger customerCounter = new AtomicInteger();	
	
	public CustomerBean(){
		customerList = new ArrayList<CustomerEntity>(2);
		customerList.add(CustomerEntity
				.builder()
				.id(new Integer (customerCounter.incrementAndGet()))
				.firstName("John")
				.lastName("Doe")
				.build());
		customerList.add(CustomerEntity
				.builder()
				.id(new Integer (customerCounter.incrementAndGet()))
				.firstName("James")
				.lastName("Bond")
				.build());
	}
	
	public List<CustomerEntity> getList(){
		return customerList;
	}
	
	public List<CustomerEntity> getListByFilter(String filter){
		return null;
	}
	
}