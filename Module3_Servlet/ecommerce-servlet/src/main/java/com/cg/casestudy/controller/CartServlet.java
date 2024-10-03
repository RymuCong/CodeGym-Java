package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Message;
import com.cg.casestudy.service.CartServiceImpl;
import com.cg.casestudy.service.ProductServiceImpl;
import com.cg.casestudy.utils.ConnectionProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CartServiceImpl cartService = new CartServiceImpl(ConnectionProvider.getConnection());
        ProductServiceImpl productService = new ProductServiceImpl(ConnectionProvider.getConnection());
        int cid =Integer.parseInt(request.getParameter("cid"));
        int opt =Integer.parseInt(request.getParameter("opt"));

        int qty = cartService.getQuantityById(cid);
        int pid = cartService.getProductId(cid);
        int quantity = productService.getProductQuantityById(pid);

        if(opt == 1) {
            if(quantity > 0) {
                cartService.updateQuantity(cid, qty+1);
                //updating(decreasing) quantity of product in database
                productService.updateProductQuantity(pid, productService.getProductQuantityById(pid) - 1);
                response.sendRedirect("cart.jsp");

            }else {
                HttpSession session = request.getSession();
                Message message = new Message("Product out of stock!", "error", "alert-danger");
                session.setAttribute("message", message);
                response.sendRedirect("cart.jsp");
            }

        }else if(opt == 2) {
            cartService.updateQuantity(cid, qty-1);

            //updating(increasing) quantity of product in database
            productService.updateProductQuantity(pid, productService.getProductQuantityById(pid) + 1);
            response.sendRedirect("cart.jsp");

        }else if(opt == 3) {
            cartService.removeProduct(cid);
            HttpSession session = request.getSession();
            Message message = new Message("Product removed from cart!", "success", "alert-success");
            session.setAttribute("message", message);

            //updating quantity of product in database
            //adding all the product qty back to database
            productService.updateProductQuantity(pid, productService.getProductQuantityById(pid) + qty);
            response.sendRedirect("cart.jsp");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}
