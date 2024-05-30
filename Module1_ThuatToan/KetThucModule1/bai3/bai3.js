class SoTietKiem {
    constructor(maSo, loaiTietKiem, hoTenKhachHang, chungMinhNhanDan, ngayMoSo) {
        this.maSo = maSo;
        this.loaiTietKiem = loaiTietKiem;
        this.hoTenKhachHang = hoTenKhachHang;
        this.chungMinhNhanDan = chungMinhNhanDan;
        this.ngayMoSo = ngayMoSo;
    }
}

class QuanLySoTietKiem {
    constructor() {
        this.danhSachSoTietKiem = [];
    }

    themSoTietKiem(maSo, loaiTietKiem, hoTenKhachHang, chungMinhNhanDan, ngayMoSo) {
        if (this.danhSachSoTietKiem.some(stk => stk.maSo === maSo)) {
            alert("Lỗi: Mã sổ tiết kiệm đã tồn tại.");
            return true;
        }

        if (maSo.length > 5 || loaiTietKiem.length > 10 ||
            hoTenKhachHang.length > 30 || maSo.length <= 0 ||
            loaiTietKiem.length <= 0 || hoTenKhachHang.length <= 0 ||
            chungMinhNhanDan.length <= 0)
        {
            alert("Lỗi: Dữ liệu nhập vào không hợp lệ.");
            return true;
        }

        if (chuanHoaNgayMoSo(ngayMoSo) === null) {
            alert("Lỗi: Ngày mở sổ không hợp lệ.");
            return true;
        }

        if (!chuanHoaCMND(chungMinhNhanDan)) {
            alert("Lỗi: Chứng minh nhân dân không hợp lệ.");
            return true;
        }

        const soTietKiemMoi = new SoTietKiem(maSo, loaiTietKiem, hoTenKhachHang, chungMinhNhanDan, ngayMoSo);
        this.danhSachSoTietKiem.push(soTietKiemMoi);
        alert("Thêm sổ tiết kiệm thành công.");
        this.capNhatBang();
        return false;
    }

    xoaSoTietKiem(maSo) {
        let index = this.danhSachSoTietKiem.findIndex(stk => stk.maSo === maSo);
        if (index === -1) {
            alert("Lỗi: Mã sổ tiết kiệm không tồn tại.");
            return true;
        }

        if (confirm("Bạn có chắc muốn xóa sổ tiết kiệm này không?")) {
            this.danhSachSoTietKiem.splice(index, 1);
            alert("Sổ tiết kiệm đã được xóa.");
            this.capNhatBang();
            return false;
        }
        return true;
    }

    capNhatBang() {
        const tbody = document.getElementById('bangSoTietKiem').getElementsByTagName('tbody')[0];
        tbody.innerHTML = ''; // Xóa các hàng hiện tại
        this.danhSachSoTietKiem.forEach(stk => {
            const tr = document.createElement('tr');
            tr.innerHTML = `<td>${stk.maSo}</td><td>${stk.loaiTietKiem}</td><td>${stk.hoTenKhachHang}</td><td>${stk.chungMinhNhanDan}</td><td>${stk.ngayMoSo}</td>`;
            tbody.appendChild(tr);
        });
    }
}

function chuanHoaNgayMoSo(ngayMoSo) {
    // Kiểm tra định dạng ngày tháng
    const regex = /^(0[1-9]|[12][0-9]|3[01])\/(0[1-9]|1[012])\/(19|20)\d\d$/;
    if (!regex.test(ngayMoSo)) {
        return null; // Trả về null nếu ngày tháng không hợp lệ
    }

    // Chia ngày tháng thành các phần
    let [ngay, thang, nam] = ngayMoSo.split('/');

    // Tạo một đối tượng Date từ các phần
    let date = new Date(nam, thang - 1, ngay);

    // Kiểm tra xem nó có phải là ngày hợp lệ hay không
    if (date && (date.getMonth() + 1) === thang && date.getDate() === Number(ngay)) {
        return null; // Trả về null nếu ngày tháng không hợp lệ
    }

    // Trả về ngày tháng đã được chuẩn hóa
    return `${ngay}/${thang}/${nam}`;
}

function chuanHoaCMND(chungMinhNhanDan) {
    const regex = /^\d+$/;
    return regex.test(chungMinhNhanDan);
}

const quanLy = new QuanLySoTietKiem();

document.getElementById('themButton').addEventListener('click', function() {
    let soTietKiemMoi;
    do
    {
        let maSo = prompt("Nhập mã sổ tiết kiệm:");
        let loaiTietKiem = prompt("Nhập loại tiết kiệm:");
        let hoTenKhachHang = prompt("Nhập họ tên khách hàng:");
        let chungMinhNhanDan = prompt("Nhập chứng minh nhân dân:");
        let ngayMoSo = prompt("Nhập ngày mở sổ (dd/MM/yyyy):");
        soTietKiemMoi = new SoTietKiem(maSo, loaiTietKiem, hoTenKhachHang, chungMinhNhanDan, ngayMoSo);
    } while (quanLy.themSoTietKiem(soTietKiemMoi.maSo, soTietKiemMoi.loaiTietKiem, soTietKiemMoi.hoTenKhachHang, soTietKiemMoi.chungMinhNhanDan, soTietKiemMoi.ngayMoSo));
});

document.getElementById('xoaButton').addEventListener('click', function() {
    let maSo;
    do {
        maSo = prompt("Nhập mã sổ tiết kiệm bạn muốn xóa:");
    } while (quanLy.xoaSoTietKiem(maSo));
});
