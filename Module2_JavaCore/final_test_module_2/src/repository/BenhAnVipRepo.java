package repository;

import model.BenhAnVip;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static repository.BenhAnRepo.updateCsvFile;
import static view.Main.benhAnList;

public class BenhAnVipRepo {
    Scanner scanner = new Scanner(System.in);

    public static int generateId() {
        return !benhAnList.isEmpty() ? benhAnList.getLast().getSoThuTu() + 1 : 1;
    }

    private String checkInput(String input, String regex) {
        while (!input.matches(regex)) {
            System.out.println("Nhập sai! Vui lòng nhập lại: ");
            input = scanner.nextLine();
        }
        return input;
    }

    public BenhAnVip add() {
        System.out.println("Nhập mã bệnh án (BA-XXX): ");
        String maBenhAn = scanner.nextLine();
        maBenhAn = checkInput(maBenhAn, "^BA-[0-9]{3}$");
        System.out.println("Nhập mã bệnh nhân (BN-XXX): ");
        String maBenhNhan = scanner.nextLine();
        maBenhNhan = checkInput(maBenhNhan, "^BN-[0-9]{3}$");
        System.out.println("Nhập tên bệnh nhân: ");
        String tenBenhNhan = scanner.nextLine();
        tenBenhNhan = checkInput(tenBenhNhan, "^[a-zA-Z\\s]+$");
        System.out.println("Nhập nhập viện (dd/MM/yyyy): ");
        String ngayNhapVientxt = scanner.nextLine();
        ngayNhapVientxt = checkInput(ngayNhapVientxt, "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        LocalDate ngayNhapVien = LocalDate.parse(ngayNhapVientxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate ngayRaVien;
        do {
            System.out.println("Nhập ra viện (dd/MM/yyyy): ");
            String ngayRaVientxt = scanner.nextLine();
            ngayRaVientxt = checkInput(ngayRaVientxt, "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
            ngayRaVien = LocalDate.parse(ngayRaVientxt, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (ngayRaVien.isBefore(ngayNhapVien)) {
                System.out.println("Ngày ra viện không thể trước ngày nhập viện. Vui lòng nhập lại.");
            }
        } while (ngayRaVien.isBefore(ngayNhapVien));
        System.out.println("Nhập lý do nhập viện: ");
        String lyDoNhapVien = scanner.nextLine();
        System.out.println("Nhập loại VIP: ");
        String loaiVip = scanner.nextLine();
        // Vip 1, Vip 2, Vip 3
        loaiVip = checkInput(loaiVip.toLowerCase(), "^(vip 1|vip 2|vip 3)$");
        BenhAnVip benhAnVip = new BenhAnVip(generateId(), maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVip);
        benhAnList.add(benhAnVip);
        updateCsvFile();
        return benhAnVip;
    }

}
