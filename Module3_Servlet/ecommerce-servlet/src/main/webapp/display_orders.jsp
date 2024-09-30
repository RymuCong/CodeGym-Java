<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@ page import="com.cg.casestudy.entity.*" %>
<%@ page import="com.cg.casestudy.service.OrderServiceImpl" %>
<%@ page import="com.cg.casestudy.service.OrderedProductServiceImpl" %>
<%@ page import="com.cg.casestudy.service.UserServiceImpl" %>
<%@ page import="com.cg.casestudy.utils.PriceFormatter" %>

<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminlogin.jsp");
	return;
}
OrderServiceImpl orderService = new OrderServiceImpl(ConnectionProvider.getConnection());
OrderedProductServiceImpl ordProdService = new OrderedProductServiceImpl(ConnectionProvider.getConnection());
List<Order> orderList = orderService.getAllOrder();
UserServiceImpl userService = new UserServiceImpl(ConnectionProvider.getConnection());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Order's</title>
<%@include file="components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="components/navbar.jsp"%>

	<!-- order details -->

	<div class="container-fluid px-3 py-3">
		<%
		if (orderList == null || orderList.isEmpty()) {
		%>
		<div class="container mt-5 mb-5 text-center">
			<img src="images/empty-cart.png" style="max-width: 200px;"
				class="img-fluid">
			<h4 class="mt-3">Zero Order found</h4>
		</div>
		<%
		} else {
		%>
		<div class="container-fluid">
			<table class="table table-hover">
				<tr class="table-primary" style="font-size: 18px;">
					<th class="text-center">Product</th>
					<th>Order ID</th>
					<th>Product Details</th>
					<th>Delivery Address</th>
					<th>Date & Time</th>
					<th>Payment Type</th>
					<th>Status</th>
					<th colspan="2" class="text-center">Action</th>
				</tr>
				<%
				for (Order order : orderList) {
					List<OrderedProduct> ordProdList = ordProdService.getAllOrderedProduct(order.getId());
					for (OrderedProduct orderProduct : ordProdList) {
				%>
				<form action="UpdateOrderServlet?oid=<%=order.getId()%>"
					method="post">
				<tr>
					<td class="text-center"><img
						src="<%=orderProduct.getImage()%>"
						style="width: 50px; height: 50px; width: auto;"></td>
					<td><%=order.getOrderId()%></td>
					<td><%=orderProduct.getName()%><br>Quantity: <%=orderProduct.getQuantity()%><br>Total
						Price: <%=PriceFormatter.formatPrice(orderProduct.getPrice() * orderProduct.getQuantity())%></td>
					<td><%=userService.getName(order.getUserId())%><br>Mobile No. <%=userService.getPhone(order.getUserId())%><br><%=userService.getAddress(order.getUserId())%></td>
					<td><%=order.getDate()%></td>
					<td><%=order.getPaymentType()%></td>
					<td><%=order.getStatus()%></td>
					<td><select id="operation" name="status" class="form-select">
							<option>--Select Operation--</option>
							<option value="Order Confirmed">Order Confirmed</option>
							<option value="Shipped">Shipped</option>
							<option value="Out For Delivery">Out For Delivery</option>
							<option value="Delivered">Delivered</option>
					</select></td>
					<td>
						<%
						if (order.getStatus().equals("Delivered")) {
						%>
						<button type="submit" class="btn btn-success disabled">Update</button>
						<%
						} else {
						%>
						<button type="submit" class="btn btn-secondary">Update</button> 
						<%
						 }
						 %>
					</td>
				</tr>
				</form>
				<%
				}
				}
				%>
			</table>

		</div>
		<%
		}
		%>
	</div>
</body>
</html>