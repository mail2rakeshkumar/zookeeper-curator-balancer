package com.msamayoa.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msamayoa.beans.WorkerBean;

/**
 * 
 * @author msamayoa
 * Worker controller
 */
@RestController
public class WorkController {
	
	private final AtomicLong counter = new AtomicLong();		
	@Autowired WorkerBean workerBean;
	
	@RequestMapping("/work")
	public String doSomeWork() {
		return new StringBuilder("Work done by ")
				.append(workerBean.getName())
				.append(" - Request number[")
				.append(counter.incrementAndGet())
				.append("]")
				.toString();		
    }
	
}
