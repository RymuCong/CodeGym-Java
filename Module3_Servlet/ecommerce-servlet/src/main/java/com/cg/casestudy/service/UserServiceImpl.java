package com.cg.casestudy.service;

import com.cg.casestudy.entity.User;
import com.cg.casestudy.utils.ConnectionProvider;

import java.sql.*;
        import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static Connection con;

    static {
        con = ConnectionProvider.getConnection();
    }

    public UserServiceImpl() {
        super();
    }

    public UserServiceImpl(Connection con) {
        super();
        UserServiceImpl.con = con;
    }

    // Display all query code
    private static final String SAVE_USER = "insert into user(name, email, password, phone, gender, address) values(?, ?, ?, ?, ?, ?)";
    private static final String GET_USER_BY_EMAIL_PASSWORD = "select * from user where email = ? and password = ?";
    private static final String GET_USER_BY_ID = "select * from user where userid = ?";
    private static final String GET_ALL_USERS = "select * from user";
    private static final String DELETE_USER = "delete from user where userid = ?";
    private static final String UPDATE_USER = "update user set name = ?, email = ?, password = ?, phone = ?, gender = ?, address = ? where userid = ?";
    private static final String UPDATE_USER_PASSWORD_BY_EMAIL = "update user set password = ? where email = ?";
    private static final String UPDATE_USER_ADDRESS = "update user set address = ? where userid = ?";
    private static final String USER_COUNT = "select count(*) from user";
    private static final String GET_USER_ADDRESS = "select address from user where userid = ?";
    private static final String GET_USER_EMAIL = "select email from user where userid = ?";
    private static final String GET_USER_PHONE = "select phone from user where userid = ?";
    private static final String GET_ALL_USER_EMAILS = "select email from user";

    @Override
    public boolean saveUser(User user) {
        boolean flag = false;
        try (PreparedStatement statement = con.prepareStatement(SAVE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getAddress());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    // no SQL Injection vulnerability
    @Override
    public User getByEmailPassword(String userEmail, String userPassword) {
        User user = null;
        try (PreparedStatement statement = con.prepareStatement(GET_USER_BY_EMAIL_PASSWORD)) {
            statement.setString(1, userEmail);
            statement.setString(2, userPassword);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("userid"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setGender(rs.getString("gender"));
                    user.setAddress(rs.getString("address"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    // SQL Injection vulnerability password = ' OR 1 = 1 --
//    @Override
//    public User getByEmailPassword(String email, String password) {
//        User user = null;
//        String query = "SELECT * FROM user WHERE email = '" + email + "' AND password = '" + password + "'";
//
//        try (Statement statement = con.createStatement();
//             ResultSet rs = statement.executeQuery(query)) {
//            if (rs.next()) {
//                user = new User();
//                user.setName(rs.getString("name"));
//                user.setEmail(rs.getString("email"));
//                user.setPassword(rs.getString("password"));
//                user.setPhone(rs.getString("phone"));
//                user.setGender(rs.getString("gender"));
//                user.setAddress(rs.getString("address"));
//            }
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//        }
//        return user;
//    }

    @Override
    public User getById(int userId) {
        User user = null;
        try (PreparedStatement statement = con.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("userid"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setPhone(rs.getString("phone"));
                    user.setGender(rs.getString("gender"));
                    user.setAddress(rs.getString("address"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_USERS)) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("userid"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                user.setAddress(rs.getString("address"));
                list.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void deleteUser(int userId) {
        try (PreparedStatement statement = con.prepareStatement(DELETE_USER)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getAddress());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateUserPasswordByEmail(String userEmail, String userPassword) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_USER_PASSWORD_BY_EMAIL)) {
            statement.setString(1, userPassword);
            statement.setString(2, userEmail);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateUserAddress(User user) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_USER_ADDRESS)) {
            statement.setString(1, user.getAddress());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int userCount() {
        int count = 0;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(USER_COUNT)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    @Override
    public String getAddress(int userId) {
        String address = "";
        try (PreparedStatement statement = con.prepareStatement(GET_USER_ADDRESS)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    address = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return address;
    }

    @Override
    public String getEmail(int userId) {
        String email = "";
        try (PreparedStatement statement = con.prepareStatement(GET_USER_EMAIL)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return email;
    }

    @Override
    public String getPhone(int userId) {
        String phone = "";
        try (PreparedStatement statement = con.prepareStatement(GET_USER_PHONE)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    phone = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return phone;
    }

    @Override
    public String getName(int userId) {
        String name = "";
        try (PreparedStatement statement = con.prepareStatement(GET_USER_BY_ID)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return name;
    }


    @Override
    public List<String> getAllUserEmails() {
        List<String> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_USER_EMAILS)) {
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}