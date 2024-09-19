<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1" %>
<%@page import="com.cg.casestudy.entity.Message" %>
<%@ page import="com.cg.casestudy.entity.Admin" %>
<%@page errorPage="error_exception.jsp" %>
<%
    Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
    if (activeAdmin == null) {
        Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
        session.setAttribute("message", message);
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Page</title>
    <%@include file="components/common_css_js.jsp" %>
</head>
<body>
<!--navbar -->
<%@include file="components/navbar.jsp" %>
<%----%>
<!--admin dashboard -->
<div class="container-fluid py-4 px-3">
    <%@include file="components/alert_message.jsp" %>
    <div class="row">
        <div class="container text-center" id="details">
            <img src="images/admin.png" style="max-width: 180px;"
                 class="img-fluid">
            <h3>
                Welcome "<%=activeAdmin.getName()%>"
            </h3>
        </div>
    </div>
    <div class="container">
        <div class="row px-3 py-3">
            <div class="col-md-4">
                <a href="display_category.jsp">
                    <div class="card text-bg-light mb-3 text-center">
                        <div class="card-body">
                            <img src="images/categories.png" style="max-width: 80px;"
                                 class="img-fluid">
                            <h4 class="card-title mt-3">Category</h4>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-4">
                <a href="display_products.jsp">
                    <div class="card text-bg-light mb-3 text-center">
                        <div class="card-body">
                            <img src="images/products.png" style="max-width: 80px;"
                                 class="img-fluid">
                            <h4 class="card-title mt-3">Products</h4>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-4">
                <a href="error_page.jsp">
                    <div class="card text-bg-light mb-3 text-center">
                        <div class="card-body">
                            <img src="images/order.png" style="max-width: 80px;"
                                 class="img-fluid">
                            <h4 class="card-title mt-3">Order</h4>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row px-3">
            <div class="col-md-4 offset-md-2">
                <a href="display_users.jsp">
                    <div class="card text-bg-light mb-3 text-center">
                        <div class="card-body">
                            <img src="images/users.png" style="max-width: 80px;"
                                 class="img-fluid">
                            <h4 class="card-title mt-3">User's</h4>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-md-4">
                <a href="display_admin.jsp">
                    <div class="card text-bg-light mb-3 text-center">
                        <div class="card-body">
                            <img src="images/add-admin.png" style="max-width: 80px;"
                                 class="img-fluid">
                            <h4 class="card-title mt-3">Admin</h4>
                        </div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>
<!--end-->
<%----%>

<%----%>

<%----%>
<script type="text/javascript">
    function validate() {
        var dis = document.addProductForm.discount.value;
        if (dis > 100 || dis < 0) {
            alert("Discount need tobe between 0-100 !");
            //document.addProductForm.discount.focus();
            return false;
        }
    }
</script>
</body>
</html>