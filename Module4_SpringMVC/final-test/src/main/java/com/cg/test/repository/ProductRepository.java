package com.cg.test.repository;

import com.cg.test.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>, JpaSpecificationExecutor<Product> {

    List <Product> findByCategoryId(Integer theId);

    Page<Product> findByProductNameContainingAndCategoryIdAndPriceGreaterThanEqual(
            String productName, Integer categoryId, Long price, Pageable pageable);

    Page<Product> findByProductNameContainingAndCategoryId(String productName, Integer categoryId, Pageable pageable);

    Page<Product> findByProductNameContainingAndPriceGreaterThanEqual(String productName, Long price, Pageable pageable);

    Page<Product> findByCategoryIdAndPriceGreaterThanEqual(Integer categoryId, Long price, Pageable pageable);

    Page<Product> findByProductNameContaining(String productName, Pageable pageable);

    Page<Product> findByCategoryId(Integer categoryId, Pageable pageable);

    Page<Product> findByPriceGreaterThanEqual(Long price, Pageable pageable);
}
