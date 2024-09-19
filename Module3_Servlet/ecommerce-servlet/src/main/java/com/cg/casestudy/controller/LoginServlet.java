package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Admin;
import com.cg.casestudy.entity.Message;
import com.cg.casestudy.entity.User;
import com.cg.casestudy.service.AdminServiceImpl;
import com.cg.casestudy.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();

		// Check if the user is an admin
		AdminServiceImpl adminService = new AdminServiceImpl();
		Admin admin = adminService.getAdminByEmailAndPassword(email, password);

		if (admin != null) {
			session.setAttribute("activeAdmin", admin);
			response.sendRedirect("admin.jsp");
			return;
		}

		// Check if the user is a regular user
		UserServiceImpl userService = new UserServiceImpl();
		User user = userService.getByEmailPassword(email, password);

		if (user != null) {
			session.setAttribute("activeUser", user);
			response.sendRedirect("index.jsp");
		} else {
			Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
			session.setAttribute("message", message);
			response.sendRedirect("login.jsp");
		}
	}
}