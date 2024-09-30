package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Cart;
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

@WebServlet("/AddToCart")
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int uid = Integer.parseInt(req.getParameter("uid"));
        int pid = Integer.parseInt(req.getParameter("pid"));

        CartServiceImpl cartService = new CartServiceImpl(ConnectionProvider.getConnection());
        int qty = cartService.getQuantity(uid, pid);
        HttpSession session = req.getSession();
        Message message = null;

        if (qty == 0) {
            Cart cart = new Cart(uid, pid, qty + 1);
            cartService.addProductToCart(cart);
            message = new Message("Product is added to cart successfully!", "success", "alert-success");

        }else {
            int cid = cartService.getIdByUserIdAndProductId(uid, pid);
            cartService.updateQuantity(cid, qty+1);
            message = new Message("Product quantity is increased!", "success", "alert-success");
        }
        //updating quantity of product in database
        ProductServiceImpl productService = new ProductServiceImpl(ConnectionProvider.getConnection());
        productService.updateProductQuantity(pid, productService.getProductQuantityById(pid) - 1);
        session.setAttribute("message", message);
        resp.sendRedirect("viewProduct.jsp?pid="+pid);
    }
}