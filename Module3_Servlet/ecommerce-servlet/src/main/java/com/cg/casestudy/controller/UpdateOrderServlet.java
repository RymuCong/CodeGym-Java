package com.cg.casestudy.controller;

import com.cg.casestudy.service.OrderServiceImpl;
import com.cg.casestudy.utils.ConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int oid = Integer.parseInt(request.getParameter("oid"));
        String status = request.getParameter("status");
        OrderServiceImpl orderService = new OrderServiceImpl(ConnectionProvider.getConnection());
        orderService.updateOrderStatus(oid, status);
//        if (status.trim().equals("Shipped") || status.trim().equals("Out For Delivery")) {
//            Order order = orderService.getOrderById(oid);
//            UserServiceImpl userService = new UserServiceImpl(ConnectionProvider.getConnection());
//        }
        response.sendRedirect("display_orders.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
