package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Message;
import com.cg.casestudy.entity.User;
import com.cg.casestudy.service.UserService;
import com.cg.casestudy.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		HttpSession session = request.getSession();
		User oldUser = (User) session.getAttribute("activeUser");

		switch (op.trim()) {
			case "changeAddress":
				handleAddressChange(request, response, session, oldUser);
				break;
			case "updateUser":
				handleUserUpdate(request, response, session, oldUser);
				break;
			case "deleteUser":
				handleUserDeletion(request, response);
				break;
		}
	}

	private void handleAddressChange(HttpServletRequest request, HttpServletResponse response, HttpSession session, User oldUser)
			throws IOException {
		try {
			String userAddress = request.getParameter("user_address");

			User user = new User();
			user.setId(oldUser.getId());
			user.setName(oldUser.getName());
			user.setEmail(oldUser.getEmail());
			user.setPassword(oldUser.getPassword());
			user.setPhone(oldUser.getPhone());
			user.setGender(oldUser.getGender());
			user.setDateTime(oldUser.getDateTime());
			user.setAddress(userAddress);

			userService.updateUserAddress(user);
			session.setAttribute("activeUser", user);
			response.sendRedirect("checkout.jsp");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void handleUserUpdate(HttpServletRequest request, HttpServletResponse response, HttpSession session, User oldUser)
			throws IOException {
		try {
			String userName = request.getParameter("name");
			String userEmail = request.getParameter("email");
			String userPhone = request.getParameter("mobile_no");
			String userGender = request.getParameter("gender");
			String userAddress = request.getParameter("address");

			User user = new User(userName, userEmail, userPhone, userGender, userAddress);
			user.setId(oldUser.getId());
			user.setPassword(oldUser.getPassword());
			user.setDateTime(oldUser.getDateTime());

			userService.updateUser(user);
			session.setAttribute("activeUser", user);
			Message message = new Message("User information updated successfully!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("profile.jsp");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void handleUserDeletion(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		userService.deleteUser(uid);
		response.sendRedirect("display_users.jsp");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}