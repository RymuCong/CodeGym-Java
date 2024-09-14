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

		String login = request.getParameter("login");

		System.out.println("Hello from LoginServlet");

        if (login.trim().equals("user")) {
			try {
				String userEmail = request.getParameter("user_email");
				String userPassword = request.getParameter("user_password");

				// getting user through entered email and password
				UserServiceImpl userService = new UserServiceImpl();
				User user = userService.getByEmailPassword(userEmail, userPassword);

				// storing current user in session
				HttpSession session = request.getSession();
				if (user != null) {
					session.setAttribute("activeUser", user);
					response.sendRedirect("index.jsp");
				} else {
					Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
					session.setAttribute("message", message);
					response.sendRedirect("login.jsp");
				}

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		} else if (login.trim().equals("admin")) {
			try {
				String userName = request.getParameter("email");
				String password = request.getParameter("password");
				System.out.println(userName + " " + password);

				AdminServiceImpl adminService = new AdminServiceImpl();
				Admin admin = adminService.getAdminByEmailAndPassword(userName, password);

				HttpSession session = request.getSession();
				if (admin != null) {
					session.setAttribute("activeAdmin", admin);
					response.sendRedirect("admin.jsp");
				} else {
					Message message = new Message("Invalid details! Try again!!", "error", "alert-danger");
					session.setAttribute("message", message);
					response.sendRedirect("adminlogin.jsp");
				}
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}
}