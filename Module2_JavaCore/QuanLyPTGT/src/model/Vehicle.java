package model;

// Phuong tien giao thong
public abstract class Vehicle {

    public String loaiXe = "";
    protected String tenPhuongTien;
    protected String hangSanXuat;
    protected int namSanXuat;
    protected double giaTien;
    protected float lePhiTruocBa;
    protected String maSoThue;
    protected String tenNguoiKhaiThue;

    public Vehicle(String loaiXe, String tenPhuongTien, String hangSanXuat, int namSanXuat, double giaTien, float lePhiTruocBa, String maSoThue, String tenNguoiKhaiThue) {
        this.loaiXe = loaiXe;
        this.tenPhuongTien = tenPhuongTien;
        this.hangSanXuat = hangSanXuat;
        this.namSanXuat = namSanXuat;
        this.giaTien = giaTien;
        this.lePhiTruocBa = lePhiTruocBa;
        this.maSoThue = maSoThue;
        this.tenNguoiKhaiThue = tenNguoiKhaiThue;
    }

    public double TinhThueTruocBa(double giaTien, float lePhiTruocBa) {
        double Thue = giaTien * lePhiTruocBa;
        return Thue;
    }

    public String getTenPhuongTien() {
        return tenPhuongTien;
    }

    public void setTenPhuongTien(String tenPhuongTien) {
        this.tenPhuongTien = tenPhuongTien;
    }

    public String getLoaiXe() {
        return loaiXe;
    }

    public void setLoaiXe(String loaiXe) {
        this.loaiXe = loaiXe;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public int getNamSanXuat() {
        return namSanXuat;
    }

    public void setNamSanXuat(int namSanXuat) {
        this.namSanXuat = namSanXuat;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public float getLePhiTruocBa() {
        return lePhiTruocBa;
    }

    public void setLePhiTruocBa(float lePhiTruocBa) {
        this.lePhiTruocBa = lePhiTruocBa;
    }

    public String getMaSoThue() {
        return maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getTenNguoiKhaiThue() {
        return tenNguoiKhaiThue;
    }

    public void setTenNguoiKhaiThue(String tenNguoiKhaiThue) {
        this.tenNguoiKhaiThue = tenNguoiKhaiThue;
    }

    public double TinhLePhiDangKyXeMoi(double giaTien) {
        double LePhiDangKyXe = 0;
        if (giaTien <= 15000000) {
            LePhiDangKyXe = 500000;
        } else if (giaTien > 15000000 && giaTien <= 40000000) {
            LePhiDangKyXe = 1000000;
        } else {
            LePhiDangKyXe = 2000000;
        }
        return LePhiDangKyXe;
    }

    public abstract double TinhThueGiaTriGiaTang(double giaTien);

    @Override
    public String toString() {
        return "{" +
                "loaiXe='" + loaiXe + '\'' +
                ", tenPhuongTien='" + tenPhuongTien + '\'' +
                ", hangSanXuat='" + hangSanXuat + '\'' +
                ", namSanXuat=" + namSanXuat +
                ", giaTien=" + giaTien +
                ", lePhiTruocBa=" + lePhiTruocBa +
                ", maSoThue='" + maSoThue + '\'' +
                ", tenNguoiKhaiThue='" + tenNguoiKhaiThue + '\'' +
                '}';
    }
}
