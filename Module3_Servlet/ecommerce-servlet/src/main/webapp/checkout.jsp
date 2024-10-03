<%@ page import="com.cg.casestudy.entity.*" %>
<%@ page import="com.cg.casestudy.service.CartServiceImpl" %>
<%@ page import="com.cg.casestudy.service.ProductServiceImpl" %>
<%@ page import="com.cg.casestudy.utils.ConnectionProvider" %>
<%@ page import="com.cg.casestudy.utils.PriceFormatter" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page errorPage="error_exception.jsp"%>
<%
User activeUser = (User) session.getAttribute("activeUser");
if (activeUser == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
String form = (String)session.getAttribute("form");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
<%@include file="components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="components/navbar.jsp"%>

	<div class="container mt-5" style="font-size: 17px;">
		<div class="row">

			<!-- left column -->
			<div class="col-md-8">
				<div class="card">
					<div class="container px-3 py-3">
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Delivery Address</h4>
							</div>
						</div>
						<div class="mt-3 mb-3">
							<h5><%=user.getName()%>
								&nbsp;<%=user.getPhone()%>
							</h5>
							<%=user.getAddress()%>
							<br>
							<div class="text-end">
								<button type="button" class="btn btn-outline-primary"
									data-bs-toggle="modal" data-bs-target="#exampleModal">
									Change Address</button>
							</div>
						</div>
						<hr>
						<div class="card">
							<div class="container-fluid text-white"
								style="background-color: #389aeb;">
								<h4>Payment Options</h4>
							</div>
						</div>
						<form action="OrderServlet" method="post">
							<div class="form-check mt-2">
								<input class="form-check-input" type="radio" name="paymentMode"
									value="Card Payment" required><label class="form-check-label">Credit
									/Debit /ATM card</label><br>
								<div class="mb-3">

									<input class="form-control mt-3" type="number"
										placeholder="Enter card number" name="cardno">
									<div class="row gx-5">
										<div class="col mt-3">
											<input class="form-control" type="number"
												placeholder="Enter CVV" name="cvv">
										</div>
										<div class="col mt-3">
											<input class="form-control" type="text"
												placeholder="Valid through i.e '07/23'">
										</div>
									</div>
									<input class="form-control mt-3" type="text"
										placeholder="Enter card holder name" name="name">
								</div>
								<input class="form-check-input" type="radio" name="paymentMode"
									value="Cash on Delivery"><label
									class="form-check-label">Cash on Delivery</label>
							</div>
							<div class="text-end">
								<button type="submit"
									class="btn btn-lg btn-outline-primary mt-3">Place
									Order</button>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- end of column -->

			<!-- right column -->
			<div class="col-md-4">
				<div class="card">
					<div class="container px-3 py-3">
						<h4>Price Details</h4>
						<hr>
						<%
						if (form.trim().equals("cart")) {
							CartServiceImpl cartService = new CartServiceImpl(ConnectionProvider.getConnection());
							int totalProduct = cartService.getCartCountByUserId(user.getId());
							int totalPrice = (int) session.getAttribute("totalPrice");
						%>
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td><%=PriceFormatter.formatPrice(totalProduct)%></td>
							</tr>
							<tr>
								<td>Total Price</td>
								<td> <%=PriceFormatter.formatPrice(totalPrice)%></td>
							</tr>
							<tr>
								<td>Delivery Charges</td>
								<td>Free</td>
							</tr>
							<tr>
								<td>Packaging Charges</td>
								<td>Free</td>
							</tr>
							<tr>
								<td><h5>Amount Payable :</h5></td>
								<td>
									<h5><%=PriceFormatter.formatPrice(totalPrice)%></h5>
								</td>
							</tr>
						</table>
						<%
						} else {
							ProductServiceImpl productService = new ProductServiceImpl(ConnectionProvider.getConnection());
							int pid = (int) session.getAttribute("pid");
							int price = productService.getProductPriceById(pid);
						%>
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td>1</td>
							</tr>
							<tr>
								<td>Total Price</td>
								<td> <%=PriceFormatter.formatPrice(price)%></td>
							</tr>
							<tr>
								<td>Delivery Charges</td>
								<td>Free</td>
							</tr>
							<tr>
								<td>Packaging Charges</td>
								<td>Free</td>
							</tr>
							<tr>
								<td><h5>Amount Payable :</h5></td>
								<td><h5>
									<%=PriceFormatter.formatPrice(price)%>
								</h5></td>
							</tr>
						</table>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<!-- end of column -->
		</div>
	</div>


	<!--Change Address Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">Change
						Address</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="UpdateUserServlet" method="post">
					<input type="hidden" name="operation" value="changeAddress">
					<div class="modal-body mx-3">
						<div class="mt-2">
							<label class="form-label fw-bold">Address</label>
							<textarea name="user_address" rows="3"
								placeholder="Enter Address(Area and Street))"
								class="form-control" required></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary">Save</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end modal -->

</body>
</html>