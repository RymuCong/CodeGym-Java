package com.cg.casestudy.service;

import com.cg.casestudy.entity.OrderedProduct;

import java.util.List;

public interface OrderedProductService {

    void insertOrderedProduct(OrderedProduct ordProduct);

    List<OrderedProduct> getAllOrderedProduct(int oid);
}
