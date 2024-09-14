package model;

import java.time.LocalDate;

public class BenhAnThuong extends BenhAn {

    private Integer phiNamVien;

    public BenhAnThuong(int soThuTu, String maBenhAn, String maBenhNhan ,String tenBenhNhan, LocalDate ngayNhapVien, LocalDate ngayRaVien, String lyDoNhapVien, Integer phiNamVien) {
        super(soThuTu, maBenhAn, maBenhNhan , tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.phiNamVien = phiNamVien;
    }

    public BenhAnThuong() {
        super();
    }

    public Integer getPhiNamVien() {
        return phiNamVien;
    }

    public void setPhiNamVien(Integer phiNamVien) {
        this.phiNamVien = phiNamVien;
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return "BenhAnThuong{"
                + super.toString()
                + ", phiNamVien=" + phiNamVien
                + " }";
    }
}
