package repository;

import model.Student;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentRepoImpl implements IStudentRepo {
    Scanner scanner = new Scanner(System.in);
    public static ArrayList<Student> students = new ArrayList<>();
    static {
        Student student1 = new Student(1, "HV-001", "Hung", LocalDate.parse("10/10/2004", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "hungCGHN@gmail.com", "C0324M4");
        Student student2 = new Student(2, "HV-002", "Trung", LocalDate.parse("01/01/2003", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "trungCGHN@gmail.com", "C0324M4");
        Student student3 = new Student(3, "HV-003", "Khanh", LocalDate.parse("31/12/1999", DateTimeFormatter.ofPattern("dd/MM/yyyy")), "hungCGHN@gmail.com", "C0324M4");
        students.add(student1);
        students.add(student2);
        students.add(student3);
    }

    public static int generateId() {
        return !students.isEmpty() ? students.get(students.size() - 1).getId() + 1 : 1;
    }

    private String checkInput(String input, String regex) {
        while (!input.matches(regex)) {
            System.out.println("Nhập sai! Vui lòng nhập lại: ");
            input = scanner.nextLine();
        }
        return input;
    }

    @Override
    public void findAll() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Override
    public Student addStudent() {
        System.out.println("Nhập mã học viên (HV-0000): ");
        String studentCode = scanner.nextLine();
        studentCode = checkInput(studentCode, "^HV-[0-9]{3,4}$");
        System.out.println("Nhập tên học viên: ");
        String studentName = scanner.nextLine();
        studentName = checkInput(studentName, "^[a-zA-Z\\s]+$");
        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
        String pattern = "dd/MM/yyyy";
        String dobStr = scanner.nextLine();
        dobStr = checkInput(dobStr, "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern(pattern));
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        email = checkInput(email, "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$");
        System.out.println("Nhập mã lớp (CxxxxMx): ");
        String classCode = scanner.nextLine();
        classCode = checkInput(classCode, "^C[0-9]{4}[A-Z][0-9]$");
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
        System.out.println("Nhập mã học viên cần sửa (HV-xxxx): ");
        String studentCode = scanner.nextLine();
        studentCode = checkInput(studentCode, "^HV-[0-9]{3,4}$");
        for (Student student : students) {
            if (student.getCode().equals(studentCode)) {
                System.out.println("Nhập tên học viên: ");
                String name = scanner.nextLine();
                name = checkInput(name, "^[a-zA-Z\\s]+$");
                System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
                String pattern = "dd/MM/yyyy";
                String dobStr = scanner.nextLine();
                dobStr = checkInput(dobStr, "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern(pattern));
                System.out.println("Nhập email: ");
                String email = scanner.nextLine();
                email = checkInput(email, "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$");
                System.out.println("Nhập mã lớp (CxxxxMx): ");
                String classCode = scanner.nextLine();
                classCode = checkInput(classCode, "^C[0-9]{4}[A-Z][0-9]$");
                Student editedStudent = new Student(student.getId(), studentCode, name, dob, email, classCode);
                students.set(students.indexOf(student), editedStudent);
                System.out.println("\nSửa thông tin học viên thành công!");
                return;
            }
        }
        System.out.println("Không tìm thấy học viên có mã " + studentCode);
    }

    @Override
    public void search() {
        System.out.println("Nhập mã học viên cần tìm: ");
        String studentCode = scanner.nextLine();
        for (Student student : students) {
            if (student.getCode().equals(studentCode)) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Không tìm thấy học viên có mã " + studentCode);
    }

    @Override
    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public void importFromCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/view/students.csv"))) {
            students.clear();
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String className = values[0];
                int id = Integer.parseInt(values[1]);
                String code = values[2];
                String name = values[3];
                LocalDate dob = LocalDate.parse(values[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String email = values[5];
                Student student = new Student(id, code, name, dob, email, className);
                students.add(student);
            }
        } catch (IOException e) {
            System.err.println("Lỗi đọc file CSV: " + e.getMessage());
            return;
        }

        System.out.println("Đã nhập file CSV thành công!");
    }

    @Override
    public void exportToCsv() {
        try {
            File directory = new File("./src/view/");
            if (! directory.exists()){
                boolean result = directory.mkdirs();
                if (!result) {
                    throw new IOException("Failed to create directory " + directory.getPath());
                }
            }
            FileWriter fileWriter = new FileWriter(new File(directory, "students.csv"), false);
            fileWriter.write("Class,ID,Code,Name,Birthday,Email,ClassName\n");
            List<Student> students = getStudents();
            for(Student student : students){
                fileWriter.write(student.getClassName() + "," + student.getId() + "," + student.getCode() + "," + student.getName() + "," + student.getBirthday() + "," + student.getEmail() + "," + student.getClassName() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
