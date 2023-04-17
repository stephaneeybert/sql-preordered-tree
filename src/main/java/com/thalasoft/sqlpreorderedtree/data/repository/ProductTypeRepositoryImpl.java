package com.thalasoft.sqlpreorderedtree.data.repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.thalasoft.sqlpreorderedtree.data.model.domain.ProductType;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductTypeRepositoryImpl implements ProductTypeRepositoryInt {

  private EntityManager entityManager;

  @Override
  @Transactional
  @Modifying(flushAutomatically = true, clearAutomatically = true)
  public ProductType addProductType(ProductType productType, ProductType destProductType, boolean asSibblingOfDest) {
    Integer border = 0;
    if (null != destProductType) {
      if (asSibblingOfDest) {
        // Add after the right of the sibbling
        border = destProductType.getTreeRight();
      } else {
        // Add after the left of the parent
        border = destProductType.getTreeLeft();
      }

      // Make room at destination for one tree node
      String makeLeft = "UPDATE productType SET treeLeft = treeLeft + 2 WHERE treeLeft > :border";
      TypedQuery<ProductType> queryMakeLeft = entityManager.createQuery(makeLeft, ProductType.class);
      queryMakeLeft.setParameter("border", border);
      queryMakeLeft.executeUpdate();
      String makeRight = "UPDATE productType SET treeRight = treeRight + 2 WHERE treeRight > :border";
      TypedQuery<ProductType> queryMakeRight = entityManager.createQuery(makeRight, ProductType.class);
      queryMakeRight.setParameter("border", border);
      queryMakeRight.executeUpdate();

      productType.setParentProductType(destProductType);
    }

    productType.setTreeLeft(border + 1);
    productType.setTreeRight(border + 2);
    entityManager.persist(productType);

    return productType;
  }

  @Override
  @Transactional
  @Modifying(flushAutomatically = true, clearAutomatically = true)
  public ProductType deleteProductType(ProductType productType) {
    // Delete the tree node
    String delete = "DELETE FROM ProductType WHERE treeLeft BETWEEN :treeLeft AND :treeRight";
    TypedQuery<ProductType> queryDelete = entityManager.createQuery(delete, ProductType.class);
    queryDelete.setParameter("treeLeft", productType.getTreeLeft());
    queryDelete.setParameter("treeRight", productType.getTreeRight());
    queryDelete.executeUpdate();

    // Clear the room of the deleted tree node
    Integer treeWidth = productType.getTreeRight() - productType.getTreeLeft();
    String clearLeft = "UPDATE ProductType SET treeLeft = treeLeft - :treeWidth + 1 WHERE treeLeft > :treeRight";
    TypedQuery<ProductType> queryClearLeft = entityManager.createQuery(clearLeft, ProductType.class);
    queryClearLeft.setParameter("treeWidth", treeWidth);
    queryClearLeft.setParameter("treeRight", productType.getTreeRight());
    queryClearLeft.executeUpdate();
    String clearRight = "UPDATE ProductType SET treeRight = treeRight - :treeWidth + 1 WHERE treeRight > :treeRight";
    TypedQuery<ProductType> queryClearRight = entityManager.createQuery(clearRight, ProductType.class);
    queryClearRight.setParameter("treeWidth", treeWidth);
    queryClearRight.setParameter("treeRight", productType.getTreeRight());
    queryClearRight.executeUpdate();

    return productType;
  }

  @Override
  @Transactional
  @Modifying(flushAutomatically = true, clearAutomatically = true)
  public ProductType moveProductType(ProductType productType, ProductType destProductType, boolean asSibblingOfDest) {
    Integer border;
    if (asSibblingOfDest) {
      // Add after the right of the sibbling
      border = destProductType.getTreeRight();
    } else {
      // Add after the left of the parent
      border = destProductType.getTreeLeft();
    }

    // Make room at destination location
    Integer treeWidth = productType.getTreeRight() - productType.getTreeLeft();
    String makeLeft = "UPDATE ProductType SET treeLeft = treeLeft + :treeWidth + 1 WHERE treeLeft > :border";
    TypedQuery<ProductType> queryMakeLeft = entityManager.createQuery(makeLeft, ProductType.class);
    queryMakeLeft.setParameter("treeWidth", treeWidth);
    queryMakeLeft.setParameter("border", border);
    queryMakeLeft.executeUpdate();
    String makeRight = "UPDATE ProductType SET treeRight = treeRight + :treeWidth + 1 WHERE treeRight > :border";
    TypedQuery<ProductType> queryMakeRight = entityManager.createQuery(makeRight, ProductType.class);
    queryMakeRight.setParameter("treeWidth", treeWidth);
    queryMakeRight.setParameter("border", border);
    queryMakeRight.executeUpdate();

    // Update the sub tree
    String updateLeft = "UPDATE ProductType SET treeLeft = treeLeft + (:border - :treeLeft) + 1, treeRight = treeRight + (:border - :treeRight) + 1 WHERE treeLeft >= :treeLeft AND treeRight <= :treeRight";
    TypedQuery<ProductType> queryUpdateLeft = entityManager.createQuery(updateLeft, ProductType.class);
    queryUpdateLeft.setParameter("border", border);
    queryUpdateLeft.setParameter("treeLeft", productType.getTreeLeft());
    queryUpdateLeft.setParameter("treeRight", productType.getTreeRight());
    queryUpdateLeft.executeUpdate();

    // Clear the room at the source location
    String clearLeft = "UPDATE ProductType SET treeLeft = treeLeft - :treeWidth - 1 WHERE treeLeft > :treeRight";
    TypedQuery<ProductType> queryClearLeft = entityManager.createQuery(clearLeft, ProductType.class);
    queryClearLeft.setParameter("treeWidth", treeWidth);
    queryClearLeft.setParameter("treeRight", productType.getTreeRight());
    queryClearLeft.executeUpdate();
    String clearRight = "UPDATE ProductType SET treeRight = treeRight - :treeWidth - 1 WHERE treeRight > :treeRight";
    TypedQuery<ProductType> queryClearRight = entityManager.createQuery(clearRight, ProductType.class);
    queryClearRight.setParameter("treeWidth", treeWidth);
    queryClearRight.setParameter("treeRight", productType.getTreeRight());
    queryClearRight.executeUpdate();

    return productType;
  }

}
