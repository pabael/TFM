package com.tfm.tfm.response;

public class LocationResponse {
	
	private String name;

	public LocationResponse() {}
	
	public LocationResponse(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	}
}
