package com.tfm.tfm.service;

import java.util.List;

import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.PriceEntity;

public interface PriceService {

	PriceEntity getPriceEntity(Integer price);

	List<BrandEntity> getBrandsByPrice(Integer price);

}
