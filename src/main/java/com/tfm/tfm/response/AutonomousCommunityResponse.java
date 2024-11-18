package com.tfm.tfm.response;

public class AutonomousCommunityResponse {
  
    private String name;

    public AutonomousCommunityResponse(String name) {
		this.name = name;
	}

    public String getName() {
        return name ;
    }

    public void setName(String name ) {
        this.name  = name ;
    }
}
