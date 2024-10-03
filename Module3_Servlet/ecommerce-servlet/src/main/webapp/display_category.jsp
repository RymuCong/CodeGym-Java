<%@page import="com.cg.casestudy.entity.Message"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<title>View Categories</title>
<%@include file="components/common_css_js.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="components/navbar.jsp"%>

	<!-- Category -->
	<div class="container mt-3">
	<%@include file="components/alert_message.jsp"%>
		<table id="tableCategories" class="table table-hover table-striped">
			<thead>
				<tr class="text-center">
					<th>Image</th>
					<th>Category Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Category c : categoryList) {
				%>
				<tr class="text-center">
					<td><img src="<%=c.getCategoryImage()%>"
						style="width: 60px; height: 60px; width: auto;"></td>
					<td><%=c.getCategoryName()%></td>
					<td><a href="update_category.jsp?cid=<%=c.getCategoryId()%>" role="button" class="btn btn-secondary">Update</a>&emsp;
						<a id="deleteCategory"
						href="AddOperationServlet?cid=<%=c.getCategoryId()%>&operation=deleteCategory"
						class="btn btn-danger" role="button">Delete</a></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>
<script>
	$(document).ready(function() {
		$('#tableCategories').DataTable({
			"dom" : 'lrtip',
			"lengthChange" : false,
			"pageLength" : 5,
			"columns": [
				{ "data": "Image" },
				{ "data": "Category Name" },
				{ "data": "Action" }
			]
		});
	});

	// Attach the click event handler to the delete button
	$(document).on('click', '#deleteCategory', function(e) {
		e.preventDefault();
		var url = $(this).attr('href');
		swal({
			title: "Are you sure?",
			text: "Once deleted you will not be able to recover this category!",
			icon: "warning",
			buttons: true,
			dangerMode: true,
		})
				.then((willDelete) => {
					if (willDelete) {
						window.location.href = url;
					} else {
						swal("Your category is safe!");
					}
				});
	});
</script>

<%--<script src="webjars/jquery/3.5.1/jquery.js"></script>--%>
<script src="webjars/datatables/1.10.7/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="webjars/datatables/1.10.7/css/jquery.dataTables.min.css">
</html>
