package repository;

import model.XeMay;

import java.util.ArrayList;
import java.util.Scanner;

public class XeMayRepositoryImpl implements XeMayRepository {
    Scanner scanner = new Scanner(System.in);

    public static ArrayList<XeMay> vehicles = new ArrayList<>();

    static {
        vehicles.add(new XeMay("XeMay", "Xe số 1", "Honda", 2021, 100000, 100, "123456", "Nguyen Van A", 150));
        vehicles.add(new XeMay("XeMay", "Xe số 2", "Yamaha", 2020, 200000, 200, "123456", "Nguyen Van B", 200));
        vehicles.add(new XeMay("XeMay", "Xe số 3", "Suzuki", 2019, 300000, 300, "123456", "Nguyen Van C", 250));
    }

    @Override
    public void them() {
        String tenxe;
        do {
            System.out.println("Nhập tên xe: ");
            tenxe = scanner.next();
            // Kiểm tra đầu vào với biểu thức chính quy
            if (!tenxe.matches("[a-zA-Z0-9]+")) {
                System.out.println("Tên xe hợp lệ mời nhập lại.");
            }
        } while (!tenxe.matches("[a-zA-Z0-9]+"));
        String hang_san_xuat;
        do {
            System.out.println("nhập tên hãng sản xuất ");
            hang_san_xuat = scanner.next();
            // Kiểm tra đầu vào với biểu thức chính quy
            if (!hang_san_xuat.matches("[a-zA-Z]+")) {
                System.out.println("Tên hãng sản xuất hợp lệ mời nhập lại.");
            }
        } while (!hang_san_xuat.matches("[a-zA-Z]+"));
        // System.out.println("nhập năm sản xuất ");

        int nam_hien_tai = java.time.Year.now().getValue();
        int nam_san_xuat = 0;

        do {
            try {
                System.out.println("Nhập năm sản xuất:");
                nam_san_xuat = Integer.parseInt(scanner.nextLine());
                if (nam_san_xuat > nam_hien_tai) {
                    System.out.println("Năm sản xuất không được vượt quá năm hiện tại.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Năm sản xuất không hợp lệ. Vui lòng nhập lại.");
            }
        } while (true);


        int dung_tich;
        do {
            System.out.println("Nhập dung tích xe: ");
            if (scanner.hasNextInt()) {
                dung_tich = scanner.nextInt();
                break;
            } else {
                System.out.println("dung tích xe không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);


        double gia_tien;
        do {
            System.out.println("Nhập giá tiền : ");
            if (scanner.hasNextInt()) {
                gia_tien = scanner.nextInt();
                break;
            } else {
                System.out.println("giá tiền không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);

        System.out.print("Nhập Lệ Phí Trước Ba: ");
        float le_phi_truoc_ba;
        do {
            if (scanner.hasNextInt()) {
                le_phi_truoc_ba = scanner.nextInt();
                break;
            } else {
                System.out.println("Lệ Phí Trước Ba không hợp lệ. Vui lòng nhập lại.");
                scanner.next(); // Đọc và loại bỏ giá trị không hợp lệ khỏi bộ đệm của scanner
            }
        } while (true);
        System.out.print("Nhập tên người khai thuế: ");
        String ten_nguoi_khai_thue = scanner.next();
        String ma_so_thue;
        do {
            System.out.println("Nhập mã số thuế : ");
            ma_so_thue = scanner.next();
            if (maSoThueChecker(vehicles,ma_so_thue)){
                System.out.println("Mã số thuế không hợp lệ");
            }
        }while (maSoThueChecker(vehicles,ma_so_thue));

        XeMay xeMay = new XeMay("XeMay", tenxe, hang_san_xuat, nam_san_xuat, gia_tien, le_phi_truoc_ba, ma_so_thue, ten_nguoi_khai_thue, dung_tich);

        // thêm đối tượng xe máy vào danh sach vehicles
        vehicles.add(xeMay);
        System.out.print("Thêm vehicle thành công: ");
        //  lưu danh sách vehicles vào tệp tin
        saveData();
    }

    @Override
    public void xemThongTin() {
        System.out.println("Xem thong tin xe may");
    }

    @Override
    public void xoa() {
        System.out.println("Xoa xe may");
    }

    @Override
    public void sua() {
        System.out.println("Sua xe may");
    }

    @Override
    public void save() {
        System.out.println("Save xe may");
    }

    @Override
    public ArrayList<XeMay> docFile() {
        return null;
    }
}
