package com.thalasoft.sqlpreorderedtree.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thalasoft.sqlpreorderedtree.data.model.constant.ProductTypeEnum;
import com.thalasoft.sqlpreorderedtree.data.model.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

  List<Profile> findByProductTypeEnum(ProductTypeEnum productTypeEnum);

  Page<Profile> findByProductTypeEnum(ProductTypeEnum productTypeEnum, Pageable pageable);

}
