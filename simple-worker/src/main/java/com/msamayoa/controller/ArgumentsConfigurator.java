package com.msamayoa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msamayoa.beans.CustomerBean;
import com.msamayoa.beans.WorkerBean;
import com.msamayoa.utils.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author msamayoa
 *
 */
@Configuration
public class ArgumentsConfigurator {

	private static final Logger logger = LoggerFactory
			.getLogger(ArgumentsConfigurator.class);
	
	@Autowired	
	private ApplicationArguments args;
	
	@Bean
	public WorkerBean createWorkerBean() {		
		WorkerBean workerBean = null;		
		if (null != args) {
			String[] configArgs = args.getSourceArgs();			
			if (configArgs.length>=2) {
				String name = configArgs[0];
				int port = Utils.getIntParameter(configArgs[1]);
				workerBean = new WorkerBean(name, port);
			}
		}else{
			logger.error("invalid args");
		}
		return workerBean;
	}
	
	@Bean
	public CustomerBean createCustomerBean(){
		return new CustomerBean();
	}
	
}