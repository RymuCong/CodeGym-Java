<%@page import="com.cg.casestudy.service.ProductServiceImpl"%>
<%@page import="com.cg.casestudy.entity.Product"%>
<%@ page import="com.cg.casestudy.utils.ConnectionProvider" %>
<%@page import="com.cg.casestudy.utils.PriceFormatter"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%
int productId = Integer.parseInt(request.getParameter("pid"));
ProductServiceImpl ProductServiceImpl = new ProductServiceImpl(ConnectionProvider.getConnection());
Product product = ProductServiceImpl.getProductById(productId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Product</title>
<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
.real-price {
	font-size: 26px !important;
	font-weight: 600;
}

.product-price {
	font-size: 18px !important;
	text-decoration: line-through;
}

.product-discount {
	font-size: 16px !important;
	color: #027a3e;
}
</style>
</head>
<body>

	<!--navbar -->
	<%@include file="components/navbar.jsp"%>

	<div class="container mt-5">
			<%@include file="components/alert_message.jsp"%>
		<div class="row border border-3">
			<div class="col-md-6">
				<div class="container-fluid text-end my-3">
					<img src="<%=product.getProductImages()%>"
						class="card-img-top"
						style="max-width: 100%; max-height: 500px; width: auto;">
				</div>
			</div>
			<div class="col-md-6">
				<div class="container-fluid my-5">
					<h4><%=product.getProductName()%></h4>
					<span class="fs-5"><b>Description</b></span><br> <span><%=product.getProductDescription()%></span><br>
					<span class="real-price"><%=PriceFormatter.formatPrice(product.getProductPriceAfterDiscount())%></span>&ensp;
					<span class="product-price"><%=PriceFormatter.formatPrice(product.getProductPrice())%></span>&ensp;
					<span class="product-discount"><%=product.getProductDiscount()%>&#37;</span><br>
					<span class="fs-5"><b>Status : </b></span> <span id="availability">
						<%
							String availability = product.getProductQuantity() > 0 ? "Available" : "Out of stock";
						%>
  						<%= availability %>
					</span><br> <span class="fs-5"><b>Category: </b></span> <span><%=catService.getCategoryNameById(product.getCategoryId())%></span>
					<form method="post">
						<div class="container-fluid text-center mt-3">
							<%
							if (user == null && admin == null) {
							%>
							<button type="button" onclick="window.open('login.jsp', '_self')"
								class="btn btn-primary text-white btn-lg">Add to cart</button>
							&emsp;
							<button type="button" onclick="window.open('login.jsp', '_self')"
								class="btn btn-info text-white btn-lg">Buy now</button>
							<%
							} else if (user != null) {
							%>
							<button type="submit"
									formaction="./AddToCart?uid=<%=user.getId()%>&pid=<%=product.getProductId()%>"
								class="btn btn-primary text-white btn-lg">Add to cart</button>
							&emsp; <a
								href="checkout.jsp" id="buy-btn"
								class="btn btn-info text-white btn-lg" role="button"
								aria-disabled="true">Buy now</a>
							<%
							} else {
							%>
								<button type="button"
								class="btn btn-primary text-white btn-lg">Add to cart</button>
										&emsp;
								<button type="button"
								class="btn btn-info text-white btn-lg">Buy now</button>
							<%
							}
							%>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function() {
			if ($('#availability').text().trim() === "Out of stock") {
				$('#availability').css('color', 'red');
				$('#add-to-cart-btn').addClass('disabled').attr('disabled', true);
				$('#buy-btn').addClass('disabled').attr('aria-disabled', 'true');
			}
			$('#buy-btn').click(function(){
				<%
                session.setAttribute("pid", productId);
                session.setAttribute("form", "buy");
                %>
			});
		});
	</script>
</body>
</html>