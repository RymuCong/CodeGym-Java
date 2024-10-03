package com.cg.casestudy.service;

import com.cg.casestudy.entity.Cart;

import java.util.List;

public interface CartService {

    boolean addProductToCart(Cart cart);

    List<Cart> getCartListByUserId(int uid);

    int getQuantity(int uid, int pid);

    int getQuantityById(int id);

    void updateQuantity(int id, int qty);

    void removeProduct(int cid);

    void removeAllProduct(int uid);

    int getIdByUserIdAndProductId(int uid, int pid);

    int getCartCountByUserId(int uid);

    int getProductId(int cid);
}
