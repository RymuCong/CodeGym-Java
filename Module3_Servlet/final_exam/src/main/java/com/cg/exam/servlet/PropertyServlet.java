package com.cg.exam.servlet;

import com.cg.exam.entity.Property;
import com.cg.exam.service.PropertyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/property")
public class PropertyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PropertyDAO propertyDAO = new PropertyDAO();
        List<Property> properties = propertyDAO.getAllPropertiesSortedByArea();
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
