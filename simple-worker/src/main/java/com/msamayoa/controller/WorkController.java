package com.msamayoa.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.msamayoa.beans.CustomerBean;
import com.msamayoa.beans.WorkerBean;
import com.msamayoa.entity.CustomerEntity;

/**
 * 
 * @author msamayoa
 * Worker controller that handles clients request
 */
@RestController
public class WorkController {	
	
	private static final Logger logger = LoggerFactory
			.getLogger(WorkController.class);
	
	private final AtomicLong counter = new AtomicLong();
	@Autowired 
	private WorkerBean workerBean;
	@Autowired 
	private CustomerBean customerBean;
	
	@RequestMapping("/api/customer/list")
	@ResponseBody
	public ResponseEntity<List<CustomerEntity>> list() {		
		logger.info( new StringBuilder("Worker ")
				.append(workerBean.getName())
				.append(" - Request [")
				.append(counter.incrementAndGet())
				.append("]")
				.toString()
				);
		return new ResponseEntity<List<CustomerEntity>>(
				customerBean.getList(), 
				HttpStatus.OK);
    }
	
}