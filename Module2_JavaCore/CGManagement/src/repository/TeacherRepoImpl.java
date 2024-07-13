package repository;

import model.Teacher;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static repository.StudentRepoImpl.generateId;

public class TeacherRepoImpl implements ITeacherRepo{
    Scanner scanner = new Scanner(System.in);
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    static{
        Teacher teacher1 = new Teacher(1, "GV-001", "Thanh Công", LocalDate.parse("1991-09-10"), "thanhcong1991@gmail.com", 30000000 );
        Teacher teacher2 = new Teacher(2, "GV-002", "Quang Hùng", LocalDate.parse("1992-10-10"), "quanghong1992@gmail.com", 30000000 );
        Teacher teacher3 = new Teacher(1, "GV-003", "Quốc Hưng", LocalDate.parse("1989-02-15"), "quochung19892@gmail.com", 30000000 );
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
    }

    public static int generateId() {
        return !teachers.isEmpty() ? teachers.get(teachers.size() - 1).getId() + 1 : 1;
    }

    @Override
    public void findAll() {
        System.out.println("Danh sách giảng viên: ");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    @Override
    public Teacher addTeacher() {
        System.out.println("Nhập mã giảng viên: ");
        String teacherCode = scanner.nextLine();
        System.out.println("Nhập tên giảng viên: ");
        String techerName = scanner.nextLine();
        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
        String pattern = "dd/MM/yyyy";
        LocalDate dob = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern(pattern));
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Nhập lương: ");
        int salary = Integer.parseInt(scanner.nextLine());
        return new Teacher(generateId(), teacherCode, techerName, dob, email, salary);
    }

    @Override
    public void save(Teacher teacher) {
        teachers.add(teacher);
    }

    @Override
    public void delete() {
        System.out.println("Nhập mã giảng viên cần xóa: ");
        String teacherCode = scanner.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.getCode().equals(teacherCode)) {
                teachers.remove(teacher);
                System.out.println("\nXóa thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy giảng viên cần xóa!");
    }

    @Override
    public void edit() {
        System.out.println("Nhập mã giảng viên cần sửa: ");
        String teacherCode = scanner.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.getCode().equals(teacherCode)) {
                System.out.println("Nhập tên giảng viên: ");
                teacher.setName(scanner.nextLine());
                System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
                String pattern = "dd/MM/yyyy";
                teacher.setBirthday(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern(pattern)));
                System.out.println("Nhập email: ");
                teacher.setEmail(scanner.nextLine());
                System.out.println("Nhập lương: ");
                teacher.setSalary(Integer.parseInt(scanner.nextLine()));
                teachers.set(teachers.indexOf(teacher), teacher);
                System.out.println("\nSửa thành công thông tin giảng viên!");
                return;
            }
        }
        System.out.println("Không tìm thấy giảng viên cần sửa!");
    }

    @Override
    public void search() {
        System.out.println("Nhập mã giảng viên cần tìm: ");
        String teacherCode = scanner.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.getCode().equals(teacherCode)) {
                System.out.println(teacher);
                return;
            }
        }
        System.out.println("Không tìm thấy giảng viên cần tìm!");
    }

    @Override
    public List<Teacher> getTeachers() {
        return teachers;
    }
}
