package com.cg.casestudy.service;

import com.cg.casestudy.DatabaseConnection;
import com.cg.casestudy.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static Connection con;

    static {
        try {
            DatabaseConnection dataBaseConnection = new DatabaseConnection();
            con = dataBaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public OrderServiceImpl() {
        super();
    }

    public OrderServiceImpl(Connection con) {
        super();
        OrderServiceImpl.con = con;
    }

    // display all necessary SQL queries
    private static final String INSERT_ORDER = "insert into `order`(orderid, status, paymentType, userId) values(?, ?, ?, ?)";
    private static final String GET_ALL_ORDER_BY_USER_ID = "select * from `order` where userId = ?";
    private static final String GET_ORDER_BY_ID = "select * from `order` where id = ?";
    private static final String GET_ALL_ORDER = "select * from `order`";
    private static final String UPDATE_ORDER_STATUS = "update `order` set status = ? where id = ?";

    @Override
    public int insertOrder(Order order) {
        int id = 0;
        try (PreparedStatement statement = con.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, order.getOrderId());
            statement.setString(2, order.getStatus());
            statement.setString(3, order.getPayementType());
            statement.setInt(4, order.getUserId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Insertion failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Insertion failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    @Override
    public List<Order> getAllOrderByUserId(int uid) {
        List<Order> list = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(GET_ALL_ORDER_BY_USER_ID)) {
            statement.setInt(1, uid);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setOrderId(rs.getString("orderid"));
                    order.setStatus(rs.getString("status"));
                    order.setDate(rs.getTimestamp("date"));
                    order.setPayementType(rs.getString("paymentType"));
                    order.setId(uid);
                    list.add(order);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Order getOrderById(int id) {
        Order order = new Order();
        try (PreparedStatement statement = con.prepareStatement(GET_ORDER_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    order.setId(rs.getInt("id"));
                    order.setOrderId(rs.getString("orderid"));
                    order.setStatus(rs.getString("status"));
                    order.setDate(rs.getTimestamp("date"));
                    order.setPayementType(rs.getString("paymentType"));
                    order.setId(rs.getInt("userId"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_ORDER)) {
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderid"));
                order.setStatus(rs.getString("status"));
                order.setDate(rs.getTimestamp("date"));
                order.setPayementType(rs.getString("paymentType"));
                order.setId(rs.getInt("userId"));
                list.add(order);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void updateOrderStatus(int oid, String status) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_ORDER_STATUS)) {
            statement.setString(1, status);
            statement.setInt(2, oid);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}