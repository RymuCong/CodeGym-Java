package com.cg.casestudy.controller;

import com.cg.casestudy.entity.*;
import com.cg.casestudy.service.CartServiceImpl;
import com.cg.casestudy.service.OrderServiceImpl;
import com.cg.casestudy.service.OrderedProductServiceImpl;
import com.cg.casestudy.service.ProductServiceImpl;
import com.cg.casestudy.utils.ConnectionProvider;
import com.cg.casestudy.utils.OrderIdGenerator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String form = (String) session.getAttribute("form");

        if (form == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form attribute is missing.");
            return;
        }

        String paymentType = request.getParameter("paymentMode");
        User user = (User) session.getAttribute("activeUser");
        String orderId = OrderIdGenerator.getOrderId();
        String status = "Order Placed";

        try {
            OrderServiceImpl orderService = new OrderServiceImpl(ConnectionProvider.getConnection());
            Order order = new Order(orderId, status, paymentType, user.getId());
            int id = orderService.insertOrder(order);

            if (id == 0) {
                throw new SQLException("Failed to insert order, no ID obtained.");
            }

            if (form.trim().equals("cart")) {
                CartServiceImpl cartService = new CartServiceImpl(ConnectionProvider.getConnection());
                List<Cart> listOfCart = cartService.getCartListByUserId(user.getId());
                OrderedProductServiceImpl orderedProductService = new OrderedProductServiceImpl(ConnectionProvider.getConnection());
                ProductServiceImpl productService = new ProductServiceImpl(ConnectionProvider.getConnection());

                for (Cart item : listOfCart) {
                    Product prod = productService.getProductById(item.getProductId());
                    String prodName = prod.getProductName();
                    int prodQty = item.getQuantity();
                    int price = prod.getProductPriceAfterDiscount();
                    String image = prod.getProductImages();

                    OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, id);
                    orderedProductService.insertOrderedProduct(orderedProduct);
                }
                session.removeAttribute("form");
                session.removeAttribute("totalPrice");

                cartService.removeAllProduct(user.getId());
            } else if (form.trim().equals("buy")) {
                int pid = (int) session.getAttribute("pid");
                OrderedProductServiceImpl orderedProductService = new OrderedProductServiceImpl(ConnectionProvider.getConnection());
                ProductServiceImpl productService = new ProductServiceImpl(ConnectionProvider.getConnection());

                Product prod = productService.getProductById(pid);
                String prodName = prod.getProductName();
                int prodQty = 1;
                int price = prod.getProductPriceAfterDiscount();
                String image = prod.getProductImages();

                OrderedProduct orderedProduct = new OrderedProduct(prodName, prodQty, price, image, id);
                orderedProductService.insertOrderedProduct(orderedProduct);

                productService.updateProductQuantity(pid, productService.getProductQuantityById(pid) - 1);

                session.removeAttribute("form");
                session.removeAttribute("pid");
            }
            session.setAttribute("order", "success");
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the order.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
