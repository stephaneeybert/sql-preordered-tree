package com.thalasoft.sqlpreorderedtree.data.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class ProfileProduct {

  private Long id;

  @JsonIgnore
  private Profile profile;

  @JsonIgnore
  private Product product;

}
