package com.cg.casestudy.service;

import com.cg.casestudy.entity.Order;
import com.cg.casestudy.utils.ConnectionProvider;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static Connection con;

    static {
        con = ConnectionProvider.getConnection();
    }

    public OrderServiceImpl() {
    }

    public OrderServiceImpl(Connection con) {
        OrderServiceImpl.con = con;
    }

    // Updated SQL queries with backticks around the table name `order`
    private static final String SAVE_ORDER = "insert into `order`(orderId, status, date, paymentType, userId) values(?, ?, ?, ?, ?)";
    private static final String GET_ORDER_BY_USER_ID = "select * from `order` where userId = ?";
    private static final String GET_ALL_ORDERS = "select * from `order`";
    private static final String UPDATE_ORDER_STATUS = "update `order` set status = ? where id = ?";
    private static final String GET_ORDER_BY_ID = "select * from `order` where orderId = ?";

    @Override
    public int insertOrder(Order order) {
        int id = 0;
        try {
            PreparedStatement statement = con.prepareStatement(SAVE_ORDER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, order.getOrderId());
            statement.setString(2, order.getStatus());
            statement.setTimestamp(3, Timestamp.from(Instant.now()));
            statement.setString(4, order.getPaymentType());
            statement.setInt(5, order.getUserId());

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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return id;
    }

    @Override
    public List<Order> getAllOrderByUserId(int uid) {
        List<Order> list = new ArrayList<Order>();
        try {
            PreparedStatement statement = con.prepareStatement(GET_ORDER_BY_USER_ID);
            statement.setInt(1, uid);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setStatus(rs.getString("status"));
                order.setDate(rs.getTimestamp("date"));
                order.setPaymentType(rs.getString("paymentType"));
                order.setUserId(uid);

                list.add(order);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Order getOrderById(int id) {
        Order order = new Order();
        try {
            PreparedStatement statement = con.prepareStatement(GET_ORDER_BY_ID);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setStatus(rs.getString("status"));
                order.setDate(rs.getTimestamp("date"));
                order.setPaymentType(rs.getString("paymentType"));
                order.setUserId(rs.getInt("userId"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<Order>();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL_ORDERS);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrderId(rs.getString("orderId"));
                order.setStatus(rs.getString("status"));
                order.setDate(rs.getTimestamp("date"));
                order.setPaymentType(rs.getString("paymentType"));
                order.setUserId(rs.getInt("userId"));

                list.add(order);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void updateOrderStatus(int oid, String status) {
        try {
            PreparedStatement statement = con.prepareStatement(UPDATE_ORDER_STATUS);
            statement.setString(1, status);
            statement.setInt(2, oid);

            System.out.println(statement);

            statement.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}