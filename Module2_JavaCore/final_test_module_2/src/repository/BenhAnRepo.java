package repository;

import model.BenhAn;
import model.BenhAnThuong;
import model.BenhAnVip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static view.Main.benhAnList;

public class BenhAnRepo {
    Scanner scanner = new Scanner(System.in);

    public void findAll() {
        if (benhAnList == null) {
            System.err.println("Danh sách bệnh án trống!");
            return;
        }
        System.out.println("Danh sách bệnh án: ");
        for (BenhAn benhAn : benhAnList) {
            if (benhAn instanceof BenhAnThuong) {
                BenhAnThuong benhAnThuong = (BenhAnThuong) benhAn;
                System.out.println(benhAnThuong);
            } else {
                BenhAnVip benhAnVip = (BenhAnVip) benhAn;
                System.out.println(benhAnVip);
            }
        }
    }

    public void delete() {
        System.out.println("Nhập mã bệnh án cần xóa: ");
        String maBenhAn = scanner.nextLine();
        BenhAn benhAnToDelete = null;

        // Check if the record exists
        for (BenhAn benhAn : benhAnList) {
            if (benhAn.getMaBenhAn().equals(maBenhAn)) {
                benhAnToDelete = benhAn;
                break;
            }
        }

        if (benhAnToDelete == null) {
            System.out.println("Không tìm thấy mã bệnh án cần xóa!");
            return;
        }

        // Confirm deletion
        System.out.println("Bạn có chắc chắn muốn xóa bệnh án này không? (Yes/No): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Yes")) {
            benhAnList.remove(benhAnToDelete);
            updateCsvFile();
            System.out.println("Đã xóa bệnh án thành công!");
            findAll(); // Display updated list
        } else {
            System.out.println("Hủy bỏ thao tác xóa.");
        }
    }

    public static void updateCsvFile() {
        if (benhAnList == null) {
            System.err.println("Danh sách bệnh án trống!");
            return;
        }
        try {
            File directory = new File("./src/data/");
            if (!directory.exists()) {
                boolean result = directory.mkdirs();
                if (!result) {
                    throw new IOException("Failed to create directory " + directory.getPath());
                }
            }
            FileWriter fileWriter = new FileWriter(new File(directory, "medical_records.csv"), false);
            fileWriter.write("STT,Mã bệnh án,Mã bệnh nhân,Tên bệnh nhân,Ngày nhập viện,Ngày ra viện,Lý do nhập viện,Chi phí nằm viện hoặc loại VIP\n");
            for (BenhAn benhAn : benhAnList) {
                if (benhAn instanceof BenhAnThuong) {
                    BenhAnThuong benhAnThuong = (BenhAnThuong) benhAn;
                    fileWriter.write(benhAnThuong.getSoThuTu() + "," + benhAnThuong.getMaBenhAn() + "," + benhAnThuong.getMaBenhNhan() + ","
                            + benhAnThuong.getTenBenhNhan() + "," + benhAnThuong.getNgayNhapVien() + "," + benhAnThuong.getNgayRaVien() + "," + benhAnThuong.getLyDoNhapVien() + "," + benhAnThuong.getPhiNamVien() + "\n");
                } else {
                    BenhAnVip benhAnVip = (BenhAnVip) benhAn;
                    fileWriter.write(benhAnVip.getSoThuTu() + "," + benhAnVip.getMaBenhAn() + "," + benhAnVip.getMaBenhNhan() + ","
                            + benhAnVip.getTenBenhNhan() + "," + benhAnVip.getNgayNhapVien() + "," + benhAnVip.getNgayRaVien() + "," + benhAnVip.getLyDoNhapVien() + "," + benhAnVip.getLoaiVip() + "\n");
                }
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
