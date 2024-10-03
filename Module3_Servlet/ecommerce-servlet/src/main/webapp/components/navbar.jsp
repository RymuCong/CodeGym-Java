<%@page import="com.cg.casestudy.entity.Admin"%>
<%@page import="com.cg.casestudy.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.cg.casestudy.entity.Category"%>
<%@ page import="com.cg.casestudy.service.CategoryServiceImpl" %>
<%@ page import="com.cg.casestudy.utils.ConnectionProvider" %>
<%@ page import="com.cg.casestudy.service.CartServiceImpl" %>
<%
	User user = (User) session.getAttribute("activeUser");
	Admin admin = (Admin) session.getAttribute("activeAdmin");

	CategoryServiceImpl catService = new CategoryServiceImpl(ConnectionProvider.getConnection());
	List<Category> categoryList = catService.getAllCategories();
%>
<style>
	.navbar {
		font-weight: 500;
	}

	.nav-link {
		color: rgb(255 255 255/ 100%) !important;
	}

	.dropdown-menu {
		background-color: #ffffff !important;
		border-color: #949494;
	}

	.dropdown-menu li a:hover {
		background-color: #808080 !important;
		color: white;
	}
</style>
<nav class="navbar navbar-expand-lg custom-color" data-bs-theme="dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%= admin != null ? "admin.jsp" : "index.jsp" %>">
			<i class="fa-sharp fa-solid fa-house" style="color: #ffffff;"></i>&ensp;SuccesS
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item">
					<a class="nav-link" href="products.jsp">Products</a>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Category</a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="products.jsp?category=0">All Products</a></li>
						<%
							for (Category c : categoryList) {
						%>
						<li><a class="dropdown-item" href="products.jsp?category=<%=c.getCategoryId()%>"><%=c.getCategoryName()%></a></li>
						<%
							}
						%>
					</ul>
				</li>
			</ul>
			<form class="d-flex pe-5" role="search" action="products.jsp" method="get">
				<input name="search" class="form-control me-2" size="50" type="search" placeholder="Search for products" aria-label="Search" style="background-color: white !important;">
				<button class="btn btn-outline-light" type="submit">Search</button>
			</form>
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
				<%
					if (admin != null) {
				%>
				<li class="nav-item">
					<button type="button" class="btn nav-link" data-bs-toggle="modal" data-bs-target="#add-category">
						<i class="fa-solid fa-plus fa-xs"></i>Add Category
					</button>
				</li>
				<li class="nav-item">
					<button type="button" class="btn nav-link" data-bs-toggle="modal" data-bs-target="#add-product">
						<i class="fa-solid fa-plus fa-xs"></i>Add Product
					</button>
				</li>
				<li class="nav-item">
					<a class="nav-link" aria-current="page" href="admin.jsp"><%=admin.getName()%></a>
				</li>
				<li class="nav-item">
					<a class="nav-link" aria-current="page" href="LogoutServlet?user=admin">
						<i class="fa-solid fa-user-slash fa-sm" style="color: #fafafa;"></i>&nbsp;Logout
					</a>
				</li>
				<!-- add product modal-->
				<div class="modal fade" id="add-product" tabindex="-1"
					 aria-labelledby="addProductModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header" style="color: #f2f2f2">
								<h1 class="modal-title fs-5" id="addProductModalLabel">Add
									Product Here</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
							</div>
							<form action="AddOperationServlet" method="post"
								  name="addProductForm" enctype="multipart/form-data">
								<div class="modal-body" style="color: #f2f2f2">
									<input type="hidden" name="operation" value="addProduct">
									<div>
										<label class="form-label"><b>Product Name</b></label> <input
											type="text" name="name" placeholder="Enter product name"
											class="form-control" required>
									</div>
									<div class="mb-2">
										<label class="form-label"><b>Product Description</b></label>
										<textarea class="form-control" name="description" rows="4"
												  placeholder="Enter product description"></textarea>
									</div>
									<div class="row">
										<div class="col-md-6 mb-2">
											<label class="form-label"><b>Unit Price</b></label> <input
												type="number" name="price" placeholder="Enter price"
												class="form-control" required>
										</div>
										<div class="col-md-6 mb-2">
											<label class="form-label"><b>Discount Percentage</b></label> <input
												type="number" name="discount" onblur="validate()"
												placeholder="Enter discount if any!" class="form-control">
										</div>
									</div>
									<div class="row">
										<div class="col-md-6 mb-2">
											<label class="form-label"><b>Product Quantity</b></label> <input
												type="number" name="quantity"
												placeholder="Enter product quantity" class="form-control">
										</div>
										<div class="col-md-6 mb-2">
											<label class="form-label"><b>Select Category Type</b></label> <select
												name="categoryType" class="form-control">
											<option value="0">--Select Category--</option>
											<%
												for (Category c : categoryList) {
											%>
											<option value="<%=c.getCategoryId()%>">
												<%=c.getCategoryName()%>
											</option>
											%>
											<%
												}
											%>
										</select>
										</div>
									</div>
									<div class="mb-2">
										<label class="form-label"><b>Product Image</b></label> <input
											type="file" name="product_img" class="form-control" id="product_img" required>
									</div>
								</div>
								<div class="modal-footer" style="color: #f2f2f2">
									<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close
									</button>
									<button type="submit" class="btn btn-primary me-3">Add
										Product
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- end of modal -->
				<!-- add category modal-->
				<div class="modal fade"  id="add-category" tabindex="-1"
					 aria-labelledby="addCategoryModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header" style="color: #f2f2f2">
								<h1 class="modal-title fs-5" id="addCategoryModalLabel">Add
									Category Here</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
							</div>
							<form action="AddOperationServlet" method="post"
								  enctype="multipart/form-data">
								<div class="modal-body" style="color: #f2f2f2">
									<input type="hidden" name="operation" value="addCategory">
									<%----%>
									<div class="mb-3">
										<label class="form-label"><b>Category Name</b></label> <input
											type="text" name="category_name"
											placeholder="Enter category here" class="form-control" required>
									</div>
									<div class="mb-3">
										<label for="formFile" class="form-label"><b>Category
											Image</b></label> <input class="form-control" type="file"
																	 name="category_img" id="formFile">
									</div>
								</div>
								<div class="modal-footer" style="color: #f2f2f2">
									<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close
									</button>
									<button type="submit" class="btn btn-primary me-3">Add
										Category
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- end of modal -->
				<%
				} else {
                if (user != null) {
					CartServiceImpl cartService = new CartServiceImpl(ConnectionProvider.getConnection());
					int cartCount = cartService.getCartCountByUserId(user.getId());
				%>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="profile.jsp"><%=user.getName()%></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="LogoutServlet?user=user">
                        <i class="fa-solid fa-user-slash" style="color: #fafafa;"></i>&nbsp;Logout
                    </a>
                </li>
<%--                <li class="nav-item">--%>
<%--                    <a class="nav-link" aria-current="page" href="cart.jsp">--%>
<%--                        <i class="fa-solid fa-shopping-cart" style="color: #ffffff;"></i>&nbsp;Cart--%>
<%--                    </a>--%>
<%--                </li>--%>
				<li class="nav-item active pe-3"><a
						class="nav-link position-relative" aria-current="page"
						href="cart.jsp"><i class="fa-solid fa-cart-shopping"
										   style="color: #ffffff;"></i> &nbsp;Cart<span
						class="position-absolute top-1 start-0 translate-middle badge rounded-pill bg-danger"><%=cartCount%></span></a></li>

				<%
				} else {
				%>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="register.jsp">
                        <i class="fa-solid fa-user-plus" style="color: #ffffff;"></i>&nbsp;Register
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="login.jsp">
                        <i class="fa-solid fa-user-lock" style="color: #fafafa;"></i>&nbsp;Login
                    </a>
                </li>
				<%
                }
                }
				%>
			</ul>
		</div>
	</div>
</nav>