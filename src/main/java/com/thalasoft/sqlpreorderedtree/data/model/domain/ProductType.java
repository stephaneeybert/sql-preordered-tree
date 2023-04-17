package com.thalasoft.sqlpreorderedtree.data.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.thalasoft.sqlpreorderedtree.data.model.constant.ProductTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "product_type")
public class ProductType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(unique = true)
  @Enumerated(EnumType.STRING)
  private ProductTypeEnum productTypeEnum;

  @JoinColumn(name = "parent_id", referencedColumnName = "id")
  @ManyToOne(optional = true)
  private ProductType parentProductType;

  @Column(nullable = true)
  private Integer treeLeft;

  @Column(nullable = true)
  private Integer treeRight;

  ProductType(ProductTypeEnum productTypeEnum) {
    this.productTypeEnum = productTypeEnum;
  }

  ProductType(ProductTypeEnum productTypeEnum, ProductType parentProducType) {
    this.productTypeEnum = productTypeEnum;
    this.parentProductType = parentProducType;
  }

}
