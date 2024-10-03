<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Students Management Application</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <header class="custom-header text-center mt-4">
    <h1 class="custom-header">Hệ thống quản lý mặt bằng cho thuê</h1>
  </header>

  <div class="d-flex justify-content-start mb-3">
    <a href="addProperty.jsp" class="btn btn-primary me-2">Thêm mặt bằng</a>
    <button class="btn btn-success" id="filterButton"><i class="bi bi-filter"></i> Bộ lọc</button>
  </div>

  <div id="filterForm" class="card d-none">
    <div class="card-body">
      <form action="filterProperties" method="post">
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="type" class="form-label">Loại Mặt bằng:</label>
            <select class="form-select" name="type" id="type">
              <option value="all">Tất cả</option>
              <option value="Văn phòng chia sẻ">Văn phòng chia sẻ</option>
              <option value="Văn phòng trọn gói">Văn phòng trọn gói</option>
            </select>
          </div>
          <div class="col-md-6">
            <label for="floor" class="form-label">Tầng:</label>
            <select class="form-select" name="floor" id="floor">
              <option value="all">Tất cả</option>
              <c:forEach var="i" begin="1" end="15">
                <option value="${i}">${i}</option>
              </c:forEach>
            </select>
          </div>
        </div>
        <div class="row mb-3">
          <div class="col-md-6">
            <label for="startDate" class="form-label">Ngày cho thuê từ:</label>
            <input type="date" class="form-control" id="startDate" name="startDate">
          </div>
          <div class="col-md-6">
            <label for="endDate" class="form-label">Đến:</label>
            <input type="date" class="form-control" id="endDate" name="endDate">
          </div>
        </div>
        <div class="d-flex justify-content-end">
          <button type="submit" class="btn btn-secondary">Tìm kiếm</button>
        </div>
      </form>
    </div>
  </div>

  <table id="mainTable" class="table table-bordered table-striped text-center align-middle mx-auto" style="width:100%">
    <thead>
    <tr>
        <th>STT</th>
      <th>Mã Mặt Bằng</th>
      <th>Trạng Thái</th>
      <th>Diện Tích</th>
      <th>Tầng</th>
      <th>Loại</th>
      <th>Giá</th>
      <th>Ngày Bắt Đầu</th>
      <th>Ngày Kết Thúc</th>
      <th>Hành Động</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach  var="property" items="${properties}" varStatus="status">
      <tr>
        <td><c:out value="${status.index+1}"/></td>
        <td><c:out value="${property.propertyId}"/></td>
        <td><c:out value="${property.status}"/></td>
        <td><c:out value="${property.area}"/></td>
        <td><c:out value="${property.floor}"/></td>
        <td><c:out value="${property.type}"/></td>
        <td><c:out value="${property.price}"/></td>
        <td><c:out value="${property.startDate}"/></td>
        <td><c:out value="${property.endDate}"/></td>
        <td>
          <form action="deleteProperty" method="post">
            <input type="hidden" name="propertyId" value="${property.propertyId}">
            <input type="submit" value="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa mặt bằng với mã số ${property.propertyId} không?')">
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

<!-- Modal xác nhận action delete -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteModalLabel">Xác nhận xóa</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Bạn có chắc chắn muốn xóa học viên <strong id="studentName"></strong> không?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
        <a id="confirmDelete" href="#" class="btn btn-danger">Xóa</a>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

<script>
  $(document).ready(function () {
    $('#mainTable').DataTable({
      "dom": 'lrtip',
      "lengthChange": false,
      "pageLength": 5,
      "columnDefs": [
        {"orderable": false, "targets": 7}
      ]
    });
  });

  let deleteModal = document.getElementById('deleteModal');
  deleteModal.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let studentId = button.getAttribute('data-id');
    let studentName = button.closest('tr').querySelector('td:nth-child(2)').textContent;
    let confirmDelete = document.getElementById('confirmDelete');
    let studentNameSpan = document.getElementById('studentName');

    confirmDelete.href = '?action=delete&id=' + studentId;
    studentNameSpan.textContent = studentName;
  });

  document.getElementById('filterButton').addEventListener('click', function () {
    let filterForm = document.getElementById('filterForm');
    filterForm.classList.toggle('d-none');
  });
</script>
</body>
</html>