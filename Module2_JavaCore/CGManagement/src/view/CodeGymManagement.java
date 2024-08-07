package view;

import controller.StudentController;
import controller.TeacherController;

import java.util.Scanner;
//view -> controller -> service -> repo

public class CodeGymManagement {
    private final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        MenuFunc();
    }

    public static void MenuFunc() {
        do {
            System.out.println("""
                    ----------------Chương trình quản lý Codegym ---------------
                    1. Quản lí học viên
                    2. Quản lí giảng viên
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
                    StudentManagement();
                    break;
                case 2:
                    TeacherManagement();
                    break;
                default:
                    System.out.println("Yêu cầu bạn nhập đúng lựa chọn!");
            }
        } while (true);
    }

    public static void StudentManagement() {
        StudentController studentController = new StudentController();
        do {
            System.out.println("""
                    ---------Quản lí học viên----------
                    1. Hiển thị danh sách HV
                    2. Thêm học viên
                    3. Xóa học viên
                    4. Chỉnh sửa thông tin HV
                    5. Xuất CSV
                    6. Đọc CSV
                    0. Quay lại trang chủ
                    \s""");
            int opt = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Nhập lựa chọn: ");
                    opt = Integer.parseInt(sc.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Yêu cầu nhập một số hợp lệ!");
                }
            }
            switch (opt) {
                case 0:
                    return;
                case 1:
                    studentController.displayAllStudents();
                    break;
                case 2:
                    studentController.addNewStudent();
                    break;
                case 3:
                    studentController.deleteStudent();
                    break;
                case 4:
                    studentController.editStudent();
                    break;
                case 5:
                    studentController.exportToCsv();
                    break;
                case 6:
                    studentController.importToCsv();
                    break;
                default:
                    System.out.println("Yêu cầu nhập đúng lựa chọn!");
            }
        } while (true);

    }

    public static void TeacherManagement() {
        TeacherController teacherController = new TeacherController();
        do {
            System.out.println("""
                    ---------Quản lí giảng viên----------
                    1. Hiển thị danh sách GV
                    2. Thêm giảng viên
                    3. Xóa giảng viên
                    4. Chỉnh sửa thông tin GV
                    5. Tìm kiếm giảng viên
                    6. Xuất CSV
                    7. Đọc CSV
                    0. Quay lại trang chủ
                    \s""");
            int opt = 0;
            boolean validInput = false;
            while (!validInput) {
                try {
                    System.out.print("Nhập lựa chọn: ");
                    opt = Integer.parseInt(sc.nextLine());
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Yêu cầu nhập một số hợp lệ!");
                }
            }
            switch (opt) {
                case 0:
                    System.out.println("Quay lại trang chủ...");
                    return;
                case 1:
                    teacherController.displayAllTeachers();
                    break;
                case 2:
                    teacherController.addNewTeacher();
                    break;
                case 3:
                    teacherController.deleteTeacher();
                    break;
                case 4:
                    teacherController.editTeacher();
                    break;
                case 5:
                    teacherController.searchTeacher();
                    break;
                case 6:
                    teacherController.exportToCsv();
                    break;
                case 7:
                    teacherController.importFromCsv();
                    break;
            }
        } while (true);
    }
}
