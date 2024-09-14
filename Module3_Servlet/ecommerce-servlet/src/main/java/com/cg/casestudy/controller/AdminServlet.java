package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Admin;
import com.cg.casestudy.entity.Message;
import com.cg.casestudy.service.AdminServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		AdminServiceImpl adminService = new AdminServiceImpl();

		String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		Message message = null;
		if (operation != null) {
			switch (operation.trim()) {
				case "save":
					String name = request.getParameter("name");
					String email = request.getParameter("email");
					String password = request.getParameter("password");
					String phone = request.getParameter("phone");

					Admin admin = new Admin(name, email, phone, password);

					boolean saveFlag = adminService.saveAdmin(admin);

					if (saveFlag) {
						message = new Message("New admin registered successfully!", "success", "alert-success");
					} else {
						message = new Message("Sorry! Something went wrong", "error", "alert-danger");
					}
					break;

				case "delete":
					int id = Integer.parseInt(request.getParameter("id"));
					boolean deleteFlag = adminService.deleteAdmin(id);

					if (deleteFlag) {
						message = new Message("Admin deleted successfully!", "success", "alert-success");
					} else {
						message = new Message("Sorry! Something went wrong", "error", "alert-danger");
					}
					break;

				default:
					message = new Message("Invalid operation!", "error", "alert-danger");
					break;
			}
		} else {
			message = new Message("Operation not specified!", "error", "alert-danger");
		}

		session.setAttribute("message", message);
		response.sendRedirect("display_admin.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}