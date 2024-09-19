package com.cg.casestudy.controller;

import com.cg.casestudy.entity.Category;
import com.cg.casestudy.entity.Message;
import com.cg.casestudy.entity.Product;
import com.cg.casestudy.service.CategoryServiceImpl;
import com.cg.casestudy.service.ProductServiceImpl;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig
@WebServlet(urlPatterns = "/AddOperationServlet")
public class AddOperationServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		try {
			// Get the absolute path to the service account key file
			String relativePath = "WEB-INF/firebase.json";
			String absolutePath = getServletContext().getRealPath(relativePath);
			FileInputStream serviceAccount = new FileInputStream(absolutePath);
			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setStorageBucket("cv-pdf-upload.appspot.com") // Your Firebase Storage bucket name
					.build();

			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			throw new ServletException("Failed to initialize Firebase", e);
		}
	}

	// The directory where the uploaded files will be saved
	private static final String UPLOAD_DIR = "src\\webapp\\uploads";

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
				String imageUrl = saveImageToFirebase(part);
				Category category = new Category(categoryName, imageUrl);
				boolean flag = categoryService.saveCategory(category);

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

				String path = request.getServletContext().getRealPath("/") + "data_images/product/" + File.separator + part.getSubmittedFileName();
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
					String path = request.getServletContext().getRealPath("/") + "data_images/category/" + File.separator + part.getSubmittedFileName();
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
				int price = Integer.parseInt(request.getParameter("price"));
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
					String path = request.getServletContext().getRealPath("/") + "data_images/product/" + File.separator + part.getSubmittedFileName();
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
		if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
			throw new IOException("Failed to create directory: " + file.getParentFile());
		}
		try (FileOutputStream fos = new FileOutputStream(file);
			 InputStream is = part.getInputStream()) {
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private String saveImageToFirebase(Part part) throws IOException {
		String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString(); // Ensure this gets the correct file name
		String bucketName = "cv-pdf-upload.appspot.com"; // Correct bucket name

		// Get a reference to the Firebase Storage bucket
		Bucket bucket = StorageClient.getInstance().bucket(bucketName);

		// Correctly specify the path within the bucket where the file will be stored
		String filePath = "images/" + fileName; // This should be the path within the bucket

		BlobId blobId = BlobId.of(bucketName, filePath);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
				.setContentType(part.getContentType()) // Set the content type correctly
				.build();

		// Upload the file to Firebase Storage
		try (InputStream inputStream = part.getInputStream()) {
			// Correct method call for creating a blob in Firebase Storage
			bucket.create(blobInfo.getName(), inputStream, blobInfo.getContentType());
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("Failed to upload file to Firebase Storage", e);
		}

		// Return the URL of the uploaded file
		return String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media", bucketName, URLEncoder.encode(filePath, StandardCharsets.UTF_8.toString()));
	}
}