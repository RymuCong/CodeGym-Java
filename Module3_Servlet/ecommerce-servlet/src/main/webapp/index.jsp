<%@page import="com.cg.casestudy.service.ProductServiceImpl"%>
<%@ page import="com.cg.casestudy.utils.ConnectionProvider" %>
<%@ page import="java.util.List" %>
<%@ page import="com.cg.casestudy.utils.PriceFormatter" %>
<%@ page import="com.cg.casestudy.entity.*" %>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
ProductServiceImpl productServiceImpl = new ProductServiceImpl(ConnectionProvider.getConnection());
List<Product> productList = productServiceImpl.getAllLatestProducts();
List<Product> topDeals = productServiceImpl.getDiscountedProducts();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@include file="components/common_css_js.jsp"%>
<style type="text/css">
.cus-card {
	border-radius: 50%;
	border-color: transparent;
	max-height: 200px;
	max-width: 200px;
	max-height: 200px;
}

.real-price {
	font-size: 20px !important;
	font-weight: 600;
}

.product-price {
	font-size: 17px !important;
	text-decoration: line-through;
}

.product-discount {
	font-size: 15px !important;
	color: #027a3e;
}
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="components/navbar.jsp"%>
	<!-- end of navbar -->

	<!-- Category list -->
	<div class="container-fluid" style="background-color: #ff0055;">
		<div class="row">
			<div class="col-12">
				<ul class="nav justify-content-center">
					<%
						for (Category c : categoryList) {
					%>
					<li class="nav-item">
						<a class="nav-link" href="products.jsp?category=<%=c.getCategoryId()%>" style="color: white; text-align: center;">
							<img src="<%=c.getCategoryImage()%>" style="width: 30px; height: 30px; margin-bottom: 5px;">
							<%=c.getCategoryName()%>
						</a>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
<%--	<!-- end of list -->--%>

	<!-- Carousel -->
	<div id="carouselAutoplaying"
		class="carousel slide carousel-dark mt-3 mb-3" data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/scroll_img2.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="images/scroll_img1.png" class="d-block w-100" alt="...">
			</div>
			<div class="carousel-item">
				<img src="images/scroll_img3.png" class="d-block w-100" alt="...">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselAutoplaying" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"
				style="color: black;"></span> <span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselAutoplaying" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<!-- end of carousel -->

	<!-- latest product listed -->
	<div class="container-fluid py-3 px-3" style="background: #f2f2f2;">
		<div class="row row-cols-1 row-cols-md-4 g-3">
			<div class="col">
				<div class="container text-center px-5 py-5">
					<h1>Latest Products</h1>
<%--					<img src="images/product.png" class="card-img-top"--%>
<%--						style="max-width: 100%; max-height: 200px; width: auto;" alt="">--%>
				</div>
			</div>
			<%
			for (int i = 0; i < Math.min(3, productList.size()); i++) {
			%>
			<div class="col">
				<a href="viewProduct.jsp?pid=<%=productList.get(i).getProductId()%>"
					style="text-decoration: none;">
					<div class="card h-100">
						<div class="container text-center">
							<img
								src="<%=productList.get(i).getProductImages()%>"
								class="card-img-top m-2"
								style="max-width: 100%; max-height: 200px; width: auto;">
						</div>
						<div class="card-body">
							<h5 class="card-title text-center"><%=productList.get(i).getProductName()%></h5>

							<div class="container text-center">
								<span class="real-price"><%=PriceFormatter.formatPrice(productList.get(i).getProductPriceAfterDiscount())%></span>
								&ensp;<span class="product-price"><%=PriceFormatter.formatPrice(productList.get(i).getProductPrice())%>
								</span>&ensp;<span class="product-discount"><%=productList.get(i).getProductDiscount()%>&#37;
									off</span>
							</div>
						</div>
					</div>
				</a>
			</div>

			<%
			}
			%>
		</div>
	</div>
	<!-- end of list -->

	<!-- product with heavy deals -->
	<div class="container-fluid py-3 px-3" style="background: #f0fffe;">
		<h3>Hot Deals</h3>
		<div class="row row-cols-1 row-cols-md-4 g-3">
			<%
			for (int i = 0; i < Math.min(4, topDeals.size()); i++) {
			%>
			<div class="col">
				<a href="viewProduct.jsp?pid=<%=topDeals.get(i).getProductId()%>"
					style="text-decoration: none;">
					<div class="card h-100">
						<div class="container text-center">
							<img src="<%=topDeals.get(i).getProductImages()%>"
								class="card-img-top m-2"
								style="max-width: 100%; max-height: 200px; width: auto;">
						</div>
						<div class="card-body">
							<h5 class="card-title text-center"><%=topDeals.get(i).getProductName()%></h5>

							<div class="container text-center">
								<span class="real-price"><%=PriceFormatter.formatPrice(topDeals.get(i).getProductPriceAfterDiscount())%></span>
								&ensp;<span class="product-price"><%=PriceFormatter.formatPrice(topDeals.get(i).getProductPrice())%>
								</span>&ensp;<span class="product-discount"><%=topDeals.get(i).getProductDiscount()%>&#37;
									off</span>
							</div>
						</div>
					</div>
				</a>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<!-- end -->

	<!-- confirmation message for successful order -->
<%--	<%--%>
<%--	String order = (String) session.getAttribute("order");--%>
<%--	if (order != null) {--%>
<%--	%>--%>
<%--	<script type="text/javascript">--%>
<%--		console.log("testing..4...");--%>
<%--		Swal.fire({--%>
<%--		  icon : 'success',--%>
<%--		  title: 'Order Placed, Thank you!',--%>
<%--		  text: 'Confirmation will be sent to <%=user.getEmail()%>',--%>
<%--		  width: 600,--%>
<%--		  padding: '3em',--%>
<%--		  showConfirmButton : false,--%>
<%--		  timer : 3500,--%>
<%--		  backdrop: `--%>
<%--		    rgba(0,0,123,0.4)--%>
<%--		  `--%>
<%--		});--%>
<%--	</script>--%>
<%--	<%--%>
<%--	}--%>
<%--	session.removeAttribute("order");--%>
<%--	%>--%>
	<!-- end of message -->

</body>
</html>