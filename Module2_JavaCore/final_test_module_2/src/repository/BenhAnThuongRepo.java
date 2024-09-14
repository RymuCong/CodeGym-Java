package repository;

import model.BenhAnThuong;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static repository.BenhAnRepo.updateCsvFile;
import static view.Main.benhAnList;

public class BenhAnThuongRepo {
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

    public BenhAnThuong add() {
        // Tạo và throw được DuplicateMedicalRecordException

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
        System.out.println("Nhập phí nằm viện: ");
        int benhPhi = 0;
        boolean validBenhPhi = false;
        while (!validBenhPhi) {
            try {
                benhPhi = Integer.parseInt(scanner.nextLine());
                if (benhPhi >= 0) {
                    validBenhPhi = true;
                } else {
                    System.out.println("Phí nằm viện không hợp lệ! Vui lòng nhập lại: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Phí nằm viện không hợp lệ! Vui lòng nhập lại: ");
            }
        }
        BenhAnThuong benhAnThuong = new BenhAnThuong(generateId(), maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, benhPhi);
        benhAnList.add(benhAnThuong);
        updateCsvFile();
        return benhAnThuong;
    }
}


//    @Override
//    public void importFromCsv() {
//        try (BufferedReader br = new BufferedReader(new FileReader("src/view/students.csv"))) {
//            benhAnList.clear();
//            String line;
//            // Skip the header line
//            br.readLine();
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                String className = values[0];
//                int id = Integer.parseInt(values[1]);
//                String code = values[2];
//                String name = values[3];
//                LocalDate dob = LocalDate.parse(values[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//                String email = values[5];
//                BenhAnThuong benhAnThuong = new BenhAnThuong(id, code, name, dob, email, className);
//                benhAnList.add(benhAnThuong);
//            }
//        } catch (IOException e) {
//            System.err.println("Lỗi đọc file CSV: " + e.getMessage());
//            return;
//        }
//
//        System.out.println("Đã nhập file CSV thành công!");
//    }
//
//    @Override
//    public void exportToCsv() {
//        try {
//            File directory = new File("./src/view/");
//            if (! directory.exists()){
//                boolean result = directory.mkdirs();
//                if (!result) {
//                    throw new IOException("Failed to create directory " + directory.getPath());
//                }
//            }
//            FileWriter fileWriter = new FileWriter(new File(directory, "students.csv"), false);
//            fileWriter.write("Class,ID,Code,Name,Birthday,Email,ClassName\n");
//            List<BenhAnThuong> benhAnList = getStudents();
//            for(BenhAnThuong benhAnThuong : benhAnList){
//                fileWriter.write(benhAnThuong.getClassName() + "," + benhAnThuong.getId() + "," + benhAnThuong.getCode() + "," + benhAnThuong.getName() + "," + benhAnThuong.getBirthday() + "," + benhAnThuong.getEmail() + "," + benhAnThuong.getClassName() + "\n");
//            }
//            fileWriter.close();
//        } catch (IOException e) {
//            System.err.println(e.getMessage());
//        }
//    }

