package com.tfm.tfm.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoApiLocationResponse {

    @JsonProperty("data")
    private List<Community> data;

    public List<Community> getData() {
        return data;
    }

    public void setData(List<Community> data) {
        this.data = data;
    }

    public static class Community {
        
        @JsonProperty("DMUN50")
        private String name;

        public String getName() {
            return name ;
        }

        public void setName(String name ) {
            this.name  = name ;
        }
    }
}
