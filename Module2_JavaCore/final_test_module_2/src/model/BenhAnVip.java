package model;

import java.time.LocalDate;

public class BenhAnVip extends BenhAn {

    private String loaiVip;

    public BenhAnVip(int soThuTu, String maBenhAn, String maBenhNhan , String tenBenhNhan, LocalDate ngayNhapVien, LocalDate ngayRaVien, String lyDoNhapVien, String loaiVip) {
        super(soThuTu, maBenhAn, maBenhNhan ,tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.loaiVip = loaiVip;
    }

    public BenhAnVip() {
        super();
    }

    public String getLoaiVip() {
        return loaiVip;
    }

    public void setLoaiVip(String loaiVip) {
        this.loaiVip = loaiVip;
    }

    @Override
    public String toString() {
        return "BenhAnVip{"
                + super.toString()
                + ", loaiVip=" + loaiVip
                + " }";
    }
}
