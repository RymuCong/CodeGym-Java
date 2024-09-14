package repository;

import model.Teacher;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherRepoImpl implements ITeacherRepo{
    Scanner scanner = new Scanner(System.in);
    private static ArrayList<Teacher> teachers = new ArrayList<>();

    static{
        Teacher teacher1 = new Teacher(1, "GV-001", "Thanh Công", LocalDate.parse("1991-09-10"), "thanhcong1991@gmail.com", 30000000 );
        Teacher teacher2 = new Teacher(2, "GV-002", "Quang Hùng", LocalDate.parse("1992-10-10"), "quanghong1992@gmail.com", 30000000 );
        Teacher teacher3 = new Teacher(3, "GV-003", "Quốc Hưng", LocalDate.parse("1989-02-15"), "quochung19892@gmail.com", 30000000 );
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);
    }

    public static int generateId() {
        return !teachers.isEmpty() ? teachers.get(teachers.size() - 1).getId() + 1 : 1;
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
        System.out.println("Danh sách giảng viên: ");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    @Override
    public Teacher addTeacher() {
        System.out.println("Nhập mã giảng viên (GV-0000): ");
        String teacherCode = scanner.nextLine();
        teacherCode = checkInput(teacherCode, "^GV-[0-9]{3,4}$");
        System.out.println("Nhập tên giảng viên: ");
        String techerName = scanner.nextLine();
        techerName = checkInput(techerName, "^[a-zA-Z\\s]+$");
        System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
        String pattern = "dd/MM/yyyy";
        String dobStr = scanner.nextLine();
        dobStr = checkInput(dobStr, "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern(pattern));
        System.out.println("Nhập email: ");
        String email = scanner.nextLine();
        email = checkInput(email, "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$");
        System.out.println("Nhập lương: ");
        int salary = 0;
        boolean validSalary = false;
        while (!validSalary) {
            try {
                salary = Integer.parseInt(scanner.nextLine());
                if (salary >= 0) {
                    validSalary = true;
                } else {
                    System.out.println("Lương không hợp lệ! Vui lòng nhập lại: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Lương không hợp lệ! Vui lòng nhập lại: ");
            }
        }
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
        System.out.println("Nhập mã giảng viên cần sửa (GV-xxxx): ");
        String teacherCode = scanner.nextLine();
        teacherCode = checkInput(teacherCode, "^GV-[0-9]{3,4}$");
        for (Teacher teacher : teachers) {
            if (teacher.getCode().equals(teacherCode)) {
                System.out.println("Nhập tên giảng viên: ");
                String techerName = scanner.nextLine();
                techerName = checkInput(techerName, "^[a-zA-Z\\s]+$");
                System.out.println("Nhập ngày sinh (dd/MM/yyyy): ");
                String pattern = "dd/MM/yyyy";
                String dobStr = scanner.nextLine();
                dobStr = checkInput(dobStr, "^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern(pattern));
                System.out.println("Nhập email: ");
                String email = scanner.nextLine();
                email = checkInput(email, "^[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z]+$");
                System.out.println("Nhập lương: ");
                int salary = 0;
                boolean validSalary = false;
                while (!validSalary) {
                    try {
                        salary = Integer.parseInt(scanner.nextLine());
                        if (salary >= 0) {
                            validSalary = true;
                        } else {
                            System.out.println("Lương không hợp lệ! Vui lòng nhập lại: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Lương không hợp lệ! Vui lòng nhập lại: ");
                    }
                }
                Teacher editedTeacher = new Teacher(teacher.getId(), teacherCode , techerName, dob, email, salary);
                teachers.set(teachers.indexOf(teacher), editedTeacher);
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
    public void importFromCsv() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/view/teachers.csv"))) {
            teachers.clear();
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String code = values[1];
                String name = values[2];
                LocalDate birthday = LocalDate.parse(values[3]);
                String email = values[4];
                int salary = Integer.parseInt(values[5]);
                teachers.add(new Teacher(id, code, name, birthday, email, salary));
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
                    throw new IOException("Lỗi tạo thư mục " + directory.getPath());
                }
            }
            FileWriter fileWriter = new FileWriter(new File(directory, "teachers.csv"), false);
            fileWriter.write("ID,Code,Name,Birthday,Email,Salary\n");
            List<Teacher> teachers = getTeachers();
            for(Teacher teacher : teachers){
                fileWriter.write(teacher.getId() + "," + teacher.getCode() + "," + teacher.getName() + "," + teacher.getBirthday() + "," + teacher.getEmail() + "," + teacher.getSalary() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Teacher> getTeachers() {
        return teachers;
    }
}
