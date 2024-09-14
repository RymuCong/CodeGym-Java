package model;

public class XeMay extends Vehicle  {

    private int dungTichDongCo;

    public XeMay(String loaiXe, String tenPhuongTien, String hangSanXuat, int namSanXuat, double giaTien, float lePhiTruocBa, String maSoThue, String tenNguoiKhaiThue, int dungTichDongCo) {
        super(loaiXe, tenPhuongTien, hangSanXuat, namSanXuat, giaTien, lePhiTruocBa, maSoThue, tenNguoiKhaiThue);
        this.dungTichDongCo = dungTichDongCo;
    }

    public int getDungTichDongCo() {
        return dungTichDongCo;
    }

    public void setDungTichDongCo(int dungTichDongCo) {
        this.dungTichDongCo = dungTichDongCo;
    }

    @Override
    public double TinhThueGiaTriGiaTang(double giaTien) {
        return giaTien*10/100;
    }

    @Override
    public String toString() {
        return "XeMay{" +
                "dungTichDongCo=" + dungTichDongCo +
                ", loaiXe='" + loaiXe + '\'' +
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

