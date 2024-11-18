package com.tfm.tfm.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GeoApiProvinceResponse {

    @JsonProperty("data")
    private List<Community> data;

    public List<Community> getData() {
        return data;
    }

    public void setData(List<Community> data) {
        this.data = data;
    }

    public static class Community {
        
        @JsonProperty("PRO")
        private String name;

        @JsonProperty("CPRO")
        private String code;

        public String getName() {
            return name ;
        }

        public void setName(String name ) {
            this.name  = name ;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
