package com.msamayoa.controller;

import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msamayoa.Application;

/**
 * 
 * @author msamayoa
 * Delegate Controller
 */
@RestController
public class DelegateController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DelegateController.class);
	
	@RequestMapping("/delegate")
	public String delegate() {
		String response = "no client register";
		try {
			ServiceInstance<Object> serviceInstance = Application.getServiceProvider().getInstance();
			String address = serviceInstance.buildUriSpec();
			response = (address + "/work").toString();
			logger.info("response :" +response);			
		} catch (Exception e) {
			logger.error("Error: " + e.getMessage());
			response = "no client registered	";
		}
		return response;
	}
	
}
