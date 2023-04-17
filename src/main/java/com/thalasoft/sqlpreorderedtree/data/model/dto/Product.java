package com.thalasoft.sqlpreorderedtree.data.model.dto;

import java.util.Set;

import lombok.Data;

@Data
public class Product {

  private Long id;

  private String name;

  private String supplier;

  private String manufacturer;

  private String modelNumber;

  private Set<ProductPart> productParts;

  private ProductType productType;

}
