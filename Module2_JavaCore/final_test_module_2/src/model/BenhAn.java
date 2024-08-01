package model;

import java.time.LocalDate;

public abstract class BenhAn {

    private int soThuTu;
    private String maBenhAn;
    private String maBenhNhan;
    private String tenBenhNhan;
    private LocalDate ngayNhapVien;
    private LocalDate ngayRaVien;
    private String lyDoNhapVien;

    public BenhAn(int soThuTu, String maBenhAn, String maBenhNhan, String tenBenhNhan, LocalDate ngayNhapVien, LocalDate ngayRaVien, String lyDoNhapVien) {
        this.soThuTu = soThuTu;
        this.maBenhAn = maBenhAn;
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
        this.ngayNhapVien = ngayNhapVien;
        this.ngayRaVien = ngayRaVien;
        this.lyDoNhapVien = lyDoNhapVien;
    }

    public BenhAn() {

    }

    public int getSoThuTu() {
        return soThuTu;
    }

    public void setSoThuTu(int soThuTu) {
        this.soThuTu = soThuTu;
    }

    public String getMaBenhAn() {
        return maBenhAn;
    }

    public void setMaBenhAn(String maBenhAn) {
        this.maBenhAn = maBenhAn;
    }

    public String getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(String maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getTenBenhNhan() {
        return tenBenhNhan;
    }

    public void setTenBenhNhan(String tenBenhNhan) {
        this.tenBenhNhan = tenBenhNhan;
    }

    public LocalDate getNgayNhapVien() {
        return ngayNhapVien;
    }

    public void setNgayNhapVien(LocalDate ngayNhapVien) {
        this.ngayNhapVien = ngayNhapVien;
    }

    public LocalDate getNgayRaVien() {
        return ngayRaVien;
    }

    public void setNgayRaVien(LocalDate ngayRaVien) {
        this.ngayRaVien = ngayRaVien;
    }

    public String getLyDoNhapVien() {
        return lyDoNhapVien;
    }

    public void setLyDoNhapVien(String lyDoNhapVien) {
        this.lyDoNhapVien = lyDoNhapVien;
    }

    @Override
    public String toString() {
        return "BenhAn{" +
                "soThuTu=" + soThuTu +
                ", maBenhAn='" + maBenhAn + '\'' +
                ", maBenhNhan='" + maBenhNhan + '\'' +
                ", tenBenhNhan='" + tenBenhNhan + '\'' +
                ", ngayNhapVien=" + ngayNhapVien +
                ", ngayRaVien=" + ngayRaVien +
                ", lyDoNhapVien='" + lyDoNhapVien + '\'' +
                '}';
    }
}
