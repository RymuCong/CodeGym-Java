package com.cg.casestudy.service;

import com.cg.casestudy.DatabaseConnection;
import com.cg.casestudy.entity.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private static Connection con;

    static {
        try {
            DatabaseConnection dataBaseConnection = new DatabaseConnection();
            con = dataBaseConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public CategoryServiceImpl() {
    }

    public CategoryServiceImpl(Connection con) {
        CategoryServiceImpl.con = con;
    }

    // display all the necessary queries
    private static final String INSERT_CATEGORY = "insert into category(name, image) values(?, ?)";
    private static final String SELECT_ALL_CATEGORIES = "select * from category";
    private static final String SELECT_CATEGORY_BY_ID = "select * from category where cid = ?";
    private static final String DELETE_CATEGORY = "delete from category where cid = ?";
    private static final String UPDATE_CATEGORY = "update category set name = ?, image = ? where cid = ?";
    private static final String SELECT_CATEGORY_NAME_BY_ID = "select name from category where cid = ?";
    private static final String SELECT_CATEGORY_BY_NAME = "select * from category where name = ?";
    private static final String SELECT_CATEGORY_COUNT = "select count(*) from category";

    @Override
    public boolean saveCategory(Category category) {
        boolean flag = false;
        try (PreparedStatement statement = con.prepareStatement(INSERT_CATEGORY)) {
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getCategoryImage());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(SELECT_ALL_CATEGORIES)) {
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("cid"));
                category.setCategoryName(rs.getString("name"));
                category.setCategoryImage(rs.getString("image"));
                list.add(category);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Category getCategoryById(int cid) {
        Category category = new Category();
        try (PreparedStatement statement = con.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            statement.setInt(1, cid);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    category.setCategoryId(rs.getInt("cid"));
                    category.setCategoryName(rs.getString("name"));
                    category.setCategoryImage(rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return category;
    }

    @Override
    public void deleteCategory(int cid) {
        try (PreparedStatement statement = con.prepareStatement(DELETE_CATEGORY)) {
            statement.setInt(1, cid);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateCategory(Category category) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_CATEGORY)) {
            statement.setString(1, category.getCategoryName());
            statement.setString(2, category.getCategoryImage());
            statement.setInt(3, category.getCategoryId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getCategoryNameById(int cid) {
        String categoryName = "";
        try (PreparedStatement statement = con.prepareStatement(SELECT_CATEGORY_NAME_BY_ID)) {
            statement.setInt(1, cid);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    categoryName = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return categoryName;
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = new Category();
        try (PreparedStatement statement = con.prepareStatement(SELECT_CATEGORY_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    category.setCategoryId(rs.getInt("cid"));
                    category.setCategoryName(rs.getString("name"));
                    category.setCategoryImage(rs.getString("image"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return category;
    }

    @Override
    public int getCategoryCount() {
        int count = 0;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_CATEGORY_COUNT)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }
}