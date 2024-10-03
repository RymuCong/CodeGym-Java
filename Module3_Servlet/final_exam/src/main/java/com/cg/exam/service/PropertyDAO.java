package com.cg.exam.service;

import com.cg.exam.common.BaseConnection;
import com.cg.exam.entity.Property;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {

    private final BaseConnection baseConnection;

    {
        try {
            baseConnection = new BaseConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String INSERT_PROPERTY_SQL = "INSERT INTO Property (propertyId, status, area, floor, type, price, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PROPERTY_BY_ID = "SELECT * FROM Property WHERE propertyId = ?";
    private static final String SELECT_ALL_PROPERTIES = "SELECT * FROM Property ORDER BY area ASC";
    private static final String DELETE_PROPERTY_SQL = "DELETE FROM Property WHERE propertyId = ?";


    public void addProperty(Property property) {
        try {
            Connection connection = baseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROPERTY_SQL);
            preparedStatement.setString(1, property.getPropertyId());
            preparedStatement.setString(2, property.getStatus());
            preparedStatement.setDouble(3, property.getArea());
            preparedStatement.setInt(4, property.getFloor());
            preparedStatement.setString(5, property.getType());
            preparedStatement.setDouble(6, property.getPrice());
            preparedStatement.setDate(7, new java.sql.Date(property.getStartDate().getTime()));
            preparedStatement.setDate(8, new java.sql.Date(property.getEndDate().getTime()));

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean isPropertyIdExists(String propertyId) {
        boolean exists = false;
        try {
            Connection connection = baseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROPERTY_BY_ID);
            preparedStatement.setString(1, propertyId);
            ResultSet rs = preparedStatement.executeQuery();
            exists = rs.next();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return exists;
    }

    public List<Property> getAllPropertiesSortedByArea() {
        List<Property> properties = new ArrayList<>();
        try {
            Connection connection = baseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PROPERTIES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String propertyId = rs.getString("propertyId");
                String status = rs.getString("status");
                double area = rs.getDouble("area");
                int floor = rs.getInt("floor");
                String type = rs.getString("type");
                double price = rs.getDouble("price");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                properties.add(new Property(propertyId, status, area, floor, type, price, startDate, endDate));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return properties;
    }

    public void deleteProperty(String propertyId) {
        try {
            Connection connection = baseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PROPERTY_SQL);
            preparedStatement.setString(1, propertyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Property> filterProperties(String type, String floorStr, String startDateStr, String endDateStr) {
        List<Property> properties = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Property WHERE 1=1");

        if (!"all".equals(type)) {
            query.append(" AND type = '").append(type).append("'");
        }
        if (!"all".equals(floorStr)) {
            query.append(" AND floor = ").append(floorStr);
        }
        if (startDateStr != null && !startDateStr.isEmpty()) {
            query.append(" AND startDate >= '").append(startDateStr).append("'");
        }
        if (endDateStr != null && !endDateStr.isEmpty()) {
            query.append(" AND endDate <= '").append(endDateStr).append("'");
        }

        try {
            Connection connection = baseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String propertyId = rs.getString("propertyId");
                String status = rs.getString("status");
                double area = rs.getDouble("area");
                int floor = rs.getInt("floor");
                String typeResult = rs.getString("type");
                double price = rs.getDouble("price");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                properties.add(new Property(propertyId, status, area, floor, typeResult, price, startDate, endDate));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return properties;
    }
}