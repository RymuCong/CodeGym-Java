package view;

import controller.BenhAnController;
import model.BenhAn;
import model.BenhAnThuong;
import model.BenhAnVip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
//view -> controller -> service -> repo

public class Main {
    private final static Scanner sc = new Scanner(System.in);

    public static ArrayList<BenhAn> benhAnList = new ArrayList<>();

    static BenhAnController benhAnController = new BenhAnController();

    public static void main(String[] args) {
        benhAnList = readFile();
        if (benhAnList == null) {
            benhAnList = new ArrayList<>();
        }
        MenuFunc();
    }

    public static ArrayList<BenhAn> readFile() {
        if (benhAnList == null) {
            benhAnList = new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/medical_records.csv"))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int soThuTu = Integer.parseInt(values[0]);
                String maBenhAn = values[1];
                String maBenhNhan = values[2];
                String tenBenhNhan = values[3];
                LocalDate ngayNhapVien = LocalDate.parse(values[4]);
                LocalDate ngayRaVien = LocalDate.parse(values[5]);
                String lyDoNhapVien = values[6];
                if (values[7].matches("\\d+")) {
                    int phiNamVien = Integer.parseInt(values[7]);
                    benhAnList.add(new BenhAnThuong(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, phiNamVien));
                } else {
                    String loaiVip = values[7];
                    benhAnList.add(new BenhAnVip(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVip));
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file CSV: " + e.getMessage());
            return null;
        }
        System.out.println("Đã nhập file CSV thành công!");
        System.out.println("Danh sách bệnh án: " + benhAnList);
        return benhAnList;
    }

    public static void MenuFunc() {
        do {
            System.out.println("""
                    ----------------Chương trình quản lý Codegym ---------------
                    1. Thêm mới bệnh án
                    2. Xóa
                    3. Xem danh sách bệnh án
                    0. Thoát chương trình
                    \s""");
            int choice = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Nhập lựa chọn: ");
                    choice = Integer.parseInt(sc.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Yêu cầu nhập một số hợp lệ!");
                }
            }
            switch (choice) {
                case 0:
                    System.exit(0);
                case 1:
                    System.out.println("1. Thêm bệnh án thường");
                    System.out.println("2. Thêm bệnh án VIP");
                    int choiceAdd = 0;
                    boolean validInputAdd = false;
                    while (!validInputAdd) {
                        try {
                            System.out.print("Nhập lựa chọn: ");
                            choiceAdd = Integer.parseInt(sc.nextLine());
                            validInputAdd = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Yêu cầu nhập một số hợp lệ!");
                        }
                    }
                    switch (choiceAdd) {
                        case 1:
                            benhAnController.addBenhAnThuong();
                            break;
                        case 2:
                            benhAnController.addBenhAnVip();
                            break;
                        default:
                            System.out.println("Yêu cầu bạn nhập đúng lựa chọn!");
                    }
                    break;
                case 2:
                    benhAnController.deleteBenhAn();
                    break;
                case 3:
                    benhAnController.xemBenhAn();
                    break;
                default:
                    System.out.println("Yêu cầu bạn nhập đúng lựa chọn!");
            }
        } while (true);
    }
}
