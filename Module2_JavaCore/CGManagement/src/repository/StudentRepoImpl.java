package repository;

import model.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentRepoImpl implements IStudentRepo {
    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Student> students = new ArrayList<>();
    static {
        Student student1 = new Student(1, "HV-001", "Hung", LocalDate.parse("2004-10-10"), "hungCGHN@gmail.com", "C0324M4");
        Student student2 = new Student(2, "HV-002", "Trung", LocalDate.parse("2003-12-12"), "trungCGHN@gmail.com", "C0324M4");
        Student student3 = new Student(3, "HV-003", "Khanh", LocalDate.parse("2002-05-14"), "hungCGHN@gmail.com", "C0324M4");
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    public static int generateId() {
        return !students.isEmpty() ? students.get(students.size() - 1).getId() + 1 : 1;
    }

    @Override
    public void findAll() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Override
    public Student addStudent() {
        System.out.println("Nhập mã học viên: ");
        String studentCode = scanner.nextLine();
        System.out.println("Nhập tên học viên: ");
        String studentName = scanner.nextLine();
        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
        String pattern = "dd/MM/yyyy";
        LocalDate dob = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern(pattern));
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        System.out.println("Nhập mã lớp: ");
        String classCode = scanner.nextLine();
        return new Student(generateId(), studentCode, studentName, dob, email, classCode);
    }

    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public void delete() {
        System.out.println("Nhập mã học viên cần xóa: ");
        String studentCode = scanner.nextLine();
        for (Student student : students) {
            if (student.getCode().equals(studentCode)) {
                students.remove(student);
                System.out.println("\nXóa thành công học viên!");
                return;
            }
        }
        System.out.println("Không tìm thấy học viên có mã " + studentCode);
    }

    @Override
    public void edit() {
        System.out.println("Nhập mã học viên cần sửa: ");
        String studentCode = scanner.nextLine();
        for (Student student : students) {
            if (student.getCode().equals(studentCode)) {
                System.out.println("Nhập tên học viên: ");
                String name = scanner.nextLine();
                System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
                String pattern = "dd/MM/yyyy";
                LocalDate dob = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern(pattern));
                System.out.println("Nhập email: ");
                String email = scanner.nextLine();
                System.out.println("Nhập tên lớp: ");
                String className = scanner.nextLine();
                Student editedStudent = new Student(student.getId(), studentCode, name, dob, email, className);
                students.set(students.indexOf(student), editedStudent);
                System.out.println("\nSửa thông tin học viên thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy học viên có mã " + studentCode);
    }

}
