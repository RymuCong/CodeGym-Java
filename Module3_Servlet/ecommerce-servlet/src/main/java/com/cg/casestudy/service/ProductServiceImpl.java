package com.cg.casestudy.service;

import com.cg.casestudy.entity.Product;
import com.cg.casestudy.utils.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private static Connection con;

    static {
        con = ConnectionProvider.getConnection();
    }

    public ProductServiceImpl() {
        super();
    }

    public ProductServiceImpl(Connection con) {
        super();
        ProductServiceImpl.con = con;
    }

    private static final String SAVE_PRODUCT = "insert into product(name, price, quantity, description, image, cid, discount) values(?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_PRODUCTS = "select * from product";
    private static final String GET_PRODUCT_BY_ID = "select * from product where pid = ?";
    private static final String DELETE_PRODUCT = "delete from product where pid = ?";
    private static final String UPDATE_PRODUCT = "update product set name = ?, price = ?, quantity = ?, description = ?, image = ?, cid = ? where pid = ?";
    private static final String GET_PRODUCTS_BY_CATEGORY = "select * from product where cid = ?";
    private static final String SEARCH_PRODUCTS = "select * from product where lower(name) like ? or lower(description) like ?";
    private static final String GET_DISCOUNTED_PRODUCTS = "select * from product where discount >= 20 order by discount desc";
    private static final String UPDATE_PRODUCT_QUANTITY = "update product set quantity = ? where pid = ?";
    private static final String GET_TOTAL_PRODUCT = "select count(*) from product";
    private static final String GET_PRODUCT_PRICE_BY_ID = "select price, discount from product where pid = ?";
    private static final String GET_PRODUCT_QUANTITY_BY_ID = "select quantity from product where pid = ?";
    private static final String GET_LATEST_PRODUCTS = "select * from product order by pid desc";

    @Override
    public boolean saveProduct(Product product) {
        boolean flag = false;
        try (PreparedStatement statement = con.prepareStatement(SAVE_PRODUCT)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getProductPrice());
            statement.setInt(3, product.getProductQuantity());
            statement.setString(4, product.getProductDescription());
            statement.setString(5, product.getProductImages());
            statement.setInt(6, product.getCategoryId());
            statement.setInt(7, product.getProductDiscount());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_ALL_PRODUCTS)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("name"));
                product.setProductDescription(rs.getString("description"));
                product.setProductPrice(rs.getInt("price"));
                product.setProductQuantity(rs.getInt("quantity"));
                product.setProductDiscount(rs.getInt("discount"));
                product.setProductImages(rs.getString("image"));
                product.setCategoryId(rs.getInt("cid"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;
        try (PreparedStatement statement = con.prepareStatement(GET_PRODUCT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    product = new Product();
                    product.setProductId(rs.getInt("pid"));
                    product.setProductName(rs.getString("name"));
                    product.setProductDescription(rs.getString("description"));
                    product.setProductPrice(rs.getInt("price"));
                    product.setProductQuantity(rs.getInt("quantity"));
                    product.setProductDiscount(rs.getInt("discount"));
                    product.setProductImages(rs.getString("image"));
                    product.setCategoryId(rs.getInt("cid"));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return product;
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean flag = false;
        try (PreparedStatement statement = con.prepareStatement(DELETE_PRODUCT)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean flag = false;
        try (PreparedStatement statement = con.prepareStatement(UPDATE_PRODUCT)) {
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getProductPrice());
            statement.setInt(3, product.getProductQuantity());
            statement.setString(4, product.getProductDescription());
            statement.setString(5, product.getProductImages());
            statement.setInt(6, product.getCategoryId());
            statement.setInt(7, product.getProductId());
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> list = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(GET_PRODUCTS_BY_CATEGORY)) {
            statement.setInt(1, categoryId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("pid"));
                    product.setProductName(rs.getString("name"));
                    product.setProductDescription(rs.getString("description"));
                    product.setProductPrice(rs.getInt("price"));
                    product.setProductQuantity(rs.getInt("quantity"));
                    product.setProductDiscount(rs.getInt("discount"));
                    product.setProductImages(rs.getString("image"));
                    product.setCategoryId(rs.getInt("cid"));
                    list.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> list = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement(SEARCH_PRODUCTS)) {
            String search = "%" + keyword.toLowerCase() + "%";
            statement.setString(1, search);
            statement.setString(2, search);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product();
                    product.setProductId(rs.getInt("pid"));
                    product.setProductName(rs.getString("name"));
                    product.setProductDescription(rs.getString("description"));
                    product.setProductPrice(rs.getInt("price"));
                    product.setProductQuantity(rs.getInt("quantity"));
                    product.setProductDiscount(rs.getInt("discount"));
                    product.setProductImages(rs.getString("image"));
                    product.setCategoryId(rs.getInt("cid"));
                    list.add(product);
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public List<Product> getDiscountedProducts() {
        List<Product> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_DISCOUNTED_PRODUCTS)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("name"));
                product.setProductDescription(rs.getString("description"));
                product.setProductPrice(rs.getInt("price"));
                product.setProductQuantity(rs.getInt("quantity"));
                product.setProductDiscount(rs.getInt("discount"));
                product.setProductImages(rs.getString("image"));
                product.setCategoryId(rs.getInt("cid"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    @Override
    public void updateProductQuantity(int id, int quantity) {
        try (PreparedStatement statement = con.prepareStatement(UPDATE_PRODUCT_QUANTITY)) {
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int getTotalProduct() {
        int count = 0;
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_TOTAL_PRODUCT)) {
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return count;
    }

    @Override
    public int getProductPriceById(int id) {
        int price = 0;
        try (PreparedStatement statement = con.prepareStatement(GET_PRODUCT_PRICE_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int orgPrice = rs.getInt("price");
                    int discount = rs.getInt("discount");
                    int discountPrice = (discount / 100) * orgPrice;
                    price = orgPrice - discountPrice;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return price;
    }

    @Override
    public int getProductQuantityById(int id) {
        int quantity = 0;
        try (PreparedStatement statement = con.prepareStatement(GET_PRODUCT_QUANTITY_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return quantity;
    }

    @Override
    public List<Product> getAllLatestProducts() {
        List<Product> list = new ArrayList<>();
        try (Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(GET_LATEST_PRODUCTS)) {
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("pid"));
                product.setProductName(rs.getString("name"));
                product.setProductDescription(rs.getString("description"));
                product.setProductPrice(rs.getInt("price"));
                product.setProductQuantity(rs.getInt("quantity"));
                product.setProductDiscount(rs.getInt("discount"));
                product.setProductImages(rs.getString("image"));
                product.setCategoryId(rs.getInt("cid"));
                list.add(product);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}