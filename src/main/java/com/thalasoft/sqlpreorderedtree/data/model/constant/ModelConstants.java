package com.thalasoft.sqlpreorderedtree.data.model.constant;

import java.util.Map;

public final class ModelConstants {

  private ModelConstants() { }

  public static final ProductFamilyEnum DEFAULT_PRODUCT_FAMILY_ENUM = ProductFamilyEnum.TRANSPORTATION;

  public static final Map<ProductFamilyEnum, Integer> MIX = Map.of(
    ProductFamilyEnum.TRANSPORTATION, 70,
    ProductFamilyEnum.TELECOM, 30
  );

}
