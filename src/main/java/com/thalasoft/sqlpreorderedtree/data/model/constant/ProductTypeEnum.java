package com.thalasoft.sqlpreorderedtree.data.model.constant;

public enum ProductTypeEnum {

    CAR(ProductFamilyEnum.TRANSPORTATION),
    SMARTPHONE(ProductFamilyEnum.TELECOM);

    ProductFamilyEnum productFamilyEnum;

    private ProductTypeEnum(ProductFamilyEnum productFamilyEnum) {
      this.productFamilyEnum = productFamilyEnum;
    }

}
