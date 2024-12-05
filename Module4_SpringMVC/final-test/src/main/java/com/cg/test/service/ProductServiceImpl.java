package com.cg.test.service;

import com.cg.test.repository.ProductRepository;
import com.cg.test.entity.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            productRepository.deleteById(id);
        }
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> searchProducts(String productName, Integer categoryId, Long price, Pageable pageable) {
        if (productName == null && categoryId == -1 && price == -1) {
            return productRepository.findAll(pageable);
        }
        else if (productName != null && categoryId == -1 && price == -1) {
            return productRepository.findByProductNameContaining(productName, pageable);
        }
        else if (productName == null && categoryId != -1 && price == -1) {
            return productRepository.findByCategoryId(categoryId, pageable);
        }
        else if (productName == null && categoryId == -1) {
            return productRepository.findByPriceGreaterThanEqual(price, pageable);
        }
        else if (productName != null && categoryId != -1 && price == -1) {
            return productRepository.findByProductNameContainingAndCategoryId(
                    productName, categoryId, pageable);
        }
        else if (productName != null && categoryId == -1 && price != -1) {
            return productRepository.findByProductNameContainingAndPriceGreaterThanEqual(
                    productName, price, pageable);
        }
        else if (productName == null && price != -1) {
            return productRepository.findByCategoryIdAndPriceGreaterThanEqual(
                    categoryId, price, pageable);
        }
        return productRepository.findByProductNameContainingAndCategoryIdAndPriceGreaterThanEqual(
                productName, categoryId, price, pageable);
    }
}
