package com.cg.casestudy.service;

import com.cg.casestudy.entity.Cart;
import com.cg.casestudy.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartServiceImpl implements CartService {

    private static Connection con;

    static {
        con = ConnectionProvider.getConnection();
    }

    public CartServiceImpl() {
    }

    public CartServiceImpl(Connection con) {
        CartServiceImpl.con = con;
    }

    // Display all necessary SQL queries
    private static final String ADD_PRODUCT_TO_CART = "insert into cart(uid, pid, quantity) values(?,?,?)";
    private static final String GET_CART_LIST_BY_USER_ID = "select * from cart where uid = ?";
    private static final String GET_QUANTITY = "select * from cart where uid = ? and pid = ?";
    private static final String GET_QUANTITY_BY_ID = "select * from cart where id = ?";
    private static final String UPDATE_QUANTITY = "update cart set quantity = ? where id = ?";
    private static final String REMOVE_PRODUCT = "delete from cart where id = ?";
    private static final String REMOVE_ALL_PRODUCT = "delete from cart where uid = ?";
    private static final String GET_ID_BY_USER_ID_AND_PRODUCT_ID = "select * from cart where uid = ? and pid = ?";
    private static final String GET_CART_COUNT_BY_USER_ID = "select count(*) from cart where uid=?";
    private static final String GET_PRODUCT_ID = "select pid from cart where id=?";

    @Override
    public boolean addProductToCart(Cart cart) {
        boolean flag = false;
        try {
            PreparedStatement statement = con.prepareStatement(ADD_PRODUCT_TO_CART);
            statement.setInt(1, cart.getUserId());
            statement.setInt(2, cart.getProductId());
            statement.setInt(3, cart.getQuantity());

            statement.executeUpdate();
            flag = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public List<Cart> getCartListByUserId(int uid) {
        List<Cart> list = new ArrayList<Cart>();
        try {
            PreparedStatement statement = con.prepareStatement(GET_CART_LIST_BY_USER_ID);
            statement.setInt(1, uid);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Cart cart = new Cart();
                cart.setCartId(rs.getInt("id"));
                cart.setUserId(rs.getInt("uid"));
                cart.setProductId(rs.getInt("pid"));
                cart.setQuantity(rs.getInt("quantity"));

                list.add(cart);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public int getQuantity(int uid, int pid) {
        int qty = 0;
        try {
            PreparedStatement statement = con.prepareStatement(GET_QUANTITY);
            statement.setInt(1, uid);
            statement.setInt(2, pid);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                qty = rs.getInt("quantity");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return qty;
    }

    @Override
    public int getQuantityById(int id) {
        int qty = 0;
        try {
            PreparedStatement statement = con.prepareStatement(GET_QUANTITY_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                qty = rs.getInt("quantity");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return qty;
    }

    @Override
    public void updateQuantity(int id, int qty) {
        try {
            PreparedStatement statement = con.prepareStatement(UPDATE_QUANTITY);
            statement.setInt(1, qty);
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeProduct(int cid) {
        try {
            PreparedStatement statement = con.prepareStatement(REMOVE_PRODUCT);
            statement.setInt(1, cid);

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeAllProduct(int uid) {
        try {
            PreparedStatement statement = con.prepareStatement(REMOVE_ALL_PRODUCT);
            statement.setInt(1, uid);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getIdByUserIdAndProductId(int uid, int pid) {
        int cid = 0;
        try {
            PreparedStatement statement = con.prepareStatement(GET_ID_BY_USER_ID_AND_PRODUCT_ID);
            statement.setInt(1, uid);
            statement.setInt(2, pid);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cid = rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cid;
    }

    @Override
    public int getCartCountByUserId(int uid) {
        int count = 0;
        try {
            PreparedStatement statement = con.prepareStatement(GET_CART_COUNT_BY_USER_ID);
            statement.setInt(1, uid);

            ResultSet rs = statement.executeQuery();
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

    @Override
    public int getProductId(int cid) {
        int pid = 0;
        try {
            PreparedStatement statement = con.prepareStatement(GET_PRODUCT_ID);
            statement.setInt(1, cid);
            ResultSet rs = statement.executeQuery();
            rs.next();
            pid = rs.getInt(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return pid;
    }
}
