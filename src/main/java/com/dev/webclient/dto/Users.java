package com.dev.webclient.dto;

import lombok.Data;

@Data
public class Users {
	
	private String id;
	private String name;
	private String username;
	private String email;
	private Address address;
	private String phone;
	private String website;

}
