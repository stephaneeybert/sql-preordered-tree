package com.thalasoft.sqlpreorderedtree.data.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thalasoft.sqlpreorderedtree.data.model.domain.Product;
import com.thalasoft.sqlpreorderedtree.data.model.domain.Profile;

public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findByProfile(Profile profile);

  Page<Product> findByProfile(Profile profile, Pageable pageable);

}
