package com.msamayoa.controller;


public class CustomPayload {
	
	private String name;
	private String speed;
	private String location;

    CustomPayload(String name, String speed, String location) {
        this.name = name;
        this.speed = speed;
        this.location = location;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}    
	
}
