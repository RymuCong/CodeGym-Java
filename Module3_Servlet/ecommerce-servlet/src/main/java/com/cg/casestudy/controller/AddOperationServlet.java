package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Category;
import com.cg.casestudy.entity.Message;
import com.cg.casestudy.entity.Product;
import com.cg.casestudy.service.CategoryServiceImpl;
import com.cg.casestudy.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@MultipartConfig
@WebServlet(urlPatterns = "/AddOperationServlet")
public class AddOperationServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CategoryServiceImpl categoryService = new CategoryServiceImpl();
		ProductServiceImpl productService = new ProductServiceImpl();

		String operation = request.getParameter("operation");
		HttpSession session = request.getSession();
		Message message = null;

		switch (operation.trim()) {
			case "addCategory": {
				String categoryName = request.getParameter("category_name");
				Part part = request.getPart("category_img");
				Category category = new Category(categoryName, part.getSubmittedFileName());
				boolean flag = categoryService.saveCategory(category);

				String path = request.getServletContext().getRealPath("/") + "Category" + File.separator + part.getSubmittedFileName();
				saveImage(part, path);

				if (flag) {
					message = new Message("Category added successfully!!", "success", "alert-success");
				} else {
					message = new Message("Something went wrong! Try again!!", "error", "alert-danger");
				}
				session.setAttribute("message", message);
				response.sendRedirect("admin.jsp");
				break;
			}
			case "addProduct": {
				String pName = request.getParameter("name");
				String pDesc = request.getParameter("description");
				int pPrice = Integer.parseInt(request.getParameter("price"));
				int pDiscount = Integer.parseInt(request.getParameter("discount"));
				if (pDiscount < 0 || pDiscount > 100) {
					pDiscount = 0;
				}
				int pQuantity = Integer.parseInt(request.getParameter("quantity"));
				Part part = request.getPart("photo");
				int categoryType = Integer.parseInt(request.getParameter("categoryType"));

				Product product = new Product(pName, pDesc, pPrice, pDiscount, pQuantity, part.getSubmittedFileName(), categoryType);
				boolean flag = productService.saveProduct(product);

				String path = request.getServletContext().getRealPath("/") + "Product" + File.separator + part.getSubmittedFileName();
				saveImage(part, path);

				if (flag) {
					message = new Message("Product added successfully!!", "success", "alert-success");
				} else {
					message = new Message("Something went wrong! Try again!!", "error", "alert-danger");
				}
				session.setAttribute("message", message);
				response.sendRedirect("admin.jsp");
				break;
			}
			case "updateCategory": {
				int cid = Integer.parseInt(request.getParameter("cid"));
				String name = request.getParameter("category_name");
				Part part = request.getPart("category_img");
				if (part.getSubmittedFileName().isEmpty()) {
					String image = request.getParameter("image");
					Category category = new Category(cid, name, image);
					categoryService.updateCategory(category);
				} else {
					Category category = new Category(cid, name, part.getSubmittedFileName());
					categoryService.updateCategory(category);
					String path = request.getServletContext().getRealPath("/") + "Category" + File.separator + part.getSubmittedFileName();
					saveImage(part, path);
				}
				message = new Message("Category updated successfully!!", "success", "alert-success");
				session.setAttribute("message", message);
				response.sendRedirect("display_category.jsp");
				break;
			}
			case "deleteCategory": {
				int cid = Integer.parseInt(request.getParameter("cid"));
				categoryService.deleteCategory(cid);
				response.sendRedirect("display_category.jsp");
				break;
			}
			case "updateProduct": {
				int pid = Integer.parseInt(request.getParameter("pid"));
				String name = request.getParameter("name");
				float price = Float.parseFloat(request.getParameter("price"));
				String description = request.getParameter("description");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				int discount = Integer.parseInt(request.getParameter("discount"));
				if (discount < 0 || discount > 100) {
					discount = 0;
				}
				Part part = request.getPart("product_img");
				int cid = Integer.parseInt(request.getParameter("categoryType"));
				if (cid == 0) {
					cid = Integer.parseInt(request.getParameter("category"));
				}
				if (part.getSubmittedFileName().isEmpty()) {
					String image = request.getParameter("image");
					Product product = new Product(pid, name, description, price, discount, quantity, image, cid);
					productService.updateProduct(product);
				} else {
					Product product = new Product(pid, name, description, price, discount, quantity, part.getSubmittedFileName(), cid);
					productService.updateProduct(product);
					String path = request.getServletContext().getRealPath("/") + "Product" + File.separator + part.getSubmittedFileName();
					saveImage(part, path);
				}
				message = new Message("Product updated successfully!!", "success", "alert-success");
				session.setAttribute("message", message);
				response.sendRedirect("display_products.jsp");
				break;
			}
			case "deleteProduct": {
				int pid = Integer.parseInt(request.getParameter("pid"));
				productService.deleteProduct(pid);
				response.sendRedirect("display_products.jsp");
				break;
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	private void saveImage(Part part, String path) throws IOException {
		File file = new File(path);
		file.getParentFile().mkdirs();
		try (FileOutputStream fos = new FileOutputStream(file);
			 InputStream is = part.getInputStream()) {
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}