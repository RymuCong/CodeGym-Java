package com.cg.exam.servlet;

import com.cg.exam.service.PropertyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProperty")
public class DeletePropertyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String propertyId = request.getParameter("propertyId");

        PropertyDAO propertyDAO = new PropertyDAO();
        propertyDAO.deleteProperty(propertyId);

        // Redirect to list page
        response.sendRedirect("/");
    }
}