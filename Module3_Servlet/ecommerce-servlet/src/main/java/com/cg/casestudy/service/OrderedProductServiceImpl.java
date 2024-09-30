package com.cg.casestudy.service;

import com.cg.casestudy.entity.OrderedProduct;
import com.cg.casestudy.utils.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderedProductServiceImpl implements OrderedProductService{

    private static Connection con;

    static {
        con = ConnectionProvider.getConnection();
    }

    public OrderedProductServiceImpl() {
    }

    public OrderedProductServiceImpl(Connection con) {
        OrderedProductServiceImpl.con = con;
    }

    // Display all necessary SQL queries
    private static final String INSERT_ORDERED_PRODUCT = "insert into order_detail(name, quantity, price, image, orderId) values(?, ?, ?, ?, ?)";
    private static final String GET_ALL_ORDERED_PRODUCT = "select * from order_detail where orderId = ?";

    @Override
    public void insertOrderedProduct(OrderedProduct ordProduct) {
        System.out.println(ordProduct);
        try {
            PreparedStatement psmt = con.prepareStatement(INSERT_ORDERED_PRODUCT);
            psmt.setString(1, ordProduct.getName());
            psmt.setInt(2, ordProduct.getQuantity());
            psmt.setFloat(3,ordProduct.getPrice());
            psmt.setString(4, ordProduct.getImage());
            psmt.setInt(5, ordProduct.getOrderId());

            psmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OrderedProduct> getAllOrderedProduct(int oid) {
        List<OrderedProduct> list = new ArrayList<OrderedProduct>();
        try {
            PreparedStatement psmt = con.prepareStatement(GET_ALL_ORDERED_PRODUCT);
            psmt.setInt(1, oid);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                OrderedProduct orderProd = new OrderedProduct();
                orderProd.setName(rs.getString("name"));
                orderProd.setQuantity(rs.getInt("quantity"));
                orderProd.setPrice(rs.getInt("price"));
                orderProd.setImage(rs.getString("image"));
                orderProd.setOrderId(oid);

                list.add(orderProd);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}
