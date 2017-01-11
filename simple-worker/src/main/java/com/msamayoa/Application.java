package com.msamayoa;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.UriSpec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.msamayoa.controller.CustomPayload;
import com.msamayoa.utils.Utils;

/**
 * 
 * @author msamayoa
 * Spring application
 */
@SpringBootApplication
public class Application {
		
	public static void main(String[] args) {		
        SpringApplication.run(Application.class, args);
        registerInZookeeper(Utils.fromStringToInt(args[1]));
    }	
		
	private static void registerInZookeeper(int port) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory
        		.newClient("localhost:2181", new RetryNTimes(5, 1000));
        curatorFramework.start();
        try{
	        ServiceInstance<CustomPayload> serviceInstance = ServiceInstance.<CustomPayload>builder()        		
	        		.uriSpec(new UriSpec("{scheme}://{address}:{port}"))
	        		.address("localhost")
	        		.port(port)
	        		.name("worker")
	        		.build();
       
	        ServiceDiscoveryBuilder.builder(CustomPayload.class)
	        .basePath("load-balancing-example")
	        .client(curatorFramework)        
	        .thisInstance(serviceInstance)
	        .build()
	        .start();
        }catch(Exception e){
        	System.out.println(e.getMessage());
        }
	}
	
}