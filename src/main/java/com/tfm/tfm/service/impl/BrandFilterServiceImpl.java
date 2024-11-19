package com.tfm.tfm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.entity.ProvinceEntity;
import com.tfm.tfm.response.BrandResponse;
import com.tfm.tfm.service.AutonomousCommunityService;
import com.tfm.tfm.service.BrandFilterService;
import com.tfm.tfm.service.BrandService;
import com.tfm.tfm.service.CategorySubcategoryService;
import com.tfm.tfm.service.ConsumerService;
import com.tfm.tfm.service.LabelService;
import com.tfm.tfm.service.LocationService;
import com.tfm.tfm.service.PriceService;
import com.tfm.tfm.service.ProvinceService;

@Service
public class BrandFilterServiceImpl implements BrandFilterService{

  @Autowired private BrandService brandService;
  @Autowired private CategorySubcategoryService categorySubcategoryService;
  @Autowired private LabelService labelService;
  @Autowired private ConsumerService consumerService;
  @Autowired private PriceService priceService;
  @Autowired private LocationService locationService;
  @Autowired private ProvinceService provinceService;
  @Autowired private AutonomousCommunityService autonomousCommunityService;

  public List<BrandResponse> getBrandsByCategory(String category){
    List<BrandEntity> brandEntityList = categorySubcategoryService.getBrandsByCategory(category);
    return getBrandResponseList(brandEntityList);
  }

  public	List<BrandResponse> getBrandsByIsVegan(boolean isVegan){
    List<BrandEntity> brandEntityList = brandService.getBrandListIsVegan(isVegan);
    return getBrandResponseList(brandEntityList);
  }

  public	List<BrandResponse> getBrandsByIsCrueltyFree(boolean isCrueltyFree){
    List<BrandEntity> brandEntityList = brandService.getBrandListIsCrueltyFree(isCrueltyFree);
    return getBrandResponseList(brandEntityList);
  }

  public List<BrandResponse> getBrandsByLabel(String label) {
    List<BrandEntity> brandEntityList = labelService.getBrandsByLabel(label);
    return getBrandResponseList(brandEntityList);
  }

  public List<BrandResponse> getBrandsByConsumer(String consumer){
    List<BrandEntity> brandEntityList = consumerService.getBrandsByConsumer(consumer);
    return getBrandResponseList(brandEntityList);
  }

  public List<BrandResponse> getBrandsByPrice(Integer price){
    List<BrandEntity> brandEntityList = priceService.getBrandsByPrice(price);
    return getBrandResponseList(brandEntityList);
  }

  public	List<BrandResponse> getBrandsByLocation(String location){
    List<BrandEntity> brandEntityList = locationService.getBrandsByLocation(location);
    return getBrandResponseList(brandEntityList);
  }

  public	List<BrandResponse> getBrandsByProvince(String province){
    List<BrandEntity> brandEntityList = provinceService.getBrandsByProvince(province);
    return getBrandResponseList(brandEntityList);
  }

  public	List<BrandResponse> getBrandsByAutonomousCommunity(String autonomousCommunity){
    
    List<ProvinceEntity> provinces = autonomousCommunityService.getProvincesByAutonomousCommunity(autonomousCommunity);

    Set<BrandEntity> brands = new HashSet<>();

    for (ProvinceEntity province : provinces) {
      brands.addAll(provinceService.getBrandsByProvince(province));
    }

    return getBrandResponseList(brands.stream().collect(Collectors.toList()));
  }

  private List<BrandResponse> getBrandResponseList(List<BrandEntity> brandEntityList){
    return brandEntityList
      .stream()
      .map(entity -> brandService.getBrandResponse(entity))
      .collect(Collectors.toList());
  }
}
