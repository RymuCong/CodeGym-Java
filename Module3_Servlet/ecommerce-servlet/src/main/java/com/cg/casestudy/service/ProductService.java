package com.cg.casestudy.service;

import com.cg.casestudy.entity.Product;

import java.util.List;

public interface ProductService {

    boolean saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(int id);

    boolean deleteProduct(int id);

    boolean updateProduct(Product product);

    List<Product> getProductsByCategory(int categoryId);

    List<Product> searchProducts(String keyword);

    List<Product> getDiscountedProducts();

    void updateProductQuantity(int id, int quantity);

    int getTotalProduct();

    int getProductPriceById(int id);

    int getProductQuantityById(int id);

    List<Product> getAllLatestProducts();
}
