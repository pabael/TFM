package com.tfm.tfm.response;

public class LocationResponse {
	
	private String name;
	private String province;

	public LocationResponse() {}
	
	public LocationResponse(String name, String province) {
		this.name = name;
		this.province = province;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
}
