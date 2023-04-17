package com.thalasoft.sqlpreorderedtree.data.model.dto;

import com.thalasoft.sqlpreorderedtree.data.model.constant.ProductTypeEnum;

import lombok.Data;

@Data
public class ProductType {

  private Long id;

  private ProductTypeEnum productTypeEnum;

  private ProductType parentProductType;

  private Integer treeLeft;

  private Integer treeRight;

}
