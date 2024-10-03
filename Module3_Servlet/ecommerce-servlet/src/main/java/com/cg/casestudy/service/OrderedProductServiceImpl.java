package com.cg.casestudy.service;

import com.cg.casestudy.DatabaseConnection;
import com.cg.casestudy.entity.OrderedProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderedProductServiceImpl implements OrderedProductService {
    private static Connection con;

    static {
        try {
            DatabaseConnection dataBaseConnection = new DatabaseConnection();
            con = dataBaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderedProductServiceImpl() {
    }

    public OrderedProductServiceImpl(Connection con) {
        OrderedProductServiceImpl.con = con;
    }

    // display all the necessary queries
    private static final String INSERT_ORDERED_PRODUCT = "insert into ordered_product(name, quantity, price, image, orderid) values(?, ?, ?, ?, ?)";
    private static final String GET_ALL_ORDERED_PRODUCT = "select * from ordered_product where orderid = ?";

    @Override
    public void insertOrderedProduct(OrderedProduct product) {
        try (PreparedStatement statement = con.prepareStatement(INSERT_ORDERED_PRODUCT)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setFloat(3, product.getPrice());
            statement.setString(4, product.getImage());
            statement.setInt(5, product.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<OrderedProduct> getAllOrderedProduct(int orderedId) {
        List<OrderedProduct> list = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(GET_ALL_ORDERED_PRODUCT)) {
            statement.setInt(1, orderedId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    OrderedProduct orderProd = new OrderedProduct();
                    orderProd.setName(rs.getString("name"));
                    orderProd.setQuantity(rs.getInt("quantity"));
                    orderProd.setPrice(rs.getFloat("price"));
                    orderProd.setImage(rs.getString("image"));
                    orderProd.setOrderId(orderedId);
                    list.add(orderProd);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}