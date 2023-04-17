package com.thalasoft.sqlpreorderedtree.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thalasoft.sqlpreorderedtree.data.model.domain.ProductType;
import com.thalasoft.sqlpreorderedtree.data.model.domain.Profile;
import com.thalasoft.sqlpreorderedtree.data.model.domain.ProfileProduct;

public interface ProfileProductRepository extends JpaRepository<ProfileProduct, Long> {

  @Query("SELECT pp FROM ProfileProduct pp WHERE pp.profile = :profile AND pp.product.productType = :productType ORDER BY pp.product.productType.productTypeEnum, pp.product.name")
  List<ProfileProduct> findByProfileAndProducType(@Param("cabinLayout") Profile profile, @Param("productType") ProductType productType);

  @Query("SELECT pp FROM ProfileProduct pp WHERE pp.profile = :profile AND pp.profile.productType IN (SELECT ptchild from ProducType pt, ProducType ptchild WHERE pt = :producType AND ptchild.treeLeft >= pt.treeLeft AND ptchild.treeRight <= pt.treeRight) ORDER BY pp.product.productType.treeLeft ASC, pp.product.name")
  List<ProfileProduct> findByProfileInProductTypesTree(@Param("profile") Profile profile, @Param("producType") ProductType producType);

  @Query("SELECT pp FROM ProfileProduct pp WHERE pp.profile = :profile AND pp.product.productType = :productType ORDER BY pp.product.productType.productTypeEnum, pp.product.name")
  List<ProfileProduct> findByProfileAndProductType(@Param("profile") Profile profile, @Param("productType") ProductType productType);

  List<ProfileProduct> findByProfile(Profile profile);

}
