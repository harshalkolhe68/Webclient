package com.dev.webclient.dto;

import lombok.Data;

@Data
public class Address {
	
	private String street;
	private String suite;
	private String 	city;
	private String zipcode;
	private Geo geo; 

}

@Data
class Geo {
	private String lat;
	private String lng;
}