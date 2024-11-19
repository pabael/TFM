package com.tfm.tfm.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfm.tfm.entity.BrandEntity;
import com.tfm.tfm.response.BrandResponse;
import com.tfm.tfm.service.BrandFilterService;
import com.tfm.tfm.service.BrandService;
import com.tfm.tfm.service.CategorySubcategoryService;
import com.tfm.tfm.service.LabelService;

@Service
public class BrandFilterServiceImpl implements BrandFilterService{

  @Autowired private BrandService brandService;
  @Autowired private CategorySubcategoryService categorySubcategoryService;
  @Autowired private LabelService labelService;

  public List<BrandResponse> getBrandsByCategory(String category){
    List<BrandEntity> brandEntityList = categorySubcategoryService.getBrandsByCategory(category);
  
    return getBrandResponseList(brandEntityList);
  }

  public List<BrandResponse> getBrandsByLabel(String label) {
    List<BrandEntity> brandEntityList = labelService.getBrandsByLabel(label);
  
    return getBrandResponseList(brandEntityList);
  }

  private List<BrandResponse> getBrandResponseList(List<BrandEntity> brandEntityList){
    return brandEntityList
      .stream()
      .map(entity -> brandService.getBrandResponse(entity))
      .collect(Collectors.toList());
  }

}
