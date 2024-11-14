package com.tfm.tfm.service;

import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService{

	public String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
