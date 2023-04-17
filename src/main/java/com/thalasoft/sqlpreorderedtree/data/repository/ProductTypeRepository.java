package com.thalasoft.sqlpreorderedtree.data.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.sqlpreorderedtree.data.model.constant.ProductTypeEnum;
import com.thalasoft.sqlpreorderedtree.data.model.dto.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long>, ProductTypeRepositoryInt {

  List<ProductType> findAll();

  @Query("SELECT pt FROM ProductType pt WHERE pt.productTypeEnum = :productTypeEnum")
  Optional<ProductType> findByProductTypeEnum(@Param("productTypeEnum") ProductTypeEnum productTypeEnum);

  Optional<ProductType> findByTreeLeftAndTreeRight(@Param("treeLeft") Integer treeLeft, @Param("treeRight") Integer treeRight);

  @Query("SELECT ptchild FROM ProductType pt, ProductType ptchild WHERE pt.productTypeEnum = :productTypeEnum AND ptchild.parentProductType = pt AND ptchild.treeLeft BETWEEN pt.treeLeft AND pt.treeRight ORDER BY ptchild.treeLeft ASC")
  List<ProductType> findProductTypeChildren(@Param("productTypeEnum") ProductTypeEnum productTypeEnum);

  @Query("SELECT ptchild FROM ProductType pt, ProductType ptchild WHERE pt.productTypeEnum = :productTypeEnum AND ptchild.treeLeft BETWEEN pt.treeLeft AND pt.treeRight ORDER BY ptchild.treeLeft ASC")
  List<ProductType> findProductTypeDescendants(@Param("productTypeEnum") ProductTypeEnum productTypeEnum);

  @Query("SELECT pt FROM ProductType pt, ProductType ptchild WHERE ptchild.productTypeEnum = :productTypeEnum AND ptchild.treeLeft BETWEEN pt.treeLeft AND pt.treeRight ORDER BY pt.treeLeft ASC")
  List<ProductType> findProductTypeAncestors(@Param("productTypeEnum") ProductTypeEnum productTypeEnum);

  @Query("SELECT ptchild, (COUNT(pt) - 1) as treeDepth FROM ProductType pt, ProductType ptchild WHERE ptchild.treeLeft BETWEEN pt.treeLeft AND pt.treeRight GROUP BY ptchild.id ORDER BY ptchild.treeLeft ASC")
  List<Map<ProductType, Integer>> findProductTypesDepths();

  @Query("SELECT (COUNT(pt) > 0) as hasChild FROM ProductType pt WHERE pt.parentProductType.productTypeEnum = :productTypeEnum")
  Boolean hasChild(@Param("productTypeEnum") ProductTypeEnum productTypeEnum);

}
