package com.cg.test.service;

import com.cg.test.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Integer id);

    void save(Product product);

    void deleteById(Integer id);

    Page<Product> findAll(Pageable pageable);

    Page<Product> searchProducts(String productName, Integer categoryId, Long price, Pageable pageable);

}
