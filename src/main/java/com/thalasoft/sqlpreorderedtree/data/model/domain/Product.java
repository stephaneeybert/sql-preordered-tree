package com.thalasoft.sqlpreorderedtree.data.model.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String supplier;

  @NotNull
  private String manufacturer;

  @NotNull
  private String modelNumber;

  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Set<ProductPart> productParts;

  @NotNull
  @JoinColumn(name = "product_type_id", referencedColumnName = "id")
  @ManyToOne(optional = true)
  private ProductType productType;

}
