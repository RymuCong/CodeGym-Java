let vatLy = prompt("Nhập điểm môn Vật lý");
let hoaHoc = prompt("Nhập điểm môn Hóa học");
let sinhHoc = prompt("Nhập điểm môn Sinh học");

let diemTrungBinh = (parseFloat(vatLy) + parseFloat(hoaHoc) + parseFloat(sinhHoc)) / 3;
let tongDiem = parseFloat(vatLy) + parseFloat(hoaHoc) + parseFloat(sinhHoc);

// alert("Điểm trung bình là: " + diemTrungBinh);
// alert("Tổng điểm là: " + tongDiem);
document.getElementById("bai1").innerHTML = "Điểm trung bình là: " + diemTrungBinh + "<br>" + "Tổng điểm là: " + tongDiem;
