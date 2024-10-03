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

@WebServlet("/filterProperties")
public class FilterPropertiesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        String floorStr = request.getParameter("floor");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        PropertyDAO propertyDAO = new PropertyDAO();
        List<Property> filteredProperties = propertyDAO.filterProperties(type, floorStr, startDateStr, endDateStr);

        request.setAttribute("properties", filteredProperties);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}