package com.msamayoa;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	private static ServiceProvider<Object> serviceProvider;
	
	public static void main(String[] args) {		
        SpringApplication.run(Application.class, args);
        try{
        	initCuratorService();
        }catch(Exception e){
        	logger.error("CuratorFramework error:" + e.getMessage());
        }
    }	
		
	private static void initCuratorService() throws Exception {
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("localhost:2181",
				new RetryNTimes(5, 1000));
		curatorFramework.start();

		ServiceDiscovery<Object> serviceDiscovery = ServiceDiscoveryBuilder.builder(Object.class)
				.basePath("load-balancing-example")
				.client(curatorFramework)
				.build();
		serviceDiscovery.start();
		
		serviceProvider = serviceDiscovery
				.serviceProviderBuilder()
				.serviceName("worker")
				.build();
		
		serviceProvider.start();
	}
	
	public static ServiceProvider<Object> getServiceProvider(){
		return serviceProvider;
	}	
	
}
