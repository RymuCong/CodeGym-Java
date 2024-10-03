package com.cg.exam.servlet;

import com.cg.exam.common.DateUtil;
import com.cg.exam.entity.Property;
import com.cg.exam.service.PropertyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@WebServlet("/addProperty")
public class AddPropertyServlet extends HttpServlet {
    private final PropertyDAO propertyDAO = new PropertyDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            createProperty(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createProperty(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String propertyId = request.getParameter("propertyId");
        String status = request.getParameter("status");
        double area = Double.parseDouble(request.getParameter("area"));
        int floor = Integer.parseInt(request.getParameter("floor"));
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = null;
        Date endDate = null;
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        try {
            startDate = DateUtil.parseDate(startDateStr);
            endDate = DateUtil.parseDate(endDateStr);
        } catch (ParseException e) {
            forwardToCreateForm(request, response, propertyId, status, area, floor, type, price, null, null, "Ngày không đúng định dạng !!!");
            return;
        }

        if (!isValidPropertyId(propertyId)) {
            forwardToCreateForm(request, response, propertyId, status, area, floor, type, price, startDate, endDate, "Mã mặt bằng không đúng định dạng !!!");
            return;
        }

        if (propertyDAO.isPropertyIdExists(propertyId)) {
            forwardToCreateForm(request, response, propertyId, status, area, floor, type, price, startDate, endDate, "Mã mặt bằng đã tồn tại !!!");
            return;
        }

        if (area <= 20) {
            forwardToCreateForm(request, response, propertyId, status, area, floor, type, price, startDate, endDate, "Diện tích phải lớn hơn 20m² !!!");
            return;
        }

        if (price <= 1000000) {
            forwardToCreateForm(request, response, propertyId, status, area, floor, type, price, startDate, endDate, "Giá tiền phải lớn hơn 1.000.000 VNĐ !!!");
            return;
        }

        if (startDate.after(endDate) || (endDate.getTime() - startDate.getTime()) < 15768000000L) {
            forwardToCreateForm(request, response, propertyId, status, area, floor, type, price, startDate, endDate, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc ít nhất là 6 tháng !!!");
            return;
        }

        if (Objects.equals(status, "0"))
            status = "Trống";
        else if (Objects.equals(status, "1"))
            status = "Hạ tầng";
        else
            status = "Đầy đủ";
        propertyDAO.addProperty(new Property(propertyId, status, area, floor, type, price, startDate, endDate));
        response.sendRedirect(request.getContextPath() + "/property");
    }

    private void forwardToCreateForm(HttpServletRequest request, HttpServletResponse response, String propertyId, String status, double area, int floor, String type, double price, Date startDate, Date endDate, String errorMessage) throws ServletException, IOException {
        Property property = new Property(propertyId, status, area, floor, type, price, startDate, endDate);
        request.setAttribute("property", property);
        request.setAttribute("errorMessage", errorMessage);
        request.getRequestDispatcher("addProperty.jsp").forward(request, response);
    }

    private boolean isValidPropertyId(String propertyId) {
        return propertyId.matches("[A-Z0-9]{3}-[A-Z0-9]{2}-[A-Z0-9]{2}");
    }
}