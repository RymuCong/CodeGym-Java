<%@page import="com.cg.casestudy.entity.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page errorPage="error_exception.jsp"%>
<%@page import="com.cg.casestudy.service.UserServiceImpl"%>
<%@page import="com.cg.casestudy.entity.Product"%>
<%@page import="com.cg.casestudy.service.ProductServiceImpl"%>
<%@ page import="com.cg.casestudy.service.ConnectionProvider" %>
<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminlogin.jsp");
	return;
}
UserServiceImpl UserServiceImpl = new UserServiceImpl(ConnectionProvider.getConnection());
ProductServiceImpl ProductServiceImpl = new ProductServiceImpl(ConnectionProvider.getConnection());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Product's</title>
<%@include file="Components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/navbar.jsp"%>

	<!-- update product -->
	<div class="container mt-3">
		<%@include file="Components/alert_message.jsp"%>
		<table class="table table-hover">
			<tr class="table-primary text-center" style="font-size: 20px;">
				<th>Image</th>
				<th>Name</th>
				<th class="text-start">Category</th>
				<th>Price</th>
				<th class="text-start">Quantity</th>
				<th class="text-start">Discount</th>
				<th>Action</th>
			</tr>
			<%
			List<Product> productList = ProductServiceImpl.getAllProducts();
			for (Product prod : productList) {
				String category = catDao.getCategoryNameById(prod.getCategoryId());
			%>
			<tr class="text-center">
				<td><img src="Product_imgs\<%=prod.getProductImages()%>"
					style="width: 60px; height: 60px; width: auto;"></td>
				<td class="text-start"><%=prod.getProductName()%></td>
				<td><%=category%></td>
				<td><%=prod.getProductPriceAfterDiscount()%></td>
				<td><%=prod.getProductQuantity()%></td>
				<td><%=prod.getProductDiscount()%>%</td>
				<td><a href="update_product.jsp?pid=<%=prod.getProductId()%>" role="button" class="btn btn-secondary">Update</a>&emsp;<a
					href="AddOperationServlet?pid=<%=prod.getProductId()%>&operation=deleteProduct"
					class="btn btn-danger" role="button">Delete</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>
