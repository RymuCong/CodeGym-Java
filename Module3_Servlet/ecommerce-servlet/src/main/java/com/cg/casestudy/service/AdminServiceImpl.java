package com.cg.casestudy.service;

import com.cg.casestudy.entity.Admin;
import com.cg.casestudy.utils.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private static Connection con;

    static {
        con = ConnectionProvider.getConnection();
    }

    public AdminServiceImpl() {
    }

    public AdminServiceImpl(Connection con) {
        AdminServiceImpl.con = con;
    }

    // Display all necessary SQL queries
    private static final String SAVE_ADMIN = "insert into admin(name, email, password, phone) values(?, ?, ?, ?)";
    private static final String GET_ADMIN_BY_EMAIL_PASSWORD = "select * from admin where email = ? and password = ?";
    private static final String GET_ALL_ADMINS = "select * from admin";
    private static final String DELETE_ADMIN = "delete from admin where id = ?";

    @Override
    public boolean saveAdmin(Admin admin) {
        boolean flag = false;

        try (PreparedStatement statement = con.prepareStatement(SAVE_ADMIN)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getPhone());

            statement.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Admin getAdminByEmailAndPassword(String email, String password) {
        Admin admin = null;

        try (PreparedStatement statement = con.prepareStatement(GET_ADMIN_BY_EMAIL_PASSWORD)) {
            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    admin = new Admin();
                    admin.setId(set.getInt("id"));
                    admin.setName(set.getString("name"));
                    admin.setEmail(set.getString("email"));
                    admin.setPassword(set.getString("password"));
                    admin.setPhone(set.getString("phone"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return admin;
    }

    @Override
    public List<Admin> getAllAdmins() {
        List<Admin> list = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_ADMINS)) {

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setEmail(rs.getString("email"));
                admin.setPhone(rs.getString("phone"));
                admin.setPassword(rs.getString("password"));

                list.add(admin);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public boolean deleteAdmin(int id) {
        boolean flag = false;

        try (PreparedStatement statement = con.prepareStatement(DELETE_ADMIN)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }
}
