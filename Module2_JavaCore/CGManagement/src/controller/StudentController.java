package controller;

import service.IStudentService;
import service.StudentServiceImpl;
import model.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StudentController {
    private final IStudentService studentService = new StudentServiceImpl();

    public void displayAllStudents() {
        studentService.findAll();
    }

    public void addNewStudent() {
        Student addStudent = studentService.addNewStudent();
        studentService.save(addStudent);
    }

    public void updateStudent() {

    }

    public void deleteStudent() {
        studentService.delete();
    }

    public void editStudent() {
        studentService.edit();
    }

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
            fileWriter.write("Class,ID,Code,Name,Birthday,Email\n");            List<Student> students = studentService.getStudents();
            for(Student student : students){
                fileWriter.write(student.getClassName() + "," + student.getId() + "," + student.getCode() + "," + student.getName() + "," + student.getBirthday() + "," + student.getEmail() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void importToCsv() {
        studentService.importFromCsv();
    }
}
