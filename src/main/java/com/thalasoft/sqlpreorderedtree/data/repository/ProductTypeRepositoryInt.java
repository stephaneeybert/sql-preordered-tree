package com.thalasoft.sqlpreorderedtree.data.repository;

import com.thalasoft.sqlpreorderedtree.data.model.domain.ProductType;

public interface ProductTypeRepositoryInt {

  public ProductType addProductType(ProductType equipmentType, ProductType destProductType, boolean addAsSibbling);

  public ProductType deleteProductType(ProductType equipmentType);

  public ProductType moveProductType(ProductType equipmentType, ProductType destProductType, boolean asSibblingOfDest);

}
