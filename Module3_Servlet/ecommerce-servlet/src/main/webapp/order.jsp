<%@ page import="com.cg.casestudy.utils.ConnectionProvider" %>
<%@ page import="com.cg.casestudy.entity.*" %>
<%@ page import="com.cg.casestudy.service.*" %>
<%@ page import="com.cg.casestudy.utils.PriceFormatter" %>
<%@page errorPage="error_exception.jsp"%>

<%
User u2 = (User) session.getAttribute("activeUser");
if (u2 == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
OrderServiceImpl orderService = new OrderServiceImpl(ConnectionProvider.getConnection());
OrderedProductServiceImpl ordProdService = new OrderedProductServiceImpl(ConnectionProvider.getConnection());

List<Order> orderList = orderService.getAllOrderByUserId(u2.getId());
%>
<div class="container-fluid px-3 py-3">
	<%
	if (orderList == null || orderList.isEmpty()) {
	%>
	<div class="container mt-5 mb-5 text-center">
		<img src="images/empty-cart.png" style="max-width: 200px;"
			class="img-fluid">
		<h4 class="mt-3">Zero Order found</h4>
		Looks like you haven't placed any order!
	</div>
	<%
	} else {
	%>
	<h4>My Order</h4>
	<hr>
	<div class="container">
		<table class="table table-hover">
			<tr class="text-center table-secondary">
			  <th>Product</th>
			  <th>Order ID</th>
			  <th>Name</th>
			  <th>Quantity</th>
			  <th>Total Price</th>
			  <th>Date and Time</th>
			  <th>Payment Type</th>
			  <th>Status</th>
			</tr>
			<%
			for (Order order : orderList) {
				List<OrderedProduct> ordProdList = ordProdService.getAllOrderedProduct(order.getId());
				for (OrderedProduct orderProduct : ordProdList) {
			%>
			<tr class="text-center">
				<td><img src="<%=orderProduct.getImage()%>"
					style="width: 40px; height: 40px; width: auto;"></td>
				<td class="text-start"><%=order.getOrderId()%></td>
				<td class="text-start"><%=orderProduct.getName()%></td>
				<td><%=orderProduct.getQuantity()%></td>
				<td><%=PriceFormatter.formatPrice(orderProduct.getPrice() * orderProduct.getQuantity())%></td>
				<td><%=order.getDate()%></td>
				<td><%=order.getPaymentType()%></td>
				<td class="fw-semibold" style="color: green;"><%=order.getStatus()%></td>
			</tr>
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
