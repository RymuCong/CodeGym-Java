<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm Mặt Bằng</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h1>Thêm Mặt Bằng</h1>
    <c:if test="${not empty errorMessage}">
        <p style="color:red">${errorMessage}</p>
    </c:if>
    <form action="addProperty" method="post">
        <div class="mb-3">
            <label for="propertyId" class="form-label">Mã mặt bằng:</label>
            <input type="text" class="form-control" id="propertyId" name="propertyId" value="${property.propertyId}" required>
        </div>
        <div class="mb-3">
            <label for="status" class="form-label">Trạng thái:</label>
            <select class="form-select" id="status" name="status" required>
                <option value="Trống" ${property.status == 'Trống' ? 'selected' : ''}>Trống</option>
                <option value="Hạ tầng" ${property.status == 'Hạ tầng' ? 'selected' : ''}>Hạ tầng</option>
                <option value="Đầy đủ" ${property.status == 'Đầy đủ' ? 'selected' : ''}>Đầy đủ</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="area" class="form-label">Diện tích (m²):</label>
            <input type="number" class="form-control" id="area" name="area" value="${property.area}" required>
        </div>
        <div class="mb-3">
            <label for="floor" class="form-label">Tầng:</label>
            <select class="form-select" id="floor" name="floor" required>
                <c:forEach var="i" begin="1" end="15">
                    <option value="${i}" ${property.floor == i ? 'selected' : ''}>${i}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="type" class="form-label">Loại mặt bằng:</label>
            <select class="form-select" id="type" name="type" required>
                <option value="Văn phòng chia sẻ" ${property.type == 'Văn phòng chia sẻ' ? 'selected' : ''}>Văn phòng chia sẻ</option>
                <option value="Văn phòng trọn gói" ${property.type == 'Văn phòng trọn gói' ? 'selected' : ''}>Văn phòng trọn gói</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="price" class="form-label">Giá tiền (VNĐ):</label>
            <input type="number" class="form-control" id="price" name="price" value="${property.price}" required>
        </div>
        <div class="mb-3">
            <label for="startDate" class="form-label">Ngày bắt đầu:</label>
            <input type="date" class="form-control" id="startDate" name="startDate" value="${property.startDate}" required>
        </div>
        <div class="mb-3">
            <label for="endDate" class="form-label">Ngày kết thúc:</label>
            <input type="date" class="form-control" id="endDate" name="endDate" value="${property.endDate}" required>
        </div>
        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>
</div>
</body>
</html>