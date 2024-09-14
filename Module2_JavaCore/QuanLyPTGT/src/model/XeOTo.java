package model;

public class XeOTo extends Vehicle{

    private int dungTichDongCo;

    private int dungTichCop;

    public XeOTo(String loaiXe, String tenPhuongTien, String hangSanXuat, int namSanXuat, double giaTien, float lePhiTruocBa, String maSoThue, String tenNguoiKhaiThue, int dungTichDongCo, int dungTichCop) {
        super(loaiXe, tenPhuongTien, hangSanXuat, namSanXuat, giaTien, lePhiTruocBa, maSoThue, tenNguoiKhaiThue);
        this.dungTichDongCo = dungTichDongCo;
        this.dungTichCop = dungTichCop;
    }

    public int getDungTichDongCo() {
        return dungTichDongCo;
    }

    public void setDungTichDongCo(int dungTichDongCo) {
        this.dungTichDongCo = dungTichDongCo;
    }

    public int getDungTichCop() {
        return dungTichCop;
    }

    public void setDungTichCop(int dungTichCop) {
        this.dungTichCop = dungTichCop;
    }

    @Override
    public double TinhThueGiaTriGiaTang(double giaTien) {
        return giaTien*10/100;
    }

    @Override
    public String toString() {
        return "XeOto{" +
                "dungTichDongCo=" + dungTichDongCo +
                ", dungTichCop=" + dungTichCop +
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
