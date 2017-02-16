package com.msamayoa.controller;

import javax.ws.rs.core.UriBuilder;

import org.apache.commons.lang3.Validate;
import org.apache.curator.x.discovery.ServiceInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msamayoa.Application;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * 
 * @author msamayoa
 * Delegate Controller
 */
@RestController
public class DelegateController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(DelegateController.class);
		
	private Client client;
	private WebResource webResource;
		
	public DelegateController() {
		client = Client.create();		
	}
	
	@RequestMapping("/cl/api/customer/list")
	public String delegateApiCustomer() {
		String response = "no client register";
		try {			
			ServiceInstance<Object> serviceInstance = Application.getServiceProvider()
					.getInstance();
			Validate.notNull(serviceInstance);
			String address = serviceInstance.buildUriSpec();			
			response = (address + "/api/customer/list").toString();
			logger.info("response :" +response);
			
			webResource  = client
					.resource(UriBuilder.fromUri(serviceInstance.buildUriSpec()).path("/api/customer/list").build());
			ClientResponse cr = webResource.accept("application/json")
	                   .get(ClientResponse.class);
			if (cr.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ cr.getStatus());
			}			
			response = cr.getEntity(String.class);
			cr.close();
			
		} catch (Exception e) {
			logger.error("Error___>>: " + e.getMessage());
			e.printStackTrace();
			//TODO improve this..
			//What I can see is that the ServiceDiscovery don't update immediatly when a worker's registry 
			//is lost in ZooKepper, it takes somes seconds to update the registry, meanwhile you 
			//lost the some income requests, due the request in redirect to an failed worker.
		}
		return response;
	}
	
}
