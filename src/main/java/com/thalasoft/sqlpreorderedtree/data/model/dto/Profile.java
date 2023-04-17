package com.thalasoft.sqlpreorderedtree.data.model.dto;

import com.thalasoft.sqlpreorderedtree.data.model.constant.ProductTypeEnum;

import lombok.Data;

@Data
public class Profile {

  private Long id;

  private ProductTypeEnum productTypeEnum;

}
