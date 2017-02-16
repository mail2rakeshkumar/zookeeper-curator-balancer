package com.msamayoa.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerEntity {

	private Integer id;
	private String firstName;
	private String lastName;
	
}
